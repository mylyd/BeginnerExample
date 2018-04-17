package com.example.a13466.jlw.util;

import android.util.Log;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by tuxzx on 2018/3/14.
 */

public class HttpPostThread extends Thread {

    public interface PostCallback{
        void handleData(String response);
    }

    private String address;
    private String strJson;
    private String responseText;
    private PostCallback mPostCallback;

    public void setmPostCallback(PostCallback mPostCallback) {
        this.mPostCallback = mPostCallback;
    }

    public HttpPostThread(String address, String strJson){
        this.address = address;
        this.strJson = strJson;
    }



    @Override
    public void run() {
        request();
    }


    // 请求数据
    private void request(){
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
        RequestBody body = RequestBody.create(JSON,strJson);
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        try {
            try {
                //Response response = client.newCall(request).execute();
                Call call = client.newCall(request);
                Response response = null;
                response = call.execute();
                responseText = response.body().string();
                Log.d("MyHttpThread", "request: "+responseText);
                mPostCallback.handleData(responseText);
            }catch (Exception e){
                e.printStackTrace();
                responseText = "null";
                mPostCallback.handleData(responseText);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
