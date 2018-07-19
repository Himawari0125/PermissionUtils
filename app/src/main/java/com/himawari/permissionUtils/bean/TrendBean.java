package com.himawari.permissionUtils.bean;

/**
 * Created by S.Lee on 2017/12/18.
 */

public class TrendBean {
    private String scaleDate;
    private float value;
    private float positionValueX;
    private float positionValueY;
    private boolean isLastNode;
    private boolean isValuePressed;




    private float weight;
    private float positionWeightX;
    private float positionWeightY;


    private int fat;
    private float positionFatX;
    private float positionFatY;

    private int muscle;
    private float positionMuscleX;
    private float positionMuscleY;


    private boolean isWeightPressed;
    private boolean isFatPressed;
    private boolean isMusclePressed;


    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPositionValueX() {
        return positionValueX;
    }

    public void setPositionValue(float positionValueX,float positionValueY) {
        this.positionValueX = positionValueX;
        this.positionValueY = positionValueY;
    }

    public float getPositionValueY() {
        return positionValueY;
    }


    public boolean isValuePressed() {
        return isValuePressed;
    }

    public void setValuePressed(boolean valuePressed) {
        isValuePressed = valuePressed;
    }

    public boolean isFatPressed() {
        return isFatPressed;
    }

    public void setFatPressed(boolean fatPressed) {
        isFatPressed = fatPressed;
    }

    public boolean isMusclePressed() {
        return isMusclePressed;
    }

    public void setMusclePressed(boolean musclePressed) {
        isMusclePressed = musclePressed;
    }

    public boolean isWeightPressed() {
        return isWeightPressed;
    }

    public void setWeightPressed(boolean weightPressed) {
        isWeightPressed = weightPressed;
    }

    public boolean getIsLastNode() {
        return isLastNode;
    }

    public void setIsLastNode(boolean isLastNode) {
        this.isLastNode = isLastNode;
    }

    public String getScaleDate() {
        return scaleDate;
    }

    public void setScaleDate(String scaleDate) {
        this.scaleDate = scaleDate;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public boolean getIsWeightPressed() {
        return isWeightPressed;
    }

    public void setIsWeightPressed(boolean isPressed) {
        this.isWeightPressed = isPressed;
    }

    public void setPositionWeightX(float positionWeightX,float positionWeightY) {
        this.positionWeightX = positionWeightX;
        this.positionWeightY = positionWeightY;
    }

    public float getPositionWeightX() {
        return positionWeightX;
    }

    public float getPositionWeightY() {
        return positionWeightY;
    }


    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public float getPositionFatX() {
        return positionFatX;
    }

    public void setPositionFatX(float positionFatX,float positionFatY) {
        this.positionFatX = positionFatX;
        this.positionFatY = positionFatY;
    }

    public float getPositionFatY() {
        return positionFatY;
    }

    public int getMuscle() {
        return muscle;
    }

    public void setMuscle(int muscle) {
        this.muscle = muscle;
    }

    public float getPositionMuscleX() {
        return positionMuscleX;
    }

    public void setPositionMuscleX(float positionMuscleX,float positionMuscleY) {
        this.positionMuscleX = positionMuscleX;
        this.positionMuscleY = positionMuscleY;
    }

    public float getPositionMuscleY() {
        return positionMuscleY;
    }
}
