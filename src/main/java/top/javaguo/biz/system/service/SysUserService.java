package top.javaguo.biz.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.javaguo.biz.system.bean.PerUserRole;
import top.javaguo.biz.system.bean.SysUser;
import top.javaguo.biz.system.dao.perUserRole.PerUserRoleDao;
import top.javaguo.biz.system.dao.sysUser.SysUserDao;
import top.javaguo.core.biz.service.BaseService;
import top.javaguo.core.cache.cacheNames.SpringCacheNames;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoRespBeanUtil;
import top.javaguo.utils.GuoStringUtil;
import top.javaguo.utils.md5.GuoMd5SaltToolUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @Example: redis缓存示例
 * 
 * @Cacheable 作用：
 *            使用@Cacheable标注的方法，Spring在每次执行前都会检查Cache中是否存在相同key的缓存元素，如果存在就不再执行该方法，而是直接从缓存中获取结果进行返回，否则才会执行并将返回结果存入指定的缓存中。
 * 
 * @CachePut 作用：
 *           使用@CachePut标注的方法在执行前不会去检查缓存中是否存在之前执行过的结果，而是每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中。
 * 
 * @Cacheable 和 @CachePut 配置方法:
 * 
 *            value、cacheNames 缓存的名称，在 spring 配置文件中定义，必须指定至少一个
 *            例如: @Cacheable(value=”mycache”) @Cacheable(value={”cache1”,”cache2”}
 * 
 *            key 缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合
 *            例如: @Cacheable(value=”testcache”,key=”#userName”)
 * 
 *            condition 缓存的条件，可以为空，使用 SpEL 编写，返回 true 或者 false，只有为 true 才进行缓存
 *            例如：@Cacheable(value=”testcache”,condition=”#userName.length()>2”)
 * 
 * 
 * @CacheEvict 作用：
 *             1@CacheEvict是用来标注在需要清除缓存元素的方法或类上的。当标记在一个类上时表示其中所有的方法的执行都会触发缓存的清除操作。
 *             value 缓存的名称，在 spring 配置文件中定义，必须指定至少一个 例如: @CacheEvict(value=”my
 *             cache”)
 * 
 *             配置方法：
 * 
 *             key 缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合
 *             例如: @CacheEvict(value=”testcache”,key=”#userName”)
 * 
 *             condition 缓存的条件，可以为空，使用 SpEL 编写，返回 true 或者 false，只有为 true 才进行缓存
 *             例如: @CacheEvict(value=”testcache”,condition=”#userName.length()>2”)
 * 
 *             allEntries 是否清空所有缓存内容，缺省为 false，如果指定为 true，则方法调用后将立即清空所有缓存
 *             例如: @CachEvict(value=”testcache”,allEntries=true)
 * 
 *             beforeInvocation 是否在方法执行前就清空，缺省为 false，如果指定为
 *             true，则在方法还没有执行的时候就清空缓存，缺省情况下，如果方法执行抛出异常，则不会清空缓存
 *             例如: @CachEvict(value=”testcache”，beforeInvocation=true)
 */
/**
 * @describe 用户
 * @author javaGuo
 * @date 2018-03-08
 */
@Service
@CacheConfig(cacheNames = SpringCacheNames.SYSUSERONE) // 此注解下所有spring cache 注解中 cacheNames默认属性
public class SysUserService extends BaseService<SysUser> {

	/** 用户 **/
	@Autowired
	private SysUserDao sysUserDao;

	/** 权限-用户角色 **/
	@Autowired
	private PerUserRoleDao perUserRoleDao;

	/** 根据条件查询所有 **/
	@Cacheable(cacheNames = SpringCacheNames.SYSUSERMANY, key = "#sysUser")
	public RespBean<Map<String, Object>> selectAll(SysUser sysUser) {
		return getResult(sysUserDao, "selectAll", sysUser);
	}

	/** 添加 **/
	@CachePut(key = "#sysUser.id")
	public RespBean<Map<String, Object>> insert(SysUser sysUser) {
		RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
		try {
			int result = sysUserDao.insert(sysUser);
			// 创建用户时，同时绑定角色值
			if (result > 0 && sysUser.getRoleIds() != null) {
				String roleIds[] = sysUser.getRoleIds().split(",");
				if (roleIds != null && roleIds.length > 0) {
					List<PerUserRole> perUserRoleList = new ArrayList<PerUserRole>();
					for (String roleId : roleIds) {
						PerUserRole perUserRole = new PerUserRole();
						perUserRole.setRoleId(roleId);
						perUserRole.setUserId(sysUser.getId());
						perUserRoleList.add(perUserRole);
					}
					result = perUserRoleDao.insertBatch(perUserRoleList);
				}
			}
			if (result > 0) {
				respBean = GuoRespBeanUtil.setSuccessRespBean(respBean);
				respBean.getData().put("data", sysUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
			respBean = GuoRespBeanUtil.setErrorRespBean(respBean);
		}
		return respBean;
	}

	/** 通过id删除 **/
	@CacheEvict(key = "#id")
	public RespBean<Map<String, Object>> deleteById(String id) {
		RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
		PerUserRole perUserRole = new PerUserRole();
		perUserRole.setLimit(9999);
		perUserRole.setUserId(id + "");
		int result = 0;
		boolean flag = false;
		try {
			// 获取当前删除用户绑定过的角色对象
			List<PerUserRole> perUserRoleList = perUserRoleDao.selectAll(perUserRole);
			if (perUserRoleList.size() > 0) {
				String ids = "";
				for (int i = 0; i < perUserRoleList.size(); i++) {
					ids += perUserRoleList.get(i).getId();
					if (i != perUserRoleList.size() - 1)
						ids += ",";
				}
				perUserRoleDao.deleteByIds(ids);
				flag = true;
			}
			// flag表示删除成功，perUserRoleList.size() < 0表示当前用户未绑定其他角色
			if (perUserRoleList.size() <= 0 || flag)
				result = sysUserDao.deleteById(id);
			if (result > 0) {
				respBean = GuoRespBeanUtil.setSuccessRespBean(respBean);
			}
			respBean.getData().put("result", result);
		} catch (Exception e) {
			e.printStackTrace();
			respBean = GuoRespBeanUtil.setErrorRespBean(respBean);
		}
		return respBean;
	}

	/** 通过id修改 **/
	@CachePut(key = "#sysUser.id")
	public RespBean<Map<String, Object>> updateById(SysUser sysUser) {
		RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
		if (sysUser.getId() != null) {
			try {
				if (!GuoStringUtil.isEmpty(sysUser.getPassword())) {
					//sysUser.setPassword(GuoMd5SaltToolUtil.generate(sysUser.getPassword(), sysUser.getUsername()));
				} else {
					sysUser.setPassword(null);
				}
				int result = sysUserDao.updateById(sysUser);
				// 编辑用户时，同时删除之前绑定的角色值在新增添加的角色值
				perUserRoleDao.deleteByUserId(sysUser.getId());
				if (sysUser.getRoleIds() != null) {
					String roleIds[] = sysUser.getRoleIds().split(",");
					if (roleIds != null && roleIds.length > 0) {
						List<PerUserRole> perUserRoleList = new ArrayList<PerUserRole>();
						for (String roleId : roleIds) {
							if(roleId.length() <= 0) {
								continue;
							}
							PerUserRole perUserRole = new PerUserRole();
							perUserRole.setRoleId(roleId);
							perUserRole.setUserId(sysUser.getId());
							perUserRoleList.add(perUserRole);
						}
						result = perUserRoleDao.insertBatch(perUserRoleList);
					}
				}
				if (result > 0) {
					respBean = GuoRespBeanUtil.setSuccessRespBean(respBean);
					respBean.getData().put("data", sysUser);
				}
			} catch (Exception e) {
				e.printStackTrace();
				respBean = GuoRespBeanUtil.setErrorRespBean(respBean);
			}
		}
		return respBean;
	}

	/** 通过id查询 **/
	@Cacheable(key = "#sysUser.id")
	public RespBean<Map<String, Object>> selectById(SysUser sysUser) {
		return getResult(sysUserDao, "selectById", sysUser);
	}

	/** 通过ids集合删除 **/
	@CacheEvict(allEntries = true)
	public RespBean<Map<String, Object>> deleteByIds(String ids) {
		return getResult(sysUserDao, "deleteByIds", ids);
	}

	/** LayUI根据条件查询所有 **/
	@Cacheable(cacheNames = SpringCacheNames.SYSUSERMANY, key = "#sysUser")
	public Map<String, Object> selectAllForLayUI(SysUser sysUser) {
		System.out.println("phone==========:"+sysUser.getPhone());
		return getResult(sysUserDao, "selectAllForLayUI", sysUser).getData();
	}

	/** 修改密码 **/
	@CacheEvict(key = "#sysUser.id")
	public RespBean<Map<String, Object>> updatePassword(SysUser sysUser, String newPassword) {
		RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
		try {
//			sysUser.setPassword(GuoMd5SaltToolUtil.generate(sysUser.getPassword(), sysUser.getUsername()));
			List<SysUser> userListTemp = sysUserDao.selectAll(sysUser);
			int result = 0;
			if (userListTemp.size() > 0) {
//				sysUser.setPassword(GuoMd5SaltToolUtil.generate(newPassword, userListTemp.get(0).getUsername()));
				sysUser.setPassword(newPassword);
				result = sysUserDao.updateById(sysUser);
			}else {
				return GuoRespBeanUtil.setCustomContentRespBean("当前密码输入错误");
			}
			if (result > 0) {
				respBean = GuoRespBeanUtil.setSuccessRespBean(respBean);
			}else {
				respBean = GuoRespBeanUtil.setCustomContentRespBean("修改密码失败");
			}
			respBean.getData().put("data", sysUser);
		} catch (Exception e) {
			e.printStackTrace();
			respBean = GuoRespBeanUtil.setErrorRespBean(respBean);
		}
		return respBean;
	}

	/** 修改头像 **/
	@CacheEvict(key = "#sysUser.id")
	public RespBean<Map<String, Object>> updateHeadPortrait(SysUser sysUser) {
		RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
		SysUser sysUserTemp = new SysUser();
		sysUserTemp.setId(sysUser.getId());
		sysUserTemp.setHeadPortrait(sysUser.getHeadPortrait());
		int result = sysUserDao.updateById(sysUserTemp);
		if (result == 0) {
			respBean = GuoRespBeanUtil.setFailRespBean(respBean);
		}
		respBean.getData().put("data", sysUser);
		return respBean;
	}

	/** @Example: mybatis映射一对一例子 **/
	public List<SysUser> selectAllForOneToOne() {
		return sysUserDao.selectAllForOneToOne();
	}

	/** 清空多对象缓存集合 **/
	@CacheEvict(cacheNames = SpringCacheNames.SYSUSERMANY, allEntries = true)
	@Override
	public void clearBeanManyCache() {
	}

}
