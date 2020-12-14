package top.javaguo.biz.system.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.javaguo.biz.system.bean.SysUser;
import top.javaguo.biz.system.service.SysUserService;
import top.javaguo.core.biz.controller.BaseController;
import top.javaguo.core.resp.RespBean;
import top.javaguo.core.util.SnowflakeIdWorkerUtil;
import top.javaguo.utils.*;

import static top.javaguo.utils.GuoLogUtil.*;

/**
 * @describe 用户
 * @author javaGuo
 * @date 2018-03-08
 */
@RestController
@RequestMapping("/system/sysUser")
public class SysUserController extends BaseController<SysUser> {

	/** 用户 **/
	@Autowired
	private SysUserService sysUserService;


	/** 根据条件查询所有 **/
	@GetMapping("/selectAll")
	public RespBean<Map<String, Object>> selectAll(SysUser sysUser) {
		return sysUserService.selectAll(sysUser);
	}

	/**
	 * 添加
	 * 
	 * @非空参数：用户名username、密码password
	 */
	@PostMapping("/insert")
	public RespBean<Map<String, Object>> insert(SysUser sysUser) {
		if (GuoStringUtil.isEmpty(new String[] { sysUser.getUsername(), sysUser.getPassword() })) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		sysUser.setId(SnowflakeIdWorkerUtil.SIWU.nextId());
		return returnIntercept(sysUserService.insert(sysUser), sysUserService);
	}

	/**
	 * 通过id删除
	 * 
	 * @非空参数：编号id
	 */
	@PostMapping("/deleteById")
	public RespBean<Map<String, Object>> deleteById(String id) {
		if (GuoStringUtil.isEmpty(id)) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(sysUserService.deleteById(id), sysUserService);
	}

	/**
	 * 通过id修改
	 * 
	 * @非空参数：编号id
	 */
	@PostMapping("/updateById")
	public RespBean<Map<String, Object>> updateById(SysUser sysUser) {
		if (GuoStringUtil.isEmpty(sysUser.getId())) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(sysUserService.updateById(sysUser), sysUserService);
	}

	/**
	 * 通过id查询
	 * 
	 * @非空参数：编号id
	 */
	@GetMapping("/selectById")
	public RespBean<Map<String, Object>> selectById(SysUser sysUser) {
		if (GuoStringUtil.isEmpty(sysUser.getId())) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return sysUserService.selectById(sysUser);
	}

	/**
	 * 通过ids集合删除
	 * 
	 * @非空参数：编号集合ids
	 */
	@PostMapping("/deleteByIds")
	public RespBean<Map<String, Object>> deleteByIds(String ids) {
		if (GuoStringUtil.isEmpty(ids)) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(sysUserService.deleteByIds(ids), sysUserService);
	}

    /**
     * LayUI根据条件查询所有
     *
     * @param sysUser
     * @return
     */
    @GetMapping("/selectAllForLayUI")
    public Object selectAllForLayUI(SysUser sysUser) {
    	sysUser.setOrderBy("id");
    	sysUser.setSortingRules("asc");
        return sysUserService.selectAllForLayUI(sysUser);
    }

	/**
	 * 修改密码
	 * 
	 * @非空参数：编号id
	 */
	@PostMapping("/updatePassword")
	public RespBean<Map<String, Object>> updatePassword(SysUser sysUser, String newPassword) {
		if (GuoStringUtil.isEmpty(new String[] { sysUser.getId(), sysUser.getPassword(), newPassword }))
			return GuoRespBeanUtil.initParamNotNullRespBean();
		return returnIntercept(sysUserService.updatePassword(sysUser, newPassword), sysUserService);
	}

	/**
	 * 修改头像
	 * 
	 * @非空参数：编号id
	 */
	@PostMapping("/updateHeadPortrait")
	public RespBean<Map<String, Object>> updateAvatar(SysUser sysUser) {
		if (GuoStringUtil.isEmpty(new String[] { sysUser.getId(), sysUser.getHeadPortrait() })) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		return returnIntercept(sysUserService.updateHeadPortrait(sysUser), sysUserService);
	}

	/** @Example: 根据条件查询所有,当被前台单独调用涉及跨域时用这样的方式解决 **/
	@GetMapping("/selectAllForCrossDomain")
	public RespBean<Map<String, Object>> selectAllForCrossDomain(HttpServletResponse response, SysUser sysUser) {
		// 设置跨域属性
		GuoCrossDomainUtil.setCrossDomain(response);
		return sysUserService.selectAll(sysUser);
	}

	/** @Example: mybatis plus 根据条件查询所有 **/
	/*
	 * @GetMapping("/selectAllForMybatisPlus") public RespBean<Map<String, Object>>
	 * selectAllForMybatisPlus(SysUser sysUser) { return
	 * sysUserService.selectAllForMybatisPlus(sysUser); }
	 */

	/** @Example: mybatis映射一对一例子 **/
	@GetMapping("/selectAllForOneToOne")
	public List<SysUser> selectAllForOneToOne() {
		return sysUserService.selectAllForOneToOne();
	}

	/**
	 * @Example: spring cache 对象中对象数据获取缓存例子
	 * 
	 *           通过id查询,同时装入部门信息
	 * 
	 * @非空参数：编号id
	 */
	@GetMapping("/selectByIdWithDepartment")
	public RespBean<Map<String, Object>> selectByIdWithDepartment(SysUser sysUser) {
		if (GuoStringUtil.isEmpty(sysUser.getId())) {
			return GuoRespBeanUtil.initParamNotNullRespBean();
		}
		RespBean<Map<String, Object>> respBean = sysUserService.selectById(sysUser);
		sysUser = (SysUser) respBean.getData().get("data");
		//sysUser.setDepartment(departmentService.selectById(sysUser.getDepartmentId()));
		return respBean;
	}

    /**
     * @Example: log使用方法
     * LayUI根据条件查询所有
     *
     * @param sysUser
     * @return
     */
    @GetMapping("/errorExample")
    public Object errorExample(SysUser sysUser) {
        info("info日志...");
        debug("debug日志...");
        warn("warn日志...");
        error("error日志...");
        return GuoRespBeanUtil.initRespBean();
    }

}
