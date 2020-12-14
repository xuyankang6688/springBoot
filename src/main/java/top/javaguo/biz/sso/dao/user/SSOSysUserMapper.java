package top.javaguo.biz.sso.dao.user;

import org.apache.ibatis.annotations.Param;

import top.javaguo.biz.sso.bean.SysUser;
import top.javaguo.utils.GuoStringUtil;

/**
 * @describe 用户
 * @author javaGuo
 * @date 2018-03-05
 */
public class SSOSysUserMapper {

	/** 根据账号密码查询用户信息 **/
	public String checkUser(@Param("username") String username, @Param("password") String password) {
		String sql = "select * from sys_user where 1=1 ";
		if (!GuoStringUtil.isEmpty(username)) {
			sql += " and username = '" + GuoStringUtil.replaceString(username) + "' ";
		}
		if (!GuoStringUtil.isEmpty(password)) {
			sql += " and password = '" + GuoStringUtil.replaceString(password) + "' ";
		}
		return sql;
	}

	/** 根据用户ID查询用户所拥有的权限资源 **/
	public String selectResourceByUserId(@Param("userId") Long userId) {
		return "select distinct pr.id , pr.* from per_role_resource prr , per_resource pr"
				+ " where prr.per_role_id IN (select role_id from per_user_role pur where user_id = " + userId + ")"
				+ " and  prr.per_resource_id = pr.id";
	}

	/** 通过id修改用户所在ip以及省市区 **/
	public String updateIpAndAddress(@Param("sysUser") SysUser sysUser) {
		String sql = "update sys_user set ";
		if (!GuoStringUtil.isEmpty(sysUser.getIp())) {
			sql += "ip = '" + sysUser.getIp() + "',";
		}
		if (!GuoStringUtil.isEmpty(sysUser.getAddress())) {
			sql += "address = '" + sysUser.getAddress() + "',";
		}
		sql += "update_time = now() where id = '" + sysUser.getId() + "'";
		return sql;
	}
	

}
