package com.example.a13466.nanjingt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisteredActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnBack;
    private TextView mTvTitle;
    private EditText mEtName;
    private EditText mEtFPassword;
    private Button mBtnRegistered;
    private EditText mEtSPassword;
    private SharedPreferences Registered_pref;
    private SharedPreferences.Editor Registered_editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        initView();
        mTvTitle.setText("注 册 页 面");
    }

    private void initView() {
        mBtnBack = (Button) findViewById(R.id.actionBar_btn_back);
        mBtnBack.setOnClickListener(this);
        mTvTitle = (TextView) findViewById(R.id.actionBar_tv_title);
        mEtName = (EditText) findViewById(R.id.Et_name);
        mEtFPassword = (EditText) findViewById(R.id.Et_password);
        mBtnRegistered = (Button) findViewById(R.id.Btn_registered);
        mBtnRegistered.setOnClickListener(this);
        mEtSPassword = (EditText) findViewById(R.id.Et_Second_password);
        Registered_pref = getSharedPreferences("Registered", Context.MODE_PRIVATE);
        Registered_editor = Registered_pref.edit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionBar_btn_back:
                // TODO 18/03/27

                break;
            case R.id.Btn_registered:
                // TODO 18/03/27 注册
                if (mEtName.length()>0&&mEtFPassword.length()>0&&mEtSPassword.length()>0){
                    String name = mEtName.getText().toString();
                    String Fpassword = mEtFPassword.getText().toString();
                    String Spassword = mEtSPassword.getText().toString();
                    if (Spassword.equals(Fpassword)){
                        Registered_editor.putString("name",name);
                        Registered_editor.putString("pass_word",Fpassword);
                        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this,LoginActivity.class));
                        finish();
                    }else {
                        Toast.makeText(this, "两次密码输入不一样", Toast.LENGTH_SHORT).show();
                        mEtName.setText(null);
                        mEtFPassword.setText(null);
                        mEtSPassword.setText(null);
                    }
                    Registered_editor.apply();
                }else {
                    Toast.makeText(this, "注册信息填写不完整", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
