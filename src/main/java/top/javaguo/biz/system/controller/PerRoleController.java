package top.javaguo.biz.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.javaguo.biz.system.bean.PerRole;
import top.javaguo.biz.system.service.PerRoleService;
import top.javaguo.core.biz.controller.BaseController;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoRespBeanUtil;
import top.javaguo.utils.GuoStringUtil;

/**
 * @describe 权限-角色
 * @author javaGuo
 * @date 2018-03-22
 */
@RestController
@RequestMapping("/system/perRole")
public class PerRoleController extends BaseController<PerRole> {

	/** 权限角色 **/
	@Autowired
	private PerRoleService perRoleService;

	/** 根据条件查询所有 **/
	@GetMapping("/selectAll")
	public RespBean<Map<String, Object>> selectAll(PerRole perRole) {
		return perRoleService.selectAll(perRole);
	}

	/**
	 * 添加
	 * 
	 * @非空参数：角色名称roleName
	 */
	@PostMapping("/insert")
	public RespBean<Map<String, Object>> insert(PerRole perRole) {
		if (GuoStringUtil.isEmpty(new String[] { perRole.getRoleName() })) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return perRoleService.insert(perRole);
	}

	/**
	 * 通过id删除
	 * 
	 * @非空参数：编号id
	 */
	@PostMapping("/deleteById")
	public RespBean<Map<String, Object>> deleteById(String id) {
		if (GuoStringUtil.isEmpty(id)) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return perRoleService.deleteById(id);
	}

	/**
	 * 通过id修改
	 * 
	 * @非空参数：编号id
	 */
	@PostMapping("/updateById")
	public RespBean<Map<String, Object>> updateById(PerRole perRole) {
		if (GuoStringUtil.isEmpty(new String[] { perRole.getId() })) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return perRoleService.updateById(perRole);
	}

	/**
	 * 通过id查询
	 * 
	 * @非空参数：编号id
	 */
	@GetMapping("/selectById")
	public RespBean<Map<String, Object>> selectById(PerRole perRole) {
		if (GuoStringUtil.isEmpty(perRole.getId())) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return perRoleService.selectById(perRole);
	}

	/**
	 * 通过ids集合删除
	 * 
	 * @非空参数：编号集合ids
	 */
	@PostMapping("/deleteByIds")
	public RespBean<Map<String, Object>> deleteByIds(String ids) {
		if (GuoStringUtil.isEmpty(ids)) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return perRoleService.deleteByIds(ids);
	}

	/**
	 * 通过userId查询角色并标识该userId的角色
	 * 
	 * @非空参数：用户编号userId
	 */
	@GetMapping("/selectRoleWithUserOwnerByUserId")
	public RespBean<Map<String, Object>> selectRoleWithUserOwnerByUserId(String userId) {
		if (GuoStringUtil.isEmpty(userId)) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return perRoleService.selectRoleWithUserOwnerByUserId(userId);
	}

	/**
	 * LayUI根据条件查询所有
	 * 
	 * @param perRole
	 * @return
	 */
	@GetMapping("/selectAllForLayUI")
	public Map<String, Object> selectAllForLayUI(PerRole perRole) {
		return perRoleService.selectAllForLayUI(perRole);
	}

	/** @Example: mybatis映射一对多例子 **/
	@GetMapping("/selectAllForOneToMany")
	public List<PerRole> selectAllForOneToMany() {
		return perRoleService.selectAllForOneToMany();
	}

}
