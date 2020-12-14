package top.javaguo.biz.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import top.javaguo.biz.system.bean.PerRole;
import top.javaguo.biz.system.bean.PerRoleResource;
import top.javaguo.biz.system.bean.PerUserRole;
import top.javaguo.biz.system.dao.perRole.PerRoleDao;
import top.javaguo.biz.system.dao.perRoleResource.PerRoleResourceDao;
import top.javaguo.biz.system.dao.perUserRole.PerUserRoleDao;
import top.javaguo.core.biz.service.BaseService;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoRespBeanUtil;
import top.javaguo.core.util.SnowflakeIdWorkerUtil;

/**
 * @describe 权限-角色
 * @author javaGuo
 * @date 2018-03-22
 */
@Service
public class PerRoleService extends BaseService<PerRole> {

	/** 权限角色 **/
	@Autowired
	private PerRoleDao perRoleDao;

	/** 权限角色资源 **/
	@Autowired
	private PerRoleResourceDao perRoleResourceDao;

	/** 权限用户角色服务 **/
	@Autowired
	private PerUserRoleDao perUserRoleDao;

	/** 根据条件查询所有 **/
	public RespBean<Map<String, Object>> selectAll(PerRole perRole) {
		return getResult(perRoleDao, "selectAll", perRole);
	}

	/** 添加 **/
	public RespBean<Map<String, Object>> insert(PerRole perRole) {
		RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
		try {
			String id = SnowflakeIdWorkerUtil.SIWU.nextId();
			perRole.setId(id);
			int result = perRoleDao.insert(perRole);
			// 创建角色时，同时绑定权限值
			if (result > 0 && perRole.getPerResourceIds() != null) {
				String arrrResourceId[] = perRole.getPerResourceIds().split(",");
				if (arrrResourceId != null && arrrResourceId.length > 0) {
					List<PerRoleResource> perRoleResourceList = new ArrayList<PerRoleResource>();
					for (String resourceId : arrrResourceId) {
						PerRoleResource perRoleResource = new PerRoleResource();
						perRoleResource.setPerResourceId(resourceId);
						perRoleResource.setPerRoleId(perRole.getId());
						perRoleResourceList.add(perRoleResource);
					}
					result = perRoleResourceDao.insertBatch(perRoleResourceList);
				}
			}
			if (result > 0)
				respBean = GuoRespBeanUtil.setSuccessRespBean(respBean);
		} catch (Exception e) {
			e.printStackTrace();
			respBean = GuoRespBeanUtil.setErrorRespBean(respBean);
		}
		return respBean;
	}

	/** 通过id删除 **/
	public RespBean<Map<String, Object>> deleteById(String id) {
		RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
		PerRoleResource perRoleResource = new PerRoleResource();
		perRoleResource.setLimit(9999);
		perRoleResource.setPerRoleId(id + "");
		int result = 0;
		boolean flag = false;
		try {
			// 获取当前删除角色绑定过的资源对象
			List<PerRoleResource> perRoleResourceList = perRoleResourceDao.selectAll(perRoleResource);
			if (perRoleResourceList.size() > 0) {
				String ids = "";
				for (int i = 0; i < perRoleResourceList.size(); i++) {
					ids += perRoleResourceList.get(i).getId();
					if (i != perRoleResourceList.size() - 1) {
						ids += ",";
					}
				}
				perRoleResourceDao.deleteByIds(ids);
				flag = true;
			}
			// flag表示删除成功，perRoleResourceList.size() < 0表示当前角色未绑定其他资源
			if (perRoleResourceList.size() <= 0 || flag)
				result = perRoleDao.deleteById(id);
			if (result > 0) {
				respBean = GuoRespBeanUtil.setSuccessRespBean(respBean);
			}
			respBean.getData().put("result", result);
		} catch (Exception e) {
			e.printStackTrace();
			respBean = GuoRespBeanUtil.setErrorRespBean(respBean);
		}
		return respBean;
	}

	/** 通过id修改 **/
	public RespBean<Map<String, Object>> updateById(PerRole perRole) {
		RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
		try {
			int result = perRoleDao.updateById(perRole);
			perRoleResourceDao.deleteByPerRoleId(perRole.getId());
			// 编辑角色时，同时删除之前绑定的资源值在新增添加的资源值
			if (result > 0 && perRole.getPerResourceIds() != null) {
				String arrrResourceId[] = perRole.getPerResourceIds().split(",");
				if (arrrResourceId != null && arrrResourceId.length > 0) {
					List<PerRoleResource> perRoleResourceList = new ArrayList<PerRoleResource>();
					for (String resourceId : arrrResourceId) {
						PerRoleResource perRoleResource = new PerRoleResource();
						perRoleResource.setPerResourceId(resourceId);
						perRoleResource.setPerRoleId(perRole.getId());
						perRoleResourceList.add(perRoleResource);
					}
					result = perRoleResourceDao.insertBatch(perRoleResourceList);
				}
			}
			if (result > 0) {
				respBean = GuoRespBeanUtil.setSuccessRespBean(respBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			respBean = GuoRespBeanUtil.setErrorRespBean(respBean);
		}
		return respBean;
	}

	/** 通过id查询 **/
	public RespBean<Map<String, Object>> selectById(PerRole perRole) {
		return getResult(perRoleDao, "selectById", perRole);
	}

	/** 通过ids集合删除 **/
	public RespBean<Map<String, Object>> deleteByIds(String ids) {
		return getResult(perRoleDao, "deleteByIds", ids);
	}

	/**
	 * 通过userId查询角色并标识该userId的角色
	 * 
	 * @非空参数：用户编号userId
	 */
	@GetMapping("/selectRoleWithUserOwnerByUserId")
	public RespBean<Map<String, Object>> selectRoleWithUserOwnerByUserId(String userId) {
		RespBean<Map<String, Object>> responseBean = GuoRespBeanUtil.initRespBean();
		Map<String, Object> map = responseBean.getData();
		if (userId != null) {
			try {
				PerRole perRole = new PerRole();
				perRole.setLimit(9999);
				// 查询所有角色
				List<PerRole> perRoleList = perRoleDao.selectAll(perRole);
				// 查询所有用户角色表中某userId的信息
				PerUserRole perUserRole = new PerUserRole();
				perUserRole.setUserId(userId + "");
				perUserRole.setLimit(9999);
				List<PerUserRole> perUserRoleList = perUserRoleDao.selectAll(perUserRole);
				// 便利资源
				for (PerRole perRoleTemp : perRoleList) {
					// 判断当前资源是否在属于某角色
					for (PerUserRole perUserRoleTemp : perUserRoleList) {
						if (perRoleTemp.getId().equals(perUserRoleTemp.getRoleId())) {
							perRoleTemp.setUserOwner("0");
						}
					}
				}
				map.put("list", perRoleList);
				GuoRespBeanUtil.setSuccessRespBean(responseBean);
			} catch (Exception e) {
				GuoRespBeanUtil.setErrorRespBean(responseBean);
			}
		}
		return responseBean;
	}

	/** LayUI根据条件查询所有 **/
	public Map<String, Object> selectAllForLayUI(PerRole perRole) {
		return getResult(perRoleDao, "selectAllForLayUI", perRole).getData();
	}

	/** @Example: mybatis映射一对多例子 **/
	public List<PerRole> selectAllForOneToMany() {
		return perRoleDao.selectAllForOneToMany();
	}

}
