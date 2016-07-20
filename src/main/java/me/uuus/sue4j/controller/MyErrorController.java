package me.uuus.sue4j.controller;

import net.bull.javamelody.MonitoredWithSpring;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 统一处理404  找不到资源的时候统一跳转到404页面
 * @author Mr.Su[swb0917@gmail.com]
 */
@Controller
@MonitoredWithSpring
public class MyErrorController implements ErrorController {


    private static final String ERROR_PATH = "/error";

    @RequestMapping(value=ERROR_PATH)
    public String handleError(){
        return "/404";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}
