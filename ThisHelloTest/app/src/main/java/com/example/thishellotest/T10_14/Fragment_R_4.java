package com.example.thishellotest.T10_14;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thishellotest.R;

public class Fragment_R_4 extends Fragment implements View.OnClickListener{
    private Button bto_f4;
    private TextView text_f4;
    private Spinner spinner_1_f4,spinner_2_f4;
    int F=0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment__r_4, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initvvv();
        Spinner();
    }

    private void initvvv(){
        bto_f4= (Button) getActivity().findViewById(R.id.F4_button_1);
        text_f4= (TextView) getActivity().findViewById(R.id.F4_text);
        spinner_1_f4= (Spinner) getActivity().findViewById(R.id.F4_Spinner_1);
        spinner_2_f4= (Spinner) getActivity().findViewById(R.id.F4_Spinner_2);

        bto_f4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.F4_button_1:
                if (F==0){
                    bto_f4.setBackgroundResource(R.drawable.yeso);
                    F=1;
                    Toast.makeText(getActivity(), "车灯开", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (F==1){
                    bto_f4.setBackgroundResource(R.drawable.noo);
                    Toast.makeText(getActivity(), "车灯关", Toast.LENGTH_SHORT).show();
                    F=0;
                    return;
                }
                break;
        }
    }

    private void Spinner(){
        spinner_1_f4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (id==0){

                }
                if (id==1){

                }
                if (id==2){

                }
                if (id==3){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_2_f4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (id==0){
                    text_f4.setText("80km/h");
                }
                if (id==1){
                    text_f4.setText("90km/h");
                }
                if (id==2){
                    text_f4.setText("60km/h");
                }
                if (id==3){
                    text_f4.setText("110km/h");
                    AlertDialog.Builder b=new AlertDialog.Builder(getActivity());
                    //设置警告对话框的标题
                    b.setTitle("提示");
                    //设置警告显示的图片
                    //    builder.setIcon(android.R.drawable.ic_dialog_alert);
                    //设置警告对话框的提示信息
                    b.setMessage("小车车号："+(id+1)+"\n小车速度：110km/h\n超速时间：5min");
                    //设置”正面”按钮，及点击事件
                    b.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    b.show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
