package com.hui.validateCode;

/**
 * @Author: CarlChen
 * @Despriction: 生成和获取图形验证码controller
 * @Date: Create in 17:09 2019\2\10 0010
 */

import com.hui.contanst.CommonContsnast;
import com.hui.properties.SecurityProperties;
import com.hui.properties.ValidateCodeProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@RestController
public class ValidateCodeController {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * spring中操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @GetMapping(value = "/image/code")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletWebRequest servletWebRequest = new ServletWebRequest(request);

        ImageCode imageCode = createImageCode(request);

        //将验证码保存到request中的session中
        sessionStrategy.setAttribute(servletWebRequest, CommonContsnast.SESSION_KEY, imageCode);

        //将验证码通过response回显
        boolean jpeg = ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

    /**
     * 生成随机验证码
     * @param request
     * @return
     */
    private ImageCode createImageCode(HttpServletRequest request) {
        int width = ServletRequestUtils.getIntParameter(request, "width", securityProperties.getValidateCode().getImageCodeValidate().getWidth());
        int height = ServletRequestUtils.getIntParameter(request, "height", securityProperties.getValidateCode().getImageCodeValidate().getHeight());
        int fontHeight = ServletRequestUtils.getIntParameter(request, "fontHeight", securityProperties.getValidateCode().getImageCodeValidate().getFontHeight());
        int codeCount = ServletRequestUtils.getIntParameter(request, "codeCount", securityProperties.getValidateCode().getImageCodeValidate().getCodeCount());
        int xx = ServletRequestUtils.getIntParameter(request, "xx", securityProperties.getValidateCode().getImageCodeValidate().getXx());
        int codeY = ServletRequestUtils.getIntParameter(request, "codeY", securityProperties.getValidateCode().getImageCodeValidate().getCodeY());

        // 定义图像bufferedImage
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics gd = buffImg.getGraphics();
        // 创建一个随机数生成器类
        Random random = new Random();
        // 将图像填充为白色
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);

        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
        // 设置字体
        gd.setFont(font);

        // 画边框
        gd.setColor(Color.BLACK);
        gd.drawRect(0, 0, width - 1, height - 1);

        // 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
        gd.setColor(Color.BLACK);
        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }

        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;

        // 随机产生codeCount数字的验证码。
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。
            String code = String.valueOf(securityProperties.getValidateCode().getImageCodeValidate().getCodeSequence()[random.nextInt(36)]);
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            // 用随机产生的颜色将验证码绘制到图像中。
            gd.setColor(new Color(red, green, blue));
            gd.drawString(code, (i + 1) * xx, codeY);

            // 将产生的四个随机数组合在一起。
            randomCode.append(code);
        }
        return new ImageCode(buffImg, randomCode.toString(), 120);
    }

}
