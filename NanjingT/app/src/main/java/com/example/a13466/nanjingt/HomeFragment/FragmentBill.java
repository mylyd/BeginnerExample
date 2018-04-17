package com.example.a13466.nanjingt.HomeFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.a13466.nanjingt.Adapter.BillRecyclerViewAdapter;
import com.example.a13466.nanjingt.R;
import com.example.a13466.nanjingt.bean.Bill;
import com.example.a13466.nanjingt.db.Book_Recharge;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 账单管理
 */
public class FragmentBill extends Fragment implements View.OnClickListener {
    private List<Bill> mbill = new ArrayList<Bill>();
    private Spinner mBillSpinner;
    private Button mBtnQuery;
    private RecyclerView mRecyclerView;
    private BillRecyclerViewAdapter billRecyclerViewAdapter;
    private TextView mTvHistory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bill, container, false);
        initView(view);
        setTvHistory();
        return view;
    }

    private void initView(View item) {
        mBillSpinner = (Spinner) item.findViewById(R.id.Bill_Spinner);
        mBtnQuery = (Button) item.findViewById(R.id.Bill_btn_query);
        mBtnQuery.setOnClickListener(this);
        mRecyclerView = (RecyclerView) item.findViewById(R.id.bill_RecyclerView);
        mTvHistory = (TextView) item.findViewById(R.id.bill_tv_history);
    }

    private void setTvHistory(){
        int count = DataSupport.count(Book_Recharge.class);
        if (count!=0){
            setRecyclerView(1);
            mTvHistory.setVisibility(View.GONE);
        }
    }
    private void setRecyclerView(int item) {
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(llm);
        billRecyclerViewAdapter = new BillRecyclerViewAdapter(setData(item));
        mRecyclerView.setAdapter(billRecyclerViewAdapter);
    }

    private List<Bill> setData(int tem) {
        if (mbill.size() != 0) {
            mbill.clear();
        }
        List<Book_Recharge> br = DataSupport.findAll(Book_Recharge.class);//正序
        List<Book_Recharge> brlast = DataSupport.order("id desc").find(Book_Recharge.class);//倒序
        if (tem == 1) {
            for (Book_Recharge asc : br) {
                mbill.add(new Bill(asc.getRecharge_CarNumber(), asc.getRecharge_Money(), asc.getRecharge_Name(), asc.getRecharge_Time()));
            }
        }
        if (tem == 2) {
            for (Book_Recharge desc : brlast) {
                mbill.add(new Bill(desc.getRecharge_CarNumber(), desc.getRecharge_Money(), desc.getRecharge_Name(), desc.getRecharge_Time()));
            }
        }
        return mbill;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Bill_btn_query:
                // TODO 18/03/30.
                setRecyclerView((int) mBillSpinner.getSelectedItemId() + 1);
                break;
            default:
                break;
        }
    }
}
