package top.javaguo.utils;

import javax.servlet.http.HttpServletResponse;

/**
 * 跨域工具类
 * 
 * @Date 2018/11/13
 * @author javaGuo
 */
public class GuoCrossDomainUtil {

	/**
	 * 设置跨域属性
	 */
	public static void setCrossDomain(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json; charset=utf-8");
		response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
		response.setHeader("Cache-Control", "no-cache");
	}

}
