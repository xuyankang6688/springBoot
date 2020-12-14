package top.javaguo.core.intercept.webMvcConfigurerAdapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.javaguo.core.intercept.interceptor.APIInterceptor;
import top.javaguo.core.intercept.interceptor.SystemInterceptor;

/**
 * @describe 初始化配置类
 * @author javaGuo
 * @date 2018/11/15
 */
@Configuration
public class BaseWebAppConfigurer implements WebMvcConfigurer {

    @Bean
    public SystemInterceptor SystemInterceptor() {
        return new SystemInterceptor();
    }

    @Bean
    public APIInterceptor APIInterceptor() {
        return new APIInterceptor();
    }

	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(APIInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login/**","")
        		;

        registry.addInterceptor(SystemInterceptor())
                .addPathPatterns("/system/**");
    }

}
