package top.javaguo.biz.system.bean;

import top.javaguo.core.biz.bean.BaseBean;

/**
 * @describe 首页活动展示
 * @author 
 * @date 2019-11-25
 */
public class AppActive extends BaseBean {

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

	/**图片**/
	private String img;

	/**活动类型（1商品/2店铺/3外链）其他类型待定**/
	private String type;

	/**关联ID跳转URL**/
	private String param;

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

	/**获取图片**/
	public String getImg() {
		return img;
	}

	/**设置图片**/
	public void setImg(String img) {
		this.img = img;
	}

	/**获取活动类型（1商品/2店铺/3外链）其他类型待定**/
	public String getType() {
		return type;
	}

	/**设置活动类型（1商品/2店铺/3外链）其他类型待定**/
	public void setType(String type) {
		this.type = type;
	}

	/**获取关联ID跳转URL**/
	public String getParam() {
		return param;
	}

	/**设置关联ID跳转URL**/
	public void setParam(String param) {
		this.param = param;
	}

	@Override
	public String toString(){
		return "AppActive{" + 
			"id='" + id + '\'' + 
			"createTime='" + createTime + '\'' + 
			"updateTime='" + updateTime + '\'' + 
			"isDeleted='" + isDeleted + '\'' + 
			"img='" + img + '\'' + 
			"type='" + type + '\'' + 
			"param='" + param + '\'' + 
			'}';
	}

}
