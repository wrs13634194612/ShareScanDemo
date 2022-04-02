package com.example.user.mathgame;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import java.util.ArrayList;
import java.util.List;

public class ShareManagerActivity extends Activity {

    /**
     * 自己写一个循环列表  LinearLayout实现  ListView的效果
     */
    private List<String> list = new ArrayList<>();
    private List<String> listOther = new ArrayList<>();
    private CommonTitleBar commonTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_manager);

        list.add("完美世界");
        list.add("遮天");
        list.add("完美世界");
        list.add("遮天");
        list.add("完美世界");
        list.add("遮天");
        list.add("完美世界");
        list.add("遮天");

        listOther.add("深渊");
        listOther.add("恶魔");
        listOther.add("玫瑰");
        listOther.add("深渊");
        listOther.add("恶魔");
        listOther.add("玫瑰");
        listOther.add("深渊");
        listOther.add("恶魔");
        listOther.add("玫瑰");
        listOther.add("深渊");
        listOther.add("恶魔");
        listOther.add("玫瑰");

        LinearLayout ll_me = findViewById(R.id.ll_me);
        LinearLayout ll_other = findViewById(R.id.ll_other);
        commonTitleBar = findViewById(R.id.title_activity_qr);


        for (int position = 0; position < list.size(); position++) {
            View view = LayoutInflater.from(ShareManagerActivity.this).inflate(R.layout.item_share_manager, null);
            TextView tv_item = view.findViewById(R.id.tv_item);
            tv_item.setText(list.get(position));
            final int finalI = position;//由于OnClick里面拿不到i,所以需要重新赋值给一个final对象
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("TAG", "click:" + list.get(finalI));
                }
            });
            ll_me.addView(view);
        }

        for (int position = 0; position < listOther.size(); position++) {
            View viewOther = LayoutInflater.from(ShareManagerActivity.this).inflate(R.layout.item_share_manager, null);
            TextView tv_item = viewOther.findViewById(R.id.tv_item);
            tv_item.setText(listOther.get(position));
            final int finalI = position;//由于OnClick里面拿不到i,所以需要重新赋值给一个final对象
            viewOther.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("TAG", "click:" + listOther.get(finalI));
                }
            });
            ll_other.addView(viewOther);
        }

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