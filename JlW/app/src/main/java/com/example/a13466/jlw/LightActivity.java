package com.example.a13466.jlw;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a13466.jlw.db.LightMgt;
import com.example.a13466.jlw.util.HttpPostThread;
import com.example.a13466.jlw.util.MyJsonUtil;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class LightActivity extends AppCompatActivity {
    public static final String TAG = "***Tuxzx";

    private Spinner mSpSelectorLight;
    private Button mBtnQueryLight;
    private RecyclerView mRecyclerViewLight;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<String> spinnerList;

    private Handler handler;
    private Button mBtnBack;
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        initView();
        mTvTitle.setText("红绿灯查询");
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBtnQueryLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerViewLight.setVisibility(View.VISIBLE);
                String selected = mSpSelectorLight.getSelectedItem().toString();
                Log.d(TAG, "onClick: " + selected);
                if (selected == "红灯升序") {
                    //requestData();
                    Log.d(TAG, "run: progress succeed");
                    RecyclerViewAdapter.order = 0;
                    recyclerViewAdapter.notifyDataSetChanged();
                }
                if (selected == "绿灯升序") {
                    //requestData();
                    RecyclerViewAdapter.order = 1;
                    recyclerViewAdapter.notifyDataSetChanged();
                }
                if (selected == "黄灯升序") {
                   // requestData();
                    RecyclerViewAdapter.order = 2;
                    recyclerViewAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void requestData() {
        Log.d(TAG, "request: success");
        //final ProgressDialog progressDialog = ProgressDialog.show(this, "", "loading...", true, true);
        String url = "http://10.31.231.85:8080/transportservice/type/jason/action/GetTrafficLightConfigAction.do";
        // 线程依此执行
        for (int i = 1; i <= 5; i++) {
            final HttpPostThread thread = new HttpPostThread(url, "{\"TrafficLightId\":" + i + "}");
            final int finalI = i;
            thread.setmPostCallback(new HttpPostThread.PostCallback() {
                @Override
                public void handleData(String response) {
                    Log.d(TAG, "handleData: success");
                    // 请求数据成功 解析JSON入库
                    if (response != "null") {
                        MyJsonUtil.handleJson(response, finalI);
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (finalI == 1) {
                                    Toast.makeText(LightActivity.this, "查询信息失败，请稍后再试", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            });
            thread.start();
            try {
                // 依次执行 等待
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                Log.d(TAG, "requestData: cencel");
//                progressDialog.dismiss();
//            }
//        }, 1000);

    }

    private void initView() {
        //progressBar = (ProgressBar) findViewById(R.id.progressbar);
        mSpSelectorLight = (Spinner) findViewById(R.id.light_sp_selector);
        mBtnQueryLight = (Button) findViewById(R.id.light_btn_query);
        mRecyclerViewLight = (RecyclerView) findViewById(R.id.light_recyclerView);
        spinnerList = new ArrayList<>();
        spinnerList.add("红灯升序");
        spinnerList.add("绿灯升序");
        spinnerList.add("黄灯升序");
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mSpSelectorLight.setAdapter(spinnerAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewLight.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter();
        mRecyclerViewLight.setAdapter(recyclerViewAdapter);
        mRecyclerViewLight.setVisibility(View.GONE);
        //progressBar.setVisibility(View.GONE);
        mBtnBack = (Button) findViewById(R.id.btn_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
    }
}

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public static int order = 0;

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView roadName;
        public TextView lightRed;
        public TextView lightGreen;
        public TextView lightYellow;

        public ViewHolder(View itemView) {
            super(itemView);
            roadName = (TextView) itemView.findViewById(R.id.light_tv_road);
            lightRed = (TextView) itemView.findViewById(R.id.light_tv_red);
            lightGreen = (TextView) itemView.findViewById(R.id.light_tv_green);
            lightYellow = (TextView) itemView.findViewById(R.id.light_tv_yellow);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.light_item, parent, false);
        ViewHolder holder = new ViewHolder(rootView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 数据库有数据
        if (DataSupport.count(LightMgt.class) > 0) {
            List<LightMgt> list = null;
            if (order == 0) {
                list = DataSupport.order("redtime").find(LightMgt.class);
            }
            if (order == 1) {
                list = DataSupport.order("greentime").find(LightMgt.class);
            }
            if (order == 2) {
                list = DataSupport.order("yellowtime").find(LightMgt.class);
            }

            LightMgt lightMgt = list.get(position);
            //Log.d(TAG, "onBindViewHolder: "+lightMgt.getRoadName()+" "+lightMgt.getRedTime()+" "+lightMgt.getGreenTime()+" "+lightMgt.getYellowTime());
            holder.roadName.setText(lightMgt.getRoadName() + "");
            holder.lightRed.setText(lightMgt.getRedTime() + "");
            holder.lightGreen.setText(lightMgt.getGreenTime() + "");
            holder.lightYellow.setText(lightMgt.getYellowTime() + "");
        } else {
            //Log.d(TAG, "onBindViewHolder: 没有数据");
            holder.roadName.setText("请求失败");
            holder.lightRed.setText("请求失败");
            holder.lightGreen.setText("请求失败");
            holder.lightYellow.setText("请求失败");
        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}