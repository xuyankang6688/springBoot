package top.javaguo.biz.system.dao.goods;

import org.apache.ibatis.annotations.Param;
import top.javaguo.biz.system.bean.Goods;
import top.javaguo.core.biz.dao.BaseMapper;

/**
 * @describe 货物表
 * @author 
 * @date 2019-12-09
 */
public class GoodsMapper extends BaseMapper<Goods> {

	/**查询条件**/
	public String commonWhere(Goods bean) {
		return bean == null ? "" : " WHERE 1 = 1 "
			+ whereAddFilter("t.id = " , bean.getId())
			+ whereAddFilter("t.create_time = " , bean.getCreateTime())
			+ whereAddFilter("t.update_time = " , bean.getUpdateTime())
			+ whereAddFilter("t.is_deleted = " , bean.getIsDeleted())
			+ whereAddFilter("t.applicant = " , bean.getApplicant())
			+ whereAddFilter("t.depart_local = " , bean.getDepartLocal())
			+ whereAddFilter("t.destination = " , bean.getDestination())
			+ whereAddFilter("t.loading_time = " , bean.getLoadingTime())
			+ whereAddFilter("t.goods_name = " , bean.getGoodsName())
			+ whereAddFilter("t.goods_weight = " , bean.getGoodsWeight())
			+ whereAddFilter("t.width = " , bean.getWidth())
			+ whereAddFilter("t.height = " , bean.getHeight())
			+ whereAddFilter("t.length = " , bean.getLength())
				+ whereAddFilter("t.phone = " , bean.getPhone())
				+ whereAddFilter("t.goods_status = " , bean.getGoodsStatus())
			+ whereAddFilter("t.vehicle_demand = " , bean.getVehicleDemand())
			+ whereAddFilter("t.is_carpooling = " , bean.getIsCarpooling())
			+ whereAddFilter("t.is_invoice = " , bean.getIsInvoice())
			+ whereAddFilter("t.goods_img = " , bean.getGoodsImg())
			+ whereAddFilter("t.user_id = " , bean.getUserId())
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
				+ "+ t.applicant"
				+ "+ t.departLocal"
				+ "+ t.destination"
				+ "+ t.loadingTime"
				+ "+ t.goodsName"
				+ "+ t.goodsWeight"
				+ "+ t.width"
				+ "+ t.height"
				+ "+ t.length"
				+ "+ t.vehicleDemand"
				+ "+ t.isCarpooling"
				+ "+ t.isInvoice"
				+ "+ t.goodsImg"
				+ "+ t.userId"
				+ "+ t.goodsStatus"
				+ "+ t.phone"
				;
	}

	/**所需关联查询表名**/
	private String getSelectedTableNameForRelation() {
		return " goods t "
				;
	}

	/**根据关联条件查询所有数据**/
	public String selectAllForRelation(@Param("bean")Goods bean) { return getSelectSql(bean, "all", getSelectFieldsForRelation(), getSelectedTableNameForRelation()); }

	/**根据关联条件查询所有数量**/
	public String selectTotalForRelation(@Param("bean")Goods bean) { return getSelectSql(bean, "total", "count(*)", getSelectedTableNameForRelation()); }

	/**根据关联id查询**/
	public String selectByIdForRelation(@Param("id")String id) {
		Goods bean = new Goods();
		bean.setId(id);
		return getSelectSql(bean, "all", getSelectFieldsForRelation(), getSelectedTableNameForRelation());
	}
}
