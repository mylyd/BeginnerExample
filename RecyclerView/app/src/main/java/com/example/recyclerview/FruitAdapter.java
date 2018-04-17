package com.example.recyclerview;

import android.media.TimedText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by 13466 on 2018/3/7.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> mFruitList;

    public  FruitAdapter(List<Fruit> fruitList){
        mFruitList=fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,parent,false);
         final ViewHolder holder=new ViewHolder(view);
        //添加点击事件
        //文字点击事件
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Fruit f=mFruitList.get(position);
                Toast.makeText(v.getContext(), "这是："+f.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        //Photo点击事件
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Fruit f=mFruitList.get(position);
                Toast.makeText(v.getContext(), "这是："+f.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit=mFruitList.get(position);
        holder.imageView.setImageResource(fruit.getImageId());
        holder.fruitText.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();//返回数据长度
    }

    /**
     *
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fruitText;
        ImageView imageView;
        View fruitView;
        public ViewHolder(View itemView) {
            super(itemView);
            fruitView=itemView;
            fruitText=(TextView) itemView.findViewById(R.id.fruit_TextName);
            imageView=(ImageView) itemView.findViewById(R.id.fruit_image);
        }
    }
}
