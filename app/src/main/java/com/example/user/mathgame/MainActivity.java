package com.example.user.mathgame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_splash = findViewById(R.id.btn_splash);

        btn_splash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SetPwdPhoneActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(MainActivity.this, ShareDeviceActivity.class);
        startActivity(intent);
    }
}