package top.javaguo.biz.system.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.javaguo.biz.system.bean.Goods;
import top.javaguo.biz.system.service.GoodsService;
import top.javaguo.core.biz.controller.BaseController;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoRespBeanUtil;
import top.javaguo.utils.GuoStringUtil;
import top.javaguo.utils.SnowflakeIdWorkerUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @describe 货物表
 * @author 
 * @date 2019-12-09
 */
@RestController
@RequestMapping("/system/goods")
public class GoodsController extends BaseController<Goods>{

	/**货物表**/
	@Autowired
	private GoodsService goodsService;

	/**根据条件查询所有**/
	@GetMapping("/selectAll")
	public RespBean<Map<String, Object>> selectAll(Goods bean) { return goodsService.selectAll(bean); }

	/**
	 * 通过id删除
	 * @非空参数：id
	 */
	@PostMapping("/deleteById")
	public RespBean<Map<String, Object>> deleteById(String id) { 
		if (GuoStringUtil.isEmpty(id)) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(goodsService.deleteById(id), goodsService);
	}

	/**
	 * 通过id查询
	 * @非空参数：id
	 */
	@GetMapping("/selectById")
	public RespBean<Map<String, Object>> selectById(Goods bean) { 
		if (GuoStringUtil.isEmpty(bean.getId())) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return goodsService.selectById(bean); 
	}

	/**
	 * 通过ids集合删除
	 * @非空参数：ids
	 */
	@PostMapping("/deleteByIds")
	public RespBean<Map<String, Object>> deleteByIds(String ids) { 
		if (GuoStringUtil.isEmpty(ids)) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(goodsService.deleteByIds(ids), goodsService); 
	}

	/**
	 * 添加
	 * @非空参数：id
	 */
	@PostMapping("/insert")
	public RespBean<Map<String, Object>> insert(Goods bean) {
		if(GuoStringUtil.isEmpty(new String[] { bean.getApplicant(), bean.getDepartLocal(), bean.getDestination(), bean.getLoadingTime(), bean.getGoodsName(), bean.getGoodsWeight(), bean.getWidth(), bean.getHeight(), bean.getLength(), bean.getVehicleDemand(), bean.getIsCarpooling(), bean.getIsInvoice(), bean.getGoodsImg(), bean.getUserId() })){
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		bean.setId(SnowflakeIdWorkerUtil.SIWU.nextId());
		return returnIntercept(goodsService.insert(bean), goodsService);
	}

	/**
	 * 通过id修改
	 * @非空参数：id
	 */
	@PostMapping("/updateById")
	public RespBean<Map<String, Object>> updateById(Goods bean) {
		if(GuoStringUtil.isEmpty(new String[] { bean.getId(),bean.getApplicant(), bean.getDepartLocal(), bean.getDestination(), bean.getLoadingTime(), bean.getGoodsName(), bean.getGoodsWeight(), bean.getWidth(), bean.getHeight(), bean.getLength(), bean.getVehicleDemand(), bean.getIsCarpooling(), bean.getIsInvoice(), bean.getGoodsImg(), bean.getUserId() })){
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(goodsService.updateById(bean), goodsService);
	}

	/**LayUI根据条件查询所有**/
	@GetMapping("/selectAllForLayUI")
	public Map<String, Object> selectAllForLayUI(Goods bean) { return goodsService.selectAllForLayUI(bean); }


}
