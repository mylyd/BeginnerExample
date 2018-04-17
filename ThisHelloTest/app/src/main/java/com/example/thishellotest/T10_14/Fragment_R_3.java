package com.example.thishellotest.T10_14;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.thishellotest.R;

public class Fragment_R_3 extends Fragment implements View.OnClickListener{
    private Button F3_button;
    private TextView F3_text;
    private Button ONOFF;
    private Button Road;
    int t=0,f=0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment__r_3, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vvv();
    }

    private void vvv(){
        F3_button=(Button)getActivity().findViewById(R.id.F3_Recharge_chaxun);
        ONOFF=(Button)getActivity().findViewById(R.id.F3_ON_OFF);
        Road=(Button)getActivity().findViewById(R.id.F3_Road);
        F3_text=(TextView)getActivity().findViewById(R.id.F3_Text);

        ONOFF.setOnClickListener(this);
        Road.setOnClickListener(this);
        F3_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.F3_Road:
                //道路信息
                new AlertDialog.Builder(getActivity()).setSingleChoiceItems(new String[]{"1号道路", "2号道路","3号道路"}, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==0){
                            //1号道路
                              f=0;
                            Road.setText("道路信息(1路)");
                            dialog.dismiss();//退出Dialog
                        }
                        if (which==1){
                            //2号道路
                              f=1;
                            Road.setText("道路信息(2路)");
                            dialog.dismiss();
                        }
                        if (which==2){
                            //3号道路
                             f=2;
                            Road.setText("道路信息(3路)");
                            dialog.dismiss();
                        }
                    }
                }).show();

                break;
            case R.id.F3_Recharge_chaxun:
                //查询
                if (t==0&&f==0){
                    F3_text.setText("1路路灯(开)");
                }
                if (t==1&&f==0){
                    F3_text.setText("1路路灯(关)");
                }
                if (t==0&&f==1){
                    F3_text.setText("2路路灯(开)");
                }
                if (t==1&&f==1){
                    F3_text.setText("2路路灯(关)");
                }
                if (t==0&&f==2){
                    F3_text.setText("3路路灯(开)");
                }
                if (t==1&&f==2){
                    F3_text.setText("3路路灯(关)");
                }
                break;
            case R.id.F3_ON_OFF:
                //路灯开关
                new AlertDialog.Builder(getActivity()).setSingleChoiceItems(new String[]{"打开", "关闭"}, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==0){
                            //打开
                            t=0;
                            dialog.dismiss();//退出Dialog
                        }
                        if (which==1){
                            //关闭
                            t=1;
                            dialog.dismiss();
                        }

                    }
                }).show();
                break;

        }
    }
}
