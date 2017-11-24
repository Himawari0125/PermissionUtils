package com.himawari.a24hoursrecord.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.himawari.a24hoursrecord.R;
import com.himawari.a24hoursrecord.utils.DensityUtils;


/**
 * Created by S.Lee on 2017/11/20.
 */

public class EditTextView extends RelativeLayout {
    private String str_title;
    private String str_hint;
    private float title_marginleft;
 //   private int hint_marginLeft;
    private EditText editText;
    public EditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.edittextview);
        str_hint = array.getString(R.styleable.edittextview_edit_goal_hint);
        str_title = array.getString(R.styleable.edittextview_edit_goal_title);
        title_marginleft = array.getFloat(R.styleable.edittextview_title_margin_left, DensityUtils.sp2px(context,7.5f));
//        hint_marginLeft = array.getInt(R.styleable.edittextview_hint_margin_left,DensityUtils.sp2px(context,13.5f));
        array.recycle();

        LayoutParams editParamas = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        LayoutParams edittitleParamas = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        edittitleParamas.addRule(ALIGN_PARENT_LEFT);
        edittitleParamas.addRule(CENTER_VERTICAL);
        edittitleParamas.leftMargin = (int)title_marginleft;

//        LayoutParams edithintParamas = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
//        edithintParamas.addRule(ALIGN_PARENT_LEFT);
//        edithintParamas.addRule(CENTER_VERTICAL);
//        edithintParamas.leftMargin = hint_marginLeft;



       
        if(str_hint != null){
            editText.setHint(str_hint);
        }

        TextView title_tv = new TextView(context);
        title_tv.setLayoutParams(edittitleParamas);
        title_tv.setText(str_title);
        title_tv.setId(R.id.edittextview_title_id);
        if(str_title == null)title_tv.setVisibility(View.GONE);
        addView(title_tv);


        editText = new EditText(context);
        editParamas.addRule(RIGHT_OF,R.id.edittextview_title_id);
        editText.setLayoutParams(editParamas);
        editText.setBackgroundDrawable(null);
        addView(editText);

    }


    public String getEditTextString(){
        return editText.getText().toString();
    }
}
