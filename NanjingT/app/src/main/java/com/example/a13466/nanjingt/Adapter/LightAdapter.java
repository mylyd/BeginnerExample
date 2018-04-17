package com.example.a13466.nanjingt.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a13466.nanjingt.R;
import com.example.a13466.nanjingt.bean.Light;

import java.util.List;

public class LightAdapter extends RecyclerView.Adapter<LightAdapter.ViewHolder> {
    private List<Light> mList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextRoadId;
        private TextView mTextRedTime;
        private TextView mTextGreenTime;
        private TextView mTextYellowTime;

        public ViewHolder(View itemView) {
            super(itemView);
            //实例化
            mTextRoadId = (TextView) itemView.findViewById(R.id.light_text_1);
            mTextRedTime = (TextView) itemView.findViewById(R.id.light_text_2);
            mTextGreenTime = (TextView) itemView.findViewById(R.id.light_text_3);
            mTextYellowTime = (TextView) itemView.findViewById(R.id.light_text_4);
        }

    }

    public LightAdapter(List<Light> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Light light =mList.get(position);
        holder.mTextRoadId.setText(light.getRoadId()+"");
        holder.mTextRedTime.setText(light.getRedTime()+"");
        holder.mTextGreenTime.setText(light.getYellowTime()+"");
        holder.mTextYellowTime.setText(light.getGreenTime()+"");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
