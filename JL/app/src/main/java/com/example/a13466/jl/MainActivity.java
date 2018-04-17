package com.example.a13466.jl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a13466.jl.Service.MyService;
import com.example.a13466.jl.ViewPagerAdapter.FragmentViewPager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 */
public class MainActivity extends AppCompatActivity {
    //广播接收器
    private IntentFilter intentfilter;
    private NetworkChangeReceiver networkchangereceiver;
    //
    private DrawerLayout mDrawerLayout;
    private AlertDialog onDialog;
    long time = 0;//用来储存时间
    private TextView mTimetext;
    private Handler handler;
    int tim=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ReceiverThis();
        setmDrawerLayout();
        startService(new Intent(this, MyService.class));//启动服务
        timer.schedule(tt,1000,1000);
    }

    Timer timer=new Timer();
    TimerTask tt=new TimerTask() {
        @Override
        public void run() {
            tim++;
            SetTime();
        }
    };
    private void SetTime(){
        SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
        Date da=new Date(System.currentTimeMillis());
        final String stringtime=format.format(da);
        handler.post(new Runnable() {
            @Override
            public void run() {
                mTimetext.setText(stringtime);
                if (tim==1){
                    mTimetext.setTextColor(Color.parseColor("#40CFFF"));
                }
                if (tim==2){
                    mTimetext.setTextColor(Color.parseColor("#FFC125"));
                }
                if (tim==3){
                    mTimetext.setTextColor(Color.parseColor("#FF0000"));
                }
                if (tim==4){
                    mTimetext.setTextColor(Color.parseColor("#EE00EE"));
                }
                if (tim==5){
                    mTimetext.setTextColor(Color.parseColor("#76EE00"));
                }
                if (tim==6){
                    mTimetext.setTextColor(Color.parseColor("#551A8B"));
                }
                if (tim==7){
                    mTimetext.setTextColor(Color.parseColor("#00FA9A"));
                }
                if (tim==8){
                    mTimetext.setTextColor(Color.parseColor("#00FF00"));
                }
                if (tim==9){
                    mTimetext.setTextColor(Color.parseColor("#CD6600"));
                    tim=0;
                }
            }
        });
    }
    private void initView() {
        //获取实例
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setScrimColor(Color.argb(1, 0, 0, 0));//让Drawer的主页面不透明
        mTimetext = (TextView) findViewById(R.id.timetext);
        handler=new Handler();
    }

    /**
     * DrawerLayout
     */
    private void setmDrawerLayout() {
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // 得到contentView
                //推移
                View content = mDrawerLayout.getChildAt(0);
                int offset = (int) (drawerView.getWidth() * slideOffset);
                content.setTranslationX(-offset);
                content.setScaleX((1 - slideOffset / 2));
                content.setScaleY((1 - slideOffset / 2));
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
    }

    /**
     * 广播发射器
     */
    private void ReceiverThis() {
        intentfilter = new IntentFilter();
        intentfilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkchangereceiver = new NetworkChangeReceiver();
        registerReceiver(networkchangereceiver, intentfilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // unregisterReceiver();
    }

    /**
     * 广播接收器
     */
    class NetworkChangeReceiver extends BroadcastReceiver {
        boolean isFirst = false;

        @Override
        public void onReceive(Context context, Intent intent) {
            //内部类定义广播接收器
            ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkinfo = connectionManager.getActiveNetworkInfo();
            if (isFirst) {
                String str;
                if (networkinfo != null && networkinfo.isAvailable()) {
                    str = "网络已链接";
                } else {
                    str = "网络已断开";
                }
                if (onDialog != null) {
                    if (onDialog.isShowing())
                        onDialog.dismiss();
                }
                ondialog(str);
            }
            isFirst = true;
        }
    }

    /**
     * 退出程序提示机制
     */
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - time < 2000) {
            finish();
        } else {
            Toast.makeText(this, "再按一次退出系统", Toast.LENGTH_SHORT).show();
            time = System.currentTimeMillis();
        }
    }

    /**
     * @param v TextView onclick 事件
     */
    public void drawerLayout(View v) {
        mDrawerLayout.openDrawer(Gravity.RIGHT);//打开DrawerLayout
        //Toast.makeText(this, "22", Toast.LENGTH_SHORT).show();
    }

    /**
     * 调用系统back键功能
     *
     * @param v
     */
    public void first(View v) {
        onBackPressed();
    }

    /**
     * 用于显示网络连接与断开的消息
     *
     * @param finalText 自定义布局的Dialog
     */
    public void ondialog(String finalText) {
        AlertDialog.Builder ondialog = new AlertDialog.Builder(MainActivity.this);
        View view = View.inflate(MainActivity.this, R.layout.style_dialog, null);
        Button dialog_btn = (Button) view.findViewById(R.id.btn_Determine);
        TextView dialog_text = (TextView) view.findViewById(R.id.dialog_text);
        ondialog.setView(view).create();
        dialog_text.setText(finalText);
        ondialog.setCancelable(true);
        onDialog = ondialog.show();
        dialog_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialog.cancel();
            }
        });
        onDialog.show();
    }

    public void animationText(View v) {
        startActivity(new Intent(this, FirstActivity.class));
        overridePendingTransition(R.anim.anim_in_left, R.anim.anim_out_left);
    }

    public void animationRecycler(View viewT) {
        startActivity(new Intent(this, RecyclerViewActivity.class));
        overridePendingTransition(R.anim.anim_in_left, R.anim.anim_out_left);
    }

    public void setPagerTo(View v) {
        startActivity(new Intent(this, FragmentViewPager.class));
        overridePendingTransition(R.anim.anim_in_left, R.anim.anim_out_left);
    }
}
