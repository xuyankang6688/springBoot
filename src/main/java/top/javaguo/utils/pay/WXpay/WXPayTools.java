package top.javaguo.utils.pay.WXpay;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.security.KeyStore;
import java.util.*;

public class WXPayTools {
    private static int socketTimeout = 10000;// 连接超时时间，默认10秒
    private static int connectTimeout = 30000;// 传输超时时间，默认30秒
    private static RequestConfig requestConfig;// 请求器的配置
    private static CloseableHttpClient httpClient;// HTTP请求器
    /**
     * 通过Https往API post xml数据
     *
     * @param url API地址
     * @param xmlObj 要提交的XML数据对象
     * @param mchId 商户ID
     * @param certPath 证书位置
     * @return
     */
    public static String postData(String url, String xmlObj, String mchId, String certPath) {
        // 加载证书
        try {
            initCert(mchId, certPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        // 得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(xmlObj, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);
        // 根据默认超时限制初始化requestConfig
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
        // 设置请求器的配置
        httpPost.setConfig(requestConfig);
        try {
            HttpResponse response = null;
            try {
                response = httpClient.execute(httpPost);
            } catch (IOException e) {
                e.printStackTrace();
            }
            HttpEntity entity = response.getEntity();
            try {
                result = EntityUtils.toString(entity, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            httpPost.abort();
        }
        return result;
    }


    /**
     * 加载证书
     *
     * @param mchId 商户ID
     * @param certPath 证书位置
     * @throws Exception
     */
    private static void initCert(String mchId, String certPath) throws Exception {
        // 证书密码，默认为商户ID
        String key = mchId;
        // 证书的路径
        String path = certPath;
        // 指定读取证书格式为PKCS12
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        // 读取本机存放的PKCS12证书文件
        FileInputStream instream = new FileInputStream(new File(path));
        try {
            // 指定PKCS12的密码(商户ID)
            keyStore.load(instream, key.toCharArray());
        } finally {
            instream.close();
        }
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, key.toCharArray()).build();
        SSLConnectionSocketFactory sslsf =
                new SSLConnectionSocketFactory(sslcontext, new String[] {"TLSv1"}, null,
                        SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }

    public static String parseMapXML(Map<String, String> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            if (null != v && !"".equals(v) && !"appkey".equals(k)) {
                sb.append("<" + k + ">" + parameters.get(k) + "</" + k + ">\n");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    public static String httpPostXml(String url, String xmlContent,String encode) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = null;
        BufferedReader reader = null;
        //int i = 0;

        //while (i < 4) {
        try {
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "text/xml; charset=UTF-8");
            StringEntity strEntity = new StringEntity(xmlContent, "UTF-8");
            httpPost.setEntity(strEntity);
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                reader = new BufferedReader(new InputStreamReader(resEntity
                        .getContent(), "UTF-8"));
                StringBuffer sb = new StringBuffer();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    //sb.append("\r\n");
                }
                return sb.toString();
            }

        } catch (Exception e) {
            // i++;
            //if (i == 4) {
            //logger.error("not connect:" + url + "\n" + e.getMessage());
            //}
        } finally {
            if (httpPost != null) {
                httpPost.abort();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpclient) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //}
        return "";
    }

    /**
     * XML转map
     * @param strxml
     * @return
     * @throws DocumentException
     */
    public static Map<String, String> xmlToMap(String strxml) throws DocumentException{
        Document doc = DocumentHelper.parseText(strxml);
        Element rootElement = doc.getRootElement();
        Map<String, String> map = toMap(rootElement);
        return map;
    }
    /**
     * 转MAP
     * @author
     * @param element
     * @return
     */
    public static Map<String, String> toMap(Element element){
        Map<String, String> rest = new HashMap<String, String>();
        List<Element> els = element.elements();
        for(Element el : els){
            rest.put(el.getName().toLowerCase(), el.getTextTrim());
        }
        return rest;
    }


}
