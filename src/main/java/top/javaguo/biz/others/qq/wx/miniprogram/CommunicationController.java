package top.javaguo.biz.others.qq.wx.miniprogram;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.javaguo.biz.others.qq.wx.util.WXInterfaceUtil;
import top.javaguo.core.cache.redis.GuoRedisUtil;
import top.javaguo.utils.GuoLogUtil;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 微信小程序通信控制层
 *
 * @author javaGuo
 * @date 2019-03-19
 */
@RestController
@RequestMapping("/others/wx/communication")
public class CommunicationController {

    @Autowired
    private GuoRedisUtil guoRedisUtil;

    @Autowired
    private WXInterfaceUtil wxInterfaceUtil;

    /**
     * GET请求：进行URL、Tocken 认证；
     * 1.将token、timestamp、nonce三个参数进行字典序排序
     * 2.将三个参数字符串拼接成一个字符串进行sha1加密
     * 3.开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     */
    @RequestMapping(value = "/receiveMessage", method = RequestMethod.GET)
    public String doGet(HttpServletRequest request) {
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");// 随机数
        String echostr = request.getParameter("echostr");// 随机字符串
        // 校验成功返回 echostr，成功成为开发者；否则返回error，接入失败
        if (wxInterfaceUtil.validSign(signature, timestamp, nonce)) {
            return echostr;
        }
        return "error";
    }

    /**
     * 接收客服消息
     */
    @RequestMapping(value = "/receiveMessage", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServletInputStream stream = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = new String("");
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            JSONObject jsonObject = JSONObject.parseObject(buffer.toString());
            //GuoLogUtil.info("jsonObject--->" + jsonObject);

            String accessToken = (String) guoRedisUtil.get("accessToken");
            if (accessToken == null || "".equals(accessToken)) {
                accessToken = wxInterfaceUtil.getAccessToken();
                if (accessToken == null || "".equals(accessToken)) {
                    GuoLogUtil.error("微信小程序通信控制层--->接收客服消息--->未获取到accessToken，请查看配置参数是否正确");
                    return;
                } else {
                    guoRedisUtil.set("accessToken", accessToken, 7200L);
                }
            }

            //收到的是文本消息
            if (jsonObject.getString("MsgType").equals("text")) {
                /*wxInterfaceUtil.sendMessageForCustom(
                        wxInterfaceUtil.init(
                                "text", jsonObject.getString("FromUserName"), jsonObject.getString("Content")
                        ),
                        accessToken
                );*/
            }
            //收到的是图片消息
            else if (jsonObject.getString("MsgType").equals("image")) {
                /*wxInterfaceUtil.sendMessageForCustom(
                        wxInterfaceUtil.init(
                                "image", jsonObject.getString("FromUserName"), jsonObject.getString("MediaId")
                        ),
                        accessToken
                );*/
            }
            //当用户进入客户聊天界面时的事件
            else if (jsonObject.getString("MsgType").equals("event")) {
                wxInterfaceUtil.sendMessageForCustom(
                        wxInterfaceUtil.init(
                                "text", jsonObject.getString("FromUserName"), wxInterfaceUtil.getCustomEventMsg()
                        ),
                        accessToken
                );
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
