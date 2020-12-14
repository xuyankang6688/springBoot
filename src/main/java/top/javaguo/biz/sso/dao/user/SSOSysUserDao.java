package top.javaguo.biz.sso.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import top.javaguo.biz.sso.bean.PerResource;
import top.javaguo.biz.sso.bean.SysUser;

/**
 * @describe 用户
 * @author javaGuo
 * @date 2018-03-05
 */
@Mapper
public interface SSOSysUserDao {

	/** 根据账号或密码查询用户信息 **/
	@SelectProvider(type = SSOSysUserMapper.class, method = "checkUser")
	public SysUser checkUser(@Param("username") String username, @Param("password") String password);

	/** 根据用户ID查询用户所拥有的权限资源 **/
	@SelectProvider(type = SSOSysUserMapper.class, method = "selectResourceByUserId")
	public List<PerResource> selectResourceByUserId(@Param("userId") Long userId);

	/** 通过id修改用户所在ip以及省市区 **/
	@UpdateProvider(type = SSOSysUserMapper.class, method = "updateIpAndAddress")
	public int updateIpAndAddress(@Param("sysUser") SysUser sysUser);

}
