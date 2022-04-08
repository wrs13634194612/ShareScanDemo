package com.example.user.mathgame;



        import android.text.TextUtils;

//        import com.mondor.mindor.BuildConfig;

        import java.io.Serializable;
        import java.util.List;
        import java.util.Random;

public class Device implements Serializable {


    /**
     * category : 家用电器
     * category02 : null
     * category03 : http://112.74.48.180/wanYe/images/product/Intelligent_socket.png
     * date : 我是产品简介
     * equipmentCount : 4
     * equipmentId :
     * equipmentName :
     * industry : 智能家居
     * introduce : 智能家电
     * onlineCount :
     * productIcon : http://112.74.48.180/wanYe/images/equipment/Intelligent_socket02.png
     * productId : mindorPro100001
     * productImage : http://112.74.48.180/wanYe/images/product/Intelligent_socket.png
     * productName : 智能插座
     */
    private String userId;
    private boolean isAdapterChecked;
    private String equipmentRoom;
    private String equipmentIndex;
    private String equipmentType;
    private String productVersion;
    private String equipmentVersion;
    private int share;
    private String operateTime;
    private String category;
    private String category02;
    private String category03;
    private String date;
    private String equipmentCount;
    private String equipmentId;
    private String equipmentName;
    private String industry;
    private String introduce;
    private String onlineCount;
    private String productIcon;
    private String productId;
    private String productImage;
    private String productName;
    private String equipmentNote;
    private String equipmentState;
    private String isOn; // 0 1
    private String equipmentLabel;
    private String role;//用户设备权限（1：主权限，2：次权限）
    private boolean readIf;//是否有未读消息
    private String warnValue;//警告
    private String warnOperate;//警告操作
    private String powerValue;//警告操作
    private String blackout;
    private String butNames = "";
    private boolean isFake;//是否空白内容
    private int showType;//0 默认设备 1title

    public String getIsOn() {
        return TextUtils.isEmpty(isOn) ? "0" : isOn;
    }

    public void setIsOn(String isOn) {
        this.isOn = isOn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isAdapterChecked() {
        return isAdapterChecked;
    }

    public void setAdapterChecked(boolean adapterChecked) {
        isAdapterChecked = adapterChecked;
    }

    public String getEquipmentRoom() {
        return equipmentRoom;
    }

    public void setEquipmentRoom(String equipmentRoom) {
        this.equipmentRoom = equipmentRoom;
    }

    public String getEquipmentIndex() {
        return equipmentIndex;
    }

    public void setEquipmentIndex(String equipmentIndex) {
        this.equipmentIndex = equipmentIndex;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    public String getEquipmentVersion() {
        return equipmentVersion;
    }

    public void setEquipmentVersion(String equipmentVersion) {
        this.equipmentVersion = equipmentVersion;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public boolean isFake() {
        return isFake;
    }

    public void setFake(boolean fake) {
        isFake = fake;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public int getShowType() {
        if (TextUtils.isEmpty(getProductId())) return 1;
        if (getProductId().startsWith("swt002") || getProductId().startsWith("swt003")) {
            return 0;
        }
        return showType;
    }

    public String getButNames() {
        return butNames == null ? "" : butNames;
    }

    public void setButNames(String butNames) {
        this.butNames = butNames;
    }

    public String getPowerValue() {
        return powerValue;
    }

    public void setPowerValue(String powerValue) {
        this.powerValue = powerValue;
    }

    public String getBlackout() {
        return blackout;
    }

    public void setBlackout(String blackout) {
        this.blackout = blackout;
    }

    public String getWarnValue() {
        return warnValue;
    }

    public void setWarnValue(String warnValue) {
        this.warnValue = warnValue;
    }

    public String getWarnOperate() {
        return warnOperate;
    }

    public void setWarnOperate(String warnOperate) {
        this.warnOperate = warnOperate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEquipmentLabel() {
        return equipmentLabel;
    }

    public void setEquipmentLabel(String equipmentLabel) {
        this.equipmentLabel = equipmentLabel;
    }

    private String specificEquipmentLabel;
    private String equipmentModelState;
    private List<Device> equipmentDatas;

    private boolean isOnline;
    private boolean isCheck;
    private boolean isFromPush;

    public static Device makeFake(String productId) {
        Device device = new Device();
        device.specificEquipmentLabel = "测试设备";
        device.productId = productId;
        device.equipmentId = productId + "100002";
        device.equipmentNote = productId;
        device.equipmentState = "2";
        device.role = "1";
        return device;
    }

    public boolean isWifiDevice() {
        if (productId == "mtm001" || productId.toLowerCase().startsWith("sig")
                || productId.toLowerCase().startsWith("hum")
                || productId.toLowerCase().startsWith("bat")
                || productId.toLowerCase().startsWith("camera")) {
            return false;
        }
        return true;
    }

    public Device() {
    }

    public String getEquipmentState() {

        return equipmentState;
    }

    public void setEquipmentState(String equipmentState) {
        this.equipmentState = equipmentState;
    }

    public boolean isFromPush() {
        return isFromPush;
    }

    public void setFromPush(boolean fromPush) {
        isFromPush = fromPush;
    }

    public Device(String equipmentId, String productId) {
        this.equipmentId = equipmentId;
        this.productId = productId;
    }

    public Device(String productId, String equipmentId, String equipmentNote, boolean isFromPush) {
        this.productId = productId;
        this.equipmentId = equipmentId;
        this.equipmentNote = equipmentNote;
        this.isFromPush = isFromPush;
    }

    public Device(String equipmentId, String equipmentNote, boolean isFromPush) {
        this.equipmentId = equipmentId;
        this.equipmentNote = equipmentNote;
        this.isFromPush = isFromPush;
    }

    public String getEquipmentNote() {
        return equipmentNote;
    }

    public void setEquipmentNote(String equipmentNote) {
        this.equipmentNote = equipmentNote;
    }

    public String getSpecificEquipmentLabel() {
        return TextUtils.isEmpty(specificEquipmentLabel) || "null".equals(specificEquipmentLabel) ? "默认房间" : specificEquipmentLabel;
    }

    public void setSpecificEquipmentLabel(String specificEquipmentLabel) {
        this.specificEquipmentLabel = specificEquipmentLabel;
    }

    public String getEquipmentModelState() {
        return equipmentModelState;
    }

    public void setEquipmentModelState(String equipmentModelState) {
        this.equipmentModelState = equipmentModelState;
    }

    public List<Device> getEquipmentDatas() {
        return equipmentDatas;
    }

    public void setEquipmentDatas(List<Device> equipmentDatas) {
        this.equipmentDatas = equipmentDatas;
    }

    public boolean isCheck() {
        return isCheck || "1".equals(equipmentModelState);
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isOnline() {
        return "2".equals(equipmentState);
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Object getCategory02() {
        return category02;
    }

    public void setCategory02(String category02) {
        this.category02 = category02;
    }

    public String getCategory03() {
        return category03;
    }

    public void setCategory03(String category03) {
        this.category03 = category03;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEquipmentCount() {
        return equipmentCount;
    }

    public void setEquipmentCount(String equipmentCount) {
        this.equipmentCount = equipmentCount;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(String onlineCount) {
        this.onlineCount = onlineCount;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    public String getProductId() {
        if (TextUtils.isEmpty(productId) && !TextUtils.isEmpty(equipmentId) && equipmentId.length() > 6) {
            productId = equipmentId.substring(0, 6);
        }
        if (productId == null) {
            productId = "";
        }
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public boolean isReadIf() {
        return readIf;
    }

    public void setReadIf(boolean readIf) {
        this.readIf = readIf;
    }

    @Override
    public boolean equals(Object obj) {
        return this.equipmentId.equals(((Device) obj).equipmentId);
    }

    @Override
    public int hashCode() {
        return this.equipmentId.length();
    }


    @Override
    public String toString() {
        String lable = "Device{" +
                "category='" + category + '\'' +
                ", category02='" + category02 + '\'' +
                ", category03='" + category03 + '\'' +
                ", date='" + date + '\'' +
                ", equipmentCount='" + equipmentCount + '\'' +
                ", equipmentId='" + equipmentId + '\'' +
                ", equipmentName='" + equipmentName + '\'' +
                ", industry='" + industry + '\'' +
                ", introduce='" + introduce + '\'' +
                ", onlineCount='" + onlineCount + '\'' +
                ", productIcon='" + productIcon + '\'' +
                ", productId='" + productId + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productName='" + productName + '\'' +
                ", equipmentNote='" + equipmentNote + '\'' +
                ", equipmentState='" + equipmentState + '\'' +
                ", isOn='" + isOn + '\'' +
                ", equipmentLabel='" + equipmentLabel + '\'' +
                ", role='" + role + '\'' +
                ", readIf=" + readIf +
                ", warnValue='" + warnValue + '\'' +
                ", warnOperate='" + warnOperate + '\'' +
                ", powerValue='" + powerValue + '\'' +
                ", blackout='" + blackout + '\'' +
                ", butNames='" + butNames + '\'' +
                ", isFake=" + isFake +
                ", showType=" + showType +
                ", specificEquipmentLabel='" + specificEquipmentLabel + '\'' +
                ", equipmentModelState='" + equipmentModelState + '\'' +
                ", equipmentDatas=" + equipmentDatas +
                ", isOnline=" + isOnline +
                ", isCheck=" + isCheck +
                ", isFromPush=" + isFromPush +
                '}';

        return lable.replace("http", "");
    }
}
