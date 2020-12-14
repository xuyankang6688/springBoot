package top.javaguo.utils.pay.test.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;

import java.util.Random;
import java.util.UUID;

/**
 * Alipay支付
 * 
 * @author 孙
 * @date 2018-08-08
 */
public class Alipay {

	/**
	 * App支付 参考：https://docs.open.alipay.com/204/105465/
	 * 
	 * @param subject
	 *            商品的标题/交易标题/订单标题/订单关键字等。
	 * @param outTradeNo
	 *            商户网站唯一订单号
	 * @param totalAmount
	 *            订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
	 * @return 字段注释 subject String 是 256 商品的标题/交易标题/订单标题/订单关键字等。 大乐透 out_trade_no
	 *         String 是 64 商户网站唯一订单号 70501111111S001111119 total_amount String 是 9
	 *         订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000] 9.00 product_code String 是
	 *         64 销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY QUICK_MSECURITY_PAY
	 */
	public static String doAppPay(String subject, String outTradeNo, String totalAmount) {
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APP_ID,
				AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,
				AlipayConfig.SIGN_TYPE);
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		Random r=new Random();
		/*request.setBizContent("  {" + "    \"subject\":\"" + subject + "\"," + "    \"out_trade_no\":\"" + outTradeNo
				+ "\"," + "    \"total_amount\":\"" + totalAmount + "\","
				+ "    \"product_code\":\"QUICK_MSECURITY_PAY\"" + "  }");
		request.setNotifyUrl(AlipayConfig.NOTIFY_URL);
		;*/
		//商户订单号，商户网站订单系统中唯一订单号，必填
		//生成随机Id
		String out_trade_no = UUID.randomUUID().toString();
		//付款金额，必填
		String total_amount =Integer.toString(r.nextInt(9999999)+1000000);
		//订单名称，必填
		String subject1 ="奥迪A8 2016款 A8L 60 TFSl quattro豪华型";
		//商品描述，可空
		String body = "尊敬的会员欢迎购买奥迪A8 2016款 A8L 60 TFSl quattro豪华型";
		request.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
				+ "\"total_amount\":\""+ total_amount +"\","
				+ "\"subject\":\""+ subject +"\","
				+ "\"body\":\""+ body +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
	String str = "";
		try {
			str = alipayClient.sdkExecute(request).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		System.out.println("\n==========>App支付resp：\n" + str);
		return str;
	}

	/**
	 * 手机网站支付 参考：https://docs.open.alipay.com/203/107090/
	 * 
	 * @param subject
	 *            商品的标题/交易标题/订单标题/订单关键字等。
	 * @param outTradeNo
	 *            商户网站唯一订单号
	 * @param totalAmount
	 *            订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
	 * @return 字段注释 subject String 是 256 商品的标题/交易标题/订单标题/订单关键字等。 大乐透 out_trade_no
	 *         String 是 64 商户网站唯一订单号 70501111111S001111119 total_amount Price 是 9
	 *         订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000] 9.00 product_code String 是
	 *         64 销售产品码，商家和支付宝签约的产品码。该产品请填写固定值：QUICK_WAP_WAY QUICK_WAP_WAY
	 */
	public static String doWapPay(String subject, String outTradeNo, String totalAmount) {
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APP_ID,
				AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,
				AlipayConfig.SIGN_TYPE);
		AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
		request.setBizContent("  {" + "    \"subject\":\"" + subject + "\"," + "    \"out_trade_no\":\"" + outTradeNo
				+ "\"," + "    \"total_amount\":\"" + totalAmount + "\"," + "    \"product_code\":\"QUICK_WAP_PAY\""
				+ "  }");
		request.setReturnUrl("https://www.baidu.com/");
		request.setNotifyUrl(AlipayConfig.NOTIFY_URL);
		String str = "";
		try {
			str = alipayClient.pageExecute(request).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		System.out.println("\n==========>手机网站支付resp：\n" + str);
		return str;
	}

	/**
	 * 电脑网站支付 参考：https://docs.open.alipay.com/270/alipay.trade.page.pay
	 * 
	 * @param subject
	 *            订单标题
	 * @param outTradeNo
	 *            商户订单号，64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复
	 * @param totalAmount
	 *            订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
	 * @return 字段注释 subject String 是 256 订单标题 Iphone6 16G out_trade_no String 是 64
	 *         商户订单号，64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复 20150320010101001 total_amount
	 *         Price 是 11 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000] 88.88
	 *         product_code String 是 64 销售产品码，与支付宝签约的产品码名称。
	 *         注：目前仅支持FAST_INSTANT_TRADE_PAY FAST_INSTANT_TRADE_PAY
	 */
	public static String doPagePay(String subject, String outTradeNo, String totalAmount,String returnUrl) {
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APP_ID,
				AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,
				AlipayConfig.SIGN_TYPE);
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		request.setBizContent("  {" + "    \"subject\":\"" + subject + "\"," + "    \"out_trade_no\":\"" + outTradeNo
				+ "\"," + "    \"total_amount\":\"" + totalAmount + "\","
				+ "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"" + "  }");
		request.setReturnUrl(returnUrl);
		request.setNotifyUrl(AlipayConfig.NOTIFY_URL);
		String str = "";
		try {
			str = alipayClient.pageExecute(request).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		System.out.println("\n==========>电脑网站支付resp：\n" + str);
		return str;
	}

	/**
	 * 单笔转账到支付宝账户
	 * 参考：https://docs.open.alipay.com/api_28/alipay.fund.trans.toaccount.transfer
	 * 
	 * @param outBizNo
	 *            商户转账唯一订单号。发起转账来源方定义的转账单据ID，用于将转账回执通知给来源方。不同来源方给出的ID可以重复，同一个来源方必须保证其ID的唯一性。只支持半角英文、数字，及“-”、“_”。
	 * @param payeeAccount
	 *            收款方账户类型。可取值：1、ALIPAY_USERID：支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。2、ALIPAY_LOGONID：支付宝登录号，支持邮箱和手机号格式。
	 * @param amount
	 *            转账金额，单位：元。只支持2位小数，小数点前最大支持13位，金额必须大于等于0.1元。最大转账金额以实际签约的限额为准。
	 * @return 字段注释 out_biz_no String 必选 64
	 *         商户转账唯一订单号。发起转账来源方定义的转账单据ID，用于将转账回执通知给来源方。不同来源方给出的ID可以重复，同一个来源方必须保证其ID的唯一性。只支持半角英文、数字，及“-”、“_”。
	 *         3142321423432 payee_type String 必选 20
	 *         收款方账户类型。可取值：1、ALIPAY_USERID：支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。2、ALIPAY_LOGONID：支付宝登录号，支持邮箱和手机号格式。
	 *         ALIPAY_LOGONID payee_account String 必选 100
	 *         收款方账户。与payee_type配合使用。付款方和收款方不能是同一个账户。 abc@sina.com amount String 必选
	 *         16 转账金额，单位：元。只支持2位小数，小数点前最大支持13位，金额必须大于等于0.1元。最大转账金额以实际签约的限额为准。 12.23
	 *         payer_show_name String 可选 100
	 *         付款方姓名（最长支持100个英文/50个汉字）。显示在收款方的账单详情页。如果该字段不传，则默认显示付款方的支付宝认证姓名或单位名称。
	 *         上海交通卡退款 payee_real_name String 可选 100
	 *         收款方真实姓名（最长支持100个英文/50个汉字）。如果本参数不为空，则会校验该账户在支付宝登记的实名是否与收款方真实姓名一致。 张三
	 *         remark String 可选 200
	 *         转账备注（支持200个英文/100个汉字）。当付款方为企业账户，且转账金额达到（大于等于）50000元，remark不能为空。收款方可见，会展示在收款用户的收支详情中。
	 *         转账备注
	 */
	public static boolean doTransfer(String outBizNo, String payeeAccount, String amount) {
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APP_ID,
				AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,
				AlipayConfig.SIGN_TYPE);
		AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
		request.setBizContent(
				"  {" + "    \"out_biz_no\":\"" + outBizNo + "\"," + "    \"payee_type\":\"ALIPAY_LOGONID\","
						+ "    \"payee_account\":\"" + payeeAccount + "\"," + "    \"amount\":\"" + amount + "\"" +
						// " \"payer_show_name\":\"上海交通卡退款\"," +
						// " \"payee_real_name\":\"张三\"," +
						// " \"remark\":\"转账备注\"" +
						"  }");
		boolean flag = false;
		try {
			AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
			if (response.isSuccess()) {
				flag = true;
			} else {
				System.out.println("\n错误码：" + response.getSubCode());
				System.out.println("错误描述：" + response.getSubMsg());
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		System.out.println("\n==========>单笔转账到支付宝账户resp：\n" + flag);
		return flag;
	}

	/**
	 * 扫码支付
	 * 
	 * @param subject
	 * @param outTradeNo
	 * @param totalAmount
	 * @return
	 */
	public static String doPrecreate(String subject, String outTradeNo, String totalAmount) {
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APP_ID,
				AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,
				AlipayConfig.SIGN_TYPE);
		AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
		request.setBizContent("  {" + "    \"subject\":\"" + subject + "\"," + "    \"out_trade_no\":\"" + outTradeNo
				+ "\"," + "    \"total_amount\":\"" + totalAmount + "\"" + "  }");
		request.setNotifyUrl(AlipayConfig.NOTIFY_URL);
		String str = "";
		try {
			str = alipayClient.execute(request).getQrCode();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		System.out.println("\n==========>扫码支付resp：\n" + str);
		return str;
	}

	//public static void main(String[] args) {
		// doAppPay("App支付test", "outTradeNo121213456", "0.01");
		//doWapPay("手机网站支付test", "outTradeNo123456", "0.01");
		// doPagePay("电脑网站支付test", "outTradeNo123456", "0.01");
		// doTransfer("3142321142343211", "1313007@zju.edu.cn", "0.01");
		// doPrecreate("扫码支付", "No1234567890", "0.01");
	//}
	/**
	 * 查询订单信息接口
	 *
	 * @param
	 * @return
	 * @throws AlipayApiException
	 */
	public static String doQueryPay() throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient(top.javaguo.utils.pay.alipay.AlipayConfig.URL, top.javaguo.utils.pay.alipay.AlipayConfig.APP_ID,
				top.javaguo.utils.pay.alipay.AlipayConfig.APP_PRIVATE_KEY, top.javaguo.utils.pay.alipay.AlipayConfig.FORMAT, top.javaguo.utils.pay.alipay.AlipayConfig.CHARSET, top.javaguo.utils.pay.alipay.AlipayConfig.ALIPAY_PUBLIC_KEY,
				top.javaguo.utils.pay.alipay.AlipayConfig.SIGN_TYPE);
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		request.setBizContent("{" +
				"\"out_trade_no\":\"HP2019101909464344722\"," +
				"\"trade_no\":\"\"," +
				"\"org_pid\":\"\"," +
				"      \"query_options\":[" +
				"        \"\"" +
				"      ]" +
				"  }");
		AlipayTradeQueryResponse response = alipayClient.execute(request);
		if (response.isSuccess()) {
			System.out.println("调用成功=======================:"+response.getBody());
		} else {
			System.out.println("调用失败=======================:"+response.getBody());
		}
		return response.getBody();
	}
	public static void main(String[] args) throws AlipayApiException {
		doQueryPay();



	}

}
