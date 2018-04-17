package com.example.thishellotest.T15_20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.thishellotest.FirstMainActivity;
import com.example.thishellotest.GuideActivity;
import com.example.thishellotest.MainActivity;
import com.example.thishellotest.R;

import java.util.Timer;
import java.util.TimerTask;

public class T16 extends AppCompatActivity implements View.OnClickListener {
    private Button back,CC;
    private LinearLayout L1,L2,L3,L4;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t16);
        intiv();
        FF();
    }

    private void intiv(){
        back= (Button) findViewById(R.id.T16_back);
        back.setOnClickListener(this);
        CC= (Button) findViewById(R.id.T16_chaxun);
        CC.setOnClickListener(this);
        L1= (LinearLayout) findViewById(R.id.T16_L1);
        L2= (LinearLayout) findViewById(R.id.T16_L2);
        L3= (LinearLayout) findViewById(R.id.T16_L3);
        L4= (LinearLayout) findViewById(R.id.T16_L4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.T16_back:
                Intent i1 = new Intent(T16.this, FirstMainActivity.class);
                startActivity(i1);
                finish();
                break;
            case R.id.T16_chaxun:
                i++;
                if (i == 1){
                    FF();
                    L1.setVisibility(View.VISIBLE);
                }
                if (i == 2){
                    FF();
                L2.setVisibility(View.VISIBLE);
                }
                if (i==3){
                    FF();
                L3.setVisibility(View.VISIBLE);
                }
                if (i==4){
                    FF();
                    L4.setVisibility(View.VISIBLE);
                    i=0;
                }

                break;

        }
    }
    /*private void times1(){
        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                L1.setVisibility(View.GONE);
                L2.setVisibility(View.VISIBLE);
            }
        };timer.schedule(timerTask,500);
    }*/
    private void FF(){
        L1.setVisibility(View.GONE);
        L2.setVisibility(View.GONE);
        L3.setVisibility(View.GONE);
        L4.setVisibility(View.GONE);
    }

}
