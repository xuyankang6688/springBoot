package top.javaguo.biz.system.controller.open;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.javaguo.core.publicParam.PublicParamUtil;
import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoRespBeanUtil;

import java.util.Map;

/**
 * 公开接口控制层
 * @author javaGuo
 * @date 2019-02-27
 */
@RestController
@RequestMapping("/open/project")
public class ProjectInfoController {

    /**
     * @Example 获取公共参数demo
     * 项目信息获取
     * @return
     */
    @GetMapping("/info")
    public RespBean<Map<String, Object>> test() {
        RespBean<Map<String, Object>> respBean = GuoRespBeanUtil.initRespBean();
        respBean = GuoRespBeanUtil.setSuccessRespBean(respBean);
        respBean.getData().put("Project_name",PublicParamUtil.map.get("Project_name"));
        respBean.getData().put("Project_describe",PublicParamUtil.map.get("Project_describe"));
        respBean.getData().put("Project_title",PublicParamUtil.map.get("Project_title"));
        return respBean;
    }

}
