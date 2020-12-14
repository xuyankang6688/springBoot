package top.javaguo.utils;

import org.slf4j.LoggerFactory;

/**
 * 日志工具类
 *
 * @author javaGuo
 * @date 2019/02/13
 */
public class GuoLogUtil {

    /**
     * error日志
     * @param msg
     */
    public static void error(String msg) {
        LoggerFactory.getLogger(getClassName()).error(msg);
    }

    /**
     * error日志
     * @param msg
     * @param obj
     */
    public static void error(String msg, Object... obj) {
        LoggerFactory.getLogger(getClassName()).error(msg, obj);
    }

    /**
     * warn日志
     * @param msg
     */
    public static void warn(String msg) {
        LoggerFactory.getLogger(getClassName()).warn(msg);
    }

    /**
     * warn日志
     * @param msg
     * @param obj
     */
    public static void warn(String msg, Object... obj) {
        LoggerFactory.getLogger(getClassName()).warn(msg, obj);
    }

    /**
     * info日志
     * @param msg
     */
    public static void info(String msg) {
        LoggerFactory.getLogger(getClassName()).info(msg);
    }

    /**
     * info日志
     * @param msg
     * @param obj
     */
    public static void info(String msg, Object... obj) {
        LoggerFactory.getLogger(getClassName()).info(msg, obj);
    }

    /**
     * debug日志
     * @param msg
     */
    public static void debug(String msg) {
        LoggerFactory.getLogger(getClassName()).debug(msg);
    }

    /**
     * debug日志
     * @param msg
     * @param obj
     */
    public static void debug(String msg, Object... obj) {
        LoggerFactory.getLogger(getClassName()).debug(msg, obj);
    }

    /**
     * 获取调用 error,info,debug静态类的类名
     * @return
     */
    private static String getClassName() {
        return new SecurityManager() {
            public String getClassName() {
                return getClassContext()[3].getName();
            }
        }.getClassName();
    }

}
