package top.javaguo.biz.system.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.javaguo.biz.system.bean.SysPublicParam;
import top.javaguo.biz.system.service.SysPublicParamService;
import top.javaguo.core.biz.controller.BaseController;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoRespBeanUtil;
import top.javaguo.utils.GuoStringUtil;
import top.javaguo.core.util.SnowflakeIdWorkerUtil;

/**
 * @describe 公共参数
 * @author javaGuo
 * @date 2019-02-26
 */
@RestController
@RequestMapping("/system/sysPublicParam")
public class SysPublicParamController extends BaseController<SysPublicParam>{

	/**公共参数**/
	@Autowired
	private SysPublicParamService sysPublicParamService;

	/**根据条件查询所有**/
	@GetMapping("/selectAll")
	public RespBean<Map<String, Object>> selectAll(SysPublicParam bean) { return sysPublicParamService.selectAll(bean); }

	/**
	 * 通过id删除
	 * @非空参数：公共参数id
	 */
	@PostMapping("/deleteById")
	public RespBean<Map<String, Object>> deleteById(String id) { 
		if (GuoStringUtil.isEmpty(id)) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(sysPublicParamService.deleteById(id), sysPublicParamService);
	}

	/**
	 * 通过id查询
	 * @非空参数：公共参数id
	 */
	@GetMapping("/selectById")
	public RespBean<Map<String, Object>> selectById(SysPublicParam bean) { 
		if (GuoStringUtil.isEmpty(bean.getId())) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return sysPublicParamService.selectById(bean); 
	}

	/**
	 * 通过ids集合删除
	 * @非空参数：公共参数ids
	 */
	@PostMapping("/deleteByIds")
	public RespBean<Map<String, Object>> deleteByIds(String ids) { 
		if (GuoStringUtil.isEmpty(ids)) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(sysPublicParamService.deleteByIds(ids), sysPublicParamService); 
	}

	/**
	 * 添加
	 * @非空参数：公共参数id
	 */
	@PostMapping("/insert")
	public RespBean<Map<String, Object>> insert(SysPublicParam bean) {
		if(GuoStringUtil.isEmpty(new String[] { bean.getParamKey(), bean.getParamValue(), bean.getName() })){
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		bean.setId(SnowflakeIdWorkerUtil.SIWU.nextId());
		return returnIntercept(sysPublicParamService.insert(bean), sysPublicParamService);
	}

	/**
	 * 通过id修改
	 * @非空参数：公共参数id
	 */
	@PostMapping("/updateById")
	public RespBean<Map<String, Object>> updateById(SysPublicParam bean) {
		if(GuoStringUtil.isEmpty(new String[] { bean.getId(), bean.getParamKey(), bean.getParamValue(), bean.getName() })){
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(sysPublicParamService.updateById(bean), sysPublicParamService);
	}

	/**LayUI根据条件查询所有**/
	@GetMapping("/selectAllForLayUI")
	public Map<String, Object> selectAllForLayUI(SysPublicParam bean) { return sysPublicParamService.selectAllForLayUI(bean); }


}
