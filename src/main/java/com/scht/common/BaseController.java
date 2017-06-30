package com.scht.common;


import com.scht.admin.SystemCache;
import com.scht.admin.entity.Admin;
import com.scht.admin.entity.SystemLog;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.LogService;
import com.scht.util.DateUtil;
import com.scht.util.StringUtil;
import com.scht.util.UUIDFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;


/**
 * @author pengqingchun
 * @version 1.0
 *          抽象基础controller
 * @date 2014-10-9
 */
public abstract class BaseController {

    @Autowired
    BaseService baseService;

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    public String PROTOCOL_SUC = "SUCCESS";

    @Autowired
    LogService logService;

    public static int default_size = 20;


    /**
     * 功能描述:获取当前登录的用户信息
     *
     * @author pengqingchun
     */
    public Object getCurrentUser(HttpServletRequest request) {
        HttpSession Session = request.getSession();
        Object obj = (Object) Session.getAttribute(SystemCache.SESSION_ADMIN);

        return obj;
    }




    /**
     * @Title: getPageResult
     * @Description: 封装页面返回信息
     * @author KeBing
     * @date 2014-9-22 下午4:37:07
     * @version V1.0
     */
    public JSONObject getPageResult(PageInfo pageInfo) {
        List array = new ArrayList();
        if (pageInfo.getResult() != null) {
            for (Object o : pageInfo.getResult()) {
//                Object oo = JSONObject.fromObject(o);
                // this.setCheckBoxState(oo);
                array.add(o);
            }
        }
        JSONObject o = new JSONObject();
        o.put("rows", array);
        o.put("results", pageInfo.getTotal());
        return o;
    }

    /**
     * 格式化输出结果
     *
     * @param success 是否成功
     * @param msg     描述
     * @param data    数据内容
     * @return
     */
    public JSONObject FmtResult(boolean success, String msg, Object data) {
        JSONObject obj = new JSONObject();
        obj.put("success", success);
        obj.put("msg", msg);
        obj.put("data", data);
        return obj;
    }

    /**
     * 上传文件
     *
     * @param destFile
     * @param file
     */
    public boolean updateLoadFile(String destFile, MultipartFile file) {
        try {
            File dFile = new File(destFile);
            file.transferTo(dFile);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return false;
    }

    public String getFilePath(String file, HttpServletRequest request) {
        return request.getServletContext().getRealPath(file);
    }

    /**
     * 保存日志
     * @param operateContent
     * @param request
     */
    public void saveLog(String operateContent,HttpServletRequest request){
        try {
            String loginName = null;
            if (request.getSession().getAttribute(SystemCache.SESSION_ADMIN) != null) {
                Admin admin = (Admin) request.getSession().getAttribute(SystemCache.SESSION_ADMIN);
                if (admin != null)
                    loginName = admin.getLoginName();

                SystemLog log = new SystemLog();
                log.setId(UUIDFactory.random());
                log.setOperateName(loginName);
                log.setOperateContent(operateContent);
                log.setIp(request.getRemoteHost());
                log.setLogDate(new Date().getTime());
                this.logService.saveLog(log);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 分页缓存
     *
     * @param className
     * @param pageInfo
     * @return
     */
    public <T> PageInfo page(Class<?> className, PageInfo pageInfo) {
        try {

            Integer total = baseService.count4Page(className, pageInfo.getParams());
            List<T> adRuleList = baseService.searchByPage(className, pageInfo.getParams());
            pageInfo.setTotal(total);
            pageInfo.setResult(adRuleList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pageInfo;
    }


    public Map uploadFile(HttpServletRequest request,String path) {
        Map<String, String> map = new HashMap<String, String>();
        MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
        MultipartFile file = mrequest.getFile("imgFile");
        map.put("suc","0");
        if (!file.isEmpty()) {
            long fileSize = file.getSize();
            if (fileSize > 1024 * 1024) {
                map.put("suc", "1");
                map.put("msg", "图片大小超过1M");
            } else {
                String fileName = file.getOriginalFilename();
                if (StringUtil.isNotNull(fileName)) {
                    String fPath = path + File.separator;
                    String targetFilePath = this.getFilePath(fPath, request);
                    File dirPath = new File(targetFilePath);
                    if (!dirPath.exists()) {
                        dirPath.mkdirs();
                    }
                    String suffix = fileName.substring(fileName.lastIndexOf("."));
                    String targetFileName = DateUtil.getFormatDate(new Date(), "yyyyMMddhhmmss") + suffix;
                    String targetFile = targetFilePath + File.separator + targetFileName;
                    boolean suc = this.updateLoadFile(targetFile, file);
                    map.put("suc", "0");
                    map.put("msg", targetFileName);
                }
            }
        }
        return map;
    }


    public void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }

    protected String getRequestIp(HttpServletRequest request) {
        try {
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }

            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }

            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            return ip;
        } catch (Exception e) {
            return request.getRemoteAddr();
        }
    }



}
