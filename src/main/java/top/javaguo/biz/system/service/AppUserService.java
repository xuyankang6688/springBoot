package top.javaguo.biz.system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;
import top.javaguo.biz.system.bean.AppUser;
import top.javaguo.biz.system.bean.CompanyUser;
import top.javaguo.biz.system.bean.UserCollect;
import top.javaguo.biz.system.bean.VehicleInformation;
import top.javaguo.biz.system.dao.appUser.AppUserDao;
import top.javaguo.biz.system.dao.companyUser.CompanyUserDao;

import top.javaguo.biz.system.dao.userCollect.UserCollectDao;
import top.javaguo.biz.system.dao.vehicleInformation.VehicleInformationDao;
import top.javaguo.core.biz.service.BaseService;
import top.javaguo.core.cache.redis.GuoRedisUtil;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoJsonUtil;
import top.javaguo.utils.GuoRespBeanUtil;
import top.javaguo.utils.GuoStringUtil;
import top.javaguo.utils.SmsUtil;
import top.javaguo.utils.SnowflakeIdWorkerUtil;
import top.javaguo.utils.md5.GuoMD5Util;
import top.javaguo.utils.md5.GuoMd5SaltToolUtil;

/**
 * @describe 用户表
 * @author admin
 * @date 2019-08-13
 */
@Service
public class AppUserService extends BaseService<AppUser>{

	/**用户表**/
	@Autowired
	private AppUserDao appUserDao;
	/** redis工具类 **/
	@Autowired
	private GuoRedisUtil guoRedisUtil;
	/**收藏表**/
	@Autowired
	private UserCollectDao userCollectDao;

	/**公司表**/
	@Autowired
	private CompanyUserDao companyUserDao;

	/*车辆信息表*/
	@Autowired
	private VehicleInformationDao vehicleInformationDao;

	/**根据条件查询所有**/
	public RespBean<Map<String, Object>> selectAll(AppUser bean) {

		return getResult(appUserDao, "selectAll", bean); }

	/**LayUI根据条件查询所有**/
	public Map<String, Object> selectAllForLayUI(AppUser bean) {
		RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
		Map<String, Object> data = respBean.getData();
		List<AppUser> appUserList = appUserDao.selectAll(bean);
		for(AppUser appUser:appUserList){
			CompanyUser companyUser = new CompanyUser();
			companyUser.setUserId(appUser.getId());
			System.out.println("companyUser=========:"+companyUser);
			List<CompanyUser> companyUsers=companyUserDao.selectAll(companyUser);
			if(companyUsers.size()>0){
				appUser.setCompanyUser(companyUsers.get(0));
			}
			VehicleInformation vehicleInformation = new VehicleInformation();
			vehicleInformation.setUserId(appUser.getId());
			System.out.println("vehicleInformation=========:"+vehicleInformation);
			List<VehicleInformation> vehicleInformations = vehicleInformationDao.selectAll(vehicleInformation);
			if(vehicleInformations.size()>0){
				appUser.setVehicleInformationList(vehicleInformations);
			}
		}
		data.put("data",appUserList);
		data.put("code", "0");
		data.put("msg", "执行成功");
		data.put("count", appUserList.size());
		return data;

	}

	/**添加**/
	public RespBean<Map<String, Object>> insert(AppUser bean) { return getResult(appUserDao, "insert", bean); }

	/**通过id删除**/
	public RespBean<Map<String, Object>> deleteById(String id) { return getResult(appUserDao, "deleteById", id); }

	/**通过id修改**/
	public RespBean<Map<String, Object>> updateById(AppUser bean) { return getResult(appUserDao, "updateById", bean); }

	/**通过id查询**/
	public RespBean<Map<String, Object>> selectById(AppUser bean) { return getResult(appUserDao, "selectById", bean); }

	/**通过ids集合删除**/
	public RespBean<Map<String, Object>> deleteByIds(String ids) { return getResult(appUserDao, "deleteByIds", ids); }

	/** 清空多对象缓存集合 **/
	@Override
	public void clearBeanManyCache() {
	}

	/** 发送短信验证码api **/
	public RespBean<Map<String, Object>> doGetSMSCode(String phone,String type) throws Exception {
		RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
		//定义验证码
		int code = (int) ((Math.random() * 9 + 1) * 100000);
		JSONObject jsonObject = new JSONObject();
		System.out.println(code);
		jsonObject.put("code", code);
		String typeName = "";
	    String template = "";
	    if("1".equals(type)) {
	    	typeName = "注册";
	    	template = "SMS_175350070";
	    }
	    if("2".equals(type)) {
	    	typeName = "忘记密码";
	    	template = "SMS_175350070";
	    }
	    if("3".equals(type)) {
	    	typeName = "更换手机号";
	    	//template = "SMS_160545243";
			template = "SMS_175350070";
	    }
	    //设置键值存入缓存
	    String key = phone+typeName;
	    System.out.println(key);
	    if(!guoRedisUtil.hasKey(key)){//未发送过验证码
	    	//存入缓存
		    guoRedisUtil.set(key , System.currentTimeMillis()+","+code , 300);
		    SmsUtil.sendSms(phone, template, jsonObject.toString());//发送验证码
	    }else {
		    String value = guoRedisUtil.get(key)+"";
		    String[] arr = value.split(",");
		    if(System.currentTimeMillis() - Long.valueOf(arr[0]) >60000) {//如果已发送过超过一分钟，可以再次发送
			    guoRedisUtil.del(key);//删除键值
			    //存入缓存并发送验证码
			    guoRedisUtil.set(key , System.currentTimeMillis()+","+code, 300);
			    SmsUtil.sendSms(phone, template, jsonObject.toString());
		    }else {
			    return GuoRespBeanUtil.setCustomContentRespBean("发送失败,请稍后再试");
		    }
	    }
/*		SmsUtil.sendSms(phone, "SMS_160545243", jsonObject.toString());
		phone = phone + "_YZM";
		// 存入验证码
		guoRedisUtil.set(phone, random, 300);*/

		return GuoRespBeanUtil.setSuccessRespBean(respBean);
	}
	
	/**
	  * 验证验证码是否正确，
	  * 发送验证码的手机号或邮箱
	  * @param code  验证码
	  * @param type  1注册2忘记密码3更换手机号
	  * @return  null 正确，其他  错误原因
	  */
	 private String checkCode(String phone,String code,String type) {
		 try {
			 String typeName = "";
		   if("1".equals(type)) typeName = "注册";
		   if("2".equals(type)) typeName = "忘记密码";
		   if("3".equals(type)) typeName = "更换手机号";
		   //验证验证码是否正确
		   String token = phone+typeName;
		   String value = guoRedisUtil.get(token)+"";
		   if(GuoStringUtil.isEmpty(value)) {
			   return "未发送验证码或验证码已失效";
		   }
		   String[] arr = value.split(",");
		   if(!arr[1].equals(code)) {//验证码错误
			   return "验证码错误";
		   }
		 }catch(Exception e){
			 e.printStackTrace();
			 return "验证码错误";
		 }
		 
		 return null;
	 }
	
	/** 手机号注册api **/
	public RespBean<Map<String, Object>> phoneReg(String phone, String code, String password,String type) {
		RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
		Map<String, Object> map = new HashMap<>();
		//验证验证码是否正确
		String checkCode = checkCode(phone, code, type);
		if(checkCode != null) return GuoRespBeanUtil.setCustomContentRespBean(checkCode);
//		if (!random.equals(guoRedisUtil.get(phone + "_YZM") + ""))
//			return GuoRespBeanUtil.setCustomContentRespBean("验证码错误");
		//设置用户信息
		AppUser bean = new AppUser();
		bean.setPhone(phone);
		//查询手机号是否已存在
		List<AppUser> list = appUserDao.selectAll(bean);
		if(list.size() > 0) return GuoRespBeanUtil.setCustomContentRespBean("该手机号已存在！");
		bean.setId(SnowflakeIdWorkerUtil.SIWU.nextId());
		bean.setHead("https://cshongbei.oss-cn-hangzhou.aliyuncs.com/20191111/9afd583d-2042-4100-8afe-f17f5504168c");
		bean.setName(SnowflakeIdWorkerUtil.SIWU.nextId());
		bean.setNickName(SnowflakeIdWorkerUtil.SIWU.nextId());
		//MD5加密
		password = GuoMd5SaltToolUtil.generate(password,"login");
		System.out.println(password);
		bean.setPassword(password);
		bean.setIsDeleted("0");
		int add = appUserDao.insert(bean);
		if(add < 1) return GuoRespBeanUtil.setErrorRespBean(respBean);
		
		respBean.setData(map);
		respBean = GuoRespBeanUtil.setSuccessRespBean(respBean);
		return respBean;
	}

	/** 手机号密码登陆api **/
	public RespBean<Map<String, Object>> phoneLogin(String phone, String password) {
		RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
		Map<String, Object> map = new HashMap<>();
		try {
			//MD5加密
			password = GuoMd5SaltToolUtil.generate(password,"login");
			System.out.println(password);
			AppUser bean = new AppUser();
			bean.setPhone(phone);
			bean.setPassword(password);
			List<AppUser> list = appUserDao.selectAll(bean);
			if(list.size() < 1) return GuoRespBeanUtil.setCustomContentRespBean("手机号或密码错误！");
			//生成token
			String token = new Date().getTime() + "";
			byte[] usernameChar = phone.getBytes();
			byte[] letter = "javaguo0628.".getBytes();
			Random random = new Random();
			for (int i = 0; i < usernameChar.length; i++) {
				token += usernameChar[i] + letter[(random.nextInt(11) + 1)] + random.nextInt(100);
			}
			token = GuoMD5Util.GetMD5Code(token);
			// 存入token
			guoRedisUtil.set(token, GuoJsonUtil.Object2Json(list), 0);
	
			map.put("id", list.get(0).getId());
//			map.put("accountNumber", list.get(0).getAccountNumber());
			if(GuoStringUtil.isEmpty(bean.getAccountNumber())) {
				map.put("isWx", "未绑定");
			}else {
				map.put("isWx", "已绑定");
			}
			//查询用户商品收藏数
			UserCollect collect = new UserCollect();
			collect.setUserId(list.get(0).getId());
			int total = userCollectDao.selectTotal(collect);
			map.put("goodsLikeNum", total);
				map.put("name", list.get(0).getName());
			map.put("phone", list.get(0).getPhone());
				map.put("head", list.get(0).getHead());
				map.put("nickName", list.get(0).getNickName());
			map.put("identity", list.get(0).getIdentity());
			map.put("synopsis", list.get(0).getSynopsis());
			map.put("myResume", list.get(0).getMyResume());
			map.put("sex", list.get(0).getSex());
			map.put("token", token);
			respBean.setData(map);
			respBean = GuoRespBeanUtil.setSuccessRespBean(respBean);
		} catch (Exception e) {
			respBean = GuoRespBeanUtil.setErrorRespBean(respBean);
			e.printStackTrace();
		}
		return respBean;
	}

	/** 忘记密码api **/
	public RespBean<Map<String, Object>> forgetPassword(String phone, String code, String newPassword, String type) {
		RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
		Map<String, Object> map = new HashMap<>();
		//验证验证码是否正确
		String checkCode = checkCode(phone, code, type);
		if(checkCode != null) return GuoRespBeanUtil.setCustomContentRespBean(checkCode);
		//根据手机号查询信息
		AppUser bean = new AppUser();
		bean.setPhone(phone);
		List<AppUser> list = appUserDao.selectAll(bean);
		if(list.size() < 1) return GuoRespBeanUtil.setCustomContentRespBean("用户信息异常");
		String id = list.get(0).getId();
		//MD5加密
		newPassword = GuoMd5SaltToolUtil.generate(newPassword,"login");
		System.out.println(newPassword);
		bean.setId(id);
		bean.setPassword(newPassword);
		//修改用户信息
		int update = appUserDao.updateById(bean);
		if(update < 1) return GuoRespBeanUtil.setCustomContentRespBean("修改密码失败");
		
		respBean.setData(map);
		respBean = GuoRespBeanUtil.setSuccessRespBean(respBean);
		return respBean;
	}

	/**根据用户id查询用户信息api**/
	public RespBean<Map<String, Object>> selUserInfoById(AppUser bean) {
		RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
		Map<String, Object> map = respBean.getData();
		bean = appUserDao.selectById(bean);
		//查询用户商品收藏数
		UserCollect collect = new UserCollect();
		collect.setUserId(bean.getId());
		collect.setIsDeleted("0");
		int total = userCollectDao.selectTotal(collect);
		bean.setGoodsLikeNum(total+"");

		map.put("id", bean.getId());
//		map.put("accountNumber", bean.getAccountNumber());
		if(GuoStringUtil.isEmpty(bean.getAccountNumber())) {
			map.put("isWx", "未绑定");
		}else {
			map.put("isWx", "已绑定");
		}
		map.put("name", bean.getName());
		map.put("phone", bean.getPhone());
		map.put("head", bean.getHead());
		map.put("nickName", bean.getNickName());
//		map.put("identity", bean.getIdentity());
		map.put("synopsis", bean.getSynopsis());
		map.put("myResume", bean.getMyResume());
		map.put("sex", bean.getSex());
		map.put("goodsLikeNum", bean.getGoodsLikeNum());
		return GuoRespBeanUtil.setSuccessRespBean(respBean);
	}

	/**查询我的收藏信息api**/
	public RespBean<Map<String, Object>> selMyCollect(String userId) {
		RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();


		return GuoRespBeanUtil.setSuccessRespBean(respBean);
	}

	/**修改新手机号api**/
	public RespBean<Map<String, Object>> updateMyNewPhone(String userId, String phone, String code, String type) {
		RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
		//验证验证码是否正确、
		String checkCode = checkCode(phone, code, type);
		if(checkCode != null) return GuoRespBeanUtil.setCustomContentRespBean(checkCode);
		//设置用户信息
		AppUser bean = new AppUser();
		bean.setPhone(phone);
		//查询手机号是否已存在
		List<AppUser> list = appUserDao.selectAll(bean);
		if(list.size() > 0) return GuoRespBeanUtil.setCustomContentRespBean("该手机号已存在！");
		bean.setIsDeleted("0");
		bean.setId(userId);
		//修改手机号
		int up = appUserDao.updateById(bean);
		if(up < 1) return GuoRespBeanUtil.setCustomContentRespBean("修改手机号失败");
		
		return GuoRespBeanUtil.setSuccessRespBean(respBean);
	}

	/**验证原手机号api**/
	public RespBean<Map<String, Object>> verifyMyOldPhone(String userId, String phone, String code, String type) {
		RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
		//验证验证码是否正确
		String checkCode = checkCode(phone, code, type);
		if(checkCode != null) return GuoRespBeanUtil.setCustomContentRespBean(checkCode);
		//验证该手机号是否该用户的
		AppUser bean = new AppUser();
		bean.setPhone(phone);
		bean.setId(userId);
		bean = appUserDao.selectById(bean);
		if(bean == null) return GuoRespBeanUtil.setCustomContentRespBean("该手机号非用户原手机号");
		
		return GuoRespBeanUtil.setSuccessRespBean(respBean);
	}

	/**类目管理（修改用户类目信息）api**/
	public RespBean<Map<String, Object>> updateUserNewsType(AppUser bean) {
		RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
		int up = appUserDao.updateById(bean);
		if(up < 1) return GuoRespBeanUtil.setErrorRespBean(respBean);
		
		return GuoRespBeanUtil.setSuccessRespBean(respBean);
	}

	/**修改个人资料api**/
	public RespBean<Map<String, Object>> updateMyUserInfo(AppUser bean) {
		RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
		int up = appUserDao.updateById(bean);
		if(up < 1) return GuoRespBeanUtil.setErrorRespBean(respBean);
		
		return GuoRespBeanUtil.setSuccessRespBean(respBean);
	}

}
