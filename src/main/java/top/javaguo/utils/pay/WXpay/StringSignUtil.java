package top.javaguo.utils.pay.WXpay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 签名工具
 * @author zhengwei
 *
 */
public class StringSignUtil {
	private static Logger log = LoggerFactory.getLogger(StringSignUtil.class);
	
	/**
	 * 微信app支付md5签名算法
	 * @param characterEncoding
	 * @param parameters
	 * @param key
	 * @return
	 */
	public static String createSignature(String characterEncoding,Map<String, String> parameters, String key) {
        Map<String, Object> sortParams = new TreeMap<String, Object>(parameters);
		//StringBuffer sb = new StringBuffer();
		StringBuffer sbkey = new StringBuffer();
		Set<Entry<String, Object>> es = sortParams.entrySet(); // 所有参与传参的参数按照accsii排序（升序）
		Iterator<Entry<String, Object>> it = es.iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = (Entry<String, Object>) it.next();
			String k = (String) entry.getKey();// 不需要sign字段
			Object v = entry.getValue(); // 空值不传递，不参与签名组串
			if (null != v && !"".equals(v) && k != null && !"sign".equals(k)) {
				//sb.append(k + "=" + v + "&");
				sbkey.append(k + "=" + v + "&");
			}
		}
		//System.out.println("字符串:"+sb.toString());
		sbkey = sbkey.append("key=" + key);
		//System.out.println("字符串:" + sbkey.toString()); // MD5加密,结果转换为大写字符
		String sign = MD5Util.digest(sbkey.toString(), characterEncoding).toUpperCase();
		//System.out.println("MD5加密值:" + sign);
		return /*sb.toString() + "sign=" + */sign;
	}


	/**
	 * 微信app支付md5签名验签算法
	 * @param paraMap
	 * @param
	 * @return
	 */
	public static boolean checkSignature(String characterEncoding,Map<String, Object> paraMap, String key) {
		String signStr = (String) paraMap.get("sign");
		paraMap.remove("sign");

		Map<String, Object> sortParams = new TreeMap<String, Object>(paraMap);
		StringBuffer sbkey = new StringBuffer();
		Set<Entry<String, Object>> es = sortParams.entrySet(); // 所有参与传参的参数按照accsii排序（升序）
		Iterator<Entry<String, Object>> it = es.iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = (Entry<String, Object>) it.next();
			String k = (String) entry.getKey();// 不需要sign字段
			Object v = entry.getValue(); // 空值不传递，不参与签名组串
			if (null != v && !"".equals(v) && k != null && !"sign".equals(k)) {
				sbkey.append(k + "=" + v + "&");
			}
		}
		sbkey = sbkey.append("key=" + key);
//		System.out.println("字符串:" + sbkey.toString()); // MD5加密,结果转换为大写字符
		String sign = MD5Util.digest(sbkey.toString(), characterEncoding).toUpperCase();
//		System.out.println("MD5加密值:" + sign);
		
		if (signStr.equals(sign))
			return true;
		return false;
	}

	/**
	 * 通道数据格式转换
	 */
	public static Map<String, Object> convertData(String str) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		String data[] = str.split("&");
		for (int i = 0; i < data.length; i++) {
			String tmp[] = data[i].split("=");
			if ("payInfo".equals(tmp[0])) {// 交易信息是嵌套字段
				maps.put(tmp[0], tmp[1] + "=" + tmp[2]);
			} else if (!"signature".equals(tmp[0]))// 不需要signature字段
				maps.put(tmp[0], tmp[1]);
		}
		return maps;
	}

	/**
	 * 无卡支付放通道签名校验 参数1=值1&参数2=值2....&key,然后MD5加密
	 * 
	 * @author zhengwei
	 */
	public static boolean checkSign(Map<String, Object> paraMap, String keys) {
		String sourceStr = (String) paraMap.get("signature");
		paraMap.remove("signature");
		StringBuffer buff = new StringBuffer();
		for (String key : paraMap.keySet()) {
			if(!HCBoolean.isEmpty(paraMap.get(key))){//值为空不参与签名
				buff.append(key).append("=").append(paraMap.get(key)).append("&");
			}
		}
		buff.append(keys);
		String encrytStr = MD5Util.digest(buff.toString());
		if (encrytStr.equals(sourceStr))
			return true;
		return false;
	}

	/**
	 * 无卡支付放通道计算签名值
	 * 
	 * @author zhengwei
	 */
	public static String getSign(Map<String, Object> paraMap, String keys) {
		log.debug("参与签名的参数:{}", paraMap);
		StringBuffer buff = new StringBuffer();
		for (String key : paraMap.keySet()) {
			buff.append(key).append("=").append(paraMap.get(key)).append("&");
		}
		buff.append(keys);
		String encrytStr = MD5Util.digest(buff.toString());
		log.debug("MD5加密结果:{}", encrytStr);
		return encrytStr;
	}

	/**
	 * Convert a hex string to binary string. example hex 3f of % to binary
	 * 00100101
	 * 
	 * @param String
	 *            src the specified hex string
	 * @return String
	 */
	public static String convertHexToBinary(String src) {
		long lg = Long.parseLong(src, 16);
		String binaryString = Long.toBinaryString(lg);
		int shouldBinaryLen = src.length() * 4;
		StringBuffer addZero = new StringBuffer();
		int addZeroNum = shouldBinaryLen - binaryString.length();
		for (int i = 1; i <= addZeroNum; i++) {
			addZero.append("0");
		}
		return addZero.toString() + binaryString;
	}

	/**
	 * Convert a hex string to byte array
	 * 
	 * @param String
	 *            src the specified hex string
	 * @return byte[]
	 */
	public static byte[] convertHexToBytes(String src) {
		src = src.toUpperCase();
		int length = src.length() / 2;
		char[] hexChars = src.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			d[i] = (byte) (charToByte(hexChars[i * 2]) << 4 | charToByte(hexChars[i * 2 + 1]));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/**
	 * Convert a byte array to hex string
	 * 
	 * @param byte[] src the specified hex string
	 * @return String
	 */
	public static String convertBytesToHex(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * Covert ASCII(String) to hex String,like:1 to 31
	 * 
	 * @param str
	 * @return String
	 */
	public static String convertStringToHex(String str) {
		char[] chars = str.toCharArray();
		StringBuffer hex = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			hex.append(Integer.toHexString((int) chars[i]));
		}
		return hex.toString();
	}

	// 内码转汉字
	public static String hexToStringGBK(String s) {
		if (s == null)
			return "";
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		}
		try {
			s = new String(baKeyword, "GBK");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
			return "";
		}
		return s;
	}

	// 16进制字符串转ASCII
	public static String covertHexToASCII(String str) {
		if (str == null)
			return null;
		try {
			String[] ss = str.replaceAll("..", "$0 ").split(" ");
			StringBuffer sb = new StringBuffer();
			for (String d : ss) {
				sb.append((char) Integer.parseInt(d, 16));
			}
			return sb.toString();
		} catch (Exception e) {
		}
		return null;

	}

	// 判断一个字符串是否都为数字
	public static boolean isDigit(String strNum) {
		Pattern pattern = Pattern.compile("[0-9]{1,}");
		Matcher matcher = pattern.matcher((CharSequence) strNum);
		return matcher.matches();
	}
	
	public static void main(String[] args) {
		Map<String,Object> parameters = new HashMap<String,Object>();
		
		String www="www";
		String name="fffff";
		String mfrchant_id="fffff";
        String merchant_id="190010002";
        String business_type="1005";
        String out_trade_no="1400000001";
        String version="v1";
        String key="3A4BC4A4000CF1B5FFA9E351E6C1539E";
        String sign = "84E8DF7CA6D5172D699A70755CD25428";
        
        parameters.put("www",www);
        parameters.put("name", name);
        parameters.put("mfrchant_id", mfrchant_id);
        parameters.put("merchant_id", merchant_id);
        parameters.put("business_type", business_type);
        parameters.put("out_trade_no",out_trade_no);
        parameters.put("version",version);
        parameters.put("sign",sign);
        
        String characterEncoding = "UTF-8"; //指定字符集UTF-8
        //String mySign = StringSignUtil.createSignature(characterEncoding,parameters,key);
        //System.out.println("我 的签名是："+mySign);
        
        boolean b = StringSignUtil.checkSignature(characterEncoding,parameters,key);
//        System.out.println(b);
	}
}