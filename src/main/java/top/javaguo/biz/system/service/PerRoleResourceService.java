package top.javaguo.biz.system.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.javaguo.biz.system.bean.PerRoleResource;
import top.javaguo.biz.system.dao.perRoleResource.PerRoleResourceDao;
import top.javaguo.core.biz.service.BaseService;

/**
 * @describe 权限-角色资源
 * @author javaGuo
 * @date 2018-03-27
 */
@Service
public class PerRoleResourceService extends BaseService<PerRoleResource> {

	/** 权限角色资源 **/
	@Autowired
	private PerRoleResourceDao perRoleResourceDao;

	/** 根据条件查询所有 **/
	public List<PerRoleResource> selectAll(PerRoleResource perRoleResource) {
		return perRoleResourceDao.selectAll(perRoleResource);
	}

	/** 根据条件查询所有的个数 **/
	public int selectTotal(PerRoleResource perRoleResource) {
		return perRoleResourceDao.selectTotal(perRoleResource);
	}

	/** 添加 **/
	public int insert(PerRoleResource perRoleResource) {
		return perRoleResourceDao.insert(perRoleResource);
	}

	/** 通过id删除 **/
	public int deleteById(String id) {
		return perRoleResourceDao.deleteById(id);
	}

	/** 通过id修改 **/
	public int updateById(PerRoleResource perRoleResource) {
		return perRoleResourceDao.updateById(perRoleResource);
	}

	/** 通过id查询 **/
	public PerRoleResource selectById(PerRoleResource perRoleResource) {
		return perRoleResourceDao.selectById(perRoleResource);
	}

	/** 通过ids集合删除 **/
	public int deleteByIds(String ids) {
		return perRoleResourceDao.deleteByIds(ids);
	}

	/** 批量增加 **/
	public int insertBatch(List<PerRoleResource> list) {
		return perRoleResourceDao.insertBatch(list);
	}

	/** 通过角色编号删除 **/
	public int deleteByRoleId(@Param("id") String id) {
		return perRoleResourceDao.deleteByPerRoleId(id);
	}

}
