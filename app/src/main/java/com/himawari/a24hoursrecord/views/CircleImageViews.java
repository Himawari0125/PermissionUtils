package com.himawari.a24hoursrecord.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.himawari.a24hoursrecord.R;


public class CircleImageViews extends android.support.v7.widget.AppCompatImageView {
    private float angle = 0;
    private int position = 0;
    private String name;

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    /**
     * @param context
     */
    public CircleImageViews(Context context) {
        this(context, null);
    }

    /**
     * @param context
     * @param attrs
     */
    public CircleImageViews(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CircleImageViews(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs,
                    R.styleable.CircleImageView);
            
            name = a.getString(R.styleable.CircleImageView_name);
        }
    }
}
