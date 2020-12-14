package top.javaguo.utils.pay.test.alipay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.internal.util.AlipaySignature;

/**
 * Alipay通知
 *
 * @author 孙
 * @date 2017-12-25
 */
@RestController
@RequestMapping("/system/api")
@SuppressWarnings("rawtypes")
public class AlipayNotify {

	protected String bathUrl = "http://www.rumandhouse.com/static/weixin/html";

//	@Autowired
//	private PayhistoryService payhistoryService;
//	/** 支付 **/
//	@Autowired
//	private BalancePay balancePay;


	/**
	 * Alipay通知 通知参数说明：https://docs.open.alipay.com/204/105301/
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "alipayNotify")
	public void alipayNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("\n====================>Alipay Notify");
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		System.out.println("====================>params：" + params);
		// ————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET,
				AlipayConfig.SIGN_TYPE);// 调用SDK验证签名
		if (signVerified) {
			// 验签成功
			System.out.println("====================>验签成功");
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");// 交易状态
			if ("TRADE_SUCCESS".equals(trade_status) || "TRADE_FINISHED".equals(trade_status)) {
				// 支付成功 || 交易完成
				System.out.println("====================>支付成功 || 交易完成");
				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");// 商户网站唯一订单号
				String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");// 订单金额
				System.out.println("====================>商户订单号：" + out_trade_no);
				System.out.println("====================>订单总金额：" + total_amount + "（元）");

//				Payhistory tpayhistory = payhistoryService.selectById(out_trade_no);// 支付记录
//				if (tpayhistory != null && "1".equals(tpayhistory.getMethod()) && "2".equals(tpayhistory.getIspay())) {// 支付记录：!=null;//
//					Double totalYuan = Double.parseDouble(total_amount);// 订单总金额（元）
//					System.out.println("\n====================>订单总金额：" + totalYuan + "（元）");
//					/**
//					 * 逻辑代码
//					 */
//					
//					balancePay.payFinish(tpayhistory);
//
//					//发送消息到页面上
//					WebSocketServer.sendInfo("支付成功,"+tpayhistory.getScene()+"",out_trade_no);
//
//					// 修改支付记录表状态
//					tpayhistory.setIspay("1");
//					System.out.println("更新记录");
//					payhistoryService.updateById(tpayhistory);
//
//				}
			}
			String returnStr = "success";
			response.getWriter().write(returnStr);
		} else {
			String returnStr = "failure";
			response.getWriter().write(returnStr);
		}
	}
}
