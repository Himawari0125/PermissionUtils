package com.himawari.permissionUtils.bean;

/**
 * Created by S.Lee on 2017/12/14.
 */

public class HistoryListBean {
    private String mesureDate;
    private String measureTime;
    private String weight;
    private String fatPercent;
    private boolean isFirstMeasure;//是否是当天测量的最新数据

    public boolean isFirstMeasure() {
        return isFirstMeasure;
    }

    public void setFirstMeasure(boolean firstMeasure) {
        isFirstMeasure = firstMeasure;
    }

    public String getMesureDate() {
        return mesureDate;
    }

    public void setMesureDate(String mesureDate) {
        this.mesureDate = mesureDate;
    }

    public String getMeasureTime() {
        return measureTime;
    }

    public void setMeasureTime(String measureTime) {
        this.measureTime = measureTime;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getFatPercent() {
        return fatPercent;
    }

    public void setFatPercent(String fatPercent) {
        this.fatPercent = fatPercent;
    }


}
