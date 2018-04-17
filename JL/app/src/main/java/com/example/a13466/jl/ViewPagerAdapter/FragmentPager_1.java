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

public class FragmentPager_1 extends Fragment {

    private TextView mText1;
    private TextView mCar1;
    private TextView mCar2;
    private Handler handler;
    private TextView mPm25;
    private TextView mTem;
    private TextView mHum;
    private TextView mCO2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_pager_1, container, false);
        initView(view);
        setUI();
        timerFragment_1.schedule(timerTask,5000,5000);
        return view;
    }

    Timer timerFragment_1 = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            setUITime();
        }
    };

    private void setUITime() {
        int cun = DataSupport.count(BookVariable.class);
        if (cun > 0) {
            final BookVariable bv = DataSupport.findLast(BookVariable.class);
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
            Toast.makeText(getActivity(), "环境变量加载失败...1", Toast.LENGTH_SHORT).show();
        }
    }

    private void setUI() {
        int count = DataSupport.count(BookEnvironmental.class);
        BookEnvironmental bk = DataSupport.findLast(BookEnvironmental.class);
        if (count > 0) {
            mCar1.setText(bk.getDistance_car_11() + "m");
            mCar2.setText(bk.getDistance_car_12() + "m");
        } else {
            Toast.makeText(getActivity(), "站台距离加载失败...", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView(final View itemView) {
        mText1 = (TextView) itemView.findViewById(R.id.fragment_text_1);
        mText1.setText("1");
        mCar1 = (TextView) itemView.findViewById(R.id.fragment1_Car_1);
        mCar2 = (TextView) itemView.findViewById(R.id.fragment1_Car_2);
        handler = new Handler();
        mPm25 = (TextView) itemView.findViewById(R.id.fragment1_pm25);
        mTem = (TextView) itemView.findViewById(R.id.fragment1_temperature);
        mHum = (TextView) itemView.findViewById(R.id.fragment1_humidity);
        mCO2 = (TextView) itemView.findViewById(R.id.fragment1_co2);
    }
}
