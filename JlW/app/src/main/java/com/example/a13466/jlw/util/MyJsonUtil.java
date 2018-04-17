package com.example.a13466.jlw.util;

import android.content.Context;


import com.example.a13466.jlw.db.Envir;
import com.example.a13466.jlw.db.LightMgt;
import com.example.a13466.jlw.db.StopInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tuxzx on 2018/3/13.
 */

public class MyJsonUtil {
    public static void handleJson(String response, int num) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String info = jsonObject.getString("serverinfo");
            JSONObject subJsonObject = new JSONObject(info);
            LightMgt lightMgt = new LightMgt();
            lightMgt.setRoadName(String.valueOf(num));
            lightMgt.setRedTime(subJsonObject.getInt("RedTime"));
            lightMgt.setGreenTime(subJsonObject.getInt("GreenTime"));
            lightMgt.setYellowTime(subJsonObject.getInt("YellowTime"));
            lightMgt.saveOrUpdate("roadname=?",String.valueOf(num));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void handleStopJson(String response, int num) {
        if (response != "null") {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String info = jsonObject.getString("serverinfo");
                JSONArray jsonArray = new JSONArray(info);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject subJsonObject = jsonArray.getJSONObject(i);
                    StopInfo stopInfo = new StopInfo();
                    stopInfo.setStopName(num);
                    stopInfo.setBus(i);
                    stopInfo.setDistance(subJsonObject.getInt("Distance"));
                    stopInfo.saveOrUpdate("bus = ? and stopname = ?", String.valueOf(i), String.valueOf(num));
                    //SharedPreferencesUtils.putString(context,"stops", "stop"+String.valueOf(num)+"bus"+String.valueOf(i),subJsonObject.getString("Distance"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static void handleEnvirJson(String response) {
        try{
            JSONObject jsonObject = new JSONObject(response);
            String info = jsonObject.getString("serverinfo");
            JSONObject subJsonObject = new JSONObject(info);
            Envir envir = new Envir();
            envir.setTemperature(subJsonObject.getInt("temperature"));
            envir.setHumidity(subJsonObject.getInt("humidity"));
            envir.setPm25(subJsonObject.getInt("pm2.5"));
            envir.setCo2(subJsonObject.getInt("co2"));
            envir.setLightIntensity(subJsonObject.getInt("LightIntensity"));
            envir.saveOrUpdate("id=?",String.valueOf(1));


        }catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static String handleAccountJson(String response, String subItem) {
        JSONObject subJsonObject;
        try {
            JSONObject jsonObject = new JSONObject(response);
            String info = jsonObject.getString("serverinfo");
            subJsonObject = new JSONObject(info);
            return subJsonObject.getString(subItem);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "faild";
    }



}
