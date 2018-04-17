package com.example.a13466.nanjingt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.a13466.nanjingt.Adapter.ViewPagerAdapter;
import com.example.a13466.nanjingt.FragmentViewPager.GuideFragment1;
import com.example.a13466.nanjingt.FragmentViewPager.GuideFragment2;
import com.example.a13466.nanjingt.FragmentViewPager.GuideFragment3;
import com.example.a13466.nanjingt.bean.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页+底部滑动小点
 */
public class GuideActivity extends FragmentActivity{
    private List<Fragment> mList;
    private ViewPager mPagerView;
    private ImageView[] dots;//初始化小点数组
    private int index;//当前页面
    private LinearLayout mPagerDotView;
    private Button mBtnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断是不是第一次进入
        SharedPreferences spf = getSharedPreferences("StartFirst", Context.MODE_PRIVATE);
        if (!spf.getBoolean("first", true)) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        setContentView(R.layout.activity_guide);
        initView();

        initStartViewPager();
        initStartDot();//初始化小点图片

        mPagerView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override// 当前页面被滑动时调用
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override// 当新的页面被选中时调用
            public void onPageSelected(int position) {
                setCurrentDot(position);
            }

            @Override// 当滑动状态改变时调用
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initStartViewPager() {
        mList = new ArrayList<>();
        mList.add(new GuideFragment1());
        mList.add(new GuideFragment2());
        mList.add(new GuideFragment3());
        ViewPagerAdapter guideAdapter = new ViewPagerAdapter(getSupportFragmentManager(),mList);
        mPagerView.setAdapter(guideAdapter);
        mPagerView.setCurrentItem(0);//起始页面为第一页
    }


    private void initView() {
        mPagerView = (ViewPager) findViewById(R.id.view_pager);

        mPagerDotView = (LinearLayout) findViewById(R.id.view_pager_dot);

    }

    //初始化小点
    private void initStartDot() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.view_pager_dot);
        dots = new ImageView[mList.size()];
        //将所有小点设置成未选中的颜色
        for (int i = 0; i < mList.size(); i++) {
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setEnabled(false);
        }
        index = 0;
        dots[index].setEnabled(true);

    }

    //滑动时小点变换颜色
    public void setCurrentDot(int position) {
        if (position < 0 || position > mList.size() - 1 || index == position) {
            return;
        }
        dots[position].setEnabled(true);
        dots[index].setEnabled(false);
        index = position;
    }


    /*class GuideAdapterViewPager extends PagerAdapter{
        public List<View> mViewList;
        public GuideAdapterViewPager(List<View> mList) {
            this.mViewList = mList;
            Log.d("****************", "GuideAdapterViewPager: ");
        }

        @Override
        public int getCount() {
            Log.d("****************", "getCount: ");
            return mViewList !=null ? mViewList.size():0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            Log.d("****************", "isViewFromObject: ");
            return view == object;
        }
    }*/
}
