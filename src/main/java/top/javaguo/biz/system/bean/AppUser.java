package top.javaguo.biz.system.bean;

import top.javaguo.core.biz.bean.BaseBean;

import java.util.List;

/**
 * @describe 用户表
 * @author admin
 * @date 2019-08-13
 */
public class AppUser extends BaseBean{

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

	/**账号**/
	private String accountNumber;

	/**密码**/
	private String password;

	/**姓名**/
	private String name;

	/**手机号**/
	private String phone;

	/**头像**/
	private String head;

	/**昵称**/
	private String nickName;

	/**用户身份（注册时多选）**/
	private String identity;

	/**支付密码（是否需要）**/
	private String payPassword;

	/**自我简介**/
	private String synopsis;

	/**我的简历（H5页面应该是存放url）**/
	private String myResume;

	/**资讯分类ID(行业资讯分类的ID集合)**/
	private String newsTypeId;

	/**性别**/
	private String sex;
	
	/**新增数据-商品收藏数**/
	private String goodsLikeNum;
	/**类型（1个人，2车队，3企业）**/
	private String type;
	/**身份证号**/
	private String idCard;
	/**星级评分**/
	private String starRating;
	/**成交单数**/
	private String transactionNumber;
	/**身份证反面**/
	private String reverseCardImg;
	/**身份证正面**/
	private String frontCardImg;
	/**手持身份证**/
	private String holdingCardImg;
	/**驾驶证**/
	private String driverCardImg;
	/**最后一次登录时间**/
	private String lastLoginTime;
	/**注册时间**/
	private String registerTime;
	/**注册时间**/
	private String carNumber;

	/**公司信息**/
	private CompanyUser companyUser;

	/**车辆信息**/
	private List<VehicleInformation> vehicleInformationList;

	public List<VehicleInformation> getVehicleInformationList() {
		return vehicleInformationList;
	}

	public void setVehicleInformationList(List<VehicleInformation> vehicleInformationList) {
		this.vehicleInformationList = vehicleInformationList;
	}

	public CompanyUser getCompanyUser() {
		return companyUser;
	}

	public void setCompanyUser(CompanyUser companyUser) {
		this.companyUser = companyUser;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getStarRating() {
		return starRating;
	}

	public void setStarRating(String starRating) {
		this.starRating = starRating;
	}

	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public String getReverseCardImg() {
		return reverseCardImg;
	}

	public void setReverseCardImg(String reverseCardImg) {
		this.reverseCardImg = reverseCardImg;
	}

	public String getFrontCardImg() {
		return frontCardImg;
	}

	public void setFrontCardImg(String frontCardImg) {
		this.frontCardImg = frontCardImg;
	}

	public String getHoldingCardImg() {
		return holdingCardImg;
	}

	public void setHoldingCardImg(String holdingCardImg) {
		this.holdingCardImg = holdingCardImg;
	}

	public String getDriverCardImg() {
		return driverCardImg;
	}

	public void setDriverCardImg(String driverCardImg) {
		this.driverCardImg = driverCardImg;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	/**获取新增数据-商品收藏数**/
	public String getGoodsLikeNum() {
		return goodsLikeNum;
	}

	/**设置新增数据-商品收藏数**/
	public void setGoodsLikeNum(String goodsLikeNum) {
		this.goodsLikeNum = goodsLikeNum;
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

	/**获取账号**/
	public String getAccountNumber() {
		return accountNumber;
	}

	/**设置账号**/
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**获取密码**/
	public String getPassword() {
		return password;
	}

	/**设置密码**/
	public void setPassword(String password) {
		this.password = password;
	}

	/**获取姓名**/
	public String getName() {
		return name;
	}

	/**设置姓名**/
	public void setName(String name) {
		this.name = name;
	}

	/**获取手机号**/
	public String getPhone() {
		return phone;
	}

	/**设置手机号**/
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**获取头像**/
	public String getHead() {
		return head;
	}

	/**设置头像**/
	public void setHead(String head) {
		this.head = head;
	}

	/**获取昵称**/
	public String getNickName() {
		return nickName;
	}

	/**设置昵称**/
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**获取用户身份（注册时多选）**/
	public String getIdentity() {
		return identity;
	}

	/**设置用户身份（注册时多选）**/
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	/**获取支付密码（是否需要）**/
	public String getPayPassword() {
		return payPassword;
	}

	/**设置支付密码（是否需要）**/
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	/**获取自我简介**/
	public String getSynopsis() {
		return synopsis;
	}

	/**设置自我简介**/
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	/**获取我的简历（H5页面应该是存放url）**/
	public String getMyResume() {
		return myResume;
	}

	/**设置我的简历（H5页面应该是存放url）**/
	public void setMyResume(String myResume) {
		this.myResume = myResume;
	}

	/**获取资讯分类ID(行业资讯分类的ID集合)**/
	public String getNewsTypeId() {
		return newsTypeId;
	}

	/**设置资讯分类ID(行业资讯分类的ID集合)**/
	public void setNewsTypeId(String newsTypeId) {
		this.newsTypeId = newsTypeId;
	}

	/**获取性别**/
	public String getSex() {
		return sex;
	}

	/**设置性别**/
	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString(){
		return "AppUser{" + 
			"id='" + id + '\'' + 
			", createTime='" + createTime + '\'' + 
			", updateTime='" + updateTime + '\'' + 
			", isDeleted='" + isDeleted + '\'' + 
			", accountNumber='" + accountNumber + '\'' + 
			", password='" + password + '\'' + 
			", name='" + name + '\'' + 
			", phone='" + phone + '\'' + 
			", head='" + head + '\'' + 
			", nickName='" + nickName + '\'' + 
			", identity='" + identity + '\'' + 
			", payPassword='" + payPassword + '\'' + 
			", synopsis='" + synopsis + '\'' + 
			", myResume='" + myResume + '\'' + 
			", newsTypeId='" + newsTypeId + '\'' + 
			", sex='" + sex + '\'' +
				", type='" + type + '\'' +
				", idCard='" + idCard + '\'' +
				", starRating='" + starRating + '\'' +
				", transactionNumber='" + transactionNumber + '\'' +
				", reverseCardImg='" + reverseCardImg + '\'' +
				", frontCardImg='" + frontCardImg + '\'' +
				", holdingCardImg='" + holdingCardImg + '\'' +
				", driverCardImg='" + driverCardImg + '\'' +
				", lastLoginTime='" + lastLoginTime + '\'' +
				", registerTime='" + registerTime + '\'' +
				", companyUser='" + companyUser + '\'' +




			getToStringParam() +
			'}';
	}
;
}
