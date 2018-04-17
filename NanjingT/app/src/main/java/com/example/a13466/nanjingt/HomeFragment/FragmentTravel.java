package com.example.a13466.nanjingt.HomeFragment;

import android.support.v7.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a13466.nanjingt.R;
import com.example.a13466.nanjingt.bean.MyApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 出行管理
 */
public class FragmentTravel extends Fragment implements View.OnClickListener {
    private TextView mTvTime;
    private TextView mTvNumber;
    private TextView mTv3;
    private Switch mSwitch3;
    private TextView mTv2;
    private Switch mSwitch2;
    private TextView mTv1;
    private Switch mSwitch1;
    private SharedPreferences prefe;
    private SharedPreferences.Editor editor;
    private AlertDialog dialog;
    private AlertDialog.Builder builder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_travel, container, false);
        initView(view);
        setData();
        mSwitch();
        return view;
    }

    private void initView(View itemView) {
        mTvTime = (TextView) itemView.findViewById(R.id.Tra_Tv_time);
        mTvNumber = (TextView) itemView.findViewById(R.id.Tra_Tv_number);
        mTv3 = (TextView) itemView.findViewById(R.id.tv_3);
        mSwitch3 = (Switch) itemView.findViewById(R.id.switch3);
        mTv2 = (TextView) itemView.findViewById(R.id.tv_2);
        mSwitch2 = (Switch) itemView.findViewById(R.id.switch2);
        mTv1 = (TextView) itemView.findViewById(R.id.tv_1);
        mSwitch1 = (Switch) itemView.findViewById(R.id.switch1);
        prefe = getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
        editor = prefe.edit();

        mTvTime.setOnClickListener(this);
    }

    private void setData() {
        SimpleDateFormat year_win = new SimpleDateFormat("yyyy");
        SimpleDateFormat month_win = new SimpleDateFormat("M");
        SimpleDateFormat day_win = new SimpleDateFormat("d");
        Date date = new Date(System.currentTimeMillis());
        String timer_year = year_win.format(date);
        String timer_month = month_win.format(date);
        int ti = Integer.parseInt(day_win.format(date));
        int TT = prefe.getInt("day", ti);
            if (TT % 2 == 0) {//双
                mTvTime.setText(prefe.getInt("year",Integer.parseInt(timer_year))+"年"+prefe.getInt("month",Integer.parseInt(timer_month))+"月"+TT+"日");
                mTvNumber.setText("单号出行车辆：2");
                mSwitch1.setEnabled(false);
                mSwitch3.setEnabled(false);
                mSwitch2.setEnabled(true);
                mSwitch2.setChecked(true);
                mSwitch1.setChecked(false);
                mSwitch3.setChecked(false);
                mTv1.setText("关");
                mTv2.setText("开");
                mTv3.setText("关");
            } else { //单
                mTvTime.setText(prefe.getInt("year",Integer.parseInt(timer_year))+"年"+prefe.getInt("month",Integer.parseInt(timer_month))+"月"+TT+"日");
                mTvNumber.setText("单号出行车辆：1、3");
                mTv1.setText("开");
                mTv2.setText("关");
                mTv3.setText("开");
                mSwitch1.setChecked(true);
                mSwitch3.setChecked(true);
                mSwitch2.setChecked(false);//打开按钮
                mSwitch2.setEnabled(false);//设置能否点击(灰度)
                mSwitch1.setEnabled(true);
                mSwitch3.setEnabled(true);
            }
    }
     private void showDialog() {
        builder = new AlertDialog.Builder(this.getContext());
        View view = LayoutInflater.from(this.getContext()).inflate(R.layout.dialog_data, null);
        builder.setView(view).create();
        final DatePicker datePicker = view.findViewById(R.id.data_data_picker);
        Button btn_ok = view.findViewById(R.id.dialog_btn_ok);
        Button btn_no = view.findViewById(R.id.dialog_btn_no);
        dialog = builder.show();
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Y = datePicker.getYear();
                int M = datePicker.getDayOfMonth()+1;
                int D = datePicker.getDayOfMonth();
                editor.putInt("year", Y);
                editor.putInt("month", M);
                editor.putInt("day", D);
                editor.apply();
                dialog.dismiss();
                Toast.makeText(MyApplication.getContext(), "设置成功", Toast.LENGTH_SHORT).show();
                setData();
                dialog.dismiss();
            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Tra_Tv_time:
                // TODO 18/04/03
               showDialog();
                break;
            default:
                break;
        }
    }

    private  void mSwitch(){
        mSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mTv1.setText("开");
                }else {
                    mTv1.setText("关");
                }
            }
        });
        mSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mTv2.setText("开");
                }else {
                    mTv2.setText("关");
                }
            }
        });
        mSwitch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mTv3.setText("开");
                }else {
                    mTv3.setText("关");
                }
            }
        });
    }
}
