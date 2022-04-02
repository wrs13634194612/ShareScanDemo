package com.example.user.mathgame;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShareManagerActivity extends Activity {
    String url = "https://www.mindordz.com:8181/mindor/shc/getDeviceShares";

    /**
     * 自己写一个循环列表  LinearLayout实现  ListView的效果
     */
    private CommonTitleBar commonTitleBar;
    private LinearLayout ll_me;
    private LinearLayout ll_other;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if (message.what == 100) {
                String post = (String) message.obj;
              /*  ShareReceiveBean mShareReceiveBean = JSONObject.parseObject(response.body(), ShareReceiveBean.class);
                Log.e("TAG", "AddActivity_onSuccess:" + mShareReceiveBean);*/
                final List<ShareReceiveBean.DataBean.GivesBean> meList = new ArrayList<>();
                final List<ShareReceiveBean.DataBean.OwnersBean> otherList = new ArrayList<>();
                Gson gson = new Gson();
                ShareReceiveBean mShareReceiveBean = gson.fromJson(post, ShareReceiveBean.class);
                meList.addAll(mShareReceiveBean.getData().getGives());
                otherList.addAll(mShareReceiveBean.getData().getOwners());
                System.out.println(meList + "\t" + otherList);

                for (int position = 0; position < meList.size(); position++) {
                    View view = LayoutInflater.from(ShareManagerActivity.this).inflate(R.layout.item_share_manager, null);
                    TextView tv_share_number = view.findViewById(R.id.tv_share_number);
                    TextView tv_share_name = view.findViewById(R.id.tv_share_name);
                    tv_share_name.setText(meList.get(position).getShare().getNickName());
                    tv_share_number.setText(meList.get(position).getDevices().size() + "个设备");
                    final int finalI = position;//由于OnClick里面拿不到i,所以需要重新赋值给一个final对象
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.e("TAG", "click:" + meList.get(finalI).getId());
                            Intent intent = new Intent(ShareManagerActivity.this, ShareMeDetailActivity.class);
                            intent.putExtra("list", (Serializable) meList.get(finalI).getDevices());
                            intent.putExtra("name", meList.get(finalI).getShare().getNickName());
                            intent.putExtra("userId", meList.get(finalI).getShare().getUserId());
                            intent.putExtra("givesId", meList.get(finalI).getId());
                            startActivity(intent);
                        }
                    });
                    ll_me.addView(view);
                }

                for (int position = 0; position < otherList.size(); position++) {
                    View viewOther = LayoutInflater.from(ShareManagerActivity.this).inflate(R.layout.item_share_manager, null);
                    TextView tv_share_name = viewOther.findViewById(R.id.tv_share_name);
                    TextView tv_share_number = viewOther.findViewById(R.id.tv_share_number);
                    tv_share_number.setText(otherList.get(position).getDevices().size() + "个设备");
                    tv_share_name.setText(otherList.get(position).getShare().getNickName());
                    final int finalI = position;//由于OnClick里面拿不到i,所以需要重新赋值给一个final对象
                    viewOther.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.e("TAG", "click:" + otherList.get(finalI).getShare().getNickName());
                            Intent intent = new Intent(ShareManagerActivity.this, ShareOtherDetailActivity.class);
                            intent.putExtra("list", (Serializable) otherList.get(finalI).getDevices());
                            intent.putExtra("name", otherList.get(finalI).getShare().getNickName());
                            intent.putExtra("userId", otherList.get(finalI).getShare().getUserId());
                            intent.putExtra("givesId", otherList.get(finalI).getId());
                            startActivity(intent);
                        }
                    });
                    ll_other.addView(viewOther);
                }
            }
            return false;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_manager);
        ll_me = findViewById(R.id.ll_me);
        ll_other = findViewById(R.id.ll_other);
        commonTitleBar = findViewById(R.id.title_activity_qr);

        commonTitleBar.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    //返回 2
                    finish();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        OkGo.<String>get(url)
                .params("userId", "minApp108881")
                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        Message msg = new Message();
                        msg.what = 100;
                        msg.obj = response.body();
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                        Log.e("TAG", "onError:" + response);
                    }
                });
    }
}