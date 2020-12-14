package top.javaguo.biz.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.javaguo.biz.system.bean.UserCollect;
import top.javaguo.biz.system.dao.userCollect.UserCollectDao;
import top.javaguo.core.biz.service.BaseService;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoRespBeanUtil;
import top.javaguo.utils.SnowflakeIdWorkerUtil;

/**
 * @describe 收藏表
 * @author admin
 * @date 2019-08-13
 */
@Service
public class UserCollectService extends BaseService<UserCollect>{

	/**收藏表**/
	@Autowired
	private UserCollectDao userCollectDao;

	/**根据条件查询所有**/
	public RespBean<Map<String, Object>> selectAll(UserCollect bean) { return getResult(userCollectDao, "selectAll", bean); }

	/**LayUI根据条件查询所有**/
	public Map<String, Object> selectAllForLayUI(UserCollect bean) { return getResult(userCollectDao, "selectAllForLayUI", bean).getData(); }

	/**添加**/
	public RespBean<Map<String, Object>> insert(UserCollect bean) { return getResult(userCollectDao, "insert", bean); }

	/**通过id删除**/
	public RespBean<Map<String, Object>> deleteById(String id) { return getResult(userCollectDao, "deleteById", id); }

	/**通过id修改**/
	public RespBean<Map<String, Object>> updateById(UserCollect bean) { return getResult(userCollectDao, "updateById", bean); }

	/**通过id查询**/
	public RespBean<Map<String, Object>> selectById(UserCollect bean) { return getResult(userCollectDao, "selectById", bean); }

	/**通过ids集合删除**/
	public RespBean<Map<String, Object>> deleteByIds(String ids) { return getResult(userCollectDao, "deleteByIds", ids); }

	/** 清空多对象缓存集合 **/
	@Override
	public void clearBeanManyCache() {
	}

	/**收藏商品api**/
	public RespBean<Map<String, Object>> isCollectGoods(UserCollect bean, String isCollect) {
		RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
		//查询是否已有该数据
		List<UserCollect> list = userCollectDao.selectAll(bean);
		//判断是否已有数据   有数据就修改，没有就新增
		if(list.size() > 0) {
			//0收藏，1取消收藏
			bean.setIsDeleted(isCollect);
			bean.setId(list.get(0).getId());
			int up = userCollectDao.updateById(bean);
			if(up < 1) return GuoRespBeanUtil.setErrorRespBean(respBean);
			return GuoRespBeanUtil.setSuccessRespBean(respBean);
		}else {
			bean.setIsDeleted(isCollect);
			bean.setId(SnowflakeIdWorkerUtil.SIWU.nextId());
			int add = userCollectDao.insert(bean);
			if(add < 1) return GuoRespBeanUtil.setErrorRespBean(respBean);
			
			return GuoRespBeanUtil.setSuccessRespBean(respBean);
		}
	}


}
