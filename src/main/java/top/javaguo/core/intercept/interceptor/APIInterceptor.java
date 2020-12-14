package top.javaguo.core.intercept.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.javaguo.core.cache.redis.GuoRedisUtil;
import top.javaguo.core.intercept.utils.InterceptUtil;
import top.javaguo.core.publicParam.PublicParamUtil;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoRespBeanUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author javaGuo
 * @describe API拦截器
 * @date 2019/02/26
 */
@Component
public class APIInterceptor implements HandlerInterceptor {

    @Autowired
    private GuoRedisUtil guoRedisUtil;

    /** 是否验证签名 **/
    public boolean getValidateSignFlag() {
        return Boolean.valueOf(PublicParamUtil.map.get("APIInterceptor_validateSignFlag"));
    }

    /** 是否验证令牌 **/
    public boolean getValidateTokenFlag() {
        return Boolean.valueOf(PublicParamUtil.map.get("APIInterceptor_validateTokenFlag"));
    }

    /** 是否验证权限 **/
    public boolean getValidateAuthFlag() {
        return Boolean.valueOf(PublicParamUtil.map.get("APIInterceptor_validateAuthFlag"));
    }

    /** 私有key **/
    public String getPrivateKey() {
        return PublicParamUtil.map.get("APIInterceptor_privateKey");
    }

    /** 不需要验证token的url **/
    public String[] getExcludeTokenUrl() {
        return PublicParamUtil.map.get("APIInterceptor_excludeTokenUrl") == null ? new String[]{} : PublicParamUtil.map.get("APIInterceptor_excludeTokenUrl").split(",");
    }

    /** 不需要验证权限的url **/
    public String[] getExcludeAuthUrl() {
        return PublicParamUtil.map.get("APIInterceptor_excludeAuthUrl") == null ? new String[]{} : PublicParamUtil.map.get("APIInterceptor_excludeAuthUrl").split(",");
    }
    /**
     * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，SpringMVC中的Interceptor拦截器是链式的，可以同时存在
     * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，而且所有的Interceptor中的preHandle方法都会在
     * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返
     * 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 设置字符流编码为UTF-8
        response.setHeader("content-type", "text/html;charset=UTF-8");
        // 请求返回对象
        RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();

        // 考虑到实时使用服务器最新的配置信息，所以每次拿公共参数PublicParamUtil.map中的数据
        // 是否验证签名
        boolean validateSignFlag = getValidateSignFlag();
        // 是否验证令牌
        boolean validateTokenFlag = getValidateTokenFlag();
        // 是否验证权限
        boolean validateAuthFlag = getValidateAuthFlag();
        // 私有key
        String privateKey = getPrivateKey();
        // 不需要验证token的url
        String[] excludeTokenUrl = getExcludeTokenUrl();
        // 不需要验证权限的url
        String[] excludeAuthUrl = getExcludeAuthUrl();

        // 验证签名
        if (validateSignFlag) {
            if (!InterceptUtil.validateSign(request, response, respBean, privateKey)) {
                return false;
            }
        }

        // 验证令牌
        if (validateTokenFlag) {
            // 判断是否需要验证令牌
            if (InterceptUtil.judgeValidateToken(request, excludeTokenUrl)) {
                // 验证令牌
                if (!InterceptUtil.validateToken(request, response, respBean,
                        guoRedisUtil, excludeAuthUrl, validateAuthFlag)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之
     * 后，也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行，也就是说在这个方法中你可以对ModelAndView进行操
     * 作。这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用，这跟Struts2里面的拦截器的执行过程有点像，
     * 只是Struts2里面的intercept方法中要手动的调用ActionInvocation的invoke方法，Struts2中调用ActionInvocation的invoke方法就是调用下一个Interceptor
     * 或者是调用action，然后要在Interceptor之前调用的内容都写在调用invoke之前，要在Interceptor之后调用的内容都写在调用invoke方法之后。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行，
     * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception exception) throws Exception {
    }

}
