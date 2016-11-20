package com.scht.admin.controller;

import com.scht.admin.SystemCache;
import com.scht.admin.entity.Admin;
import com.scht.admin.service.AdminService;
import com.scht.common.BaseController;
import com.scht.util.MD5Util;
import com.scht.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/6.
 */
@Controller
public class LoginController extends BaseController {


    private static Logger logger  = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    AdminService adminService;


    @RequestMapping("/login")
    public String login(String userName, String password, String validateCode, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String session_yzm = (String) session.getAttribute(SystemCache.SESSION_YZM);

        String rediect = "login";
        String message_login = "";
        if(!StringUtil.isNotNull(userName)&&!StringUtil.isNotNull(password)&&!StringUtil.isNotNull(validateCode)){
                logger.info("首次进入登录");
        }else{
            if (validateCode.equals(session_yzm)) {//�ж���֤��
                Subject user = SecurityUtils.getSubject();
                String key = userName+"|Admin";
                UsernamePasswordToken token = new UsernamePasswordToken(key, MD5Util.getMD5ofStr(password));
                token.setRememberMe(true);
                try {
                    user.login(token);

                    rediect = "redirect:main";
                } catch (UnknownAccountException uae) {
                    logger.debug("account[" + userName + "] not exist");
                    message_login = "账号不存在";
                } catch (IncorrectCredentialsException ice) {
                    logger.debug("account [" + userName + "] invalid password");
                    message_login = "密码错误";
                } catch (LockedAccountException lae) {
                    logger.debug("account[" + userName + "] is locked");
                    message_login = "账号被锁定";
                }/* catch (ExcessiveAttemptsException eae) {
                message_login = "�û���������ʧ�ܴ�������";
            } */catch (AuthenticationException ae) {
                    ae.printStackTrace();
                    message_login = "授权失败";
                }
                if(user.isAuthenticated()){
                    logger.info("user: "+userName+" ��¼�ɹ�!");
                    Admin admin = (Admin) this.adminService.listAdminbyName(userName).get(0);
                    admin.setLastLoginTime(new Date().getTime());
                    admin.setLoginIP(request.getRemoteHost());
                    if(admin.getLoginCnt()!=null){
                        admin.setLoginCnt(admin.getLoginCnt()+1);
                    }else{
                        admin.setLoginCnt(1);
                    }
                    this.adminService.updateAdmin(admin);
                    //������־
                    this.saveLog("��¼",request);
                }

            } else {
                message_login = "验证码错误";
            }
        }
        request.setAttribute("message_login",message_login);

        return rediect;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){

        Subject subject = SecurityUtils.getSubject();
        if (null != subject)
            subject.logout();
        if(session!=null){
            try {
                session.invalidate();
            }catch(Exception e){

            }
        }
        return "login";
    }


}
