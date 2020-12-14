package top.javaguo.utils.pay.test.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
/**
 * 生成app测试字符串
 */
public class Alipaytradeapppay {

	public static void main(String[] args) {
		String APP_ID="2016101300675757";
		// 开发者应用私钥，由开发者自己生成
		  String APP_PRIVATE_KEY ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQChRm1ivQCPva1LGdDd4RpABMC1UsAyHVVcP/qpjjnqZiKCMi9fC32YdDRFEEEk6gd3JYN8UQRRl/6d8YmbLDDdUyuPbT47ZrmqDIWKstsvYGQ6XPdrk6cowzvqspWR25Afur2pR15UxBGzES58JfYhluThtrVnG46sLNr0FFSAmmBfHg9po+Q7RfrhSnXh0nRHqv9dfPQXZZS967+xVX6GFeYBjgzC1E43MkBi2piPzW35nMdItQ7qebiVztvT7wvbvU9T5LPpK+5AQfAssWrmSSEw7wkwQmQzpe/kzfTNM4OQvJAyaCjsQFbN7r+8E8v5dZZW408TPPJO4e1qNy3DAgMBAAECggEAMPA9Gzw9ImEtZ9+ymNHI9Go8zAkLV2m9UFv9xVvqSS2rBRUTeOT0vMFuZxKyoe/+SfJxPtUa2WOlGOqFljfHsDGfw0skGkRmzKo2D8uMZYj7gRZa4iMRE3aDBQoiBvu4dVhmWyrT/ibtNAhfsb6XPMQcOj4GN/6NwcNuwag1qDw02Sv7UypCAF8aC6YtDeq73nOGvZVGO/l1XoYh7O65RzI1O0OQF73AH4W4AROkHFO6mxzlBJ1bsn1fRbyLKcXQ7XRoptTPWoxevmyvyZ3wZCdC3/GC5FPSwnRGHn/9/kIngDk1mviEku8rfZa21LnN00fnYlGS2I2+TzhZ8OmPmQKBgQDUPLgulI9YUT7lkXYIyl7l6BxFuzIZkbEktN/+lec4p5OI0clQn0BQ+oIwNxi4dPUTjz89HQKivfcUxDj49Mm3KQokdnUqTIAkf7YaF2ElWAJxo3kQL6sCp9FyHv2yh43C5PH76kclXe4FtCkdpms/FcLU43webEJYwXJP1zTurwKBgQDCh5e6Wpa2pL7MKMiwpYE0OSCYsPRJbjdPbc6kRXpEgXV5EBdi2YyM7o1j3z8Su9VksKGVUyWkcUnV6v+mXaIv9K5gnlCThPaJScOV2Cvuj4d8mYSCEr7cT1PpPLetsRghQ18YGKCC4x4WL28mE2+lUh/A3mBDLKrheRAVvOCXLQKBgQCwD4bCrV6UZyGEEz0VYbuDgR8jAX04FNIcnydJ7FSZfPo4f78+8DzMcGNbV/lb2zoK9cQCEHTFSE0VnwmcDIThOlw9jz72zetnW8JWtppR/kZYusGNGd4FH+3xe5/9X9HLoVzUG67VYqw3cxLAk/NXq9ep/uRR4IDRW/hWHzK75QKBgCQI0gZnOXvj0KQo+eQfBGwUGQGv5dCkZgKEqL6tUBTYllYysOJSwkJo/i/97sUdnk3kmkn6+QIZKkBCI9FBjwWrIZIsQWjSPIj57Q0Y2ip60/I0zeL6bfiJiVWvNacrIvkmbbsc9L7KK4yYhNJcz1WkfNVRXCuVXqIdtukP79qRAoGAT6FdtYC6j2R/ptqC4Ybz0jAmjcKwpg5Qb48xeIRG8yLoTglJhgnGsd+JEanwwmeDH1r3qwqlcpWWlvLBrfVcd3Ztr/xgXMyJm0SEwz2kmjgfHJjad23a3gFWizaEBsU9C44sbLhkWwP1Qc83Gsp5AuE+xvLDdHQw8TsxtQpN1R4=";
		// 支付宝公钥，由支付宝生成
		  String ALIPAY_PUBLIC_KEY ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjqrvh0NpVGaX7Oc0DsVik1HO9800cvkGAsRoyYsX+waS3k7D1PuwsQzwOW9twfYMiyQ/fPFkEPP+Y1nh3qa8DMGaat6qI8+OSIci3MvkC09oi8gFplylFCjlqaPrtDVxZoMVVUFtDOn5fiPXBkzRX//b3Bl9u/rApnxgH7AdDSYtoe1s6Y28dhnLaVdAnfI4tvP+cwLyYFgxOiVWK+HdutQ2gmRVihuwV3fUqa5mtZk24CG1j79up6eD1rgqfvOqheWKLFnU+wcAC7VN8Hkz8k3lP2Xmg19ege7RvVmKv520r8kav2p4gKb8/0njsNFkn+77H8bdJahHXJDJd+GbqwIDAQAB";
		//签名方式
		String sign_type="RSA2";
		//编码格式
		String CHARSET="utf-8";
		//正式环境支付宝网关，如果是沙箱环境需更改成https://openapi.alipaydev.com/gateway.do
		String url="https://openapi.alipaydev.com/gateway.do";
		//String url="https://openapi.alipay.com/gateway.do";
		//实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient(url, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY,sign_type);
		//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody("我是测试数据");
		model.setSubject("App支付测试Java");
		//请保证OutTradeNo值每次保证唯一
		model.setOutTradeNo("2017090080066467895");
		model.setTimeoutExpress("15m");
		model.setTotalAmount("22");
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl("商户外网可以访问的异步地址");
		try {
			//这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
	}

}
