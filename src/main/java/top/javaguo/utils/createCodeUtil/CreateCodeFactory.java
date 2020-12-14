package top.javaguo.utils.createCodeUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import top.javaguo.biz.system.bean.CreateCode;

/**
 * @author javaGuo
 * @describe 文件工具类
 * @date 2018-02-06
 */
public class CreateCodeFactory {
    /**
     * 将字符串首字母改为大写
     * @param word
     * @return
     */
    private String changeFirstCharToUpper(String word){
        return word.substring(0,1).toUpperCase()+word.substring(1);
    }
    /**
     * 通过字段类型返回默认值
     * @param field
     * @return
     */
    private String returnFieldDefaultValue(String field){
        if(field.indexOf("char") >=0)return "";
        if(field.indexOf("int")>=0)return "0";
        return "";
    }

    public Map<String,String> createFileContent(CreateCode createCode){
        Map<String,String > map = new HashMap<>();
        String[] keyStrArr = createCode.getKeyStr().split(",");
        String[] valueStrArr = createCode.getValueStr().split(",");
        String[] tableKeyStrArr = createCode.getTableKeyStr().split(",");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(date);

        String beanStr = createBeanContent(createCode,keyStrArr,valueStrArr,today,"");
        String mapperStr = createMapperContent(createCode,keyStrArr,tableKeyStrArr,today,"");
        String daoStr = createDaoContent(createCode,today,"");
        String serviceStr = createServiceContent(createCode,today,"");
        String controllerStr = createControllerContent(createCode,today,"",keyStrArr);
        String sqlStr = createSqlContent(createCode,tableKeyStrArr,valueStrArr);
        String listStr = createListHtmlContent(createCode,today,"",keyStrArr,valueStrArr);
        String editStr = createEditHtmlContent(createCode,today,"",keyStrArr,valueStrArr);

        map.put("bean",beanStr);
        map.put("mapper",mapperStr);
        map.put("dao",daoStr);
        map.put("service",serviceStr);
        map.put("controller",controllerStr);
        map.put("sqlFile",sqlStr);
        map.put("htmlListPage",listStr);
        map.put("htmlAddAndEditPage",editStr);

        return map;
    }

    /**
     * 创建edit页面内容
     * @param createCode
     * @param today
     * @param author
     * @return
     */
    private String createEditHtmlContent(CreateCode createCode,String today,String author,String[] keyStrArr,String[] valueStrArr){
        String editContent = "<!-- \n" +
                "\t@describe: "+createCode.getDescribes()+"编辑\n" +
                "\t@author: "+author+" \n" +
                "\t@date: "+today+" \n" +
                "-->\n" +
                "<!DOCTYPE html>\n" +
                "<head>\n" +
                "\t<meta charset='utf-8'>\n" +
                "\t<title>"+createCode.getDescribes()+"编辑</title>\n" +
                "\t<meta name='keywords' content=''/>\n" +
                "\t<meta name='description' content='' />\n" +
                "\t<meta name='viewport' content='width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0'/>\n" +
                "\t<meta name='renderer' content='webkit'/>\n" +
                "\t<meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'/>\n" +
                "\t<link rel='stylesheet' href='../../source/layui/css/layui.css' media='all'/>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\t<div class='layui-form' style='padding: 20px 0 0 0;'>\n" +
                "\t\t<input type='hidden' name='id' class='layui-input'>\n";
                for(int i=4;i<keyStrArr.length;i++){
                    editContent +=  "\t\t<div class='layui-form-item'>\n" +
                            "\t\t\t<label class='layui-form-label'>"+valueStrArr[i]+"</label>\n" +
                            "\t\t\t<div class='layui-input-inline'>\n" +
                            "\t\t\t\t<input type='text' name='"+keyStrArr[i]+"' lay-verify='required' placeholder='请输入"+valueStrArr[i]+"' autocomplete='off' class='layui-input'>\n" +
                            "\t\t\t</div>\n" +
                            "\t\t</div>\n";
                }
                editContent+=
                "\t\t<div class='layui-form-item layui-hide'>\n" +
                "\t\t\t<input type='button' lay-submit lay-filter='LAY-submit' id='LAY-submit' value='确认'>\n" +
                "\t\t</div>\n" +
                "\t</div>\n" +
                "\t<script src='../../source/layui/layui.js'></script>\n" +
                "\t<script>layui.use('form')</script>\n" +
                "</body>\n" +
                "</html>";

        return editContent;
    }

    /**
     * 创建list页面内容
     * @param createCode
     * @param today
     * @param author
     * @return
     */
    private String createListHtmlContent(CreateCode createCode,String today,String author,String[] keyStrArr,String[] valueStrArr){
        StringBuffer buffer = new StringBuffer();
        buffer.append("<!-- \n" +
                "\t@describe: "+createCode.getDescribes()+"列表\n" +
                "\t@author: "+author+" \n" +
                "\t@date: "+today+" \n" +
                "-->\n" +
                "<!DOCTYPE html>\n" +
                "<head>\n" +
                "\t<meta charset='utf-8'>\n" +
                "\t<title>"+createCode.getDescribes()+"列表</title>\n" +
                "\t<meta name='keywords' content=''/>\n" +
                "\t<meta name='description' content=''/>\n" +
                "\t<meta name='viewport' content='width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0'/>\n" +
                "\t<meta name='renderer' content='webkit'/>\n" +
                "\t<meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'/>\n" +
                "\t<link rel='stylesheet' href='../../source/layui/css/layui.css' media='all'/>\n" +
                "\t<link rel='stylesheet' href='../../source/style/admin.css' media='all'/>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\t<div class='layui-fluid'>\n" +
                "\t\t<div class='layui-card'>\n" +
                "\t\t\t<div class='layui-form layui-card-header layuiadmin-card-header-auto'>\n" +
                "\t\t\t\t<div class='layui-form-item'>\n");

        for(int i=4;i<keyStrArr.length;i++){
            buffer.append("\t\t\t\t\t<div class='layui-inline'>\n" +
                    "\t\t\t\t\t\t<label class='layui-form-label'>"+valueStrArr[i]+"</label>\n" +
                    "\t\t\t\t\t\t<div class='layui-input-block'>\n" +
                    "\t\t\t\t\t\t\t<input type='text' name='"+keyStrArr[i]+"' placeholder='请输入' autocomplete='off' class='layui-input'>\n" +
                    "\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t</div>\n");
        }
        buffer.append("\t\t\t\t\t<div class='layui-inline'>\n" +
                "\t\t\t\t\t\t<button class='layui-btn layuiadmin-btn' lay-submit lay-filter='LAY-search'>\n" +
                "\t\t\t\t\t\t\t<i class='layui-icon layui-icon-search layuiadmin-button-btn'></i>\n" +
                "\t\t\t\t\t\t</button>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t</div>\n" +
                "\t\t</div>\n" +
                "\t\t<div class='layui-card-body'>\n" +
                "\t\t\t<div id='layui-card-body-div-btn' style='padding-bottom: 10px;'></div>\n" +
                "\t\t\t<table id='LAY-table-list' lay-filter='LAY-table-list'></table>\n" +
                "\t\t\t<script type='text/html' id='table-tool-btn'></script>\n" +
                "\t\t</div>\n" +
                "\t</div>\n" +
                "\t<script src='../../source/layui/layui.js'></script>\n" +
                "\t<script src='../../js/javaGuoJs/common.js'></script>\n" +
                "\t<script>\n" +
                "\t\tlayui.config({\n" +
                "\t\t\tbase: '../../source/' //静态资源所在路径\n" +
                "\t\t}).extend({\n" +
                "\t\t\tindex: 'lib/index' //主入口模块\n" +
                "\t\t}).use(['index' , 'table' , 'form'], function(){\n" +
                "\t\t\tvar $ = layui.$\n" +
                "\t\t\t,form = layui.form\n" +
                "\t\t\t,table = layui.table\n" +
                "\t\t\t,admin = layui.admin\n" +
                "\t\t\t,actionUrl = '/system/"+createCode.getClassNameLower()+"/'\n" +
                "\t\t\t,roleResourceName = '"+createCode.getDescribes()+"';\n" +
                "\n" +
                "\t\t\t//进入界面成功后可执行的方法，可用于初始化一些数据\n" +
                "\t\t\tvar successFunction = \n" +
                "\t\t\t{\n" +
                "\t\t\t\taddSuccess: function(layero, index){\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\teditSuccess: function(layero, index , obj){\n" +
                "\t\t\t\t\t//界面对象，用于获取界面中节点\n" +
                "\t\t\t\t\tvar $IC = layero.find('iframe').contents();\n"+
                "\t\t\t\t\t$IC.find('input[name=\"id\"]').val(obj.data.id);\n" +
                "\t\t\t\t\t$IC.find('input[name=\"isDeleted\"]').val(obj.data.isDeleted);\n");

        for(int i=4;i<keyStrArr.length;i++){
            buffer.append("\t\t\t\t\t$IC.find('input[name=\""+keyStrArr[i]+"\"]').val(obj.data."+keyStrArr[i]+");\n");
        }
        buffer.append("\t\t\t\t},\n" +
                "\t\t\t\tsubmitBefore: function(layero){\n" +
                "\t\t\t\t}\n" +
                "\t\t\t};\n" +
                "\n" +
                "\t\t\t//表格初始化列表json参数\n" +
                "\t\t\tvar tableRenderJson = {\n" +
                "\t\t\t\telem: '#LAY-table-list'\n" +
                "\t\t\t\t,url:  baseUrl  + actionUrl + 'selectAllForLayUI'\n" +
                "\t\t\t\t,where : paramAddInfo({})\n" +
                "\t\t\t\t,cols: [[\n" +
                "\t\t\t\t\t{type: 'checkbox', fixed: 'left'}\n" +
                "\t\t\t\t\t,{title: '序号', width:70 , templet : function(d){return d.LAY_INDEX;}}\n"+
                "\t\t\t\t\t,{field: 'createTime', title: '创建时间'}\n" +
                "\t\t\t\t\t,{field: 'updateTime', title: '修改时间'}\n");
        for(int i=4;i<keyStrArr.length;i++){
            buffer.append("\t\t\t\t\t,{field: '"+keyStrArr[i]+"', title: '"+valueStrArr[i]+"'}\n");
        }
        buffer.append("\t\t\t\t\t,{title: '操作', width: 150, align:'center', fixed: 'right', toolbar: '#table-tool-btn'}\n" +
                "\t\t\t\t]]\n" +
                "\t\t\t\t,request : {pageName: 'offset',limitName: 'limit'}\n" +
                "\t\t\t\t,page: true\n" +
                "\t\t\t};\n" +
                "\n" +
                "\t\t\t//初始化页面基础功能\n" +
                "\t\t\tinitPageFunction($ , form , table , admin , actionUrl , successFunction , roleResourceName , tableRenderJson);\n" +
                "\n" +
                "\t\t\t//初始化列表数据\n" +
                "\t\t\ttable.render(tableRenderJson);\n" +
                "\t\t});\n" +
                "\t</script>\n" +
                "</body>\n" +
                "</html>");

        return buffer.toString();
    }


    /**
     * 创建sql文本
     * @param createCode
     * @return
     */
    private String createSqlContent(CreateCode createCode,String[] tableKeyStrArr,String[] valueStrArr){
        String[] tableKeyLengthArray = createCode.getTableKeyLength().split(",");
        String[] tableKeyTypeArray = createCode.getTableKeyType().split(",");
        String sqlStr = "SET FOREIGN_KEY_CHECKS=0;\n" +
                "DROP TABLE IF EXISTS `"+createCode.getTableName()+"`;\n" +
                "CREATE TABLE `"+createCode.getTableName()+"` (\n";
        sqlStr += "\t`id` varchar(18) NOT NULL COMMENT '编号',\n";
        for(int i=1;i<tableKeyStrArr.length;i++){
            sqlStr += "\t`"+tableKeyStrArr[i]+"` "+tableKeyTypeArray[i]+"("+tableKeyLengthArray[i]+") DEFAULT '"+
                    returnFieldDefaultValue(tableKeyTypeArray[i])+"' COMMENT '"+valueStrArr[i]+"',\n";
        }

        sqlStr += "\tPRIMARY KEY (`id`)\n" + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '"+createCode.getDescribes()+"';";

        return sqlStr;
    }


    /**
     * 创建controller 文本
     * @return
     */
    private String createControllerContent(CreateCode createCode,String today,String author,String[] keyStrArr){

        String allColum = "";
        for(int i=4;i<keyStrArr.length;i++){
            allColum += "bean.get"+changeFirstCharToUpper(keyStrArr[i])+"(), ";
        }
        allColum =allColum.substring(0,allColum.length()-2);

        String controllerContent ="package top.javaguo.biz.system.controller;\n" +
                "\n" +
                "import java.util.Map;\n" +
                "\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.web.bind.annotation.GetMapping;\n" +
                "import org.springframework.web.bind.annotation.PostMapping;\n" +
                "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                "import org.springframework.web.bind.annotation.RestController;\n" +
                "\n" +
                "import top.javaguo.biz.system.bean."+createCode.getClassName()+";\n" +
                "import top.javaguo.biz.system.service."+createCode.getClassName()+"Service;\n" +
                "import top.javaguo.core.biz.controller.BaseController;\n" +
                "import top.javaguo.core.resp.RespBean;\n" +
                "import top.javaguo.utils.GuoRespBeanUtil;\n" +
                "import top.javaguo.utils.GuoStringUtil;\n" +
                "import top.javaguo.utils.SnowflakeIdWorkerUtil;\n" +
                "\n" +
                "/**\n" +
                " * @describe "+createCode.getDescribes()+"\n" +
                " * @author "+author+"\n" +
                " * @date "+today+"\n" +
                " */\n" +
                "@RestController\n" +
                "@RequestMapping(\"/system/"+createCode.getClassNameLower()+"\")\n" +
                "public class "+createCode.getClassName()+"Controller extends BaseController<"+createCode.getClassName()+">{\n" +
                "\n" +
                "\t/**"+createCode.getDescribes()+"**/\n" +
                "\t@Autowired\n" +
                "\tprivate "+createCode.getClassName()+"Service "+createCode.getClassNameLower()+"Service;\n" +
                "\n" +
                "\t/**根据条件查询所有**/\n" +
                "\t@GetMapping(\"/selectAll\")\n" +
                "\tpublic RespBean<Map<String, Object>> selectAll("+createCode.getClassName()+" bean) { return "+createCode.getClassNameLower()+"Service.selectAll(bean); }\n" +
                "\n" +
                "\t/**\n" +
                "\t * 通过id删除\n" +
                "\t * @非空参数：id\n" +
                "\t */\n" +
                "\t@PostMapping(\"/deleteById\")\n" +
                "\tpublic RespBean<Map<String, Object>> deleteById(String id) { \n" +
                "\t\tif (GuoStringUtil.isEmpty(id)) {\n" +
                "\t\t\treturn GuoRespBeanUtil.initParamNotNullRespBean();\n" +
                "\t\t}\n" +
                "\t\treturn returnIntercept("+createCode.getClassNameLower()+"Service.deleteById(id), "+createCode.getClassNameLower()+"Service);\n" +
                "\t}\n" +
                "\n" +
                "\t/**\n" +
                "\t * 通过id查询\n" +
                "\t * @非空参数：id\n" +
                "\t */\n" +
                "\t@GetMapping(\"/selectById\")\n" +
                "\tpublic RespBean<Map<String, Object>> selectById("+createCode.getClassName()+" bean) { \n" +
                "\t\tif (GuoStringUtil.isEmpty(bean.getId())) {\n" +
                "\t\t\treturn GuoRespBeanUtil.initParamNotNullRespBean();\n" +
                "\t\t}\n" +
                "\t\treturn "+createCode.getClassNameLower()+"Service.selectById(bean); \n" +
                "\t}\n" +
                "\n" +
                "\t/**\n" +
                "\t * 通过ids集合删除\n" +
                "\t * @非空参数：ids\n" +
                "\t */\n" +
                "\t@PostMapping(\"/deleteByIds\")\n" +
                "\tpublic RespBean<Map<String, Object>> deleteByIds(String ids) { \n" +
                "\t\tif (GuoStringUtil.isEmpty(ids)) {\n" +
                "\t\t\treturn GuoRespBeanUtil.initParamNotNullRespBean();\n" +
                "\t\t}\n" +
                "\t\treturn returnIntercept("+createCode.getClassNameLower()+"Service.deleteByIds(ids), "+createCode.getClassNameLower()+"Service); \n" +
                "\t}\n" +
                "\n" +
                "\t/**\n" +
                "\t * 添加\n" +
                "\t * @非空参数：id\n" +
                "\t */\n" +
                "\t@PostMapping(\"/insert\")\n" +
                "\tpublic RespBean<Map<String, Object>> insert("+createCode.getClassName()+" bean) {\n" +
                "\t\tif(GuoStringUtil.isEmpty(new String[] { "+allColum+" })){\n" +
                "\t\t\treturn GuoRespBeanUtil.initParamNotNullRespBean();\n" +
                "\t\t}\n" +
                "\t\tbean.setId(SnowflakeIdWorkerUtil.SIWU.nextId());\n" +
                "\t\treturn returnIntercept("+createCode.getClassNameLower()+"Service.insert(bean), "+createCode.getClassNameLower()+"Service);\n" +
                "\t}\n" +
                "\n" +
                "\t/**\n" +
                "\t * 通过id修改\n" +
                "\t * @非空参数：id\n" +
                "\t */\n" +
                "\t@PostMapping(\"/updateById\")\n" +
                "\tpublic RespBean<Map<String, Object>> updateById("+createCode.getClassName()+" bean) {\n" +
                "\t\tif(GuoStringUtil.isEmpty(new String[] { bean.getId(),"+allColum+" })){\n" +
                "\t\t\treturn GuoRespBeanUtil.initParamNotNullRespBean();\n" +
                "\t\t}\n" +
                "\t\treturn returnIntercept("+createCode.getClassNameLower()+"Service.updateById(bean), "+createCode.getClassNameLower()+"Service);\n" +
                "\t}\n" +
                "\n" +
                "\t/**LayUI根据条件查询所有**/\n" +
                "\t@GetMapping(\"/selectAllForLayUI\")\n" +
                "\tpublic Map<String, Object> selectAllForLayUI("+createCode.getClassName()+" bean) { return "+createCode.getClassNameLower()+"Service.selectAllForLayUI(bean); }\n" +
                "\n" +
                "\n" +
                "}";


        return controllerContent;
    }


    /**
     * 创建service文本
     * @param createCode
     * @param today
     * @param author
     * @return
     */
    private String createServiceContent(CreateCode createCode,String today,String author){
        String serviceStr = "package top.javaguo.biz.system.service;\n" +
                "\n" +
                "import java.util.Map;\n" +
                "\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "import top.javaguo.biz.system.bean."+createCode.getClassName()+";\n" +
                "import top.javaguo.biz.system.dao."+createCode.getClassNameLower()+"."+createCode.getClassName()+"Dao;\n" +
                "import top.javaguo.core.biz.service.BaseService;\n" +
                "import top.javaguo.core.resp.RespBean;\n" +
                "\n" +
                "/**\n" +
                " * @describe "+createCode.getDescribes()+"\n" +
                " * @author "+author+"\n" +
                " * @date "+today+"\n" +
                " */\n" +
                "@Service\n" +
                "public class "+createCode.getClassName()+"Service extends BaseService<"+createCode.getClassName()+">{\n" +
                "\n" +
                "\t/**"+createCode.getDescribes()+"**/\n" +
                "\t@Autowired\n" +
                "\tprivate "+createCode.getClassName()+"Dao "+createCode.getClassNameLower()+"Dao;\n" +
                "\n" +
                "\t/**根据条件查询所有**/\n" +
                "\tpublic RespBean<Map<String, Object>> selectAll("+createCode.getClassName()+" bean) { return getResult("+createCode.getClassNameLower()+"Dao, \"selectAll\", bean); }\n" +
                "\n" +
                "\t/**LayUI根据条件查询所有**/\n" +
                "\tpublic Map<String, Object> selectAllForLayUI("+createCode.getClassName()+" bean) { return getResult("+createCode.getClassNameLower()+"Dao, \"selectAllForLayUI\", bean).getData(); }\n" +
                "\n" +
                "\t/**添加**/\n" +
                "\tpublic RespBean<Map<String, Object>> insert("+createCode.getClassName()+" bean) { return getResult("+createCode.getClassNameLower()+"Dao, \"insert\", bean); }\n" +
                "\n" +
                "\t/**通过id删除**/\n" +
                "\tpublic RespBean<Map<String, Object>> deleteById(String id) { return getResult("+createCode.getClassNameLower()+"Dao, \"deleteById\", id); }\n" +
                "\n" +
                "\t/**通过id修改**/\n" +
                "\tpublic RespBean<Map<String, Object>> updateById("+createCode.getClassName()+" bean) { return getResult("+createCode.getClassNameLower()+"Dao, \"updateById\", bean); }\n" +
                "\n" +
                "\t/**通过id查询**/\n" +
                "\tpublic RespBean<Map<String, Object>> selectById("+createCode.getClassName()+" bean) { return getResult("+createCode.getClassNameLower()+"Dao, \"selectById\", bean); }\n" +
                "\n" +
                "\t/**通过ids集合删除**/\n" +
                "\tpublic RespBean<Map<String, Object>> deleteByIds(String ids) { return getResult("+createCode.getClassNameLower()+"Dao, \"deleteByIds\", ids); }\n" +
                "\n" +
                "\t/** 清空多对象缓存集合 **/\n" +
                "\t@Override\n" +
                "\tpublic void clearBeanManyCache() {\n" +
                "\t}\n" +
                "\n" +
                "\n" +
                "}\n";
        return serviceStr;
    }


    /**
     * 创建dao文本
     * @param createCode
     * @param today
     * @return
     */
    private String createDaoContent(CreateCode createCode,String today,String author){
        StringBuffer buffer = new StringBuffer();
        buffer.append("package top.javaguo.biz.system.dao."+createCode.getClassNameLower()+";\n" +
                "\n" +
                "import java.util.List;\n" +
                "import org.apache.ibatis.annotations.DeleteProvider;\n" +
                "import org.apache.ibatis.annotations.InsertProvider;\n" +
                "import org.apache.ibatis.annotations.Mapper;\n" +
                "import org.apache.ibatis.annotations.Param;\n" +
                "import org.apache.ibatis.annotations.SelectProvider;\n" +
                "import org.apache.ibatis.annotations.UpdateProvider;\n" +
                "import top.javaguo.biz.system.bean."+createCode.getClassName()+";\n" +
                "import top.javaguo.core.biz.dao.BaseDao;\n" +
                "\n" +
                "/**\n" +
                " * @describe "+createCode.getDescribes()+"\n" +
                " * @author "+author+"\n" +
                " * @date "+today+"\n" +
                " */\n" +
                "@Mapper\n" +
                "public interface "+createCode.getClassName()+"Dao extends BaseDao<"+createCode.getClassName()+"> {\n" +
                "\n" +
                "\t/**根据条件查询所有**/\n" +
                "\t@SelectProvider(type="+createCode.getClassName()+"Mapper.class,method = \"selectAll\")\n" +
                "\tpublic List<"+createCode.getClassName()+"> selectAll(@Param(\"bean\")"+createCode.getClassName()+" bean);\n" +
                "\n" +
                "\t/**根据条件查询所有的个数**/\n" +
                "\t@SelectProvider(type="+createCode.getClassName()+"Mapper.class,method = \"selectTotal\")\n" +
                "\tpublic int selectTotal(@Param(\"bean\")"+createCode.getClassName()+" bean);\n" +
                "\n" +
                "\t/**添加**/\n" +
                "\t@InsertProvider(type="+createCode.getClassName()+"Mapper.class,method = \"insert\")\n" +
                "\tpublic int insert(@Param(\"bean\")"+createCode.getClassName()+" bean);\n" +
                "\n" +
                "\t/**通过id删除**/\n" +
                "\t@DeleteProvider(type="+createCode.getClassName()+"Mapper.class,method = \"deleteById\")\n" +
                "\tpublic int deleteById(@Param(\"id\")String id);\n" +
                "\n" +
                "\t/**通过id修改**/\n" +
                "\t@UpdateProvider(type="+createCode.getClassName()+"Mapper.class,method = \"updateById\")\n" +
                "\tpublic int updateById(@Param(\"bean\")"+createCode.getClassName()+" bean);\n" +
                "\n" +
                "\t/**通过id查询**/\n" +
                "\t@SelectProvider(type="+createCode.getClassName()+"Mapper.class,method = \"selectById\")\n" +
                "\tpublic "+createCode.getClassName()+" selectById(@Param(\"bean\")"+createCode.getClassName()+" bean);\n" +
                "\n" +
                "\t/**通过ids集合删除**/\n" +
                "\t@DeleteProvider(type="+createCode.getClassName()+"Mapper.class,method = \"deleteByIds\")\n" +
                "\tpublic int deleteByIds(@Param(\"ids\")String ids);\n" +
                "\n" +
                "\t/**根据关联条件查询所有**/\n" +
                "\t@SelectProvider(type="+createCode.getClassName()+"Mapper.class,method = \"selectAllForRelation\")\n" +
                "\tpublic List<"+createCode.getClassName()+"> selectAllForRelation(@Param(\"bean\")"+createCode.getClassName()+" bean);\n" +
                "\n" +
                "\t/**根据关联条件查询所有的个数**/\n" +
                "\t@SelectProvider(type="+createCode.getClassName()+"Mapper.class,method = \"selectTotalForRelation\")\n" +
                "\tpublic int selectTotalForRelation(@Param(\"bean\")"+createCode.getClassName()+" bean);\n" +
                "\n" +
                "\t/**根据关联id查询**/\n" +
                "\t@SelectProvider(type="+createCode.getClassName()+"Mapper.class,method = \"selectByIdForRelation\")\n" +
                "\tpublic "+createCode.getClassName()+" selectByIdForRelation(@Param(\"id\")String id);\n" +
                "\n" +
                "}");


        return buffer.toString();
    }

    /**
     * 创建Mapper文本
     * @param createCode
     * @param keyStrArr
     * @param tableKeyStrArr
     * @return
     */
    private String createMapperContent(CreateCode createCode,String[] keyStrArr,String[] tableKeyStrArr,String today,String author){
        String mapperStr = "package top.javaguo.biz.system.dao."+createCode.getClassNameLower()+";\n" +
                "\n" +
                "import org.apache.ibatis.annotations.Param;\n" +
                "import top.javaguo.biz.system.bean."+createCode.getClassName()+";\n" +
                "import top.javaguo.core.biz.dao.BaseMapper;\n" +
                "\n" +
                "/**\n" +
                " * @describe "+createCode.getDescribes()+"\n" +
                " * @author "+author+"\n" +
                " * @date "+today+"\n" +
                " */\n";
        mapperStr += "public class "+createCode.getClassName()+"Mapper extends BaseMapper<"+createCode.getClassName()+"> {\n" +
                "\n" +
                "\t/**查询条件**/\n" +
                "\t";

        mapperStr += "public String commonWhere("+createCode.getClassName()+" bean) {\n" +
                "\t\treturn bean == null ? \"\" : \" WHERE 1 = 1 \"\n";
        for(int i =0;i<keyStrArr.length;i++){
            String firstUpCaseKey = keyStrArr[i].substring(0,1).toUpperCase()+keyStrArr[i].substring(1);
            mapperStr += "\t\t\t+ whereAddFilter(\"t."+tableKeyStrArr[i]+" = \" , bean.get"+firstUpCaseKey+"())\n";
        }
        mapperStr += "\t\t\t+ whereAddFilter(\"t.create_time >= \" , bean.getQueryBeginDate())\n" +
                "\t\t\t+ whereAddFilter(\"t.create_time < \" , bean.getQueryEndDate())\n" +
                "\t\t\t;\n" +
                "\t}\n" +
                "\n" +
                "\t";
        mapperStr += "/**所需关联查询字段**/\n" +
                "\tprivate String getSelectFieldsForRelation() {\n" +
                "\t\treturn ";
        mapperStr += "\"+ t."+keyStrArr[0]+"\"\n\t\t\t\t";
        for(int i =1;i<tableKeyStrArr.length;i++){
            mapperStr += "+ \"+ t."+keyStrArr[i]+"\"\n\t\t\t\t";
        }
        mapperStr += ";\n\t}\n" +
                "\n" +
                "\t/**所需关联查询表名**/\n" +
                "\tprivate String getSelectedTableNameForRelation() {\n" +
                "\t\treturn \" "+createCode.getTableName()+" t \"\n" +
                "\t\t\t\t;\n" +
                "\t}\n" +
                "\n" +
                "\t/**根据关联条件查询所有数据**/\n" +
                "\tpublic String selectAllForRelation(@Param(\"bean\")"+createCode.getClassName()+" bean) { return getSelectSql(bean, \"all\", getSelectFieldsForRelation(), getSelectedTableNameForRelation()); }\n" +
                "\n" +
                "\t/**根据关联条件查询所有数量**/\n" +
                "\tpublic String selectTotalForRelation(@Param(\"bean\")"+createCode.getClassName()+" bean) { return getSelectSql(bean, \"total\", \"count(*)\", getSelectedTableNameForRelation()); }\n" +
                "\n" +
                "\t/**根据关联id查询**/\n" +
                "\tpublic String selectByIdForRelation(@Param(\"id\")String id) {\n" +
                "\t\t"+createCode.getClassName()+" bean = new "+createCode.getClassName()+"();\n" +
                "\t\tbean.setId(id);\n" +
                "\t\treturn getSelectSql(bean, \"all\", getSelectFieldsForRelation(), getSelectedTableNameForRelation());\n" +
                "\t}\n}";


        return mapperStr;
    }

    /**
     * 创建Bean文本内容
     * @param createCode
     * @param keyStrArr
     * @param valueStrArr
     * @return
     */
    private String createBeanContent(CreateCode createCode,String[] keyStrArr,String[] valueStrArr,String today,String author){
        String beanStr = "package top.javaguo.biz.system.bean;\n" +
                "\n" +
                "import top.javaguo.core.biz.bean.BaseBean;\n" +
                "\n" +
                "/**\n" +
                " * @describe " +createCode.getDescribes()+"\n"+
                " * @author "+author+"\n" +
                " * @date "+today+"\n" +
                " */\n" +
                "public class "+createCode.getClassName()+" extends BaseBean {\n" +
                "\n" +
                "\tprivate static final long serialVersionUID = 1L;\n" +
                "\n" +
                "\t/** 非表字段属性 **/\n" +
                "\tprivate String notFieldParams = \"serialVersionUID,notFieldParams\";\n" +
                "\n" +
                "\t";

        for(int i =0;i<keyStrArr.length;i++){
            beanStr += "/**"+valueStrArr[i]+"**/\n" +
                    "\tprivate String "+keyStrArr[i]+";\n" +
                    "\n" +
                    "\t";
        }
        beanStr += "/** 获取非表字段属性 **/\n" +
                "\tpublic String getNotFieldParams() {\n" +
                "\t\treturn notFieldParams;\n" +
                "\t}\n" +
                "\n" +
                "\t";
        for(int i =0;i<keyStrArr.length;i++){
            String firstUpCaseKey = keyStrArr[i].substring(0,1).toUpperCase()+keyStrArr[i].substring(1);
            beanStr += "/**获取"+valueStrArr[i]+"**/\n" +
                    "\tpublic String get"+firstUpCaseKey+"() {\n" +
                    "\t\treturn "+keyStrArr[i]+";\n" +
                    "\t}\n" +
                    "\n" +
                    "\t/**设置"+valueStrArr[i]+"**/\n" +
                    "\tpublic void set"+firstUpCaseKey+"(String "+keyStrArr[i]+") {\n" +
                    "\t\tthis."+keyStrArr[i]+" = "+keyStrArr[i]+";\n" +
                    "\t}\n" +
                    "\n" +
                    "\t";
        }
        beanStr += "@Override\n" +
                "\tpublic String toString(){\n" +
                "\t\treturn \""+createCode.getClassName()+"{\" + \n";

        for(int i =0;i<keyStrArr.length;i++){
            beanStr += "\t\t\t\""+keyStrArr[i]+"='\" + "+keyStrArr[i]+" + '\\'' + \n";
        }

        beanStr+= "\t\t\t'}';\n\t}\n\n}";


        return beanStr;
    }

    public static void main(String[] args) {

    }

}