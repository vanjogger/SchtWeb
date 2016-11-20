package com.scht.admin.controller;

import com.scht.common.BaseController;
import com.scht.common.ConfigHelper;
import com.scht.util.DateUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/5.
 */
@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController {


    @RequestMapping("/uploadFile")
    @ResponseBody
    public String uploadHtmlImg(HttpServletRequest request,ModelMap model){
        JSONObject json = new JSONObject();
        try {
            String upload_path = ConfigHelper.GetInstance().GetConfig("Upload_Path");
            Map map = uploadFile(request, upload_path);
            String url = "http://" + request.getServerName() + ":" + request.getServerPort() + "/"+upload_path+"/";
            if (map.get("suc").equals("0")) {
                url += map.get("msg");
            }
            json.put("error", "0");
            json.put("url", url);
        }catch(Exception e){
            json.put("error","1");
            json.put("message",e.getMessage());
        }
        return  json.toString();
    }


    @RequestMapping(value = "/uploadImage")
    @ResponseBody
    public String uploadImage(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request,
                              HttpSession session) {
            JSONObject json = saveUploadImage(session, file);

            return json.toString();
    }



    public JSONObject saveUploadImage(HttpSession session, MultipartFile file){
        if (file == null) {
            return this.FmtResult(false,"上传文件不存在",null);//上传图片为空
        }

      /*  long maxSize = 500;//最大500k
        try {
            ConfigHelper helper = ConfigHelper.getInstance();
            maxSize = Long.parseLong(helper.getConfig("web.imgMaxSize"));
        } catch (Exception ex) {
            logger.error("config.properties中图片文件上传限制大小错误" + maxSize, ex);
        }*/

        try {
            String path = "/upload/" + DateUtil.getFormatDate(new Date(), "yyyyMMdd");
            String descPathString = session.getServletContext().getRealPath(path);

            File descPath = new File(descPathString);
            if (!descPath.exists()) {
                descPath.mkdirs();
            }
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

            String fileName = System.currentTimeMillis()  + suffix;
            String descFilePath = descPathString + File.separator + fileName;

            file.transferTo(new File(descFilePath));
            String fullPath = path + File.separator + fileName;
            fullPath = fullPath.replaceAll("\\\\", "/");
            return this.FmtResult(true,null,fullPath);
        }catch (Exception e){
            e.printStackTrace();
            return this.FmtResult(false,"上传文件出错",null);
        }
    }


}
