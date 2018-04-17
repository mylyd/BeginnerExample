package com.example.thishellotest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 13466 on 2018/1/17.
 */

public class DialogActivity extends DialogFragment{
    private EditText acc1,acc2,acc3,acc4,pas;

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager,"DialogActivity");
    }



    public interface Callback{
        void onClick(String S1,String S2);
    }

    private Callback callback;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //添加到AlertDialog.Builder中
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        final View view=inflater.inflate(R.layout.internet,null);
        //

       /*Dialog dialog=new Dialog(getActivity());
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
        lp.x = 500; // 新位置X坐标
        lp.y = 500; // 新位置Y坐标
        lp.width = 800; // 宽度
        lp.height = 100; // 高度
        lp.alpha = 1.7f; // 透明度
        dialogWindow.setAttributes(lp);*/

        //
        builder.setView(view)
                //Dialog中的响应确定Button
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (callback != null) {
                            acc1 = (EditText) view.findViewById(R.id.edit_1);
                            acc2 = (EditText) view.findViewById(R.id.edit_2);
                            acc3 = (EditText) view.findViewById(R.id.edit_3);
                            acc4=(EditText)view.findViewById(R.id.edit_4) ;
                            pas = (EditText) view.findViewById(R.id.internet_port);
                            callback.onClick(acc1.getText().toString()+"."+acc2.getText().toString()+"."+acc3.getText().toString()+"."+acc4.getText().toString(), pas.getText().toString());
                        }
                    }
                })
                //取消按钮
                .setNegativeButton("取消",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "取消网络设置", Toast.LENGTH_SHORT).show();
                    }
                });
              return builder.create();
    }
        @Override
    public void onAttach(Context context){
            super.onAttach(context);
            if (context instanceof Callback){
                callback=(Callback)context;
            }else {
                throw new RuntimeException(context.toString()+"must implement Callback");
            }
        }

        @Override
    public void onDestroy(){
            super.onDestroy();
            callback=null;
        }
}
