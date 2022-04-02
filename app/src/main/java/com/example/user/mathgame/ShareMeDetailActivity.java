package com.example.user.mathgame;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import java.util.ArrayList;
import java.util.List;

public class ShareMeDetailActivity extends Activity {
    private CommonTitleBar commonTitleBar;

    private List<ShareReceiveBean.DataBean.GivesBean> meList;
    //    private OnlineDeviceListAdapter mAdapter;

    //    private RecyclerView rv_share_detail;
    private String name;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_me_detail);


        commonTitleBar = findViewById(R.id.title_activity_qr);
        TextView tv_name_share_content = findViewById(R.id.tv_name_share_content);
        TextView tv_id_share_content = findViewById(R.id.tv_id_share_content);

        // rv_share_detail = findViewById(R.id.rv_share_detail);

        /**
         不需要做网络请求了   把list传递过来就行 然后展示  先把list传过来

         intent.putExtra("list",(Serializable) meList.get(finalI).getDevices());
         * */
        meList = new ArrayList<>();

        //        list = (ArrayList<StudentBean>)  intent.getSerializableExtra("list");
        //        ArrayList list =  (List<Object>) getIntent().getSerialzableExtra(list);
        //  list = (ArrayList<StudentBean>)  intent.getSerializableExtra("list");

        //表示传递过来的是melist
        meList.addAll((ArrayList<ShareReceiveBean.DataBean.GivesBean>) getIntent().getSerializableExtra("list"));
        name = getIntent().getStringExtra("name");
        id = getIntent().getStringExtra("id");


        //     mAdapter = new OnlineDeviceListAdapter(ShareMeDetailActivity.this, mDevices);
//            rv_share_detail.setLayoutManager(new LinearLayoutManager(ShareMeDetailActivity.this));
//            rv_share_detail.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//            rv_share_detail.setAdapter(mAdapter);

        Log.e("TAG", "ShareMeDetailActivity: " + "\t" + meList + "\t" + name + "\t" + id);

        tv_name_share_content.setText(name);
        tv_id_share_content.setText(id);

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