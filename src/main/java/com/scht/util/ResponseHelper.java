/**
 * @(#) DaoException.java     2012/11/08 15:15
 *
 * 版权所有 (c) 北京银软网络技术有限公司
 * 北京市海淀区上地国际创业园西区1号
 * 保留所有权利
 */
package com.scht.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 张奎
 * @version 1.0
 */
public class ResponseHelper {

    public static void forward(HttpServletRequest request,HttpServletResponse response,String path) throws IOException, ServletException {
        request.getRequestDispatcher(path).forward(request, response);
    }

    public static void send(HttpServletResponse response,String path) throws IOException {
        response.sendRedirect(path);
    }

    public static void text(HttpServletResponse response,String string) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(string);
        out.flush();
        out.close();
    }
}
