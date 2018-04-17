package com.example.thishellotest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.thishellotest.T10_14.Fragment_R;
import com.example.thishellotest.T15_20.T15;
import com.example.thishellotest.T15_20.T16;
import com.example.thishellotest.T15_20.T17;
import com.example.thishellotest.T15_20.T18;
import com.example.thishellotest.T15_20.T19;
import com.example.thishellotest.T15_20.T20;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class FirstMainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bto_1,bto_2,bto_3,bto_4,bto_5,bto_6,bto_7,bto_8;
    private Button button_DrawerLayout;
    private Button litepal;
    private Button T1,T2,T3,T4,T5,T6;
    private Button book_1,book_2,book_3,bookopen;

    private DrawerLayout mDrawerLayout;
    private NavigationView navView;


    private void initView() {
        bto_1=(Button)findViewById(R.id.bto_1);
        bto_2=(Button)findViewById(R.id.bto_2);
        bto_3=(Button)findViewById(R.id.bto_3);
        bto_4=(Button)findViewById(R.id.bto_4);
        bto_5=(Button)findViewById(R.id.bto_5);
        bto_6=(Button)findViewById(R.id.bto_6);
        bto_7=(Button)findViewById(R.id.bto_7);
        bto_8=(Button)findViewById(R.id.bto_8);

        T1=(Button)findViewById(R.id.t_1);
        T2=(Button)findViewById(R.id.t_2);
        T3=(Button)findViewById(R.id.t_3);
        T4=(Button)findViewById(R.id.t_4);
        T5=(Button)findViewById(R.id.t_5);
        T6=(Button)findViewById(R.id.t_6);
        T1.setOnClickListener(this);
        T2.setOnClickListener(this);
        T3.setOnClickListener(this);
        T4.setOnClickListener(this);
        T5.setOnClickListener(this);
        T6.setOnClickListener(this);

        button_DrawerLayout=(Button)findViewById(R.id.button_DrawerLayout);
        litepal= (Button) findViewById(R.id.open_LitePal);

        book_1= (Button) findViewById(R.id.Book_1);
        book_2= (Button) findViewById(R.id.Book_2);
        book_3= (Button) findViewById(R.id.Book_3);
        bookopen= (Button) findViewById(R.id.BookOpen);


        book_1.setOnClickListener(this);
        book_2.setOnClickListener(this);
        book_3.setOnClickListener(this);
        bookopen.setOnClickListener(this);

        bto_1.setOnClickListener(this);
        bto_2.setOnClickListener(this);
        bto_3.setOnClickListener(this);
        bto_4.setOnClickListener(this);
        bto_5.setOnClickListener(this);
        bto_6.setOnClickListener(this);
        bto_7.setOnClickListener(this);
        bto_8.setOnClickListener(this);


        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        litepal.setOnClickListener(this);
        button_DrawerLayout.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_mainactivity);

         /*隐藏title*/
        ActionBar actionbar=getSupportActionBar();
        if (actionbar!=null){
            actionbar.hide();
        }

       initView();

        //


        /*
        * */
        navView= (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.back_ButtonFirst:
                        //关闭DrawerLayout界面
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.back_ButtonLogin:
                        //返回到登录页面
                        finish();
                        break;

                    case R.id.RR:
                        //车辆管理
                        Intent intent111=new Intent(FirstMainActivity.this,Fragment_R.class);
                        startActivity(intent111);
                        finish();
                        break;

                    case R.id.nav_Ambient:
                        //环境指标
                        Intent intentAmbient=new Intent(FirstMainActivity.this,AmbientActivty.class);
                        startActivity(intentAmbient);
                        finish();
                        break;

                    case R.id.nav_bill:
                        //充值历史记录
                        Intent intent0=new Intent(FirstMainActivity.this,RechargeActivity.class);
                        startActivity(intent0);
                        finish();
                        break;

                    case R.id.nav_carHistory:
                        //传感器数据历史记录
                        Intent intent00=new Intent(FirstMainActivity.this,SensorActivity.class);
                        startActivity(intent00);
                        finish();
                        break;

                    case R.id.nav_carMax:
                        //小车速度/账户额度阀值
                        Intent intenti=new Intent(FirstMainActivity.this,RechargeFragmentActivity.class);
                        startActivity(intenti);
                        finish();
                        break;

                    case R.id.nav_moneyMax:
                        //账户充值阀值
                        Toast.makeText(FirstMainActivity.this, "账户充值阀值", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_Recharge:
                        //账户充值
                        Intent i11=new Intent(FirstMainActivity.this,AccountRecharge.class);
                        startActivity(i11);
                        finish();
                        break;

                    case R.id.nav_SetupDrawerLayout:
                        //设置
                        Book book=new Book();
                        book.setIde(1);
                        book.save();
                        BookSecond second=new BookSecond();
                        second.setIdt(1);
                        second.setCarnumber("GGbbbbbb");
                        second.setSpeedmax("60");
                        second.setSpeedmin("20");
                        second.save();



                        Toast.makeText(FirstMainActivity.this, "设置这个按钮我会还没有扩展\n哈哈哈哈哈哈……", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_AboutDrawerLayout:
                        //关于
                        Toast.makeText(FirstMainActivity.this, "这里面什么都没有你点个锤子\n 是不是傻", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bto_1:
                //环境指标
                Intent intentFirstMain=new Intent(FirstMainActivity.this,AmbientActivty.class);
                startActivity(intentFirstMain);
                break;
            case R.id.bto_2:
                //充值历史记录
                Intent intent0=new Intent(FirstMainActivity.this,RechargeActivity.class);
                startActivity(intent0);
                break;
            case R.id.bto_3:
                //传感器数据历史记录
                Intent intent00=new Intent(FirstMainActivity.this,SensorActivity.class);
                startActivity(intent00);
                break;
            case R.id.bto_4:
                //小车速度/账户额度阀值
                Intent intenti=new Intent(FirstMainActivity.this,RechargeFragmentActivity.class);
                startActivity(intenti);
                finish();
                break;
            case R.id.bto_5:
                //账户充值
                Intent i11=new Intent(FirstMainActivity.this,AccountRecharge.class);
                startActivity(i11);
                break;
            case R.id.bto_6:
                //车辆管理
                Intent intent111=new Intent(FirstMainActivity.this,Fragment_R.class);
                startActivity(intent111);
                break;
            case R.id.bto_7:

                break;
            case R.id.bto_8:

                break;

            case R.id.t_1:
                Intent t1=new Intent(FirstMainActivity.this,T15.class);
                startActivity(t1);
                break;
            case R.id.t_2:
                Intent t2=new Intent(FirstMainActivity.this,T16.class);
                startActivity(t2);

                break;
            case R.id.t_3:
                Intent t3=new Intent(FirstMainActivity.this,T17.class);
                startActivity(t3);

                break;
            case R.id.t_4:
                Intent t4=new Intent(FirstMainActivity.this,T18.class);
                startActivity(t4);

                break;
            case R.id.t_5:
                Intent t5=new Intent(FirstMainActivity.this,T19.class);
                startActivity(t5);

                break;
            case R.id.t_6:
                Intent t6=new Intent(FirstMainActivity.this,T20.class);
                startActivity(t6);

                break;


            case R.id.button_DrawerLayout:
                //打开DrawerLayout界面按钮
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;

            case R.id.open_LitePal:
             //   LitePal.getDatabase(); //创建数据库
           //DataSupport.deleteAll(BookSecond.class);// 删除数据
                break;
            case R.id.Book_1:

                break;
            case R.id.Book_2:

                break;
            case R.id.Book_3:

                break;
            case R.id.BookOpen:

                break;


        }
    }

}
