package com.himawari.permissionUtils.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.constraint.ConstraintLayout;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.utils.RegularExpressionUtils;
import com.himawari.permissionUtils.utils.SharePerferenceUtils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SharePActivity extends BaseActivity{

    private EditText keyEdit,valueEdit;
    private Button addBtn,keepBtn,removeBtn;
    private Spinner spinner;
    private TextView resultTv;

    private String[] spinnerStr = new String[]{"boolean","int","string","float","long"};
    private final int BOOLEAN  = 0,INT = 1,STRING = 2,FLOAT = 3,LONG = 4;
    private int keepType;
    private Map<String,Object> objectList;
    private List<String> keyList;
    private final String NAME = "aaa";
    @Override
    protected void initView() {
        spinner = findViewById(R.id.spinner);
        keyEdit = findViewById(R.id.editText2);
        valueEdit = findViewById(R.id.editText3);
        addBtn = findViewById(R.id.button40);
        keepBtn = findViewById(R.id.button41);
        removeBtn = findViewById(R.id.button43);
        resultTv = findViewById(R.id.textView20);

        objectList = new HashMap<>();
        keyList = new ArrayList<>();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                           SharePActivity.this, android.R.layout.simple_spinner_item,
                Arrays.asList(spinnerStr));
        spinner.setAdapter(adapter);

       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               keepType = position;
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_sharep);
        super.onCreate(savedInstanceState);


    }

    public void onAddClick(View view){

        switch (keepType){
            case BOOLEAN:
                objectList.put(keyEdit.getText().toString(),Boolean.parseBoolean(valueEdit.getText().toString()));
                break;
            case INT:
                if(RegularExpressionUtils.isAccord(
                        RegularExpressionUtils.FILTER_ISINTEGER,valueEdit.getText().toString()))
                    objectList.put(keyEdit.getText().toString(),Integer.parseInt(valueEdit.getText().toString()));
                break;
            case STRING:
                objectList.put(keyEdit.getText().toString(),valueEdit.getText().toString());
                break;
            case FLOAT:
                if(RegularExpressionUtils.isAccord(
                        RegularExpressionUtils.FILTER_ISDECIMAL,valueEdit.getText().toString()))
                    objectList.put(keyEdit.getText().toString(),Float.parseFloat(valueEdit.getText().toString()));
                break;
            case LONG:
                if(RegularExpressionUtils.isAccord(
                        RegularExpressionUtils.FILTER_ISINTEGER,valueEdit.getText().toString()))
                    objectList.put(keyEdit.getText().toString(),Long.parseLong(valueEdit.getText().toString()));
                break;
        }
    }

    public void onKeepClick(View view){
        SharePerferenceUtils.keepObject(this,NAME,objectList,Context.MODE_PRIVATE);
       // objectList.clear();//java.lang.Object[] cannot be cast to java.lang.String[]
    }

    public void onRemoveClick(View view){
        SharePerferenceUtils.removeObject(this,NAME,Context.MODE_PRIVATE,keyList.toArray(new String[]{}));
        keyList.clear();
    }

    public void onAddKeyClick(View view){
        keyList.add(keyEdit.getText().toString());
    }

    public void onPrintClick(View view){
        resultTv.setText(SharePerferenceUtils.getAllKeyValues(this,NAME,Context.MODE_PRIVATE));

    }

    public void onClearAllClick(View view){
        SharePerferenceUtils.clearAll(SharePActivity.this,NAME, Context.MODE_PRIVATE);
    }

    public void onGetValueClick(View view){
        resultTv.setText(SharePerferenceUtils.getValue(SharePActivity.this,NAME, Context.MODE_PRIVATE,keyEdit.getText().toString()));
    }
}
