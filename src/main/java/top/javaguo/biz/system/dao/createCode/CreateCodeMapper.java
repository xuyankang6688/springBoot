package top.javaguo.biz.system.dao.createCode;

import org.apache.ibatis.annotations.Param;
import top.javaguo.biz.system.bean.CreateCode;
import top.javaguo.core.biz.dao.BaseMapper;

/**
 * @describe 代码生成
 * @author 超级管理员
 * @date 2019-01-18
 */
public class CreateCodeMapper extends BaseMapper<CreateCode> {

	/**查询条件**/
	public String commonWhere(CreateCode createCode) {
		return createCode == null ? "" : " WHERE 1 = 1 "
			+ whereAddFilter("t.id = " , createCode.getId())
			+ whereAddFilter("t.create_time = " , createCode.getCreateTime())
			+ whereAddFilter("t.update_time = " , createCode.getUpdateTime())
			+ whereAddFilter("t.is_deleted = " , createCode.getIsDeleted())
			+ whereAddFilter("t.author = " , createCode.getAuthor())
			+ whereAddFilter("t.describes = " , createCode.getDescribes())
			+ whereAddFilter("t.table_name = " , createCode.getTableName())
			+ whereAddFilter("t.class_name = " , createCode.getClassName())
			+ whereAddFilter("t.class_name_lower = " , createCode.getClassNameLower())
			+ whereAddFilter("t.key_str = " , createCode.getKeyStr())
			+ whereAddFilter("t.table_key_str = " , createCode.getTableKeyStr())
			+ whereAddFilter("t.value_str = " , createCode.getValueStr())
			+ whereAddFilter("t.user_id = " , createCode.getUserId())
			+ whereAddFilter("t.project_id = " , createCode.getProjectId())
			+ whereAddFilter("t.table_key_type = " , createCode.getTableKeyType())
			+ whereAddFilter("t.table_key_length = " , createCode.getTableKeyLength())
			+ whereAddFilter("t.create_time >= " , createCode.getQueryBeginDate())
			+ whereAddFilter("t.create_time < " , createCode.getQueryEndDate())
			;
	}

	/**所需关联查询字段**/
	private String getSelectFieldsForRelation() {
		return " t.id"
				+ " , t.create_time"
				+ " , t.update_time"
				+ " , t.is_deleted"
				+ " , t.author"
				+ " , t.describes"
				+ " , t.table_name"
				+ " , t.class_name"
				+ " , t.class_name_lower"
				+ " , t.key_str"
				+ " , t.table_key_str"
				+ " , t.value_str"
				+ " , t.user_id"
				+ " , t.project_id"
				+ " , t.table_key_type"
				+ " , t.table_key_length"
				;
	}

	/**所需关联查询表名**/
	private String getSelectedTableNameForRelation() {
		return " create_code t "
				;
	}

	/**根据关联条件查询所有数据**/
	public String selectAllForRelation(@Param("createCode") CreateCode createCode) { return getSelectSql(createCode, "all", getSelectFieldsForRelation(), getSelectedTableNameForRelation()); }

	/**根据关联条件查询所有数量**/
	public String selectTotalForRelation(@Param("createCode") CreateCode createCode) { return getSelectSql(createCode, "total", "count(*)", getSelectedTableNameForRelation()); }

	/**根据关联id查询**/
	public String selectByIdForRelation(@Param("id")String id) {
		CreateCode createCode = new CreateCode();
		createCode.setId(id);
		return getSelectSql(createCode, "all", getSelectFieldsForRelation(), getSelectedTableNameForRelation());
	}


}
