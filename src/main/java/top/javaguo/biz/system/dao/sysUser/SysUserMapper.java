package top.javaguo.biz.system.dao.sysUser;

import top.javaguo.biz.system.bean.SysUser;
import top.javaguo.core.biz.dao.BaseMapper;

/**
 * @describe 用户
 * @author javaGuo
 * @date 2018-03-08
 */
public class SysUserMapper extends BaseMapper<SysUser> {

	/** 查询条件 **/
	@Override
	public String commonWhere(SysUser bean) {
		return bean == null ? ""
				: " WHERE 1 = 1 " + whereAddFilter("t.id = ", bean.getId())
						+ whereAddFilter("t.create_time = ", bean.getCreateTime())
						+ whereAddFilter("t.update_time = ", bean.getUpdateTime())
						+ whereAddLikeFilter(new String[] { "t.username" }, bean.getUsername())
						+ whereAddFilter("t.password = ", bean.getPassword())
						+ whereAddFilter("t.create_time >= ", bean.getQueryBeginDate())
						+ whereAddFilter("t.create_time < ", bean.getQueryEndDate())
						
				;
	}

}
