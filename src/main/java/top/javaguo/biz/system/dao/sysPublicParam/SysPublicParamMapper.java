package top.javaguo.biz.system.dao.sysPublicParam;

import org.apache.ibatis.annotations.Param;
import top.javaguo.biz.system.bean.SysPublicParam;
import top.javaguo.core.biz.dao.BaseMapper;

/**
 * @describe 公共参数
 * @author javaGuo
 * @date 2019-02-26
 */
public class SysPublicParamMapper extends BaseMapper<SysPublicParam> {

	/**查询条件**/
	public String commonWhere(SysPublicParam bean) {
		return bean == null ? "" : " WHERE 1 = 1 "
			+ whereAddFilter("t.id = " , bean.getId())
			+ whereAddFilter("t.create_time = " , bean.getCreateTime())
			+ whereAddFilter("t.update_time = " , bean.getUpdateTime())
			+ whereAddFilter("t.is_deleted = " , bean.getIsDeleted())
			+ whereAddLikeFilter(new String[]{"t.param_key"} , bean.getParamKey())
			+ whereAddLikeFilter(new String[]{"t.param_value"} , bean.getParamValue())
			+ whereAddLikeFilter(new String[]{"t.name"} , bean.getName())
			+ whereAddFilter("t.create_time >= " , bean.getQueryBeginDate())
			+ whereAddFilter("t.create_time < " , bean.getQueryEndDate())
			;
	}

	/**所需关联查询字段**/
	private String getSelectFieldsForRelation() {
		return " t.id"
				+ " , t.create_time"
				+ " , t.update_time"
				+ " , t.is_deleted"
				+ " , t.param_key"
				+ " , t.param_value"
				+ " , t.name"
				;
	}

	/**所需关联查询表名**/
	private String getSelectedTableNameForRelation() {
		return " sys_public_param t "
				;
	}

	/**根据关联条件查询所有数据**/
	public String selectAllForRelation(@Param("bean")SysPublicParam bean) { return getSelectSql(bean, "all", getSelectFieldsForRelation(), getSelectedTableNameForRelation()); }

	/**根据关联条件查询所有数量**/
	public String selectTotalForRelation(@Param("bean")SysPublicParam bean) { return getSelectSql(bean, "total", "count(*)", getSelectedTableNameForRelation()); }

	/**根据关联id查询**/
	public String selectByIdForRelation(@Param("id")String id) {
		SysPublicParam bean = new SysPublicParam();
		bean.setId(id);
		return getSelectSql(bean, "all", getSelectFieldsForRelation(), getSelectedTableNameForRelation());
	}


}
