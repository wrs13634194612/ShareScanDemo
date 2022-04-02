package com.example.user.mathgame;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShareOtherDetailActivity extends Activity {
    private CommonTitleBar commonTitleBar;
    private List<String> dataList;
    private List<ShareReceiveBean.DataBean.OwnersBean.DevicesBean> otherList;
    //    private OnlineDeviceListAdapter mAdapter;
    //    private RecyclerView rv_share_detail;
    private String name;
    private int givesId;
    private String userId;
    private String url = "https://www.mindordz.com:8181/mindor/shc/removeDeviceShare";
    private RecyclerView gv_devices;
    private ShareOtherDetailAdapter mAdapter;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if (message.what == 101) {
                //刷新页面  如果为null  返回上一页  {"code":200,"message":"解除授权成功"}
                String post = (String) message.obj;
                Gson gson = new Gson();
                DeleteShareBean mDeleteShareBean = gson.fromJson(post, DeleteShareBean.class);
                if (mDeleteShareBean.getCode() == 200) {
                    // 表示成功
                    finish();
                    Toast.makeText(ShareOtherDetailActivity.this,
                            "删除成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ShareOtherDetailActivity.this,
                            "删除失败", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(ShareOtherDetailActivity.this,
                        "删除失败", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_other_detail);

        commonTitleBar = findViewById(R.id.title_activity_qr);
        TextView tv_name_share_content = findViewById(R.id.tv_name_share_content);
        TextView tv_id_share_content = findViewById(R.id.tv_id_share_content);
        Button btn_remove_share_detail = findViewById(R.id.btn_remove_share_detail);
        gv_devices = findViewById(R.id.rv_share_detail);

        // rv_share_detail = findViewById(R.id.rv_share_detail);

        /**
         不需要做网络请求了   把list传递过来就行 然后展示  先把list传过来

         intent.putExtra("list",(Serializable) meList.get(finalI).getDevices());
         * */
        otherList = new ArrayList<>();
        dataList = new ArrayList<>();
        //        list = (ArrayList<StudentBean>)  intent.getSerializableExtra("list");
        //        ArrayList list =  (List<Object>) getIntent().getSerialzableExtra(list);
        //  list = (ArrayList<StudentBean>)  intent.getSerializableExtra("list");

        //表示传递过来的是melist
        otherList.addAll((ArrayList<ShareReceiveBean.DataBean.OwnersBean.DevicesBean>) getIntent().getSerializableExtra("list"));
        name = getIntent().getStringExtra("name");
        userId = getIntent().getStringExtra("userId");
        givesId = getIntent().getIntExtra("givesId", -1);


        //     mAdapter = new OnlineDeviceListAdapter(ShareMeDetailActivity.this, mDevices);
        //            rv_share_detail.setLayoutManager(new LinearLayoutManager(ShareMeDetailActivity.this));
        //            rv_share_detail.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //            rv_share_detail.setAdapter(mAdapter);

        Log.e("TAG", "ShareMeDetailActivity: " + "\t" + otherList + "\t" + name + "\t" + userId + "\t" + givesId);

        tv_name_share_content.setText(name);
        tv_id_share_content.setText(userId);

        /**
         * 好  数据传递过来了  接下来   是做页面   然后做删除  先把ui做了
         *接下来做删除   选中删除   获取数据  把列表搞了
         * 接下来做移除共享的事情
         * 首先看接口   然后在okgo里面调用测试   然后在移植到我们的项目里
         * 这个可以了  接下来是拿到id和选的数据
         *字符串拿到了  接下来就是做网络请求  做移除
         *
         * 我需要在字段里面新增一个标记的字段 用于标记数据是否被删除 默认全部为false
         */
        //        mAdapter.setOnViewClickListener(new BaseRecyclerViewAdapter.OnViewClickListener() {
        //            @Override
        //            public void onViewClick(View view, int position) {
        //                Log.e("TAG", view + ":onViewClick:" + position);
        //            }
        //        });

        mAdapter = new ShareOtherDetailAdapter(ShareOtherDetailActivity.this, otherList);
        gv_devices.setLayoutManager(new LinearLayoutManager(ShareOtherDetailActivity.this));
        gv_devices.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        gv_devices.setAdapter(mAdapter);

        mAdapter.setOnViewClickListener(new BaseRecyclerViewAdapter.OnViewClickListener() {
            @Override
            public void onViewClick(View view, int position) {
                Log.e("TAG", view + ":onViewClick:" + position + otherList);
                /**
                 * 选中的时候   为true  否则为false
                 */
                //选中的时候  应该改变device数据的状态并刷新
                // 选中状态并刷新  先去把布局改一下
                if (otherList.get(position).isOtherCheck()) {
                    otherList.get(position).setOtherCheck(false);
                } else {
                    otherList.get(position).setOtherCheck(true);
                }
                mAdapter.resetDevices(otherList);
            }
        });

        commonTitleBar.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    //返回 2
                    finish();
                }
            }
        });

        btn_remove_share_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //移除  获取adapter  选中的值
                if (!dataList.isEmpty()) {
                    dataList.clear();
                }

                for (int i = 0; i < otherList.size(); i++) {
                    if (otherList.get(i).isOtherCheck()) {
                        dataList.add(otherList.get(i).getEquipmentId());
                    }
                }
                //数据已经拿到  接下来做网络请求 移除
                getData(listToString5(dataList, ','));
            }
        });

    }

    public String listToString5(List list, char separator) {
        return StringUtils.join(list.toArray(), separator);
    }

    private void getData(String equipmentId) {
        Log.e("TAG","other:"+givesId+"\t"+equipmentId);
        OkGo.<String>put(url)
                .params("id", givesId)
                .params("equipmentId", equipmentId)
                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        /// DeleteBean mDeleteBean = JSONObject.parseObject(response.body(), DeleteBean.class);
                        Log.e("TAG", "onSuccess:" + response.body());
                        Message msg = new Message();
                        msg.what = 101;
                        msg.obj = response.body();
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                        Log.e("TAG", "onError:" + response);
                        Message msg = new Message();
                        msg.what = 104;
                        msg.obj = response.body();
                        mHandler.sendMessage(msg);
                    }
                });
    }
}