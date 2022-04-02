package com.example.user.mathgame;



        import java.io.Serializable;
        import java.util.List;

public class ShareReceiveBean {

    /**
     * code : 200
     * data : {"owners":[{"devices":[{"equipmentNote":"易燃气体","productId":"zcz003","equipmentId":"zcz003100192"}],"share":{"nickName":"黑色的凉白开","headPortrait":"https://thirdwx.qlogo.cn/mmopen/vi_32/fPEdzgvXkO8qWra95HQ468Qobu3oibrG6jnibB2TOACNmibEhcjqNYeme4YneaOTW3vtjSUiaTjUub0O97pAP5K3sg/132","userId":"minApp113980"},"id":9},{"devices":[{"equipmentNote":"易燃气体","productId":"zcz003","equipmentId":"zcz003100192"},{"equipmentNote":"易燃气体","productId":"zcz003","equipmentId":"zcz003100192"},{"equipmentNote":"易燃气体","productId":"zcz003","equipmentId":"zcz003100192"}],"share":{"nickName":"黑色的凉白开","headPortrait":"https://thirdwx.qlogo.cn/mmopen/vi_32/fPEdzgvXkO8qWra95HQ468Qobu3oibrG6jnibB2TOACNmibEhcjqNYeme4YneaOTW3vtjSUiaTjUub0O97pAP5K3sg/132","userId":"minApp113980"},"id":10}],"gives":[{"devices":[{"equipmentNote":"智能插座","productId":"zcz001","equipmentId":"zcz001101027"},{"equipmentNote":"一氧化碳(co)","productId":"zcz002","equipmentId":"zcz002103910"}],"share":{"nickName":"1","headPortrait":"https://thirdwx.qlogo.cn/mmopen/vi_32/Ih0t4ACgYJqvlicJe4NZUia6qfo2jGrK1rhzy4fBKteBuItlqG0KC7ztbwSgZo56Yibw7Bne4MfaepwoCX34uBqjA/132","userId":"minApp113043"},"id":6},{"devices":[{"equipmentNote":"智能插座","productId":"zcz001","equipmentId":"zcz001101027"},{"equipmentNote":"一氧化碳(co)","productId":"zcz002","equipmentId":"zcz002103910"}],"share":{"nickName":"1","headPortrait":"https://thirdwx.qlogo.cn/mmopen/vi_32/Ih0t4ACgYJqvlicJe4NZUia6qfo2jGrK1rhzy4fBKteBuItlqG0KC7ztbwSgZo56Yibw7Bne4MfaepwoCX34uBqjA/132","userId":"minApp113043"},"id":6}]}
     * message : 查询成功
     */

    private int code;
    private DataBean data;
    private String message;

    public ShareReceiveBean(int code, DataBean data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean implements Serializable {
        private List<OwnersBean> owners;
        private List<GivesBean> gives;

        public List<OwnersBean> getOwners() {
            return owners;
        }

        public void setOwners(List<OwnersBean> owners) {
            this.owners = owners;
        }

        public List<GivesBean> getGives() {
            return gives;
        }

        public void setGives(List<GivesBean> gives) {
            this.gives = gives;
        }

        public static class OwnersBean implements Serializable {
            /**
             * devices : [{"equipmentNote":"易燃气体","productId":"zcz003","equipmentId":"zcz003100192"}]
             * share : {"nickName":"黑色的凉白开","headPortrait":"https://thirdwx.qlogo.cn/mmopen/vi_32/fPEdzgvXkO8qWra95HQ468Qobu3oibrG6jnibB2TOACNmibEhcjqNYeme4YneaOTW3vtjSUiaTjUub0O97pAP5K3sg/132","userId":"minApp113980"}
             * id : 9
             */

            private ShareBean share;
            private int id;
            private List<DevicesBean> devices;

            public ShareBean getShare() {
                return share;
            }

            public void setShare(ShareBean share) {
                this.share = share;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<DevicesBean> getDevices() {
                return devices;
            }

            public void setDevices(List<DevicesBean> devices) {
                this.devices = devices;
            }

            public static class ShareBean implements Serializable {
                public ShareBean(String nickName, String headPortrait, String userId) {
                    this.nickName = nickName;
                    this.headPortrait = headPortrait;
                    this.userId = userId;
                }

                /**
                 * nickName : 黑色的凉白开
                 * headPortrait : https://thirdwx.qlogo.cn/mmopen/vi_32/fPEdzgvXkO8qWra95HQ468Qobu3oibrG6jnibB2TOACNmibEhcjqNYeme4YneaOTW3vtjSUiaTjUub0O97pAP5K3sg/132
                 * userId : minApp113980
                 */


                private String nickName;
                private String headPortrait;
                private String userId;

                public String getNickName() {
                    return nickName;
                }

                public void setNickName(String nickName) {
                    this.nickName = nickName;
                }

                public String getHeadPortrait() {
                    return headPortrait;
                }

                public void setHeadPortrait(String headPortrait) {
                    this.headPortrait = headPortrait;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }
            }

            public static class DevicesBean implements Serializable {

                public DevicesBean(String equipmentNote, String productId, String equipmentId, boolean isOtherCheck) {
                    this.equipmentNote = equipmentNote;
                    this.productId = productId;
                    this.equipmentId = equipmentId;
                    this.isOtherCheck = isOtherCheck;
                }

                /**
                 * equipmentNote : 易燃气体
                 * productId : zcz003
                 * equipmentId : zcz003100192
                 */

                private String equipmentNote;
                private String productId;
                private String equipmentId;
                private boolean isOtherCheck;

                public boolean isOtherCheck() {
                    return isOtherCheck;
                }

                public void setOtherCheck(boolean otherCheck) {
                    isOtherCheck = otherCheck;
                }

                public String getEquipmentNote() {
                    return equipmentNote;
                }

                public void setEquipmentNote(String equipmentNote) {
                    this.equipmentNote = equipmentNote;
                }

                public String getProductId() {
                    return productId;
                }

                public void setProductId(String productId) {
                    this.productId = productId;
                }

                public String getEquipmentId() {
                    return equipmentId;
                }

                public void setEquipmentId(String equipmentId) {
                    this.equipmentId = equipmentId;
                }
            }
        }

        public static class GivesBean implements Serializable {
            /**
             * devices : [{"equipmentNote":"智能插座","productId":"zcz001","equipmentId":"zcz001101027"},{"equipmentNote":"一氧化碳(co)","productId":"zcz002","equipmentId":"zcz002103910"}]
             * share : {"nickName":"1","headPortrait":"https://thirdwx.qlogo.cn/mmopen/vi_32/Ih0t4ACgYJqvlicJe4NZUia6qfo2jGrK1rhzy4fBKteBuItlqG0KC7ztbwSgZo56Yibw7Bne4MfaepwoCX34uBqjA/132","userId":"minApp113043"}
             * id : 6
             */

            private ShareBeanX share;
            private int id;
            private List<DevicesBeanX> devices;

            public ShareBeanX getShare() {
                return share;
            }

            public void setShare(ShareBeanX share) {
                this.share = share;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<DevicesBeanX> getDevices() {
                return devices;
            }

            public void setDevices(List<DevicesBeanX> devices) {
                this.devices = devices;
            }

            public static class ShareBeanX implements Serializable {
                public ShareBeanX(String nickName, String headPortrait, String userId) {
                    this.nickName = nickName;
                    this.headPortrait = headPortrait;
                    this.userId = userId;
                }

                /**
                 * nickName : 1
                 * headPortrait : https://thirdwx.qlogo.cn/mmopen/vi_32/Ih0t4ACgYJqvlicJe4NZUia6qfo2jGrK1rhzy4fBKteBuItlqG0KC7ztbwSgZo56Yibw7Bne4MfaepwoCX34uBqjA/132
                 * userId : minApp113043
                 */



                private String nickName;
                private String headPortrait;
                private String userId;

                public String getNickName() {
                    return nickName;
                }

                public void setNickName(String nickName) {
                    this.nickName = nickName;
                }

                public String getHeadPortrait() {
                    return headPortrait;
                }

                public void setHeadPortrait(String headPortrait) {
                    this.headPortrait = headPortrait;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }
            }

            public static class DevicesBeanX implements Serializable {

                public DevicesBeanX(String equipmentNote, String productId, String equipmentId, boolean ischeck) {
                    this.equipmentNote = equipmentNote;
                    this.productId = productId;
                    this.equipmentId = equipmentId;
                    this.ischeck = ischeck;
                }

                /**
                 * equipmentNote : 智能插座
                 * productId : zcz001
                 * equipmentId : zcz001101027
                 */

                private String equipmentNote;
                private String productId;
                private String equipmentId;
                private boolean ischeck;

                public boolean isIscheck() {
                    return ischeck;
                }

                public void setIscheck(boolean ischeck) {
                    this.ischeck = ischeck;
                }

                public String getEquipmentNote() {
                    return equipmentNote;
                }

                public void setEquipmentNote(String equipmentNote) {
                    this.equipmentNote = equipmentNote;
                }

                public String getProductId() {
                    return productId;
                }

                public void setProductId(String productId) {
                    this.productId = productId;
                }

                public String getEquipmentId() {
                    return equipmentId;
                }

                public void setEquipmentId(String equipmentId) {
                    this.equipmentId = equipmentId;
                }
            }
        }
    }
}
