package top.javaguo.biz.system.bean;

import top.javaguo.core.biz.bean.BaseBean;

/**
 * @describe 货物表
 * @author 
 * @date 2019-12-09
 */
public class Goods extends BaseBean {

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

	/**申请人**/
	private String applicant;

	/**出发地**/
	private String departLocal;

	/**目的地**/
	private String destination;

	/**装货时间**/
	private String loadingTime;

	/**货物名称**/
	private String goodsName;

	/**货物重量**/
	private String goodsWeight;

	/**宽**/
	private String width;

	/**高**/
	private String height;

	/**长**/
	private String length;

	/**用车需求**/
	private String vehicleDemand;

	/**是否可以拼车**/
	private String isCarpooling;

	/**是否开发票**/
	private String isInvoice;

	/**货物图片**/
	private String goodsImg;

	/**货主id**/
	private String userId;
	/**电话**/
	private String phone;

	/**货物状态**/
	private String goodsStatus;

	/**发布人**/
	private String userName;

	/**类型**/
	private String type;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

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

	/**获取申请人**/
	public String getApplicant() {
		return applicant;
	}

	/**设置申请人**/
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	/**获取出发地**/
	public String getDepartLocal() {
		return departLocal;
	}

	/**设置出发地**/
	public void setDepartLocal(String departLocal) {
		this.departLocal = departLocal;
	}

	/**获取目的地**/
	public String getDestination() {
		return destination;
	}

	/**设置目的地**/
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**获取装货时间**/
	public String getLoadingTime() {
		return loadingTime;
	}

	/**设置装货时间**/
	public void setLoadingTime(String loadingTime) {
		this.loadingTime = loadingTime;
	}

	/**获取货物名称**/
	public String getGoodsName() {
		return goodsName;
	}

	/**设置货物名称**/
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**获取货物重量**/
	public String getGoodsWeight() {
		return goodsWeight;
	}

	/**设置货物重量**/
	public void setGoodsWeight(String goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	/**获取宽**/
	public String getWidth() {
		return width;
	}

	/**设置宽**/
	public void setWidth(String width) {
		this.width = width;
	}

	/**获取高**/
	public String getHeight() {
		return height;
	}

	/**设置高**/
	public void setHeight(String height) {
		this.height = height;
	}

	/**获取长**/
	public String getLength() {
		return length;
	}

	/**设置长**/
	public void setLength(String length) {
		this.length = length;
	}

	/**获取用车需求**/
	public String getVehicleDemand() {
		return vehicleDemand;
	}

	/**设置用车需求**/
	public void setVehicleDemand(String vehicleDemand) {
		this.vehicleDemand = vehicleDemand;
	}

	/**获取是否可以拼车**/
	public String getIsCarpooling() {
		return isCarpooling;
	}

	/**设置是否可以拼车**/
	public void setIsCarpooling(String isCarpooling) {
		this.isCarpooling = isCarpooling;
	}

	/**获取是否开发票**/
	public String getIsInvoice() {
		return isInvoice;
	}

	/**设置是否开发票**/
	public void setIsInvoice(String isInvoice) {
		this.isInvoice = isInvoice;
	}

	/**获取货物图片**/
	public String getGoodsImg() {
		return goodsImg;
	}

	/**设置货物图片**/
	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	/**获取货主id**/
	public String getUserId() {
		return userId;
	}

	/**设置货主id**/
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString(){
		return "Goods{" + 
			"id='" + id + '\'' + 
			"createTime='" + createTime + '\'' + 
			"updateTime='" + updateTime + '\'' + 
			"isDeleted='" + isDeleted + '\'' + 
			"applicant='" + applicant + '\'' + 
			"departLocal='" + departLocal + '\'' + 
			"destination='" + destination + '\'' + 
			"loadingTime='" + loadingTime + '\'' + 
			"goodsName='" + goodsName + '\'' + 
			"goodsWeight='" + goodsWeight + '\'' + 
			"width='" + width + '\'' + 
			"height='" + height + '\'' + 
			"length='" + length + '\'' +
			"vehicleDemand='" + vehicleDemand + '\'' + 
			"isCarpooling='" + isCarpooling + '\'' + 
			"isInvoice='" + isInvoice + '\'' + 
			"goodsImg='" + goodsImg + '\'' + 
			"userId='" + userId + '\'' +
				"phone='" + phone + '\'' +
				"goodsStatus='" + goodsStatus + '\'' +
				"userName='" + userName + '\'' +
				"type='" + type + '\'' +
				'}';
	}

}
