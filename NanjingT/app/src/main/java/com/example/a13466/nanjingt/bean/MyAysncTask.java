package com.example.a13466.nanjingt.bean;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

public class MyAysncTask extends AsyncTask<Void,Integer,Boolean> {

    private ProgressDialog progressDialog;
    //在执行之前调用，准备，**初始化
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(MyApplication.getContext());
        progressDialog.setMessage("加载中...");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    //在后台运行执行，**子线程
    @Override
    protected Boolean doInBackground(Void... voids) {


        return null;
    }


    //执行结束后，返回结果更新UI  **handel.post();
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        progressDialog.dismiss();
    }
}
