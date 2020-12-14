package top.javaguo.biz.system.bean;

import top.javaguo.core.biz.bean.BaseBean;

/**
 * @describe 收藏表
 * @author admin
 * @date 2019-08-13
 */
public class UserCollect extends BaseBean{

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

	/**商品id**/
	private String goodsId;

	/**用户id**/
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

	/**获取商品id**/
	public String getGoodsId() {
		return goodsId;
	}

	/**设置商品id**/
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	/**获取用户id**/
	public String getUserId() {
		return userId;
	}

	/**设置用户id**/
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString(){
		return "UserCollect{" + 
			"id='" + id + '\'' + 
			", createTime='" + createTime + '\'' + 
			", updateTime='" + updateTime + '\'' + 
			", isDeleted='" + isDeleted + '\'' + 
			", goodsId='" + goodsId + '\'' + 
			", userId='" + userId + '\'' + 
			getToStringParam() +
			'}';
	}
;
}
