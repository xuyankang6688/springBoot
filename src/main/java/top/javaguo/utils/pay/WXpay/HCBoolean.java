package top.javaguo.utils.pay.WXpay;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 简单的判断工具
 * 
 * @author 钟展峰
 *
 *         2015年8月21日
 */
public class HCBoolean {

	/**
	 * 判断传入对象是否为空或值为空
	 *
	 * @param obj
	 *            需要判断是否为空的对象
	 * @return true-如果对象为空，false-如果对象非空
	 */
	public static boolean isEmpty(Object obj) {
		// 判断是否为空
		if (obj == null)
			return true;
		// ----------------根据各种对象类型判断是否值为空--------------
		if (obj instanceof String)
			return ((String) obj).trim().equals("");
		if (obj instanceof Collection) {
			Collection coll = (Collection) obj;
			return coll.size() == 0;
		}
		if (obj instanceof Map) {
			Map map = (Map) obj;
			return map.size() == 0;
		}
		if (obj.getClass().isArray())
			return Array.getLength(obj) == 0;
		else
			return false;
	}

	/**
	 * 是Null或 ""
	 * 
	 * @param source
	 * @return
	 */
	public static boolean isEmpty(String source) {
		return (source == null || source.trim().isEmpty());
	}

	/**
	 * 不为Null和 ""
	 * 
	 * @param source
	 * @return
	 */
	public static boolean isNotEmpty(String source) {
		return (source != null && !source.trim().isEmpty());
	}

	/**
	 * Null或 长度=0
	 * 
	 * @author 钟展峰 2015年8月21日 <br/>
	 * @param source
	 * @return
	 */
	public static boolean isEmpty(String[] source) {
		return (source == null || source.length == 0);
	}

	/**
	 * 非Null 并且 长度>0 返回true
	 * 
	 * @author 钟展峰 2015年8月21日 <br/>
	 * @param source
	 * @return
	 */
	public static boolean isNotEmpty(String[] source) {
		return (source != null && source.length > 0);
	}

	/**
	 * Null 或 长度=0 返回true
	 * 
	 * @author 钟展峰 2015年8月21日 <br/>
	 * @param source
	 * @return
	 */
	public static boolean isEmpty(List<Object> source) {
		return (source == null || source.size() == 0);
	}

	/**
	 * 非Null 并且 长度>0 返回true
	 * 
	 * @author 钟展峰 2015年8月21日 <br/>
	 * @param source
	 * @return
	 */
	public static boolean isNotEmpty(List<Object> source) {
		return (source != null && source.size() > 0);
	}

	/**
	 * 非Null
	 * 
	 * @param source
	 * @return
	 */
	public static boolean isNotNull(Object source) {
		return source != null;
	}

	/**
	 * 是Null
	 * 
	 * @param source
	 * @return
	 */
	public static boolean isNull(Object source) {
		return source == null;
	}

	public static boolean isWXWeb(String agent) {
		if (HCBoolean.isEmpty(agent))
			return false;
		if (agent.contains("MicroMessenger"))
			return true;
		return false;
	}

	public static boolean isAliWeb(String agent) {
		if (HCBoolean.isEmpty(agent))
			return false;
		if (agent.contains("Alipay"))
			return true;
		return false;
	}
	
	public static boolean isWXQR(String agent) {
		if (HCBoolean.isEmpty(agent))
			return false;
		if (agent.startsWith("13"))
			return true;
		return false;
	}
	
	public static boolean isQqQR(String agent) {
		if (HCBoolean.isEmpty(agent))
			return false;
		if (agent.startsWith("14"))
			return true;
		return false;
	}

	public static boolean isAliQR(String agent) {
		if (HCBoolean.isEmpty(agent))
			return false;
		if (agent.startsWith("28"))
			return true;
		return false;
	}
	
	public static boolean isUnionQR(String agent) {
		if (HCBoolean.isEmpty(agent))
			return false;
		if (agent.startsWith("31"))
			return true;
		return false;
	}
	
	public static boolean isJdQR(String agent) {
		if (HCBoolean.isEmpty(agent))
			return false;
		if (agent.startsWith("40"))
			return true;
		return false;
	}	
	
	/*public static String getOrganSimpleName(String organName) {
		if (HCBoolean.isEmpty(organName)){
			return "ORGAN";
		}
		if (organName.equals("ORGAN_RCYY")){
			return "RC";
		}
		return "ORGAN";
	}*/
}
