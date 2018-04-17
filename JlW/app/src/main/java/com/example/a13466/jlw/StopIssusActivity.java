package com.example.a13466.jlw;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a13466.jlw.util.HttpPostThread;
import com.example.a13466.jlw.util.MyJsonUtil;

import org.w3c.dom.Text;


public class StopIssusActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    public static final String TAG = "***Tuxzx";

    private ViewPager mViewpager;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private Button mBack;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_issus);
        initView();
        //request();
    }

    private void initView() {

        mViewpager = (ViewPager) findViewById(R.id.stop_viewpager);
//        FragmentManager fm = getSupportFragmentManager();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(mSectionsPagerAdapter);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mViewpager.addOnPageChangeListener(this);
        mTitle.setText("第"+(mViewpager.getCurrentItem()+1)+"站台");
        mBack = (Button) findViewById(R.id.btn_back);
        mBack.setOnClickListener(this);
    }

    public void request() {
        String url = "http://10.31.231.85:8080/transportservice/type/jason/action/GetBusStationInfo.do";
        HttpPostThread thread1 = new HttpPostThread(url,"{\"BusStationId\":" + 1 + "}");
        thread1.setmPostCallback(new HttpPostThread.PostCallback() {
            @Override
            public void handleData(String response) {
                Log.d(TAG, "handleData1: "+response);
                MyJsonUtil.handleStopJson(response,1);
            }
        });
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HttpPostThread thread2 = new HttpPostThread(url,"{\"BusStationId\":" + 2 + "}");
        thread2.setmPostCallback(new HttpPostThread.PostCallback() {
            @Override
            public void handleData(String response) {
                Log.d(TAG, "handleData2: "+response);
                MyJsonUtil.handleStopJson(response,2);
            }
        });
        thread2.start();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HttpPostThread thread3 = new HttpPostThread("http://10.31.231.85:8080/transportservice/type/jason/action/%20GetAllSense.do",
                "null");
        thread3.setmPostCallback(new HttpPostThread.PostCallback() {
            @Override
            public void handleData(String response) {
                Log.d(TAG, "handleData3: "+response);
                MyJsonUtil.handleEnvirJson(response);
            }
        });
        thread3.start();

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mTitle.setText("第"+(position+1)+"站台");
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            default:
                break;
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
//            StopFragmentPages.newInstance("1");
//            Fragment[] fragment = new Fragment[]{StopFragmentPages.newInstance("1"), StopFragmentPages.newInstance("1");};
            return StopFragmentPages.newInstance(String.valueOf(position+1));
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
