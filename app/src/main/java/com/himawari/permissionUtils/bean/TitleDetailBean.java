package com.himawari.permissionUtils.bean;

/**
 * Created by S.Lee on 2017/12/13.
 */

public class TitleDetailBean {
    private String title;
    private String detail;
    private boolean isDrawDown;

    public boolean isDrawDown() {
        return isDrawDown;
    }

    public void setDrawDown(boolean drawDown) {
        isDrawDown = drawDown;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
