package com.example.a13466.nanjingt.HomeFragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a13466.nanjingt.R;
import com.example.a13466.nanjingt.db.Book_Recharge;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 我的账户
 */
public class FragmentMyAccount extends Fragment implements View.OnClickListener {
    private TextView mTvBalance;
    private Spinner mSpCarNum;
    private Button mBtnQuery;
    private Button mBtnRecharge;
    private EditText mEtMoney;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    int Carnum;//Spinner显示的号码
    int mMoney_1,mMoney_2,mMoney_3;
    int F=0;

    public FragmentMyAccount() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myaccount, container, false);
        initView(view);
        setmSpCarNum();
        return view;
    }

    private void initView(View view) {
        mTvBalance = (TextView) view.findViewById(R.id.my_tv_balance);
        mSpCarNum = (Spinner) view.findViewById(R.id.my_spinner_carNumber);
        mBtnQuery = (Button) view.findViewById(R.id.my_btn_Query);
        mBtnQuery.setOnClickListener(this);
        mBtnRecharge = (Button) view.findViewById(R.id.my_btn_Recharge);
        mBtnRecharge.setOnClickListener(this);
        mEtMoney = (EditText) view.findViewById(R.id.my_Et_money);
        pref = getActivity().getSharedPreferences("balance", Context.MODE_PRIVATE);
        editor =pref.edit();
        mTvBalance.setText(pref.getInt("num_1",0)+" 元");
        mMoney_1 = pref.getInt("num_1",0);
        mMoney_2 = pref.getInt("num_2",0);
        mMoney_3 = pref.getInt("num_3",0);
    }

    @Override
    public void onClick(View v) {
        Carnum = Integer.parseInt(mSpCarNum.getSelectedItem().toString());
        switch (v.getId()) {
            case R.id.my_btn_Query:
                // TODO 18/03/28
                         if (Carnum==1) {
                        //Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
                            mTvBalance.setText(pref.getInt("num_1",0)+" 元");
                        }
                        if (Carnum==2) {
                        //Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();
                            mTvBalance.setText(pref.getInt("num_2",0)+" 元");
                        }
                        if (Carnum==3) {
                        //Toast.makeText(getContext(), "3", Toast.LENGTH_SHORT).show();
                            mTvBalance.setText(pref.getInt("num_3",0)+" 元");
                        }

                break;
            case R.id.my_btn_Recharge:
                // TODO 18/03/28
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
                Date date = new Date(System.currentTimeMillis());
                String timer = simpleDateFormat.format(date);
                Book_Recharge br = new Book_Recharge();
                if (mEtMoney.length()>0){
                    String Money = mEtMoney.getText().toString();
                    int mon = Integer.parseInt(Money);
                    if (mon>0&&mon<1000){
                        if (Carnum==1){
                            mMoney_1=mon+mMoney_1;
                            editor.putInt("num_1",mMoney_1);
                            mTvBalance.setText(mMoney_1+" 元");
                            br.setRecharge_Money(mon);
                        }
                        if (Carnum==2){
                            mMoney_2=mon+mMoney_2;
                            editor.putInt("num_2",mMoney_2);
                            mTvBalance.setText(mMoney_2+" 元");
                            br.setRecharge_Money(mon);
                        }
                        if (Carnum==3){
                            mMoney_3=mon+mMoney_3;
                            editor.putInt("num_3",mMoney_3);
                            mTvBalance.setText(mMoney_3+" 元");
                            br.setRecharge_Money(mon);
                        }
                        Toast.makeText(getContext(), "充值成功", Toast.LENGTH_SHORT).show();
                        editor.apply();
                        br.setRecharge_CarNumber(Carnum);
                        br.setRecharge_Name("admin");
                        br.setRecharge_Time(timer);
                        br.save();
                    }else {
                        Toast.makeText(getContext(), "充值范围是：1~999元的整数", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(), "请输入充值金额", Toast.LENGTH_SHORT).show();
                }
                mEtMoney.setText(null);
                break;
            default:
                break;
        }
    }

    private void setmSpCarNum(){
        mSpCarNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (F!=0){
                Toast.makeText(getContext(), "当前选择为："+(position+1)+"号车", Toast.LENGTH_SHORT).show();
                mTvBalance.setText(null);
                }
                F++;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    /**
     * 仅保留数据库10组数据
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        int count = DataSupport.count(Book_Recharge.class);
        if (count>9){
            DataSupport.deleteAll(Book_Recharge.class,"id<?",String.valueOf(DataSupport.findLast(Book_Recharge.class).getId()-9));
        }
    }



}
