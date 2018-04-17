package com.example.a13466.jlw.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a13466.jlw.MyCarFragmentActivity;
import com.example.a13466.jlw.R;
import com.example.a13466.jlw.util.HttpPostThread;
import com.example.a13466.jlw.util.MyJsonUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyCarFragmentAccount extends Fragment implements View.OnClickListener {

    private Spinner mSpAccount;
    private Button mBtnQueryMyCar;
    private TextView mTvMyCar;
    private Spinner mSpRecharge;
    private EditText mEtTextMyCar;
    private Button mBtnRechargeMyCar;
    private TextView mTvHistoricalRecordQueryMyCar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_mycar_fragment_account, null);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void requestAccount(int carId) {
        String url = "http://10.31.231.85:8080/transportservice/type/jason/action/GetCarAccountBalance.do";
        HttpPostThread thread = new HttpPostThread(url,"{\"CarId\":" +  carId + "}");
        thread.setmPostCallback(new HttpPostThread.PostCallback() {
            @Override
            public void handleData(String response) {
                final String balance = MyJsonUtil.handleAccountJson(response,"Balance");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTvMyCar.setText("当前账户余额："+balance+"");
                    }
                });
            }
        });
        thread.start();

    }

    private void postRecharge(final int carId){
        if (mEtTextMyCar.length() > 0){
            final String money = mEtTextMyCar.getText().toString();
            String url = "http://10.31.231.85:8080/transportservice/type/jason/action/SetCarAccountRecharge.do";
            HttpPostThread thread = new HttpPostThread(url,"{\"CarId\":" +  carId + ",\"Money\":" +  Integer.parseInt(money) + "}");
            thread.setmPostCallback(new HttpPostThread.PostCallback() {
                @Override
                public void handleData(String response) {
                    final String status = MyJsonUtil.handleAccountJson(response,"result");
                    Log.d("status", "handleData: "+status);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (status.equals("ok")) {
                                Toast.makeText(getActivity(), "充值成功", Toast.LENGTH_SHORT).show();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
//获取当前时间
                                Date date = new Date(System.currentTimeMillis());
                                mTvHistoricalRecordQueryMyCar.setText("车号"+carId+"充值"+money+"于"+simpleDateFormat.format(date));
                                //mTvHistoricalRecordQueryMyCar.setText();
                            } else {
                                Toast.makeText(getActivity(), "充值失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
            thread.start();
        }else {
            Toast.makeText(getActivity(), "请输入充值金额", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView(View itemView) {

        mSpAccount = (Spinner) itemView.findViewById(R.id.MyCar_Fragment_account_Spinner_1);
        mBtnQueryMyCar = (Button) itemView.findViewById(R.id.MyCar_Fragment_account_btn_query);
        mTvMyCar = (TextView) itemView.findViewById(R.id.MyCar_Fragment_account_TextView);
        mSpRecharge = (Spinner) itemView.findViewById(R.id.MyCar_Fragment_account_Spinner_2);
        mEtTextMyCar = (EditText) itemView.findViewById(R.id.MyCar_Fragment_account_EditText);
        mBtnRechargeMyCar = (Button) itemView.findViewById(R.id.MyCar_Fragment_account_btn_Recharge);
        mTvHistoricalRecordQueryMyCar = (TextView) itemView.findViewById(R.id.MyCar_Fragment_account_TextView_HistoricalRecordQuery);
        mBtnQueryMyCar.setOnClickListener(this);
        mBtnRechargeMyCar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.MyCar_Fragment_account_btn_query:
                requestAccount(Integer.parseInt(mSpAccount.getSelectedItem().toString()));
                break;
            case R.id.MyCar_Fragment_account_btn_Recharge:
                postRecharge(Integer.parseInt(mSpRecharge.getSelectedItem().toString()));
                break;
            default:
                break;
        }
    }

}
