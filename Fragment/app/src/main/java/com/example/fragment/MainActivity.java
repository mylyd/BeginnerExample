package com.example.fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private Button button_home;
    private Button button_friends;
    private Button button_message;
    private Button button_more;
    //Fragment管理器
    private FragmentManager fm = this.getSupportFragmentManager();
    private FragmentTransaction ft;

    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
        //开始事务（每次改变Fragment管理器之后都要提交）
        ft = fm.beginTransaction();
        home();
        //提交事务
        ft.commit();
    }

    private void initView() {


        button_home = (Button) findViewById(R.id.Button_home);
        button_friends = (Button) findViewById(R.id.Button_friends);
        button_message = (Button) findViewById(R.id.Button_message);
        button_more = (Button) findViewById(R.id.Button_more);

        button_home.setOnClickListener(this);
        button_friends.setOnClickListener(this);
        button_message.setOnClickListener(this);
        button_more.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        //每次点击时都需要重新开始事务
        ft = fm.beginTransaction();
        //把显示的Fragment隐藏
         setSelected();
        switch (v.getId()) {
            case R.id.Button_home:
                home();
                break;
            case R.id.Button_friends:
                friend();
                break;
            case R.id.Button_message:
                message();
                break;
            case R.id.Button_more:
                more();
                break;
        }
        ft.commit();

    }

   private void setSelected() {
        if (fragment1 != null) {
            //隐藏Fragment
            ft.hide(fragment1);
        }
        if (fragment2 != null) {
            ft.hide(fragment2);
        }
        if (fragment3 != null) {
            ft.hide(fragment3);
        }
        if (fragment4 != null) {
            ft.hide(fragment4);
        }
    }

    private void home() {
        if (fragment1 == null) {
            fragment1 = new Fragment1();
            /*添加到Fragment管理器中
            这里如果用replace，
            当每次调用时都会把前一个Fragment给干掉，
            这样就导致了每一次都要创建、销毁，
            数据就很难保存，用add就不存在这样的问题了，
            当Fragment存在时候就让它显示，不存在时就创建，
            这样的话数据就不需要自己保存了，
            因为第一次创建的时候就已经保存了，
            只要不销毁一直都将存在*/
            ft.add(R.id.fl_content, fragment1);
        } else {
            //显示Fragment
            ft.show(fragment1);
        }
    }

    private void friend() {
        if (fragment2 == null) {
            fragment2 = new Fragment2();
            ft.add(R.id.fl_content, fragment2);
        } else {
            ft.show(fragment2);
        }

    }

    private void message() {
        if (fragment3 == null) {
            fragment3 = new Fragment3();
            ft.add(R.id.fl_content, fragment3);
        } else {
            ft.show(fragment3);
        }

    }

    private void more() {
        if (fragment4 == null) {
            fragment4 = new Fragment4();
            ft.add(R.id.fl_content, fragment4);
        } else {
            ft.show(fragment4);
        }

    }
}

