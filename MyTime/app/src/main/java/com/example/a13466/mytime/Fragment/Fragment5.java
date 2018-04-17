package com.example.a13466.mytime.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.a13466.mytime.R;

public class Fragment5 extends Fragment implements View.OnClickListener {
    private Button mButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment5, container, false);
        initView(view);
        return view;
    }

    private void initView(final View itemView) {
        mButton = (Button) itemView.findViewById(R.id.button);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                // TODO 18/03/27
                Toast.makeText(getContext(), "111", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
