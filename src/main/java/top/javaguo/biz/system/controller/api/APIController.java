package top.javaguo.biz.system.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoRespBeanUtil;

import java.util.Map;

/**
 * api接口控制层
 * 
 * @author javaGuo
 * @date 2019-02-26
 */
@RestController
@RequestMapping("/api/api")
public class APIController {

    /**
     * LayUI根据条件查询所有
     *
     * @return
     */
    @GetMapping("/test")
    public RespBean<Map<String, Object>> test() {
        RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
        respBean = GuoRespBeanUtil.setSuccessRespBean(respBean);
        respBean.getData().put("key","这是javaGuo前端接口");
        return respBean;
    }

}
