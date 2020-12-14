package top.javaguo.utils.pay.test.wechat;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import top.javaguo.utils.GuoHttpRequestUtil;
import top.javaguo.utils.pay.WXpay.WXPayTools;

/**
 * WXPay支付
 * 
 * @author 孙
 * @date 2018-08-08
 */
public class WXpay {

	/**
	 * JSAPi支付 appid 应用ID 是 String(32) wxd678efh567hg6787 微信开放平台审核通过的应用APPID mch_id
	 * 商户号 是 String(32) 1230000109 微信支付分配的商户号 nonce_str 随机字符串 是 String(32)
	 * 5K8264ILTKCH16CQ2502SI8ZNMTM67VS 随机字符串，不长于32位。推荐随机数生成算法 sign 签名 是 String(32)
	 * C380BEC2BFD727A4B6845133519F3AD6 签名，详见签名生成算法 body 商品描述 是 String(128)
	 * 腾讯充值中心-QQ会员充值
	 * 商品描述交易字段格式根据不同的应用场景按照以下格式：APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值。 out_trade_no
	 * 商户订单号 是 String(32) 20150806125346 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@
	 * ，且在同一个商户号下唯一。详见商户订单号 total_fee 总金额 是 Int 888 订单总金额，单位为分，详见支付金额
	 * spbill_create_ip 终端IP 是 String(16) 123.12.12.123 用户端实际ip notify_url 通知地址 是
	 * String(256) http://www.weixin.qq.com/wxpay/pay.php
	 * 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。 openid 用户标识String(128) 是
	 * oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
	 * trade_type=JSAPI时（即公众号支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识。openid如何获取，可参考【获取openid】。企业号请使用【企业号OAuth2.0接口】获取企业号内成员userid，再调用【企业号userid转openid接口】进行转换
	 * discountConsumeId 优惠券使用id type 调用支付的地方 1 支付学费 2商品购买
	 * 
	 * @throws Exception
	 */
	public static Map<String, String> doJSAPIPay(String body, String outTradeNo, String totalFee, String openId,
			String spbill_create_ip) throws Exception {

		WXpayConfig config = new WXpayConfig();
		// WXPay wxpay = new WXPay(config);
		Map<String, String> data = new HashMap<String, String>();
		data.put("appid", config.getAppID());
		data.put("body", body);
		data.put("mch_id", config.getMchID());
		String nonecStr = com.github.wxpay.sdk.WXPayUtil.generateNonceStr();
		data.put("nonce_str", nonecStr);
		data.put("openid", openId);
		data.put("out_trade_no", outTradeNo);
		data.put("spbill_create_ip", spbill_create_ip);
		data.put("total_fee", (int) (new BigDecimal(totalFee).multiply(new BigDecimal("100")).doubleValue()) + "");
		data.put("trade_type", "JSAPI");
		data.put("sign_type", "MD5");
		data.put("notify_url", WXpayConfig.NOTIFY_URL);
		System.out.println(data.toString());
		String sign = WXPayUtil.generateSignature(data, config.getKey());
		data.put("sign", sign);
		System.out.println("\\sign==========>" + sign);
		String xml = com.github.wxpay.sdk.WXPayUtil.mapToXml(data);// 将所有参数(map)转xml格式
		// 统一下单 https://api.mch.weixin.qq.com/pay/unifiedorder
		String unifiedorder_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		/*
		 * Map<String, String> unifiedOrder = wxpay.unifiedOrder(data);
		 * System.out.println(unifiedorder_url);return unifiedOrder;
		 */
		String xmlStr = GuoHttpRequestUtil.sendPost(unifiedorder_url, xml);// 发送post请求"统一下单接口"返回预支付id:prepay_id
		System.out.println("\n==========>统一下单resp：" + xmlStr);
		try {
			// 以下内容是返回前端页面的json数据
			String prepay_id = "";// 预支付id
			if (xmlStr.indexOf("SUCCESS") != -1) {
				Map<String, String> map = WXPayUtil.xmlToMap(xmlStr);
				prepay_id = (String) map.get("prepay_id");
			}
			Map<String, String> payMap = new HashMap<String, String>();
			payMap.put("appId", config.getAppID());
			payMap.put("timeStamp", new Date().getTime() + "");
			payMap.put("nonceStr", com.github.wxpay.sdk.WXPayUtil.generateNonceStr());
			payMap.put("signType", "MD5");
			payMap.put("package", "prepay_id=" + prepay_id);
			String paySign = WXPayUtil.generateSignature(payMap, config.getKey());
			payMap.put("paySign", paySign);
			payMap.put("success", "ok");
			System.out.println("\n==========>调起支付resp：" + payMap);

			return payMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * App支付 参考：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1
	 * 
	 *  body
	 *            商品描述交易字段格式根据不同的应用场景按照以下格式：APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值。
	 *  outTradeNo
	 *            商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一。详见商户订单号
	 *  totalFee
	 *            订单总金额，单位为分，详见支付金额
	 * @return
	 * @throws Exception
	 *             字段注释 appid 应用ID 是 String(32) wxd678efh567hg6787
	 *             微信开放平台审核通过的应用APPID mch_id 商户号 是 String(32) 1230000109 微信支付分配的商户号
	 *             nonce_str 随机字符串 是 String(32) 5K8264ILTKCH16CQ2502SI8ZNMTM67VS
	 *             随机字符串，不长于32位。推荐随机数生成算法 sign 签名 是 String(32)
	 *             C380BEC2BFD727A4B6845133519F3AD6 签名，详见签名生成算法 body 商品描述 是
	 *             String(128) 腾讯充值中心-QQ会员充值
	 *             商品描述交易字段格式根据不同的应用场景按照以下格式：APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值。
	 *             out_trade_no 商户订单号 是 String(32) 20150806125346
	 *             商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。详见商户订单号
	 *             total_fee 总金额 是 Int 888 订单总金额，单位为分，详见支付金额 spbill_create_ip 终端IP 是
	 *             String(16) 123.12.12.123 用户端实际ip notify_url 通知地址 是 String(256)
	 *             http://www.weixin.qq.com/wxpay/pay.php
	 *             接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。 trade_type 交易类型 是
	 *             String(16) APP 支付类型
	 */
	public static Map<String, String> doAppPay() throws Exception {
		// WXpayConfig config = new WXpayConfig();
		AppWXpayConfig config = new AppWXpayConfig();
		System.out.println(config.getAppID());
		System.out.println(config.getMchID());
		System.out.println(config.getKey());
		WXPay wxpay = new WXPay(config);
		Map<String, String> data = new HashMap<String, String>();
		data.put("body"," orderPayParm.getBody()");
		data.put("out_trade_no", "orderPayParm.getOut_trade_no()");
		data.put("total_fee", (int) (new BigDecimal("orderPayParm.getTotal_amount()").multiply(new BigDecimal("100")).doubleValue()) + "");
		data.put("spbill_create_ip", "0.0.0.0");
		data.put("notify_url", AppWXpayConfig.NOTIFY_URL);
		data.put("trade_type", "APP");
		try {
			Map<String, String> resp = wxpay.unifiedOrder(data);
			System.out.println("\n==========>统一下单resp：" + resp);
			/**
			 * 出参app
			 * 调起支付接口：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_12&index=2
			 */
			Map<String, String> resultMap = new LinkedHashMap<String, String>();
			resultMap.put("appid", config.getAppID()); // 应用ID
			resultMap.put("partnerid", config.getMchID()); // 商户号
			resultMap.put("prepayid", resp.get("prepay_id")); // 预支付交易会话ID
			resultMap.put("package", "Sign=WXPay"); // 扩展字段
			resultMap.put("noncestr", WXPayUtil.generateNonceStr()); // 随机字符串
			resultMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000)); // 时间戳
			resultMap.put("sign", WXPayUtil.generateSignature(resultMap, config.getKey())); // 签名
			System.out.println("\n==========>调起支付resp：" + resultMap);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public static String httpPostRequest( String requestStr) throws IOException {
		String url="https://api.mch.weixin.qq.com/pay/profitsharingaddreceiver";
		HttpClientBuilder httpClientBuilder = HttpClients.custom();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new StringEntity(new String(requestStr.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1)));
		try (CloseableHttpClient httpclient = httpClientBuilder.build()) {
			httpPost.setEntity(new StringEntity(new String(requestStr.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1)));
			try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
				String responseString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
				System.out.println("【请求地址】==："+url+"【请求数据】：=="+requestStr+"【响应数据】==:"+responseString);
				return responseString;
			}
		}catch (Exception e){
			System.out.println("【请求地址】==："+url+"【请求数据】：=="+requestStr+"【异常信息】==:"+e.getMessage());
		}
		finally {
			httpPost.releaseConnection();
		}

		return null;
	}

	public static void main(String[] args) throws Exception {
		AppWXpayConfig config = new AppWXpayConfig();
		Map<String,String> xmlMap= new HashMap<>();
		xmlMap.put("mch_id",config.getMchID());
		xmlMap.put("appid",config.getAppID());
		xmlMap.put("nonce_str",WXPayUtil.generateNonceStr());
		xmlMap.put("receiver","{\"type\":\"PERSONAL_WECHATID\",\"account\":\"18710056738\",\"relation_type\":\"SUPPLIER\",\"name\":\"徐燕康\"}");
		xmlMap.put("sign",WXPayUtil.generateSignature(xmlMap, config.getKey()));
		String xml=WXPayTools.parseMapXML(xmlMap);
		System.out.println("iiii==:"+WXPayTools.parseMapXML(xmlMap));
		httpPostRequest(xml );


	}













	/**
	 * 扫码支付 参考：https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
	 * 
	 * @param body
	 *            商品简单描述，该字段请按照规范传递，具体请见参数规定
	 * @param outTradeNo
	 *            商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一。详见商户订单号
	 * @param totalFee
	 *            订单总金额，单位为分，详见支付金额
	 * @return
	 * @throws Exception
	 *             字段注释 appid 公众账号ID 是 String(32) wxd678efh567hg6787
	 *             微信支付分配的公众账号ID（企业号corpid即为此appId） mch_id 商户号 是 String(32)
	 *             1230000109 微信支付分配的商户号 nonce_str 随机字符串 是 String(32)
	 *             5K8264ILTKCH16CQ2502SI8ZNMTM67VS 随机字符串，长度要求在32位以内。推荐随机数生成算法 sign
	 *             签名 是 String(32) C380BEC2BFD727A4B6845133519F3AD6
	 *             通过签名算法计算得出的签名值，详见签名生成算法 body 商品描述 是 String(128) 腾讯充值中心-QQ会员充值
	 *             商品简单描述，该字段请按照规范传递，具体请见参数规定 out_trade_no 商户订单号 是 String(32)
	 *             20150806125346 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*
	 *             且在同一个商户号下唯一。详见商户订单号 total_fee 标价金额 是 Int 88 订单总金额，单位为分，详见支付金额
	 *             spbill_create_ip 终端IP 是 String(16) 123.12.12.123
	 *             APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。 notify_url 通知地址 是
	 *             String(256) http://www.weixin.qq.com/wxpay/pay.php
	 *             异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。 trade_type 交易类型 是
	 *             String(16) JSAPI JSAPI 公众号支付、NATIVE 扫码支付、APP APP支付，说明详见参数规定
	 */
	public static String doNativePay(String body, String outTradeNo, String totalFee) throws Exception {
		WXpayConfig config = new WXpayConfig();
		WXPay wxpay = new WXPay(config);
		Map<String, String> data = new HashMap<String, String>();
		data.put("body", body);
		data.put("out_trade_no", outTradeNo);
		data.put("total_fee", (int) (new BigDecimal(totalFee).multiply(new BigDecimal("100")).doubleValue()) + "");
		data.put("spbill_create_ip", "0.0.0.0");
		data.put("notify_url", WXpayConfig.NOTIFY_URL);
		data.put("trade_type", "NATIVE");
		String str = "";
		try {
			Map<String, String> resp = wxpay.unifiedOrder(data);
			System.out.println("\n==========>统一下单resp：" + resp);
			str = resp.get("code_url");
			System.out.println("\n==========>扫码支付resp：" + str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * H5支付：unfinished....
	 * 
	 * @param body
	 * @param outTradeNo
	 * @param totalFee
	 * @param spbillCreateIp
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> doMwebPay(String body, String outTradeNo, String totalFee, String spbillCreateIp)
			throws Exception {
		WXpayConfig config = new WXpayConfig();
		WXPay wxpay = new WXPay(config);
		Map<String, String> data = new HashMap<String, String>();
		data.put("body", body);
		data.put("out_trade_no", outTradeNo);
		data.put("total_fee", (int) (new BigDecimal(totalFee).multiply(new BigDecimal("100")).doubleValue()) + "");
		data.put("spbill_create_ip", spbillCreateIp);
		data.put("notify_url", WXpayConfig.NOTIFY_URL);
		data.put("trade_type", "MWEB");
		try {
			Map<String, String> resp = wxpay.unifiedOrder(data);
			System.out.println("\n==========>统一下单resp：" + resp);
			// 出参
			Map<String, String> resultMap = new LinkedHashMap<String, String>();
			resultMap.put("mwebUrl", resp.get("mweb_url"));// mweb_url为拉起微信支付收银台的中间页面，可通过访问该url来拉起微信客户端，完成支付,mweb_url的有效期为5分钟。
			System.out.println("\n==========>H5支付resp：" + resultMap);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 微信退款
	 * @param outTradeNo  微信支付的订单号 唯一
	 * @param outRefundNo  退款的单号 唯一 不能重复
	 * @param refundFee  退款金额
	 * @param totalFee  订单总金额（订单实际支付）
	 * @param payType  支付类型
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> doWxReturn(String outTradeNo, String outRefundNo, String refundFee,
			String totalFee, String payType) throws Exception {
		WXpayConfig config = new WXpayConfig();
		WXPay wxpay = new WXPay(config);

		Map<String, String> data = new HashMap<String, String>();
		data.put("out_trade_no", outTradeNo);// 微信支付的订单号 唯一
		data.put("out_refund_no", outRefundNo);// 退款的单号 唯一 不能重复
		data.put("refund_fee", (int) (new BigDecimal(refundFee).multiply(new BigDecimal("100")).doubleValue()) + "");// 退款金额
		data.put("total_fee", (int) (new BigDecimal(totalFee).multiply(new BigDecimal("100")).doubleValue()) + "");// 订单总金额（订单实际支付）
		data.put("notify_url", AppWXpayConfig.NOTIFY_URL_BY_REFUND);// 回调地址
		// 其他的参数不需要传 因为后面封装好了
		System.out.println("\\data======" + data.toString());
		Map<String, String> refund = wxpay.refund(data);// 发起退款请求
		/**
		 * 下面这个是退款后的案例 {nonce_str=4INOLlFj0AY7jm7M, appid=wx67e052a16e3d0f5c,
		 * sign=31FA45051F16B4541BA7683A6191FBDB, err_code=ERROR, return_msg=OK,
		 * result_code=FAIL, err_code_des=订单已全额退款, mch_id=1540972391,
		 * return_code=SUCCESS} 本方法只是向微信发起退款请求，成功了只是请求成功了 不代表退款成功 是否成功要看回调方法
		 */
		System.out.println(refund.toString());
		return refund;
	}

	//public static void main(String[] args) throws Exception {
		//doAppPay("App支付test", "11234567183ewe", "0.01");
		// doNativePay("扫码支付test", "111234567890121141", "0.01");
		// doMwebPay("H5支付test", "43243243221", "0.01", "211.138.116.238");
		// String body, String outTradeNo, String totalFee,String openid, String
		// spbill_create_ip
		// doJSAPIPay("支付","1212xczx1211","100","omHcp0m59h2AgEmyQ45ayWQAC7wU","0.0.0.0");
	//}






}
