package top.javaguo.utils.pay.test.wechat;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * WXPay配置
 * @author 孙
 * @date 2018-08-08
 */
public class AppWXpayConfig implements WXPayConfig {
	
	// 正式服  微信支付回調地址
	public static String NOTIFY_URL = "http://ip地址，/api/wxpayNotify";//回調接口地址
	//微信退款回調地址
	public static String NOTIFY_URL_BY_REFUND = "http://ip地址，/api/wxpayNotifyByRefund";//回調接口地址

//	@Value("${appid}")
//	private String appId;
//
//	@Value("${wx_key}")
//	private String key;
//
//	@Value("${mchID}")
//	private String mechID;

	private byte[] certData;
//	
	/*public AppWXpayConfig() throws Exception {
        String certPath = "/path/to/apiclient_cert.p12";
        File file = new File(certPath);
       InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
   }*/

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
	
}
