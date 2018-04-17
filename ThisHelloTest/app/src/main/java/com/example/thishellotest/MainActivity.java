package com.example.thishellotest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,DialogActivity.Callback {

    private TextView text;
    private EditText mAccountEt;
    private EditText mPasswordEt;
    private CheckBox mSavePassword;
    private CheckBox mAutoLogin;
    private Button mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        initState();
        /*隐藏title*/
        ActionBar actionbar=getSupportActionBar();
        if (actionbar!=null){
            actionbar.hide();
        }
        //记录进入的次数；
        SharedPreferences pref = getSharedPreferences("first",MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("status",false);
        editor.commit();
        //更改Title
        text.setText("用   户   登   录");

        ReadData();
    }

    private void init(){
        Button zhuce= (Button) findViewById(R.id.button_zhuce);
        zhuce.setOnClickListener(this);
        Button internet= (Button) findViewById(R.id.bto_Internet);
        internet.setOnClickListener(this);
        text= (TextView) findViewById(R.id.title_text);
        mAccountEt = (EditText) findViewById(R.id.account);
        mPasswordEt = (EditText) findViewById(R.id.password);
        mSavePassword = (CheckBox) findViewById(R.id.checkbox_Remember);
        mAutoLogin = (CheckBox) findViewById(R.id.checkbox_Automatic);
        mLoginBtn = (Button) findViewById(R.id.button_loginPage);
        mLoginBtn.setOnClickListener(this);
        //
        SharedPreferencesUtils.init(this);
    }

    public void onClick(View view){
    switch (view.getId()){
        case R.id.button_zhuce:
            Intent intent=new Intent(MainActivity.this,Register.class);
            startActivity(intent);
            break;
        case R.id.bto_Internet:
            DialogActivity dialogActivity=new DialogActivity();
            dialogActivity.show(getSupportFragmentManager());
            break;
        case R.id.button_loginPage:
            //初始化账号和密码
            String initAccont = SharedPreferencesUtils.getString("Login", "init_account", "admin");
            String initPassword = SharedPreferencesUtils.getString("Login", "init_password", "admin");
            String account = mAccountEt.getText().toString().trim();
            String password = mPasswordEt.getText().toString().trim();
            if(initAccont.equals(account) && initPassword.equals(password)){
                SharedPreferencesUtils.putString("Login","account",account);
                if (SharedPreferencesUtils.getBoolean("Login","save_password",false)) {
                    SharedPreferencesUtils.putString("Login","password",password);
                }
                Intent intent1 = new Intent(this,FirstMainActivity.class);
                startActivity(intent1);
            }else {
                Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                SharedPreferencesUtils.putString("Login","password","");
            }
            break;

        }
    }



    /**
     * 自动判读
     */
    private void ReadData(){
        //自动登录判读
        boolean autoLogin = SharedPreferencesUtils.getBoolean("Login","auto_login",false);
        if (autoLogin) {
            String initAccont = SharedPreferencesUtils.getString("Login", "init_account", "admin");
            String initPassword = SharedPreferencesUtils.getString("Login", "init_password", "admin");

            String account = SharedPreferencesUtils.getString("Login","account","");
            String password = SharedPreferencesUtils.getString("Login","password","");
            if(initAccont.equals(account) && initPassword.equals(password)){
                Intent intent = new Intent(this,FirstMainActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
            }
        }


        mSavePassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferencesUtils.putBoolean("Login","save_password",isChecked);
            }
        });
        mAutoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferencesUtils.putBoolean("Login","auto_login",isChecked);
            }
        });
    }

    private void initState() {
        boolean savePass = SharedPreferencesUtils.getBoolean("Login","save_password",false);
        boolean autoLogin = SharedPreferencesUtils.getBoolean("Login","auto_login",false);
        mSavePassword.setChecked(savePass);
        mAutoLogin.setChecked(autoLogin);
        String account = SharedPreferencesUtils.getString("Login","account","");
        String password = SharedPreferencesUtils.getString("Login","password","");
        mAccountEt.setText(account);
        if (savePass) {
            mPasswordEt.setText(password);
        }
    }


    public void onClick(String S1,String S2){
        Toast.makeText(this, "服务器地址："+S1+"\n"+"  端口："+S2, Toast.LENGTH_SHORT).show();
    }
}
