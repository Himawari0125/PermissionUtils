package com.himawari.permissionUtils.utils;

import android.app.Activity;

import com.himawari.permissionUtils.mineException.EmptyException;

import java.util.Stack;

public class MyActivityManager {
    private static MyActivityManager manager;
    public Stack<Activity> activityStack = new Stack<>();
    public static MyActivityManager getManager() {
        if (manager==null)
            synchronized (MyActivityManager.class){
                if (manager == null){
                    manager = new MyActivityManager();
                }
            }
        return manager;
    }

    private MyActivityManager(){

    }

    public void addActivity(Activity activity){
        activityStack.add(activity);
    }

    public void removeActivity(Activity activity){
        if (activityStack.indexOf(activity) >=0)
            activityStack.remove(activity);
    }

    public void finishAllActivity() {
        for (Activity activity : activityStack) {
            LogUtils.i(LogUtils.superIndex,activity.getComponentName()+"");
            activity.finish();
        }
        activityStack.clear();

    }

    public void finishTillActivity(String componentName){
        int length = activityStack.size();
        int targetIdx = -1;
        for(int i = length-1;i >= 0;i--){
            String localName = activityStack.get(i).getLocalClassName();
            String simpleName = localName.substring(localName.lastIndexOf(".")+1);
            if(componentName.equals(simpleName)){
                targetIdx = i;
                break;
            };
        }
        if(targetIdx<0){
            finishAllActivity();
        }else{
            for (int i = length - 1; i > targetIdx; i--){
                Activity activity = activityStack.get(i);
                activityStack.remove(i);
                activity.finish();
            }
        }
    }

    public void finishOneActivity(Activity activity) {
        if (activityStack.indexOf(activity) >= 0)
            activityStack.remove(activity);
        activity.finish();
    }

    public void finishTopActivity() {
        if (activityStack.size() > 0) {
            Activity activity = activityStack.lastElement();
            activity.finish();
            activityStack.remove(activity);
        }
    }
    public Activity getRunningActivity() throws EmptyException{
        if(activityStack.isEmpty())
            throw new EmptyException("Activity stack is empty") ;

        return activityStack.lastElement();
    }
}
