package top.javaguo.biz.others.qq.wx.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javaguo.biz.others.qq.wx.miniprogram.WXMiniprogramConfig;
import top.javaguo.core.publicParam.PublicParamUtil;
import top.javaguo.utils.GuoHttpRequestUtil;
import top.javaguo.utils.GuoJsonUtil;
import top.javaguo.utils.GuoStringUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信接口工具类
 *
 * @author javaGuo
 * @date 2019/03/19
 */
@Component
public class WXInterfaceUtil {

    /**
     * 微信小程序配置文件
     */
    @Autowired
    private WXMiniprogramConfig wxMiniprogramConfig;

    /** 进入事件引导语 **/
    public String getEventGuideMsg() {
        return PublicParamUtil.map.get("WXInterfaceUtil_eventGuideMsg") == null ? "" : PublicParamUtil.map.get("WXInterfaceUtil_eventGuideMsg");
    }

    /**
     * 获取微信access_token
     */
    public String getAccessToken() {
        String result = GuoHttpRequestUtil.sendGet(
                "https://api.weixin.qq.com/cgi-bin/token"
                , "grant_type=client_credential"
                        + "&appid=" + wxMiniprogramConfig.getMiniprogramAppid()
                        + "&secret=" + wxMiniprogramConfig.getMiniprogramSecret()
        );
        if (result != null && !"".equals(result)) {
            return GuoJsonUtil.json2Map(result).get("access_token");
        }
        return "";
    }

    /**
     * 给使用小程序客服的用户发送消息
     */
    public String sendMessageForCustom(JSONObject msg, String accessToken) {
        String jsonObject = GuoHttpRequestUtil.sendPost(
                "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken,
                msg.toString()
        );
        return jsonObject;
    }

    /**
     * 初始化并封装小程序客服的消息
     */
    public JSONObject init(String msgType, String userOpenId, String content) {
        JSONObject msg = new JSONObject();
        msg.put("touser", userOpenId);
        msg.put("msgtype", msgType);
        JSONObject info = new JSONObject();
        if ("text".equals(msgType)) {
            info.put("content", content);
            msg.put("text", info);
        } else if ("image".equals(msgType)) {
            info.put("media_id", content);
            msg.put("image", info);
        }
        return msg;
    }

    /**
     * 通过code获取登录凭证
     */
    public Map<String, Object> getOpenIdAndSessionKeyForCode(String code) {
        Map<String, Object> model = new HashMap<>();
        String result = GuoHttpRequestUtil.sendGet(
                "https://api.weixin.qq.com/sns/jscode2session"
                , "appid=" + wxMiniprogramConfig.getMiniprogramAppid()
                        + "&secret=" + wxMiniprogramConfig.getMiniprogramSecret()
                        + "&js_code=" + code
                        + "&grant_type=authorization_code"
        );
        JSONObject convertvalue = JSON.parseObject(result);
        model.put("sessionkey", (String) convertvalue.get("session_key"));
        model.put("openId", (String) convertvalue.get("openid"));
        return model;
    }

    /**
     * 验证签名是否是微信小程序客服签名
     *
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @return boolean 返回判断值true或false
     */
    public boolean validSign(String signature, String timestamp, String nonce) {
        String[] paramArr = new String[]{wxMiniprogramConfig.getMiniprogramCustomToken(), timestamp, nonce};
        Arrays.sort(paramArr);//Arrays类用来操作数组（比如排序和搜索）的各种方法。sort对数组按数字升序进行排序。
        StringBuilder sb = new StringBuilder(paramArr[0]);
        sb.append(paramArr[1]).append(paramArr[2]);
        String ciphertext = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");//为应用程序提供信息摘要算法的功能
            byte[] digest = md.digest(sb.toString().getBytes());
            ciphertext = GuoStringUtil.byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ciphertext != null ? ciphertext.equals(signature.toUpperCase()) : false;
    }

    /**
     * 获取微信小程序客服消息进入事件消息
     */
    public String getCustomEventMsg(){
        String eventMsg = getEventGuideMsg();
        return eventMsg;
    }

}
