package com.example.thishellotest.T10_14;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.thishellotest.R;

public class Fragment_R_1 extends Fragment implements View.OnClickListener{
        private Button Recharge;

        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment__r_1, null);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            initView();
        }

        private void initView(){
            Recharge=(Button)getActivity().findViewById(R.id.F1_Recharge_chaxun);
            
            Recharge.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.F1_Recharge_chaxun:
                Snackbar.make(getView(),"2号道路与3号道路发生拥挤",Snackbar.LENGTH_LONG).setAction("知道了", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "红路灯信息已改变", Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
        }
    }
}
