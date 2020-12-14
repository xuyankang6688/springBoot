package top.javaguo.biz.system.bean;

import top.javaguo.core.biz.bean.BaseBean;

/**
 * @describe 车主或者货主公司表
 * @author 
 * @date 2019-12-07
 */
public class CompanyUser extends BaseBean {

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

	/**公司名称**/
	private String companyName;

	/**公司地址**/
	private String companyAddress;

	/**公司电话**/
	private String companyPhone;

	/**公司图片**/
	private String companysImg;

	/**关联用户id**/
	private String userId;

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

	/**获取公司名称**/
	public String getCompanyName() {
		return companyName;
	}

	/**设置公司名称**/
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**获取公司地址**/
	public String getCompanyAddress() {
		return companyAddress;
	}

	/**设置公司地址**/
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	/**获取公司电话**/
	public String getCompanyPhone() {
		return companyPhone;
	}

	/**设置公司电话**/
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	/**获取公司图片**/
	public String getCompanysImg() {
		return companysImg;
	}

	/**设置公司图片**/
	public void setCompanysImg(String companysImg) {
		this.companysImg = companysImg;
	}

	/**获取关联用户id**/
	public String getUserId() {
		return userId;
	}

	/**设置关联用户id**/
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString(){
		return "CompanyUser{" + 
			"id='" + id + '\'' + 
			"createTime='" + createTime + '\'' + 
			"updateTime='" + updateTime + '\'' + 
			"isDeleted='" + isDeleted + '\'' + 
			"companyName='" + companyName + '\'' + 
			"companyAddress='" + companyAddress + '\'' + 
			"companyPhone='" + companyPhone + '\'' + 
			"companysImg='" + companysImg + '\'' +
			"userId='" + userId + '\'' + 
			'}';
	}

}
