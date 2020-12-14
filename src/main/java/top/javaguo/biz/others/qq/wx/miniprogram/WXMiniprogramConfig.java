package top.javaguo.biz.others.qq.wx.miniprogram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 微信小程序配置文件
 *
 * @author javaGuo
 * @date 2019/03/19
 */
@Component
public class WXMiniprogramConfig {

    //微信公众平台-小程序客服消息令牌
    @Value("${wx.miniProgram.customToken}")
    private String miniprogramCustomToken;

    //微信公众平台-小程序APPID
    @Value("${wx.miniProgram.appid}")
    private String miniprogramAppid;

    //微信公众平台-小程序秘钥
    @Value("${wx.miniProgram.secret}")
    private String miniprogramSecret;

    public String getMiniprogramCustomToken() {
        return miniprogramCustomToken;
    }

    public String getMiniprogramAppid() {
        return miniprogramAppid;
    }

    public String getMiniprogramSecret() {
        return miniprogramSecret;
    }
}
