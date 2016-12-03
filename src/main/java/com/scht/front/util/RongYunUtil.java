package com.scht.front.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.elink.area.tours.admin.utils.FileUtils;
import org.elink.area.tours.admin.utils.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import io.rong.ApiHttpClient;
import io.rong.models.FormatType;
import io.rong.models.SdkHttpResult;

public class RongYunUtil {

	private static String appKey = "3argexb6r20ie";
	private static String appSecret = "vefLq9hgw4B";

	/**
	 * 获取用户token
	 * 
	 * @param userId
	 * @param userName
	 * @param headImg
	 * @return
	 */
	public static String getUserToken(String userId, String userName,
			String headImg) {
		SdkHttpResult result = null;
		try {
			// 获取token
			result = ApiHttpClient.getToken(appKey, appSecret, userId,
					userName, headImg, FormatType.json);
			System.out.println("*****************  " + result.getHttpCode()
					+ " : " + result.getResult());
			if (result != null) {
				String str = result.getResult();
				if (StringUtils.isNotEmpty(str)) {
					JSONObject json = JSONObject.fromObject(str);
					return json.getString("token");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 刷新用户信息
	 * 
	 * @param userId
	 * @param userName
	 * @param headImg
	 * @return
	 */
	public static int refreshUser(String userId, String userName,
			String headImg) {
		SdkHttpResult result = null;
		try {
			result = ApiHttpClient.refreshUser(appKey, appSecret, userId,
					userName, headImg, FormatType.json);
			System.out.println("*****************  " + result.getHttpCode()
					+ " : " + result.getResult());
			if (result != null) {
				String str = result.getResult();
				if (StringUtils.isNotEmpty(str)) {
					JSONObject json = JSONObject.fromObject(str);
					return json.getInt("code");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 创建群组
	 * 
	 * @param userId
	 * @param groupId
	 * @param groupName
	 * @return
	 */
	public static int createGroup(String userId, String groupId,
			String groupName) {
		SdkHttpResult result = null;
		try {

			List<String> data = new ArrayList<>();
			data.add(userId);
			result = ApiHttpClient.createGroup(appKey, appSecret, data,
					groupId, groupName, FormatType.json);
			System.out.println("*****************  " + result.getHttpCode()
					+ " : " + result.getResult());
			if (result != null) {
				String str = result.getResult();
				if (StringUtils.isNotEmpty(str)) {
					JSONObject json = JSONObject.fromObject(str);
					return json.getInt("code");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	/**
	 * 加入群组
	 * @param userId
	 * @param groupId
	 * @param groupName
	 * @return
	 */
	public static int joinGroup(String userId, String groupId,String groupName) {
		SdkHttpResult result = null;
		try {
			result = ApiHttpClient.joinGroup(appKey, appSecret, userId,
					groupId,groupName, FormatType.json);
			System.out.println("*****************  " + result.getHttpCode()
					+ " : " + result.getResult());
			if (result != null) {
				String str = result.getResult();
				if (StringUtils.isNotEmpty(str)) {
					JSONObject json = JSONObject.fromObject(str);
					return json.getInt("code");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	/**
	 * 退出群组
	 * @param userId
	 * @param groupId
	 * @return
	 */
	public static int quitGroup(String userId, String groupId) {
		SdkHttpResult result = null;
		try {
			result = ApiHttpClient.quitGroup(appKey, appSecret, userId,
					groupId, FormatType.json);
			System.out.println("*****************  " + result.getHttpCode()
					+ " : " + result.getResult());
			if (result != null) {
				String str = result.getResult();
				if (StringUtils.isNotEmpty(str)) {
					JSONObject json = JSONObject.fromObject(str);
					return json.getInt("code");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	/**
	 * 解散群组
	 * @param userId
	 * @param groupId
	 * @return
	 */
	public static int dismissGroup(String userId, String groupId) {
		SdkHttpResult result = null;
		try {
			result = ApiHttpClient.dismissGroup(appKey, appSecret, userId,
					groupId, FormatType.json);
			System.out.println("*****************  " + result.getHttpCode()
					+ " : " + result.getResult());
			if (result != null) {
				String str = result.getResult();
				if (StringUtils.isNotEmpty(str)) {
					JSONObject json = JSONObject.fromObject(str);
					return json.getInt("code");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 刷新群组信息
	 * @param groupName
	 * @param groupId
	 * @return
	 */
	public static int refreshGroupInfo(String groupId, String groupName) {
		SdkHttpResult result = null;
		try {
			result = ApiHttpClient.refreshGroupInfo(appKey, appSecret, groupId,
					groupName, FormatType.json);
			System.out.println("*****************  " + result.getHttpCode()
					+ " : " + result.getResult());
			if (result != null) {
				String str = result.getResult();
				if (StringUtils.isNotEmpty(str)) {
					JSONObject json = JSONObject.fromObject(str);
					return json.getInt("code");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 查询群组成员
	 * @param groupId
	 * @return
	 */
	public static String queryGroupUserList(String groupId) {
		SdkHttpResult result = null;
		try {
			result = ApiHttpClient.queryGroupUserList(appKey, appSecret, groupId,
					FormatType.json);
			System.out.println("*****************  " + result.getHttpCode()
					+ " : " + result.getResult());
			if (result != null) {
				String str = result.getResult();
				if (StringUtils.isNotEmpty(str)) {
					JSONObject json = JSONObject.fromObject(str);
					return json.getString("users");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	public static String downloadHistroy(String date) {
		SdkHttpResult result = null;
		try {
			result = ApiHttpClient.getMessageHistoryUrl(appKey, appSecret, date,
					FormatType.json);
			System.out.println("*****************  " + result.getHttpCode()
					+ " : " + result.getResult());
			if (result != null) {
				String str = result.getResult();
				if (StringUtils.isNotEmpty(str)) {
					JSONObject json = JSONObject.fromObject(str);
					return json.getString("url");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	public static boolean downloadFile(String downloadUrl,String savePath){
		int byteread = 0;
        try {
            URL url = new URL(downloadUrl);
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            FileOutputStream fs = new FileOutputStream(savePath);
            byte[] buffer = new byte[1204];
            while ((byteread = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteread);
            }
            System.out.println(savePath+" download finished!");
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public static void main(String[] args) {
		String userId = "1";
		String userName = "135444d";
		String headImg = "";
		//String res  = getUserToken(userId, userName, headImg);
		//int res = joinGroup(userId, "72e8194aa83348c282cc51a6a82fc828", "标题群聊");
		String url = downloadHistroy("2016032515");
		/*JSONArray array = JSONArray.fromObject(str);
		List<String> list = new ArrayList<>();
		for(int i=0;i<array.size();i++){
			JSONObject json = (JSONObject) array.get(i);
			list.add(json.getString("id"));
		}*/
		System.out.println(url+" : ");
		String file = "e:\\temp.gz";
		
		boolean suc = downloadFile(url,file);
		String unzipfile = "e:\\tmp";
		ZipUtil.upzipFile(file, unzipfile);
		//读取文件
		File dirPath =  new File(unzipfile);
		File[] files = dirPath.listFiles();
		for(File subfile:files){
			System.out.println("000  "+subfile.getAbsolutePath());
			String content = FileUtils.getFileContent(subfile.getAbsolutePath());
			System.out.println("___  "+content);
			String[] arrays = content.split("\n");
			for(String str:arrays){
				String substr = str.substring(19);
				System.out.println(substr);
				
				
			}
		}
		
		 
	}
}
