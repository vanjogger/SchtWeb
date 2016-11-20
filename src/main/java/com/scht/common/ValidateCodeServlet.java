package com.scht.common;


import com.scht.admin.SystemCache;
import com.scht.util.VerifyCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Administrator on 2015/12/23.
 */
public class ValidateCodeServlet extends HttpServlet implements Servlet {

    private static Logger logger = LoggerFactory.getLogger(ValidateCodeServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);
        //将验证码放到HttpSession里面
        request.getSession().setAttribute(SystemCache.SESSION_YZM, verifyCode);
        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
        //写给浏览器
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ImageIO.setUseCache(false);
    }
}
