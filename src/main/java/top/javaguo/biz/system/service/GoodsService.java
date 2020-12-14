package top.javaguo.biz.system.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.javaguo.biz.system.bean.AppUser;
import top.javaguo.biz.system.bean.Goods;
import top.javaguo.biz.system.dao.appUser.AppUserDao;
import top.javaguo.biz.system.dao.goods.GoodsDao;
import top.javaguo.core.biz.service.BaseService;
import top.javaguo.core.cache.redis.GuoRedisUtil;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.CookiesUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @describe 货物表
 * @author 
 * @date 2019-12-09
 */
@Service
public class GoodsService extends BaseService<Goods>{

	/**货物表**/
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private GuoRedisUtil guoRedisUtil;

	/**用户表**/
	@Autowired
	private AppUserDao userDao;

	/**根据条件查询所有**/
	public RespBean<Map<String, Object>> selectAll(Goods bean) { return getResult(goodsDao, "selectAll", bean); }

	/**LayUI根据条件查询所有**/
	public Map<String, Object> selectAllForLayUI(Goods bean) {
		Map<String,Object> data=getResult(goodsDao, "selectAllForLayUI", bean).getData();
	/*	String token = CookiesUtils.getCookie(request);//获取用户信息
		String user= (String) guoRedisUtil.get(token);
		JSONObject json = JSONObject.fromObject(user);
		String phone = json.getString("phone");
		AppUser appUser = new AppUser();
		appUser.setPhone(phone);
		appUser.setIsDeleted("0");
		List<AppUser> appUserList = userDao.selectAll(appUser);*/
		List<Goods> goodsList= (List<Goods>) data.get("data");
		if(goodsList.size()>0){
			for(Goods goods:goodsList){
				AppUser appUser = new AppUser();
				appUser.setId(goods.getUserId());
				appUser.setIsDeleted("0");
				appUser=userDao.selectById(appUser);
				goods.setUserName(appUser.getName());
				goods.setType(appUser.getType());
			}
		}
		return data;

	}

	/**添加**/
	public RespBean<Map<String, Object>> insert(Goods bean) { return getResult(goodsDao, "insert", bean); }

	/**通过id删除**/
	public RespBean<Map<String, Object>> deleteById(String id) { return getResult(goodsDao, "deleteById", id); }

	/**通过id修改**/
	public RespBean<Map<String, Object>> updateById(Goods bean) { return getResult(goodsDao, "updateById", bean); }

	/**通过id查询**/
	public RespBean<Map<String, Object>> selectById(Goods bean) { return getResult(goodsDao, "selectById", bean); }

	/**通过ids集合删除**/
	public RespBean<Map<String, Object>> deleteByIds(String ids) { return getResult(goodsDao, "deleteByIds", ids); }

	/** 清空多对象缓存集合 **/
	@Override
	public void clearBeanManyCache() {
	}


}

