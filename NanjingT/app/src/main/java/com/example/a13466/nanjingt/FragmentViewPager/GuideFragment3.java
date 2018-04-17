package com.example.a13466.nanjingt.FragmentViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.a13466.nanjingt.LoginActivity;
import com.example.a13466.nanjingt.MainActivity;
import com.example.a13466.nanjingt.R;

/**
 * Created by 13466 on 2018/3/27.
 */

public class GuideFragment3 extends Fragment implements View.OnClickListener {
    private Button mStartBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_guide_third, container, false);
        initView(view);
        return view;
    }

    private void initView(View itemView) {
        mStartBtn = (Button) itemView.findViewById(R.id.guide_start_btn);
        mStartBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.guide_start_btn:
                // TODO 18/03/27 立即体验
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            default:
                break;
        }
    }
}
