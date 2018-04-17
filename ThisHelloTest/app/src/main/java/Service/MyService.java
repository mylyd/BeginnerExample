package Service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SyncContext;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.IntDef;
import android.util.Log;

import com.example.thishellotest.BookFourth;
import com.example.thishellotest.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyService extends Service {

    private void netRequest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    /*String url="http://192.168.123.207:8080/transportservice/type/jason/action/GetAllSense.do";
                    HttpUtil.requestData(url, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.d("MyService","错误----异常-----注意");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseText= response.body().string();
                            parseData(responseText);
                        }
                    });*/
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder()
                            .url("http://10.31.231.85:8080/transportservice/type/jason/action/GetAllSense.do")
                            .build();
                    Response response=client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String responseData=response.body().string();//从服务器获取的未解析的数据；
                        Log.d("MyService", "run: "+responseData);
                        parseData(responseData);
                    } else {
                        Log.d("MyService", "run: 请求失败");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseData(String jsonData){
        try {
            JSONObject jsonObject=new JSONObject(jsonData);
            String str=jsonObject.getString("serverinfo");
            JSONObject object=new JSONObject(str);

            int count= DataSupport.count(BookFourth.class);
            BookFourth bookFourth=new BookFourth();

            if (count<36){
                bookFourth.setTemperature(object.getInt("temperature"));
                bookFourth.setHumidity(object.getInt("humidity"));
                bookFourth.setLight(object.getInt("LightIntensity"));
                bookFourth.setCo2(object.getInt("co2"));
                bookFourth.setPm25(object.getInt("pm2.5"));
                bookFourth.save();
            }else {//数据库保存36条数据，多余的删除；
                long last=DataSupport.findLast(BookFourth.class).getId();
                DataSupport.delete(BookFourth.class,last-35);
                bookFourth.setTemperature(object.getInt("temperature"));
                bookFourth.setHumidity(object.getInt("humidity"));
                bookFourth.setLight(object.getInt("LightIntensity"));
                bookFourth.setCo2(object.getInt("co2"));
                bookFourth.setPm25(object.getInt("pm2.5"));
                bookFourth.save();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*

        List<BookFourth> bookFourths=DataSupport.findAll(BookFourth.class);
        for (BookFourth t:bookFourths){
            Log.d("MyService","id:"+t.getId());
            Log.d("MyService","LightIntensity:"+t.getLight());
            Log.d("MyService","temperature:"+t.getTemperature());
            Log.d("MyService","CO2:"+t.getCo2());
            Log.d("MyService","PM 2.5:"+t.getPm25());
        }*/
    }

    /**
     * 设置过5秒就解析储存一次数据
     * @param intent
     * @param flags
     * @param startId
     * @return
     */

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        netRequest();
        AlarmManager manager= (AlarmManager) getSystemService(ALARM_SERVICE);
        int Hour=5000;//5s
        long triggerAtTime= SystemClock.elapsedRealtime()+Hour;
        Intent i=new Intent(this,MyService.class);
        PendingIntent pi=PendingIntent.getService(this,0,i,0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);

        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 退出应用时清空数据库
     */

  /*  @Override
    public void onDestroy() {
        super.onDestroy();
        DataSupport.deleteAll(BookFourth.class);
    }*/

    @Override
    public IBinder onBind(Intent intent) {

       return null;
    }
}
