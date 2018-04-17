package com.example.a13466.mytime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a13466.mytime.Fragment.Fragment1;
import com.example.a13466.mytime.Fragment.Fragment2;
import com.example.a13466.mytime.Fragment.Fragment3;
import com.example.a13466.mytime.Fragment.Fragment4;
import com.example.a13466.mytime.Fragment.Fragment5;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerActivity extends FragmentActivity implements View.OnClickListener {
    private List<Fragment> mList;
    private ViewPager vp;
    private TextView mText1;
    private TextView mText2;
    private TextView mText3;
    private TextView mText4;
    private TextView mText5;
    private TextView mView5;
    private TextView mView4;
    private TextView mView3;
    private TextView mView2;
    private TextView mView1;
    //
    private ImageView[] dots;
    int Index;
    //

    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        initView();
        setViewPager();
        initImageData();
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            // 当滑动状态改变时调用
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            // 当当前页面被滑动时调用
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            // 当新的页面被选中时调用
            @Override
            public void onPageSelected(int arg0) {
                // 设置底部小点选中状态
                setCurrentDot(arg0);
            }


        });
    }

    private void initImageData() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.view_dot);
        dots = new ImageView[mList.size()];
        for (int i=0;i<mList.size();i++){   //将所有小圆点设置为没有选中状态
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setEnabled(false);
        }
        Index = 0;
        dots[Index].setEnabled(true);// 设置为白色，即选中状态
    }
    //滑动时小点变换颜色
    private void setCurrentDot(int position) {
        if (position<0||position>mList.size()-1||position==Index){
            return;
        }
        dots[position].setEnabled(true);// 设置为灰色，将选中状态的显示的前一个重新显示为灰色
        dots[Index].setEnabled(false);// 设置为白色，即选中状态
        Index = position;
    }

    private void setViewPager() {
        mList = new ArrayList<Fragment>();
        mList.add(new Fragment1());
        mList.add(new Fragment2());
        mList.add(new Fragment3());
        mList.add(new Fragment4());
        mList.add(new Fragment5());
        ViewPagerAdapter ad = new ViewPagerAdapter(getSupportFragmentManager(), mList);
        vp.setAdapter(ad);
        setTextColor(mText1, 0,mView1);//设置当前页是第一页

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                index = position;
                if (index == 0) {
                    setTextColor(mText1, 0,mView1);
                }
                if (index == 1) {
                    setTextColor(mText2, 1,mView2);
                }
                if (index == 2) {
                    setTextColor(mText3, 2,mView3);
                }
                if (index == 3) {
                    setTextColor(mText4, 3,mView4);
                }
                if (index == 4) {
                    setTextColor(mText5, 4,mView5);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        mText1 = (TextView) findViewById(R.id.fragment_text_1);
        mText1.setOnClickListener(this);
        mText2 = (TextView) findViewById(R.id.fragment_text_2);
        mText2.setOnClickListener(this);
        mText3 = (TextView) findViewById(R.id.fragment_text_3);
        mText3.setOnClickListener(this);
        mText4 = (TextView) findViewById(R.id.fragment_text_4);
        mText4.setOnClickListener(this);
        mText5 = (TextView) findViewById(R.id.fragment_text_5);
        mText5.setOnClickListener(this);
        mView1 = (TextView) findViewById(R.id.view_1);
        mView2 = (TextView) findViewById(R.id.view_2);
        mView3 = (TextView) findViewById(R.id.view_3);
        mView4 = (TextView) findViewById(R.id.view_4);
        mView5 = (TextView) findViewById(R.id.view_5);

        vp = (ViewPager) findViewById(R.id.view_pager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_text_1:
                // TODO 18/03/23
                setTextColor(mText1, 0,mView1);
                break;
            case R.id.fragment_text_2:
                // TODO 18/03/23
                setTextColor(mText2, 1,mView2);
                break;
            case R.id.fragment_text_3:
                // TODO 18/03/23
                setTextColor(mText3, 2,mView3);
                break;
            case R.id.fragment_text_4:
                // TODO 18/03/23
                setTextColor(mText4, 3,mView4);
                break;
            case R.id.fragment_text_5:
                // TODO 18/03/23
                setTextColor(mText5, 4,mView5);
                break;
            default:
                break;
        }
    }

    private void setTextColor(TextView textView, int item,TextView v) {
        mText1.setTextColor(ContextCompat.getColor(this, R.color.PagerTitleText_off));
        mText2.setTextColor(ContextCompat.getColor(this, R.color.PagerTitleText_off));
        mText3.setTextColor(ContextCompat.getColor(this, R.color.PagerTitleText_off));
        mText4.setTextColor(ContextCompat.getColor(this, R.color.PagerTitleText_off));
        mText5.setTextColor(ContextCompat.getColor(this, R.color.PagerTitleText_off));
        mView1.setBackgroundResource(R.color.PagerTitleView_off);
        mView2.setBackgroundResource(R.color.PagerTitleView_off);
        mView3.setBackgroundResource(R.color.PagerTitleView_off);
        mView4.setBackgroundResource(R.color.PagerTitleView_off);
        mView5.setBackgroundResource(R.color.PagerTitleView_off);
        textView.setTextColor(ContextCompat.getColor(this, R.color.PagerTitleText_on));
        v.setBackgroundResource(R.color.PagerTitleView_on);
        vp.setCurrentItem(item);
    }


}
