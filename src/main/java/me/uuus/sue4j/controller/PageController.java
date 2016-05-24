package me.uuus.sue4j.controller;

import me.uuus.sue4j.dao.SUserMapper;
import me.uuus.sue4j.pojo.SUserExample;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author Mr.Su[swb0917@gmail.com]
 */
@Controller
@PropertySource("config/ftp.properties")
public class PageController {

    @Resource
    private SUserMapper userMapper;

    @Value("${FTP_IP}")
    private String ftp_ip;

    @RequestMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("all", userMapper.selectByExample(new SUserExample()));
        model.addAttribute("ftp_ip", this.ftp_ip);
        return "index";
    }

}
