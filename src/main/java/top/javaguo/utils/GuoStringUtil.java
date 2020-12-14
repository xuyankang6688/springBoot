package top.javaguo.utils;

/**
 * 字符串工具类
 *
 * @author javaGuo
 * @date 2018/03/07
 */
public class GuoStringUtil {

    /**
     * 替换字符串中的or和and
     **/
    public static String replaceString(String str) {
        return str.replaceAll("or", "").replaceAll("and", "");
    }

    /**
     * 判断字符串是否为空
     **/
    public static boolean isEmpty(String param) {
        if (param == null || param.length() <= 0 || "null".equals(param)) return true;
        return false;
    }

    /**
     * 判断字符串是否为空
     **/
    public static boolean isEmpty(String[] param) {
        for (int i = 0; i < param.length; i++) {
            if (param[i] == null || param[i].length() <= 0) return true;
        }
        return false;
    }

    /**
     * 判断token是否不为空同时长度等于32
     **/
    public static boolean judgeToken(String token) {
        if (token != null && token.length() == 32) return true;
        return false;
    }

    /**
     * 判断字符串是否为null，若是则返回空字符串，若不是则原路返回
     **/
    public static String replaceNull(String param) {
        return param != null ? param : "";
    }

    /**
     * 替换字符串中的除首字母意外的大写字母为_加小写字母
     **/
    public static String replaceUpperToLower(String param) {
        String temp = "";
        if (!isEmpty(param)) {
            for (int i = 0; i < param.length(); i++) {
                char c = param.charAt(i);
                if (!Character.isLowerCase(c)) {
                    if (i != 0) temp += '_';
                    temp += String.valueOf(c).toLowerCase();
                } else temp += c;
            }
        }
        return temp;
    }

    /**
     * 替换字符串中的下划线同时改变下划线后面的一个字母为大写
     **/
    public static String replaceUnderlineToUpper(String param, boolean flag) {
        String temp = "";
        if (!isEmpty(param)) {
            for (int i = 0; i < param.length(); i++) {
                String c = String.valueOf(param.charAt(i));
                if (i == 0 && flag) c = c.toUpperCase();
                if ("_".equals(c)) {
                    temp += String.valueOf(param.charAt(++i)).toUpperCase();
                } else temp += c;
            }
        }
        return temp;
    }

    /**
     * 替换字符串数组中的下划线同时改变下划线后面的一个字母为大写同时返回一个拼接字符串
     **/
    public static String replaceUnderlineToUpperForArray(String[] param, boolean flag) {
        String temp = "";
        for (int i = 0; i < param.length; i++) {
            if (!isEmpty(param)) {
                for (int j = 0; j < param[i].length(); j++) {
                    String c = String.valueOf(param[i].charAt(j));
                    if (j == 0 && flag) c = c.toUpperCase();
                    if ("_".equals(c)) {
                        temp += String.valueOf(param[i].charAt(++j)).toUpperCase();
                    } else temp += c;
                }
            }
            if (i != param.length - 1) temp += ",";
        }
        return temp;
    }

    /**
     * byte数组转String
     **/
    public static String byteToStr(byte[] byteArray) {
        String rst = "";
        for (int i = 0; i < byteArray.length; i++) {
            rst += byteToHex(byteArray[i]);
        }
        return rst;
    }

    /**
     * byte转String
     **/
    public static String byteToHex(byte b) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(b >>> 4) & 0X0F];
        tempArr[1] = Digit[b & 0X0F];
        String s = new String(tempArr);
        return s;
    }

}
