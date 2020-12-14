package top.javaguo.biz.sso.bean;

/**
 * @describe 权限-资源
 * @author javaGuo
 * @date 2018-03-20
 */
public class PerResource {

	/** 权限-资源ID **/
	private String id;

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

    /** 菜单级别 **/
    private String menuLevel;

    /** 接口url **/
    private String apiUrl;

	/** 获取权限-资源ID **/
	public String getId() {
		return id;
	}

	/** 设置权限-资源ID **/
	public void setId(String id) {
		this.id = id;
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
}
