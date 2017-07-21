package demo.com.sam.demofactory.java;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * 时间格式转化，时区因素
 * @author SamWang(199004)
 *         2017/6/23 17:50
 */
public class TimeZoneTest {


    public static void main(String[] args) {
        System.out.println("Current zone " + TimeZone.getDefault().getID());

        System.out.println(formatDate(System.currentTimeMillis(),"yyyy-MM-dd HH:mm","GMT+9"));
        System.out.println(formatDate(System.currentTimeMillis(),"yyyy-MM-dd HH:mm","Asia/Shanghai"));
        System.out.println(formatDate(System.currentTimeMillis(),"yyyy-MM-dd HH:mm",null));

    }

        /**
         * Timestamp 转 String
         *
         * @param timestamp
         * @return
         */
    public static String formatDate(long timestamp, String dateFormat,String timeZone) {
        DateFormat format = new SimpleDateFormat(dateFormat);
        if (timeZone != null && !timeZone.isEmpty()) {
            format.setTimeZone(TimeZone.getTimeZone(timeZone));
        }
        try {
            return format.format(timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static long getBeiJingTime(long time) {
        if ("Asia/Shanghai".equals(TimeZone.getDefault().getID())) {
            return time;
        }
        DateFormat format = new SimpleDateFormat();
        String format1 = format.format(time);
        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        try {
            return format.parse(format1).getTime();
        } catch (ParseException e) {
            Log.e("Sam", "TimeUtil getBeiJingTime ", e);
        }

        return -1;

    }
}
