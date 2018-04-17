package com.example.thishellotest;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

public class AccountRecharge extends AppCompatActivity implements View.OnClickListener{
    private Button backButton;
    private Button QueryButton;
    private Button RechargeButton;
    private Button Fazhi;
    private Button bto;
    private TextView QueryText;
    private TextView CarNum;
    private EditText RechargeEdit;
    private BookSecond lastbs= DataSupport.findLast(BookSecond.class);
    private int I=lastbs.getIdt();
    private List<BookSecond> bslast=DataSupport.select("carnumber","speedmax","speedmin").where("idt=?",String.valueOf(I)).find(BookSecond.class);
    private Book last= DataSupport.findLast(Book.class);
    private String Carnum,Max,Min;
    private String M , str;
    private String num,mon,time;
    private int F=1;
    int ID=last.getIde();//最后一组数据的ID
    private String Y=last.getMoney();//最后一组数据的money;
    int y=Integer.parseInt(Y);//获取最后一组数money的大小


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_recharge_zhanghuchongzhi);
        inVVV();


        //获取设置阀值（账户）时的车号
        for (BookSecond lastnum:bslast){
            Carnum=lastnum.getCarnumber();
            Max=lastnum.getSpeedmax();
            Min=lastnum.getSpeedmin();
        }
        //获取系统时间
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date curDate =  new Date(System.currentTimeMillis());
        str=formatter.format(curDate);

    }
    private void inVVV(){
        backButton=(Button)findViewById(R.id.AccountRecharge_BackButton);
        QueryButton= (Button) findViewById(R.id.AccountRecharge_QueryButton);
        RechargeButton= (Button) findViewById(R.id.AccountRecharge_RechargeButton);
        QueryText=(TextView)findViewById(R.id.AccountRecharge_QueryTextView);
        CarNum=(TextView)findViewById(R.id.AccountRecharge_CarTextView);
        RechargeEdit= (EditText) findViewById(R.id.AccountRecharge_RechargeEditText);
        bto= (Button) findViewById(R.id.AccountRecharge_Button);
        Fazhi=(Button) findViewById(R.id.AccountRecharge_FaZhiBack);

        backButton.setOnClickListener(this);
        QueryButton.setOnClickListener(this);
        RechargeButton.setOnClickListener(this);
        bto.setOnClickListener(this);
        Fazhi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.AccountRecharge_BackButton:
                //返回
                Intent i=new Intent(this,FirstMainActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.AccountRecharge_Button:
                //历史记录查询
                Intent i111=new Intent(this,RechargeActivity.class);
                startActivity(i111);
                finish();
                break;

            case R.id.AccountRecharge_QueryButton:
                //查询
                if (F==1){
                    CarNum.setText(Carnum);
                    QueryText.setText("余额："+Y+" 元");
                }
                if (F==2){
                    //按照id查询最后一组数据
                    List<Book> bf=DataSupport.select("number","money","time").where("ide=?",String.valueOf(ID+1)).find(Book.class);
                    for (Book fir:bf){
                        num=fir.getNumber();
                        mon=fir.getMoney();
                        time=fir.getTime();
                    }
                    //显示到页面
                    int e=Integer.parseInt(mon);
                    QueryText.setText("余额："+String.valueOf(e+y)+" 元");
                    CarNum.setText(num);
                    Toast.makeText(this, "上次充值时间： "+time, Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.AccountRecharge_RechargeButton:
                //充值
                if (RechargeEdit.length()==0){
                    Toast.makeText(this, "您还没有输入金额", Toast.LENGTH_SHORT).show();
                }else {
                    M=RechargeEdit.getText().toString();
                    //强制类型转换判断大小
                    int A=Integer.parseInt(M);//获取充值的金额(这是你充值的金额数)
                    int m=Integer.parseInt(Max);//(这是最大阀值)
                    int n=Integer.parseInt(Min);//这是最小阀值
                    //判断余额是否在阀值范围
                    if (y<=m){
                        if (A<=(m-y)&&A>=n){//充值金额必须小于：最大值-余额
                            F=2;
                            //储存数据
                            Book b=new Book();
                            b.setIde(ID+1);
                            b.setNamee("admin");
                            b.setNumber(Carnum);
                            b.setMoney(M);
                            b.setTime(str);
                            b.save();
                            //清除数据库(删除第三条后面于最后一条数据中间的数据)
                            DataSupport.deleteAll(Book.class,"ide=?",String.valueOf(ID-3));
                            RechargeEdit.setText(null);//将输入内容清空
                            //提示充值充值成功
                            Snackbar.make(v,"充值成功",Snackbar.LENGTH_LONG).setAction(" ", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            }).show();
                        }else {
                            Toast.makeText(this, "账户金额超过设置的阀值\n可充值最大值："+(m-y), Toast.LENGTH_SHORT).show();
                            QueryText.setText(null);
                            CarNum.setText(null);
                        }
                    }else { //超过阀值
                        Toast.makeText(this, "账户金额超过设置的阀值\n\t\t禁止充值", Toast.LENGTH_SHORT).show();
                        QueryText.setText(null);
                    }
                }
                break;
            case R.id.AccountRecharge_FaZhiBack:
                Intent iii=new Intent(this,RechargeFragmentActivity.class);
                startActivity(iii);
                finish();
                break;
        }
    }
}
