package com.himawari.permissionUtils.bean;

/**
 * Created by Kokosnuss on 2018/10/15
 */
public class AddScheduleBean {
    private String scheduleTitle;
    private String scheduleDetail;
    private String scheduleTime;

    private String CALENDAR_NAME ;
    private String CALENDARS_ACCOUNT_NAME;
    private String CALENDARS_ACCOUNT_TYPE;
    private String CALENDARS_DISPLAY_NAME;

    public String getScheduleTitle() {
        return scheduleTitle;
    }

    public void setScheduleTitle(String scheduleTitle) {
        this.scheduleTitle = scheduleTitle;
    }

    public String getScheduleDetail() {
        return scheduleDetail;
    }

    public void setScheduleDetail(String scheduleDetail) {
        this.scheduleDetail = scheduleDetail;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getCALENDAR_NAME() {
        return CALENDAR_NAME;
    }

    public void setCALENDAR_NAME(String CALENDAR_NAME) {
        this.CALENDAR_NAME = CALENDAR_NAME;
    }

    public String getCALENDARS_ACCOUNT_NAME() {
        return CALENDARS_ACCOUNT_NAME;
    }

    public void setCALENDARS_ACCOUNT_NAME(String CALENDARS_ACCOUNT_NAME) {
        this.CALENDARS_ACCOUNT_NAME = CALENDARS_ACCOUNT_NAME;
    }

    public String getCALENDARS_ACCOUNT_TYPE() {
        return CALENDARS_ACCOUNT_TYPE;
    }

    public void setCALENDARS_ACCOUNT_TYPE(String CALENDARS_ACCOUNT_TYPE) {
        this.CALENDARS_ACCOUNT_TYPE = CALENDARS_ACCOUNT_TYPE;
    }

    public String getCALENDARS_DISPLAY_NAME() {
        return CALENDARS_DISPLAY_NAME;
    }

    public void setCALENDARS_DISPLAY_NAME(String CALENDARS_DISPLAY_NAME) {
        this.CALENDARS_DISPLAY_NAME = CALENDARS_DISPLAY_NAME;
    }
}
