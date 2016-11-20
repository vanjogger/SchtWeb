package com.scht.util;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil {

    public static String DateFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String GET = "get";

    /**
     * find property get method name eg: property: name return: getName
     *
     * @param property pro
     * @return getPro
     */
    public static String findGetPro(String property) {
        String preffix = property.substring(0, 1);
        String newPro = preffix.toUpperCase() + property.substring(1);
        return GET + newPro;
    }

    /**
     * 判断传入的字符串是否为null或""
     *
     * @param str
     * @return
     */
    public static boolean isNotNull(String str) {
        if (str != null && !str.equals(""))
            return true;
        return false;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 拼装sql查询时的日期
     *
     * @param dateStr
     * @return
     */
    public static String getOracleDate(String dateStr) {
        StringBuffer sb = new StringBuffer();
        sb.append("to_date('");
        sb.append(dateStr);
        sb.append("','yyyy-MM-dd hh24:mi:ss')");
        return sb.toString();
    }

    public static Object getPercentStr(int count) {
        return null;
    }

    /**
     * 将字符串转成指定格式的日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date getDateFromStr(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式化日期 将日期转换成字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getDateFormat(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String getDataFormat(Long date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date(date));
    }

    /**
     * 得到指定日期前几天或后几天的日期
     *
     * @param date
     * @param i
     * @param flag true：前i天 false：后i天
     * @return
     */
    public static String getCompareDate(String date, int i, boolean flag, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date current = sdf.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(current);
            if (flag) {
                cal.add(Calendar.DAY_OF_MONTH, i);
            } else {
                cal.add(Calendar.DAY_OF_MONTH, -i);
            }
            return sdf.format(cal.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到指定日期前几天或后几天的日期
     *
     * @param date
     * @param i
     * @param flag true：前i天 false：后i天
     * @return
     */
    public static String getCompareDate(Date date, int i, boolean flag, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            if (flag) {
                cal.add(Calendar.DAY_OF_MONTH, i);
            } else {
                cal.add(Calendar.DAY_OF_MONTH, -i);
            }
            return sdf.format(cal.getTime());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Long[] getLongArrFromStr(String subarea) {
        if (subarea != null) {
            String[] arrs = subarea.split(",");
            if (arrs.length > 0) {
                Long[] longarr = new Long[arrs.length];
                if (arrs != null && arrs.length > 0) {
                    for (int i = 0; i < arrs.length; i++) {
                        if (arrs[i] != "") {
                            longarr[i] = Long.valueOf(arrs[i]);
                        }
                    }
                    return longarr;
                }
            }
        }
        return null;
    }

    public static Integer[] getIntegerArrFromStr(String subarea) {
        if (subarea != null) {
            String[] arrs = subarea.split(",");
            Integer[] longarr = new Integer[arrs.length];
            if (arrs != null && arrs.length > 0) {
                for (int i = 0; i < arrs.length; i++) {
                    longarr[i] = Integer.valueOf(arrs[i]);
                }
                return longarr;
            }
        }
        return null;
    }

    public static String getArrIndex(String[] arr, int i) {
        if (arr.length > i) {
            return arr[i];
        }
        return "";
    }

    /**
     * 得到日期所在的星期数
     *
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return cal.get(Calendar.DAY_OF_WEEK);

    }

    public static int getHoursFromDates(Date sdate, Date eDate) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(sdate);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(eDate);

        long aa = calendar2.getTimeInMillis() - calendar1.getTimeInMillis();
        int index = 0;
        if (aa % (1000 * 60) != 0) {
            index = 1;// 大于1分钟的 默认为1个小时
        }
        return (int) aa / (1000 * 60 * 60) + index;
    }

    public static List<Date> getMinsList(Date date, int hours) {
        List<Date> list = new ArrayList<Date>();
        for (int i = hours * 60; i >= 0; i--) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MINUTE, -i);
            list.add(cal.getTime());
        }

        return list;
    }

    /**
     * 得到i天前从0时0分到现在的分钟数
     *
     * @param date
     * @param i
     * @return
     */
    public static List<Date> getYesMinsList(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -i);
        Calendar inst = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

        List<Date> list = new ArrayList<Date>();
        int index = 1;
        list.add(inst.getTime());
        while (true) {
            inst.add(Calendar.MINUTE, index);
            // 注意不能使用date来比较 用字符串来比对
            if (getDateFormat(inst.getTime(), "yyyy-MM-dd HH:mm").equals(getDateFormat(date, "yyyy-MM-dd HH:mm"))) {
                list.add(inst.getTime());
                break;
            }

            list.add(inst.getTime());
        }
        return list;
    }

    public static Long getYMax(Long max) {
        int length = max.toString().length();
        if (length > 0) {
            long bcs = 1L;
            for (int i = 0; i < length - 1; i++) {
                bcs *= 10;
            }
            if (max % bcs != 0) {
                return (max / bcs + 1) * bcs;
            } else {
                return (max / bcs) * bcs;
            }
        }
        return 0L;
    }

    public static boolean writeToTxt(String filePath, String text) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
            FileWriter writer = new FileWriter(file);
            writer.write(text);
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getLasttime(Date date, Date startDate) {
        long diff = date.getTime() - startDate.getTime();
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数

        StringBuffer str = new StringBuffer();
        long day = diff / nd;
        long hour = diff % nd / nh;
        long min = diff % nd % nh / nm;
        long sec = diff % nd % nh % nm / ns;
        if (day > 0) {
            str.append(day + "天");
        }
        if (hour > 0) {
            str.append(hour + "小时");
        }
        if (min > 0) {
            str.append(min + "分钟");
        }
        str.append(sec + "秒");

        return str.toString();
    }

    public static String getLasttimeFormDuration(long dur) {

        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数

        StringBuffer str = new StringBuffer();
        long day = dur / nd;
        long hour = dur % nd / nh;
        long min = dur % nd % nh / nm;
        long sec = dur % nd % nh % nm / ns;
        if (day > 0) {
            str.append(day + "天");
        }
        if (hour > 0) {
            str.append(hour + "小时");
        }
        if (min > 0) {
            str.append(min + "分钟");
        }
        str.append(sec + "秒");

        return str.toString();
    }

    public static byte[] hex2byte(String str) { // 十六进制字符串转二进制
        if (str == null)
            return null;
        str = str.trim();
        int len = str.length();

        if (len == 0 || len % 2 == 1)
            return null;

        byte[] b = new byte[len / 2];
        try {
            for (int i = 0; i < str.length(); i += 2) {
                b[i / 2] = (byte) Integer.decode("0x" + str.substring(i, i + 2)).intValue();
            }
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 二进制转十六进制
     *
     * @param buf
     * @return
     */
    public static String asHex(byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;

        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10)
                strbuf.append("0");

            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }

        return strbuf.toString();
    }

    /**
     * 十六进制转二进制
     *
     * @param src
     * @return
     */
    public static byte[] asBin(String src) {
        if (src.length() < 1)
            return null;
        byte[] encrypted = new byte[src.length() / 2];
        for (int i = 0; i < src.length() / 2; i++) {
            int high = Integer.parseInt(src.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(src.substring(i * 2 + 1, i * 2 + 2), 16);

            encrypted[i] = (byte) (high * 16 + low);
        }
        return encrypted;


    }


    /**
     * 得到length位的随机码
     *
     * @param length
     * @return
     */
    public static String getRandom(int length) {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < length; i++) {
            str.append((int) (Math.random() * 10) + "");
        }
        return str.toString();
    }


    public static String getDoubleFormat(double tapeWidth) {
        DecimalFormat format = new DecimalFormat("0.00");
        return format.format(tapeWidth);
    }

    /**
     * 对空字符串进行指定替换
     *
     * @param source
     * @param dest
     * @return
     */
    public static String replaceNull(String source, String dest) {
        if (source == null || "".equals(source) || "null".equals(source))
            return dest;
        return source;
    }

    /**
     * 将集合转化为字符串返还用","隔开
     *
     * @param list
     * @return
     */
    public static String coverList2String(List<String> list) {
        if (list == null || list.size() < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                sb.append(list.get(i));
            } else {
                sb.append(list.get(i)).append(",");
            }
        }
        return sb.toString();
    }

    /**
     * 将字符串数组转化成字符串
     *
     * @param target
     * @param con
     * @return
     */
    public static String coverArray2String(String[] target, String con) {
        if (target == null || target.length < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < target.length - 1; i++) {
            sb.append(target[i]).append(con);
        }
        sb.append(target[target.length - 1]);
        return sb.toString();
    }

    /**
     * 将字符串数组转化成字符串
     *
     * @param target
     * @return
     */
    public static String coverArray2String(String[] target) {
        return coverArray2String(target, ",");
    }

    /**
     * 将number数组转化成字符串数组
     *
     * @param
     * @return
     */
    public static String[] coverNumberArray2StringArray(int[] source) {
        String[] value = new String[source.length];
        for (int i = 0; i < source.length; i++) {
            value[i] = String.valueOf(source[i]);
        }
        return value;
    }

    public static String converObject2String(Object[] objs) {
        String value = "";
        for (int i = 0; i < objs.length; i++) {
            value += String.valueOf(objs[i]) + ",";
        }
        /*if (value.endsWith(",")) {
            value = value.substring(0, value.length()-1);
		}*/
        return value;
    }

    public static void print(Object... args) {
        for (int i = 0; i < args.length; i++) {
            System.out.print(args[i] + " ");
        }
        System.out.println();
    }

    public static void println(Object... args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
    }

    public static boolean isNotEmpty(Collection<?> obj) {
        // TODO Auto-generated method stub
        if (obj != null && obj.size() > 0)
            return true;
        return false;
    }


    public static String getRandom() {
        return (int) (Math.random() * 100) + "";
    }

    /**
     * 得到本机ip   使用linux系统
     *
     * @return
     */
    public static String getLocatlIP() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostAddress().toString();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return null;
    }

    /**
     * IP地址转换long
     *
     * @param ip
     * @return
     */
    public static Long ipToLong(String ip) {
        long[] ips = new long[4];
        String[] ipArrs = ip.split("\\.");
        if (ipArrs.length == 4) {
            return (Long.parseLong(ipArrs[0]) << 24) + (Long.parseLong(ipArrs[1]) << 16) + (Long.parseLong(ipArrs[2]) << 8) + (Long.parseLong(ipArrs[3]));
        }
        return null;
    }

    //Long值 转换为ip
    public static String LongToIp(Long longIp) {
        StringBuffer sb = new StringBuffer("");
        //直接右移24位
        sb.append(String.valueOf((longIp >>> 24)));
        sb.append(".");
        //将高8位置0，然后右移16位
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
        sb.append(".");
        //将高16位置0，然后右移8位
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
        sb.append(".");
        //将高24位置0
        sb.append(String.valueOf((longIp & 0x000000FF)));
        return sb.toString();
    }

    /**
     * @param (：长度是以byte为单位的，一个汉字是2个byte)
     * @param symbol                      用于表示省略的信息的字符，如“...”等。
     * @return 返回处理后的字符串
     */
    public static String getLimitLengthString(String str, int toCount, String symbol) {
        int reInt = 0;
        String reStr = "";
        if (str == null)
            return "";
        char[] tempChar = str.toCharArray();
        for (int kk = 0; (kk < tempChar.length && toCount > reInt); kk++) {
            String s1 = String.valueOf(tempChar[kk]);
            byte[] b = s1.getBytes();
            reInt += b.length;
            reStr += tempChar[kk];
        }
        if (toCount == reInt || (toCount == reInt - 1))
            reStr += symbol;
        return reStr;
    }

    /**
     * 判断是否为合法IP
     *
     * @return the ip
     */
    public static boolean isIp(String ipAddress) {
        if (isNullOrEmpty(ipAddress)) return false;
        String ip = "([1-9]|[1-9]//d|1//d{2}|2[0-4]//d|25[0-5])(//.(//d|[1-9]//d|1//d{2}|2[0-4]//d|25[0-5])){3}";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }

    /**
     * 字符串转化为二进制字符串
     */
    public static String StrToBinstr(String str, String split) {
        char[] chars = str.toCharArray();
        String result = "";
        for (int i = 0; i < chars.length; i++) {
            result += Integer.toBinaryString(chars[i]) + split;
        }
        try {
            return new String(result.getBytes(), "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //得到随机字符串
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static byte[] getImgByte(String file) {
        InputStream is = null;
        byte[] data = null;
        try {
            is = new FileInputStream(file);
            data = new byte[is.available()];
            is.read(data);
            is.close();

            // BASE64Encoder encode = new BASE64Encoder();
            //return   Base64Util.encodeByte(data);
            return data;
            //return encode.encode(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //图片转换为base64字符串
    public static String getBase64Str(String file) {
        InputStream is = null;
        byte[] data = null;
        try {
            is = new FileInputStream(file);
            data = new byte[is.available()];
            is.read(data);
            is.close();

            // BASE64Encoder encode = new BASE64Encoder();
            //return   Base64Util.encodeByte(data);
            return Base64.encodeBase64String(data);
            //return encode.encode(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //base64字符串转图片
    public static boolean parseBase64ToImg(String base64Str, String file) {
        if (StringUtil.isNotNull(base64Str)) {
            try {
                byte[] buffer = Base64.decodeBase64(base64Str);
                for (int i = 0; i < buffer.length; i++) {
                    if (buffer[i] < 0) {
                        buffer[i] += 256;
                    }
                }
                OutputStream os = new FileOutputStream(file);
                os.write(buffer);
                os.flush();
                os.close();
                ;
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 截取end
     *
     * @param str
     * @param end
     * @return
     */
    public static String splitEnd(String str, String end) {
        if (StringUtil.isNotNull(str) && StringUtil.isNotNull(end)) {
            if (str.indexOf(end) != -1) {
                str = str.substring(0, str.length() - end.length());
            }
        }
        return str;
    }

    /**
     * 得到class文件根目录
     *
     * @return
     */
    public static String getSystemClassPath() {
        return StringUtil.class.getResource("/").getPath();
    }

    /**
     * 得到web目录
     *
     * @return
     */
    public static String getSystemWebPath() {
        String classFile = getSystemClassPath();
        String indexOf = null;
        if (classFile.contains("\\")) {//window系统
            indexOf = "\\";
        } else if (classFile.contains("/")) {//linux系统
            indexOf = "/";
        }
        //截取三次后为web目录
        for (int i = 0; i < 3; i++) {
            int index = classFile.lastIndexOf(indexOf);
            classFile = classFile.substring(0, index);
        }
        return classFile;
    }

    //转化字符串为十六进制编码
    public static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

    // 转化十六进制编码为字符串
    public static String toStringHex1(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
                        i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "utf-8");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    public static void saveToImgFile(String src, String output) {
        if (src == null || src.length() == 0) {
            return;
        }
        try {
            FileOutputStream out = new FileOutputStream(new File(output));
            byte[] bytes = src.getBytes();
            for (int i = 0; i < bytes.length; i += 2) {
                out.write(charToInt(bytes[i]) * 16 + charToInt(bytes[i + 1]));
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int charToInt(byte ch) {
        int val = 0;
        if (ch >= 0x30 && ch <= 0x39) {
            val = ch - 0x30;
        } else if (ch >= 0x41 && ch <= 0x46) {
            val = ch - 0x41 + 10;
        }
        return val;
    }

    public static void main(String[] args) {
        System.out.println("*************  "+StringNumber.pow(2,10));

    }



    /**
     * 十六进制数据转换成图片
     * 首先将十六进制数据转成二进制数据  由二进制数据转换成图片文件
     *
     * @param str
     * @param file
     * @return
     */
    public static boolean parseHex2Img(String str, String file) {
        byte[] bytes1 = hex2byte(str);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
        BufferedImage bi1 = null;
        try {
            bi1 = ImageIO.read(bais);
            File w2 = new File(file);//可以是jpg,png,gif格式
            ImageIO.write(bi1, "jpg", w2);//不管输出什么格式图片，此处不需改动
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * 二进制字符串转十六进制字符串
     *
     * @param bString
     * @return
     */
    public static String binaryString2hexString(String bString) {
        if (bString == null || bString.equals("") || bString.length() % 8 != 0)
            return null;
        StringBuffer tmp = new StringBuffer();
        int iTmp = 0;
        for (int i = 0; i < bString.length(); i += 4) {
            iTmp = 0;
            for (int j = 0; j < 4; j++) {
                iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);
            }
            tmp.append(Integer.toHexString(iTmp));
        }
        return tmp.toString();
    }

    /**
     * 十六进制字符串转二进制字符串
     *
     * @param hexString
     * @return
     */
    public static String hexString2binaryString(String hexString) {
        if (hexString == null || hexString.length() % 2 != 0)
            return null;
        String bString = "", tmp;
        for (int i = 0; i < hexString.length(); i++) {
            tmp = "0000"
                    + Integer.toBinaryString(Integer.parseInt(hexString
                    .substring(i, i + 1), 16));
            bString += tmp.substring(tmp.length() - 4);
        }
        return bString;
    }

    public static String getDomain(String url,String uri){
        return url.substring(0,url.lastIndexOf(uri));
    }


    public static String getLocalIP(){
        String ip  = null;
        Enumeration allNetInterfaces;
        try{
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while(allNetInterfaces.hasMoreElements()){
                NetworkInterface networkInterface = (NetworkInterface) allNetInterfaces.nextElement();
                List<InterfaceAddress> list = networkInterface.getInterfaceAddresses();
                for(InterfaceAddress address:list){
                    InetAddress net = address.getAddress();
                    if(net!=null && net instanceof Inet4Address){
                        ip = net.getHostAddress();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return ip;
    }


    /**
     * map转换为对象
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
                continue;
            }

            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }

        return obj;
    }

    /**
     * 对象转换为map
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if(obj == null){
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            System.out.println(field.getName() + " :: " + field.get(obj));
            if(field.get(obj)!=null)
                map.put(field.getName(), field.get(obj)+"");
        }

        return map;
    }

    /**
     * 格式化数字
     * @param f
     * @return
     */
    public static String formatFloat(Double f){
        if(f!=null) {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            return decimalFormat.format(f);
        }
        return "0";
    }
}
