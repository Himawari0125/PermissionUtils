package com.himawari.permissionUtils.parcels;

import android.os.Parcel;
import android.os.Parcelable;

import com.himawari.permissionUtils.bean.AddScaleDateBeans;

/**
 * Created by S.Lee on 2018/3/2.
 */

public class ParcelTest implements Parcelable {
    private AddScaleDateBeans mData;

    public AddScaleDateBeans getmData() {
        return mData;
    }

    public ParcelTest(AddScaleDateBeans mData) {
        this.mData = mData;
    }

    public static Creator<ParcelTest> getCREATOR() {
        return CREATOR;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData.getBodyType());
        out.writeFloat(mData.getSkeletalMuscle());
        out.writeFloat(mData.getWeightControl());
        out.writeFloat(mData.getSuttleWeight());
        out.writeFloat(mData.getBmi());
        out.writeFloat(mData.getMuscle());
        out.writeFloat(mData.getNormWeight());
        out.writeFloat(mData.getBfr());
        out.writeFloat(mData.getBodyScore());
        out.writeFloat(mData.getBodyWeight());
        out.writeInt(mData.getFatLevel());
        out.writeFloat(mData.getBodyAge());
        out.writeFloat(mData.getProtein());
        out.writeFloat(mData.getWater());
        out.writeFloat(mData.getMetabolism());
        out.writeFloat(mData.getFatWeight());
        out.writeFloat(mData.getBone());
        out.writeFloat(mData.getVisceraFat());
        out.writeFloat(mData.getMuscleControl());
        out.writeFloat(mData.getFatControl());
        out.writeString(mData.getCheckTime());
        out.writeInt(mData.getSkeletalMuscleLevel());
    }

    public static final Parcelable.Creator<ParcelTest> CREATOR
            = new Parcelable.Creator<ParcelTest>() {
        public ParcelTest createFromParcel(Parcel in) {
            return new ParcelTest(in);
        }

        public ParcelTest[] newArray(int size) {
            return new ParcelTest[size];
        }
    };

    private ParcelTest(Parcel in) {
        mData = new AddScaleDateBeans();
        mData.setBodyType(in.readInt());
        mData.setSkeletalMuscle(in.readFloat());
        mData.setWeightControl(in.readFloat());
        mData.setSuttleWeight(in.readFloat());
        mData.setBmi(in.readFloat());
        mData.setMuscle(in.readFloat());
        mData.setNormWeight(in.readFloat());
        mData.setBfr(in.readFloat());
        mData.setBodyScore(in.readFloat());
        mData.setBodyWeight(in.readFloat());
        mData.setFatLevel(in.readInt());
        mData.setBodyAge(in.readFloat());
        mData.setProtein(in.readFloat());
        mData.setWater(in.readFloat());
        mData.setMetabolism(in.readFloat());
        mData.setFatWeight(in.readFloat());
        mData.setBone(in.readFloat());
        mData.setVisceraFat(in.readFloat());
        mData.setMuscleControl(in.readFloat());
        mData.setFatControl(in.readFloat());
        mData.setCheckTime(in.readString());
        mData.setSkeletalMuscleLevel( in.readInt());
    }
}
