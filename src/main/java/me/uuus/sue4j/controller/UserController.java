package me.uuus.sue4j.controller;

import me.uuus.sue4j.core.entity.JsonResult;
import me.uuus.sue4j.core.entity.Result;
import me.uuus.sue4j.dao.UserMapper;
import me.uuus.sue4j.model.User;
import me.uuus.sue4j.security.PermissionSign;
import me.uuus.sue4j.security.RoleSign;
import net.bull.javamelody.MonitoredWithSpring;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@MonitoredWithSpring
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserMapper userDao;

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@Valid User user, BindingResult result, String rememberMe, HttpServletRequest request) {
        try {
            Subject subject = SecurityUtils.getSubject();
            if (request.getAttribute("shiroLoginFailure") != null) {
                return new JsonResult(false, "请输入正确的验证码!");
            }
            if (result.hasErrors()) {
                return new JsonResult(false, "非法参数!!!");
            }
            subject.login(new UsernamePasswordToken(user.getUsername(), user.getPassword(), "true".equals(rememberMe)));
        }catch (AuthenticationException e) {
            return new JsonResult<>(false, "用户名或密码错误");
        }catch (Exception e){
            return new JsonResult<>(false, "出错啦..");
        }
        log.info("--------用户{}登录成功了,跳转到首页--------", user.getUsername());
        return new JsonResult<>("/");
    }

    /**
     * 用户登出
     *
     * @param session .
     * @return .
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        /*
          部分用记住密码的用户登录一些@RequiresAuthentication页面时
          @see PageController (参考前面类的two()方法已经它的说明)
         * 抛出org.apache.shiro.authz.UnauthenticatedException,异常
         * @see me.uuus.sue4j.config.ExceptionConfig ,
         * 在上面的类中我们捕获了这个异常,并跳转到注销页面,然后在跳转到首页
         *
         * see注解按住ctrl然后点击后面的类可以直接进入
         */
        session.removeAttribute("userInfo");
        // 登出操作
        Subject subject = SecurityUtils.getSubject();
        log.info("--------用户{}注销成功了,跳转到登录页--------", subject.getPrincipal());
        subject.logout();
        return "redirect:/login";
    }

    /**
     * 测试注解 @RequiresRoles 需要有指定的角色才能够访问
     * 基于角色 标识的权限控制案例
     */
    @RequestMapping(value = "/admin")
    @ResponseBody
    @RequiresRoles(value = RoleSign.ADMIN)
    public String admin() {
        return "拥有admin角色,能访问";
    }

    /**
     * 测试注解 @RequiresPermissions 需要有指定的权限才能够访问
     * 基于权限标识的权限控制案例
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    @RequiresPermissions(PermissionSign.USER_CREATE)
    public String create() {
        return "拥有user:create权限,能访问";
    }

}