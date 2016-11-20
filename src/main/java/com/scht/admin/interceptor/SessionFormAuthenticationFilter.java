package com.scht.admin.interceptor;

import com.scht.admin.SystemCache;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2016/1/6.
 */
public class SessionFormAuthenticationFilter extends FormAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(SessionFormAuthenticationFilter.class);


    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        if(session.getAttribute(SystemCache.SESSION_ADMIN)==null) {
            String uri = req.getRequestURI();
            if(uri.startsWith("/front")){
                resp.sendRedirect(req.getContextPath() + "/front/index");
            }else {
                resp.sendRedirect(req.getContextPath() + "/admin");
            }
            return ;
        }
        super.redirectToLogin(request, response);
    }


}
