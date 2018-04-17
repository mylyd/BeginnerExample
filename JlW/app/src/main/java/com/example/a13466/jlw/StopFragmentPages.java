package com.example.a13466.jlw;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a13466.jlw.db.Envir;
import com.example.a13466.jlw.db.StopInfo;

import org.litepal.crud.DataSupport;


/**
 * Created by tuxzx on 2018/3/15.
 */

public class StopFragmentPages extends Fragment {

    private static final String  TAG = "Tuxzx";

    private TextView mBus1Stop;
    private TextView mBus2Stop;
    private TextView mPm25Stop;
    private TextView mTemStop;
    private TextView mHumStop;
    private TextView mCo2Stop;
    private TextView mStopHint;


    public static StopFragmentPages newInstance(String stopNum){
        StopFragmentPages fragment = new StopFragmentPages();
        StopInfo s1 = DataSupport.where("bus = ? and stopname = ?",String.valueOf(0),stopNum).findLast(StopInfo.class);
        StopInfo s2 = DataSupport.where("bus = ? and stopname = ?",String.valueOf(1),stopNum).findLast(StopInfo.class);
        Bundle bundle = new Bundle();
        bundle.putInt("stopNum", Integer.parseInt(stopNum));
        bundle.putInt("bus1", s1.getBus());
        bundle.putInt("distance1", s1.getDistance());
        bundle.putInt("bus2", s2.getBus());
        bundle.putInt("distance2", s2.getDistance());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View rootView = inflater.inflate(R.layout.stop_fragment_page, container, false);
        initView(rootView);
        mStopHint.setText("距"+getArguments().getInt("stopNum")+"号站台距离");
        mBus1Stop.setText((getArguments().getInt("bus1")+1)+"号公交车距离站台"+getArguments().getInt("distance1"));
        mBus2Stop.setText((getArguments().getInt("bus2")+1)+"号公交车距离站台"+getArguments().getInt("distance2"));
        query();

        return rootView;
    }

    private void initView(View rootView) {
        mBus1Stop = (TextView) rootView.findViewById(R.id.stop_bus1);
        mBus2Stop = (TextView) rootView.findViewById(R.id.stop_bus2);
        mPm25Stop = (TextView) rootView.findViewById(R.id.stop_pm25);
        mTemStop = (TextView) rootView.findViewById(R.id.stop_tem);
        mHumStop = (TextView) rootView.findViewById(R.id.stop_hum);
        mCo2Stop = (TextView) rootView.findViewById(R.id.stop_co2);
        mStopHint = (TextView) rootView.findViewById(R.id.stop_hint);
    }

    private void query(){
        if (DataSupport.count(Envir.class)>0) {
            Envir envir = DataSupport.where("id =?", String.valueOf(1)).findLast(Envir.class);
            mPm25Stop.setText("PM2.5："+envir.getPm25()+"μg/㎥");
            mTemStop.setText("温度："+envir.getTemperature()+"℃");
            mHumStop.setText("湿度："+envir.getHumidity()+"%");
            mCo2Stop.setText("C02："+envir.getCo2()+"μg/㎥");
        }else {
            query();
        }
    }
}