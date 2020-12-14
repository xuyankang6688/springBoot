/**
 * @example：oss web直传
 * @author: javaGuo
 * @date: 2018-12-13
 * @describe: plupload中文使用文档，需要的可以去看看：http://www.cnblogs.com/2050/p/3913184.html
 */
// oss的配置参数
accessid = 'LTAI06QfAvmNq3Wh';
accesskey = 'LQDLbGu3u64kvyWy5V5gfMUKzDEyao';
// 请求地址
host = 'https://cxqc-2019.oss-cn-hangzhou.aliyuncs.com/';

// 下面三个字段不用配置
// 文件上传路径
g_dirname = '';
// 文件名
g_object_name = '';
// 文件名生成方式
g_object_name_type = '';

// 过滤上传的文件
var filters = {};

// 是否允许多文件上传
var multiSelection = true;

/** OSS上传接口参数 * */
var policyText = {
    "expiration": "2020-01-01T12:00:00.000Z", // 设置该Policy的失效时间，超过这个失效时间之后，就没有办法通过这个policy上传文件了
    "conditions": [
        ["content-length-range", 1, 1024 * 1024 * 10] // 设置上传文件的大小限制（单位为：b），第二位为最小限制，第三位为最大限制
    ]
};

/** OSS请求签名 * */
var policyBase64 = Base64.encode(JSON.stringify(policyText));
message = policyBase64;
var bytes = Crypto.HMAC(Crypto.SHA1, message, accesskey, {asBytes: true});
var signature = Crypto.util.bytesToBase64(bytes);

/** 设置上传文件的名字类型（原文件名为：local_name，随机文件名为：random_name） * */
function check_object_radio(fileUploadParam) {
    g_object_name_type = 'random_name';
    if (fileUploadParam.nameRandom == false) {
        g_object_name_type = 'local_name';
    }
}

// 生成上传路径
function get_dirname(fileUploadParam) {
    dir = 'test/' + getNowDate();
    if (!isEmpty(fileUploadParam.dirPath)) {
        dir = fileUploadParam.dirPath;
    }
    if (dir != '' && dir.indexOf('/') == 0) {
        dir = dir.substring(1);
    }
    g_dirname = dir + '/';
}

// 随机生成字符串（用于文件名）
function random_string(len) {
    len = len || 32;
    var chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
    var maxPos = chars.length;
    var pwd = '';
    for (i = 0; i < len; i++) {
        pwd += chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
}

// 获取文件后缀
function get_suffix(filename) {
    pos = filename.lastIndexOf('.')
    suffix = ''
    if (pos != -1) {
        suffix = filename.substring(pos)
    }
    return suffix;
}

// 生成文件名
function calculate_object_name(filename) {
    if (g_object_name_type == 'local_name') {
        g_object_name += "${filename}"
    } else if (g_object_name_type == 'random_name') {
        suffix = get_suffix(filename)
        g_object_name = g_dirname + random_string(10) + suffix
    }
    return ''
}

// 获取文件名
function get_uploaded_object_name(filename) {
    if (g_object_name_type == 'local_name') {
        tmp_name = g_object_name;
        tmp_name = tmp_name.replace("${filename}", filename);
        return tmp_name;
    } else if (g_object_name_type == 'random_name') {
        return g_object_name;
    }
}

// 设置上传属性
function set_upload_param(up, filename, ret, fileUploadParam) {
    get_dirname(fileUploadParam);

    g_object_name = g_dirname;
    if (filename != '') {
        suffix = get_suffix(filename);
        calculate_object_name(filename);
    }
    new_multipart_params = {
        'key': g_object_name,
        'policy': policyBase64,
        'OSSAccessKeyId': accessid,
        'success_action_status': '200',                         // 让服务端返回200,不然，默认会返回204
        'signature': signature,                                  // 签名
        'multi_selection': multiSelection,	                  // 是否允许多文件上传
    };

    up.setOption({
        'multipart_params': new_multipart_params,
        'filters': filters
    });

    up.start();
}

/** 获取当前年月日 * */
function getNowDate() {
    var myDate = new Date();
    return myDate.getFullYear() + '' + (myDate.getMonth() + 1) + myDate.getDate();
}

/**通过code获取message**/
function getMessageForCode(code){
    switch (code) {
        case -100:
            message = '通用错误';
            break;
        case -200:
            message = 'http网络错误，例如服务器端返回的状态码不是200';
            break;
        case -300:
            message = '磁盘读写错误，例如本地上某个文件不可读';
            break;
        case -400:
            message = '因为安全问题而产生的错误';
            break;
        case -500:
            message = '初始化时发生错误';
            break;
        case -600:
            message = '选择的文件太大，当前文件大小限制为' + up.settings.filters.max_file_size;
            break;
        case -601:
            message = '选择的文件类型不符合要求';
            break;
        case -602:
            message = '选取了重复的文件而配置中又不允许有重复文件';
            break;
        case -700:
            message = '发生图片格式错误时的错误代码';
            break;
        case -702:
            message = '文件大小超过了plupload所能处理的最大值';
            break;
        default:
            message = err.message;
    }
}

/**
 * 初始化上传对象
 * @param   fileUploadParam(文件上传参数){
 *              selectFileButton(文件选择按钮id) ,
 *              uploadFileButton(文件上传按钮id),
 *              fileUploadedFunction(文件上传后执行方法)
 *              isUploadMany(是否允许多文件上传，默认允许，不允许时请设置该参数为false)
 *          }
 * @returns {Uploader}
 */
function initUploader(fileUploadParam) {
    // 实例化上传对象
    var uploader = new plupload.Uploader({
        'runtimes': 'html5,flash,silverlight,html4',
        'browse_button': fileUploadParam.selectFileButton,     // 选择文件的按钮
        'url': host,	                                          // 阿里云oss上传地址,
        'filters': {
            // 文件后缀限制   只允许上传图片格式为jpg,gif,png的文件和zip压缩包格式为zip的文件
            mime_types:
                isEmpty(fileUploadParam.mimeTypes) ?
                    [{title: "Image files", extensions: "jpg,gif,png"}, {
                        title: "Zip files",
                        extensions: "zip"
                    }] : fileUploadParam.mimeTypes
            ,
            //最大只能上传5120kb的文件
            max_file_size:
                isEmpty(fileUploadParam.maxFileSize) ?
                    '10m' : fileUploadParam.maxFileSize
            ,
            //不允许选取重复文件
            prevent_duplicates:
                isEmpty(fileUploadParam.preventDuplicates) ?
                    true : fileUploadParam.preventDuplicates
        },
        // 初始化
        init: {
            PostInit: function () {
                var ossfile = document.getElementById('ossfile');
                if (ossfile != null) {
                    document.getElementById('ossfile').innerHTML = '';
                }
                //给选择文件按钮绑定上传参数
                document.getElementById(fileUploadParam.uploadFileButton).onclick = function () {
                    set_upload_param(uploader, '', false, fileUploadParam);
                };

                // 是否允许多文件上传
                multiSelection = true;
                if (!isEmpty(fileUploadParam.multiSelection)) {
                    multiSelection = fileUploadParam.multiSelection;
                    /** uploader队列改变事件（仅仅需要上传一个文件时使用该方法） * */
                    up.bind('QueueChanged', function (up) {
                        // 当队列数量大于1时移除第一位文件（注：新增文件时位置是依次往后排）
                        if (up.files.length > 1) {
                            up.splice(0, 1);
                        }
                    });
                }

            },
            // 添加文件
            FilesAdded: function (up, files) {
                //每次添加文件初始化文件数组
                up.files = [];
                fileUploadParam.filesAddedFunction ? fileUploadParam.filesAddedFunction(files) : '';
            },
            // 上传之前
            BeforeUpload: function (up, file) {
                check_object_radio(fileUploadParam);
                set_upload_param(up, file.name, true, fileUploadParam);
                fileUploadParam.beforeUploadFunction ? fileUploadParam.beforeUploadFunction(file) : '';
            },
            // 上传过程中
            UploadProgress: function (up, file) {
                fileUploadParam.uploadProgressFunction ? fileUploadParam.uploadProgressFunction(file) : '';
            },
            // 上传结束
            FileUploaded: function (up, file, info) {
                file.filePath = host + get_uploaded_object_name(file.name);
                fileUploadParam.fileUploadedFunction ? fileUploadParam.fileUploadedFunction(info, file) : '';
            },
            // 上传异常
            Error: function (up, err) {
                err.message = getMessageForCode(err.code);
                fileUploadParam.fileUploadErrorFunction ? fileUploadParam.fileUploadErrorFunction(err) : '';
            }
        }


    });
    return uploader;
}