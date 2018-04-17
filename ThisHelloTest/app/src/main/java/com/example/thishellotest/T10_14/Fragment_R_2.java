package com.example.thishellotest.T10_14;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thishellotest.R;

public class Fragment_R_2 extends Fragment implements View.OnClickListener{
    private Button chaxun;
    private Spinner spinner;
    private LinearLayout L1,L2,L3,L4;
    private TextView l1v1,l1v2,l1v3,l1v4,
                     l2v1,l2v2,l2v3,l2v4,
                     l3v1,l3v2,l3v3,l3v4,
                     l4v1,l4v2,l4v3,l4v4;
    int F=0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment__r_2, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        invvv();
        L1.setVisibility(View.GONE);
        L2.setVisibility(View.GONE);
        L3.setVisibility(View.GONE);
        L4.setVisibility(View.GONE);
        Spinner();
    }

    private void invvv(){
        chaxun= (Button) getActivity().findViewById(R.id.F2_Recharge_chaxun);
        spinner= (Spinner) getActivity().findViewById(R.id.Spinner_R);

        L1=(LinearLayout)getActivity().findViewById(R.id.R_L1);
        L2=(LinearLayout)getActivity().findViewById(R.id.R_L2);
        L3=(LinearLayout)getActivity().findViewById(R.id.R_L3);
        L4=(LinearLayout)getActivity().findViewById(R.id.R_L4);

        l1v1=(TextView)getActivity().findViewById(R.id.L1V1);
        l1v2=(TextView)getActivity().findViewById(R.id.L1V2);
        l1v3=(TextView)getActivity().findViewById(R.id.L1V3);
        l1v4=(TextView)getActivity().findViewById(R.id.L1V4);
        l2v1=(TextView)getActivity().findViewById(R.id.L2V1);
        l2v2=(TextView)getActivity().findViewById(R.id.L2V2);
        l2v3=(TextView)getActivity().findViewById(R.id.L2V3);
        l2v4=(TextView)getActivity().findViewById(R.id.L2V4);
        l3v1=(TextView)getActivity().findViewById(R.id.L3V1);
        l3v2=(TextView)getActivity().findViewById(R.id.L3V2);
        l3v3=(TextView)getActivity().findViewById(R.id.L3V3);
        l3v4=(TextView)getActivity().findViewById(R.id.L3V4);
        l4v1=(TextView)getActivity().findViewById(R.id.L4V1);
        l4v2=(TextView)getActivity().findViewById(R.id.L4V2);
        l4v3=(TextView)getActivity().findViewById(R.id.L4V3);
        l4v4=(TextView)getActivity().findViewById(R.id.L4V4);


        chaxun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.F2_Recharge_chaxun:
                L1.setVisibility(View.VISIBLE);
                L2.setVisibility(View.VISIBLE);
                L3.setVisibility(View.VISIBLE);
                L4.setVisibility(View.VISIBLE);
               // Spinner();
                if (F==0){
                    Moneyshenxu();
                }
                if (F==1){
                    Moneyjiangxu();
                }
                if (F==2){
                    shijianshenxu();
                }
                if (F==3){
                    shijianjiangxu();
                }
                if (F==4){
                    chehaoshenxu();
                }
                if (F==5){
                    chehaojiangxu();
                }
                break;
        }
    }

    private void Spinner(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (id==0){
                    F=0;
                    //Moneyshenxu();
                }
                if (id==1){
                    F=1;
                   // Moneyjiangxu();
                }
                if (id==2){
                    F=2;
                   // shijianshenxu();
                }
                if (id==3){
                    F=3;
                   // shijianjiangxu();
                }
                if (id==4){
                    F=4;
                   // chehaoshenxu();
                }
                if (id==5){
                    F=5;
                   //chehaojiangxu();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void shijianshenxu(){
        l1v1.setText("2");
        l1v2.setText("2018.2.9 1:38:00");
        l1v3.setText("2018.2.9 13:21:27");
        l1v4.setText("20");
        l3v1.setText("3");
        l3v2.setText("2018.2.9 1:50:25");
        l3v3.setText("2018.2.9 14:20:21");
        l3v4.setText("30");
        l4v1.setText("1");
        l4v2.setText("2018.2.9 1:52:13");
        l4v3.setText("2018.2.9 15:02:17");
        l4v4.setText("40");
        l2v1.setText("4");
        l2v2.setText("2018.2.9 1:47:22");
        l2v3.setText("2018.2.10 17:01:25");
        l2v4.setText("99");


    }
    private void shijianjiangxu(){
        l4v1.setText("2");
        l4v2.setText("2018.2.9 1:38:00");
        l4v3.setText("2018.2.9 13:21:27");
        l4v4.setText("20");
        l2v1.setText("3");
        l2v2.setText("2018.2.9 1:50:25");
        l2v3.setText("2018.2.9 14:20:21");
        l2v4.setText("30");
        l1v1.setText("1");
        l1v2.setText("2018.2.9 1:52:13");
        l1v3.setText("2018.2.9 15:02:17");
        l1v4.setText("40");
        l3v1.setText("4");
        l3v2.setText("2018.2.9 1:47:22");
        l3v3.setText("2018.2.10 17:01:25");
        l3v4.setText("99");

    }
    private void Moneyshenxu(){

        l1v1.setText("2");
        l1v2.setText("2018.2.9 1:38:00");
        l1v3.setText("2018.2.9 13:21:27");
        l1v4.setText("20");
        l2v1.setText("3");
        l2v2.setText("2018.2.9 1:50:25");
        l2v3.setText("2018.2.9 14:20:21");
        l2v4.setText("30");
        l3v1.setText("1");
        l3v2.setText("2018.2.9 1:52:13");
        l3v3.setText("2018.2.9 15:02:17");
        l3v4.setText("40");
        l4v1.setText("4");
        l4v2.setText("2018.2.9 1:47:22");
        l4v3.setText("2018.2.10 17:01:25");
        l4v4.setText("99");
    }
    private void Moneyjiangxu(){

        l4v1.setText("2");
        l4v2.setText("2018.2.9 1:38:00");
        l4v3.setText("2018.2.9 13:21:27");
        l4v4.setText("20");
        l3v1.setText("3");
        l3v2.setText("2018.2.9 1:50:25");
        l3v3.setText("2018.2.9 14:20:21");
        l3v4.setText("30");
        l2v1.setText("1");
        l2v2.setText("2018.2.9 1:52:13");
        l2v3.setText("2018.2.9 15:02:17");
        l2v4.setText("40");
        l1v1.setText("4");
        l1v2.setText("2018.2.9 1:47:22");
        l1v3.setText("2018.2.10 17:01:25");
        l1v4.setText("99");

    }
    private void chehaoshenxu(){

        l2v1.setText("2");
        l2v2.setText("2018.2.9 1:38:00");
        l2v3.setText("2018.2.9 13:21:27");
        l2v4.setText("20");
        l3v1.setText("3");
        l3v2.setText("2018.2.9 1:50:25");
        l3v3.setText("2018.2.9 14:20:21");
        l3v4.setText("30");
        l1v1.setText("1");
        l1v2.setText("2018.2.9 1:52:13");
        l1v3.setText("2018.2.9 15:02:17");
        l1v4.setText("40");
        l4v1.setText("4");
        l4v2.setText("2018.2.9 1:47:22");
        l4v3.setText("2018.2.10 17:01:25");
        l4v4.setText("99");

    }
    private void chehaojiangxu(){
        l3v1.setText("2");
        l3v2.setText("2018.2.9 1:38:00");
        l3v3.setText("2018.2.9 13:21:27");
        l3v4.setText("20");
        l2v1.setText("3");
        l2v2.setText("2018.2.9 1:50:25");
        l2v3.setText("2018.2.9 14:20:21");
        l2v4.setText("30");
        l4v1.setText("1");
        l4v2.setText("2018.2.9 1:52:13");
        l4v3.setText("2018.2.9 15:02:17");
        l4v4.setText("40");
        l1v1.setText("4");
        l1v2.setText("2018.2.9 1:47:22");
        l1v3.setText("2018.2.10 17:01:25");
        l1v4.setText("99");

    }
}
