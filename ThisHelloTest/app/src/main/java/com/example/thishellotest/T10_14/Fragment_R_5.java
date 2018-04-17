package com.example.thishellotest.T10_14;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.thishellotest.R;


public class Fragment_R_5 extends Fragment implements View.OnClickListener{
    private Button bto_f5_1,bto_f5_2;
    private TextView text_f5;
    int F=0,T=0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment__r_5, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bto_f5_1= (Button) getActivity().findViewById(R.id.F5_button_1);
        bto_f5_2= (Button) getActivity().findViewById(R.id.F5_button_2);
        text_f5= (TextView) getActivity().findViewById(R.id.F5_Text_1);

        bto_f5_1.setOnClickListener(this);
        bto_f5_2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.F5_button_1:
                if (F==0){
                    text_f5.setText("90");
                    Snackbar.make(getView(),"光线值过高，已为您自动关闭所有灯光",Snackbar.LENGTH_LONG).setAction("知道了", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }).show();
                    F=1;
                    return;
                }
                if (F==1){
                    text_f5.setText("20");
                    Snackbar.make(getView(),"光线值过低，已为您自动开启所有灯光",Snackbar.LENGTH_LONG).setAction("知道了", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }).show();
                    F=0;
                    return;
                }

                break;

            case R.id.F5_button_2:
                if (T==0){
                    bto_f5_2.setBackgroundResource(R.drawable.yesoo);
                    Snackbar.make(getView(),"手动模式已开启",Snackbar.LENGTH_LONG).setAction("", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }).show();
                    T=1;
                    return;
                }
                if (T==1){
                    bto_f5_2.setBackgroundResource(R.drawable.nooo);
                    Snackbar.make(getView(),"自动模式已开启",Snackbar.LENGTH_LONG).setAction("", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }).show();
                    T=0;
                    return;
                }
                break;
        }

    }
}
