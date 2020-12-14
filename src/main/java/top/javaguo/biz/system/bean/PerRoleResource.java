package top.javaguo.biz.system.bean;

import top.javaguo.core.biz.bean.BaseBean;

/**
 * @describe 权限-角色资源
 * @author javaGuo
 * @date 2018-03-27
 */
public class PerRoleResource extends BaseBean {

	private static final long serialVersionUID = 1L;

	/** 非表字段属性 **/
	private String notFieldParams = "serialVersionUID,notFieldParams";

	/** 权限-角色资源ID **/
	private String id;

	/** 创建时间 **/
	private String createTime;

	/** 修改时间 **/
	private String updateTime;

	/** 角色id **/
	private String perRoleId;

	/** 资源id **/
	private String perResourceId;

	/** 获取非表字段属性 **/
	public String getNotFieldParams() {
		return notFieldParams;
	}

	/** 获取权限-角色资源ID **/
	public String getId() {
		return id;
	}

	/** 设置权限-角色资源ID **/
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

	/** 获取角色id **/
	public String getPerRoleId() {
		return perRoleId;
	}

	/** 设置角色id **/
	public void setPerRoleId(String perRoleId) {
		this.perRoleId = perRoleId;
	}

	/** 获取资源id **/
	public String getPerResourceId() {
		return perResourceId;
	}

	/** 设置资源id **/
	public void setPerResourceId(String perResourceId) {
		this.perResourceId = perResourceId;
	}

    @Override
    public String toString() {
        return "PerRoleResource{" +
                "notFieldParams='" + notFieldParams + '\'' +
                ", id='" + id + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", perRoleId='" + perRoleId + '\'' +
                ", perResourceId='" + perResourceId + '\'' +
                getToStringParam() +
                '}';
    }
}
