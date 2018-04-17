package com.example.a13466.jlw;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mSettings01Et;
    private EditText mSettings02Et;
    private EditText mSettings03Et;
    private EditText mSettings04Et;
    private Button mSettingsBtn;
    private Button mBackBtn;
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initView();
        mTvTitle.setText("网络设置");
    }

    private void initView() {
        mSettings01Et = (EditText) findViewById(R.id.et_settings_01);
        mSettings02Et = (EditText) findViewById(R.id.et_settings_02);
        mSettings03Et = (EditText) findViewById(R.id.et_settings_03);
        mSettings04Et = (EditText) findViewById(R.id.et_settings_04);
        mSettingsBtn = (Button) findViewById(R.id.btn_settings);
        mSettingsBtn.setOnClickListener(this);
        mBackBtn = (Button) findViewById(R.id.btn_back);
        mBackBtn.setOnClickListener(this);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_settings:

                break;
            case R.id.btn_back:// TODO 18/03/16
                finish();
                break;
            default:
                break;
        }
    }
}
