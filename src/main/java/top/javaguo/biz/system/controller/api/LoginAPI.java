package top.javaguo.biz.system.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.javaguo.biz.system.service.AppUserService;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoRespBeanUtil;
import top.javaguo.utils.GuoStringUtil;
import top.javaguo.utils.WmqRegexUtil;

/**
 * 登录接口控制层
 * 
 */
@RestController
@RequestMapping("/api/login")
public class LoginAPI {
	
	/**用户表**/
	@Autowired
	private AppUserService appUserService;
	
	/**
	 * 手机号注册
	 * @param phone 手机号
	 * @param code 验证码
	 * @param password 密码
	 * @param type  1注册2忘记密码3更换手机号
	 * @return
	 */
	@PostMapping("/phoneReg")
	public RespBean<Map<String, Object>> phoneReg(String phone, String code, String password,String type) {
		if (GuoStringUtil.isEmpty(phone))
			return GuoRespBeanUtil.setCustomContentRespBean("手机号不能为空");
		if (GuoStringUtil.isEmpty(code))
			return GuoRespBeanUtil.setCustomContentRespBean("验证码不能为空");
		if (GuoStringUtil.isEmpty(password))
			return GuoRespBeanUtil.setCustomContentRespBean("密码不能为空");
		if (phone.length() != 11 || !WmqRegexUtil.isDigits(phone))
			return GuoRespBeanUtil.setCustomContentRespBean("手机号应为11位的纯数字");
		if (GuoStringUtil.isEmpty(type)) return GuoRespBeanUtil.setCustomContentRespBean("验证码类型不能为空");

		return appUserService.phoneReg(phone,code,password, type);
	}

	/**
	 * 发送短信验证码
	 * 
	 * @param phone 手机号
	 * @param type  1注册2忘记密码3更换手机号
	 * @return
	 */
	@PostMapping("/sendCode")
	public RespBean<Map<String, Object>> doGetSMSCode(String phone, String type) throws Exception {
		if (GuoStringUtil.isEmpty(phone))
			return GuoRespBeanUtil.setCustomContentRespBean("手机号不能为空");
		if (GuoStringUtil.isEmpty(type))
			return GuoRespBeanUtil.setCustomContentRespBean("验证码类型不能为空");

		return appUserService.doGetSMSCode(phone,type);
	}

	/**
	 * 手机号密码登陆
	 * @param phone 手机号
	 * @param password  密码
	 * @return
	 */
	@PostMapping("/phoneLogin")
	public RespBean<Map<String, Object>> phoneLogin(String phone, String password) {
		if (GuoStringUtil.isEmpty(phone))
			return GuoRespBeanUtil.setCustomContentRespBean("手机号不能为空");
		if (GuoStringUtil.isEmpty(password))
			return GuoRespBeanUtil.setCustomContentRespBean("密码不能为空");
		if (phone.length() !=11 || !WmqRegexUtil.isDigits(phone))
			return GuoRespBeanUtil.setCustomContentRespBean("手机号应为11位的纯数字");

		return appUserService.phoneLogin(phone, password);
	}
	
	/**
	 * 忘记密码
	 * @param phone 手机号
	 * @param code 验证码
	 * @param newPassword 新密码
	 * @param type  1注册2忘记密码3更换手机号
	 * @return
	 */
	@PostMapping("/forgetPassword")
	public RespBean<Map<String, Object>> forgetPassword(String phone, String code, String newPassword,String type) {
		if (GuoStringUtil.isEmpty(phone)) return GuoRespBeanUtil.setCustomContentRespBean("手机号不能为空");
		if (GuoStringUtil.isEmpty(code)) return GuoRespBeanUtil.setCustomContentRespBean("验证码不能为空");
		if (GuoStringUtil.isEmpty(newPassword)) return GuoRespBeanUtil.setCustomContentRespBean("密码不能为空");
		if (phone.length() != 11 || !WmqRegexUtil.isDigits(phone)) return GuoRespBeanUtil.setCustomContentRespBean("手机号应为11位的纯数字");
		if (GuoStringUtil.isEmpty(type)) return GuoRespBeanUtil.setCustomContentRespBean("验证码类型不能为空");

		return appUserService.forgetPassword(phone,code,newPassword,type);
	}

}
