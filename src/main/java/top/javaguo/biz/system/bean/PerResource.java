package top.javaguo.biz.system.bean;

import top.javaguo.core.biz.bean.BaseBean;

/**
 * @describe 权限-资源
 * @author javaGuo
 * @date 2018-03-20
 */
public class PerResource extends BaseBean {

	private static final long serialVersionUID = 1L;

	public PerResource() {
		super();
	}

	public PerResource(String id, String parentId, String resName, String resIntroduce, String resOrderNum,
			String resUrl, String menuLevel, String apiUrl) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.resName = resName;
		this.resIntroduce = resIntroduce;
		this.resOrderNum = resOrderNum;
		this.resUrl = resUrl;
		this.menuLevel = menuLevel;
		this.apiUrl = apiUrl;
	}

	/** 非表字段属性 **/
	private String notFieldParams = "serialVersionUID,roleOwner,notFieldParams";

	/** 权限-资源ID **/
	private String id;

	/** 创建时间 **/
	private String createTime;

	/** 修改时间 **/
	private String updateTime;

	/** 上级ID **/
	private String parentId;

	/** 资源名称 **/
	private String resName;

	/** 资源地址 **/
	private String resUrl;

	/** 资源介绍 **/
	private String resIntroduce;

	/** 排序顺序 **/
	private String resOrderNum;

	/** 资源是否属于某一角色 **/
	private String roleOwner;

	/** 菜单级别 **/
	private String menuLevel;

    /** 接口url **/
    private String apiUrl;


    /** 获取非表字段属性 **/
	public String getNotFieldParams() {
		return notFieldParams;
	}

	/** 获取权限-资源ID **/
	public String getId() {
		return id;
	}

	/** 设置权限-资源ID **/
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

	/** 获取上级ID **/
	public String getParentId() {
		return parentId;
	}

	/** 设置上级ID **/
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/** 获取资源名称 **/
	public String getResName() {
		return resName;
	}

	/** 设置资源名称 **/
	public void setResName(String resName) {
		this.resName = resName;
	}

	/** 获取资源地址 **/
	public String getResUrl() {
		return resUrl;
	}

	/** 设置资源地址 **/
	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	/** 获取资源介绍 **/
	public String getResIntroduce() {
		return resIntroduce;
	}

	/** 设置资源介绍 **/
	public void setResIntroduce(String resIntroduce) {
		this.resIntroduce = resIntroduce;
	}

	/** 获取排序顺序 **/
	public String getResOrderNum() {
		return resOrderNum;
	}

	/** 设置排序顺序 **/
	public void setResOrderNum(String resOrderNum) {
		this.resOrderNum = resOrderNum;
	}

	/** 获取资源是否属于某一角色 **/
	public String getRoleOwner() {
		return roleOwner;
	}

	/** 设置资源是否属于某一角色 **/
	public void setRoleOwner(String roleOwner) {
		this.roleOwner = roleOwner;
	}

	/** 获取菜单级别 **/
	public String getMenuLevel() {
		return menuLevel;
	}

	/** 设置菜单级别 **/
	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

    /** 获取接口url **/
    public String getApiUrl() { return apiUrl; }

    /** 设置接口url **/
    public void setApiUrl(String apiUrl) { this.apiUrl = apiUrl; }

    @Override
    public String toString() {
        return "PerResource{" +
                "notFieldParams='" + notFieldParams + '\'' +
                ", id='" + id + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", parentId='" + parentId + '\'' +
                ", resName='" + resName + '\'' +
                ", resUrl='" + resUrl + '\'' +
                ", resIntroduce='" + resIntroduce + '\'' +
                ", resOrderNum='" + resOrderNum + '\'' +
                ", roleOwner='" + roleOwner + '\'' +
                ", menuLevel='" + menuLevel + '\'' +
                ", apiUrl='" + apiUrl + '\'' +
                getToStringParam() +
                '}';
    }
}
