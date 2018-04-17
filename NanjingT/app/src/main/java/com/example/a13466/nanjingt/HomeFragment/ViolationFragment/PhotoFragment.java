package com.example.a13466.nanjingt.HomeFragment.ViolationFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.a13466.nanjingt.R;

public class PhotoFragment extends Fragment implements View.OnClickListener {

    private LinearLayout m1Photo;
    private LinearLayout m2Photo;
    private LinearLayout m3Photo;
    private LinearLayout m4Photo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photo_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View itemView) {
        m1Photo = (LinearLayout) itemView.findViewById(R.id.photo_1);
        m1Photo.setOnClickListener(this);
        m2Photo = (LinearLayout) itemView.findViewById(R.id.photo_2);
        m2Photo.setOnClickListener(this);
        m3Photo = (LinearLayout) itemView.findViewById(R.id.photo_3);
        m3Photo.setOnClickListener(this);
        m4Photo = (LinearLayout) itemView.findViewById(R.id.photo_4);
        m4Photo.setOnClickListener(this);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction ft = this.getFragmentManager().beginTransaction();
        ft.replace(R.id.Vio_fragment, fragment);
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.photo_1:
                // TODO 18/04/04
                replaceFragment(new PhotoViewFrag());
                break;
            case R.id.photo_2:
                // TODO 18/04/04
                replaceFragment(new PhotoViewFrag());
                break;
            case R.id.photo_3:
                // TODO 18/04/04
                replaceFragment(new PhotoViewFrag());
                break;
            case R.id.photo_4:
                // TODO 18/04/04
                replaceFragment(new PhotoViewFrag());
                break;
            default:
                break;
        }
    }
}
