package top.javaguo.biz.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.javaguo.biz.system.bean.PerUserRole;
import top.javaguo.biz.system.dao.perUserRole.PerUserRoleDao;
import top.javaguo.core.biz.service.BaseService;

/**
 * @describe 权限-用户角色
 * @author javaGuo
 * @date 2018-03-28
 */
@Service
public class PerUserRoleService extends BaseService<PerUserRole> {

	/** 权限用户角色 **/
	@Autowired
	private PerUserRoleDao perUserRoleDao;

	/** 根据条件查询所有 **/
	public List<PerUserRole> selectAll(PerUserRole perUserRole) {
		return perUserRoleDao.selectAll(perUserRole);
	}

	/** 根据条件查询所有的个数 **/
	public int selectTotal(PerUserRole perUserRole) {
		return perUserRoleDao.selectTotal(perUserRole);
	}

	/** 添加 **/
	public int insert(PerUserRole perUserRole) {
		return perUserRoleDao.insert(perUserRole);
	}

	/** 通过id删除 **/
	public int deleteById(String id) {
		return perUserRoleDao.deleteById(id);
	}

	/** 通过id修改 **/
	public int updateById(PerUserRole perUserRole) {
		return perUserRoleDao.updateById(perUserRole);
	}

	/** 通过id查询 **/
	public PerUserRole selectById(PerUserRole perUserRole) {
		return perUserRoleDao.selectById(perUserRole);
	}

	/** 通过ids集合删除 **/
	public int deleteByIds(String ids) {
		return perUserRoleDao.deleteByIds(ids);
	}

	/** 批量增加 **/
	public int insertBatch(List<PerUserRole> list) {
		return perUserRoleDao.insertBatch(list);
	}

	/** 通过用户编号删除 **/
	public int deleteByUserId(String id) {
		return perUserRoleDao.deleteByUserId(id);
	}

}
