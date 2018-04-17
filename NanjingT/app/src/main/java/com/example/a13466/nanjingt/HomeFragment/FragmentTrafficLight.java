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

import com.example.a13466.nanjingt.Adapter.LightAdapter;
import com.example.a13466.nanjingt.R;
import com.example.a13466.nanjingt.bean.Light;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 红绿灯管理
 */
public class FragmentTrafficLight extends Fragment implements View.OnClickListener {
    private Spinner mSpinner;
    private Button mBtnQuery;
    private RecyclerView mRecyclerView;
    private List<Light> mlist = new ArrayList<Light>();
    private LightAdapter lightAdater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trafficlight, container, false);
        initView(view);
        setRecyclerView();
        return view;
    }

    private void initView(View view) {
        mSpinner = (Spinner) view.findViewById(R.id.Tra_Sp_Sorting);
        mBtnQuery = (Button) view.findViewById(R.id.Tra_btn_query);
        mBtnQuery.setOnClickListener(this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.Tra_RecyclerView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Tra_btn_query:
                // TODO 18/03/29
                setSorting((int)(mSpinner.getSelectedItemId()+1));
                lightAdater.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    private void setRecyclerView(){
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(llm);
        lightAdater = new LightAdapter(setData());
        mRecyclerView.setAdapter(lightAdater);
    }
    private List<Light> setData(){
        if (mlist.size()!=0){
            mlist.clear();
        }
        mlist.add(new Light(1,6,36,26));//第一组数据
        mlist.add(new Light(3,14,100,65));//第二组数据
        mlist.add(new Light(2,24,25,55));//第三组数据
        setSorting(1);
        return mlist;
    }
    /**
     * int compare(Light p2, Light p1) 返回一个基本类型的整型，
     * 返回负数表示：p1 小于p2，
     * 返回0 表示：p1和p2相等，
     * 返回正数表示：p1大于p2
     * // compare()返回值为int类型，大于0表示正序，小于0表示逆序
     * //排序
     */
    private int S;
    private void setSorting(final int in){
        Collections.sort(mlist, new Comparator<Light>() {
            @Override
            public int compare(Light p1, Light p2) {
                switch (in){
                    case 1:S = p1.getRoadId() - p2.getRoadId();break;
                    case 2:S = p2.getRoadId() - p1.getRoadId();break;
                    case 3:S = p1.getRedTime() - p2.getRedTime();break;
                    case 4:S = p2.getRedTime() - p1.getRedTime();break;
                    case 5:S = p1.getYellowTime() - p2.getYellowTime();break;
                    case 6:S = p2.getYellowTime() - p1.getYellowTime();break;
                    case 7:S = p1.getGreenTime() - p2.getGreenTime();break;
                    case 8:S = p2.getGreenTime() - p1.getGreenTime();break;
                }
                return S;
            }
        });
    }

}
