package top.javaguo.biz.system.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.javaguo.biz.system.bean.AppActive;
import top.javaguo.biz.system.dao.appActive.AppActiveDao;
import top.javaguo.core.biz.service.BaseService;
import top.javaguo.core.resp.RespBean;

/**
 * @describe 首页活动展示
 * @author 
 * @date 2019-11-25
 */
@Service
public class AppActiveService extends BaseService<AppActive>{

	/**首页活动展示**/
	@Autowired
	private AppActiveDao appActiveDao;

	/**根据条件查询所有**/
	public RespBean<Map<String, Object>> selectAll(AppActive bean) { return getResult(appActiveDao, "selectAll", bean); }

	/**LayUI根据条件查询所有**/
	public Map<String, Object> selectAllForLayUI(AppActive bean) { return getResult(appActiveDao, "selectAllForLayUI", bean).getData(); }

	/**添加**/
	public RespBean<Map<String, Object>> insert(AppActive bean) { return getResult(appActiveDao, "insert", bean); }

	/**通过id删除**/
	public RespBean<Map<String, Object>> deleteById(String id) { return getResult(appActiveDao, "deleteById", id); }

	/**通过id修改**/
	public RespBean<Map<String, Object>> updateById(AppActive bean) { return getResult(appActiveDao, "updateById", bean); }

	/**通过id查询**/
	public RespBean<Map<String, Object>> selectById(AppActive bean) { return getResult(appActiveDao, "selectById", bean); }

	/**通过ids集合删除**/
	public RespBean<Map<String, Object>> deleteByIds(String ids) { return getResult(appActiveDao, "deleteByIds", ids); }

	/** 清空多对象缓存集合 **/
	@Override
	public void clearBeanManyCache() {
	}


}

