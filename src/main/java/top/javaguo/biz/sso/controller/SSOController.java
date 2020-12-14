package top.javaguo.biz.sso.controller;

import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.javaguo.biz.sso.bean.SysUser;
import top.javaguo.biz.sso.dto.SysUserResult;
import top.javaguo.biz.sso.service.SSOSysUserService;
import top.javaguo.core.cache.redis.GuoRedisUtil;
import top.javaguo.core.resp.RespBean;
import top.javaguo.core.resp.enums.RespMsgEnum;
import top.javaguo.utils.GuoJsonUtil;
import top.javaguo.utils.GuoRespBeanUtil;
import top.javaguo.utils.GuoStringUtil;
import top.javaguo.utils.md5.GuoMD5Util;

/**
 * sso
 * 
 * @author javaGuo
 * @date 2018-03-05
 */
@RestController
@RequestMapping("/system/sso")
public class SSOController {

	@Autowired
	private SSOSysUserService sysUserService;

	@Autowired
	private GuoRedisUtil guoRedisUtil;

	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 * @非空参数 用户名username,密码password
	 */
	@PostMapping("/login")
	public RespBean<Map<String, Object>> login(HttpSession session, String username, String password, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (GuoStringUtil.isEmpty(username) || GuoStringUtil.isEmpty(password))
			return GuoRespBeanUtil.initParamNotNullRespBean();
		else {
			RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
			try {
				String token = new Date().getTime() + "";
				SysUser sysUser = sysUserService.checkUser(username, password);// 查询账号密码是否正确
				if (sysUser != null) {
					byte[] usernameChar = username.getBytes();
					byte[] letter = "javaguo0628.".getBytes();
					Random random = new Random();
					for (int i = 0; i < usernameChar.length; i++) {
						token += usernameChar[i] + letter[(random.nextInt(11) + 1)] + random.nextInt(100);
					}
					token = GuoMD5Util.GetMD5Code(token);
					response.addHeader("token",token);
					session.setAttribute("token", token);
					Cookie cookie = new Cookie("token", URLEncoder.encode(token, "utf-8"));
					cookie.setMaxAge(30*60);
					cookie.setPath("/");
					response.addCookie(cookie);
					// 封装返回给前台的数据
					SysUserResult sysUserResult = new SysUserResult(sysUser.getId(), sysUser.getUsername(), token,sysUser.getPhone(),
							sysUserService.selectResourceByUserId(Long.valueOf(sysUser.getId())));
					respBean.getData().put("info", sysUserResult);
					respBean.setCode(RespMsgEnum._0000001.getCode());
					respBean.setMsg(RespMsgEnum._0000001.getMsg());
					if (!guoRedisUtil.hasKey(token)) {
					}
                    guoRedisUtil.set(token, GuoJsonUtil.Object2Json(sysUserResult), 30*60);
                    // 根据ip获取用户所在地址并修改
					sysUser.setIp(request.getRemoteAddr());
					//sysUser.setAddress(GuoAddressUtil.getAddressByIp(request.getRemoteAddr()));
					sysUserService.updateIpAndAddress(sysUser);
				} else {
					sysUser = sysUserService.checkUser(username, null);
					if (sysUser != null) {
						respBean.setCode(RespMsgEnum._0000003.getCode());
						respBean.setMsg(RespMsgEnum._0000003.getMsg());
					} else {
						respBean.setCode(RespMsgEnum._0000002.getCode());
						respBean.setMsg(RespMsgEnum._0000002.getMsg());
					}
				}
			} catch (Exception e) {
				respBean = GuoRespBeanUtil.setErrorRespBean(respBean);
				e.printStackTrace();
			}
			return respBean;
		}
	}

	/**
	 * 验证token
	 * 
	 * @param token
	 * @return
	 * @非空参数 令牌token
	 */
	@PostMapping("/checkToken")
	public RespBean<Map<String, Object>> checkToken(String token) {
		if (GuoStringUtil.isEmpty(token))
			return GuoRespBeanUtil.initParamNotNullRespBean();
		else {
			RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
			boolean flag = false;
			if (token != null && !"".equals(token)) {
				try {
					if (guoRedisUtil.hasKey(token)) {
						flag = true;
						guoRedisUtil.expire(token, 1800);
					}
				} catch (Exception e) {
					respBean = GuoRespBeanUtil.setErrorRespBean(respBean);
				}
			}
			if (flag) {
				respBean.setCode(RespMsgEnum._0000004.getCode());
				respBean.setMsg(RespMsgEnum._0000004.getMsg());
			} else {
				respBean.setCode(RespMsgEnum._0000005.getCode());
				respBean.setMsg(RespMsgEnum._0000005.getMsg());
			}
			return respBean;
		}
	}

	/**
	 * 注销
	 * 
	 * @return
	 * @非空参数 令牌token
	 */
	@PostMapping("/logout")
	public RespBean<Map<String, Object>> logout(HttpServletRequest request) {
		String token = request.getHeader("token");
		if (GuoStringUtil.isEmpty(token))
			return GuoRespBeanUtil.initParamNotNullRespBean();
		else {
			RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>();
			boolean flag = false;
			try {
				if (guoRedisUtil.hasKey(token)) {
					flag = true;
					guoRedisUtil.del(token);
				}
			} catch (Exception e) {
				respBean = GuoRespBeanUtil.setErrorRespBean(respBean);
			}
			if (flag) {

				respBean.setCode(RespMsgEnum._0000006.getCode());
				respBean.setMsg(RespMsgEnum._0000006.getMsg());
			} else {

				respBean.setCode(RespMsgEnum._0000007.getCode());
				respBean.setMsg(RespMsgEnum._0000007.getMsg());
			}
			return respBean;
		}
	}

}
