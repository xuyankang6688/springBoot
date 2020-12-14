package top.javaguo.biz.system.dao.perRole;

import top.javaguo.biz.system.bean.PerRole;
import top.javaguo.core.biz.dao.BaseMapper;

/**
 * @describe 权限-角色
 * @author javaGuo
 * @date 2018-05-31
 */
public class PerRoleMapper extends BaseMapper<PerRole> {

	/** 查询条件 **/
	@Override
	public String commonWhere(PerRole bean) {
		return bean == null ? ""
				: " WHERE 1 = 1 " + whereAddFilter("t.id = ", bean.getId())
						+ whereAddFilter("t.create_time = ", bean.getCreateTime())
						+ whereAddFilter("t.update_time = ", bean.getUpdateTime())
						+ whereAddFilter("t.role_name = ", bean.getRoleName())
						+ whereAddFilter("t.role_introduce = ", bean.getRoleIntroduce())
						+ whereAddFilter("t.create_time >= ", bean.getQueryBeginDate())
						+ whereAddFilter("t.create_time < ", bean.getQueryEndDate());
	}

}
