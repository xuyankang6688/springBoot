package top.javaguo.biz.system.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.javaguo.biz.system.bean.AppActive;
import top.javaguo.biz.system.service.AppActiveService;
import top.javaguo.core.biz.controller.BaseController;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoRespBeanUtil;
import top.javaguo.utils.GuoStringUtil;
import top.javaguo.utils.SnowflakeIdWorkerUtil;

/**
 * @describe 首页活动展示
 * @author 
 * @date 2019-11-25
 */
@RestController
@RequestMapping("/system/appActive")
public class AppActiveController extends BaseController<AppActive>{

	/**首页活动展示**/
	@Autowired
	private AppActiveService appActiveService;

	/**根据条件查询所有**/
	@GetMapping("/selectAll")
	public RespBean<Map<String, Object>> selectAll(AppActive bean) { return appActiveService.selectAll(bean); }

	/**
	 * 通过id删除
	 * @非空参数：id
	 */
	@PostMapping("/deleteById")
	public RespBean<Map<String, Object>> deleteById(String id) { 
		if (GuoStringUtil.isEmpty(id)) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(appActiveService.deleteById(id), appActiveService);
	}

	/**
	 * 通过id查询
	 * @非空参数：id
	 */
	@GetMapping("/selectById")
	public RespBean<Map<String, Object>> selectById(AppActive bean) { 
		if (GuoStringUtil.isEmpty(bean.getId())) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return appActiveService.selectById(bean); 
	}

	/**
	 * 通过ids集合删除
	 * @非空参数：ids
	 */
	@PostMapping("/deleteByIds")
	public RespBean<Map<String, Object>> deleteByIds(String ids) { 
		if (GuoStringUtil.isEmpty(ids)) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(appActiveService.deleteByIds(ids), appActiveService); 
	}

	/**
	 * 添加
	 * @非空参数：id
	 */
	@PostMapping("/insert")
	public RespBean<Map<String, Object>> insert(AppActive bean) {
		if(GuoStringUtil.isEmpty(new String[] { bean.getImg(), bean.getType(), bean.getParam() })){
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		bean.setId(SnowflakeIdWorkerUtil.SIWU.nextId());
		return returnIntercept(appActiveService.insert(bean), appActiveService);
	}

	/**
	 * 通过id修改
	 * @非空参数：id
	 */
	@PostMapping("/updateById")
	public RespBean<Map<String, Object>> updateById(AppActive bean) {
		if(GuoStringUtil.isEmpty(new String[] { bean.getId(),bean.getImg(), bean.getType(), bean.getParam() })){
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(appActiveService.updateById(bean), appActiveService);
	}

	/**LayUI根据条件查询所有**/
	@GetMapping("/selectAllForLayUI")
	public Map<String, Object> selectAllForLayUI(AppActive bean) {
		return appActiveService.selectAllForLayUI(bean); }


}
