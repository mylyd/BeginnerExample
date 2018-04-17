package com.example.a13466.jl;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a13466.jl.RecyclerAdapter.Light;
import com.example.a13466.jl.RecyclerAdapter.LightAdapter;
import com.example.a13466.jl.db.BookRecycler;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RecyclerViewActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Light> mLight = new ArrayList<Light>();
    private Button mBtnQuery;
    private Handler handler;
    private ProgressDialog p;
    private Button mBtn1RecyclerView;
    private RecyclerView recyclerView;
    int F=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initView();

        // setRecyclerView();
    }

    /**
     * RecyclerView实例
     */
    private void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        llm.setReverseLayout(true);//列表翻转
        llm.setStackFromEnd(true);//列表再底部开始展示，反转后由上面开始展示
        LightAdapter lightAdapter = new LightAdapter(setData());
        recyclerView.setAdapter(lightAdapter);
    }

    /**
     * RecyclerView 内容提供器
     *
     * @return
     */
    private List<Light> setData() {
        int count = DataSupport.count(BookRecycler.class);
        if (count > 3) {
            List<BookRecycler> rb = DataSupport.order("id desc").limit(4).find(BookRecycler.class);
            for (BookRecycler r : rb) {
                int c = r.getCarnum();
                int red = r.getRedtime();
                int green = r.getGreentime();
                int yellow = r.getYellowtime();
                mLight.add(new Light(c + "号路口", red, green, yellow));
            }
            if (F==2){
                Toast.makeText(this, "刷新成功", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "加载成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "提取失败", Toast.LENGTH_SHORT).show();
        }
        return mLight;
    }


    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.anim_in_right, R.anim.anim_out_right);
    }

    public void animationText(View v) {
        onBackPressed();
    }


    private void NetRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 5; i++) {
                    parseJSON(request(i), i);
                    Log.d("11111111111111111111", "run: " + i);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        p.dismiss();
                        setRecyclerView();
                    }
                });
            }
        }).start();
    }

    /**
     * 请求数据
     *
     * @param num
     */
    private String request(int num) {
        OkHttpClient client = new OkHttpClient();
        MediaType type = MediaType.parse("application/json;charset=utf-8");
        RequestBody requestBody = RequestBody.create(type, "{\"TrafficLightId\":" + num + "}");
        Request request = new Request.Builder()
                .url("http://" + getString(R.string.url) + "/transportservice/type/jason/action/GetTrafficLightConfigAction.do")
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        String str = "";
        try {
            Response response = call.execute();
            str = response.body().string();
            Log.d("11111111111111111111", "服务器返回的数据: " + str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 解析数据
     *
     * @param string
     * @param table
     */
    private void parseJSON(String string, int table) {
        try {
            JSONObject jsonObject = new JSONObject(string);
            String json = jsonObject.getString("serverinfo");
            JSONObject JSON = new JSONObject(json);
            BookRecycler rb = new BookRecycler();
            rb.setCarnum(table);
            rb.setRedtime(JSON.getInt("RedTime"));
            rb.setGreentime(JSON.getInt("GreenTime"));
            rb.setYellowtime(JSON.getInt("YellowTime"));
            rb.save();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("11111111111111111111", "parseJSON:解析异常");
        }
    }

    private void initView() {
        mBtnQuery = (Button) findViewById(R.id.RecyclerView_Btn_query);
        mBtnQuery.setOnClickListener(this);
        handler = new Handler();
        mBtn1RecyclerView = (Button) findViewById(R.id.RecyclerView_Btn_1);
        mBtn1RecyclerView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.RecyclerView_Btn_query:
                // TODO 18/03/20
                if (recyclerView!=null) {
                    mLight.clear();
                    F=2;
                }
                    NetRequest();
                    mDialog();
                break;
            case R.id.RecyclerView_Btn_1:// TODO 18/03/21
                int ccc=DataSupport.count(BookRecycler.class);
                if (ccc!=0){
               List<BookRecycler> rb = DataSupport.findAll(BookRecycler.class);
                for (BookRecycler r : rb) {
                    int c = r.getCarnum();
                    int red = r.getRedtime();
                    int green = r.getGreentime();
                    int yellow = r.getYellowtime();
                    Log.d("11111111111111111111", "数据库：" + c + "         " + red + "         " + green + "         " + yellow);
                }
                BookRecycler rl=DataSupport.findLast(BookRecycler.class);
                Log.d("11111111111111111111", "最后一条id："+rl.getId());
                }else {
                    Toast.makeText(this, "数据库为空", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataSupport.deleteAll(BookRecycler.class);
    }

    private void mDialog() {
        p = new ProgressDialog(this);
        p.setMessage("正在加载......");
        p.setCancelable(false);
        p.show();
    }
}
