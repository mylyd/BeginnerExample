package com.example.a13466.nanjingt.HomeFragment.EnvironmentalFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.a13466.nanjingt.Adapter.ViewPagerAdapter;
import com.example.a13466.nanjingt.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EnvironmentalActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Fragment> mList;
    private ImageView[] dots;
    private Button mBtnBack;
    private ViewPager mViewPager;
    private int item_1;//传入过来用于判断是从什么数据点击的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environmental);
        initView();
        Intent intent = getIntent();
        item_1 = intent.getIntExtra("item_View",0);
        setData(item_1);
        initDot(item_1);

    }


    private void initDot(int index) {
        LinearLayout linearLayout = findViewById(R.id.En_dot_view_pager);
        dots =new ImageView[mList.size()];
        for (int i=0;i<mList.size();i++){
            dots[i] = (ImageView) linearLayout.getChildAt(i);
            dots[i].setEnabled(true);
        }
            dots[index].setEnabled(false);
    }


    private void setData(int item){
        initData();
        ViewPagerAdapter FragmentViewPager = new ViewPagerAdapter(getSupportFragmentManager(),mList);
        mViewPager.setAdapter(FragmentViewPager);
        mViewPager.setCurrentItem(item);
    }

    private void initData() {
        //空气温度，对应putExtra("id",0);
        Bundle bundle1 = new Bundle();
        bundle1.putInt("id",40);
        bundle1.putString("Y","温度/℃");
        bundle1.putString("title","空 气 温 度");
        Fragment fragment1 = MPAndroidChartFragment.newInstance(bundle1);

        //空气湿度，对应putExtra("id",1);
        Bundle bundle2 = new Bundle();
        bundle2.putInt("id",100);
        bundle2.putString("Y","湿度/mV");
        bundle2.putString("title","空 气 湿 度");
        Fragment fragment2 = MPAndroidChartFragment.newInstance(bundle2);

        //光照，对应putExtra("id",2);
        Bundle bundle3 = new Bundle();
        bundle3.putInt("id",1000);
        bundle3.putString("Y","光照/Lx");
        bundle3.putString("title","光 照 强 度");
        Fragment fragment3 = MPAndroidChartFragment.newInstance(bundle3);

        //CO2，对应putExtra("id",3);
        Bundle bundle4 = new Bundle();
        bundle4.putInt("id",3000);
        bundle4.putString("Y","ppm/m3");
        bundle4.putString("title","CO2 浓 度");
        Fragment fragment4 = MPAndroidChartFragment.newInstance(bundle4);

        //PM2.5，对应putExtra("id",4);
        Bundle bundle5 = new Bundle();
        bundle5.putInt("id",3000);
        bundle5.putString("Y","μg/m3");
        bundle5.putString("title","PM 2.5");
        Fragment fragment5 = MPAndroidChartFragment.newInstance(bundle5);

        //道路状况，对应putExtra("id",5);
        Bundle bundle6 = new Bundle();
        bundle6.putInt("id",5);
        bundle6.putString("Y","方案/T");
        bundle6.putString("title","道 路 状 况");
        Fragment fragment6 = MPAndroidChartFragment.newInstance(bundle6);

        mList.add(fragment1);
        mList.add(fragment2);
        mList.add(fragment3);
        mList.add(fragment4);
        mList.add(fragment5);
        mList.add(fragment6);
    }

    private void initView() {
        mList = new ArrayList<Fragment>();
        mBtnBack = (Button) findViewById(R.id.En_btn_back);
        mBtnBack.setOnClickListener(this);
        mViewPager = (ViewPager) findViewById(R.id.En_ViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                initDot(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.En_btn_back:
                // TODO 18/03/30
                finish();
                break;
            default:
                break;
        }
    }

}
