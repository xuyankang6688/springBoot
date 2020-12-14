package top.javaguo.biz.system.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.javaguo.biz.system.bean.VehicleInformation;
import top.javaguo.biz.system.service.VehicleInformationService;
import top.javaguo.core.biz.controller.BaseController;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoRespBeanUtil;
import top.javaguo.utils.GuoStringUtil;
import top.javaguo.utils.SnowflakeIdWorkerUtil;

/**
 * @describe 车辆信息
 * @author 
 * @date 2019-12-07
 */
@RestController
@RequestMapping("/system/vehicleInformation")
public class VehicleInformationController extends BaseController<VehicleInformation>{

	/**车辆信息**/
	@Autowired
	private VehicleInformationService vehicleInformationService;

	/**根据条件查询所有**/
	@GetMapping("/selectAll")
	public RespBean<Map<String, Object>> selectAll(VehicleInformation bean) { return vehicleInformationService.selectAll(bean); }

	/**
	 * 通过id删除
	 * @非空参数：id
	 */
	@PostMapping("/deleteById")
	public RespBean<Map<String, Object>> deleteById(String id) { 
		if (GuoStringUtil.isEmpty(id)) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(vehicleInformationService.deleteById(id), vehicleInformationService);
	}

	/**
	 * 通过id查询
	 * @非空参数：id
	 */
	@GetMapping("/selectById")
	public RespBean<Map<String, Object>> selectById(VehicleInformation bean) { 
		if (GuoStringUtil.isEmpty(bean.getId())) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return vehicleInformationService.selectById(bean); 
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
		return returnIntercept(vehicleInformationService.deleteByIds(ids), vehicleInformationService); 
	}

	/**
	 * 添加
	 * @非空参数：id
	 */
	@PostMapping("/insert")
	public RespBean<Map<String, Object>> insert(VehicleInformation bean) {
		if(GuoStringUtil.isEmpty(new String[] { bean.getHeadstockBrand(), bean.getTrailerBrand(), bean.getAllowLoad(), bean.getVehicleType(), bean.getConductor(), bean.getVehicleImg(), bean.getUserId(), bean.getVehicleStatus() })){
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		bean.setId(SnowflakeIdWorkerUtil.SIWU.nextId());
		return returnIntercept(vehicleInformationService.insert(bean), vehicleInformationService);
	}

	/**
	 * 通过id修改
	 * @非空参数：id
	 */
	@PostMapping("/updateById")
	public RespBean<Map<String, Object>> updateById(VehicleInformation bean) {
		if(GuoStringUtil.isEmpty(new String[] { bean.getId(),bean.getHeadstockBrand(), bean.getTrailerBrand(), bean.getAllowLoad(), bean.getVehicleType(), bean.getConductor(), bean.getVehicleImg(), bean.getUserId(), bean.getVehicleStatus() })){
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(vehicleInformationService.updateById(bean), vehicleInformationService);
	}

	/**LayUI根据条件查询所有**/
	@GetMapping("/selectAllForLayUI")
	public Map<String, Object> selectAllForLayUI(VehicleInformation bean) { return vehicleInformationService.selectAllForLayUI(bean); }


}
