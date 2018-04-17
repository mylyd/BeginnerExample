package com.example.a13466.jl.ViewPagerAdapter;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a13466.jl.R;
import com.example.a13466.jl.db.BookEnvironmental;
import com.example.a13466.jl.db.BookVariable;

import org.litepal.crud.DataSupport;

import java.util.Timer;
import java.util.TimerTask;

public class FragmentPager_2 extends Fragment {

    private TextView mText2;
    private TextView mCar1;
    private TextView mCar2;
    private Handler handler;
    private TextView mPm25;
    private TextView mTem;
    private TextView mHum;
    private TextView mCO2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_pager_2, container, false);
        initView(view);
        setUI();
        timer.schedule(ttt,5000,5000);
        return view;
    }

    Timer timer = new Timer();
    TimerTask ttt = new TimerTask() {
        @Override
        public void run() {
            setUItimer();
        }
    };
    private void setUItimer(){
        int cunn = DataSupport.count(BookVariable.class);
        if (cunn > 0){
            final BookVariable bv=DataSupport.findLast(BookVariable.class);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mCO2.setText(bv.getCO2()+"");
                    mPm25.setText(bv.getPM25()+"");
                    mHum.setText(bv.getHum()+"");
                    mTem.setText(bv.getTem()+"");
                }
            });
        } else {
            Toast.makeText(getActivity(), "环境变量加载失败...2", Toast.LENGTH_SHORT).show();
        }
    }
    private void setUI() {
        int count = DataSupport.count(BookEnvironmental.class);
        BookEnvironmental bk = DataSupport.findLast(BookEnvironmental.class);
        if (count > 0) {
            mCar1.setText(bk.getDistance_car_21() + "m");
            mCar2.setText(bk.getDistance_car_22() + "m");
        } else {
            Toast.makeText(getActivity(), "加载失败...", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView(final View itemView) {
        mText2 = (TextView) itemView.findViewById(R.id.fragment_text_2);
        mText2.setText("2");
        mCar1 = (TextView) itemView.findViewById(R.id.fragment2_Car_1);
        mCar2 = (TextView) itemView.findViewById(R.id.fragment2_Car_2);
        handler=new Handler();
        mPm25 = (TextView) itemView.findViewById(R.id.fragment2_pm25);
        mTem = (TextView) itemView.findViewById(R.id.fragment2_temperature);
        mHum = (TextView) itemView.findViewById(R.id.fragment2_humidity);
        mCO2 = (TextView) itemView.findViewById(R.id.fragment2_co2);
    }
}
