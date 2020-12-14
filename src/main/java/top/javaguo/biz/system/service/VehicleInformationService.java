package top.javaguo.biz.system.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.javaguo.biz.system.bean.VehicleInformation;
import top.javaguo.biz.system.dao.vehicleInformation.VehicleInformationDao;
import top.javaguo.core.biz.service.BaseService;
import top.javaguo.core.resp.RespBean;

/**
 * @describe 车辆信息
 * @author 
 * @date 2019-12-07
 */
@Service
public class VehicleInformationService extends BaseService<VehicleInformation>{

	/**车辆信息**/
	@Autowired
	private VehicleInformationDao vehicleInformationDao;

	/**根据条件查询所有**/
	public RespBean<Map<String, Object>> selectAll(VehicleInformation bean) { return getResult(vehicleInformationDao, "selectAll", bean); }

	/**LayUI根据条件查询所有**/
	public Map<String, Object> selectAllForLayUI(VehicleInformation bean) { return getResult(vehicleInformationDao, "selectAllForLayUI", bean).getData(); }

	/**添加**/
	public RespBean<Map<String, Object>> insert(VehicleInformation bean) { return getResult(vehicleInformationDao, "insert", bean); }

	/**通过id删除**/
	public RespBean<Map<String, Object>> deleteById(String id) { return getResult(vehicleInformationDao, "deleteById", id); }

	/**通过id修改**/
	public RespBean<Map<String, Object>> updateById(VehicleInformation bean) { return getResult(vehicleInformationDao, "updateById", bean); }

	/**通过id查询**/
	public RespBean<Map<String, Object>> selectById(VehicleInformation bean) { return getResult(vehicleInformationDao, "selectById", bean); }

	/**通过ids集合删除**/
	public RespBean<Map<String, Object>> deleteByIds(String ids) { return getResult(vehicleInformationDao, "deleteByIds", ids); }

	/** 清空多对象缓存集合 **/
	@Override
	public void clearBeanManyCache() {
	}


}

