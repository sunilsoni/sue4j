package me.uuus.sue4j.core.util;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码工具类  生成一个验证码
 * @author Mr.Su[swb0917@gmail.com]
 * @since 2016/7/16 18:12
 */
public class ValidateCodeUtil {

    public static BufferedImage getValidateCode(HttpSession session) {
        int width = 60;
        int height = 32;
        //create the image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        // set the background color
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);
        // draw the border
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        // create a random instance to generate the codes
        Random rdm = new Random();
        String hash1 = Integer.toHexString(rdm.nextInt());
        // make some confusion
        for (int i = 0; i < 50; i++) {
            int x = rdm.nextInt(width);
            int y = rdm.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }
        // generate a random code
        String capstr = hash1.substring(0, 4);
        //将生成的验证码存入session
        session.setAttribute("validateCode", capstr);
        g.setColor(new Color(rdm.nextInt(255), rdm.nextInt(255), rdm.nextInt(255)));
        g.setFont(new Font("Candara", Font.BOLD, 24));
        g.drawString(capstr, 8, 24);
        g.dispose();
        return image;
    }

}
