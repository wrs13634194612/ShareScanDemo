package com.example.user.mathgame;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import java.util.ArrayList;
import java.util.List;

public class ShareDeviceActivity extends Activity {
    private List<ShareBean> mDevices;
    private RecyclerView gv_devices;
    private OnlineDeviceListAdapter mAdapter;
    private CommonTitleBar commonTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_device);
        gv_devices = findViewById(R.id.rv_online_devices);
        Button btn_share = findViewById(R.id.btn_share);
        Button btn_scan = findViewById(R.id.btn_scan);
        commonTitleBar = findViewById(R.id.title_activity_qr);

        mDevices = new ArrayList<>();
        mDevices.add(new ShareBean(1, false, "智能插座"));
        mDevices.add(new ShareBean(2, true, "易燃气体插座"));
        mDevices.add(new ShareBean(0, false, "零火线单开开关"));

        mAdapter = new OnlineDeviceListAdapter(ShareDeviceActivity.this, mDevices);
        gv_devices.setLayoutManager(new LinearLayoutManager(ShareDeviceActivity.this));
        gv_devices.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        gv_devices.setAdapter(mAdapter);
        mAdapter.setOnViewClickListener(new BaseRecyclerViewAdapter.OnViewClickListener() {
            @Override
            public void onViewClick(View view, int position) {
                Log.e("TAG", view + ":onViewClick:" + position);
            }
        });


        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // type  弹窗类型，xml页面  ，  chs_about_error_dialog
                AboutDialogErrorFragment aboutDialog = new AboutDialogErrorFragment();
                aboutDialog.setDialogContent(false, R.layout.fragment_item_input);
                aboutDialog.show(getFragmentManager(), "AboutDialogFragment");
                aboutDialog.onSetClickDialogListener(new AboutDialogErrorFragment.SetOnClickDialogListener() {

                    @Override
                    public void onClickDialogListener(int type, boolean boolClick, String content) {
                        Log.e("TAG", "onClickDialogListener： " + type + "\t" + boolClick + "\t" + content);
                         /**
                          //这里应该做网络请求 直接使用go做网络请求
                          接下来  给我找到  go的网络请求在哪里  okgo
                          * */

                    }
                });
            }
        });

        commonTitleBar.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    //返回 2
                    finish();
                } else if (action == CommonTitleBar.ACTION_RIGHT_TEXT) {
                    //右侧弹框  4
                    for (int i = 0; i < mDevices.size(); i++) {
                        mDevices.get(i).setChecked(true);
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

    }
}