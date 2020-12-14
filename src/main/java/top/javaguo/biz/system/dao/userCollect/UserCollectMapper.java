package top.javaguo.biz.system.dao.userCollect;

import org.apache.ibatis.annotations.Param;
import top.javaguo.biz.system.bean.UserCollect;
import top.javaguo.core.biz.dao.BaseMapper;

/**
 * @describe 收藏表
 * @author admin
 * @date 2019-08-13
 */
public class UserCollectMapper extends BaseMapper<UserCollect> {

	/**查询条件**/
	public String commonWhere(UserCollect bean) {
		return bean == null ? "" : " WHERE 1 = 1 "
			+ whereAddFilter("t.id = " , bean.getId())
			+ whereAddFilter("t.create_time = " , bean.getCreateTime())
			+ whereAddFilter("t.update_time = " , bean.getUpdateTime())
			+ whereAddFilter("t.is_deleted = " , bean.getIsDeleted())
			+ whereAddFilter("t.goods_id = " , bean.getGoodsId())
			+ whereAddFilter("t.user_id = " , bean.getUserId())
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
				+ " , t.goods_id"
				+ " , t.user_id"
				;
	}

	/**所需关联查询表名**/
	private String getSelectedTableNameForRelation() {
		return " user_collect t "
				;
	}

	/**根据关联条件查询所有数据**/
	public String selectAllForRelation(@Param("bean")UserCollect bean) { return getSelectSql(bean, "all", getSelectFieldsForRelation(), getSelectedTableNameForRelation()); }

	/**根据关联条件查询所有数量**/
	public String selectTotalForRelation(@Param("bean")UserCollect bean) { return getSelectSql(bean, "total", "count(*)", getSelectedTableNameForRelation()); }

	/**根据关联id查询**/
	public String selectByIdForRelation(@Param("id")String id) {
		UserCollect bean = new UserCollect();
		bean.setId(id);
		return getSelectSql(bean, "all", getSelectFieldsForRelation(), getSelectedTableNameForRelation());
	}


}
