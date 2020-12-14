package top.javaguo.biz.system.bean;

import top.javaguo.core.biz.bean.BaseBean;

/**
 * @describe 城市表
 * @author 
 * @date 2019-10-23
 */
public class CodeAreas extends BaseBean {

	private static final long serialVersionUID = 1L;

	/** 非表字段属性 **/
	private String notFieldParams = "serialVersionUID,notFieldParams";

	/**编号**/
	private String id;

	/**创建时间**/
	private String createTime;

	/**修改时间**/
	private String updateTime;

	/**是否删除**/
	private String isDeleted;

	/**名称**/
	private String name;

	/**父级id**/
	private String parentId;

	/**简称**/
	private String shortName;

	/**等级类型（0国家1省2市3区）**/
	private String levelType;

	/**城市编码**/
	private String cityCode;

	/**邮政编码**/
	private String zipCode;

	/**合并名称**/
	private String mergerName;

	/**城市经度**/
	private String lng;

	/**城市纬度**/
	private String lat;

	/**城市拼音**/
	private String pinyin;

	/**地区名称**/
	private String areaName;

	/**是否是热点城市**/
	private String isHot;

	/**是否是商店**/
	private String isStore;

	/** 获取非表字段属性 **/
	public String getNotFieldParams() {
		return notFieldParams;
	}

	/**获取编号**/
	public String getId() {
		return id;
	}

	/**设置编号**/
	public void setId(String id) {
		this.id = id;
	}

	/**获取创建时间**/
	public String getCreateTime() {
		return createTime;
	}

	/**设置创建时间**/
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**获取修改时间**/
	public String getUpdateTime() {
		return updateTime;
	}

	/**设置修改时间**/
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**获取是否删除**/
	public String getIsDeleted() {
		return isDeleted;
	}

	/**设置是否删除**/
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**获取名称**/
	public String getName() {
		return name;
	}

	/**设置名称**/
	public void setName(String name) {
		this.name = name;
	}

	/**获取父级id**/
	public String getParentId() {
		return parentId;
	}

	/**设置父级id**/
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**获取简称**/
	public String getShortName() {
		return shortName;
	}

	/**设置简称**/
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**获取等级类型（0国家1省2市3区）**/
	public String getLevelType() {
		return levelType;
	}

	/**设置等级类型（0国家1省2市3区）**/
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}

	/**获取城市编码**/
	public String getCityCode() {
		return cityCode;
	}

	/**设置城市编码**/
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**获取邮政编码**/
	public String getZipCode() {
		return zipCode;
	}

	/**设置邮政编码**/
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**获取合并名称**/
	public String getMergerName() {
		return mergerName;
	}

	/**设置合并名称**/
	public void setMergerName(String mergerName) {
		this.mergerName = mergerName;
	}

	/**获取城市经度**/
	public String getLng() {
		return lng;
	}

	/**设置城市经度**/
	public void setLng(String lng) {
		this.lng = lng;
	}

	/**获取城市纬度**/
	public String getLat() {
		return lat;
	}

	/**设置城市纬度**/
	public void setLat(String lat) {
		this.lat = lat;
	}

	/**获取城市拼音**/
	public String getPinyin() {
		return pinyin;
	}

	/**设置城市拼音**/
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	/**获取地区名称**/
	public String getAreaName() {
		return areaName;
	}

	/**设置地区名称**/
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**获取是否是热点城市**/
	public String getIsHot() {
		return isHot;
	}

	/**设置是否是热点城市**/
	public void setIsHot(String isHot) {
		this.isHot = isHot;
	}

	/**获取是否是商店**/
	public String getIsStore() {
		return isStore;
	}

	/**设置是否是商店**/
	public void setIsStore(String isStore) {
		this.isStore = isStore;
	}

	@Override
	public String toString(){
		return "CodeAreas{" + 
			"id='" + id + '\'' + 
			"createTime='" + createTime + '\'' + 
			"updateTime='" + updateTime + '\'' + 
			"isDeleted='" + isDeleted + '\'' + 
			"name='" + name + '\'' + 
			"parentId='" + parentId + '\'' + 
			"shortName='" + shortName + '\'' + 
			"levelType='" + levelType + '\'' + 
			"cityCode='" + cityCode + '\'' + 
			"zipCode='" + zipCode + '\'' + 
			"mergerName='" + mergerName + '\'' + 
			"lng='" + lng + '\'' + 
			"lat='" + lat + '\'' + 
			"pinyin='" + pinyin + '\'' + 
			"areaName='" + areaName + '\'' + 
			"isHot='" + isHot + '\'' + 
			"isStore='" + isStore + '\'' + 
			'}';
	}

}
