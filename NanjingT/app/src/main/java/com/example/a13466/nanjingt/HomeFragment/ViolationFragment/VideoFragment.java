package com.example.a13466.nanjingt.HomeFragment.ViolationFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.VideoView;

import com.example.a13466.nanjingt.R;
import com.example.a13466.nanjingt.bean.MyApplication;

public class VideoFragment extends Fragment implements View.OnClickListener {

    private VideoView mViewVideo;
    private LinearLayout m1Video;
    private LinearLayout m2Video;
    private LinearLayout m3Video;
    private LinearLayout m4Video;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View itemView) {
        m1Video = (LinearLayout) itemView.findViewById(R.id.video_1);
        m1Video.setOnClickListener(this);
        m2Video = (LinearLayout) itemView.findViewById(R.id.video_2);
        m2Video.setOnClickListener(this);
        m3Video = (LinearLayout) itemView.findViewById(R.id.video_3);
        m3Video.setOnClickListener(this);
        m4Video = (LinearLayout) itemView.findViewById(R.id.video_4);
        m4Video.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video_1:
                // TODO 18/04/04
                replaceFragment(new VideoViewFrag());
                break;
            case R.id.video_2:
                // TODO 18/04/04
                replaceFragment(new VideoViewFrag());
                break;
            case R.id.video_3:
                // TODO 18/04/04
                replaceFragment(new VideoViewFrag());
                break;
            case R.id.video_4:
                // TODO 18/04/04
                replaceFragment(new VideoViewFrag());
                break;
            default:
                break;
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction ft = this.getFragmentManager().beginTransaction();
        ft.replace(R.id.Vio_fragment,fragment);
        ft.commit();
    }
}
