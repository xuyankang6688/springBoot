package top.javaguo.biz.system.dao.appActive;

import org.apache.ibatis.annotations.Param;
import top.javaguo.biz.system.bean.AppActive;
import top.javaguo.core.biz.dao.BaseMapper;

/**
 * @describe 首页活动展示
 * @author 
 * @date 2019-11-25
 */
public class AppActiveMapper extends BaseMapper<AppActive> {

	/**查询条件**/
	public String commonWhere(AppActive bean) {
		return bean == null ? "" : " WHERE 1 = 1 "
			+ whereAddFilter("t.id = " , bean.getId())
			+ whereAddFilter("t.create_time = " , bean.getCreateTime())
			+ whereAddFilter("t.update_time = " , bean.getUpdateTime())
			+ whereAddFilter("t.is_deleted = " , bean.getIsDeleted())
			+ whereAddFilter("t.img = " , bean.getImg())
			+ whereAddFilter("t.type = " , bean.getType())
			+ whereAddFilter("t.param = " , bean.getParam())
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
				+ "+ t.img"
				+ "+ t.type"
				+ "+ t.param"
				;
	}

	/**所需关联查询表名**/
	private String getSelectedTableNameForRelation() {
		return " app_active t "
				;
	}

	/**根据关联条件查询所有数据**/
	public String selectAllForRelation(@Param("bean")AppActive bean) { return getSelectSql(bean, "all", getSelectFieldsForRelation(), getSelectedTableNameForRelation()); }

	/**根据关联条件查询所有数量**/
	public String selectTotalForRelation(@Param("bean")AppActive bean) { return getSelectSql(bean, "total", "count(*)", getSelectedTableNameForRelation()); }

	/**根据关联id查询**/
	public String selectByIdForRelation(@Param("id")String id) {
		AppActive bean = new AppActive();
		bean.setId(id);
		return getSelectSql(bean, "all", getSelectFieldsForRelation(), getSelectedTableNameForRelation());
	}
}
