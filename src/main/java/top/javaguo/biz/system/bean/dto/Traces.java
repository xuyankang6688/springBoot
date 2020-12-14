package top.javaguo.biz.system.bean.dto;



/**物流信息实体类
 * 
 * @author Administrator
 *
 */
public class Traces {
	
	/**物流信息*/
	private String AcceptStation;
	
	/** 非表字段属性 **/
	private String notFieldParams = "serialVersionUID,notFieldParams";
	
	/**时间*/
	private String AcceptTime;
	
	/** 获取非表字段属性 **/
	public String getNotFieldParams() {
		return notFieldParams;
	}
	/**获得物流信息*/
	public String getAcceptStation() {
		return AcceptStation;
	}
	/**设置物流信息*/
	public void setAcceptStation(String acceptStation) {
		AcceptStation = acceptStation;
	}
	/**获得物流时间*/
	public String getAcceptTime() {
		return AcceptTime;
	}
	/**设置物流时间*/
	public void setAcceptTime(String acceptTime) {
		AcceptTime = acceptTime;
	}
	@Override
	public String toString() {
		return "Traces [AcceptStation=" + AcceptStation + ", AcceptTime=" + AcceptTime + "]";
	}
	
	

}
