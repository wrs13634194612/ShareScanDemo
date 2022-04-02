package com.example.user.mathgame;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import java.util.ArrayList;
import java.util.List;

public class ShareOtherDetailActivity extends Activity {
    private CommonTitleBar commonTitleBar;

    private List<ShareReceiveBean.DataBean.OwnersBean> otherList;
//    private OnlineDeviceListAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_other_detail);


        commonTitleBar = findViewById(R.id.title_activity_qr);

        // rv_share_detail = findViewById(R.id.rv_share_detail);

        /**
         不需要做网络请求了   把list传递过来就行 然后展示  先把list传过来

         intent.putExtra("list",(Serializable) meList.get(finalI).getDevices());
         * */
        otherList = new ArrayList<>();

        //        list = (ArrayList<StudentBean>)  intent.getSerializableExtra("list");
        //        ArrayList list =  (List<Object>) getIntent().getSerialzableExtra(list);
        //  list = (ArrayList<StudentBean>)  intent.getSerializableExtra("list");

        //表示传递过来的是melist
        otherList.addAll((ArrayList<ShareReceiveBean.DataBean.OwnersBean>) getIntent().getSerializableExtra("list"));

        //     mAdapter = new OnlineDeviceListAdapter(ShareMeDetailActivity.this, mDevices);
//            rv_share_detail.setLayoutManager(new LinearLayoutManager(ShareMeDetailActivity.this));
//            rv_share_detail.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//            rv_share_detail.setAdapter(mAdapter);

        Log.e("TAG", "ShareMeDetailActivity: " + otherList );


        /**
         * 好  数据传递过来了  接下来   是做页面   然后做删除  先把ui做了
         *
         *接下来做删除   选中删除   获取数据
         *
         */


//        mAdapter.setOnViewClickListener(new BaseRecyclerViewAdapter.OnViewClickListener() {
//            @Override
//            public void onViewClick(View view, int position) {
//                Log.e("TAG", view + ":onViewClick:" + position);
//            }
//        });


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


}