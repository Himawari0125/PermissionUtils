package com.himawari.permissionUtils.bean;

/**
 * Created by S.Lee on 2017/12/15.
 */

public class CalenderPickerBean {
    private int year;
    private int month;
    private int day;

    private int dayofWeek;
    private int weekofMonth;


    private float setX;
    private float setY;

    private boolean isScaledDay;
    private boolean isSelectedDay;
    private boolean isToday;
    private boolean isNormalDay;


    public boolean isScaledDay() {
        return isScaledDay;
    }

    public void setScaledDay(boolean scaledDay) {
        isScaledDay = scaledDay;
    }

    public boolean isSelectedDay() {
        return isSelectedDay;
    }

    public void setSelectedDay(boolean selectedDay) {
        isSelectedDay = selectedDay;
    }

    public boolean isToday() {
        return isToday;
    }

    public void setToday(boolean today) {
        isToday = today;
    }

    public boolean isNormalDay() {
        return isNormalDay;
    }

    public void setNormalDay(boolean normalDay) {
        isNormalDay = normalDay;
    }

    public float getSetX() {
        return setX;
    }

    public void setSetXAndY(float setX,float setY) {
        this.setX = setX;
        this.setY = setY;
    }

    public float getSetY() {
        return setY;
    }



    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDayofWeek() {
        return dayofWeek;
    }

    public void setDayofWeek(int dayofWeek) {
        this.dayofWeek = dayofWeek;
    }

    public int getWeekofMonth() {
        return weekofMonth;
    }

    public void setWeekofMonth(int weekofMonth) {
        this.weekofMonth = weekofMonth;
    }

}
