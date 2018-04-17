package com.example.thishellotest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SensorActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spinner_Left,spinner_Right ;
    private Button sensorBack,sensorButton_chax;
    private int o=0,id_11=1;
    private LinearLayout L13,L15,L23,L25,L33,L35,L43,L45,L53,L55,L63,L65;

    int T=0;
    int F=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_activity_chuanganqi);

        initViews();
        Spinner();
         YC();

    }

    private void initViews(){
        sensorBack= (Button) findViewById(R.id.Sensor_BackButton);
        spinner_Left=(Spinner)findViewById(R.id.Spinner_left);
        spinner_Right=(Spinner)findViewById(R.id.Spinner_right);
        sensorButton_chax=(Button)findViewById(R.id.Sensor_button);

        /*
        * */
        L13=(LinearLayout)findViewById(R.id.list_layout_13);
        L23=(LinearLayout)findViewById(R.id.list_layout_23);
        L33=(LinearLayout)findViewById(R.id.list_layout_33);
        L43=(LinearLayout)findViewById(R.id.list_layout_43);
        L53=(LinearLayout)findViewById(R.id.list_layout_53);
        L63=(LinearLayout)findViewById(R.id.list_layout_63);
        L15=(LinearLayout)findViewById(R.id.list_layout_15);
        L25=(LinearLayout)findViewById(R.id.list_layout_25);
        L35=(LinearLayout)findViewById(R.id.list_layout_35);
        L45=(LinearLayout)findViewById(R.id.list_layout_45);
        L55=(LinearLayout)findViewById(R.id.list_layout_55);
        L65=(LinearLayout)findViewById(R.id.list_layout_65);

        sensorBack.setOnClickListener(this);
        sensorButton_chax.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Sensor_BackButton:
                //返回
                Intent i00=new Intent(SensorActivity.this,FirstMainActivity.class);
                startActivity(i00);
                finish();
                break;

            case R.id.Sensor_button:
                //查询
                if (F==0&&T==0){
                    //空气温度3
                    YC();
                    L13.setVisibility(View.VISIBLE);
                }
                if (F==1&&T==0){
                    //空气温度5
                    YC();
                    L15.setVisibility(View.VISIBLE);
                }
                if (F==0&&T==1){
                    //空气湿度3
                    YC();
                    L23.setVisibility(View.VISIBLE);
                }
                if (F==1&&T==1){
                    //空气湿度5
                    YC();
                    L25.setVisibility(View.VISIBLE);
                }
                if (F==0&&T==2){
                    //光照3
                    YC();
                    L33.setVisibility(View.VISIBLE);
                }
                if (F==1&&T==2){
                    //光照5
                    YC();
                    L35.setVisibility(View.VISIBLE);
                }
                if (F==0&&T==3){
                    //CO2 3
                    YC();
                    L43.setVisibility(View.VISIBLE);
                }
                if (F==1&&T==3){
                    //CO2 5
                    YC();
                    L45.setVisibility(View.VISIBLE);
                }
                if (F==0&&T==4){
                    //PM2.5 3
                    YC();
                    L53.setVisibility(View.VISIBLE);
                }
                if (F==1&&T==4){
                    //PM2.5 5
                    YC();
                    L55.setVisibility(View.VISIBLE);
                }
                if (F==0&&T==5){
                    //道路状况3
                    YC();
                    L63.setVisibility(View.VISIBLE);
                }
                if (F==1&&T==5){
                    //道路状况5
                    YC();
                    L65.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    private void Spinner(){
        //传感器类型
        spinner_Left.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] left_1 = getResources().getStringArray(R.array.String_left);
               // Toast.makeText(SensorActivity.this, "你点击的是"+left_1[position], Toast.LENGTH_SHORT).show();
                    //从id_1 从0开始的计数；
                if (left_1[position].equals("空气温度")){//类似于id_1==0
                        T=0;
                }
                if (id==1){
                        T=1;
                }
                if (id==2){
                        T=2;
                }
                if (id==3){
                        T=3;
                }
                if (id==4){
                        T=4;
                }
                if (id==5){
                         T=5;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //周期
        spinner_Right.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // String[] left_2 = getResources().getStringArray(R.array.String_right);

                    //从id_1 从0开始的计数；
                    if (id==0){
                        F=0;

                    }else if (id==1){
                        F=1;
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    private void YC(){
        L13.setVisibility(View.GONE);
        L23.setVisibility(View.GONE);
        L33.setVisibility(View.GONE);
        L43.setVisibility(View.GONE);
        L53.setVisibility(View.GONE);
        L63.setVisibility(View.GONE);
        L15.setVisibility(View.GONE);
        L25.setVisibility(View.GONE);
        L35.setVisibility(View.GONE);
        L45.setVisibility(View.GONE);
        L55.setVisibility(View.GONE);
        L65.setVisibility(View.GONE);
    }
}
