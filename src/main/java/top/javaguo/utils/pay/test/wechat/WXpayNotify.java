package top.javaguo.utils.pay.test.wechat;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;

import top.javaguo.utils.md5.AESUtil;

/** 
 * WXPay通知
 * @author 孙
 * @date 2018-08-08
 */
@RestController
@RequestMapping("/api")
public class WXpayNotify {

//	@Autowired
//	private PayhistoryService payhistoryService;
//	/** 支付 **/
//	@Autowired
//	private BalancePay balancePay;

	
	/**
	 * WXPay通知
	 * 支付结果通知：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_7&index=3
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/wxpayNotify", method = RequestMethod.POST)
	@ResponseBody
	public void wxpayNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("\n====================>WXPay Notify");
		InputStream inStream = request.getInputStream();
		ByteArrayOutputStream ouStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			ouStream.write(buffer, 0, len);
		}
		String notifyData = new String(ouStream.toByteArray(), "utf-8");
		inStream.close();
		ouStream.close();
		System.out.println("====================>notifyData：" + notifyData);
		// ————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
//		String notifyData = "....";// 支付结果通知的xml格式数据
		Map<String, String> notifyMap = WXPayUtil.xmlToMap(notifyData);// 转换成map
		WXpayConfig config = new WXpayConfig();
		WXPay wxpay = new WXPay(config);
		if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {
			// 签名正确
			System.out.println("====================>签名正确");
			if("SUCCESS".equals(notifyMap.get("return_code")) && "SUCCESS".equals(notifyMap.get("result_code"))) {
				// 通信成功 && 交易成功
				System.out.println("====================>通信成功 && 交易成功");
				String out_trade_no = notifyMap.get("out_trade_no");// 商户订单号
				String total_fee = notifyMap.get("total_fee");		// 订单总金额，单位为分
				System.out.println("====================>商户订单号：" + out_trade_no);
				System.out.println("====================>订单总金额：" + total_fee + "（分）");
				
				//支付完成回调函数
//				Payhistory tpayhistory = payhistoryService.selectById(out_trade_no);// 支付记录
//				if (tpayhistory != null && "2".equals(tpayhistory.getIspay())) {// 支付记录：!=null;//
//					Double totalYuan = Double.parseDouble(total_fee);// 订单总金额（元）
//					System.out.println("\n====================>订单总金额：" + totalYuan + "（分）");
//					/**
//					 * 逻辑代码
//					 */
//					balancePay.payFinish(tpayhistory);
//
//					// 修改支付记录表状态
//					tpayhistory.setIspay("1");
//					System.out.println("更新记录");
//					payhistoryService.updateById(tpayhistory);
//
//				}

			}
			String returnStr = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
			response.getWriter().write(returnStr);
		}else {
			String returnStr = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[签名失败]]></return_msg></xml>";
			response.getWriter().write(returnStr);
		}
	}
	
	/**
	 * WXPay通知5 退款成功的回调
	 * 支付结果通知：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_7&index=3
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "wxpayNotifyByRefund", method = RequestMethod.POST)
	@ResponseBody
	public void wxpayNotifyByRefund(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("\n====================>WXPay Notify");
		InputStream inStream = request.getInputStream();
		ByteArrayOutputStream ouStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			ouStream.write(buffer, 0, len);
		}
		String notifyData = new String(ouStream.toByteArray(), "utf-8");
		inStream.close();
		ouStream.close();
		System.out.println("====================>notifyData：" + notifyData);
		// ————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
		// String notifyData = "....";// 支付结果通知的xml格式数据
		Map<String, String> notifyMap = WXPayUtil.xmlToMap(notifyData);// 转换成map
		WXpayConfig config = new WXpayConfig();
		// 签名正确
		System.out.println("====================>签名正确");
		if ("SUCCESS".equals(notifyMap.get("return_code"))) {
			// 通信成功 && 交易成功
			System.out.println("====================>通信成功");
			String req_info = notifyMap.get("req_info");// 商户订单号
			System.out.println("获得reqInfo==" + req_info);
			// 解密信息
			String decryptData = AESUtil.decryptData(req_info, config.getKey());
			Map<String, String> map = WXPayUtil.xmlToMap(decryptData);
			System.out.println("解密后的信息是===============" + map.toString());

			// 拿到解密后的订单号
			String outTradeNo = map.get("out_trade_no");
			String afterId = map.get("out_refund_no");
			String refundStatus = map.get("refund_status");
			System.out.println("outTradeNo："+outTradeNo);
			System.out.println("afterId："+afterId);
			System.out.println("refundStatus："+refundStatus);
			// ***这里写业务逻辑***//
			// ***这里是业务逻辑***//
			// 操作完成 通知微信 信息已接收成功
			String returnStr = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA]></return_msg></xml>";
			response.getWriter().write(returnStr);
		} else {
			// 支付失败
		}
	}
	
}
