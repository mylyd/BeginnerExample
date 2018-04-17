package com.example.a13466.jl.Service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.IntDef;
import android.system.ErrnoException;
import android.util.Log;

import com.example.a13466.jl.R;
import com.example.a13466.jl.db.BookVariable;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyService extends Service {
    /**
     * 创建Service时调用
     */
    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * Service启动时调用
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        NetRequest();
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        long timeAT = SystemClock.elapsedRealtime()+5000;
        Intent i = new Intent(this,MyService.class);
        PendingIntent pi = PendingIntent.getService(this,0,i,0);
        if (Build.VERSION.SDK_INT<19){
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,timeAT,pi);
        }else {
            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,timeAT,pi);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 请求JSON数据
     */
    private void NetRequest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder()
                           .url("http://"+getString(R.string.url)+"/transportservice/type/jason/action/GetAllSense.do")
                           // .url("http://119.28.137.50/envir.json")
                            .build();
                    Response response=client.newCall(request).execute();
                    String str=response.body().string();
                    if (response.isSuccessful()){
                        parseJson(str);
                        Log.d("MyService", "-----run:"+str);
                    }else {
                        Log.d("MyService", "-----run: 请求失败");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 解析JSON数据 , 加载进数据库
     */
    private void parseJson(String Json){
            try {
                JSONObject jsonObject=new JSONObject(Json);
                String off=jsonObject.getString("serverinfo");
                Log.d("MyService", "parseJson First: "+off);
                JSONObject json=new JSONObject(off);
                Log.d("MyService", "parseJson First: "+json);
                //保留一分钟的数据
                int count = DataSupport.count(BookVariable.class);
                BookVariable bkv = new BookVariable();
                if (count<=36){
                    bkv.setCO2(json.getInt("co2"));
                    bkv.setPM25(json.getInt("pm2.5"));
                    bkv.setHum(json.getInt("humidity"));
                    bkv.setTem(json.getInt("temperature"));
                }else {
                    long last=DataSupport.findLast(BookVariable.class).getId();
                    DataSupport.delete(BookVariable.class,last-35);
                    bkv.setCO2(json.getInt("co2"));
                    bkv.setPM25(json.getInt("pm2.5"));
                    bkv.setHum(json.getInt("humidity"));
                    bkv.setTem(json.getInt("temperature"));
                }
                bkv.save();
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }
    /**
     * 销毁Service时调用
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
