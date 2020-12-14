package top.javaguo.biz.system.dao.appUser;

import org.apache.ibatis.annotations.Param;
import top.javaguo.biz.system.bean.AppUser;
import top.javaguo.core.biz.dao.BaseMapper;

/**
 * @describe 用户表
 * @author admin
 * @date 2019-08-13
 */
public class AppUserMapper extends BaseMapper<AppUser> {

	/**查询条件**/
	public String commonWhere(AppUser bean) {
		return bean == null ? "" : " WHERE 1 = 1 "
			+ whereAddFilter("t.id = " , bean.getId())
			+ whereAddFilter("t.create_time = " , bean.getCreateTime())
			+ whereAddFilter("t.update_time = " , bean.getUpdateTime())
			+ whereAddFilter("t.is_deleted = " , bean.getIsDeleted())
			+ whereAddFilter("t.account_number = " , bean.getAccountNumber())
			+ whereAddFilter("t.password = " , bean.getPassword())
			+ whereAddFilter("t.name = " , bean.getName())
			+ whereAddFilter("t.phone = " , bean.getPhone())
			+ whereAddFilter("t.head = " , bean.getHead())
			+ whereAddFilter("t.nick_name = " , bean.getNickName())
			+ whereAddFilter("t.pay_password = " , bean.getPayPassword())
			+ whereAddFilter("t.sex = " , bean.getSex())
				+ whereAddFilter("t.type = " , bean.getType())
				+ whereAddFilter("t.id_card = " , bean.getIdCard())
				+ whereAddFilter("t.star_rating = " , bean.getStarRating())
				+ whereAddFilter("t.transaction_number = " , bean.getTransactionNumber())
				+ whereAddFilter("t.reverse_card_img = " , bean.getReverseCardImg())
				+ whereAddFilter("t.front_card_img = " , bean.getFrontCardImg())
				+ whereAddFilter("t.holding_card_img = " , bean.getHoldingCardImg())
				+ whereAddFilter("t.driver_card_img = " , bean.getDriverCardImg())
				+ whereAddFilter("t.last_login_time = " , bean.getLastLoginTime())
				+ whereAddFilter("t.register_time = " , bean.getRegisterTime())
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
				+ " , t.account_number"
				+ " , t.password"
				+ " , t.name"
				+ " , t.phone"
				+ " , t.head"
				+ " , t.nick_name"
				+ " , t.pay_password"
				+ " , t.sex"
				+ " , t.type"
				+ " , t.id_card"
				+ " , t.star_rating"
				+ " , t.transaction_number"
				+ " , t.reverse_card_img"
				+ " , t.front_card_img"
				+ " , t.holding_card_img"
				+ " , t.driver_card_img"
				+ " , t.last_login_time"
				+ " , t.register_time"
				;
	}

	/**所需关联查询表名**/
	private String getSelectedTableNameForRelation() {
		return " app_user t "
				;
	}

	/**根据关联条件查询所有数据**/
	public String selectAllForRelation(@Param("bean")AppUser bean) { return getSelectSql(bean, "all", getSelectFieldsForRelation(), getSelectedTableNameForRelation()); }

	/**根据关联条件查询所有数量**/
	public String selectTotalForRelation(@Param("bean")AppUser bean) { return getSelectSql(bean, "total", "count(*)", getSelectedTableNameForRelation()); }

	/**根据关联id查询**/
	public String selectByIdForRelation(@Param("id")String id) {
		AppUser bean = new AppUser();
		bean.setId(id);
		return getSelectSql(bean, "all", getSelectFieldsForRelation(), getSelectedTableNameForRelation());
	}


}
