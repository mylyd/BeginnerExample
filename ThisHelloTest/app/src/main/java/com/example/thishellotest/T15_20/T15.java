package com.example.thishellotest.T15_20;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.thishellotest.FirstMainActivity;
import com.example.thishellotest.R;

import java.util.Date;

public class T15 extends AppCompatActivity implements View.OnClickListener {
    private Button back;
    private Button B1,B2;
    private Spinner S1,S2;
    private EditText E1;
    int F=0,T=0,y1=0,y2=0,y3=0,y4=0;
    String YY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t15);
        intvvv();
        Spinner();
    }

    private void intvvv(){
        back=(Button)findViewById(R.id.T15_back);
        back.setOnClickListener(this);
        B1= (Button) findViewById(R.id.T15_button_chaxun_1);
        B2= (Button) findViewById(R.id.T15_button_chaxun_2);
        B1.setOnClickListener(this);
        B2.setOnClickListener(this);
        S1= (Spinner) findViewById(R.id.T15_Spinner_1);
        S2= (Spinner) findViewById(R.id.T15_Spinner_2);
        E1= (EditText) findViewById(R.id.T15_EditText);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.T15_back:
                Intent i1=new Intent(T15.this, FirstMainActivity.class);
                startActivity(i1);
                finish();
                break;
            case R.id.T15_button_chaxun_1:
                if (F==1){
                    Toast.makeText(this, "余额为:"+y1+"元", Toast.LENGTH_SHORT).show();
                }
                if (F==2){
                    Toast.makeText(this, "余额为:"+y2+"元", Toast.LENGTH_SHORT).show();
                }
                if (F==3){
                    Toast.makeText(this, "余额为:"+y3+"元", Toast.LENGTH_SHORT).show();
                }
                if (F==4){
                    Toast.makeText(this, "余额为:"+y4+"元", Toast.LENGTH_SHORT).show();
                }
                E1.setText(null);
                break;
            case R.id.T15_button_chaxun_2:
                SimpleDateFormat formatter=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date curDate =  new Date(System.currentTimeMillis());
                String str=formatter.format(curDate);
                if (E1.length()==0){
                    Toast.makeText(this, "充值金额不能为空", Toast.LENGTH_SHORT).show();
                }
                if (E1.length()!=0){
                    YY=E1.getText().toString();

                if(T==1){
                    int yy1=Integer.parseInt(YY);
                    y1=y1+yy1;
                }
                if(T==2){
                    int yy2=Integer.parseInt(YY);
                    y2=y2+yy2;
                }
                if(T==3){
                    int yy3=Integer.parseInt(YY);
                    y3=y3+yy3;
                }
                if(T==4){
                    int yy4=Integer.parseInt(YY);
                    y3=y3+yy4;
                }


                ProgressDialog prodressDialog=new ProgressDialog(T15.this);
                Window window=prodressDialog.getWindow();
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.alpha = 0.8f; // 透明度
                window.setAttributes(lp);
                prodressDialog.setTitle("小车账户充值");
                prodressDialog.setMessage("在"+str+"给"+F+"号小车充值"+YY+"元");
                prodressDialog.setCancelable(true);//能否取消该属性；
                //DialogInterface.BUTTON_NEGATIVE这个属性使显示在最右边的左边
                prodressDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"取  消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //
                        Toast.makeText(T15.this, "取消充值", Toast.LENGTH_SHORT).show();
                    }
                });
                //DialogInterface.BUTTON_NEUTRAL这个属性使显示在最左边
                prodressDialog.setButton(DialogInterface.BUTTON_NEUTRAL,"忽  略", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                });

                //DialogInterface.BUTTON_POSITIVE这个属性使显示在最右边的右边
                prodressDialog.setButton(DialogInterface.BUTTON_POSITIVE,"确  定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(T15.this, "充值成功", Toast.LENGTH_SHORT).show();
                    }
                });
                prodressDialog.show();
                }
                break;


        }
    }

    private void Spinner(){
        S1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (id==0){
                    F=1;
                }
                if (id==1){
                    F=2;
                }
                if (id==2){
                    F=3;
                }
                if (id==3){
                    F=4;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        S2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (id==0){
                    T=1;
                }
                if (id==1){
                    T=2;
                }
                if (id==2){
                    T=3;
                }
                if (id==3){
                    T=4;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
