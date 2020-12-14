package top.javaguo.biz.system.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.javaguo.biz.system.bean.CompanyUser;
import top.javaguo.biz.system.service.CompanyUserService;
import top.javaguo.core.biz.controller.BaseController;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoRespBeanUtil;
import top.javaguo.utils.GuoStringUtil;
import top.javaguo.utils.SnowflakeIdWorkerUtil;

/**
 * @describe 车主或者货主公司表
 * @author 
 * @date 2019-12-07
 */
@RestController
@RequestMapping("/system/companyUser")
public class CompanyUserController extends BaseController<CompanyUser>{

	/**车主或者货主公司表**/
	@Autowired
	private CompanyUserService companyUserService;

	/**根据条件查询所有**/
	@GetMapping("/selectAll")
	public RespBean<Map<String, Object>> selectAll(CompanyUser bean) { return companyUserService.selectAll(bean); }

	/**
	 * 通过id删除
	 * @非空参数：id
	 */
	@PostMapping("/deleteById")
	public RespBean<Map<String, Object>> deleteById(String id) { 
		if (GuoStringUtil.isEmpty(id)) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(companyUserService.deleteById(id), companyUserService);
	}

	/**
	 * 通过id查询
	 * @非空参数：id
	 */
	@GetMapping("/selectById")
	public RespBean<Map<String, Object>> selectById(CompanyUser bean) { 
		if (GuoStringUtil.isEmpty(bean.getId())) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return companyUserService.selectById(bean); 
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
		return returnIntercept(companyUserService.deleteByIds(ids), companyUserService); 
	}

	/**
	 * 添加
	 * @非空参数：id
	 */
	@PostMapping("/insert")
	public RespBean<Map<String, Object>> insert(CompanyUser bean) {
		if(GuoStringUtil.isEmpty(new String[] { bean.getCompanyName(), bean.getCompanyAddress(), bean.getCompanyPhone(), bean.getCompanysImg(), bean.getUserId() })){
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		bean.setId(SnowflakeIdWorkerUtil.SIWU.nextId());
		return returnIntercept(companyUserService.insert(bean), companyUserService);
	}

	/**
	 * 通过id修改
	 * @非空参数：id
	 */
	@PostMapping("/updateById")
	public RespBean<Map<String, Object>> updateById(CompanyUser bean) {
		if(GuoStringUtil.isEmpty(new String[] { bean.getId(),bean.getCompanyName(), bean.getCompanyAddress(), bean.getCompanyPhone(), bean.getCompanysImg(), bean.getUserId() })){
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(companyUserService.updateById(bean), companyUserService);
	}

	/**LayUI根据条件查询所有**/
	@GetMapping("/selectAllForLayUI")
	public Map<String, Object> selectAllForLayUI(CompanyUser bean) { return companyUserService.selectAllForLayUI(bean); }


}
