package top.javaguo.biz.system.bean;

import top.javaguo.core.biz.bean.BaseBean;

/**
 * @describe 用户
 * @author javaGuo
 * @date 2018-03-08
 */
public class SysUser extends BaseBean {

	private static final long serialVersionUID = 1L;

	/** 非表字段属性 **/
	private String notFieldParams = "serialVersionUID,roleIds,department,notFieldParams";

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

	/** 用户对应角色的id集合 **/
	private String roleIds;

	/** 部门ID **/
	private String departmentId;

	/** 头像 **/
	private String headPortrait;

	/*手机号*/
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/** 获取非表字段属性 **/
	public String getNotFieldParams() {
		return notFieldParams;
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

	/** 获取用户对应角色的id集合 **/
	public String getRoleIds() {
		return roleIds;
	}

	/** 设置用户对应角色的id集合 **/
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}


	/** 获取部门ID **/
	public String getDepartmentId() {
		return departmentId;
	}

	/** 设置部门ID **/
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/** 获取头像地址 **/
	public String getHeadPortrait() {
		return headPortrait;
	}

	/** 设置头像地址 **/
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

    @Override
    public String toString() {
        return "SysUser{" +
                "notFieldParams='" + notFieldParams + '\'' +
                ", id='" + id + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roleIds='" + roleIds + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
				", phone='" + phone + '\'' +
                getToStringParam() +
                '}';
    }
}
