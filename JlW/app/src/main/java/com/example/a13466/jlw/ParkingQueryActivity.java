package com.example.a13466.jlw;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ParkingQueryActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButtonBackParkingQuery;
    private Button mButtonRateQueryParkingQuery;
    private TextView mTextViewRateParkingQuery;
    private EditText mEditTextRateParkingQuery;
    private Button mButtonRateSetupParkingQuery;
    private Button mButtonParkingQueryParkingQuery;
    private TextView mTextViewParkingLot1ParkingQuery;
    private TextView mTextViewParkingLot2ParkingQuery;
    private SharedPreferences.Editor editor;
    private SharedPreferences pref;
    int RateMoney;
    int F=1;
    String RateEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_query);
        initView();
        ActionBar action=getSupportActionBar();
        if (action!=null){
            action.hide();
        }
        pref=getSharedPreferences("Rate",MODE_PRIVATE);
        RateMoney=pref.getInt("reta_f",0);
    }

    private void initView() {
        mButtonBackParkingQuery = (Button) findViewById(R.id.ParkingQuery_button_back);
        mButtonBackParkingQuery.setOnClickListener(this);
        mButtonRateQueryParkingQuery = (Button) findViewById(R.id.ParkingQuery_button_RateQuery);
        mButtonRateQueryParkingQuery.setOnClickListener(this);
        mTextViewRateParkingQuery = (TextView) findViewById(R.id.ParkingQuery_TextView_Rate);
        mEditTextRateParkingQuery = (EditText) findViewById(R.id.ParkingQuery_EditText_Rate);
        mButtonRateSetupParkingQuery = (Button) findViewById(R.id.ParkingQuery_button_RateSetup);
        mButtonRateSetupParkingQuery.setOnClickListener(this);
        mButtonParkingQueryParkingQuery = (Button) findViewById(R.id.ParkingQuery_button_ParkingQuery);
        mButtonParkingQueryParkingQuery.setOnClickListener(this);
        mTextViewParkingLot1ParkingQuery = (TextView) findViewById(R.id.ParkingQuery_TextView_ParkingLot_1);
        mTextViewParkingLot2ParkingQuery = (TextView) findViewById(R.id.ParkingQuery_TextView_ParkingLot_2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ParkingQuery_button_back:
                // TODO 18/03/14
                Intent intentBack = new Intent(ParkingQueryActivity.this, MainActivity.class);
                startActivity(intentBack);
                finish();
                break;
            case R.id.ParkingQuery_button_RateQuery:
                // TODO 18/03/14
                if (F==1){
                    mTextViewRateParkingQuery.setText(RateMoney+"");
                }else {
                    mTextViewRateParkingQuery.setText(RateMoney+"");
                }
                break;
            case R.id.ParkingQuery_button_RateSetup:
                // TODO 18/03/14
                RateEdit=mEditTextRateParkingQuery.getText().toString();
                if (mEditTextRateParkingQuery.length()==0){
                    Toast.makeText(this, "费率为空", Toast.LENGTH_SHORT).show();
                }else {
                    if (RateEdit.equals("0")){
                        Toast.makeText(this, "费率不能设置为：0元", Toast.LENGTH_SHORT).show();
                    }else {
                        editor=getSharedPreferences("Rate",MODE_PRIVATE).edit();
                        editor.putInt("reta_f",Integer.parseInt(RateEdit));
                        editor.apply();
                        F=2;
                        RateMoney=Integer.parseInt(RateEdit);
                        Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.ParkingQuery_button_ParkingQuery:
                // TODO 18/03/14
                mTextViewParkingLot1ParkingQuery.setBackgroundResource(R.drawable.car_off);
                mTextViewParkingLot2ParkingQuery.setBackgroundResource(R.drawable.car_off);
                break;
            default:
                break;
        }
    }
}
