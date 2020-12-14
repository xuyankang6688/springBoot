package top.javaguo.core.resp;

import java.io.Serializable;

import top.javaguo.core.resp.enums.RespMsgEnum;

/**
 * 响应实体类
 * 
 * @author javaGuo
 * @date 2018/03/06
 */
public class RespBean<T> implements Serializable  {

	private static final long serialVersionUID = 1L;

	/** 响应码 **/
	private String code = RespMsgEnum._0000000.getCode();

	/** 响应内容 **/
	private String msg = RespMsgEnum._0000000.getMsg();

	/** 内容 **/
	private T data;

	public RespBean() {
		super();
	}

	public RespBean(T data) {
		super();
		this.data = data;
	}

	/** 获取响应码 **/
	public String getCode() {
		return code;
	}

	/** 设置响应码 **/
	public void setCode(String code) {
		this.code = code;
	}

	/** 获取响应内容 **/
	public String getMsg() {
		return msg;
	}

	/** 设置响应内容 **/
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/** 获取内容 **/
	public T getData() {
		return data;
	}

	/** 设置内容 **/
	public void setData(T data) {
		this.data = data;
	}

}
