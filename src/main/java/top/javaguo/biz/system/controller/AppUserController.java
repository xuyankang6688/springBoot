package top.javaguo.biz.system.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.javaguo.biz.system.bean.AppUser;
import top.javaguo.biz.system.service.AppUserService;
import top.javaguo.core.biz.controller.BaseController;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoRespBeanUtil;
import top.javaguo.utils.GuoStringUtil;
import top.javaguo.utils.SnowflakeIdWorkerUtil;
import top.javaguo.utils.md5.GuoMd5SaltToolUtil;

/**
 * @describe 用户表
 * @author admin
 * @date 2019-08-13
 */
@RestController
@RequestMapping("/system/appUser")
public class AppUserController extends BaseController<AppUser>{

	/**用户表**/
	@Autowired
	private AppUserService appUserService;

	/**根据条件查询所有**/
	@GetMapping("/selectAll")
	public RespBean<Map<String, Object>> selectAll(AppUser bean) { return appUserService.selectAll(bean); }

	/**
	 * 通过id删除
	 * @非空参数：用户表id
	 */
	@PostMapping("/deleteById")
	public RespBean<Map<String, Object>> deleteById(String id) { 
		if (GuoStringUtil.isEmpty(id)) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(appUserService.deleteById(id), appUserService);
	}

	/**
	 * 通过id查询
	 * @非空参数：用户表id
	 */
	@GetMapping("/selectById")
	public RespBean<Map<String, Object>> selectById(AppUser bean) {
		if (GuoStringUtil.isEmpty(bean.getId())) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return appUserService.selectById(bean);
	}

	/**
	 * 通过ids集合删除
	 * @非空参数：用户表ids
	 */
	@PostMapping("/deleteByIds")
	public RespBean<Map<String, Object>> deleteByIds(String ids) { 
		if (GuoStringUtil.isEmpty(ids)) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(appUserService.deleteByIds(ids), appUserService); 
	}

	/**
	 * 添加
	 * @非空参数：用户表id
	 */
	@PostMapping("/insert")
	public RespBean<Map<String, Object>> insert(AppUser bean) {
		if(GuoStringUtil.isEmpty(new String[] { bean.getAccountNumber(), bean.getPassword(), bean.getName(), bean.getPhone(), bean.getHead(), bean.getNickName(), bean.getSynopsis(), bean.getMyResume(), bean.getSex() })){
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		bean.setId(SnowflakeIdWorkerUtil.SIWU.nextId());
		//MD5加密
		String password = GuoMd5SaltToolUtil.generate(bean.getPassword(),"login");
		bean.setPassword(password);
		System.out.println(bean.getPassword());
		return returnIntercept(appUserService.insert(bean), appUserService);
	}

	/**
	 * 通过id修改
	 * @非空参数：用户表id
	 */
	@PostMapping("/updateById")
	public RespBean<Map<String, Object>> updateById(AppUser bean) {
		if(GuoStringUtil.isEmpty(new String[] { bean.getId(), bean.getAccountNumber(), bean.getPassword(), bean.getName(), bean.getPhone(), bean.getHead(), bean.getNickName(), bean.getSynopsis(), bean.getMyResume(), bean.getSex() })){
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		//MD5加密
		String password = GuoMd5SaltToolUtil.generate(bean.getPassword(),"login");
		bean.setPassword(password);
		System.out.println(bean.getPassword());
		return returnIntercept(appUserService.updateById(bean), appUserService);
	}

	/**LayUI根据条件查询所有**/
	@GetMapping("/selectAllForLayUI")
	public Map<String, Object> selectAllForLayUI(AppUser bean) { return appUserService.selectAllForLayUI(bean); }


}
