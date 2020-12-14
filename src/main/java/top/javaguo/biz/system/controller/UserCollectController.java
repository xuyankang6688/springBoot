package top.javaguo.biz.system.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.javaguo.biz.system.bean.UserCollect;
import top.javaguo.biz.system.service.UserCollectService;
import top.javaguo.core.biz.controller.BaseController;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoRespBeanUtil;
import top.javaguo.utils.GuoStringUtil;
import top.javaguo.utils.SnowflakeIdWorkerUtil;

/**
 * @describe 收藏表
 * @author admin
 * @date 2019-08-13
 */
@RestController
@RequestMapping("/system/userCollect")
public class UserCollectController extends BaseController<UserCollect>{

	/**收藏表**/
	@Autowired
	private UserCollectService userCollectService;

	/**根据条件查询所有**/
	@GetMapping("/selectAll")
	public RespBean<Map<String, Object>> selectAll(UserCollect bean) { return userCollectService.selectAll(bean); }

	/**
	 * 通过id删除
	 * @非空参数：收藏表id
	 */
	@PostMapping("/deleteById")
	public RespBean<Map<String, Object>> deleteById(String id) { 
		if (GuoStringUtil.isEmpty(id)) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(userCollectService.deleteById(id), userCollectService);
	}

	/**
	 * 通过id查询
	 * @非空参数：收藏表id
	 */
	@GetMapping("/selectById")
	public RespBean<Map<String, Object>> selectById(UserCollect bean) { 
		if (GuoStringUtil.isEmpty(bean.getId())) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return userCollectService.selectById(bean); 
	}

	/**
	 * 通过ids集合删除
	 * @非空参数：收藏表ids
	 */
	@PostMapping("/deleteByIds")
	public RespBean<Map<String, Object>> deleteByIds(String ids) { 
		if (GuoStringUtil.isEmpty(ids)) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(userCollectService.deleteByIds(ids), userCollectService); 
	}

	/**
	 * 添加
	 * @非空参数：收藏表id
	 */
	@PostMapping("/insert")
	public RespBean<Map<String, Object>> insert(UserCollect bean) {
		if(GuoStringUtil.isEmpty(new String[] { bean.getGoodsId(), bean.getUserId() })){
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		bean.setId(SnowflakeIdWorkerUtil.SIWU.nextId());
		return returnIntercept(userCollectService.insert(bean), userCollectService);
	}

	/**
	 * 通过id修改
	 * @非空参数：收藏表id
	 */
	@PostMapping("/updateById")
	public RespBean<Map<String, Object>> updateById(UserCollect bean) {
		if(GuoStringUtil.isEmpty(new String[] { bean.getId(), bean.getGoodsId(), bean.getUserId() })){
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(userCollectService.updateById(bean), userCollectService);
	}

	/**LayUI根据条件查询所有**/
	@GetMapping("/selectAllForLayUI")
	public Map<String, Object> selectAllForLayUI(UserCollect bean) { return userCollectService.selectAllForLayUI(bean); }


}
