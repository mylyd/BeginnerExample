package com.example.a13466.nanjingt.HomeFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.a13466.nanjingt.HomeFragment.ViolationFragment.PhotoFragment;
import com.example.a13466.nanjingt.HomeFragment.ViolationFragment.VideoFragment;
import com.example.a13466.nanjingt.R;

/**
 * 违章查询
 */
public class FragmentViolation extends Fragment implements View.OnClickListener {

    private Button mBtnVideo;
    private Button mBtnPhoto;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_violation, container, false);
        initView(view);
        replaceFragment(new VideoFragment());
        return view;
    }

    private void initView(View item) {
        mBtnVideo = (Button) item.findViewById(R.id.Vio_btn_video);
        mBtnVideo.setOnClickListener(this);
        mBtnPhoto = (Button) item.findViewById(R.id.Vio_btn_photo);
        mBtnPhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Vio_btn_video:
                // TODO 18/03/30
                replaceFragment(new VideoFragment());
                mBtnVideo.setBackgroundResource(R.drawable.black_bg_border);
                mBtnPhoto.setBackgroundResource(R.drawable.white_bg_border);
                break;
            case R.id.Vio_btn_photo:
                // TODO 18/03/30
                replaceFragment(new PhotoFragment());
                mBtnPhoto.setBackgroundResource(R.drawable.black_bg_border);
                mBtnVideo.setBackgroundResource(R.drawable.white_bg_border);
                break;
            default:
                break;
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fm = this.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.Vio_fragment,fragment);
        ft.commit();
    }


}
