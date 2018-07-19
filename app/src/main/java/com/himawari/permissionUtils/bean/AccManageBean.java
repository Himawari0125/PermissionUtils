package com.himawari.permissionUtils.bean;


/**
 * Created by S.Lee on 2017/11/21.
 */

public class AccManageBean {
    private String user_name;
    private boolean isPrimaryAccount;
    private boolean isLastItem;
    private boolean isChecked;
    private Boolean isChild;
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Boolean IsChild() {
        return isChild;
    }

    public void setIsChild(Boolean child) {
        isChild = child;
    }

    public boolean isLastItem() {
        return isLastItem;
    }

    public void setLastItem(boolean lastItem) {
        isLastItem = lastItem;
    }

    public boolean isPrimaryAccount() {
        return isPrimaryAccount;
    }

    public void setPrimaryAccount(boolean primaryAccount) {
        isPrimaryAccount = primaryAccount;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public class Data{
        private int bindUserId;
        private String headUrl;
        private int id;
        private int isChild;
        private String nickName;
        private int parentUserId;
        private String userName;
        private int sex;

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getBindUserId() {
            return bindUserId;
        }

        public void setBindUserId(int bindUserId) {
            this.bindUserId = bindUserId;
        }

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsChild() {
            return isChild;
        }

        public void setIsChild(int isChild) {
            this.isChild = isChild;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getParentUserId() {
            return parentUserId;
        }

        public void setParentUserId(int parentUserId) {
            this.parentUserId = parentUserId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
        public String toString(){
            return "bindUserId:"+bindUserId+" headUrl:"+headUrl+" id:"+id+" isChild:"+isChild+" nickName:"+nickName
                    +" parentUserId:"+parentUserId+" userName:"+userName+"\n";
        }
    }
}
