package com.example.a13466.jlw.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.a13466.jlw.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class MyCarFragmentControl extends Fragment {
    private Spinner mFragmentControlSpinnerMyCar;
    private Switch mFragmentControlSwitchMyCar;
    int Carnum;
    boolean CarisChecked;
    private SharedPreferences pref;
    public static final int OK = 1;
    public static final int NO = 2;


    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case OK:
                    if (CarisChecked) {
                        Toast.makeText(getActivity(), Carnum + "号小车已启动", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), Carnum + "号小车已停止", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case NO:
                    Toast.makeText(getActivity(), "小车启停操作失败", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_mycar_fragment_control, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setSwitch();
        setSpinner();
    }

    private void setSwitch() {
        mFragmentControlSwitchMyCar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Carnum = Integer.parseInt(mFragmentControlSpinnerMyCar.getSelectedItem().toString());
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("SwitchState", Context.MODE_PRIVATE).edit();
                if (Carnum==1){
                    if (isChecked){
                        editor.putBoolean("isCheck_1", true);
                        outOkhttp(Carnum, "move");
                        CarisChecked=isChecked;
                    }else {
                        editor.putBoolean("isCheck_1", false);
                        outOkhttp(Carnum, "stop");
                        CarisChecked=isChecked;
                    }
                }
                if (Carnum==2){
                    if (isChecked){
                        editor.putBoolean("isCheck_2", true);
                        outOkhttp(Carnum, "move");
                        CarisChecked=isChecked;
                    }else {
                        editor.putBoolean("isCheck_2", false);
                        outOkhttp(Carnum, "stop");
                        CarisChecked=isChecked;
                    }
                }
                if (Carnum==3){
                    if (isChecked){
                        editor.putBoolean("isCheck_3", true);
                        outOkhttp(Carnum, "move");
                        CarisChecked=isChecked;
                    }else {
                        editor.putBoolean("isCheck_3", false);
                        outOkhttp(Carnum, "stop");
                        CarisChecked=isChecked;
                    }
                }
                if (Carnum==4){
                    if (isChecked){
                        editor.putBoolean("isCheck_4", true);
                        outOkhttp(Carnum, "move");
                        CarisChecked=isChecked;
                    }else {
                        editor.putBoolean("isCheck_4", false);
                        outOkhttp(Carnum, "stop");
                        CarisChecked=isChecked;
                    }
                }
                editor.apply();
            }
        });
    }
    private void initView() {
        mFragmentControlSpinnerMyCar = (Spinner) getActivity().findViewById(R.id.MyCar_Fragment_control_spinner);
        mFragmentControlSwitchMyCar = (Switch) getActivity().findViewById(R.id.MyCar_Fragment_control_switch);
    }

    private void outOkhttp(int carid, String caraction) {
        OkHttpClient client = new OkHttpClient();
        MediaType type = MediaType.parse("application/json;charset=utf-8");
       // Log.d(TAG,  "{\"CarId\":" + carid + ",\"CarAction\":\"" + caraction + "\"}");
        RequestBody requestBody = RequestBody.create(type, "{\"CarId\":" + carid + ",\"CarAction\":\"" + caraction + "\"}");
        Request request = new Request.Builder()
                .url("http://10.31.231.85:8080/transportservice/type/jason/action/SetCarMove.do")
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //失败
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //成功
                String str = response.body().string();
                try {
                    Message okk = new Message();
                    JSONObject jsonobject = new JSONObject(str);
                    String Result = jsonobject.getString("serverinfo");
                    JSONObject object = new JSONObject(Result);
                    String t = object.getString("result");
                   // Log.d(TAG, "T: "+t);
                    if (t.equals("ok")) {
                        okk.what = OK;
                       // Log.d(TAG, "ttttttttt: "+t);
                    } else {
                        okk.what = NO;
                    }
                    handler.sendMessage(okk);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setSpinner() {
        mFragmentControlSpinnerMyCar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setSwicthstate(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setSwicthstate(int itemId) {
        pref = getActivity().getSharedPreferences("SwitchState", Context.MODE_PRIVATE);
        boolean btn_1=pref.getBoolean("isCheck_1",false);
        boolean btn_2=pref.getBoolean("isCheck_2",false);
        boolean btn_3=pref.getBoolean("isCheck_3",false);
        boolean btn_4=pref.getBoolean("isCheck_4",false);
        if (itemId == 1) {
            mFragmentControlSwitchMyCar.setChecked(btn_1);
        }
        if (itemId == 2) {
            mFragmentControlSwitchMyCar.setChecked(btn_2);
        }
        if (itemId == 3) {
            mFragmentControlSwitchMyCar.setChecked(btn_3);
        }
        if (itemId == 4) {
            mFragmentControlSwitchMyCar.setChecked(btn_4);
        }
    }


}
