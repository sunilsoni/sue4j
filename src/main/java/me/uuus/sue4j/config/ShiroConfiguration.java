package me.uuus.sue4j.config;

import me.uuus.sue4j.security.MyShiroRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Mr.Su[swb0917@gmail.com]
 */

@Configuration
public class ShiroConfiguration {

    /**
     * 配置shiro的核心拦截器
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        //管理器,必须设置
        shiroFilter.setSecurityManager(securityManager());
        //拦截到跳转到的地址,通过此地址去认证
        shiroFilter.setLoginUrl("/login");
        //认证成功统一跳转到的url,建议不配置,shiro认证成功后自动跳转到上一个请求路径
        shiroFilter.setSuccessUrl("/");
        //通过unauthorizedUrl指定没有权限操作时跳转页面
        shiroFilter.setUnauthorizedUrl("/403");

        //自定义filter,可用来更改默认的表单名称配置  --以下三行代码
        /*Map<String, Filter> filters = new HashMap<>();
        //将自定义的FormAuthenticationFilter注入到shiroFilter中
        filters.put("authc", formAuthenticationFilter());
        shiroFilter.setFilters(filters);*/

        Map<String, String> filterChainDefinitionMapping = new HashMap<>();
        //对静态资源设置匿名访问
        /*filterChainDefinitionMapping.put("/css*", "anon");
        filterChainDefinitionMapping.put("/js*", "anon");
        filterChainDefinitionMapping.put("/img*", "anon");*/
        //首页,可匿名访问
        /*filterChainDefinitionMapping.put("/", "anon");
        filterChainDefinitionMapping.put("/login", "anon");*/
//        filterChainDefinitionMapping.put("/user/login", "authc");

        //请求logout,shiro去清除session
//        filterChainDefinitionMapping.put("/user/logout", "logout");

        //配置记住我或认证通过可以访问的地址
//        filterChainDefinitionMapping.put("/first", "user");
        //authc 所有的url都必须通过认证才可以访问
//        filterChainDefinitionMapping.put("/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
        return shiroFilter;
    }

    /**
     * securityManage 安全管理器
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        //注入缓存管理器
        securityManager.setCacheManager(cacheManager());
        //注入session管理器
        securityManager.setSessionManager(sessionManager());
        //记住我
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * 自定义realm
     */
    @Bean(name = "myShiroRealm")
//    @DependsOn("lifecycleBeanPostProcessor")
    public MyShiroRealm myShiroRealm() {
        //将凭证匹配器设置到realm中,realm按照凭证匹配器的要求进行散列
        //myShiroRealm.setCredentialsMatcher(credentialsMatcher());
        return new MyShiroRealm();
    }

    /**
     * 凭证匹配器
     */
    /*@Bean
    public HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //选用sha256散列算法
        credentialsMatcher.setHashAlgorithmName("SHA-256");
        //进行一次加密
        credentialsMatcher.setHashIterations(1);
        return credentialsMatcher;
    }*/

    /**
     * 自定义form认证过滤器
     * 基于form表单的身份验证过滤器,不配置将也会注册此过滤器,
     * 表单中的用户账号、密码、及loginurl将采用默认值，建议配置
     */
    /*@Bean
    public CustomFormAuthenticationFilter formAuthenticationFilter() {
        CustomFormAuthenticationFilter formAuthenticationFilter = new CustomFormAuthenticationFilter();
        formAuthenticationFilter.setUsernameParam("username");
        formAuthenticationFilter.setPasswordParam("password");
        formAuthenticationFilter.setRememberMeParam("remenberMe");
        formAuthenticationFilter.setLoginUrl("/user/login");
        return formAuthenticationFilter;
    }*/

    /**
     * 会话管理器
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //session的失效时长,单位毫秒
        sessionManager.setGlobalSessionTimeout(300000L);
        sessionManager.setDeleteInvalidSessions(true);
        return sessionManager;
    }

    /**
     * 缓存管理器
     */
    @Bean
    public EhCacheManager cacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:config/shiro/ehcache-shiro.xml");
        return cacheManager;
    }

    /**
     * remenberMeManage管理器,写cookie,取出cookie生成用户信息
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(remenberMeCookie());
        return rememberMeManager;
    }

    /**
     * 记住我的cookile
     */
    @Bean
    public SimpleCookie remenberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        //remenberMeCookie是cookie的名字
        simpleCookie.setName("SUE4JCOOKIE");
        simpleCookie.setMaxAge(1296000);
        return simpleCookie;
    }


//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }


//    @Bean
//    @DependsOn("lifecycleBeanPostProcessor")
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        return new DefaultAdvisorAutoProxyCreator();
//    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }


}

