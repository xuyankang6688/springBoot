package top.javaguo.utils.pay.test.alipay;

import java.io.IOException;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeOrderSettleRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeOrderSettleResponse;

public class AlipayMobileApp {
	/**
	 * 支付手机网站 请求参数说明：https://docs.open.alipay.com/204/105465/ body String 否 128
	 * 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。 Iphone6 16G subject String 是 256
	 * 商品的标题/交易标题/订单标题/订单关键字等。 大乐透 out_trade_no String 是 64 商户网站唯一订单号
	 * 70501111111S001111119 timeout_express String 否 6
	 * 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。
	 * 该参数数值不接受小数点， 如 1.5h，可转换为 90m。注：若为空，则默认为15d。 90m total_amount String 是 9
	 * 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000] 9.00 product_code String 是 64
	 * 销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY QUICK_MSECURITY_PAY
	 *
	 * @param subject
	 * @param outTradeNo
	 * @param totalAmount
	 * @return
	 * @throws IOException
	 * @author HWX
	 */
	public static String doPay(String subject, String outTradeNo, String totalAmount, String url) throws IOException {
		// 实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APP_ID,
				AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,
				AlipayConfig.SIGN_TYPE);
		System.out.println("开始执行方法");
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
		model.setBody("");
		model.setSubject(subject);
		model.setOutTradeNo(outTradeNo);
		model.setTimeoutExpress("60m");
		model.setTotalAmount(totalAmount);
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl(AlipayConfig.NOTIFY_URL);
		request.setReturnUrl(url);
		// form表单生产
		String form = "";
		try {
			// 调用SDK生成表单
			form = alipayClient.pageExecute(request, "GET").getBody();//调用get方法直接返回
			System.out.print(form);
			// response.reset();
			// response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
			// response.getWriter().write(form);//直接将完整的表单html输出到页面
			// response.getWriter().flush();
			// response.getWriter().close();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return form;
	}

	public static void main(String[] args) throws IOException, AlipayApiException {
		//String returnForm = doPay("test", "21112223321323131311", "1", "http://www.baidu.com");
		doSettlePay();
		//System.out.println(returnForm);
	}

	public static String doSettlePay() throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", "UTF-8", AlipayConfig.ALIPAY_PUBLIC_KEY, "RSA2");
		AlipayTradeOrderSettleRequest request = new AlipayTradeOrderSettleRequest();
		request.setBizContent("{" +
				"\"out_request_no\":\"20160727001\"," +
				"\"trade_no\":\"2014030411001007850000672009\"," +
				"      \"royalty_parameters\":[{" +
				"        \"royalty_type\":\"transfer\"," +
				"\"trans_out\":\"2088102179198718\"," +
				"\"trans_out_type\":\"userId\"," +
				"\"trans_in_type\":\"userId\"," +
				"\"trans_in\":\"2088102179198718\"," +
				"\"amount\":0.1," +
				"\"desc\":\"分账给2088102179198718\"" +
				"        }]," +
				"\"operator_id\":\"A0001\"" +
				"  }");
		AlipayTradeOrderSettleResponse response = alipayClient.execute(request);
		if (response.isSuccess()) {
			System.out.println("调用成功");
		} else {
			System.out.println("调用失败");
		}
return null;
	}








}
