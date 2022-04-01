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
        Button btn_share = findViewById(R.id.btn_share); //全选
        Button btn_scan = findViewById(R.id.btn_scan);  //拿到全选的name数据
        Button btn_share2 = findViewById(R.id.btn_share2);    //账号分享
        Button btn_scan2 = findViewById(R.id.btn_scan2);         //扫码分享
        Button btn_share3 = findViewById(R.id.btn_share3);
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
                Log.e("TAG", "" + mDevices);
                StringBuffer buf = new StringBuffer();
                for (int i = 0; i < mDevices.size(); i++) {
                    buf.append(mDevices.get(i).getName());
                    if (i < mDevices.size() - 1) {
                        buf.append(",");
                    }
                }
                String name = buf.toString();
                Log.e("TAG", "" + name);
            }
        });
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < mDevices.size(); i++) {
                    mDevices.get(i).setChecked(true);
                }
                mAdapter.notifyDataSetChanged();
            }
        });

        btn_share2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutDialogFragment aboutDialog = new AboutDialogFragment();
                aboutDialog.setName("韩红");
                aboutDialog.show(getFragmentManager(), "AboutDialogFragment");
                aboutDialog.onSetClickDialogListener(new AboutDialogFragment.SetOnClickDialogListener() {
                    @Override
                    public void onClickDoalogListener(int type, boolean boolClick) {
                        //Log.e("TAG", "return:" + type + "==" + boolClick);
                        //finish();  //点击了知道了  结束掉页面
                    }
                });
            }
        });

        btn_share3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutDialogErrorFragment aboutDialog = new AboutDialogErrorFragment();
                aboutDialog.show(getFragmentManager(), "AboutDialogFragment");
                aboutDialog.onSetClickDialogListener(new AboutDialogErrorFragment.SetOnClickDialogListener() {
                    @Override
                    public void onClickDoalogListener(int type, boolean boolClick) {
                        //Log.e("TAG", "return:" + type + "==" + boolClick);
                        //finish();  //点击了知道了  结束掉页面
                    }
                });
            }
        });
        btn_scan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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