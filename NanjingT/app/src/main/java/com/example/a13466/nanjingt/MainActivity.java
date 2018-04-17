package com.example.a13466.nanjingt;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a13466.nanjingt.HomeFragment.FragmentBill;
import com.example.a13466.nanjingt.HomeFragment.FragmentCreativity;
import com.example.a13466.nanjingt.HomeFragment.FragmentEnvironmental;
import com.example.a13466.nanjingt.HomeFragment.FragmentMyAccount;
import com.example.a13466.nanjingt.HomeFragment.FragmentThreshold;
import com.example.a13466.nanjingt.HomeFragment.FragmentTrafficLight;
import com.example.a13466.nanjingt.HomeFragment.FragmentTravel;
import com.example.a13466.nanjingt.HomeFragment.FragmentViolation;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private TextView mTvToolbar;
    private Button mBtnMyAccount;
    private Button mBtnTrafficLight;
    private Button mBtnBill;
    private Button mBtnViolation;
    private Button mBtnEnvironmental;
    private Button mBtnThreshold;
    private Button mBtnTravel;
    private Button mBtnCreativity;
    private Button mBtnSetting;
    private Button mBtnSidebar;
    private long firstBack = 0;//记录当前时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mDrawerLayout.setScrimColor(Color.argb(1, 0, 0, 0));//抽屉拉出后主页面不遮挡（透明）
        //加载第一个Fragment
        replaceFragment(new FragmentMyAccount());
    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mTvToolbar = (TextView) findViewById(R.id.toolbar_tv);
        mBtnMyAccount = (Button) findViewById(R.id.drawer_btn_MyAccount);
        mBtnMyAccount.setOnClickListener(this);
        mBtnTrafficLight = (Button) findViewById(R.id.drawer_btn_TrafficLight);
        mBtnTrafficLight.setOnClickListener(this);
        mBtnBill = (Button) findViewById(R.id.drawer_btn_Bill);
        mBtnBill.setOnClickListener(this);
        mBtnViolation = (Button) findViewById(R.id.drawer_btn_Violation);
        mBtnViolation.setOnClickListener(this);
        mBtnEnvironmental = (Button) findViewById(R.id.drawer_btn_Environmental);
        mBtnEnvironmental.setOnClickListener(this);
        mBtnThreshold = (Button) findViewById(R.id.drawer_btn_Threshold);
        mBtnThreshold.setOnClickListener(this);
        mBtnTravel = (Button) findViewById(R.id.drawer_btn_Travel);
        mBtnTravel.setOnClickListener(this);
        mBtnCreativity = (Button) findViewById(R.id.drawer_btn_Creativity);
        mBtnCreativity.setOnClickListener(this);
        mBtnSetting = (Button) findViewById(R.id.drawer_btn_Setting);
        mBtnSetting.setOnClickListener(this);
        mBtnSidebar = (Button) findViewById(R.id.btn_sidebar);

        mBtnSidebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

    @Override
    public void onClick(View v) {
        mDrawerLayout.closeDrawer(Gravity.LEFT);
        switch (v.getId()) {
            case R.id.drawer_btn_MyAccount:
                // TODO 18/03/28 我的账户
                replaceFragment(new FragmentMyAccount());
                mTvToolbar.setText("我 的 账 户");
                break;
            case R.id.drawer_btn_TrafficLight:
                // TODO 18/03/28 红路灯管理
                replaceFragment(new FragmentTrafficLight());
                mTvToolbar.setText("红 绿 灯 管 理");
                break;
            case R.id.drawer_btn_Bill:
                // TODO 18/03/28 账单管理
                replaceFragment(new FragmentBill());
                mTvToolbar.setText("账 单 管 理");
                break;
            case R.id.drawer_btn_Violation:
                // TODO 18/03/28 违章查询
               replaceFragment(new FragmentViolation());
                mTvToolbar.setText("车 辆 违 章");
                break;
            case R.id.drawer_btn_Environmental:
                // TODO 18/03/28 环境指标
               replaceFragment(new FragmentEnvironmental());
                mTvToolbar.setText("环 境 指 标");
                break;
            case R.id.drawer_btn_Threshold:
                // TODO 18/03/28 阀值设置
               replaceFragment(new FragmentThreshold());
                mTvToolbar.setText("阀 值 设 置");
                break;
            case R.id.drawer_btn_Travel:
                // TODO 18/03/28 出行管理
               replaceFragment(new FragmentTravel());
                mTvToolbar.setText("出 行 管 理");
                break;
            case R.id.drawer_btn_Creativity:
                // TODO 18/03/28 创意
                replaceFragment(new FragmentCreativity());
                mTvToolbar.setText("创 意");
                break;
            case R.id.drawer_btn_Setting:
                // TODO 18/03/28 设置-销毁所有活动
                onBackPressed();
                break;

            default:
                break;
        }
    }
    private void replaceFragment(Fragment fragment){
        //绑定Fragment事件
       FragmentManager fm = this.getSupportFragmentManager();
       FragmentTransaction ft = fm.beginTransaction();
       ft.replace(R.id.big_fragment,fragment);
       ft.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - firstBack) < 2000) {
            finish();
        } else {
            Toast.makeText(this, "快速点击两次退出", Toast.LENGTH_SHORT).show();
            firstBack = System.currentTimeMillis();
        }
    }
}
