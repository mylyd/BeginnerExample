package com.example.thishellotest;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAmbientActivity extends AppCompatActivity  {
    private ViewPager mViewPager;
    List<Fragment> mFragmentList=new ArrayList<>();
    private Button discoundButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_ambient);

        /*隐藏title*/
        ActionBar actionbar=getSupportActionBar();
        if (actionbar!=null){
            actionbar.hide();
        }

        /*返回按钮
        * */
        discoundButton=(Button)findViewById(R.id.discount_button);
        discoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDiscount=new Intent(ViewPagerAmbientActivity.this,AmbientActivty.class);
                startActivity(intentDiscount);
                finish();
            }
        });
        //////
        mViewPager= (ViewPager) findViewById(R.id.ViewPager_Fragment);
        initData();

        MyFragmentViewPagerAmbientActivity adadter=new MyFragmentViewPagerAmbientActivity(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(adadter);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        mViewPager.setCurrentItem(id);
    }


    private void initData(){

        //空气温度，对应putExtra("id",0);
        Bundle bundle1 = new Bundle();
        bundle1.putInt("id",1);
        bundle1.putString("Y","温度/℃");
        bundle1.putString("title","空 气 温 度");
        Fragment fragment1 = FragmentMPAndroidChart.newInstance(bundle1);

        //空气湿度，对应putExtra("id",1);
        Bundle bundle2 = new Bundle();
        bundle2.putInt("id",2);
        bundle2.putString("Y","湿度/mV");
        bundle2.putString("title","空 气 湿 度");
        Fragment fragment2 = FragmentMPAndroidChart.newInstance(bundle2);

        //光照，对应putExtra("id",2);
        Bundle bundle3 = new Bundle();
        bundle3.putInt("id",3);
        bundle3.putString("Y","光照/Lx");
        bundle3.putString("title","光 照 强 度");
        Fragment fragment3 = FragmentMPAndroidChart.newInstance(bundle3);

        //CO2，对应putExtra("id",3);
        Bundle bundle4 = new Bundle();
        bundle4.putInt("id",4);
        bundle4.putString("Y","ppm/m3");
        bundle4.putString("title","CO2 浓 度");
        Fragment fragment4 = FragmentMPAndroidChart.newInstance(bundle4);

        //PM2.5，对应putExtra("id",4);
        Bundle bundle5 = new Bundle();
        bundle5.putInt("id",5);
        bundle5.putString("Y","μg/m3");
        bundle5.putString("title","PM 2.5");
        Fragment fragment5 = FragmentMPAndroidChart.newInstance(bundle5);

        //道路状况，对应putExtra("id",5);
        Bundle bundle6 = new Bundle();
        bundle6.putInt("id",6);
        bundle6.putString("Y","方案/T");
        bundle6.putString("title","道 路 状 况");
        Fragment fragment6 = FragmentMPAndroidChart.newInstance(bundle6);

        mFragmentList.add(fragment1);
        mFragmentList.add(fragment2);
        mFragmentList.add(fragment3);
        mFragmentList.add(fragment4);
        mFragmentList.add(fragment5);
        mFragmentList.add(fragment6);

    }

    /*
    *
    * */
    class MyFragmentViewPagerAmbientActivity extends FragmentStatePagerAdapter{

        List<Fragment> mFragmentList;

        public MyFragmentViewPagerAmbientActivity(FragmentManager fm,List<Fragment> list) {
            super(fm);
            mFragmentList=list;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }
}
