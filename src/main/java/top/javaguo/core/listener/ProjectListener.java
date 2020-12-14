package top.javaguo.core.listener;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import top.javaguo.biz.system.bean.SysPublicParam;
import top.javaguo.biz.system.dao.sysPublicParam.SysPublicParamDao;
import top.javaguo.core.publicParam.PublicParamUtil;

import java.util.List;

/**
 * @author javaGuo
 * @describe 项目启动监听
 * @date 2019/02/27
 */
@Component
public class ProjectListener implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        initPublicParam(event);
    }

    // 初始化公共参数
    private void initPublicParam(ApplicationReadyEvent event) {
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        SysPublicParamDao sysPublicParamDao = applicationContext.getBean(SysPublicParamDao.class);

        List<SysPublicParam> list = sysPublicParamDao.selectAll(new SysPublicParam());
        for (int i = 0; i < list.size(); i++) {
            SysPublicParam sysPublicParam = list.get(i);
            PublicParamUtil.map.put(sysPublicParam.getParamKey(), sysPublicParam.getParamValue());
        }
        System.out.println("加载系统公共参数共计：" + PublicParamUtil.map.size() + "个");
    }

}
