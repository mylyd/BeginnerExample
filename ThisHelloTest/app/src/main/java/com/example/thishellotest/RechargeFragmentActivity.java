package com.example.thishellotest;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RechargeFragmentActivity extends FragmentActivity implements View.OnClickListener {
    private Button fragmentRechargeBack;
    private Button fragmentRecharge_Fragment_1;
    private Button fragmentRecharge_Fragment_2;
    private TextView fragment_1_carSudu;
    private TextView fragment_1_carZhanghu;

    //Fragment管理器
    private FragmentManager fm=this.getSupportFragmentManager();
    private FragmentTransaction ft;
    private RechargeFragment_1 rechargeFragment_1;
    private RechargeFragment_2 rechargeFragment_2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recharge_fragment);

        intView();
        //开始事务（每次改变Fragment管理器之后都要提交）
        ft=fm.beginTransaction();
        FG2();//设置每次进去最新显示的Fragment
        //提交事务
        ft.commit();



    }

    private void intView(){
        fragmentRechargeBack= (Button) findViewById(R.id.fragmentRecharge_back);
        fragmentRecharge_Fragment_1= (Button) findViewById(R.id.fragmentRecharge_fragment_1);
        fragmentRecharge_Fragment_2= (Button) findViewById(R.id.fragmentRecharge_fragment_2);
        fragment_1_carSudu=(TextView)findViewById(R.id.fragment_1_carSudu);
        fragment_1_carZhanghu=(TextView)findViewById(R.id.fragment_1_carZhanghu);


        fragmentRechargeBack.setOnClickListener(this);
        fragmentRecharge_Fragment_1.setOnClickListener(this);
        fragmentRecharge_Fragment_2.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        //每次点击时都需要重新开始事务
        ft=fm.beginTransaction();
        //把显示的Fragment隐藏
        setSelected();
        switch (v.getId()){
            case R.id.fragmentRecharge_back:
                //返回
                Intent i1=new Intent(this,FirstMainActivity.class);
                startActivity(i1);
                finish();
                break;
            case R.id.fragmentRecharge_fragment_1:
                //Fragment_1
                FG2();//加载另外一个Fragment
                //这里是改变Button的背景色跟字体颜色
                fragmentRecharge_Fragment_1.setBackgroundResource(R.drawable.button_on);
                fragmentRecharge_Fragment_2.setBackgroundResource(R.drawable.button_off);
				//这是新的写法
				//fragmentRecharge_Fragment_1.setTextColor(ContextCompat.getColor(this,R.color.ButtonON));
                fragmentRecharge_Fragment_1.setTextColor(getResources().getColor(R.color.ButtonON));
                fragmentRecharge_Fragment_2.setTextColor(getResources().getColor(R.color.ButtonOFF));
                fragment_1_carSudu.setTextColor(getResources().getColor(R.color.CarON));
                fragment_1_carZhanghu.setTextColor(getResources().getColor(R.color.CarOFF));
                break;
            case R.id.fragmentRecharge_fragment_2:
                //Fragment_2
                FG1();//加载另外一个Fragment
                //这里是改变Button的背景色跟字体颜色
                fragmentRecharge_Fragment_1.setBackgroundResource(R.drawable.button_off);
                fragmentRecharge_Fragment_2.setBackgroundResource(R.drawable.button_on);
                fragmentRecharge_Fragment_1.setTextColor(getResources().getColor(R.color.ButtonOFF));
                fragmentRecharge_Fragment_2.setTextColor(getResources().getColor(R.color.ButtonON));
                fragment_1_carSudu.setTextColor(getResources().getColor(R.color.CarOFF));
                fragment_1_carZhanghu.setTextColor(getResources().getColor(R.color.CarON));
                break;
        }

        ft.commit();
    }
    private void setSelected(){
        //隐藏Fragment
        if (rechargeFragment_1!=null){
            ft.hide(rechargeFragment_1);
        }
        if(rechargeFragment_2!=null){
           ft.hide(rechargeFragment_2) ;
        }
    }

    private void FG1(){
        if (rechargeFragment_1==null){
            rechargeFragment_1=new RechargeFragment_1();
            ft.add(R.id.fragment_recharge,rechargeFragment_1);
             /*添加到Fragment管理器中
            这里如果用replace，
            当每次调用时都会把前一个Fragment给干掉，
            这样就导致了每一次都要创建、销毁，
            数据就很难保存，用add就不存在这样的问题了，
            当Fragment存在时候就让它显示，不存在时就创建，
            这样的话数据就不需要自己保存了，
            因为第一次创建的时候就已经保存了，
            只要不销毁一直都将存在*/
        }else {
            ft.show(rechargeFragment_1);
        }
    }

    private void FG2(){
        if (rechargeFragment_2==null){
            rechargeFragment_2=new RechargeFragment_2();
            ft.add(R.id.fragment_recharge,rechargeFragment_2);
        }else {
            ft.show(rechargeFragment_2);
        }
    }


}
