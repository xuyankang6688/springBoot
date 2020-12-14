package top.javaguo.utils.createCodeUtil;

/**
 * @describe 创建代码所需属性类
 * @author javaGuo
 * @date 2018-02-07
 */
public class CreateCodeBean {
	
	public CreateCodeBean() {
	}
	
	public CreateCodeBean(String author, String describe, String tableName, String className, String keyStr, String keyStrFirstLower, 
			String tableKeyStr, String tableKeyLength, String tableKeyType, String valueStr) {
		super();
		this.author = author;
		this.describe = describe;
		this.tableName = tableName;
		this.className = className;
		this.classNameLower = className.substring(0,1).toLowerCase()+className.substring(1);
		this.keyStr = keyStr;
		this.tableKeyLength = tableKeyLength;
		this.tableKeyType = tableKeyType;
		this.keyStrFirstLower = keyStrFirstLower;
		this.tableKeyStr = tableKeyStr;
		this.valueStr = valueStr;
	}

	/**作者**/
	public String author = "javaGuo";
		
	/**描述**/
	public String describe = "用户";
	
	/**表名**/
	public String tableName = "sys_user";

	/**类名前部分**/
	public String className = "SysUser";

	/**类名首字母小写**/
	public String classNameLower = "sysUser";
	
	/**类字段名拼接**/
	public String keyStr = "Id,CreateTime,UpdateTime,Username,Password";
	
	/**类字段名拼接**/
	public String keyStrFirstLower = "id,createTime,updateTime,username,password";
	
	/**表字段名拼接**/
	public String tableKeyStr = "id,create_time,update_time,username,password";
	
	/**字段注释拼接**/
	public String valueStr = "用户ID,创建时间,修改时间,用户名,密码";

	/**字段类型拼接**/
	private String tableKeyType = "varchar,varchar,varchar,varchar,varchar";
	
	/**字段长度拼接**/
	private String tableKeyLength = "18,19,19,20,48";
	
	/**获得作者**/
	public String getAuthor() {
		return author;
	}

	/**设置作者**/
	public void setAuthor(String author) {
		this.author = author;
	}

	/**获得描述**/
	public String getDescribe() {
		return describe;
	}

	/**设置描述**/
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	/**获得表名**/
	public String getTableName() {
		return tableName;
	}

	/**设置表名**/
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**类名首字母小写**/
	public String getClassNameLower() {
		return classNameLower;
	}

	/**类名首字母小写**/
	public void setClassNameLower(String classNameLower) {
		this.classNameLower = classNameLower;
	}

	/**获得类名前部分**/
	public String getClassName() {
		return className;
	}

	/**设置类名前部分**/
	public void setClassName(String className) {
		this.className = className;
	}

	/**获得字段拼接**/
	public String getKeyStr() {
		return keyStr;
	}

	/**设置字段拼接**/
	public void setKeyStr(String keyStr) {
		this.keyStr = keyStr;
	}

	/**获得字段拼接**/
	public String getTableKeyStr() {
		return tableKeyStr;
	}

	/**设置字段拼接**/
	public void setTableKeyStr(String tableKeyStr) {
		this.tableKeyStr = tableKeyStr;
	}

	/**获得字段注释拼接**/
	public String getValueStr() {
		return valueStr;
	}

	/**设置字段注释拼接**/
	public void setValueStr(String valueStr) {
		this.valueStr = valueStr;
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
	
}
