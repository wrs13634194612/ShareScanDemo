package com.example.user.mathgame;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

public class SetPwdPhoneActivity extends Activity {
    private CommonTitleBar commonTitleBar;
    private TextView tvUpdate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pwd_new2);

        commonTitleBar = findViewById(R.id.title_activity_qr);
        tvUpdate  = findViewById(R.id.tvUpdate);

        tvUpdate.setOnClickListener(new View.OnClickListener() {
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
                }
            }
        });


    }

}
