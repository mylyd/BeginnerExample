package com.example.a13466.jlw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a13466.jlw.Fragment.MyCarFragmentAccount;
import com.example.a13466.jlw.Fragment.MyCarFragmentControl;
import com.example.a13466.jlw.Fragment.MyCarFragmentState;

public class MyCarFragmentActivity extends FragmentActivity implements View.OnClickListener {
    private Button mFragmentButtonBackMyCar;
    private Button mFragmentBtnControlMyCar;
    private Button mFragmentBtnStateMyCar;
    private Button mFragmentBtnAccountMyCar;
    private TextView mFragmentTitleTextViewMyCar;

    // TODO Fragment管理器
    private FragmentManager fm = this.getSupportFragmentManager();
    private FragmentTransaction ft;
    //
    private MyCarFragmentControl myCarFragmentControl;
    private MyCarFragmentState myCarFragmentState;
    private MyCarFragmentAccount myCarFragmentAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycar_fragment);
        initView();
        //开启事务
        ft = fm.beginTransaction();
        onControl();//最先显示的Fragment
        ft.commit();//提交
    }

    private void initView() {
        mFragmentButtonBackMyCar = (Button) findViewById(R.id.MyCar_Fragment_button_back);
        mFragmentButtonBackMyCar.setOnClickListener(this);
        mFragmentBtnControlMyCar = (Button) findViewById(R.id.MyCar_Fragment_btn_control);
        mFragmentBtnControlMyCar.setOnClickListener(this);
        mFragmentBtnStateMyCar = (Button) findViewById(R.id.MyCar_Fragment_btn_state);
        mFragmentBtnStateMyCar.setOnClickListener(this);
        mFragmentBtnAccountMyCar = (Button) findViewById(R.id.MyCar_Fragment_btn_account);
        mFragmentBtnAccountMyCar.setOnClickListener(this);
        mFragmentTitleTextViewMyCar = (TextView) findViewById(R.id.MyCar_Fragment_TitleTextView);
    }

    @Override
    public void onClick(View v) {
        ft = fm.beginTransaction();//提交事务
        setGone();
        switch (v.getId()) {
            case R.id.MyCar_Fragment_button_back:
                // TODO 18/03/14 fragment_back 返回
                onBackPressed();
                break;
            case R.id.MyCar_Fragment_btn_control:
                // TODO 18/03/14 fragment_control 控制
                onControl();
                break;
            case R.id.MyCar_Fragment_btn_state:
                // TODO 18/03/14 fragment_state 状态
                onState();
                break;
            case R.id.MyCar_Fragment_btn_account:
                // TODO 18/03/14 fragment_account 账户
                onAccount();
                break;
            default:
                break;
        }
        ft.commit();
    }

    /**
     * 将多余的不用显示的Fragment隐藏
     */
    private void setGone() {
        if (myCarFragmentControl != null) {
            ft.hide(myCarFragmentControl);
        }
        if (myCarFragmentState != null) {
            ft.hide(myCarFragmentState);
        }
        if (myCarFragmentAccount != null) {
            ft.hide(myCarFragmentAccount);
        }
    }

    private void onControl() {
        if (myCarFragmentControl == null) {
            myCarFragmentControl = new MyCarFragmentControl();
            ft.add(R.id.MyCar_Fragment_Layout, myCarFragmentControl);
        } else {
            ft.show(myCarFragmentControl);
        }
        //Style
        mFragmentTitleTextViewMyCar.setText("(控制)");
        onStyleButtonBG();
        mFragmentBtnControlMyCar.setBackgroundResource(R.drawable.fragment_style_btn_press);
    }

    private void onState() {
        if (myCarFragmentState == null) {
            myCarFragmentState = new MyCarFragmentState();
            ft.add(R.id.MyCar_Fragment_Layout, myCarFragmentState);
        } else {
            ft.show(myCarFragmentState);
        }
        //Style
        mFragmentTitleTextViewMyCar.setText("(状态)");
        onStyleButtonBG();
        mFragmentBtnStateMyCar.setBackgroundResource(R.drawable.fragment_style_btn_press);
    }

    private void onAccount() {
        if (myCarFragmentAccount == null) {
            myCarFragmentAccount = new MyCarFragmentAccount();
            ft.add(R.id.MyCar_Fragment_Layout, myCarFragmentAccount);
        } else {
            ft.show(myCarFragmentAccount);
        }
        //Style
        mFragmentTitleTextViewMyCar.setText("(账户)");
        onStyleButtonBG();
        mFragmentBtnAccountMyCar.setBackgroundResource(R.drawable.fragment_style_btn_press);
    }

    private void onStyleButtonBG() {
        mFragmentBtnControlMyCar.setBackgroundResource(R.drawable.fragment_style_btn_normal);
        mFragmentBtnStateMyCar.setBackgroundResource(R.drawable.fragment_style_btn_normal);
        mFragmentBtnAccountMyCar.setBackgroundResource(R.drawable.fragment_style_btn_normal);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.push_in_right,R.anim.push_out_right);
    }
}
