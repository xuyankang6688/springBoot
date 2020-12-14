package top.javaguo.core.biz.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页控制层
 * 
 * @author javaGuo
 * @date 2018-11-15
 */
@RestController
public class IndexController {

    /** 域名访问 **/
    @GetMapping("/")
    public void login(HttpServletResponse response) throws IOException {
        response.sendRedirect("/static/system/index.html");
    }

}
