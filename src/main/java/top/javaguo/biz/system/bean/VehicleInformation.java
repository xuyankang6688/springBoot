package top.javaguo.biz.system.bean;

import top.javaguo.core.biz.bean.BaseBean;

/**
 * @describe 车辆信息
 * @author 
 * @date 2019-12-07
 */
public class VehicleInformation extends BaseBean {

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

	/**车头车牌号**/
	private String headstockBrand;

	/**挂车车牌号**/
	private String trailerBrand;

	/**核定载重**/
	private String allowLoad;

	/**车型**/
	private String vehicleType;

	/**车长**/
	private String conductor;

	/**车辆图片**/
	private String vehicleImg;

	/**用户id**/
	private String userId;

	/**是否通过审核1是，2否**/
	private String vehicleStatus;

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

	/**获取车头车牌号**/
	public String getHeadstockBrand() {
		return headstockBrand;
	}

	/**设置车头车牌号**/
	public void setHeadstockBrand(String headstockBrand) {
		this.headstockBrand = headstockBrand;
	}

	/**获取挂车车牌号**/
	public String getTrailerBrand() {
		return trailerBrand;
	}

	/**设置挂车车牌号**/
	public void setTrailerBrand(String trailerBrand) {
		this.trailerBrand = trailerBrand;
	}

	/**获取核定载重**/
	public String getAllowLoad() {
		return allowLoad;
	}

	/**设置核定载重**/
	public void setAllowLoad(String allowLoad) {
		this.allowLoad = allowLoad;
	}

	/**获取车型**/
	public String getVehicleType() {
		return vehicleType;
	}

	/**设置车型**/
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	/**获取车长**/
	public String getConductor() {
		return conductor;
	}

	/**设置车长**/
	public void setConductor(String conductor) {
		this.conductor = conductor;
	}

	/**获取车辆图片**/
	public String getVehicleImg() {
		return vehicleImg;
	}

	/**设置车辆图片**/
	public void setVehicleImg(String vehicleImg) {
		this.vehicleImg = vehicleImg;
	}

	/**获取用户id**/
	public String getUserId() {
		return userId;
	}

	/**设置用户id**/
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**获取是否通过审核1是，2否**/
	public String getVehicleStatus() {
		return vehicleStatus;
	}

	/**设置是否通过审核1是，2否**/
	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	@Override
	public String toString(){
		return "VehicleInformation{" + 
			"id='" + id + '\'' + 
			"createTime='" + createTime + '\'' + 
			"updateTime='" + updateTime + '\'' + 
			"isDeleted='" + isDeleted + '\'' + 
			"headstockBrand='" + headstockBrand + '\'' + 
			"trailerBrand='" + trailerBrand + '\'' + 
			"allowLoad='" + allowLoad + '\'' + 
			"vehicleType='" + vehicleType + '\'' + 
			"conductor='" + conductor + '\'' + 
			"vehicleImg='" + vehicleImg + '\'' + 
			"userId='" + userId + '\'' + 
			"vehicleStatus='" + vehicleStatus + '\'' + 
			'}';
	}

}
