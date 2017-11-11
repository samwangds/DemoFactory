package demo.com.sam.demofactory.java.thinkinginjava;

import android.text.TextUtils;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期工具类
 *
 * @author Chenyw
 *         Created on 2015/6/19.
 * @description
 */
public class TimeUtil {
    public static final long MINUTE_MILLIS = 60 * 1000L;
    public static final long DAY_MILLIS = 24 * 60 * MINUTE_MILLIS;

    public final static String FORMAT_DATE = "yyyy年 MM月dd日 HH:mm";
    public final static String FORMAT_DATE1 = "HH时mm分ss秒_yyyy-MM-dd";
    public final static String FORMAT_DATE2 = "yyyy-MM-dd";
    public final static String FORMAT_DATE3 = "yyMMdd";
    public final static String FORMAT_DATE4 = "HH:mm";
    public final static String FORMAT_DATE5 = "yyyy年 MM月dd日 ";
    public final static String FORMAT_DATE6 = "yyyy年MM月dd日";
    public final static String FORMAT_DATE8 = "yyyy";
    public final static String FORMAT_DATE7 = "MM-dd";
    public final static String FORMAT_DATE9 = "yyyy/MM/dd";
    public final static String FORMAT_DATE10 = "yyyy-MM-dd HH:mm";
    public final static String FORMAT_DATE11 = "MM月dd日";
    public final static String FORMAT_DATE12 = "yyyyMMdd";
    public final static String FORMAT_DATE13 = "MM月dd日 hh:mm";
    public final static String FORMAT_DATE14 = "yyyy年  MM月dd日  HH时mm分";
    public final static String FORMAT_DATE15 = "yyyy-MM-dd";
    public static final String BEIJING_TIMEZONE_ID = "Asia/Shanghai";


    /**
     * Timestamp 转 String 格式：yyyy年 MM月dd日 HH:mm
     *
     * @param timestamp
     * @return
     */
    public static String formatDate(long timestamp) {
        DateFormat format = new SimpleDateFormat(FORMAT_DATE);
        try {
            return format.format(timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 格式：yyyy -> long
     *
     * @return
     */
    public static long formatYear2Long(int year) {
        DateFormat format = new SimpleDateFormat(FORMAT_DATE8);
        try {
            Date date = format.parse(String.valueOf(year));
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Timestamp 转 String
     *
     * @param timestamp 格式：yyyy-MM-dd
     * @return
     */
    public static String formatDateForYearMonthDay(long timestamp) {
        DateFormat format = new SimpleDateFormat(FORMAT_DATE2);
        try {
            return format.format(timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Timestamp 转 String
     *
     * @param timestamp 格式：yyyy-MM-dd HH:mm
     * @return
     */
    public static String formatDateForYearMonthDayHourMin(long timestamp) {
        DateFormat format = new SimpleDateFormat(FORMAT_DATE10);
        try {
            return format.format(timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Timestamp 转 String
     *
     * @param timestamp
     * @return
     */
    public static String formatDate(long timestamp, String dateFormat) {
        DateFormat format = new SimpleDateFormat(dateFormat);
        try {
            return format.format(timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param timestamp
     * @param dateFormat
     * @param timeZone 时区
     * @return
     */
    public static String formatDate(long timestamp, String dateFormat, String timeZone) {
        if (TextUtils.isEmpty(timeZone)) {
            return formatDate(timestamp, dateFormat);
        }
        DateFormat format = new SimpleDateFormat(dateFormat);
        format.setTimeZone(TimeZone.getTimeZone(timeZone));
        try {
            return format.format(timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * year month day 转 String
     *
     * @param year
     * @param month
     * @param day
     * @param dateFormat
     * @return String
     */
    public static String formatDate(int year, int month, int day, String dateFormat) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);//设置年
        calendar.set(Calendar.MONTH, month - 1);//设置月 PS:月份从0开始
        calendar.set(Calendar.DATE, day);//设置日
        calendarDayTime(calendar);
        DateFormat format = new SimpleDateFormat(dateFormat);
        try {
            return format.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 格式化时间为HH:mm:ss
     *
     * @param time
     * @return
     */
    public static String formatTime(long time) {
       /* SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
        Date date = new Date(time);*/
        StringBuffer stringBuffer = new StringBuffer();

        long hour = time / (3600 * 1000);
        long minute = (time - hour * 3600 * 1000) / (60 * 1000);
        long second = (time - hour * 3600 * 1000 - minute * 60 * 1000) / 1000;

        stringBuffer.append(String.format("%02d", hour));
        stringBuffer.append(":");
        stringBuffer.append(String.format("%02d", minute));
        stringBuffer.append(":");
        stringBuffer.append(String.format("%02d", second));

        // String formatTime = formatter.format(date);
        String formatTime = stringBuffer.toString();
        return formatTime;
    }

    /**
     * 组装日期
     *
     * @param year
     * @param day
     * @param month
     * @return
     */
    public static String date2YYYYMMDD(int year, int month, int day) {
        return formatDate(year, month, day, FORMAT_DATE2);
    }

    /**
     * 组装日期
     *
     * @param year
     * @param day
     * @param month
     * @return
     */
    public static String buildYYYYMMDD(int year, int month, int day) {
        if (year != 0) {
            return year + "-" + month + "-" + day;
        }
        return month + "-" + day;
    }

    /**
     * 组装日期
     *
     * @param timestamp
     * @return
     */
    public static String date2YYYYMMDD(long timestamp) {
        return formatDate(timestamp, FORMAT_DATE2);
    }

    /**
     * 组装日期
     *
     * @param day
     * @param month
     * @return
     */
    public static String date2MMDD(int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());//设置当前时间

        return formatDate(calendar.get(Calendar.YEAR), month, day, FORMAT_DATE2);
    }

    /**
     * 字符串转成long
     *
     * @param dateString 时间
     * @param formatStr  时间格式
     * @return
     */
    public static long string2Date(String dateString, String formatStr) {
        DateFormat format = new SimpleDateFormat(formatStr);
        try {
            Date date = format.parse(dateString);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 根据具体日期获取当天时周几
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static int getWhichWeek(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);//设置年
        calendar.set(Calendar.MONTH, month);//设置月
        calendar.set(Calendar.DATE, day);//设置日
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取距离天数
     *
     * @param tStart
     * @param tEnd
     * @return
     */
    public static int getDeltaDays(long tStart, long tEnd) {
        if (tStart == tEnd) {
            return 0;
        }
        tStart = getFirstMilliSecendOfDay(tStart);
        tEnd = getFirstMilliSecendOfDay(tEnd);
        long absDeltaMs = Math.abs(tStart - tEnd);
        long days = absDeltaMs / DAY_MILLIS;
        if (tStart > tEnd) {
            days = -days;
        }
        long offset = absDeltaMs % DAY_MILLIS;

        if (offset == 0) {
            return (int) days;
        }
//
        //计算误差
        Calendar startC = Calendar.getInstance();
        startC.setTimeInMillis(tStart);
        startC.add(Calendar.DATE, (int) days);

        Calendar endC = Calendar.getInstance();
        endC.setTimeInMillis(tEnd);

        while(startC.get(Calendar.DAY_OF_MONTH)!=endC.get(Calendar.DAY_OF_MONTH)){
            startC.add(Calendar.DATE,days>0? 1:-1);
            if (days > 0) {
                days++;
            } else {
                days--;
            }
        }

        return (int) days;
    }

    /**
     * 获取当天0时
     *
     * @param tStart
     * @return
     */
    public static long getFirstMilliSecendOfDay(long tStart) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(tStart);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        return instance.getTimeInMillis();
    }

    /**
     * 获取时间戳
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static long getTimestamp(int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取当前年
     *
     * @return
     */
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    /**
     * 获取当前月
     *
     * @return
     */
    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;//月份从0开始
    }



    /**
     * 返回年月日
     *
     * @param timestamp
     * @return year, month, day
     */
    public static int[] parseTime(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        int[] times = new int[3];
        times[0] = calendar.get(Calendar.YEAR);
        times[1] = calendar.get(Calendar.MONTH);
        times[2] = calendar.get(Calendar.DAY_OF_MONTH);
        return times;
    }

    public static String callTimeFormat(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        SimpleDateFormat format = new SimpleDateFormat(TimeUtil.FORMAT_DATE11);
        StringBuilder sb = new StringBuilder(format.format(time));
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String minuteStr = String.valueOf(minute);
        if (minuteStr.length() == 1) {
            minuteStr = "0" + minuteStr;
        }
        sb.append(" ");
        if (hour < 6) {
            sb.append("凌晨");
            sb.append(hour)
                    .append(":")
                    .append(minuteStr);
        } else if (hour < 12) {
            sb.append("早上");
            sb.append(hour)
                    .append(":")
                    .append(minuteStr);
        } else if (hour < 14) {
            sb.append("中午");
            sb.append(hour)
                    .append(":")
                    .append(minuteStr);
        } else if (hour < 18) {
            sb.append("下午");
            sb.append(hour)
                    .append(":")
                    .append(minuteStr);
        } else if (hour < 25) {
            sb.append("晚上");
            sb.append(hour)
                    .append(":")
                    .append(minuteStr);
        }
        return sb.toString();
    }

    /**
     * 根据偏移获取某天的时间戳，比如昨天，就传-1，前天传-2
     *
     * @param offset
     * @return
     */
    public static long getAppointDay(int offset) {
        Calendar calendar = Calendar.getInstance();
        calendarDayTime(calendar);
        calendar.add(Calendar.DATE, offset);//设置日
        return calendar.getTimeInMillis();
    }

    public static long getDay(int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1970, Calendar.JANUARY, 1);
        calendarDayTime(calendar);
        calendar.add(Calendar.DATE, offset);//设置日
        calendar.getTime().toString();
        return calendar.getTimeInMillis();
    }

    public static String getDateByOffset(int offset) {
//        long day = getDay(offset);
        Calendar calendar = Calendar.getInstance();
        calendar.set(1970, Calendar.JANUARY, 1);
        calendarDayTime(calendar);
        calendar.add(Calendar.DATE, offset);//设置日
        return String.format("%04d-%02d-%02d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH));

//        return TimeUtil.formatDate(day, TimeUtil.FORMAT_DATE2);
    }

    /**
     * 获取当天的天数偏移量 从 （1970.1.1）
     * @return currentDayOffset
     */

    public static int currentDayOffset() {
        return TimeUtil.getDeltaDays(getFirstDayTime(), TimeUtil.currentDay2Long());
    }

    /**
     * 获取当天日期
     * @return currentDayOffset
     */

    public static long currentDay2Long() {
        Calendar calendar = Calendar.getInstance();
        calendarDayTime(calendar);
        return calendar.getTimeInMillis();
    }


    /**
     * 获取当天的天数偏移量 从 （1970.1.1）
     *@param timeStamp
     * @return currentDayOffset
     */

    public static int getDayOffset(long timeStamp) {
        return TimeUtil.getDeltaDays(getFirstDayTime(), timeStamp);
    }

    /**
     * @return 1970-1-1的时间
     */
    public static long getFirstDayTime() {
        return getDay(0);
    }

    public static int getDayOffset(String yyyy_mm_dd) {
        return getDayOffset(getYYYY_MM_DDtime(yyyy_mm_dd));
    }

    public static long getYYYY_MM_DDtime(String yyyy_mm_dd) {
        int[] ints = dateSplitInt(yyyy_mm_dd);
        Calendar calendar = Calendar.getInstance();
        calendar.set(ints[0], ints[1]-1, ints[2]);
        calendarDayTime(calendar);
        return calendar.getTimeInMillis();
    }

    /**
     * @param yymmdd yyyy-MM-dd
     * @return
     */
    public static long getBeiJingTime(String yymmdd) {
        DateFormat format = new SimpleDateFormat(FORMAT_DATE2);
        try {
            format.setTimeZone(TimeZone.getTimeZone(BEIJING_TIMEZONE_ID));
            Date date = format.parse(yymmdd);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 将某一时间传换成北京时间的这个时间下的时间戳
     * @param time
     * @return
     */
    public static long getBeiJingTime(long time) {
        if (BEIJING_TIMEZONE_ID.equals(TimeZone.getDefault().getID())) {
            return time;
        }
        DateFormat format = new SimpleDateFormat();
        String format1 = format.format(time);
        format.setTimeZone(TimeZone.getTimeZone(BEIJING_TIMEZONE_ID));
        try {
            return format.parse(format1).getTime();
        } catch (ParseException e) {
            Log.e("Sam", "TimeUtil getBeiJingTime ", e);
        }

        return -1;

    }

    /**
     * 只设置日，时分秒重置为0
     * @param calendar
     */
    public static void calendarDayTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    /**
     * 获取指定的天数偏移量 从 （1970.1.1）
     *@param year
     * @param month
     * @param day
     * @return currentDayOffset
     */
    public static int someDayOffset(int year, int month, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return getDayOffset(calendar.getTimeInMillis());
    }

    public static int getDay(String format_date2) {
       return Integer.parseInt(format_date2.split("-")[2]);
    }

    public static int[] dateSplitInt(String format_date2) {
        String[] split = format_date2.split("-");
        int[] result = new int[3];
        for (int i = 0; i < split.length; i++) {
            result[i] = Integer.parseInt(split[i]);
        }
        return result;
    }
}
