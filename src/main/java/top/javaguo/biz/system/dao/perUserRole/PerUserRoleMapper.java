package top.javaguo.biz.system.dao.perUserRole;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import top.javaguo.biz.system.bean.PerUserRole;
import top.javaguo.core.biz.dao.BaseMapper;
import top.javaguo.core.util.SnowflakeIdWorkerUtil;

/**
 * @describe 权限-用户角色
 * @author javaGuo
 * @date 2018-05-31
 */
public class PerUserRoleMapper extends BaseMapper<PerUserRole> {

	/** 查询条件 **/
	@Override
	public String commonWhere(PerUserRole bean) {
		return bean == null ? ""
				: " WHERE 1 = 1 " + whereAddFilter("t.id = ", bean.getId())
						+ whereAddFilter("t.create_time = ", bean.getCreateTime())
						+ whereAddFilter("t.update_time = ", bean.getUpdateTime())
						+ whereAddFilter("t.user_id = ", bean.getUserId())
						+ whereAddFilter("t.role_id = ", bean.getRoleId())
						+ whereAddFilter("t.create_time >= ", bean.getQueryBeginDate())
						+ whereAddFilter("t.create_time < ", bean.getQueryEndDate());
	}

	/** 批量增加 **/
	public String insertBatch(@Param("list") List<PerUserRole> list) {
		String sql = "insert into per_user_role (id, create_time, update_time, user_id, role_id) values ";
		for (int i = 0; i < list.size(); i++) {
			PerUserRole bean = list.get(i);
			bean.setId(SnowflakeIdWorkerUtil.SIWU.nextId());
			sql += "('" + bean.getId() + "',  now(),  now(), '" + bean.getUserId() + "', " + "'"
					+ bean.getRoleId() + "')";
			if (i != list.size() - 1) {
				sql += ",";
			}
		}
		return sql;
	}

	/** 通过用户编号删除 **/
	public String deleteByUserId(@Param("id") String id) {
		return "delete from per_user_role where user_id =" + id;
	}

}
