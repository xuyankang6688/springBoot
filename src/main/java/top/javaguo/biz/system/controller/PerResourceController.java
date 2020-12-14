package top.javaguo.biz.system.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.javaguo.biz.system.bean.PerResource;
import top.javaguo.biz.system.service.PerResourceService;
import top.javaguo.core.biz.controller.BaseController;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoRespBeanUtil;
import top.javaguo.utils.GuoStringUtil;

/**
 * @describe 权限-资源
 * @author javaGuo
 * @date 2018-03-20
 */
@RestController
@RequestMapping("/system/perResource")
public class PerResourceController extends BaseController<PerResource> {

	/** 权限资源 **/
	@Autowired
	private PerResourceService perResourceService;

	/** 根据条件查询所有 **/
	@GetMapping("/selectAll")
	public RespBean<Map<String, Object>> selectAll(PerResource perResource) {
		return perResourceService.selectAll(perResource);
	}

	/**
	 * 添加
	 * 
	 * @非空参数：资源名称resName、资源地址resUrl、菜单级别menuLevel
	 */
	@PostMapping("/insert")
	public RespBean<Map<String, Object>> insert(PerResource perResource) {
		if (GuoStringUtil.isEmpty(new String[] { perResource.getResName(),
				// perResource.getResUrl(),
				perResource.getMenuLevel() })) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return perResourceService.insert(perResource);
	}

	/**
	 * 通过id删除
	 * 
	 * @非空参数：编号id
	 */
	@PostMapping("/deleteById")
	public RespBean<Map<String, Object>> deleteById(String id) {
		if (GuoStringUtil.isEmpty(id))
			return GuoRespBeanUtil.initParamNotNullRespBean();
		return perResourceService.deleteById(id);
	}

	/**
	 * 通过id修改
	 * 
	 * @非空参数：编号id
	 */
	@PostMapping("/updateById")
	public RespBean<Map<String, Object>> updateById(PerResource perResource) {
		if (GuoStringUtil.isEmpty(new String[] { perResource.getId() })) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return perResourceService.updateById(perResource);
	}

	/**
	 * 通过id查询
	 * 
	 * @非空参数：编号id
	 */
	@GetMapping("/selectById")
	public RespBean<Map<String, Object>> selectById(PerResource perResource) {
		if (GuoStringUtil.isEmpty(perResource.getId())) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return perResourceService.selectById(perResource);
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
		return perResourceService.deleteByIds(ids);
	}

	/**
	 * 获取对应角色的资源ids
	 * 
	 * @非空参数：角色编号roleId
	 */
	@GetMapping("/selectResourceIdsByRoleId")
	public RespBean<Map<String, Object>> selectResourceIdsByRoleId(String roleId) {
		if (GuoStringUtil.isEmpty(roleId)) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return perResourceService.selectResourceIdsByRoleId(roleId);
	}

	/**
	 * LayUI根据条件查询所有
	 * 
	 * @param perResource
	 * @return
	 */
	@GetMapping("/selectAllForLayUI")
	public Object selectMember(PerResource perResource) {
		return perResourceService.selectAllForLayUI(perResource);
	}

	/** 权限-资源编辑界面获取一级二级资源 **/
	@GetMapping("/selectAllForPerResourceEdit")
	public RespBean<Map<String, Object>> selectAllForPerResourceEdit() {
		return perResourceService.selectAllForPerResourceEdit();
	}

}
