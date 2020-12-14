package top.javaguo.biz.system.controller.open;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoRespBeanUtil;

import java.util.Map;

/**
 * 公开接口控制层
 * 
 * @author javaGuo
 * @date 2019-02-26
 */
@RestController
@RequestMapping("/open/open")
public class OpenController {

    /**
     * 公开测试接口
     */
    @GetMapping("/test")
    public RespBean<Map<String, Object>> test() {
        RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
        respBean = GuoRespBeanUtil.setSuccessRespBean(respBean);
        respBean.getData().put("key","这是javaGuo公开接口");
        return respBean;
    }

}
