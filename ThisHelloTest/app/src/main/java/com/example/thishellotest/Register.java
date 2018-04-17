package com.example.thishellotest;


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity implements View.OnClickListener,DialogActivity.Callback {

    private EditText YHM;
    private EditText MM;
    private EditText FMM;
    private EditText TEL;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        final TextView text= (TextView) findViewById(R.id.title_text);
        text.setText("用   户   注   册");

          /*隐藏title*/
        ActionBar actionbar=getSupportActionBar();
        if (actionbar!=null){
            actionbar.hide();
        }

        YHM= (EditText) findViewById(R.id.edit_YHMZC);
        MM= (EditText) findViewById(R.id.edit_MMFirst);
        FMM= (EditText) findViewById(R.id.edit_MMSecond);
        TEL= (EditText) findViewById(R.id.edit_Tel);

        Button quxiao= (Button) findViewById(R.id.button_quxiao);
        Button internet= (Button) findViewById(R.id.bto_Internet);
        Button zhuce= (Button) findViewById(R.id.zhuce);
        quxiao.setOnClickListener(this);
        zhuce.setOnClickListener(this);
        internet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_quxiao:
                finish();
                break;
            case R.id.zhuce:
                String account = YHM.getText().toString().trim();
                String password = MM.getText().toString().trim();
                String remember_pass = FMM.getText().toString().trim();
                String numberTel =TEL.getText().toString().trim();
                //主要用于什么都补填写就注册
                if (TextUtils.isEmpty(account)||TextUtils.isEmpty(password)||TextUtils.isEmpty(remember_pass)||TextUtils.isEmpty(numberTel)) {
                    Toast.makeText(this, "信息填写不完整", Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断两次密码是否一致
                if(!password.equals(remember_pass)){
                    Toast.makeText(this, "密码输入不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断手机号是否是11位
                if (numberTel.length()!=11){
                    Toast.makeText(this, "请输入11位的电话号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(this, "注册成功（这只是一个模板，不能返回你注册的账号进行新登陆）", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.bto_Internet:
                DialogActivity dialogActivity=new DialogActivity();
                dialogActivity.show(getSupportFragmentManager());
                break;
        }
    }

    @Override
    public void onClick(String S1, String S2) {
        Toast.makeText(this, "服务器地址："+S1+"  端口："+S2, Toast.LENGTH_SHORT).show();
    }
}
