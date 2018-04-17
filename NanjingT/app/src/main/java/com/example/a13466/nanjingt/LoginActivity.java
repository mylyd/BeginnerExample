package com.example.a13466.nanjingt;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 登录主页
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnBack;
    private TextView mTvTitle;
    private EditText mAccount;
    private EditText mPassword;
    private CheckBox mRememberCheckbox;
    private CheckBox mAutomaticCheckbox;
    private Button mBtnLogin;
    private Button mBtnRegistered;
    private SharedPreferences pref,Registered_pref;
    private SharedPreferences.Editor editor;
    String ATadmin;
    String PSadmin;
    boolean remember_password;
    boolean automatic;
    String name;
    String pass;
    int T;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        //判断是不是第一次进入，只要进入一次以后 持久化值变为：False;
        SharedPreferences.Editor editor = getSharedPreferences("StartFirst", Context.MODE_PRIVATE).edit();
        editor.putBoolean("first", false);
        editor.apply();
        //
        mTvTitle.setText("登 录 页 面");

        if (remember_password){
            String acc = pref.getString("YHM","");
            String pss = pref.getString("MM","");
            if (T==0){
                mAccount.setText(acc);
                mPassword.setText(pss);
            }
            if (T==1){
                mAccount.setText(name);
                mPassword.setText(pass);
            }
            mRememberCheckbox.setChecked(true);
            if (automatic){
                mAutomaticCheckbox.setChecked(true);
                startActivity(new Intent(this,MainActivity.class));
                finish();
            }
        }
    }

    private void initView() {
        mBtnBack = (Button) findViewById(R.id.actionBar_btn_back);
        mBtnBack.setOnClickListener(this);
        mTvTitle = (TextView) findViewById(R.id.actionBar_tv_title);
        mAccount = (EditText) findViewById(R.id.account);
        mPassword = (EditText) findViewById(R.id.password);
        mRememberCheckbox = (CheckBox) findViewById(R.id.checkbox_Remember);
        mAutomaticCheckbox = (CheckBox) findViewById(R.id.checkbox_Automatic);
        mBtnLogin = (Button) findViewById(R.id.btn_loginPage);
        mBtnLogin.setOnClickListener(this);
        mBtnRegistered = (Button) findViewById(R.id.btn_registered);
        mBtnRegistered.setOnClickListener(this);
        //注册的账号信息
        Registered_pref = getSharedPreferences("Registered", Application.MODE_PRIVATE);
        name = Registered_pref.getString("name","");
        pass = Registered_pref.getString("pass_word","");

        pref = getSharedPreferences("Ps",Context.MODE_PRIVATE);
        editor = pref.edit();
        remember_password = pref.getBoolean("re_ps",false);
        automatic = pref.getBoolean("au_ma",false);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionBar_btn_back:
                // TODO 18/03/27

                Toast.makeText(this, name+"     "+pass, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_loginPage:
                // TODO 18/03/27 登录
                ATadmin = mAccount.getText().toString();
                PSadmin = mPassword.getText().toString();
                if (mAccount.length()>0||mPassword.length()>0){
                    if ((ATadmin.equals("admin")&&PSadmin.equals("admin"))||(ATadmin.equals(name)&&PSadmin.equals(pass))){
                            if (ATadmin.equals("admin")&&PSadmin.equals("admin")){
                                    T=0;
                            }else {
                                    T=1;
                            }
                        if (mRememberCheckbox.isChecked()){
                            editor.putBoolean("re_ps",true);
                            editor.putString("YHM",ATadmin);
                            editor.putString("MM",PSadmin);
                            if (mAutomaticCheckbox.isChecked()){
                                editor.putBoolean("au_ma",true);
                            }else {
                                editor.putBoolean("au_ma",false);
                            }
                        }else {
                            editor.clear();
                        }
                       editor.apply();
                        startActivity(new Intent(this,MainActivity.class));
                        //finish();
                    }else {
                        Toast.makeText(this, "用户名和密码输入错误", Toast.LENGTH_SHORT).show();
                        mAccount.setText(null);
                        mPassword.setText(null);
                    }
                }else {
                    Toast.makeText(this, "请输入正确的用户名和密码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_registered:
                // TODO 18/03/27 注册
                startActivity(new Intent(this,RegisteredActivity.class));
                break;
            default:
                break;
        }
    }
}
