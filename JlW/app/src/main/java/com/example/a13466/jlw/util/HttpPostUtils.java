package com.example.a13466.jlw.util;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by tuxzx on 2018/3/13.
 */

public class HttpPostUtils {
    static String responseText = null;
    public static String HttpPost(String address, String postJson) {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
        RequestBody body = RequestBody.create(JSON,postJson);
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            responseText = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseText;
    }
}
