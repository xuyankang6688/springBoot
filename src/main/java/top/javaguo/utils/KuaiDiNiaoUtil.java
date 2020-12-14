package top.javaguo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import top.javaguo.biz.system.bean.dto.Traces;


/**
 *
 * 快递鸟物流轨迹即时查询接口
 *
 * 
 * @see: http://www.kdniao.com/YundanChaxunAPI.aspx
 *
 * DEMO中的电商ID与私钥仅限测试使用，正式环境请单独注册账号
 * 单日超过500单查询量，建议接入我方物流轨迹订阅推送接口
 * 
 * ID和Key请到官网申请：http://www.kdniao.com/ServiceApply.aspx
 */
public class KuaiDiNiaoUtil {

        //DEMO
        public static void main(String[] args) {
        	KuaiDiNiaoUtil api = new KuaiDiNiaoUtil();
            try {
                String result = api.getOrderTracesByJson("STO", "773003595024063");//第一个参数是快递公司的编号，第二个是快递号码
            	//String result = api.getOrderTracesByJson("1165899722962");
//            	String result = api.getOrderTracesByJson("3366936746459");
            	//String result = api.getOrderTracesByJson("802912169232149136");
                System.out.print(result);
                if (result.equals("9999999")) {
					System.out.println("未查到物流信息");
				}else {
				}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	

        //电商ID
        private String EBusinessID="1450808";//挖挖叫专用
        //电商加密私钥，快递鸟提供，注意保管，不要泄漏
        private String AppKey="8bff1532-b59e-4646-9a66-9b446c02bec9";
        //请求url
        private String ReqURL="http://api.kdniao.com/Ebusiness/EbusinessOrderHandle.aspx";
        
       /* private String EBusinessID="1398725";//xks专用
      //电商加密私钥，快递鸟提供，注意保管，不要泄漏
        private String AppKey="31ef1998-6b6f-489c-a451-efb9da4d49e9";*/
        
        /**
         * Json方式 通过 单号识别 来查找快递物流信息
         * @throws Exception
         */
        public String getOrderTracesByJson(String expNo) throws Exception{
            String requestData= "{'LogisticCode':'" + expNo + "'}";
             
            Map<String, String> params = new HashMap<String, String>();
            params.put("RequestData", urlEncoder(requestData, "UTF-8"));
            params.put("EBusinessID", EBusinessID);
            params.put("RequestType", "2002");
            String dataSign=encrypt(requestData, AppKey, "UTF-8");
            params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
            params.put("DataType", "2");
             
            String result=sendPost(ReqURL, params);
            	JSONObject parseObject = JSONObject.parseObject(result);
            	Boolean success = parseObject.getBoolean("Success");
            	if (success) {
            		String string = parseObject.getString("Shippers");
            		if(string==null||string==""||string.equals("[]"))return "9999999";
            	 JSONArray jsonArray = parseObject.getJSONArray("Shippers");
            	Object object = jsonArray.get(0);
            	JSONObject parseObject2 = JSONObject.parseObject(object.toString());
            	 String expCode = parseObject2.getString("ShipperCode");
            	 System.out.println("查询快递公司编号是是："+expCode);
            	 
            		 KuaiDiNiaoUtil kuaiDiNiaoUtil = new KuaiDiNiaoUtil();
            		 String orderTracesByJson = kuaiDiNiaoUtil.getOrderTracesByJson(expCode, expNo);
            		 System.out.println(orderTracesByJson.toString());
					return orderTracesByJson;
				}else {
					 return "9999999";
				}
            //根据公司业务处理返回的信息......
             
           
        }

        /**
         * Json方式 查询订单物流轨迹
         * @throws Exception 
         */
        public String getOrderTracesByJson(String expCode, String expNo) throws Exception{
            String requestData= "{'OrderCode':'','ShipperCode':'" + expCode + "','LogisticCode':'" + expNo + "'}";

            Map<String, String> params = new HashMap<String, String>();
            params.put("RequestData", urlEncoder(requestData, "UTF-8"));
            params.put("EBusinessID", EBusinessID);
            params.put("RequestType", "1002");
            String dataSign=encrypt(requestData, AppKey, "UTF-8");
            params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
            params.put("DataType", "2");

            String result=sendPost(ReqURL, params);  

            //根据公司业务处理返回的信息......

            return result;
        }

        /**
         * XML方式 查询订单物流轨迹
         * @throws Exception 
         */
        public String getOrderTracesByXml() throws Exception{
            String requestData= "<?xml version=\"1.0\" encoding=\"utf-8\" ?>"+
                                "<Content>"+
                                "<OrderCode></OrderCode>"+
                                "<ShipperCode>SF</ShipperCode>"+
                                "<LogisticCode>589707398027</LogisticCode>"+
                                "</Content>";

            Map<String, String> params = new HashMap<String, String>();
            params.put("RequestData", urlEncoder(requestData, "UTF-8"));
            params.put("EBusinessID", EBusinessID);
            params.put("RequestType", "1002");
            String dataSign=encrypt(requestData, AppKey, "UTF-8");
            params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
            params.put("DataType", "1");

            String result=sendPost(ReqURL, params);  

            //根据公司业务处理返回的信息......

            return result;
        }

        /**
         * MD5加密
         * @param str 内容       
         * @param charset 编码方式
         * @throws Exception 
         */
        @SuppressWarnings("unused")
        private String MD5(String str, String charset) throws Exception {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes(charset));
            byte[] result = md.digest();
            StringBuffer sb = new StringBuffer(32);
            for (int i = 0; i < result.length; i++) {
                int val = result[i] & 0xff;
                if (val <= 0xf) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(val));
            }
            return sb.toString().toLowerCase();
        }

        /**
         * base64编码
         * @param str 内容       
         * @param charset 编码方式
         * @throws UnsupportedEncodingException 
         */
        private String base64(String str, String charset) throws UnsupportedEncodingException{
            String encoded = base64Encode(str.getBytes(charset));
            return encoded;    
        }  

        @SuppressWarnings("unused")
        private String urlEncoder(String str, String charset) throws UnsupportedEncodingException{
            String result = URLEncoder.encode(str, charset);
            return result;
        }

        /**
         * 电商Sign签名生成
         * @param content 内容   
         * @param keyValue Appkey  
         * @param charset 编码方式
         * @throws UnsupportedEncodingException ,Exception
         * @return DataSign签名
         */
        @SuppressWarnings("unused")
        private String encrypt (String content, String keyValue, String charset) throws UnsupportedEncodingException, Exception
        {
            if (keyValue != null)
            {
                return base64(MD5(content + keyValue, charset), charset);
            }
            return base64(MD5(content, charset), charset);
        }

         /**
         * 向指定 URL 发送POST方法的请求     
         * @param url 发送请求的 URL    
         * @param params 请求的参数集合     
         * @return 远程资源的响应结果
         */
        @SuppressWarnings("unused")
        private String sendPost(String url, Map<String, String> params) {
            OutputStreamWriter out = null;
            BufferedReader in = null;        
            StringBuilder result = new StringBuilder(); 
            try {
                URL realUrl = new URL(url);
                HttpURLConnection conn =(HttpURLConnection) realUrl.openConnection();
                // 发送POST请求必须设置如下两行
                conn.setDoOutput(true);
                conn.setDoInput(true);
                // POST方法
                conn.setRequestMethod("POST");
                // 设置通用的请求属性
                conn.setRequestProperty("accept", "*/*");
                conn.setRequestProperty("connection", "Keep-Alive");
                conn.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.connect();
                // 获取URLConnection对象对应的输出流
                out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
                // 发送请求参数            
                if (params != null) {
                      StringBuilder param = new StringBuilder(); 
                      for (Map.Entry<String, String> entry : params.entrySet()) {
                          if(param.length()>0){
                              param.append("&");
                          }                 
                          param.append(entry.getKey());
                          param.append("=");
                          param.append(entry.getValue());                   
                          //System.out.println(entry.getKey()+":"+entry.getValue());
                      }
                      //System.out.println("param:"+param.toString());
                      out.write(param.toString());
                }
                // flush输出流的缓冲
                out.flush();
                // 定义BufferedReader输入流来读取URL的响应
                in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
            } catch (Exception e) {            
                e.printStackTrace();
            }
            //使用finally块来关闭输出流、输入流
            finally{
                try{
                    if(out!=null){
                        out.close();
                    }
                    if(in!=null){
                        in.close();
                    }
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            }
            return result.toString();
        }


        private static char[] base64EncodeChars = new char[] { 
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
            'w', 'x', 'y', 'z', '0', '1', '2', '3', 
            '4', '5', '6', '7', '8', '9', '+', '/' }; 

        public static String base64Encode(byte[] data) { 
            StringBuffer sb = new StringBuffer(); 
            int len = data.length; 
            int i = 0; 
            int b1, b2, b3; 
            while (i < len) { 
                b1 = data[i++] & 0xff; 
                if (i == len) 
                { 
                    sb.append(base64EncodeChars[b1 >>> 2]); 
                    sb.append(base64EncodeChars[(b1 & 0x3) << 4]); 
                    sb.append("=="); 
                    break; 
                } 
                b2 = data[i++] & 0xff; 
                if (i == len) 
                { 
                    sb.append(base64EncodeChars[b1 >>> 2]); 
                    sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]); 
                    sb.append(base64EncodeChars[(b2 & 0x0f) << 2]); 
                    sb.append("="); 
                    break; 
                } 
                b3 = data[i++] & 0xff; 
                sb.append(base64EncodeChars[b1 >>> 2]); 
                sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]); 
                sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]); 
                sb.append(base64EncodeChars[b3 & 0x3f]); 
            } 
            return sb.toString(); 
        }

    	/**物流信息字符转转成物流对象*/
    	public static List<Traces> queryLoginstics(String str) {
    		if(str.equals("9999999"))return null;
    		String string = JSON.parseObject(str).getString("Traces");
    		List<Traces> list = JSON.parseArray(string, Traces.class);
    		Collections.reverse(list);
    		return list;
    	}
    }
