package top.javaguo.biz.system.dao.vehicleInformation;

import org.apache.ibatis.annotations.Param;
import top.javaguo.biz.system.bean.VehicleInformation;
import top.javaguo.core.biz.dao.BaseMapper;

/**
 * @describe 车辆信息
 * @author 
 * @date 2019-12-07
 */
public class VehicleInformationMapper extends BaseMapper<VehicleInformation> {

	/**查询条件**/
	public String commonWhere(VehicleInformation bean) {
		return bean == null ? "" : " WHERE 1 = 1 "
			+ whereAddFilter("t.id = " , bean.getId())
			+ whereAddFilter("t.create_time = " , bean.getCreateTime())
			+ whereAddFilter("t.update_time = " , bean.getUpdateTime())
			+ whereAddFilter("t.is_deleted = " , bean.getIsDeleted())
			+ whereAddFilter("t.headstock_brand = " , bean.getHeadstockBrand())
			+ whereAddFilter("t.trailer_brand = " , bean.getTrailerBrand())
			+ whereAddFilter("t.allow_load = " , bean.getAllowLoad())
			+ whereAddFilter("t.vehicle_type = " , bean.getVehicleType())
			+ whereAddFilter("t.conductor = " , bean.getConductor())
			+ whereAddFilter("t.vehicle_img = " , bean.getVehicleImg())
			+ whereAddFilter("t.user_id = " , bean.getUserId())
			+ whereAddFilter("t.vehicle_status = " , bean.getVehicleStatus())
			+ whereAddFilter("t.create_time >= " , bean.getQueryBeginDate())
			+ whereAddFilter("t.create_time < " , bean.getQueryEndDate())
			;
	}

	/**所需关联查询字段**/
	private String getSelectFieldsForRelation() {
		return "+ t.id"
				+ "+ t.createTime"
				+ "+ t.updateTime"
				+ "+ t.isDeleted"
				+ "+ t.headstockBrand"
				+ "+ t.trailerBrand"
				+ "+ t.allowLoad"
				+ "+ t.vehicleType"
				+ "+ t.conductor"
				+ "+ t.vehicleImg"
				+ "+ t.userId"
				+ "+ t.vehicleStatus"
				;
	}

	/**所需关联查询表名**/
	private String getSelectedTableNameForRelation() {
		return " vehicle_information t "
				;
	}

	/**根据关联条件查询所有数据**/
	public String selectAllForRelation(@Param("bean")VehicleInformation bean) { return getSelectSql(bean, "all", getSelectFieldsForRelation(), getSelectedTableNameForRelation()); }

	/**根据关联条件查询所有数量**/
	public String selectTotalForRelation(@Param("bean")VehicleInformation bean) { return getSelectSql(bean, "total", "count(*)", getSelectedTableNameForRelation()); }

	/**根据关联id查询**/
	public String selectByIdForRelation(@Param("id")String id) {
		VehicleInformation bean = new VehicleInformation();
		bean.setId(id);
		return getSelectSql(bean, "all", getSelectFieldsForRelation(), getSelectedTableNameForRelation());
	}
}
