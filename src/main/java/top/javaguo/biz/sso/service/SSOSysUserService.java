package top.javaguo.biz.sso.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.javaguo.biz.sso.bean.PerResource;
import top.javaguo.biz.sso.bean.SysUser;
import top.javaguo.biz.sso.dao.user.SSOSysUserDao;

/**
 * @describe 用户
 * @author javaGuo
 * @date 2018-03-05
 */
@Service
public class SSOSysUserService {

	/** 用户 **/
	@Autowired
	private SSOSysUserDao userDao;

	/** 根据账号或密码查询用户信息 **/
	public SysUser checkUser(String username, String password) {
		return userDao.checkUser(username, password);
	}

	/** 根据用户ID查询用户所拥有的权限资源 **/
	public List<PerResource> selectResourceByUserId(@Param("userId") Long userId) {
		return userDao.selectResourceByUserId(userId);
	}

	/** 通过id修改用户所在ip以及省市区 **/
	public int updateIpAndAddress(SysUser sysUser) {
		return userDao.updateIpAndAddress(sysUser);
	}

}
