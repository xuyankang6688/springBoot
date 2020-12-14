package top.javaguo.utils.pay.test.alipay;

 
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
/**
 * 支付宝退款接口
 * @author Administrator
 *
 */
public class AlipayRefund {
	
	
    //支付宝退款请求的网关
   // private static String requestUrl = "https://openapi.alipay.com/gateway.do";
    //用户自己生成的私钥
    
    /**
     * 支付宝退款接口
     * @param orderId 订单id
     * @param price 退款金额
     * @param afterId 退款申请id
     * @return
     */
    @SuppressWarnings("static-access")
	public static AlipayTradeRefundResponse refundOrder(String orderId,String price,String afterId){
	  	System.out.println("开始调用支付宝加密******************************************************");
	  	AlipayConfig config = new AlipayConfig();
	  	//实例化客户端
	    	AlipayClient alipayClient = new DefaultAlipayClient(config.URL,
	    			config.APP_ID,
	    			config.APP_PRIVATE_KEY,
	    			"json",
	    			"UTF-8",
	    			config.ALIPAY_PUBLIC_KEY,
	    			"RSA2");
	    	//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
	    	AlipayTradeRefundModel refundModel = new AlipayTradeRefundModel();
//	    	refundModel.setTradeNo(orderId);
	    	refundModel.setOutTradeNo(orderId);//商户订单号
	    	refundModel.setRefundAmount(price);//退款金额
	    	refundModel.setRefundReason("商品退款");
	    	refundModel.setOutRequestNo(afterId);//部分退款时必传-退款单号
	    	//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
	    	AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
	    	request.setBizModel(refundModel);
	    	try{
	    		AlipayTradeRefundResponse response = alipayClient.execute(request);
	    		System.out.println(response.getCode());
	    		System.out.println(response.getMsg()+"\n");
	    		System.out.println(response.getBody());
	    		String code = response.getCode();
	    		String msg = response.getMsg();
	    		System.out.println("code:"+code);
	    		System.out.println("msg:"+msg);
	    		return response;
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
    	return null;
  	}
  
  	public static void main(String[] arg){
  		AlipayTradeRefundResponse res = refundOrder("HP2019101909464344722","88","");
  		System.out.println(res.toString());
  	}
}
