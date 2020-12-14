package top.javaguo.biz.system.dao.companyUser;

import org.apache.ibatis.annotations.Param;
import top.javaguo.biz.system.bean.CompanyUser;
import top.javaguo.core.biz.dao.BaseMapper;

/**
 * @describe 车主或者货主公司表
 * @author 
 * @date 2019-12-07
 */
public class CompanyUserMapper extends BaseMapper<CompanyUser> {

	/**查询条件**/
	public String commonWhere(CompanyUser bean) {
		return bean == null ? "" : " WHERE 1 = 1 "
			+ whereAddFilter("t.id = " , bean.getId())
			+ whereAddFilter("t.create_time = " , bean.getCreateTime())
			+ whereAddFilter("t.update_time = " , bean.getUpdateTime())
			+ whereAddFilter("t.is_deleted = " , bean.getIsDeleted())
			+ whereAddFilter("t.company_name = " , bean.getCompanyName())
			+ whereAddFilter("t.company_address = " , bean.getCompanyAddress())
			+ whereAddFilter("t.company_phone = " , bean.getCompanyPhone())
			+ whereAddFilter("t.companys_img = " , bean.getCompanysImg())
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
				+ "+ t.companyName"
				+ "+ t.companyAddress"
				+ "+ t.companyPhone"
				+ "+ t.companysImg"
				+ "+ t.userId"
				;
	}

	/**所需关联查询表名**/
	private String getSelectedTableNameForRelation() {
		return " company_user t "
				;
	}

	/**根据关联条件查询所有数据**/
	public String selectAllForRelation(@Param("bean")CompanyUser bean) { return getSelectSql(bean, "all", getSelectFieldsForRelation(), getSelectedTableNameForRelation()); }

	/**根据关联条件查询所有数量**/
	public String selectTotalForRelation(@Param("bean")CompanyUser bean) { return getSelectSql(bean, "total", "count(*)", getSelectedTableNameForRelation()); }

	/**根据关联id查询**/
	public String selectByIdForRelation(@Param("id")String id) {
		CompanyUser bean = new CompanyUser();
		bean.setId(id);
		return getSelectSql(bean, "all", getSelectFieldsForRelation(), getSelectedTableNameForRelation());
	}
}
