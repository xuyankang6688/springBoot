package top.javaguo.biz.system.bean;

import top.javaguo.core.biz.bean.BaseBean;

/**
 * @describe 权限-用户角色
 * @author javaGuo
 * @date 2018-03-28
 */
public class PerUserRole extends BaseBean {

	private static final long serialVersionUID = 1L;

	/** 非表字段属性 **/
	private String notFieldParams = "serialVersionUID,notFieldParams";

	/** 权限-用户角色ID **/
	private String id;

	/** 创建时间 **/
	private String createTime;

	/** 修改时间 **/
	private String updateTime;

	/** 用户ID **/
	private String userId;

	/** 角色ID **/
	private String roleId;

	/** 获取非表字段属性 **/
	public String getNotFieldParams() {
		return notFieldParams;
	}

	/** 获取权限-用户角色ID **/
	public String getId() {
		return id;
	}

	/** 设置权限-用户角色ID **/
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

	/** 获取用户ID **/
	public String getUserId() {
		return userId;
	}

	/** 设置用户ID **/
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/** 获取角色ID **/
	public String getRoleId() {
		return roleId;
	}

	/** 设置角色ID **/
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

    @Override
    public String toString() {
        return "PerUserRole{" +
                "notFieldParams='" + notFieldParams + '\'' +
                ", id='" + id + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", userId='" + userId + '\'' +
                ", roleId='" + roleId + '\'' +
                getToStringParam() +
                '}';
    }
}
