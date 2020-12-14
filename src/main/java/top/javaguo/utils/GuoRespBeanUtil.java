package top.javaguo.utils;

import java.util.HashMap;
import java.util.Map;

import top.javaguo.core.resp.RespBean;
import top.javaguo.core.resp.enums.RespMsgEnum;

/**
 * 数据返回实体类工具类
 * 
 * @author javaGuo
 * @date 2018/04/10
 */
public class GuoRespBeanUtil {

	/** 实例化respBean **/
	public static RespBean<Map<String, Object>> initRespBean() {
		RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		respBean.setData(map);
		return respBean;
	}

	/** 实例化respBean请求参数不能为空 **/
	public static RespBean<Map<String, Object>> initParamNotNullRespBean() {
		RespBean<Map<String, Object>> respBean = initRespBean();
		respBean.setCode(RespMsgEnum._0000011.getCode());
		respBean.setMsg(RespMsgEnum._0000011.getMsg());
		return respBean;
	}

	/** 实例化respBean请求feign失败 **/
	public static RespBean<Map<String, Object>> initFeignHystrixErrorRespBean() {
		RespBean<Map<String, Object>> respBean = initRespBean();
		respBean.setCode(RespMsgEnum._0000012.getCode());
		respBean.setMsg(RespMsgEnum._0000012.getMsg());
		return respBean;
	}

	/** 设置respBean执行成功的响应 **/
	public static RespBean<Map<String, Object>> setSuccessRespBean(RespBean<Map<String, Object>> respBean) {
		respBean.setCode(RespMsgEnum._0000000.getCode());
		respBean.setMsg(RespMsgEnum._0000000.getMsg());
		return respBean;
	}

	/** 设置respBean执行失败的响应 **/
	public static RespBean<Map<String, Object>> setFailRespBean(RespBean<Map<String, Object>> respBean) {
		respBean.setCode(RespMsgEnum._1111111.getCode());
		respBean.setMsg(RespMsgEnum._1111111.getMsg());
		return respBean;
	}

	/** 设置respBean执行异常的响应 **/
	public static RespBean<Map<String, Object>> setErrorRespBean(RespBean<Map<String, Object>> respBean) {
		respBean.setCode(RespMsgEnum._9999999.getCode());
		respBean.setMsg(RespMsgEnum._9999999.getMsg());
		return respBean;
	}
	
	/** 设置respBean自定义异常 **/
	public static RespBean<Map<String, Object>> setCustomContentRespBean(String msg) {
		RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>();
		respBean.setCode(RespMsgEnum._0000013.getCode());
		respBean.setMsg(msg);
		return respBean;
	}

}
