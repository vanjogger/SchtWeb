package com.scht.front.controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.scht.admin.service.BaseService;
import com.scht.common.BaseFrontController;

import com.scht.common.ConfigHelper;
import com.scht.front.bean.ElinkResources;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import com.scht.util.DateUtil;
import com.scht.util.UUIDFactory;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/rest/app")
public class RestAppUploadController extends BaseFrontController {

	private static Logger logger = LoggerFactory.getLogger(RestAppUploadController.class);


	@RequestMapping(value = "/upload")
	@ResponseBody
	public Object patrolInspectionUpload(HttpServletRequest request, @RequestParam(value = "multipartFile") MultipartFile multipartFile) {
		logger.info("用户：{}，app端执行上传图片操作");
		RetResult result = null;
		try {
			Enumeration<String> req = request.getHeaderNames();
			while (req.hasMoreElements()) {
				String obj = req.nextElement();
				logger.info("-----name:{}------value:{}----", obj, request.getHeader(obj));
			}
			// result = validateParam(au);
			if (result == null) {
				if (multipartFile != null) {
					ElinkResources upload = this.converToUpload(multipartFile);
					File file = new File(request.getSession().getServletContext().getRealPath("") + upload.getFileRelativePath() + upload.getUuidName());
					File parent = file.getParentFile();
					if (parent != null && !parent.exists()) {
						parent.mkdirs();
					}
					file.createNewFile();
					Thumbnails.of(multipartFile.getInputStream()).scale(0.5f).toFile(file);
					result = new RetResult(RetResult.RetCode.OK);
					RetData data = new RetData(upload);
					result.setData(data);
				} else {
					logger.warn("-----用户：{}，执行上传图片操作，参数错误-multipartFile为空-------");
					result = new RetResult(RetResult.RetCode.Execute_Error);
				}
			}
		} catch (Exception e) {
			logger.error("----用户：{}，执行上传图片操作，文件上传错误：{}-----", e);
			result = new RetResult(RetResult.RetCode.System_Error);
		}
		return JSON.toJSON(result);
	}


	@RequestMapping(value = "/multiUpload")
	@ResponseBody
	public Object imgupload( HttpServletRequest request, @RequestParam MultipartFile[] multipartFiles) throws IOException {
		logger.info("用户：{}，app端执行上传图片操作");
		RetResult result = null;
		try {
			if (result == null) {
				if (multipartFiles != null && multipartFiles.length > 0) {
					List<ElinkResources> list = new ArrayList<>();
					for (MultipartFile multipartFile : multipartFiles) {
						Image img = ImageIO.read(multipartFile.getInputStream());
						ElinkResources upload = this.converToUpload(multipartFile);
						String filePath = request.getSession().getServletContext().getRealPath("") + upload.getFileRelativePath() + upload.getUuidName();
						File file = new File(filePath);
						String thumbnailFilePath = request.getSession().getServletContext().getRealPath("") + upload.getFileRelativePath() + upload.getId();
						File parent = file.getParentFile();
						if (parent != null && !parent.exists()) {
							parent.mkdirs();
						}
						file.createNewFile();
						String thumbnailFilePath2 = request.getSession().getServletContext().getRealPath("") + upload.getFileRelativePath() + upload.getThumbnail();
						//FileUtils.uploadFile(multipartFile.getInputStream(), filePath);
						logger.info("-------size:{}------",multipartFile.getSize());
						multipartFile.transferTo(file);// 原图
						Thumbnails.of(filePath).scale(0.5f).toFile(thumbnailFilePath2);
						list.add(upload);
					}
					result = new RetResult(RetResult.RetCode.OK);
					RetData data = new RetData(list);
					result.setData(data);

				} else {
					logger.warn("-----用户：{}，执行上传图片操作，参数错误-multipartFile为空-------");
					result = new RetResult(RetResult.RetCode.Execute_Error);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = new RetResult(RetResult.RetCode.System_Error);
		}
		return JSON.toJSON(result);
	}


	/**
	 * 上传文件转换
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private ElinkResources converToUpload(MultipartFile file) throws Exception {
		ElinkResources upload = new ElinkResources();
		upload.setFileName(file.getOriginalFilename());
		upload.setFileType("jpg");
		String fileName = UUIDFactory.random();
		upload.setId(fileName);
		upload.setUuidName(fileName + ".jpg");
		upload.setThumbnail(fileName + "_slt.jpg");
		upload.setFileSize(Long.valueOf(file.getSize()).intValue());
		String filePath = ConfigHelper.GetInstance().GetConfig("Upload_Path")+"/" +DateUtil.getFormatDate(new Date(),"yyyyMMdd");
		upload.setFileRelativePath(filePath);
		upload.setCreateTime(new Date());
		upload.setStatus("1");
		return upload;
	}

}
