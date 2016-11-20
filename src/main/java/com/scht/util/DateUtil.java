package com.scht.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    public static final String default_pattern = "yyyy-MM-dd HH:mm:ss";
    public static String min_pattern = "MM-dd HH:mm";
    public static String pattern_10 = "yyyy-MM-dd";
    public static String pattern_13 = "yyyy-MM-dd HH";
    public static String pattern_16 = "yyyy-MM-dd HH:mm";
    public static String pattern_19 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 相差天数
     * @param date1
     * @param date2
     * @return
     */
    public static int diffDays(Date date1,Date date2){
        Calendar aCalendar = Calendar.getInstance();

        aCalendar.setTime(date1);

        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

        aCalendar.setTime(date2);

        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

        int i = day2 - day1;
        return (i<0?-i:i);

    }

    public static String getDateFromLong(Long time) {
        Date date = new Date(time);
        return getFormatDate(date, null);
    }

    public static Date getFormatDate(Calendar calendar, int i) {
        calendar.add(Calendar.DAY_OF_MONTH, i);
        return calendar.getTime();
    }

    /**
     * 将日期增加num，发生在field字段上，然后转化为指定的日期格式
     *
     * @param date
     * @param field
     * @param num
     * @return
     * @throws ParseException
     */
    public static Date makeDiffDate(Date date, int field, int num) throws ParseException {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(field, calendar.get(field) + num);
        return calendar.getTime();
    }

    /**
     * 得到过去1分钟的秒数
     *
     * @return
     */
    public static List<Date> getPassedDayMin() {
        List<Date> list = new ArrayList<Date>();

        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < 60; i++) {
            cal.add(Calendar.SECOND, 1);
            list.add(cal.getTime());
        }
        return list;
    }

    /**
     * 得到过去years的年份
     * @param years
     * @return
     */
    public static List<String> getPassedYear(int years) {
        List<String> list = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        list.add(cal.get(Calendar.YEAR)+"");
        for (int i = 0; i < years-1; i++) {
            cal.add(Calendar.YEAR, -1);
            list.add(cal.get(Calendar.YEAR)+"");
        }
        return list;
    }

    public static String getYear(int i){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, i);
        return cal.get(Calendar.YEAR)+"";
    }

    public static List<Integer> getRandomData() {
        List<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < 60; i++) {
            list.add(((int) (Math.random() * 1000)));
        }

        return list;
    }

    /**
     * 格式化日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getFormatDate(Date date, String pattern) {
        if (pattern == null) {
            pattern = default_pattern;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 转换字符串日期的格式
     *
     * @param date
     * @param srcFormat
     * @param dstFormat
     * @return
     */
    public static String changeDateStrFormat(String date, String srcFormat, String dstFormat) {
        try {
            if (srcFormat == null)
                srcFormat = default_pattern;
            SimpleDateFormat sdf = new SimpleDateFormat(srcFormat);
            Date transDate = sdf.parse(date);
            sdf = new SimpleDateFormat(dstFormat);
            return sdf.format(transDate);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 将字符串按照指定或者默认的格式转化为date型
     *
     * @return
     * @throws ParseException
     */
    public static Date makeStr2Date(String date, String datePartten) throws ParseException {
        if (datePartten == null || "".equals(datePartten.trim())) {
            datePartten = default_pattern;
        }
        SimpleDateFormat format = new SimpleDateFormat(datePartten);
        if (date == null || "".equals(date.trim())) {
            return new Date();
        }
        return format.parse(date);
    }

    public static String getCurrentDate() {
        return StringUtil.getDataFormat(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
    }


    public static Long getLongFormStrDate(String date) {
        return getLongFormStrDate(date, default_pattern);
    }

    public static Long getLongFormStrDate(String beginDate, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date transDate = sdf.parse(beginDate);
            return transDate.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date longToDate(Long l){
        Date date = new Date();
        date.setTime(l);
        return date;
    }

    public static void main(String[] args) {
//	//	System.out.println(gtPassedDayMin());
//		Date date = new Date(1381470000000L);
//		System.out.println(getFormatDate(date, null));
        System.out.println(getFormatDate(new Date(), "yyyyMMddHHmmssSSS"));
       // System.out.println(diffDays(new Date(),longToDate(1456469874127L)));
        System.out.println(DateUtil.getDateFromLong(1471417920929L));

        Calendar cal = Calendar.getInstance();
        //cal.add(Calendar.HOUR,-1);

        //System.out.println(getDateFromLong(cal.getTimeInMillis()));
        getPassedYear(10);
        System.out.println(getYear(-1));
    }
}
