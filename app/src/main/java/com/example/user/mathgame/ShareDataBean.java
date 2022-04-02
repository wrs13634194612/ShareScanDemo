package com.example.user.mathgame;

import java.io.Serializable;

public class ShareDataBean {

    private ShareDataBean.ShareMeBean meData;       //me表示我分享出去的
    private ShareDataBean.ShareOtherBean otherData; //other表示别人分享给我的


    public ShareMeBean getMeData() {
        return meData;
    }

    public void setMeData(ShareMeBean meData) {
        this.meData = meData;
    }

    public ShareOtherBean getOtherData() {
        return otherData;
    }

    public void setOtherData(ShareOtherBean otherData) {
        this.otherData = otherData;
    }

    public static class ShareMeBean implements Serializable {
        private String meName;  //用户名
        private String meIcon;  //头像
        private int meDeviceNumber; //单个分享的设备数量

        public ShareMeBean(String meName, String meIcon, int meDeviceNumber) {
            this.meName = meName;
            this.meIcon = meIcon;
            this.meDeviceNumber = meDeviceNumber;
        }

        public String getMeName() {
            return meName;
        }

        public void setMeName(String meName) {
            this.meName = meName;
        }

        public String getMeIcon() {
            return meIcon;
        }

        public void setMeIcon(String meIcon) {
            this.meIcon = meIcon;
        }

        public int getMeDeviceNumber() {
            return meDeviceNumber;
        }

        public void setMeDeviceNumber(int meDeviceNumber) {
            this.meDeviceNumber = meDeviceNumber;
        }
    }
    public static class ShareOtherBean implements Serializable {
        private String otherName;  //用户名
        private String otherIcon;  //头像
        private int otherDeviceNumber; //单个分享的设备数量

        public ShareOtherBean(String otherName, String otherIcon, int otherDeviceNumber) {
            this.otherName = otherName;
            this.otherIcon = otherIcon;
            this.otherDeviceNumber = otherDeviceNumber;
        }

        public String getOtherName() {
            return otherName;
        }

        public void setOtherName(String otherName) {
            this.otherName = otherName;
        }

        public String getOtherIcon() {
            return otherIcon;
        }

        public void setOtherIcon(String otherIcon) {
            this.otherIcon = otherIcon;
        }

        public int getOtherDeviceNumber() {
            return otherDeviceNumber;
        }

        public void setOtherDeviceNumber(int otherDeviceNumber) {
            this.otherDeviceNumber = otherDeviceNumber;
        }
    }
}
