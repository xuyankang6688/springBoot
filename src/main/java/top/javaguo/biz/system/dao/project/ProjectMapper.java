package top.javaguo.biz.system.dao.project;

import org.apache.ibatis.annotations.Param;
import top.javaguo.biz.system.bean.Project;
import top.javaguo.core.biz.dao.BaseMapper;

/**
 * @describe 项目
 * @author javaGuo
 * @date 2019-01-18
 */
public class ProjectMapper extends BaseMapper<Project> {

	/**查询条件**/
	public String commonWhere(Project project) {
		return project == null ? "" : " WHERE 1 = 1 "
			+ whereAddFilter("t.id = " , project.getId())
			+ whereAddFilter("t.create_time = " , project.getCreateTime())
			+ whereAddFilter("t.update_time = " , project.getUpdateTime())
			+ whereAddFilter("t.is_deleted = " , project.getIsDeleted())
            + whereAddLikeFilter(new String[] { "t.name" }, project.getName())
			+ whereAddFilter("t.create_time >= " , project.getQueryBeginDate())
			+ whereAddFilter("t.create_time < " , project.getQueryEndDate())
			;
	}

	/**所需关联查询字段**/
	private String getSelectFieldsForRelation() {
		return " t.id"
				+ " , t.create_time"
				+ " , t.update_time"
				+ " , t.is_deleted"
				+ " , t.name"
				;
	}

	/**所需关联查询表名**/
	private String getSelectedTableNameForRelation() {
		return " project t "
				;
	}

	/**根据关联条件查询所有数据**/
	public String selectAllForRelation(@Param("project") Project project) { return getSelectSql(project, "all", getSelectFieldsForRelation(), getSelectedTableNameForRelation()); }

	/**根据关联条件查询所有数量**/
	public String selectTotalForRelation(@Param("project") Project project) { return getSelectSql(project, "total", "count(*)", getSelectedTableNameForRelation()); }

	/**根据关联id查询**/
	public String selectByIdForRelation(@Param("id")String id) {
		Project project = new Project();
		project.setId(id);
		return getSelectSql(project, "all", getSelectFieldsForRelation(), getSelectedTableNameForRelation());
	}

}
