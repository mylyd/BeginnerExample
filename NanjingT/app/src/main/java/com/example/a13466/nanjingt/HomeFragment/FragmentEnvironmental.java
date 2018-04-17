package com.example.a13466.nanjingt.HomeFragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.a13466.nanjingt.HomeFragment.EnvironmentalFragment.EnvironmentalActivity;
import com.example.a13466.nanjingt.R;
import com.example.a13466.nanjingt.bean.MyApplication;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 环境指标
 */
public class FragmentEnvironmental extends Fragment implements View.OnClickListener {

    private TextView mTvTem;
    private RelativeLayout mTem;
    private TextView mTvHum;
    private RelativeLayout mHum;
    private TextView mTvLight;
    private RelativeLayout mLig;
    private TextView mTvCO2;
    private RelativeLayout mCO2;
    private TextView mTvPM25;
    private RelativeLayout mPM25;
    private TextView mTvRoad;
    private RelativeLayout mRoad;
    private SharedPreferences pref = MyApplication.getContext().getSharedPreferences("text", Context.MODE_PRIVATE);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_environmental, container, false);
        initView(view);
        timer.schedule(timerTask,3000,3000);
        return view;
    }

    Handler handler = new Handler();
    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    setrandom();
                }
            });
        }
    };
    private void setrandom(){
        Random random = new Random();
        int Tem = random.nextInt(30)+1;
        int Hum = random.nextInt(35)+1;
        int lig = random.nextInt(1500)+1;
        int CO2 = random.nextInt(3000)+1;
        int PM25 = random.nextInt(3000)+1;
        int road = random.nextInt(4)+1;
        if (Tem>pref.getInt("tv_1",15)){mTem.setBackgroundResource(R.drawable.red);}else {mTem.setBackgroundResource(R.drawable.green);}
        if (Hum>pref.getInt("tv_1",50)){mHum.setBackgroundResource(R.drawable.red);}else {mHum.setBackgroundResource(R.drawable.green);}
        if (lig>pref.getInt("tv_1",800)){mLig.setBackgroundResource(R.drawable.red);}else {mLig.setBackgroundResource(R.drawable.green);}
        if (CO2>pref.getInt("tv_1",1800)){mCO2.setBackgroundResource(R.drawable.red);}else {mCO2.setBackgroundResource(R.drawable.green);}
        if (PM25>pref.getInt("tv_1",2000)){mPM25.setBackgroundResource(R.drawable.red);}else {mPM25.setBackgroundResource(R.drawable.green);}
        if (road>pref.getInt("tv_1",2)){mRoad.setBackgroundResource(R.drawable.red);}else {mRoad.setBackgroundResource(R.drawable.green);}
        mTvTem.setText(Tem+"");
        mTvHum.setText(Hum+"");
        mTvLight.setText(lig+"");
        mTvCO2.setText(CO2+"");
        mTvPM25.setText(PM25+"");
        mTvRoad.setText(road+"");
    }
    private void initView(View item) {
        mTvTem = (TextView) item.findViewById(R.id.Text_temperature);
        mTem = (RelativeLayout) item.findViewById(R.id.En_temperature);
        mTem.setOnClickListener(this);
        mTvHum = (TextView) item.findViewById(R.id.Text_humidity);
        mHum = (RelativeLayout) item.findViewById(R.id.En_humidity);
        mHum.setOnClickListener(this);
        mTvLight = (TextView) item.findViewById(R.id.Text_Light);
        mLig = (RelativeLayout) item.findViewById(R.id.En_beam);
        mLig.setOnClickListener(this);
        mTvCO2 = (TextView) item.findViewById(R.id.Text_CO2);
        mCO2 = (RelativeLayout) item.findViewById(R.id.En_CO2);
        mCO2.setOnClickListener(this);
        mTvPM25 = (TextView) item.findViewById(R.id.Text_pm25);
        mPM25 = (RelativeLayout) item.findViewById(R.id.En_PM25);
        mPM25.setOnClickListener(this);
        mTvRoad = (TextView) item.findViewById(R.id.Text_road);
        mRoad = (RelativeLayout) item.findViewById(R.id.En_road);
        mRoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.En_temperature:
                // TODO 18/03/30
                Intent intent_1 = new Intent(getActivity(), EnvironmentalActivity.class);
                intent_1.putExtra("item_View",0);
                startActivity(intent_1);
                break;
            case R.id.En_humidity:
                // TODO 18/03/30
                Intent intent_2 = new Intent(getActivity(), EnvironmentalActivity.class);
                intent_2.putExtra("item_View",1);
                startActivity(intent_2);
                break;
            case R.id.En_beam:
                // TODO 18/03/30
                Intent intent_3 = new Intent(getActivity(), EnvironmentalActivity.class);
                intent_3.putExtra("item_View",2);
                startActivity(intent_3);
                break;
            case R.id.En_CO2:
                // TODO 18/03/30
                Intent intent_4 = new Intent(getActivity(), EnvironmentalActivity.class);
                intent_4.putExtra("item_View",3);
                startActivity(intent_4);
                break;
            case R.id.En_PM25:
                // TODO 18/03/30
                Intent intent_5 = new Intent(getActivity(), EnvironmentalActivity.class);
                intent_5.putExtra("item_View",4);
                startActivity(intent_5);
                break;
            case R.id.En_road:
                // TODO 18/03/30
                Intent intent_6 = new Intent(getActivity(), EnvironmentalActivity.class);
                intent_6.putExtra("item_View",5);
                startActivity(intent_6);
                break;
            default:
                break;
        }
    }
}
