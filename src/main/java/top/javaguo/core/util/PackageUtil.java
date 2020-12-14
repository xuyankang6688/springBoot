package top.javaguo.core.util;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author javaGuo
 * @describe 包工具类
 * @date 2018-12-03
 */
public class PackageUtil {

    public static String replacementStr = "\\";

    static {
        String osName = System.getProperty("os.name");
        if (osName.indexOf("Windows") < 0) {
            replacementStr = "/";
        }
    }

    /**
     * 解析包的全路径
     */
    @SuppressWarnings("deprecation")
    public String resovlePackagePath(String webPackage) {
        // 扫码所有的包并把其放入到访问关系和方法放入到内存中
        File f = new File(this.getClass().getResource("/").getPath());
        String totalPath = URLDecoder.decode(f.getAbsolutePath());
        String pageName = getClass().getPackage().getName().replace(".", replacementStr);
        totalPath = totalPath.replace(pageName, "");
        if(totalPath.indexOf(".jar") != -1){
           //totalPath = "C:\\Users\\xyk\\Desktop\\taipengproject\\hongpei\\target";
            totalPath = "/classes";
        }
        String packagePath = webPackage.replace(".", replacementStr);
        totalPath = totalPath + "/" + packagePath;
        return totalPath;
    }

    /**
     * 解析包下面的所有类
     */
    public List<String> parseClassName(String packagePath, String webPackage) {

        List<String> array = new ArrayList<>();
        File root = new File(packagePath);
        resolveFile(root, webPackage, array);
        return array;
    }

    private void resolveFile(File root, String webPackage, List<String> classNames) {
        if (!root.exists())
            return;
        File[] childs = root.listFiles();
        if (childs != null && childs.length > 0) {
            for (File child : childs) {
                if (child.isDirectory()) {
                    String parentPath = child.getParent();
                    String childPath = child.getAbsolutePath();
                    String temp = childPath.replace(parentPath, "");
                    temp = temp.replace(replacementStr, "");
                    resolveFile(child, webPackage + "." + temp, classNames);
                } else {
                    String fileName = child.getName();
                    if (fileName.endsWith(".class")) {
                        String name = fileName.replace(".class", "");
                        String className = webPackage + "." + name;
                        classNames.add(className);
                    }
                }
            }
        }
    }

    /**
     * 根据路径获取路径下所有类
     */
    public static List<String> getClassWithPath(String path) {
        PackageUtil packageUtil = new PackageUtil();
        String totalPath = packageUtil.resovlePackagePath(path);
        List<String> datas = packageUtil.parseClassName(totalPath, path);
        return datas;
    }

    public static void main(String[] args) {
        PackageUtil.getClassWithPath("top.javaGuo.biz.system.bean");
    }


}
