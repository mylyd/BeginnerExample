package com.example.thishellotest.T15_20;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.thishellotest.FirstMainActivity;
import com.example.thishellotest.R;

public class T18 extends AppCompatActivity implements View.OnClickListener {
    private Button back,c1,c2,c3;
    int a=1,b=1,c=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t18);
        
        intb();
    }
    private void intb(){
        back= (Button) findViewById(R.id.T18_back);
        back.setOnClickListener(this);
        c1= (Button) findViewById(R.id.T18_car_1);
        c1.setOnClickListener(this);
        c2= (Button) findViewById(R.id.T18_car_2);
        c2.setOnClickListener(this);
        c3= (Button) findViewById(R.id.T18_car_3);
        c3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.T18_back:
                Intent i1 = new Intent(T18.this, FirstMainActivity.class);
                startActivity(i1);
                finish();
                break;
            case R.id.T18_car_1:
                if (a==1){
                    c1.setBackgroundResource(R.drawable.car_offf);
                    Snackbar.make(v,"小车已启动",Snackbar.LENGTH_LONG).setAction("", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }).show();
                    a=2;
                    return;
                }
                if (a==2){
                    c1.setBackgroundResource(R.drawable.car_onnn);
                    Snackbar.make(v,"小车已停止",Snackbar.LENGTH_LONG).setAction("", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }).show();
                    a=1;
                    return;
                }
                break;
            case R.id.T18_car_2:
                if (b==1){
                    c2.setBackgroundResource(R.drawable.car_offf);
                    Snackbar.make(v,"小车已启动",Snackbar.LENGTH_LONG).setAction("", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }).show();
                    b=2;
                    return;
                }
                if (b==2){
                    c2.setBackgroundResource(R.drawable.car_onnn);
                    Snackbar.make(v,"小车已停止",Snackbar.LENGTH_LONG).setAction("", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }).show();
                    b=1;
                    return;
                }
                break;
            case R.id.T18_car_3:
                if (c==1){
                    c3.setBackgroundResource(R.drawable.car_offf);
                    Snackbar.make(v,"小车已启动",Snackbar.LENGTH_LONG).setAction("", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }).show();
                    c=2;
                    return;
                }
                if (c==2){
                    c3.setBackgroundResource(R.drawable.car_onnn);
                    Snackbar.make(v,"小车已停止",Snackbar.LENGTH_LONG).setAction("", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }).show();
                    c=1;
                    return;
                }
                break;
        }
    }
}
