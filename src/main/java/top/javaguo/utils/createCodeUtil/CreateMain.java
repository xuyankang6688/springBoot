package top.javaguo.utils.createCodeUtil;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import top.javaguo.utils.GuoFileUtil;
import top.javaguo.utils.GuoStringUtil;

/**
 * @describe 创建代码启动类
 * @author javaGuo
 * @date 2018-02-07
 */
public class CreateMain {

    public static String tempFolderPath = "E:\\createCodeZip\\";
    public static String folderPath = "E:\\createCode";

    /**
     * 创建文件
     */
    public void createFile(String fileTempStr , String path , String fileName) throws IOException {
        String[] paths = (folderPath + path).split("/");
        for (int i = 1; i < paths.length; i++) {
            String temp = "";
            for (int j = 0; j < i + 1; j++) {
                temp += paths[j] + "/";
            }
            GuoFileUtil.creatFolder(temp);
        }
        if(GuoFileUtil.creatTxtFile(folderPath + path, fileName)) {
            GuoFileUtil.writeTxtFile(folderPath + path + fileName,fileTempStr);
        }
    }

    public String create(Map<String,String> map , String className) throws IOException {
        String classNameLower = className.substring(0,1).toLowerCase()+className.substring(1);
        String tableName = GuoStringUtil.replaceUpperToLower(className);
        createFile(map.get("mapper"), "/biz/system/dao/"+classNameLower+"/" ,className + "Mapper.java");
        createFile(map.get("bean"),"/biz/system/bean/" ,className + ".java");
        createFile(map.get("dao"), "/biz/system/dao/"+classNameLower+"/" , className + "Dao.java");
        createFile(map.get("service"), "/biz/system/service/", className + "Service.java");
        createFile(map.get("controller"), "/biz/system/controller/", className + "Controller.java");
        createFile(map.get("htmlListPage"), "/html/"+classNameLower+"/", "list.html");
        createFile(map.get("htmlAddAndEditPage"), "/html/"+classNameLower+"/", "edit.html");
        createFile(map.get("sqlFile"), "/sql/", tableName + ".sql");

        File file = new File(tempFolderPath);
        System.out.println("file======================"+file);
        if(!file.exists()) GuoFileUtil.creatFolder(tempFolderPath);//判断目录是否存在，不存在就创建
        file = new File(folderPath);
        if(!file.exists()) GuoFileUtil.creatFolder(folderPath);//判断目录是否存在，不存在就创建
        String filePath = tempFolderPath+ tableName + new Date().getTime()+".zip";
        GuoFileUtil.createZip(folderPath,filePath);
        GuoFileUtil.delAllFile(folderPath);
        return filePath;
    }

}