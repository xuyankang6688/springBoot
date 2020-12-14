package top.javaguo.biz.system.dao.codeAreas;

import org.apache.ibatis.annotations.Param;
import top.javaguo.biz.system.bean.CodeAreas;
import top.javaguo.core.biz.dao.BaseMapper;

/**
 * @describe 城市表
 * @author 
 * @date 2019-10-23
 */
public class CodeAreasMapper extends BaseMapper<CodeAreas> {

	/**查询条件**/
	public String commonWhere(CodeAreas bean) {
		return bean == null ? "" : " WHERE 1 = 1 "
			+ whereAddFilter("t.id = " , bean.getId())
			+ whereAddFilter("t.create_time = " , bean.getCreateTime())
			+ whereAddFilter("t.update_time = " , bean.getUpdateTime())
			+ whereAddFilter("t.is_deleted = " , bean.getIsDeleted())
			+ whereAddFilter("t.name = " , bean.getName())
			+ whereAddFilter("t.parent_id = " , bean.getParentId())
			+ whereAddFilter("t.short_name = " , bean.getShortName())
			+ whereAddFilter("t.level_type = " , bean.getLevelType())
			+ whereAddFilter("t.city_code = " , bean.getCityCode())
			+ whereAddFilter("t.zip_code = " , bean.getZipCode())
			+ whereAddFilter("t.merger_name = " , bean.getMergerName())
			+ whereAddFilter("t.lng = " , bean.getLng())
			+ whereAddFilter("t.lat = " , bean.getLat())
			+ whereAddFilter("t.pinyin = " , bean.getPinyin())
			+ whereAddFilter("t.area_name = " , bean.getAreaName())
			+ whereAddFilter("t.is_hot = " , bean.getIsHot())
			+ whereAddFilter("t.is_store = " , bean.getIsStore())
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
				+ "+ t.name"
				+ "+ t.parentId"
				+ "+ t.shortName"
				+ "+ t.levelType"
				+ "+ t.cityCode"
				+ "+ t.zipCode"
				+ "+ t.mergerName"
				+ "+ t.lng"
				+ "+ t.lat"
				+ "+ t.pinyin"
				+ "+ t.areaName"
				+ "+ t.isHot"
				+ "+ t.isStore"
				;
	}

	/**所需关联查询表名**/
	private String getSelectedTableNameForRelation() {
		return " code_areas t "
				;
	}

	/**根据关联条件查询所有数据**/
	public String selectAllForRelation(@Param("bean")CodeAreas bean) { return getSelectSql(bean, "all", getSelectFieldsForRelation(), getSelectedTableNameForRelation()); }

	/**根据关联条件查询所有数量**/
	public String selectTotalForRelation(@Param("bean")CodeAreas bean) { return getSelectSql(bean, "total", "count(*)", getSelectedTableNameForRelation()); }

	/**根据关联id查询**/
	public String selectByIdForRelation(@Param("id")String id) {
		CodeAreas bean = new CodeAreas();
		bean.setId(id);
		return getSelectSql(bean, "all", getSelectFieldsForRelation(), getSelectedTableNameForRelation());
	}
}
