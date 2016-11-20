package com.scht.util;


import java.io.*;

/**
 * Created by Administrator on 2015/5/18.
 */
public class FileUtil {

    private static String Default_Character = "UTF-8";

    // 复制文件
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }

    // 复制文件夹
    public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {
        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        if (file != null)
            for (int i = 0; i < file.length; i++) {
                if (file[i].isFile()) {
                    // 源文件
                    File sourceFile = file[i];
                    // 目标文件
                    File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
                    copyFile(sourceFile, targetFile);
                }
                if (file[i].isDirectory()) {
                    // 准备复制的源文件夹
                    String dir1 = sourceDir + "/" + file[i].getName();
                    // 准备复制的目标文件夹
                    String dir2 = targetDir + "/" + file[i].getName();
                    copyDirectiory(dir1, dir2);
                }
            }
    }

 /*   *//**
     * 读取html文件  读取其中的tag节点
     * @param file
     * @param tag
     * @return
     *//*
    public static String readHtmlFile(String file,String tag){
        try {
            Document doc = Jsoup.parse(new File(file),Default_Character);
            Element element = doc.select(tag).first();
            if(element!=null)
                return element.outerHtml();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    /**
     * 读取tag内
     * @param file
     * @param tag
     * @return
     */
   /* public static String readInnerHtml(String file,String tag){
        try {
            Document doc = Jsoup.parse(new File(file),Default_Character);
            Element element = doc.select(tag).first();
            if(element!=null)
                return element.html();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    /**
     * 读取文件内容
     *
     * @param file
     * @return
     */
   /* public static String readFileContent(String file){
        try {
            Document doc = Jsoup.parse(new File(file),Default_Character);
            if(doc!=null)
                return doc.body().html();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/
    public static boolean saveFile(String content, String file) {
        boolean suc = false;
        FileOutputStream fos = null;
        try {
            File jsp_file = new File(file);
            if (!jsp_file.exists())
                jsp_file.createNewFile();

            fos = new FileOutputStream(file);
            fos.write(content.getBytes("UTF-8"));
            suc = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null)
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return suc;
    }

    /**
     * 保存文件时需替换的字符
     *
     * @return
     */
    public static String[] needReplace() {
        return new String[]{
                "executeresult", "executeResult",
                "&gt;", ">",
                "&lt;", "<",
                "foreach", "forEach",
                "varstatus", "varStatus",
                "&quot;", "\"",
                "=\"=\"", "=="
        };
    }

    /**
     * 获取jsp页面中的head
     *
     * @param path
     * @return
     */
    public static String getJspHeader(String path) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(path);
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            StringBuffer htmlBuffer = new StringBuffer();
            StringBuffer headerBuffer = new StringBuffer();
            int index = 0;
            String temp;
            while ((temp = br.readLine()) != null) {
                String temp1 = temp.trim();

                htmlBuffer.append(temp).append("\r\n");
                if (temp1.indexOf("</head>") > -1) {
                    index = htmlBuffer.length();
                }
            }
            headerBuffer.append(htmlBuffer.substring(0, index));

            return headerBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (isr != null) isr.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 读取file
     *
     * @param path
     * @return
     */
    public static String readFile(String path) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(path);
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            StringBuffer htmlBuffer = new StringBuffer();

            String temp;
            while ((temp = br.readLine()) != null) {
                String temp1 = temp.trim();

                htmlBuffer.append(temp).append("\r\n");
            }

            return htmlBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (isr != null) isr.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {


    }

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        try {
            if (!dir.endsWith(File.separator))
                dir = dir + File.separator;
            File dirFile = new File(dir);
            // 如果dir对应的文件不存在，或者不是一个目录，则退出
            if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
                return false;
            }
            boolean flag = true;
            // 删除文件夹中的所有文件包括子目录
            File[] files = dirFile.listFiles();
            for (int i = 0; i < files.length; i++) {
                // 删除子文件
                if (files[i].isFile()) {
                    flag = deleteFile(files[i].getAbsolutePath());
                    if (!flag)
                        break;
                }
                // 删除子目录
                else if (files[i].isDirectory()) {
                    flag = deleteDirectory(files[i]
                            .getAbsolutePath());
                    if (!flag)
                        break;
                }
            }
            if (!flag) {
                return false;
            }
            // 删除当前目录
            if (dirFile.delete()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
