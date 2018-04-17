package com.example.a13466.nanjingt.HomeFragment;

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
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a13466.nanjingt.R;
import com.example.a13466.nanjingt.bean.MyApplication;

/**
 * 阀值设置
 */
public class FragmentThreshold extends Fragment implements View.OnClickListener {
    private TextView mTvOpen;
    private Switch mSwitch;
    private EditText mEtTem;
    private EditText mEtHum;
    private EditText mEtLight;
    private EditText mEtCO2;
    private EditText mEtPM25;
    private EditText mETRoad;
    private Button mBtnThr;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_threshold, container, false);
        initView(view);
       // setSwitch();
        Et(false);
        return view;
    }

    private void initView(View itemView) {
        mTvOpen = (TextView) itemView.findViewById(R.id.Thr_open);
        mSwitch = (Switch) itemView.findViewById(R.id.Thr_switch);
        mEtTem = (EditText) itemView.findViewById(R.id.editText_Tem);
        mEtHum = (EditText) itemView.findViewById(R.id.editText_Hum);
        mEtLight = (EditText) itemView.findViewById(R.id.editText_Light);
        mEtCO2 = (EditText) itemView.findViewById(R.id.editText_CO2);
        mEtPM25 = (EditText) itemView.findViewById(R.id.editText_PM25);
        mETRoad = (EditText) itemView.findViewById(R.id.editText_Road);
        mBtnThr = (Button) itemView.findViewById(R.id.Thr_button);
        mBtnThr.setOnClickListener(this);


        pref = getActivity().getSharedPreferences("text",Context.MODE_PRIVATE);
        editor = pref.edit();

        mSwitch.setChecked(true);//将switch设置为open
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    //
                    Et(false);
                    mTvOpen.setText("开");
                    editor.putBoolean("sw",true);
                }else {
                    Et(true);
                    mTvOpen.setText("关");
                    editor.putBoolean("sw",false);
                }
                editor.apply();
            }
        });
    }

    private void setSwitch(){
        if (mETRoad.length()>0&&mEtPM25.length()>0&&mEtCO2.length()>0&&mEtLight.length()>0&&mEtHum.length()>0&&mEtTem.length()>0){
            editor.putInt("tv_1",Integer.parseInt(mEtTem.getText().toString()));
            editor.putInt("tv_2",Integer.parseInt(mEtHum.getText().toString()));
            editor.putInt("tv_3",Integer.parseInt(mEtLight.getText().toString()));
            editor.putInt("tv_4",Integer.parseInt(mEtCO2.getText().toString()));
            editor.putInt("tv_5",Integer.parseInt(mEtPM25.getText().toString()));
            editor.putInt("tv_6",Integer.parseInt(mETRoad.getText().toString()));
        }else {
            Toast.makeText(MyApplication.getContext(), "输入有误", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Thr_button:
                // TODO 18/04/02
                setSwitch();
                editor.apply();
                 mEtTem.setText(null);
                 mEtHum.setText(null);
                 mEtLight.setText(null);
                 mEtCO2.setText(null);
                 mEtPM25.setText(null);
                 mETRoad.setText(null);
                break;
            default:
                break;
        }
    }
    private void Et(boolean ok){
         mEtTem.setFocusable(ok);
         mEtTem.setFocusableInTouchMode(ok);
         mEtHum.setFocusable(ok);
         mEtHum.setFocusableInTouchMode(ok);
         mEtLight.setFocusable(ok);
         mEtLight.setFocusableInTouchMode(ok);
         mEtCO2.setFocusable(ok);
         mEtCO2.setFocusableInTouchMode(ok);
         mEtPM25.setFocusable(ok);
         mEtPM25.setFocusableInTouchMode(ok);
         mETRoad.setFocusable(ok);
         mETRoad.setFocusableInTouchMode(ok);

         mEtTem.setText(pref.getInt("tv_1",15)+"");
         mEtHum.setText(pref.getInt("tv_2",20)+"");
         mEtLight.setText(pref.getInt("tv_3",800)+"");
         mEtCO2.setText(pref.getInt("tv_4",2000)+"");
         mEtPM25.setText(pref.getInt("tv_5",2000)+"");
         mETRoad.setText(pref.getInt("tv_6",2)+"");

    }
}
