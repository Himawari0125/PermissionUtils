package com.himawari.permissionUtils.utils;

import com.himawari.permissionUtils.R;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by S.Lee on 2018/1/18.
 */

public class TimeUtils {

    public enum FormatExpression{
        HHMMSS {//24小时制--时:分:秒
            @Override
            public String details() {
                return "HH:mm:ss";
            }
        }
        ,YMDHMS{
            @Override
            public String details() {//年-月-日 24小时制--时:分:秒
                return "yyyy-MM-dd HH:mm:ss";
            }
        }
        ,YMD{
            @Override
            public String details() {//年-月-日
                return "yyyy-MM-dd";
            }
        };

        public abstract String details();
    }

    public static String getFormatTimeStr(FormatExpression formatExpression){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatExpression.details());
        return simpleDateFormat.format(new Date());
    }
    public static SimpleDateFormat getFormatTime(FormatExpression formatExpression){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatExpression.details());
        return simpleDateFormat;
    }




    /**
     *
     * @param time yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static TimeBean getTimeBean(String time){
        TimeBean bean = new TimeBean();
        try {
            String[] dayHour = time.split(" ");
            String[] day = dayHour[0].split("-");
            String[] hour = dayHour[1].split(":");
            bean.setYmd(dayHour[0]);
            bean.setHms(dayHour[1]);
            bean.setYear(day[0]);
            bean.setMonth(day[1]);
            bean.setDay(day[2]);
            bean.setHour(hour[0]);
            bean.setMunite(hour[1]);
            bean.setSecond(hour[0]);
        }catch (Exception e){
            LogUtils.e(LogUtils.originalIndex,e.getMessage());
        }

        return bean;
    }
    public static class TimeBean{
        private String year;
        private String month;
        private String day;
        private String hour;
        private String munite;
        private String second;

        private String ymd;
        private String hms;

        public String getYmd() {
            return ymd;
        }

        public void setYmd(String ymd) {
            this.ymd = ymd;
        }

        public String getHms() {
            return hms;
        }

        public void setHms(String hms) {
            this.hms = hms;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getHour() {
            return hour;
        }

        public void setHour(String hour) {
            this.hour = hour;
        }

        public String getMunite() {
            return munite;
        }

        public void setMunite(String munite) {
            this.munite = munite;
        }

        public String getSecond() {
            return second;
        }

        public void setSecond(String second) {
            this.second = second;
        }
    }

    /**
     *
     * @return 0:今天 1:昨天 2:都不是
     */
    private int isTodayOrYesterday(String day){

        if(IsToday(day)){
            return 0;
        }else if(IsYesterday(day)){
            return 1;
        }else{
            return 2;
        }
    }

    /**
     * 判断是否为今天
     * @param day 传入的 时间
     */
    public static boolean IsToday(String day) {
        Date currentDate = new Date(System.currentTimeMillis());
        if(day.equals(getFormatTime(FormatExpression.YMD).format(currentDate))){
            return true;
        }
        return false;
    }

    /**
     * 判断是否为昨天
     * @param day 传入的 时间
     */
    public static boolean IsYesterday(String day) {
        Calendar pre = Calendar.getInstance();
        pre.set(Calendar.DAY_OF_MONTH,pre.get(Calendar.DAY_OF_MONTH)-1);
        Date date = new Date(pre.getTimeInMillis());
        if(day.equals(getFormatTime(FormatExpression.YMD).format(date))){
            return true;
        }
        return false;
    }

    /**
     * 比对两个日期前后顺序
     * @param arg1 arg2 日期参数
     * @return int arg1在arg2之后:return AFTER,arg1与arg2在同时:return SAMETIME,arg1在arg2之前:return BEFORE
     * 使用时注意处理因ParseException引起的others的情况
     */
    public static CompareTime compareTime(String arg1, String arg2) {
        LogUtils.i(LogUtils.originalIndex,"compareTime:"+arg1+" "+arg2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = simpleDateFormat.parse(arg1);
            Date date2 = simpleDateFormat.parse(arg2);
            if(date1.getTime() > date2.getTime())
                return CompareTime.AFTER;
            else if(date1.getTime() < date2.getTime())
                return CompareTime.BEFORE;
            else
                return CompareTime.SAMETIME;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return CompareTime.OTHERS;
    }

    public enum CompareTime{
        BEFORE,SAMETIME, AFTER,OTHERS
    }

    public static String getMonthDay(String ymd){
        return ymd.substring(ymd.indexOf("-")+1);
    }


    public static int getMonStr(int size){
        switch (size){
            case 0:
                return R.string.January;
            case 1:
                return R.string.February;
            case 2:
                return R.string.March;
            case 3:
                return R.string.April;
            case 4:
                return R.string.May;
            case 5:
                return R.string.June;
            case 6:
                return R.string.July;
            case 7:
                return R.string.August;
            case 8:
                return R.string.September;
            case 9:
                return R.string.October;
            case 10:
                return R.string.November;
            case 11:
                return R.string.December;
                default:
                    return R.string.NONONO;

        }
    }

    public static boolean isFormatMatch(String waitCheckTime,String formatExpression){
        DateFormat dateFormat = new SimpleDateFormat(formatExpression);
        try {
            dateFormat.parse(waitCheckTime);
        }catch (ParseException e){
            return false;
        }
        return true;
    }

}
