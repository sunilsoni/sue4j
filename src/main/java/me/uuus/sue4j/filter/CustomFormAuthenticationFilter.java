package me.uuus.sue4j.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 自定义filter,可用来更改默认的表单名称配置
 * 进行了一些验证码的处理操作,但是并没有启动
 * @see me.uuus.sue4j.config.ShiroConfiguration
 * 可能有一些小问题,可根据自己情况更改
 * @author Mr.Su[swb0917@gmail.com]
 * @since 2016/7/16 17:44
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(CustomFormAuthenticationFilter.class);

    /**
     * 覆写原FormAuthenticationFilter的认证方法
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //在这里进行验证码校验
        //从session中获取正确的验证码
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        String validateCode = (String) session.getAttribute("validateCode");
        //输入的验证和session中的验证进行对比
        String randomcode = httpServletRequest.getParameter("randomcode");
        if (randomcode == null || validateCode == null || !randomcode.equals(validateCode)) {
            //如果检验结果失败,将验证码错误失败信息,通过shiroLoginFailure设置到request中
            httpServletRequest.setAttribute("shiroLoginFailure", "randomCodeError");
            return true;
        }
//        return super.onAccessDenied(request, response);
        return true;
    }
}
