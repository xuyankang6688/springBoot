package top.javaguo.biz.system.bean;

import java.util.List;

import top.javaguo.core.biz.bean.BaseBean;

/**
 * @describe 权限-角色
 * @author javaGuo
 * @date 2018-03-22
 */
public class PerRole extends BaseBean {

	private static final long serialVersionUID = 1L;

	/** 非表字段属性 **/
	private String notFieldParams = "serialVersionUID,perResourceIds,perResourceList,userOwner,perResourceIds,notFieldParams";

	/** 权限-角色ID **/
	private String id;

	/** 创建时间 **/
	private String createTime;

	/** 修改时间 **/
	private String updateTime;

	/** 角色名称 **/
	private String roleName;

	/** 角色介绍 **/
	private String roleIntroduce;

	/** 资源id **/
	private String perResourceIds;

	/** 资源集合 **/
	private List<PerResource> perResourceList;

	/** 角色是否属于某一用户 **/
	private String userOwner;

	/** 获取非表字段属性 **/
	public String getNotFieldParams() {
		return notFieldParams;
	}

	/** 获取权限-角色ID **/
	public String getId() {
		return id;
	}

	/** 设置权限-角色ID **/
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

	/** 获取角色名称 **/
	public String getRoleName() {
		return roleName;
	}

	/** 设置角色名称 **/
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/** 获取角色介绍 **/
	public String getRoleIntroduce() {
		return roleIntroduce;
	}

	/** 设置角色介绍 **/
	public void setRoleIntroduce(String roleIntroduce) {
		this.roleIntroduce = roleIntroduce;
	}

	/** 获取资源id介绍 **/
	public String getPerResourceIds() {
		return perResourceIds;
	}

	/** 设置资源id介绍 **/
	public void setPerResourceIds(String perResourceIds) {
		this.perResourceIds = perResourceIds;
	}

	/** 获取角色是否属于某一用户 **/
	public String getUserOwner() {
		return userOwner;
	}

	/** 设置角色是否属于某一用户 **/
	public void setUserOwner(String userOwner) {
		this.userOwner = userOwner;
	}

	/** 获取资源集合 **/
	public List<PerResource> getPerResourceList() {
		return perResourceList;
	}

	/** 设置资源集合 **/
	public void setPerResourceList(List<PerResource> perResourceList) {
		this.perResourceList = perResourceList;
	}

    @Override
    public String toString() {
        return "PerRole{" +
                "notFieldParams='" + notFieldParams + '\'' +
                ", id='" + id + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleIntroduce='" + roleIntroduce + '\'' +
                ", perResourceIds='" + perResourceIds + '\'' +
                ", perResourceList=" + perResourceList +
                ", userOwner='" + userOwner + '\'' +
                getToStringParam() +
                '}';
    }
}
