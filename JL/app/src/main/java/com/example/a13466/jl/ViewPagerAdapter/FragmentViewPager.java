package com.example.a13466.jl.ViewPagerAdapter;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.a13466.jl.R;
import com.example.a13466.jl.db.BookEnvironmental;

import org.json.JSONArray;
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

public class FragmentViewPager extends FragmentActivity {
    private List<Fragment> mFragment;
    private FragmentViewPagerAdapter mFragmentViewPagerAdapter;
    private ViewPager mVP;
    private Handler handler;
    private ProgressDialog pd;
    private BookEnvironmental be;
    int a,b,c,d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_view_pager);
        handler=new Handler();
        onViewPagerAdapter();
        setDialog();
        NetRequest();

    }

    /**
     * ViewPager
     */
    private void onViewPagerAdapter(){
        //构建适配器
        mFragment=new ArrayList<Fragment>();
        mFragment.add(new FragmentPager_1());
        mFragment.add(new FragmentPager_2());
        mFragmentViewPagerAdapter=new FragmentViewPagerAdapter(getSupportFragmentManager(),mFragment);
        //创建适配器
        mVP= (ViewPager) findViewById(R.id.view_pager);
        mVP.setAdapter(mFragmentViewPagerAdapter);
    }

    public void onPagerBack(View v){
        finish();
        overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_right);
    }

    /**
     * 执行线程
      */
    private void NetRequest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=2;i++){
                        parseJSON(request(i),i);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //执行线程处理结束后的逻辑
                        setupData();
                        pd.dismiss();
                        //清除数据库
                        BookEnvironmental bbb=DataSupport.findLast(BookEnvironmental.class);
                        DataSupport.deleteAll(BookEnvironmental.class,"id<?",(bbb.getId()-5)+"");
                    }
                });
            }
        }).start();
    }

    private void setDialog(){
        pd=new ProgressDialog(this);
        pd.setMessage("正在加载......");
        pd.setCancelable(false);
        pd.show();
    }
    /**
     * 网络请求
     * @param img
     */
    private String request(int img) {
        OkHttpClient client=new OkHttpClient();
        MediaType type=MediaType.parse("application/json;charset=utf-8");
        RequestBody rb=RequestBody.create(type,"{\"BusStationId\":"+img+"}");
        Request rq=new Request.Builder()
                .url("http://"+getString(R.string.url)+"/transportservice/type/jason/action/GetBusStationInfo.do")
                .post(rb)
                .build();
        Call call = client.newCall(rq);
        String str = "";
        try{
            Response rp = call.execute();
            str = rp.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    private void parseJSON(String str, int num){
        try {
            JSONObject jsonObject = new JSONObject(str);
            String json = jsonObject.getString("serverinfo");
            JSONArray JSON = new JSONArray(json);
            JSONObject j1 = JSON.getJSONObject(0);
            JSONObject j2 = JSON.getJSONObject(1);

            if (num==1){
                a=j1.getInt("Distance");
                b=j2.getInt("Distance");
            }
            if (num==2){
                c=j1.getInt("Distance");
                d=j2.getInt("Distance");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加数据
     */
    private void setupData(){
        be=new BookEnvironmental();
        be.setDistance_car_11(a);
        be.setDistance_car_12(b);
        be.setDistance_car_21(c);
        be.setDistance_car_22(d);
        be.save();
    }
}
