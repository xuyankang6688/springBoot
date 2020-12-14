package top.javaguo.utils.pay.test.wechat;

import java.io.InputStream;

import com.github.wxpay.sdk.WXPayConfig;

/**
 * WXPay配置
 * @author 孙
 * @date 2018-08-08
 */
public class WXpayConfig implements WXPayConfig {
	
	// 正式服
	//public static String NOTIFY_URL = "http://www.rumandhouse.com/api/wxpayNotify";
	public static String NOTIFY_URL = "http://192.168.3.138:8077/api/wxpayNotify";
//	@Value("${appid}")
//	private String appId;
//
//	@Value("${wx_key}")
//	private String key;
//
//	@Value("${mchID}")
//	private String mechID;

//	private byte[] certData;
//	
//	public WXpayConfig() throws Exception {
//        String certPath = "/path/to/apiclient_cert.p12";
//        File file = new File(certPath);
//        InputStream certStream = new FileInputStream(file);
//        this.certData = new byte[(int) file.length()];
//        certStream.read(this.certData);
//        certStream.close();
//    }

	@Override
	public String getAppID() {
		// TODO Auto-generated method stub
		return "wxba8d971a44c1fbf1";// wuchan
	}

	@Override
	public String getMchID() {
		// TODO Auto-generated method stub
		return "1560603751";

	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
//		return "LmzY520aDMIn168woAInIruMadMAN666";
		return "4a9f2c433adcc2698ba7704faedeaf82";
	}

	@Override
	public InputStream getCertStream() {
		// TODO Auto-generated method stub


		return null;
	}

	@Override
	public int getHttpConnectTimeoutMs() {
		// TODO Auto-generated method stub
		return 8000;
	}

	@Override
	public int getHttpReadTimeoutMs() {
		// TODO Auto-generated method stub
		return 10000;
	}
	/**
	 * 退款密钥
	 */
	public String refundKey;

	public String getRefundKey() {
		return refundKey;
	}

	public void setRefundKey(String refundKey) {
		this.refundKey = refundKey;
	}
}
