package com.himawari.permissionUtils.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.himawari.permissionUtils.BaseActivity;
import com.himawari.permissionUtils.R;
import com.himawari.permissionUtils.commons.Constant;
import com.himawari.permissionUtils.utils.CameraAlbumUtils;
import com.himawari.permissionUtils.utils.LogUtils;
import com.himawari.permissionUtils.utils.PermissionRequestUtils;

import java.io.File;
import java.security.Permission;

public class CrashFileActivity extends BaseActivity {
    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crashfile);
    }

    public void onCreateDirClick(View view){
        if(!PermissionRequestUtils.RequestPermission(this, PermissionRequestUtils.storage_RequestCode,""))
            createFile();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case PermissionRequestUtils.storage_RequestCode:
                if(PermissionRequestUtils.IsAllGranted(permissions,grantResults)) {
                    Log.i("Permission_Yes","权限已授予");
                    createFile();
                }else{
                    Log.i("Permission_No","权限未授予");
                }
                break;
        }
    }


    private void createFile(){
        try{
            File dirFile = new File(Constant.appFolderPath+Constant.crashPath);
            if(!dirFile.exists()){
                if(dirFile.mkdirs())
                    Toast.makeText(this,"the file created success",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this,"the file created failed",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"the file is already exists",Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            LogUtils.e(e.getMessage());
        }
    }
}
