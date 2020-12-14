package top.javaguo.biz.sso.bean;

import top.javaguo.core.biz.bean.BaseBean;

/**
 * @describe 用户
 * @author javaGuo
 * @date 2018-03-05
 */
public class SysUser extends BaseBean {

	private static final long serialVersionUID = 1L;

	/** 用户ID **/
	private String id;

	/** 创建时间 **/
	private String createTime;

	/** 修改时间 **/
	private String updateTime;

	/** 用户名 **/
	private String username;

	/** 密码 **/
	private String password;

	/** ip地址 **/
	private String ip;

	/** 真实地址 **/
	private String address;

	/*手机号*/
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/** 获取用户ID **/
	public String getId() {
		return id;
	}

	/** 设置用户ID **/
	public void setId(String id) {
		this.id = id;
	}

	/** 获取创建时间 **/
	public String getCreateTime() {
		return createTime;
	}

	/** 设置创建时间 **/
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/** 获取修改时间 **/
	public String getUpdateTime() {
		return updateTime;
	}

	/** 设置修改时间 **/
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/** 获取用户名 **/
	public String getUsername() {
		return username;
	}

	/** 设置用户名 **/
	public void setUsername(String username) {
		this.username = username;
	}

	/** 获取密码 **/
	public String getPassword() {
		return password;
	}

	/** 设置密码 **/
	public void setPassword(String password) {
		this.password = password;
	}

	/** 获取ip地址 **/
	public String getIp() {
		return ip;
	}

	/** 设置ip地址 **/
	public void setIp(String ip) {
		this.ip = ip;
	}

	/** 获取真实地址 **/
	public String getAddress() {
		return address;
	}

	/** 设置真实地址 **/
	public void setAddress(String address) {
		this.address = address;
	}

}
