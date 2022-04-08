package com.example.user.mathgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_share = findViewById(R.id.btn_share);
        Button btn_share_manager = findViewById(R.id.btn_share_manager);

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                //                Intent intent = new Intent(MainActivity.this, ShareDeviceActivity.class);
                //                startActivity(intent);
            }
        });

        btn_share_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShareManagerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getData(){
        List<Device> list = new ArrayList<>();
        Device mDevice1 = new Device();
        Device mDevice2 = new Device();
        mDevice1.setEquipmentNote("十六孔智能插座");
        mDevice1.setProductId("zcz101");


        mDevice2.setEquipmentNote("零火线插座");
        mDevice2.setProductId("zxz102");


        list.add(mDevice1);
        list.add(mDevice2);

        Log.e("TAG",""+list);
        Intent intent = new Intent(MainActivity.this, ShareDeviceActivity.class);
        intent.putExtra("list", (Serializable) list);
        startActivity(intent);

    }
}
