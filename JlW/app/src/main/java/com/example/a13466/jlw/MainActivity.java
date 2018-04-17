package com.example.a13466.jlw;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a13466.jlw.db.Envir;
import com.example.a13466.jlw.db.LightMgt;
import com.example.a13466.jlw.db.StopInfo;
import com.example.a13466.jlw.util.HttpPostThread;
import com.example.a13466.jlw.util.MyJsonUtil;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "MAINACTIVITY";

    private DrawerLayout mDrawerLayout;
    private Button mButtonBackHomepage;
    private TextView mParameterPm25Homepage;
    private TextView mParameterTemperatureHomepage;
    private TextView mStation1Transit1Homepage;
    private TextView mStation1Transit2Homepage;
    private TextView mParameterHumidityHomepage;
    private TextView mParameterCO2Homepage;
    private TextView mStation2Transit1Homepage;
    private TextView mStation2Transit2Homepage;
    private Button mDrawerItemBtnMyCarHomepage;
    private Button mDrawerItemBtnMyRoadConditionHomepage;
    private Button mDrawerItemBtnMyParkingHomepage;
    private Button mDrawerItemBtnMyTransitQueryHomepage;
    private Button mDrawerItemBtnMyEnvironmentHomepage;
    private Button mDrawerItemBtnSetupHomepage;
    private Button mDrawerItemBtnMyOriginalityHomepage;

    private MyBroadcastReceiver mBroadcastReceiver;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private boolean isFirstShow = true;
    private TextView mStop1Bus1;
    private TextView mStop1Bus2;
    private TextView mStop2Bus1;
    private TextView mStop2Bus2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        animation();
        // 由于服务器未接入公网 直接填充数据至数据库
        initdata();

        mDrawerLayout.setScrimColor(Color.argb(1, 0, 0, 0));//抽屉拉出后主页面不遮挡（透明）
        queryEnvir();
        queryStopInfo();
    }

    private void initdata() {
        for (int i = 1;i<=5;i++) {
            LightMgt lightMgt = new LightMgt();
            lightMgt.setId(i);
            lightMgt.setRoadName(String.valueOf(i));
            lightMgt.setRedTime(i);
            lightMgt.setGreenTime(i);
            lightMgt.setYellowTime(i);
            lightMgt.saveOrUpdate("roadname=?",String.valueOf(i));
        }
        StopInfo stopInfo = new StopInfo();
        stopInfo.setStopName(1);
        stopInfo.setBus(0);
        stopInfo.setDistance((1001));
        stopInfo.saveOrUpdate("bus = ? and stopname = ?", String.valueOf(0), String.valueOf(1));
        StopInfo stopInfo1 = new StopInfo();
        stopInfo1.setStopName(1);
        stopInfo1.setBus(1);
        stopInfo1.setDistance((1002));
        stopInfo1.saveOrUpdate("bus = ? and stopname = ?", String.valueOf(1), String.valueOf(1));
        StopInfo stopInfo2 = new StopInfo();
        stopInfo2.setStopName(2);
        stopInfo2.setBus(0);
        stopInfo2.setDistance((2001));
        stopInfo2.saveOrUpdate("bus = ? and stopname = ?", String.valueOf(0), String.valueOf(2));
        StopInfo stopInfo3 = new StopInfo();
        stopInfo3.setStopName(2);
        stopInfo3.setBus(1);
        stopInfo3.setDistance((2002));
        stopInfo3.saveOrUpdate("bus = ? and stopname = ?", String.valueOf(1), String.valueOf(2));
        Envir envir = new Envir();
        envir.setTemperature(30);
        envir.setHumidity(40);
        envir.setPm25(200);
        envir.setCo2(50);
        envir.setLightIntensity(70);
        envir.saveOrUpdate("id=?",String.valueOf(1));

    }

    private void queryEnvir() {
        if (DataSupport.count(Envir.class) > 0) {
            List<Envir> list = DataSupport.where("id =?", String.valueOf(1)).find(Envir.class);
            Envir envir = list.get(0);
            mParameterPm25Homepage.setText(envir.getPm25() + "");
            mParameterTemperatureHomepage.setText(envir.getTemperature() + "");
            mParameterHumidityHomepage.setText(envir.getHumidity() + "");
            mParameterCO2Homepage.setText(envir.getCo2() + "");
        } else {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "未请求到服务器", Toast.LENGTH_SHORT).show();
                        }
                    });
                    //request();
                    queryEnvir();
                    this.cancel();
                }
            },5000);
        }
    }
//    public void request() {
//        String url = "http://10.31.231.85:8080/transportservice/type/jason/action/GetBusStationInfo.do";
//        HttpPostThread thread1 = new HttpPostThread(url,"{\"BusStationId\":" + 1 + "}");
//        thread1.setmPostCallback(new HttpPostThread.PostCallback() {
//            @Override
//            public void handleData(String response) {
//                Log.d(TAG, "handleData1: "+response);
//                MyJsonUtil.handleStopJson(response,1);
//            }
//        });
//        thread1.start();
//        try {
//            thread1.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        HttpPostThread thread2 = new HttpPostThread(url,"{\"BusStationId\":" + 2 + "}");
//        thread2.setmPostCallback(new HttpPostThread.PostCallback() {
//            @Override
//            public void handleData(String response) {
//               // Log.d(TAG, "handleData2: "+response);
//                MyJsonUtil.handleStopJson(response,2);
//            }
//        });
//        thread2.start();
//        try {
//            thread2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        HttpPostThread thread3 = new HttpPostThread("http://10.31.231.85:8080/transportservice/type/jason/action/%20GetAllSense.do",
//                "null");
//        thread3.setmPostCallback(new HttpPostThread.PostCallback() {
//            @Override
//            public void handleData(String response) {
//               // Log.d(TAG, "handleData3: "+response);
//                MyJsonUtil.handleEnvirJson(response);
//            }
//        });
//        thread3.start();
//
//    }

    private void queryStopInfo() {
        if (DataSupport.count(StopInfo.class)>0) {
            StopInfo s1bus1 = DataSupport.where("bus = ? and stopname = ?", String.valueOf(0), "1").findLast(StopInfo.class);
            StopInfo s1bus2 = DataSupport.where("bus = ? and stopname = ?", String.valueOf(1), "1").findLast(StopInfo.class);
            StopInfo s2bus1 = DataSupport.where("bus = ? and stopname = ?", String.valueOf(0), "2").findLast(StopInfo.class);
            StopInfo s2bus2 = DataSupport.where("bus = ? and stopname = ?", String.valueOf(1), "2").findLast(StopInfo.class);

            mStop1Bus1.setText(s1bus1.getDistance() + "");
            mStop1Bus2.setText(s1bus2.getDistance() + "");
            mStop2Bus1.setText(s2bus1.getDistance() + "");
            mStop2Bus2.setText(s2bus2.getDistance() + "");
        }else {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "未请求到服务器", Toast.LENGTH_SHORT).show();
                        }
                    });
                    //request();
                    queryStopInfo();
                    this.cancel();
                }
            },5000);
        }
    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mButtonBackHomepage = (Button) findViewById(R.id.btn_back);
        mButtonBackHomepage.setOnClickListener(this);
        mParameterPm25Homepage = (TextView) findViewById(R.id.homepage_parameter_pm25);
        mParameterTemperatureHomepage = (TextView) findViewById(R.id.homepage_parameter_temperature);
        mParameterHumidityHomepage = (TextView) findViewById(R.id.homepage_parameter_humidity);
        mParameterCO2Homepage = (TextView) findViewById(R.id.homepage_parameter_CO2);
        //DrawerLayout内置btn
        mDrawerItemBtnMyCarHomepage = (Button) findViewById(R.id.homepage_drawer_item_btn_MyCar);
        mDrawerItemBtnMyCarHomepage.setOnClickListener(this);
        mDrawerItemBtnMyRoadConditionHomepage = (Button) findViewById(R.id.homepage_drawer_item_btn_MyRoadCondition);
        mDrawerItemBtnMyRoadConditionHomepage.setOnClickListener(this);
        mDrawerItemBtnMyParkingHomepage = (Button) findViewById(R.id.homepage_drawer_item_btn_MyParking);
        mDrawerItemBtnMyParkingHomepage.setOnClickListener(this);
        mDrawerItemBtnMyTransitQueryHomepage = (Button) findViewById(R.id.homepage_drawer_item_btn_MyTransitQuery);
        mDrawerItemBtnMyTransitQueryHomepage.setOnClickListener(this);
        mDrawerItemBtnMyEnvironmentHomepage = (Button) findViewById(R.id.homepage_drawer_item_btn_MyEnvironment);
        mDrawerItemBtnMyEnvironmentHomepage.setOnClickListener(this);
        mDrawerItemBtnSetupHomepage = (Button) findViewById(R.id.homepage_drawer_item_btn_Setup);
        mDrawerItemBtnSetupHomepage.setOnClickListener(this);
        mDrawerItemBtnMyOriginalityHomepage = (Button) findViewById(R.id.homepage_drawer_item_btn_MyOriginality);
        mDrawerItemBtnMyOriginalityHomepage.setOnClickListener(this);


        mStop1Bus1 = (TextView) findViewById(R.id.homepage_stop1_bus1);
        mStop1Bus2 = (TextView) findViewById(R.id.homepage_stop1_bus2);
        mStop2Bus1 = (TextView) findViewById(R.id.homepage_stop2_bus1);
        mStop2Bus2 = (TextView) findViewById(R.id.homepage_stop2_bus2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                // TODO 18/03/13
                mDrawerLayout.openDrawer(Gravity.RIGHT);//打开右边抽屉
                //Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
                break;
            case R.id.homepage_drawer_item_btn_MyCar:
                // TODO 18/03/13 我的座驾
                Intent intentmyCar = new Intent(MainActivity.this, MyCarFragmentActivity.class);

                startActivity(intentmyCar);


                //mDrawerLayout.closeDrawer(Gravity.RIGHT);//关闭抽屉
                break;
            case R.id.homepage_drawer_item_btn_MyRoadCondition:
                // TODO 18/03/13 我的路况
                Intent intentLight = new Intent(MainActivity.this, LightActivity.class);
                startActivity(intentLight);

                //mDrawerLayout.closeDrawer(Gravity.RIGHT);//关闭抽屉
                break;
            case R.id.homepage_drawer_item_btn_MyParking:
                // TODO 18/03/13 停车查询
                Intent intentParking = new Intent(MainActivity.this, ParkingQueryActivity.class);
                startActivity(intentParking);

                //mDrawerLayout.closeDrawer(Gravity.RIGHT);//关闭抽屉
                break;
            case R.id.homepage_drawer_item_btn_MyTransitQuery:
                // TODO 18/03/13 公交查询
                Intent stopIntent = new Intent(MainActivity.this, StopIssusActivity.class);
                startActivity(stopIntent);

                //mDrawerLayout.closeDrawer(Gravity.RIGHT);//关闭抽屉
                break;
            case R.id.homepage_drawer_item_btn_MyEnvironment:
                // TODO 18/03/13 道路环境
                Toast.makeText(this, mDrawerItemBtnMyEnvironmentHomepage.getText(), Toast.LENGTH_SHORT).show();

                //mDrawerLayout.closeDrawer(Gravity.RIGHT);//关闭抽屉
                break;
            case R.id.homepage_drawer_item_btn_Setup:
                // TODO 18/03/13 设置
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                //mDrawerLayout.closeDrawer(Gravity.RIGHT);//关闭抽屉
                break;
            case R.id.homepage_drawer_item_btn_MyOriginality:
                // TODO 18/03/15 //创意设计
                Intent intent1 = new Intent(this, OriginalityActivity.class);
                startActivity(intent1);
                //mDrawerLayout.closeDrawer(Gravity.RIGHT);//关闭抽屉
                break;
            default:
                break;
        }
    }

    /**
     * 这里是DrawerLayout的动画
     */
    private void animation() {
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //这里是写动画的
                View content = mDrawerLayout.getChildAt(0);
                int offset = (int) (drawerView.getWidth() * slideOffset);
                content.setTranslationX(-offset);//+为左边，-为右边
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                //这里是写打开
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                //这里关闭

            }

            @Override
            public void onDrawerStateChanged(int newState) {
                //菜单切换状态变化时回调的方法
            }
        });

    }


    /**
     * 重置 Dialog 状态
     * 注册广播
     */
    @Override
    protected void onResume() {
        super.onResume();
        isFirstShow = true;

        //注册广播
        mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(mBroadcastReceiver, intentFilter);
        //关闭抽屉
        mDrawerLayout.closeDrawer(Gravity.RIGHT);
    }

    /**
     * 销毁广播
     */
    @Override
    protected void onPause() {
        super.onPause();
        //关闭抽屉
        //mDrawerLayout.closeDrawer(Gravity.RIGHT);
        overridePendingTransition(R.anim.push_in_left, R.anim.push_out_left);
        //销毁在onResume()方法中的广播
        unregisterReceiver(mBroadcastReceiver);
    }



    /**
     * 显示网络状态变化 Dialog
     *
     * @param str 显示的内容
     */
    private void showDialog(String str) {
        //第一次进入不会显示
        if (isFirstShow) {
            isFirstShow = false;
            return;
        }
        //如果Dialog 在显示就先关闭
        if (dialog != null) {
            if (dialog.isShowing()) dialog.dismiss();
        }
        builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_net, null);
        builder.setView(view).create();
        TextView dialogTv = (TextView) view.findViewById(R.id.dialog_content);
        Button dialogBtn = (Button) view.findViewById(R.id.dialog_btn);
        dialogTv.setText(str);
        dialog = builder.show();
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    /**
     * 对接收到的广播进行处理
     */
    private class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String str = intent.getAction();
            if (str.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                ConnectivityManager connectivityManager = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                String state;
                if (networkInfo != null && networkInfo.isAvailable()) {
                    state = "网络已连接";
                } else {
                    state = "网络已断开";
                }
                showDialog(state);
            }
        }
    }


    private long firstBack = 0;

    /**
     * 返回键监听
     * 两次点击返回键时间小于2秒退出程序
     */
    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - firstBack) < 2000) {
            finish();
        } else {
            Toast.makeText(this, "快速点击两次返回键退出", Toast.LENGTH_SHORT).show();
            firstBack = System.currentTimeMillis();
        }
    }
}
