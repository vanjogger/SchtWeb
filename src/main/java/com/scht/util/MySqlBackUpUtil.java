package com.scht.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2016/5/24.
 */
public class MySqlBackUpUtil {

    /**
     * �������ݿ�
     * @param host  ip��ַ
     * @param userName �û���
     * @param pwd  ����
     * @param databaseName  ���ݿ���
     * @param file  �����ļ�·��
     */
    public static boolean banckUp(String host,String userName,String pwd,String databaseName,String file){
        StringBuffer commend = new StringBuffer();
        commend.append("mysqldump");
        commend.append(" -h "+host);
        commend.append(" -u "+userName);
        commend.append(" --p="+pwd);
        commend.append(" database_name>"+databaseName);
        commend.append(" --set-charset=utf-8");

        try {
            Process process = Runtime.getRuntime().exec("cmd /c "+commend.toString());

            InputStream is = process.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader);

            StringBuffer content = new StringBuffer();
            String line = null;
            while((line = br.readLine())!=null){
                content.append(line);
            }
            FileUtil.saveFile(content.toString(),file);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
            return false;
    }


    public static void main(String[] args){
        String host = "192.168.1.250";
        String userName = "root";
        String pwd = "123";
        String databaseName = "directsell";

        String file = "e:\\backup.sql";
        System.out.println("--------   "+banckUp(host,userName,pwd,databaseName,file));
    }

}
