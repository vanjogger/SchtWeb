package com.scht.util;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by Administrator on 2014/10/29.
 */
public class ZipUtil {

    /**
     * 压缩文件或者目录
     *
     * @param baseDirName    压缩的根目录
     * @param fileName       根目录下待压缩的文件或文件夹名，
     *                       星号*表示压缩根目录下的全部文件。
     * @param targetFileName 目标ZIP文件
     */
    public static void zipFile(String baseDirName, String fileName,
                               String targetFileName) {
        //检测根目录是否存在
        if (baseDirName == null) {
            return;
        }
        File baseDir = new File(baseDirName);
        if (!baseDir.exists() || (!baseDir.isDirectory())) {
            return;
        }

        String baseDirPath = baseDir.getAbsolutePath();
        //目标文件
        File targetFile = new File(targetFileName);
        try {
            //创建一个zip输出流来压缩数据并写入到zip文件
            ZipOutputStream out = new ZipOutputStream(
                    new FileOutputStream(targetFile), Charset.forName("GBK"));
            if (fileName.equals("*")) {
                //将baseDir目录下的所有文件压缩到ZIP
                ZipUtil.dirToZip(baseDirPath, baseDir, out);
            } else {
                File file = new File(baseDir, fileName);
                if (file.isFile()) {
                    ZipUtil.fileToZip(baseDirPath, file, out);
                } else {
                    ZipUtil.dirToZip(baseDirPath, file, out);
                }
            }
            out.close();
        } catch (IOException e) {
            System.out.println("压缩失败：" + e);
            e.printStackTrace();
        }
    }

    /**
     * 解压缩ZIP文件，将ZIP文件里的内容解压到targetDIR目录下
     *
     * @param targetBaseDirName 目标目录
     * @paramzipName 待解压缩的ZIP文件名
     */
    public static void upzipFile(String zipFileName, String targetBaseDirName) {
        if (!targetBaseDirName.endsWith(File.separator)) {
            targetBaseDirName += File.separator;
        }
        try {
            //根据ZIP文件创建ZipFile对象
            ZipFile zipFile = new ZipFile(zipFileName);
            ZipEntry entry = null;
            String entryName = null;
            String targetFileName = null;
            byte[] buffer = new byte[4096];
            int bytes_read;
            //获取ZIP文件里所有的entry
            Enumeration entrys = zipFile.entries();
            //遍历所有entry
            while (entrys.hasMoreElements()) {
                entry = (ZipEntry) entrys.nextElement();
                //获得entry的名字
                entryName = entry.getName();
                targetFileName = targetBaseDirName + entryName;
                if (entry.isDirectory()) {
                    //  如果entry是一个目录，则创建目录
                    new File(targetFileName).mkdirs();
                    continue;
                } else {
                    // 如果entry是一个文件，则创建父目录
                    new File(targetFileName).getParentFile().mkdirs();
                }

                //否则创建文件
                File targetFile = new File(targetFileName);
                //打开文件输出流
                FileOutputStream os = new FileOutputStream(targetFile);
                //从ZipFile对象中打开entry的输入流
                InputStream is = zipFile.getInputStream(entry);
                while ((bytes_read = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytes_read);
                }
                //关闭流
                os.close();
                is.close();
            }

        } catch (IOException err) {
            System.err.println("解压缩文件失败: " + err);
        }
    }

    /**
     * 将目录压缩到ZIP输出流。
     */
    private static void dirToZip(String baseDirPath, File dir,
                                 ZipOutputStream out) {
        if (dir.isDirectory()) {
            //列出dir目录下所有文件
            File[] files = dir.listFiles();
            // 如果是空文件夹
            if (files.length == 0) {
                ZipEntry entry = new ZipEntry(getEntryName(baseDirPath, dir));
                // 存储目录信息
                try {
                    out.putNextEntry(entry);
                    out.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    //如果是文件，调用fileToZip方法
                    ZipUtil.fileToZip(baseDirPath, files[i], out);
                } else {
                    //如果是目录，递归调用
                    ZipUtil.dirToZip(baseDirPath, files[i], out);
                }
            }
        }
    }

    /**
     * 将文件压缩到ZIP输出流
     */
    private static void fileToZip(String baseDirPath, File file,
                                  ZipOutputStream out) {
        FileInputStream in = null;
        ZipEntry entry = null;
        // 创建复制缓冲区
        byte[] buffer = new byte[4096];
        int bytes_read;
        if (file.isFile()) {
            try {
                // 创建一个文件输入流
                in = new FileInputStream(file);
                // 做一个ZipEntry
                entry = new ZipEntry(getEntryName(baseDirPath, file));
                // 存储项信息到压缩文件
                out.putNextEntry(entry);
                // 复制字节到压缩文件
                while ((bytes_read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytes_read);
                }
                out.flush();
                out.closeEntry();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取待压缩文件在ZIP文件中entry的名字。即相对于跟目录的相对路径名
     *
     * @param baseDirPath
     * @param file
     * @return
     */
    private static String getEntryName(String baseDirPath, File file) {
        if (!baseDirPath.endsWith(File.separator)) {
            baseDirPath += File.separator;
        }
        String filePath = file.getAbsolutePath();
        // 对于目录，必须在entry名字后面加上"/"，表示它将以目录项存储。
        if (file.isDirectory()) {
            filePath += "/";
        }
        int index = filePath.indexOf(baseDirPath);
        return filePath.substring(index + baseDirPath.length());
    }

    public static void main(String[] args) {
        //压缩C盘下的temp目录，压缩后的文件是C:/temp.zip
        String baseDirName = "E:/";
        String fileName = "canshu.xls";
        String zipFileName = "E:/canshu.zip";
        ZipUtil.zipFile(baseDirName, fileName, zipFileName);
        //将刚创建的ZIP文件解压缩到D盘的temp目录下
        System.out.println();
        fileName = "e:/text.xls";
        ZipUtil.upzipFile(zipFileName, fileName);
    }


    public static StringBuffer zip(String str) {
        if (str == null)
            return null;
        byte[] compressed;
        ByteArrayOutputStream out = null;
        ZipOutputStream zout = null;
        StringBuffer compressedStr = new StringBuffer();
        try {
            out = new ByteArrayOutputStream();
            zout = new ZipOutputStream(out);
            zout.putNextEntry(new ZipEntry("0"));
            zout.write(str.getBytes());
            zout.closeEntry();
            compressed = out.toByteArray();
            compressedStr.append(compressed);
        } catch (IOException e) {
            compressed = null;
        } finally {
            if (zout != null) {
                try {
                    zout.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return compressedStr;
    }

    /**
     * 使用zip进行解压缩
     *
     * @param
     * @return 解压后的字符串
     */
    public static String unzip(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }

        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        ZipInputStream zin = null;
        String decompressed = null;
        try {
            byte[] compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
            out = new ByteArrayOutputStream();
            in = new ByteArrayInputStream(compressed);
            zin = new ZipInputStream(in);
            zin.getNextEntry();
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = zin.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
            decompressed = null;
        } finally {
            if (zin != null) {
                try {
                    zin.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return decompressed;
    }


    public static String readZipFile(String zipFile) {
        String content = null;
        try {
            ZipFile file = new ZipFile(zipFile);
            InputStream is = new BufferedInputStream(new FileInputStream(zipFile));
            ZipInputStream zis = new ZipInputStream(is);

            ZipEntry zipEntry = null;
            while ((zipEntry = zis.getNextEntry()) != null) {
                if (!zipEntry.isDirectory()) {
                    long size = file.size();
                    if (size > 0) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(zipEntry)));
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            content += line;
                        }
                        reader.close();
                    }
                }
            }
            zis.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static byte[] readZipFileByte(String zipFile) {
        try {
            ZipFile file = new ZipFile(zipFile);
            byte[] content = new byte[file.size()];
            InputStream is = new BufferedInputStream(new FileInputStream(zipFile));
            ZipInputStream zis = new ZipInputStream(is);

            ZipEntry zipEntry = null;
            while ((zipEntry = zis.getNextEntry()) != null) {
                if (!zipEntry.isDirectory()) {
                    long size = file.size();
                    if (size > 0) {
                        //BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(zipEntry)));
                        InputStream ips = file.getInputStream(zipEntry);
                        ips.read(content, 0, file.size());
                        // reader.read(content,0,file.size());
                        // reader.close();
                        ips.close();
                    }
                }
            }
            zis.closeEntry();
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] zip(byte[] data) {
        byte[] b = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ZipOutputStream zip = new ZipOutputStream(bos);
            ZipEntry entry = new ZipEntry("zip");
            entry.setSize(data.length);
            zip.putNextEntry(entry);
            zip.write(data);
            zip.closeEntry();
            zip.close();
            b = bos.toByteArray();
            bos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }

    /***
     * 解压Zip
     *
     * @param data
     * @return
     */
    public static byte[] unZip(byte[] data) {
        byte[] b = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            ZipInputStream zip = new ZipInputStream(bis);
            while (zip.getNextEntry() != null) {
                byte[] buf = new byte[1024];
                int num = -1;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while ((num = zip.read(buf, 0, buf.length)) != -1) {
                    baos.write(buf, 0, num);
                }
                b = baos.toByteArray();
                baos.flush();
                baos.close();
            }
            zip.close();
            bis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }


    public static byte[] readFileByte(String file) {
        FileInputStream fis = null;
        BufferedInputStream br = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(file);
            br = new BufferedInputStream(fis);
            bos = new ByteArrayOutputStream();
            int count = 0;
            byte[] data = new byte[1024];
            while ((count = br.read(data, 0, 1024)) != -1) {
                bos.write(data, 0, count);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null)
                    bos.close();
                if (br != null)
                    br.close();
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bos.toByteArray();
    }

}
