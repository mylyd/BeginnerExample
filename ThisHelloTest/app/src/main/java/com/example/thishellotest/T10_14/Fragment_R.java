package com.example.thishellotest.T10_14;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.NonNull;
//import android.support.design.widget.NavigationView;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.thishellotest.FirstMainActivity;
import com.example.thishellotest.R;

public class Fragment_R extends FragmentActivity implements View.OnClickListener{
    private DrawerLayout mDrawerLayout_R;
    private NavigationView NavView;
    private Button button_DrawerLayout_R;
    private TextView RRText;
    private FragmentManager fm = this.getSupportFragmentManager();
    private FragmentTransaction ft;

    private Fragment_R_1 fragment_r_1;
    private Fragment_R_2 fragment_r_2;
    private Fragment_R_3 fragment_r_3;
    private Fragment_R_4 fragment_r_4;
    private Fragment_R_5 fragment_r_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment__r);

        ivvv();
        //开始事务（每次改变Fragment管理器之后都要提交）
        ft = fm.beginTransaction();
        F1();//初始界面
        //提交事务
        ft.commit();

        /**
         * */
        NavView= (NavigationView) findViewById(R.id.R_view);
        NavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //每次点击时都需要重新开始事务
                ft = fm.beginTransaction();
                //把显示的Fragment隐藏
                setSelected();
                switch (item.getItemId()){
                    case R.id.back_ButtonFirst_R:
                        //关闭DrawerLayout界面
                        F1();
                        RRText.setText("道   路   状   况");
                        mDrawerLayout_R.closeDrawers();

                        break;

                    case R.id.back_ButtonLogin_R:
                        //返回到登录页面
                        Intent intentLogin=new Intent(Fragment_R.this,FirstMainActivity.class);
                        startActivity(intentLogin);
                        break;

                    case R.id.R_1:
                        //城市道路状态查询
                            F1();
                        RRText.setText("道   路   状   况");
                        mDrawerLayout_R.closeDrawers();
                        break;

                    case R.id.R_2:
                        //停车记录查询
                            F2();
                        RRText.setText("停   车   记   录");
                        mDrawerLayout_R.closeDrawers();
                        break;

                    case R.id.R_3:
                        //对路灯的管理控制
                            F3();
                        RRText.setText("路   灯   管   理");
                        mDrawerLayout_R.closeDrawers();
                        break;

                    case R.id.R_4:
                        //个人对车辆的管理与控制
                            F4();
                        RRText.setText("车   辆   管   理");
                        mDrawerLayout_R.closeDrawers();
                        break;

                    case R.id.R_5:
                        //根据光照强度打开或关闭车灯
                            F5();
                        RRText.setText("光   照   监   测");
                        mDrawerLayout_R.closeDrawers();
                        break;

                }
                //提交事务
                ft.commit();
                return true;
            }
        });

    }

    private void ivvv(){
            button_DrawerLayout_R= (Button) findViewById(R.id.R_DrawerLayout);
            RRText=(TextView)findViewById(R.id.RR_Text);

            button_DrawerLayout_R.setOnClickListener(this);
            mDrawerLayout_R= (DrawerLayout) findViewById(R.id.drawer_layout_R);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.R_DrawerLayout:
                //打开DrawerLayout界面按钮
                mDrawerLayout_R.openDrawer(GravityCompat.START);
                break;

        }
    }

    private void setSelected() {
        if (fragment_r_1 != null) {
            //隐藏Fragment
            ft.hide(fragment_r_1);
        }
        if (fragment_r_2 != null) {
            ft.hide(fragment_r_2);
        }
        if (fragment_r_3 != null) {
            ft.hide(fragment_r_3);
        }
        if (fragment_r_4 != null) {
            ft.hide(fragment_r_4);
        }
        if (fragment_r_5!=null){
            ft.hide(fragment_r_5);
        }
    }

    private void F1() {
        if (fragment_r_1 == null) {
            fragment_r_1= new Fragment_R_1();
            ft.add(R.id.R_fragment, fragment_r_1);
        } else {
            ft.show(fragment_r_1);
        }
    }
    private void F2() {
        if (fragment_r_2 == null) {
            fragment_r_2= new Fragment_R_2();
            ft.add(R.id.R_fragment, fragment_r_2);
        } else {
            ft.show(fragment_r_2);
        }
    }
    private void F3() {
        if (fragment_r_3 == null) {
            fragment_r_3= new Fragment_R_3();
            ft.add(R.id.R_fragment, fragment_r_3);
        } else {
            ft.show(fragment_r_3);
        }
    }
    private void F4() {
        if (fragment_r_4 == null) {
            fragment_r_4= new Fragment_R_4();
            ft.add(R.id.R_fragment, fragment_r_4);
        } else {
            ft.show(fragment_r_4);
        }
    }
    private void F5() {
        if (fragment_r_5 == null) {
            fragment_r_5= new Fragment_R_5();
            ft.add(R.id.R_fragment, fragment_r_5);
        } else {
            ft.show(fragment_r_5);
        }
    }
}
