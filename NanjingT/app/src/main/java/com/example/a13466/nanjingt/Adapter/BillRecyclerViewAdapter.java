package com.example.a13466.nanjingt.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a13466.nanjingt.R;
import com.example.a13466.nanjingt.bean.Bill;

import java.util.List;

public class BillRecyclerViewAdapter extends RecyclerView.Adapter<BillRecyclerViewAdapter.ViewHolder> {
    private List<Bill> mBill;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mText1;
        private TextView mText2;
        private TextView mText3;
        private TextView mText4;
        private TextView mText5;
        public ViewHolder(View itemView) {
            super(itemView);
            mText1 = (TextView) itemView.findViewById(R.id.bill_text_1);
            mText2 = (TextView) itemView.findViewById(R.id.bill_text_2);
            mText3 = (TextView) itemView.findViewById(R.id.bill_text_3);
            mText4 = (TextView) itemView.findViewById(R.id.bill_text_4);
            mText5 = (TextView) itemView.findViewById(R.id.bill_text_5);
        }
    }

    public BillRecyclerViewAdapter(List<Bill> mbill){
        this.mBill = mbill;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_recycler_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bill bill = mBill.get(position);
        holder.mText1.setText((position+1)+"");
        holder.mText2.setText(bill.getBill_CarNumber()+"");
        holder.mText3.setText(bill.getBill_Money()+"");
        holder.mText4.setText(bill.getBill_Name()+"");
        holder.mText5.setText(bill.getBill_Time()+"");
    }

    @Override
    public int getItemCount() {
        return mBill.size();
    }
}
