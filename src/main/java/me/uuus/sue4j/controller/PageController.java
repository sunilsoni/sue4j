package me.uuus.sue4j.controller;

import me.uuus.sue4j.core.util.ValidateCodeUtil;
import net.bull.javamelody.MonitoredWithSpring;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Mr.Su[swb0917@gmail.com]
 */
@Controller
@MonitoredWithSpring
public class PageController {


    @RequestMapping(value = {"/", "/index"})
    public String showIndex(Model model) {
        String user = (String) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("userInfo", user);
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return ( SecurityUtils.getSubject().getPrincipal() == null) ? "login" : "redirect:/";
    }

    /**
     * 测试页  /first
     * 测试@ReqiresUser  记住密码也可以访问
     */
    @RequestMapping("/first")
    @RequiresUser
    public String first() {
        return "first";
    }

    /**
     * 测试页  /two
     * 测试@ReqiresUser  记住密码用cookie不能访问  需要此次会话登录的用户访问
     * 所以这个注解可以用在一些特别的地方  比如涉及支付等操作
     * 这样通过记住密码的方式不能够进行操作  比如cookie被盗用 用了这个注解也需要重新登录能保证安全
     */
    @RequestMapping("/two")
    @RequiresAuthentication
    public String two() {
        return "two";
    }


    /**
     * 验证码
     */
    @RequestMapping(value = "/validateCode", produces = "image/jpeg")
    @ResponseBody
    public void validateCode(HttpSession session, HttpServletResponse response) throws IOException {
        BufferedImage image = ValidateCodeUtil.getValidateCode(session);
        ImageIO.write(image, "jpeg", response.getOutputStream());
    }

    /**
     * 403 页面
     */
    @RequestMapping("/403")
    public String error403() {
        return "403";
    }

    /**
     * 500 页面
     */
    @RequestMapping("/500")
    public String error500() {
        return "500";
    }

}
