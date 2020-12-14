/**
 * 公共js
 * @author javaGuo
 * @date 2018/03/09
 */

/**
 * @Example js中引入js文件
 *
 * **/
document.write("<script language=javascript src='/static/system/js/javaGuoJs/md5.js'></script>");

/**url前缀**/
var baseUrl = "http://"+window.location.host;
//var baseUrl = "http://47.101.135.162:8077";


/**判断是否登录**/
isSuccess();

/**通过菜单栏的切换iframe显示对应页面**/
function changePageCenterIframe(url) {
    isSuccess();
    $('#pageCenterIframe').attr('src', url);
}

/**注销**/
function logout() {
    $.ajax({
        type: "POST",
        url: baseUrl + '/system/sso/logout',
        headers: paramAddInfo({}),
        dataType: "json",
        async: false,
        success: function (data) {
            if ("0000006" == data.code) {
                alert('退出成功');
                location.href = "/static/system/sign-in.html";
            } else {
                alert(data.msg);
            }
            delCookie('memberInfo');
        },
        error: function (data) {
            delCookie('info');
            alert("系统维护中。。。");
        }
    });
}

/**判断当前用户是否登录**/
function isSuccess() {
    if (getCookie('memberInfo') == null) {
        if (window.location.href.indexOf("sign-in.html") == -1) {
            location.href = "/static/system/sign-in.html";
        }
    } else {
        if (!getCookie('memberInfo').info.token) {
            if (window.location.href.indexOf("sign-in.html") == -1) {
                location.href = "/static/system/sign-in.html";
            }
        }
    }
}

/**
 * 添加增加功能
 * @author javaGuo
 * @date 2018/4/3
 */
function setAddFunction() {
    var addFunctionHtml = "<li class='active'><a href='edit.html'>新增</a></li>";
    $('#functionBar').append(addFunctionHtml);
}

/**判断字符是否为空的方法**/
function isEmpty(obj) {
    if (typeof obj == "undefined" || obj == null || obj == "" || obj == 'null') {
        return true;
    }
    return false;
}

/**设置cookie*/
function setCookie(key, value) {
    setLocalStorage(key, value);
}

/**获取cookie*/
function getCookie(key) {
    return getLocalStorage(key);
}

/**删除cookie*/
function delCookie(key) {
    delLocalStorage(key);
}

/**设置localStorage*/
function setLocalStorage(key, value, seconds) {
    if (!value) localStorage.removeItem(key);
    else {
        var seconds = (seconds || 30 * 60) * 1000; // 资源有效期，默认保留30分钟
        // var seconds = (seconds ||30 * 60)*100
        var exp = new Date();
        value.expires = exp.getTime() + seconds;
        window.localStorage.setItem(key + '', JSON.stringify(value));
    }
};

/**获取LocalStorage*/
function getLocalStorage(key) {
    if (localStorage.length > 0 && localStorage[key]) {
        var o = JSON.parse(localStorage[key]);
        delLocalStorage(key);
        if (!o || o.expires < Date.now()) return null;
        if (o && o.expires >= Date.now()) setLocalStorage(key, o);
        return o
    } else return null;
};

/**删除localStorage*/
function delLocalStorage(key) {
    if (localStorage.length > 0 && localStorage[key]) localStorage.removeItem(key);
};

/**
 * 给list页面增加页码
 * @author javaGuo
 * @date 2018/4/3
 */
function pageLimitChangeAdd() {
    var pageLimitChangeAddHtml = "<div class='btn-group pull-right' id='templatemo_sort_btn'><input type='hidden' id='limit' value='10'/><button type='button' class='btn btn-default' data-toggle='dropdown'>页码</button><button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown'><span class='caret'></span><span class='sr-only'>Toggle Dropdown</span></button><ul class='dropdown-menu' role='menu'><li><a href='javascript:changeLimit(5);'>5</a></li><li><a href='javascript:changeLimit(10);'>10</a></li><li><a href='javascript:changeLimit(9999);'>全部</a></li></ul></div>";
    $('#functionBar').append(pageLimitChangeAddHtml);
}

/**
 * 给list页面增加排序
 * @author javaGuo
 * @date 2018/4/3
 */
function pageOrderChangeAdd(orderParam) {
    var pageOrderChangeAddHtml = "<div class='btn-group pull-right' id='templatemo_sort_btn'><input type='hidden' id='limit' value='10'/><button type='button' class='btn btn-default' data-toggle='dropdown'>排序</button><button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown'><span class='caret'></span><span class='sr-only'>Toggle Dropdown</span></button><ul class='dropdown-menu' role='menu'>";
    for (var element in orderParam) {
        pageOrderChangeAddHtml += "<li><a href=\"javascript:changeOrder('" + element + "')\";>" + orderParam[element] + "</a></li>";
    }
    pageOrderChangeAddHtml += "</ul></div><div class='btn-group pull-right' id='templatemo_sort_btn'><input type='hidden' id='limit' value='10'/><button type='button' class='btn btn-default' data-toggle='dropdown'>排序规则</button><button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown'><span class='caret'></span><span class='sr-only'>Toggle Dropdown</span></button><ul class='dropdown-menu' role='menu'>";
    pageOrderChangeAddHtml += "<li><a href=\"javascript:changeSortingRules('asc')\";>正序</a></li><li><a href=\"javascript:changeSortingRules('desc')\";>倒序</a></li></ul></div>";
    $('#functionBar').append(pageOrderChangeAddHtml);
}

/*function pageOrderChangeAdd(orderParam) {
	var pageOrderChangeAddHtml = "<div class='btn-group pull-right' id='templatemo_sort_btn'><input type='hidden' id='limit' value='10'/><button type='button' class='btn btn-default' data-toggle='dropdown'>排序</button><button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown'><span class='caret'></span><span class='sr-only'>Toggle Dropdown</span></button><ul class='dropdown-menu' role='menu'>";
	for ( var element in orderParam) {
		pageOrderChangeAddHtml += "<li><a href=\"javascript:changeOrder('"+element+"')\";>"+orderParam[element]+"</a></li>";
	}
	pageOrderChangeAddHtml += "</ul></div><div class='btn-group pull-right' id='templatemo_sort_btn'><input type='hidden' id='limit' value='10'/><button type='button' class='btn btn-default' data-toggle='dropdown'>排序规则</button><button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown'><span class='caret'></span><span class='sr-only'>Toggle Dropdown</span></button><ul class='dropdown-menu' role='menu'>";
	pageOrderChangeAddHtml += "<li><a href=\"javascript:changeSortingRules('asc')\";>正序</a></li><li><a href=\"javascript:changeSortingRules('desc')\";>倒序</a></li></ul></div>";
	$('#functionBar').append(pageOrderChangeAddHtml);
}*/

/**
 * 通过资源名称判断当前用户是否拥有该权限
 * @param resName 资源名称
 */
function judgeRoleResource(resName) {

    //用户资源权限集合
    var listPerResource = getCookie('memberInfo').info.listPerResource;
    for (var i = 0; i < listPerResource.length; i++) {
        if (listPerResource[i].resName == resName) {
            return true;
        }
    }
    return false;
}

/**页面左侧菜单数据加载**/
function pageLeftMenuAdd() {
    var pageLeftMenuHtml = '';
    var listPerResource = getCookie('memberInfo').info.listPerResource;
    for (var i = 0; i < listPerResource.length; i++) {
        if (listPerResource[i].menuLevel == '0') {
            pageLeftMenuHtml += '<li class="layui-nav-item"><a href="javascript:;" lay-tips="' + listPerResource[i].resName +
                //layui-icon-component表示菜单栏的左侧图标，可以更改他来改变图标
                '" lay-direction="2"><i class="layui-icon layui-icon-component"></i><cite>'
                + listPerResource[i].resName + '</cite></a><dl class="layui-nav-child">';
            for (var j = 0; j < listPerResource.length; j++) {
                if (listPerResource[j].menuLevel == '1' && listPerResource[j].parentId == listPerResource[i].id) {
                    pageLeftMenuHtml += '<dd><a lay-href="' + listPerResource[j].resUrl + '">' + listPerResource[j].resName + '</a></dd>';
                }
            }
            pageLeftMenuHtml += '</dl></li>';
        }
    }
    $('#LAY-system-side-menu').html(pageLeftMenuHtml);
}

/**
 *@describe: 给参数对象中加入相关信息
 *@author: javaGuo
 *@date: 2018-09-11
 **/
function paramAddInfo(param) {
    var jsonTemp = {};
    if (!isEmpty(param)) {
        jsonTemp = param;
    }
    var privateKey = "springbootsystem";
    var publicKey = "" + new Date().getTime();
    var sign = hex_md5((privateKey + publicKey));
    if (!isEmpty(layui.data('memberInfo').info)) {
        jsonTemp.token = layui.data('memberInfo').info.token;
    }
    jsonTemp.publicKey = publicKey;
    jsonTemp.sign = sign;
    return jsonTemp;
}

/**
 *@describe: 列表对应页面功能初始化
 *@author: javaGuo
 *@date: 2018-09-11
 **/
function initPageFunction($, form, table, admin, actionUrl, successFunction, roleResourceName, tableRenderJson, width, height) {

    //监听搜索，执行重载
    form.on('submit(LAY-search)', function (data) {
        tableRenderJson.headers = paramAddInfo();
        tableRenderJson.where = paramAddInfo(data.field);
        table.render(tableRenderJson);
    });


    //增删改权限
    var addFlag = judgeRoleResource(roleResourceName + '新增');
    var deleteFlag = judgeRoleResource(roleResourceName + '删除');
    var editFlag = judgeRoleResource(roleResourceName + '编辑');

    //增删改对应的按钮代码
    var editBtn = '<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>'
    var delBtn = '<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>';
    var batchdel = '<button class="layui-btn layuiadmin-btn" data-type="batchdel">删除</button>';
    var addBtn = '<button class="layui-btn layuiadmin-btn" data-type="add">添加</button>';

    //根据权限展开对应功能
    var tableToolBtnContent = '';
    var layuiCardBodyDivBtnContent = '';
    if (editFlag) tableToolBtnContent += editBtn;
    if (deleteFlag) tableToolBtnContent += delBtn;
    if (addFlag) layuiCardBodyDivBtnContent += addBtn;
    if (deleteFlag) layuiCardBodyDivBtnContent += batchdel;
    $('#table-tool-btn').append(tableToolBtnContent);
    $('#layui-card-body-div-btn').append(layuiCardBodyDivBtnContent);

    width = width ? width : '80%';
    height = height ? height : '90%';

    //封装功能方法
    var active = {
        add: function () {//添加
            layer.open({
                type: 2
                , title: '添加'
                , content: 'edit.html'
                , maxmin: true
                , area: [width, height]
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    successFunction['submitBefore'](layero);
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'LAY-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);
                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        admin.req({
                            url: baseUrl + actionUrl + 'insert'
                            , type: 'post'
                            , data: data.field	//layui-form下的input
                            , done: function (res) {
                                tableRenderJson.headers = paramAddInfo();
                                table.reload('LAY-table-list', tableRenderJson); //数据刷新
                                layer.close(index); //关闭弹层
                            }
                        });
                    });
                    submit.trigger('click');
                }, success: function (layero, index) {
                    successFunction['addSuccess'](layero, index);
                }
            });
        }
        , del: function (obj) {//删除
            layer.confirm('真的删除行么', function (index) {
                admin.req({
                    url: baseUrl + actionUrl + 'deleteById'
                    , type: 'post'
                    , data: {id: obj.data.id}	//layui-form下的input
                    , done: function (res) {
                        tableRenderJson.headers = paramAddInfo();
                        table.reload('LAY-table-list', tableRenderJson); //数据刷新
                        layer.close(index);
                    }
                });
            });
        }
        , batchdel: function () {//多选删除
            var checkStatus = table.checkStatus('LAY-table-list')
                , checkData = checkStatus.data; //得到选中的数据
            var ids = '';
            for (var i = 0; i < checkData.length; i++) {
                ids += checkData[i].id;
                if (i != checkData.length - 1) ids += ',';
            }
            if (checkData.length === 0) return layer.msg('请选择数据');
            layer.confirm('真的删除行么', function (index) {
                admin.req({
                    url: baseUrl + actionUrl + 'deleteByIds'
                    , type: 'post'
                    , data: {ids: ids}	//layui-form下的input
                    , done: function (res) {
                        tableRenderJson.headers = paramAddInfo();
                        table.reload('LAY-table-list', tableRenderJson); //数据刷新
                        layer.close(index); //关闭弹层
                    }
                });
            });
        }
        , edit: function (obj) {//编辑
            layer.open({
                type: 2
                , title: '编辑'
                , content: 'edit.html'
                , maxmin: true
                , area: [width, height]
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    successFunction['submitBefore'](layero);
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'LAY-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);
                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        admin.req({
                            url: baseUrl + actionUrl + 'updateById'
                            , type: 'post'
                            , data: data.field	//layui-form下的input
                            , done: function (res) {
                                tableRenderJson.headers = paramAddInfo();
                                table.reload('LAY-table-list', tableRenderJson); //数据刷新
                                layer.close(index); //关闭弹层
                            }
                        });
                    });
                    submit.trigger('click');
                }
                , success: function (layero, index) {
                    successFunction['editSuccess'](layero, index, obj);
                }
            });
        }
    };

    //把successFunction加入active
    if (successFunction) $.each(successFunction, function (i, val) {
        active[i] = val;
    });

    //绑定事件
    $('.layui-btn.layuiadmin-btn').on('click', function () {
        active[$(this).data('type')] ? active[$(this).data('type')].call(this) : '';
    });

    //监听LAY-table-list中事件并绑定方法
    table.on('tool(LAY-table-list)', function (obj) {
        $.each(active, function (key) {
            if (obj.event === key) active[key](obj);
        });
    });

}

/**
 * 检查对象是否为json对象
 * @param obj
 */
function checkJson(obj) {
    if (typeof (obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length) {
        return true;
    }
    return false;
}

/**
 * 判断密码是否为记住密码，是则返回密码，不是则返回加密后密码，若用户选择记住密码则把密码放入缓存
 * @param username
 * @param password
 * @returns {*}
 */
function checkPassword(username, password, remember) {
    // 密码若不等于32位则表明密码不是记住的密码，则加密密码
    if (password.length != 32) {
        password = hex_md5(password + username);
        // 若用户点击了记住密码则把用户账号密码放入缓存中
        if (remember == 'on') {
            setCookie('remember', {
                'info': {'username': username, 'password': password}
            });
        }
    }
    return password;
}

/**
 * 初始化记住密码数据
 * @param rememberCache
 */
function initRemember(rememberCache) {
// 若上次有记住密码，则填充上次的账号密码
    if (checkJson(rememberCache) && checkJson(rememberCache.info)) {
        var username = rememberCache.info.username;
        var password = rememberCache.info.password;
        if (!isEmpty(username) && !isEmpty(password)) {
            document.getElementById('LAY-user-login-username').value = username;
            document.getElementById('LAY-user-login-password').value = password;
        }
    }
}

/** 获取当前年月日 * */
function getNowDate(){
    return new Date().getFullYear() + '' + ( new Date().getMonth() + 1 ) + new Date().getDate();
}

