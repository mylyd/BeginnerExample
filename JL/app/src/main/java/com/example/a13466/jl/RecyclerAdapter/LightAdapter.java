package com.example.a13466.jl.RecyclerAdapter;



import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a13466.jl.R;

import java.util.List;

/**
 * Created by 13466 on 2018/3/20.
 */

public class LightAdapter extends RecyclerView.Adapter<LightAdapter.ViewHolder> {
    private List<Light> mLightView;

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextCarnum;
        public TextView mTextRedTime;
        public TextView mTextGreenTime;
        public TextView mTextYellowTime;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextCarnum = (TextView) itemView.findViewById(R.id.light_text_1);
            mTextRedTime = (TextView) itemView.findViewById(R.id.light_text_2);
            mTextGreenTime = (TextView) itemView.findViewById(R.id.light_text_3);
            mTextYellowTime = (TextView) itemView.findViewById(R.id.light_text_4);
            Log.d("######", "ViewHolder: " + mTextCarnum);
        }
    }

    public LightAdapter(List<Light> mLightView) {
        this.mLightView = mLightView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.light_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Light light=mLightView.get(position);
        holder.mTextCarnum.setText(light.getCar());
        holder.mTextRedTime.setText(light.getRedlight()+"");
        holder.mTextGreenTime.setText(light.getGreenlight()+"");
        holder.mTextYellowTime.setText(light.getYellowlight()+"");
    }

    @Override
    public int getItemCount() {
        return mLightView.size();
    }
}
