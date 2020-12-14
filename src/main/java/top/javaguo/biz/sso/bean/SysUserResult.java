package top.javaguo.biz.sso.bean;

import java.util.List;

/**
 * @describe 登陆返回对象
 * @author javaGuo
 * @date 2018-03-28
 */
public class SysUserResult{

	/**用户ID**/
	private String id;

	/**用户名**/
	private String username;
	
	/**令牌名**/
	private String token;
	
	/**用户权限资源**/
	List<PerResource> listPerResource;
	
	public SysUserResult() {
		super();
	}
	
	public SysUserResult(String id , String username, String token, List<PerResource> listPerResource) {
		super();
		this.id = id;
		this.username = username;
		this.token = token;
		this.listPerResource = listPerResource;
	}

	/**获取用户ID**/
	public String getId() {
		return id;
	}

	/**设置用户ID**/
	public void setId(String id) {
		this.id = id;
	}

	/**获取用户名**/
	public String getUsername() {
		return username;
	}

	/**设置用户名**/
	public void setUsername(String username) {
		this.username = username;
	}

	/**获取令牌名**/
	public String getToken() {
		return token;
	}

	/**设置令牌名**/
	public void setToken(String token) {
		this.token = token;
	}
	
	/**获取用户权限资源**/
	public List<PerResource> getListPerResource() {
		return listPerResource;
	}

	/**设置用户权限资源**/
	public void setListPerResource(List<PerResource> listPerResource) {
		this.listPerResource = listPerResource;
	}

}
