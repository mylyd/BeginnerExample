package com.example.a13466.mytime;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView mTextTime;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        handler = new Handler();
        timer.schedule(tt,0,1000);
        //startService(new Intent(this,MyService.class));
    }
    Timer timer=new Timer();
    TimerTask tt=new TimerTask() {
        @Override
        public void run() {
            setData();
        }
    };
    private void setData(){
        SimpleDateFormat f=new SimpleDateFormat("HH:mm:ss");
        Date D=new Date(System.currentTimeMillis());
        final String str=f.format(D);
        handler.post(new Runnable() {
            @Override
            public void run() {
                mTextTime.setText(str);
            }
        });
    }


    private void initView() {
        mTextTime = (TextView) findViewById(R.id.time_text);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
    public void setViewPager(View v){
        startActivity(new Intent(this,ViewPagerActivity.class));


    }
}
