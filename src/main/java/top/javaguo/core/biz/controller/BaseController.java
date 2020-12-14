package top.javaguo.core.biz.controller;

import java.util.Map;

import top.javaguo.core.biz.service.BaseService;
import top.javaguo.core.resp.RespBean;

/**
 * 基础controller
 * 
 * @author javaGuo
 * @date 2018-12-06
 */
public class BaseController<T> {

	/**
	 * 返回拦截，当使用了缓存，同时需要清空多对象缓存集合时调用该方法
	 * 
	 * @param respBean
	 * @param baseService
	 * @return
	 */
	public RespBean<Map<String, Object>> returnIntercept(RespBean<Map<String, Object>> respBean,
			BaseService<T> baseService) {
		if ("0000000".equals(respBean.getCode())) {
			baseService.clearBeanManyCache();
		}
		return respBean;
	}
}
