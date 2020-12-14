package top.javaguo.biz.system.bean;

import top.javaguo.core.biz.bean.BaseBean;

/**
 * @describe 代码生成
 * @author 超级管理员
 * @date 2019-01-18
 */
public class CreateCode extends BaseBean {

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

	/**作者**/
	private String author;

	/**描述**/
	private String describes;

	/**表名**/
	private String tableName;

	/**类名前部分**/
	private String className;

	/**类名首字母小写**/
	private String classNameLower;

	/**类字段名拼接**/
	private String keyStr;

	/**表字段名拼接**/
	private String tableKeyStr;

	/**字段注释拼接**/
	private String valueStr;

	/**用户id**/
	private String userId;

	/**项目id**/
	private String projectId;

	/**字段类型拼接**/
	private String tableKeyType;

	/**字段长度拼接**/
	private String tableKeyLength;

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

	/**获取作者**/
	public String getAuthor() {
		return author;
	}

	/**设置作者**/
	public void setAuthor(String author) {
		this.author = author;
	}

	/**获取描述**/
	public String getDescribes() {
		return describes;
	}

	/**设置描述**/
	public void setDescribes(String describes) {
		this.describes = describes;
	}

	/**获取表名**/
	public String getTableName() {
		return tableName;
	}

	/**设置表名**/
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**获取类名前部分**/
	public String getClassName() {
		return className;
	}

	/**设置类名前部分**/
	public void setClassName(String className) {
		this.className = className;
	}

	/**获取类名首字母小写**/
	public String getClassNameLower() {
		return classNameLower;
	}

	/**设置类名首字母小写**/
	public void setClassNameLower(String classNameLower) {
		this.classNameLower = classNameLower;
	}

	/**获取类字段名拼接**/
	public String getKeyStr() {
		return keyStr;
	}

	/**设置类字段名拼接**/
	public void setKeyStr(String keyStr) {
		this.keyStr = keyStr;
	}

	/**获取表字段名拼接**/
	public String getTableKeyStr() {
		return tableKeyStr;
	}

	/**设置表字段名拼接**/
	public void setTableKeyStr(String tableKeyStr) {
		this.tableKeyStr = tableKeyStr;
	}

	/**获取字段注释拼接**/
	public String getValueStr() {
		return valueStr;
	}

	/**设置字段注释拼接**/
	public void setValueStr(String valueStr) {
		this.valueStr = valueStr;
	}

	/**获取用户id**/
	public String getUserId() {
		return userId;
	}

	/**设置用户id**/
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**获取项目id**/
	public String getProjectId() {
		return projectId;
	}

	/**设置项目id**/
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**获取字段类型拼接**/
	public String getTableKeyType() {
		return tableKeyType;
	}

	/**设置字段类型拼接**/
	public void setTableKeyType(String tableKeyType) {
		this.tableKeyType = tableKeyType;
	}

	/**获取字段长度拼接**/
	public String getTableKeyLength() {
		return tableKeyLength;
	}

	/**设置字段长度拼接**/
	public void setTableKeyLength(String tableKeyLength) {
		this.tableKeyLength = tableKeyLength;
	}

	@Override
	public String toString(){
		return "CreateCode{" + 
			"id='" + id + '\'' + 
			", createTime='" + createTime + '\'' + 
			", updateTime='" + updateTime + '\'' + 
			", isDeleted='" + isDeleted + '\'' + 
			", author='" + author + '\'' + 
			", describes='" + describes + '\'' + 
			", tableName='" + tableName + '\'' + 
			", className='" + className + '\'' + 
			", classNameLower='" + classNameLower + '\'' + 
			", keyStr='" + keyStr + '\'' + 
			", tableKeyStr='" + tableKeyStr + '\'' + 
			", valueStr='" + valueStr + '\'' + 
			", userId='" + userId + '\'' + 
			", projectId='" + projectId + '\'' + 
			", tableKeyType='" + tableKeyType + '\'' + 
			", tableKeyLength='" + tableKeyLength + '\'' +
            getToStringParam() +
			'}';
	}
;
}
