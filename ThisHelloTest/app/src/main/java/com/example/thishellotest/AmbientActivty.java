package com.example.thishellotest;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import java.util.logging.LogRecord;

import Service.MyService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AmbientActivty extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private Button TitleButton;
    private TextView M1,M2,M3,M4,M5,M6;
    private int t1,t2,t3,t4,t5;
    private  TextView text1;
    private  TextView text2;
    private  TextView text3;
    private  TextView text4;
    private  TextView text5;
    private  TextView text6;
    private Handler handler;
    private Runnable runnable;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ambient_activty);
            openService();      //启动服务
            vvv();

        //隐藏title
        ActionBar actionbar=getSupportActionBar();
        if (actionbar!=null) {
            actionbar.hide();
        }

        //延迟更新
         handler=new Handler();
         runnable=new Runnable() {
             @Override
             public void run() {
                UpdateData();
             }
         };

        /*返回按钮
        * */
        TitleButton=(Button)findViewById(R.id.Title_button);
        TitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent titleintent=new Intent(AmbientActivty.this,FirstMainActivity.class);
                startActivity(titleintent);
                finish();
            }
        });

        //下拉更新；
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (DataSupport.count(BookFourth.class)!=0){
                    BookFourth bookFourthlast = DataSupport.findLast(BookFourth.class);
                    t1 = bookFourthlast.getTemperature();
                    t2 = bookFourthlast.getHumidity();
                    t3 = bookFourthlast.getLight();
                    t4 = bookFourthlast.getCo2();
                    t5 = bookFourthlast.getPm25();

                    iout();
                }
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }
 /**
     * 活动由不可见到可见时会调用调用
     */
    @Override
    protected void onStart() {
        super.onStart();
        UpdateData();
    }

    /**
     * 活动由可见到不可见时调用
     */
    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable); //停止更新ui
    }


    private void UpdateData(){
        if (DataSupport.count(BookFourth.class)!=0){
        BookFourth bookFourthlast = DataSupport.findLast(BookFourth.class);
        t1 = bookFourthlast.getTemperature();
        t2 = bookFourthlast.getHumidity();
        t3 = bookFourthlast.getLight();
        t4 = bookFourthlast.getCo2();
        t5 = bookFourthlast.getPm25();

         iout();
        }
        handler.postDelayed(runnable,5000);//等待5秒
    }

    private void vvv(){
        M1=(TextView)findViewById(R.id.Text_temperature_Max);
        M2=(TextView)findViewById(R.id.Text_humidity_Max);
        M3=(TextView)findViewById(R.id.Text_beam_Max);
        M4=(TextView)findViewById(R.id.Text_CO2_Max);
        M5=(TextView)findViewById(R.id.Text_pm25_Max);
        M6=(TextView)findViewById(R.id.Text_road_Max);
        text1=(TextView)findViewById(R.id.Text_temperature_Min);
        text2=(TextView)findViewById(R.id.Text_humidity_Min);
        text3=(TextView)findViewById(R.id.Text_beam_Min);
        text4=(TextView)findViewById(R.id.Text_CO2_Min);
        text5=(TextView)findViewById(R.id.Text_pm25_Min);
        text6=(TextView)findViewById(R.id.Text_road_Min);
    }
    /*
    道路指标点击事件；
    */
    //空气温度
    public void temperature(View v){
        Intent intentTemperature=new Intent(this, ViewPagerAmbientActivity.class);
        intentTemperature.putExtra("id",0);
        startActivity(intentTemperature);
        finish();
    }
    //空气湿度
    public void humidity(View v){
        Intent intentTemperature=new Intent(this, ViewPagerAmbientActivity.class);
        intentTemperature.putExtra("id",1);
        startActivity(intentTemperature);
        finish();
    }
    //光照
    public void beam(View v){
        Intent intentTemperature=new Intent(this, ViewPagerAmbientActivity.class);
        intentTemperature.putExtra("id",2);
        startActivity(intentTemperature);
        finish();
    }
    //二氧化碳
    public void CO2(View v){
        Intent intentTemperature=new Intent(this, ViewPagerAmbientActivity.class);
        intentTemperature.putExtra("id",3);
        startActivity(intentTemperature);
        finish();
    }
    //PM2.5
    public void pm25(View v){
        Intent intentTemperature=new Intent(this, ViewPagerAmbientActivity.class);
        intentTemperature.putExtra("id",4);
        startActivity(intentTemperature);
        finish();
    }
    //道路状况
    public void road(View v){
        Intent intentTemperature=new Intent(this, ViewPagerAmbientActivity.class);
        intentTemperature.putExtra("id",5);
        startActivity(intentTemperature);
        finish();
    }

    private void SiSe(){
       if (t1>20){//温度
            M1.setBackgroundResource(R.drawable.red);
        }else {
            M1.setBackgroundResource(R.drawable.green);
        }
        if (t2>30){//湿度
            M2.setBackgroundResource(R.drawable.red);
        }else {
            M2.setBackgroundResource(R.drawable.green);
        }
        if (t3>2000){//光照
            M3.setBackgroundResource(R.drawable.red);
        }else {
            M3.setBackgroundResource(R.drawable.green);
        }
        if (t4>3000){//CO2
            M4.setBackgroundResource(R.drawable.red);
        }else {
            M4.setBackgroundResource(R.drawable.green);
        }
        if (t5>150){//pm2.5
            M5.setBackgroundResource(R.drawable.red);
        }else {
            M5.setBackgroundResource(R.drawable.green);
        }
    }

    private void iout(){
        text1.setText(String.valueOf(t1));
        text2.setText(String.valueOf(t2));
        text3.setText(String.valueOf(t3));
        text4.setText(String.valueOf(t4));
        text5.setText(String.valueOf(t5));
        text6.setText("1");
        SiSe();
    }

    /**
     * 启动服务
     */
    private void openService(){
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }
}
