package top.javaguo.biz.system.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.javaguo.biz.system.bean.CompanyUser;
import top.javaguo.biz.system.dao.companyUser.CompanyUserDao;
import top.javaguo.core.biz.service.BaseService;
import top.javaguo.core.resp.RespBean;

/**
 * @describe 车主或者货主公司表
 * @author 
 * @date 2019-12-07
 */
@Service
public class CompanyUserService extends BaseService<CompanyUser>{

	/**车主或者货主公司表**/
	@Autowired
	private CompanyUserDao companyUserDao;

	/**根据条件查询所有**/
	public RespBean<Map<String, Object>> selectAll(CompanyUser bean) { return getResult(companyUserDao, "selectAll", bean); }

	/**LayUI根据条件查询所有**/
	public Map<String, Object> selectAllForLayUI(CompanyUser bean) { return getResult(companyUserDao, "selectAllForLayUI", bean).getData(); }

	/**添加**/
	public RespBean<Map<String, Object>> insert(CompanyUser bean) { return getResult(companyUserDao, "insert", bean); }

	/**通过id删除**/
	public RespBean<Map<String, Object>> deleteById(String id) { return getResult(companyUserDao, "deleteById", id); }

	/**通过id修改**/
	public RespBean<Map<String, Object>> updateById(CompanyUser bean) { return getResult(companyUserDao, "updateById", bean); }

	/**通过id查询**/
	public RespBean<Map<String, Object>> selectById(CompanyUser bean) { return getResult(companyUserDao, "selectById", bean); }

	/**通过ids集合删除**/
	public RespBean<Map<String, Object>> deleteByIds(String ids) { return getResult(companyUserDao, "deleteByIds", ids); }

	/** 清空多对象缓存集合 **/
	@Override
	public void clearBeanManyCache() {
	}


}

