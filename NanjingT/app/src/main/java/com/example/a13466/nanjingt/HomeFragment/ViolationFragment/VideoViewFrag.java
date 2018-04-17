package com.example.a13466.nanjingt.HomeFragment.ViolationFragment;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.a13466.nanjingt.R;


public class VideoViewFrag extends Fragment {


    private VideoView mViewVideo;
    private Uri uri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_view, container, false);
        initView(view);
        return view;
    }

    private void initView(View itemView) {
        uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.pm);
        mViewVideo = (VideoView) itemView.findViewById(R.id.Video_View);
        mViewVideo.setVideoURI(uri);
        MediaController media = new MediaController(getActivity());
        mViewVideo.start();//开始播放
        mViewVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mViewVideo.start();
            }
        });
        mViewVideo.setMediaController(media);
    }

}
