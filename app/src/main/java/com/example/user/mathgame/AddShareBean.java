package com.example.user.mathgame;


import android.content.Intent;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddShareBean {



//   private void getData(){
//       List<ShareReceiveBean.DataBean.GivesBean.DevicesBeanX> list =new ArrayList<ShareReceiveBean.DataBean.GivesBean.DevicesBeanX>();
//       ShareReceiveBean.DataBean.GivesBean.DevicesBeanX mData =
//               new ShareReceiveBean.DataBean.GivesBean.DevicesBeanX("note","pid","eid",false);
//       list.add(mData);
//
//       Intent intent = new Intent(ShareManagerActivity.this, ShareOtherDetailActivity.class);
//       intent.putExtra("list", (Serializable) list);
//       startActivity(intent);
//
//
//
//   }


    private String userId;
    private String code;
    private String share;
    private String devices;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getDevices() {
        return devices;
    }

    public void setDevices(String devices) {
        this.devices = devices;
    }
}
