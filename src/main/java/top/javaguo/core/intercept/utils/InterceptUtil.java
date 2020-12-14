package top.javaguo.core.intercept.utils;

import top.javaguo.biz.sso.bean.PerResource;
import top.javaguo.biz.sso.dto.SysUserResult;
import top.javaguo.core.cache.redis.GuoRedisUtil;
import top.javaguo.core.resp.RespBean;
import top.javaguo.core.resp.enums.RespMsgEnum;
import top.javaguo.utils.GuoJsonUtil;
import top.javaguo.utils.GuoStringUtil;
import top.javaguo.utils.md5.GuoMD5Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 拦截器公用方法
 *
 * @author javaGuo
 * @Date 2019/02/26 16:12
 */
public class InterceptUtil {

    /**
     * 验证签名
     *
     * @author javaGuo
     * @date 20190222
     */
    public static boolean validateSign(HttpServletRequest request, HttpServletResponse response,
                                       RespBean<Map<String, Object>> respBean, String privateKey)
            throws Exception {
        // 公开key
        String publicKey = request.getHeader("publicKey");
        // 前端签名
        String sign = request.getHeader("sign");

        // 判断必传参数是否齐全
        if (GuoStringUtil.isEmpty(new String[]{publicKey, sign})) {
            // 请求返回对象设置应答语
            respBean.setCode(RespMsgEnum._0000011.getCode());
            respBean.setMsg(RespMsgEnum._0000011.getMsg());
            // 给调用者返回应答语
            response.getWriter().print(GuoJsonUtil.Object2Json(respBean));
            return false;
        }

        // 根据前台传来的时间戳生成本地签名
        String localSign = GuoMD5Util.GetMD5Code(privateKey + publicKey);
        // 判断前端签名是否正确
        if (!sign.equalsIgnoreCase(localSign)) {
            // 请求返回对象设置应答语
            respBean.setCode(RespMsgEnum._4444444.getCode());
            respBean.setMsg(RespMsgEnum._4444444.getMsg());
            // 给调用者返回应答语
            response.getWriter().print(GuoJsonUtil.Object2Json(respBean));
            return false;
        }

        // 判断请求是否在有效时间内
        long nowTimespan = System.currentTimeMillis();
        if (nowTimespan - Long.valueOf(publicKey) > 2000) {
            // 请求返回对象设置应答语
            respBean.setCode(RespMsgEnum._5555555.getCode());
            respBean.setMsg(RespMsgEnum._5555555.getMsg());
            // 给调用者返回应答语
            response.getWriter().print(GuoJsonUtil.Object2Json(respBean));
            return false;
        }
        return true;
    }

    /**
     * 验证令牌
     *
     * @author javaGuo
     * @date 20190222
     */
    public static boolean validateToken(HttpServletRequest request, HttpServletResponse response,
                                        RespBean<Map<String, Object>> respBean, GuoRedisUtil guoRedisUtil,
                                        String[] excludeAuthUrl, boolean validateAuthFlag)
            throws Exception {
        // 令牌
        String token = request.getHeader("token");
        if (GuoStringUtil.isEmpty(token)) {
            token = request.getParameter("token");
        }

        // 1请求无令牌
        // 判断token是否为空
        if (GuoStringUtil.isEmpty(token)) {
            // 当请求状态为404时则跳转404界面
            if (response.getStatus() == 404) {
                response.sendRedirect("/static/system/html/error/404.html");
                return false;
            }
            // 请求返回对象设置应答语
            respBean.setCode(RespMsgEnum._2222222.getCode());
            respBean.setMsg(RespMsgEnum._2222222.getMsg());
            // 给调用者返回应答语
            response.getWriter().print(GuoJsonUtil.Object2Json(respBean));
            return false;
        }

        long startTime = System.currentTimeMillis();    //获取开始时间


        // 2 请求有令牌

        // 在redis中获取token
        Object tokenValue = guoRedisUtil.get(token);

        // 2-1 令牌验证通过
        // 去redis中验证令牌是否存在，若为null则说明不存在
        if (tokenValue != null) {

            long endTime = System.currentTimeMillis();    //获取结束时间

            System.out.println("第一处使用时间：" + (endTime - startTime) + "ms");    //输出程序运行时间

            // 刷新令牌有效期
            guoRedisUtil.expire(token, 0);

            long endTime2 = System.currentTimeMillis();    //获取结束时间

            System.out.println("第二处使用时间：" + (endTime2 - endTime) + "ms");    //输出程序运行时间

            // 当请求状态为500时则说明代码异常且未处理，此处做最终守护
            if (response.getStatus() == 500) {
                // 请求返回对象设置应答语
                respBean.setCode(RespMsgEnum._9999999.getCode());
                respBean.setMsg(RespMsgEnum._9999999.getMsg());
                // 给调用者返回应答语
                response.getWriter().print(GuoJsonUtil.Object2Json(respBean));
                return false;
            }

            // 验证权限
            if (validateAuthFlag) {
                // 权限验证标识
                boolean authResult = false;
                // 判断是否需要验证权限
                if (judgeValidateAuth(request, excludeAuthUrl)) {
                    // 验证权限标识
                    authResult = validateAuth(request, token, (String)tokenValue);

                    if (!authResult) {
                        // 请求返回对象设置应答语
                        respBean.setCode(RespMsgEnum._8888888.getCode());
                        respBean.setMsg(RespMsgEnum._8888888.getMsg());
                        // 给调用者返回应答语
                        response.getWriter().print(GuoJsonUtil.Object2Json(respBean));
                    }

                    long endTime3 = System.currentTimeMillis();    //获取结束时间

                    System.out.println("1最终使用时间：" + (endTime3 - startTime) + "ms");    //输出程序运行时间

                    return authResult;
                } else {
                    return true;
                }
            }


            long endTime3 = System.currentTimeMillis();    //获取结束时间

            System.out.println("2最终使用时间：" + (endTime3 - startTime) + "ms");    //输出程序运行时间

            return true;
        }
        // 2-2 令牌验证不通过
        else {
            // 请求返回对象设置应答语
            respBean.setCode(RespMsgEnum._0000005.getCode());
            respBean.setMsg(RespMsgEnum._0000005.getMsg());
            // 给调用者返回应答语
            response.getWriter().print(GuoJsonUtil.Object2Json(respBean));
            return false;
        }
    }

    /**
     * 验证权限
     *
     * @param request
     * @param token
     * @return
     */
    static boolean validateAuth(HttpServletRequest request, String token, String tokenValue) {
        // 请求url
        String url = request.getRequestURI();

        // 用户资源列表
        List<PerResource> listPerResource =
                GuoJsonUtil.json2Bean(
                        tokenValue, SysUserResult.class
                ).getListPerResource();

        // 判断用户是否有当前接口权限
        for (int i = 0; i < listPerResource.size(); i++) {
            PerResource perResource = listPerResource.get(i);
            // parentId为0时此数据为资源类别无需比较
            if (perResource.getParentId().equals("0")) {
                continue;
            }
            // 拿请求url和用户权限接口url对比
            if (url.indexOf(perResource.getApiUrl()) >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否需要验证令牌
     *
     * @author javaGuo
     * @date 20190225
     */
    public static boolean judgeValidateToken(HttpServletRequest request, String[] excludeTokenUrl) {
        String url = request.getRequestURI();
        for (int i = 0; i < excludeTokenUrl.length; i++) {
            if (url.equals(excludeTokenUrl[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否需要验证权限
     *
     * @author javaGuo
     * @date 20190225
     */
    public static boolean judgeValidateAuth(HttpServletRequest request, String[] excludeAuthUrl) {
        String url = request.getRequestURI();
        for (int i = 0; i < excludeAuthUrl.length; i++) {
            if (url.indexOf(excludeAuthUrl[i]) >= 0) {
                return false;
            }
        }
        return true;
    }


}
