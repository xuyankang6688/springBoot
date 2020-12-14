package top.javaguo.biz.system.bean;

import top.javaguo.core.biz.bean.BaseBean;

/**
 * @describe 项目
 * @author javaGuo
 * @date 2019-01-18
 */
public class Project extends BaseBean {

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

	/**项目名**/
	private String name;

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

	/**获取项目名**/
	public String getName() {
		return name;
	}

	/**设置项目名**/
	public void setName(String name) {
		this.name = name;
	}

    @Override
    public String toString() {
        return "Project{" +
                "notFieldParams='" + notFieldParams + '\'' +
                ", id='" + id + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", isDeleted='" + isDeleted + '\'' +
                ", name='" + name + '\'' +
                getToStringParam() +
                '}';
    }
}
