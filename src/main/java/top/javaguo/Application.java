package top.javaguo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import top.javaguo.core.listener.ProjectListener;


/**
 * 程序启动类
 *
 * @author javaGuo
 * @date 2018/02/06
 */

/**
 * @Example Spring Boot 扫包优化
 * 原因：
 * 1.会导致项目启动时间变长。当启动一个大的应用程序,或将做大量的集成测试启动应用程序时，影响会特别明显。
 * 2.会加载一些不需要的多余的实例（beans）。
 * 3.会增加 CPU 消耗。
 * <p>
 * 解决：
 * 移除 @SpringBootApplication 然后使用 @Configuration、@EnableAutoConfiguration 和 @ComponentScan注解来扫描特定的包
 */
//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {
        "top.javaguo.biz.sso.controller",
        "top.javaguo.biz.sso.service",
        "top.javaguo.biz.system.controller",
        "top.javaguo.biz.system.service",
        "top.javaguo.core.cache.redis",
        "top.javaguo.core.intercept.webMvcConfigurerAdapter",
        "top.javaguo.core.biz.controller",
        "top.javaguo.biz.others.qq.wx.miniprogram",
        "top.javaguo.biz.others.qq.wx.util",
})
public class Application {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        // 加入项目监听
        application.addListeners(new ProjectListener());
        application.run(args);
    }



/*    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory(){
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");//机密的
                SecurityCollection securityCollection = new SecurityCollection();
                securityCollection.addPattern("/*");
                securityConstraint.addCollection(securityCollection);
                context.addConstraint(securityConstraint);
            }
        };
        factory.addAdditionalTomcatConnectors(createStandardConnector());
        return factory;
    }

    private Connector createStandardConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        //监听http的端口号
        connector.setPort(80);
        connector.setSecure(false);
        //监听到http的端口号后转向到的https的端口号
        connector.setRedirectPort(443);//这里的端口写成和配置文件一样的端口就Ok
        return connector;
    }*/

}

