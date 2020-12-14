package top.javaguo.biz.system.bean.dto;

import top.javaguo.core.biz.bean.BaseBean;

/**
 * @describe 物流公司信息表
 */
public class ExpressNumberDto extends BaseBean{

	private static final long serialVersionUID = 1L;

	/** 非表字段属性 **/
	private String notFieldParams = "serialVersionUID,notFieldParams";

	/**编号**/
	private String id;

	/**快递公司名**/
	private String name;

	/**快递公司编号**/
	private String code;

	/**获取编号**/
	public String getId() {
		return id;
	}

	/**设置编号**/
	public void setId(String id) {
		this.id = id;
	}

	/**获取快递公司名**/
	public String getName() {
		return name;
	}

	/**设置快递公司名**/
	public void setName(String name) {
		this.name = name;
	}

	/**获取快递公司编号**/
	public String getCode() {
		return code;
	}

	/**设置快递公司编号**/
	public void setCode(String code) {
		this.code = code;
	}

	/** 获取非表字段属性 **/
	public String getNotFieldParams() {
		return notFieldParams;
	}

	@Override
	public String toString() {
		return "ExpressNumberDto [notFieldParams=" + notFieldParams + ", id=" + id + ", name=" + name + ", code=" + code
				+ "]";
	}

}
