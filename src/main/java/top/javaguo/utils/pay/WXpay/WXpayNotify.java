package top.javaguo.utils.pay.WXpay;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;

/**
 * WXPay通知
 * 
 * @author 孙
 * @date 2018-08-08
 */

public class WXpayNotify {

	/**
	 * WXPay通知
	 * 支付结果通知：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_7&index=3
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "wxpayNotify", method = RequestMethod.POST)
	@Transactional(readOnly = false,  propagation = Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
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
		// String notifyData = "....";// 支付结果通知的xml格式数据
		Map<String, String> notifyMap = WXPayUtil.xmlToMap(notifyData);// 转换成map
		WXpayConfig config = new WXpayConfig();
		WXPay wxpay = new WXPay(config);
		if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {
			// 签名正确
			System.out.println("====================>签名正确");
			if ("SUCCESS".equals(notifyMap.get("return_code")) && "SUCCESS".equals(notifyMap.get("result_code"))) {
				// 通信成功 && 交易成功
				System.out.println("====================>通信成功 && 交易成功");
				String out_trade_no = notifyMap.get("out_trade_no");// 商户订单号
				String total_fee = notifyMap.get("total_fee"); // 订单总金额，单位为分
				System.out.println("====================>商户订单号：" + out_trade_no);
				System.out.println("====================>订单总金额：" + total_fee + "（分）");

				// 下面开始写业务逻辑
			/*	AlipayNotify alipayNotify = new AlipayNotify();
				alipayNotify.selAllMyCart(out_trade_no,total_fee);*/
			}
			String returnStr = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
			response.getWriter().write(returnStr);
		} else {
			String returnStr = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[签名失败]]></return_msg></xml>";
			response.getWriter().write(returnStr);
		}
	}

}
