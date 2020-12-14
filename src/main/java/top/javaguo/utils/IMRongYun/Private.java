package top.javaguo.utils.IMRongYun;

import io.rong.RongCloud;
import io.rong.messages.BaseMessage;
import io.rong.messages.TxtMessage;
import io.rong.models.CheckMethod;
import io.rong.models.message.PrivateMessage;
import io.rong.models.response.ResponseResult;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

public class Private {
    private static final String UTF8 = "UTF-8";
    private static final String PATH = "message/_private";
    private static final String RECAL_PATH = "message/recall";
    private static final TxtMessage txtMessage = new TxtMessage("hello", "helloExtra");
    private String appKey;
    private String appSecret;
    private RongCloud rongCloud;

    public RongCloud getRongCloud() {
        return rongCloud;
    }

    public void setRongCloud(RongCloud rongCloud) {
        this.rongCloud = rongCloud;
    }
    public Private() {
        this.appKey = "c9kqb3rdc4iij";
        this.appSecret = "KHo1OTQWWVqe";

    }
    /**
     * 发送单聊消息方法（一个用户向另外一个用户发送消息，单条消息最大 128k。每分钟最多发送 6000 条信息，每次发送用户上限为 1000 人，如：一次发送 1000 人时，示为 1000 条消息。）
     *
     *  单聊消息
     *
     * @return ResponseResult
     * @throws Exception
     **/
    @Test
    public void  send() throws Exception {
        PrivateMessage message = new PrivateMessage();
        String[] targetId={"348730193566896128"};
        message.setContent(txtMessage);
        message.setSenderId("331295530435088384");
        message.setTargetId(targetId);
        message.setObjectName("11");
        String errMsg = CommonUtil.checkFiled(message,PATH, CheckMethod.SEND);
        if(null != errMsg){
            ResponseResult responseResult=(ResponseResult) GsonUtil.fromJson(errMsg,ResponseResult.class);

        }

        StringBuilder sb = new StringBuilder();
        sb.append("&fromUserId=").append(URLEncoder.encode(message.getSenderId().toString(), UTF8));

        for (int i = 0 ; i< message.getTargetId().length; i++) {
            String child  = message.getTargetId()[i];
            if(null != child){
                sb.append("&toUserId=").append(URLEncoder.encode(child, UTF8));
            }
        }

        sb.append("&objectName=").append(URLEncoder.encode(message.getContent().getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(message.getContent().toString(), UTF8));

        if (message.getPushContent() != null) {
            sb.append("&pushContent=").append(URLEncoder.encode(message.getPushContent().toString(), UTF8));
        }

        if (message.getPushData() != null) {
            sb.append("&pushData=").append(URLEncoder.encode(message.getPushData().toString(), UTF8));
        }

        if (message.getCount() != null) {
            sb.append("&count=").append(URLEncoder.encode(message.getCount().toString(), UTF8));
        }

        if (message.getVerifyBlacklist() != null) {
            sb.append("&verifyBlacklist=").append(URLEncoder.encode(message.getVerifyBlacklist().toString(), UTF8));
        }

        if (message.getIsPersisted() != null) {
            sb.append("&isPersisted=").append(URLEncoder.encode(message.getIsPersisted().toString(), UTF8));
        }

        if (message.getIsCounted() != null) {
            sb.append("&isCounted=").append(URLEncoder.encode(message.getIsCounted().toString(), UTF8));
        }

        if (message.getIsIncludeSender() != null) {
            sb.append("&isIncludeSender=").append(URLEncoder.encode(message.getIsIncludeSender().toString(), UTF8));
        }
        if (message.getContentAvailable() != null) {
            sb.append("&contentAvailable=").append(URLEncoder.encode(message.getContentAvailable().toString(), UTF8));
        }
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(rongCloud.getApiHostType(), "c9kqb3rdc4iij", "KHo1OTQWWVqe", "/message/private/publish.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn);
        ResponseResult responseResult=(ResponseResult) GsonUtil.fromJson(CommonUtil.getResponseByCode(PATH,CheckMethod.PUBLISH,HttpUtil.returnResult(conn)), ResponseResult.class);
        System.out.println("responseResult============="+responseResult);
    }



}
