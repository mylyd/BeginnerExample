package com.example.thishellotest;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class FragmentMPAndroidChart extends Fragment {
    private Bundle arg;
    private TextView mChartTitleTv,mChartY;
    private LineChart mChartLine;
    //private LineChart mLineChart;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arg=getArguments();
    }

    private void initView(final View itemView){
        mChartTitleTv=(TextView)itemView.findViewById(R.id.text_viewChart);
        mChartLine= (LineChart) itemView.findViewById(R.id.line_chart);
        mChartY= (TextView) itemView.findViewById(R.id.text_Y);
    }



    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mpandroidchart, null, false);
        initView(view);
        mChartTitleTv.setText(arg.getString("title"));
        mChartY.setText(arg.getString("Y"));



        //1.创建一个List集合，用来存放一条折线上的所有点
        List<Entry> entries = new ArrayList<Entry>();
        int id=arg.getInt("id",1);
        if (id==1){
            entries.add(new Entry(0,12));
            entries.add(new Entry(1,11));
            entries.add(new Entry(2,10));
            entries.add(new Entry(3,7));
            entries.add(new Entry(4,8));
            entries.add(new Entry(5,9));
            entries.add(new Entry(6,11));
            entries.add(new Entry(7,15));
            entries.add(new Entry(8,20));
            entries.add(new Entry(9,23));
            entries.add(new Entry(10,24));
            entries.add(new Entry(11,25));
            entries.add(new Entry(12,28));
            entries.add(new Entry(13,30));
            entries.add(new Entry(14,31));
            entries.add(new Entry(15,31));
            entries.add(new Entry(16,30));
            entries.add(new Entry(17,27));
            entries.add(new Entry(18,22));
            entries.add(new Entry(19,16));
            entries.add(new Entry(20,16));
            entries.add(new Entry(21,15));
            entries.add(new Entry(22,12));
            entries.add(new Entry(23,10));
        }else if (id==2){
            entries.add(new Entry(0,113));
            entries.add(new Entry(1,117));
            entries.add(new Entry(2,120));
            entries.add(new Entry(3,122));
            entries.add(new Entry(4,122));
            entries.add(new Entry(5,110));
            entries.add(new Entry(6,100));
            entries.add(new Entry(7,99));
            entries.add(new Entry(8,98));
            entries.add(new Entry(9,98));
            entries.add(new Entry(10,92));
            entries.add(new Entry(11,88));
            entries.add(new Entry(12,88));
            entries.add(new Entry(13,86));
            entries.add(new Entry(14,82));
            entries.add(new Entry(15,82));
            entries.add(new Entry(16,83));
            entries.add(new Entry(17,86));
            entries.add(new Entry(18,88));
            entries.add(new Entry(19,92));
            entries.add(new Entry(20,94));
            entries.add(new Entry(21,96));
            entries.add(new Entry(22,101));
            entries.add(new Entry(23,110));
        }else if (id==3){
            entries.add(new Entry(0,12));
            entries.add(new Entry(1,11));
            entries.add(new Entry(2,11));
            entries.add(new Entry(3,13));
            entries.add(new Entry(4,13));
            entries.add(new Entry(5,17));
            entries.add(new Entry(6,22));
            entries.add(new Entry(7,32));
            entries.add(new Entry(8,33));
            entries.add(new Entry(9,38));
            entries.add(new Entry(10,42));
            entries.add(new Entry(11,50));
            entries.add(new Entry(12,50));
            entries.add(new Entry(13,55));
            entries.add(new Entry(14,56));
            entries.add(new Entry(15,56));
            entries.add(new Entry(16,54));
            entries.add(new Entry(17,52));
            entries.add(new Entry(18,50));
            entries.add(new Entry(19,34));
            entries.add(new Entry(20,30));
            entries.add(new Entry(21,15));
            entries.add(new Entry(22,11));
            entries.add(new Entry(23,10));
        }else if (id==4){
            entries.add(new Entry(0,330));
            entries.add(new Entry(1,320));
            entries.add(new Entry(2,300));
            entries.add(new Entry(3,299));
            entries.add(new Entry(4,289));
            entries.add(new Entry(5,298));
            entries.add(new Entry(6,320));
            entries.add(new Entry(7,342));
            entries.add(new Entry(8,346));
            entries.add(new Entry(9,366));
            entries.add(new Entry(10,376));
            entries.add(new Entry(11,380));
            entries.add(new Entry(12,390));
            entries.add(new Entry(13,410));
            entries.add(new Entry(14,450));
            entries.add(new Entry(15,470));
            entries.add(new Entry(16,510));
            entries.add(new Entry(17,320));
            entries.add(new Entry(18,280));
            entries.add(new Entry(19,170));
            entries.add(new Entry(20,200));
            entries.add(new Entry(21,211));
            entries.add(new Entry(22,243));
            entries.add(new Entry(23,255));
        }else if (id==5){
            entries.add(new Entry(0,998));
            entries.add(new Entry(1,997));
            entries.add(new Entry(2,1010));
            entries.add(new Entry(3,1020));
            entries.add(new Entry(4,1080));
            entries.add(new Entry(5,1110));
            entries.add(new Entry(6,1300));
            entries.add(new Entry(7,1350));
            entries.add(new Entry(8,1400));
            entries.add(new Entry(9,1000));
            entries.add(new Entry(10,998));
            entries.add(new Entry(11,999));
            entries.add(new Entry(12,970));
            entries.add(new Entry(13,950));
            entries.add(new Entry(14,930));
            entries.add(new Entry(15,900));
            entries.add(new Entry(16,888));
            entries.add(new Entry(17,960));
            entries.add(new Entry(18,1000));
            entries.add(new Entry(19,1110));
            entries.add(new Entry(20,1200));
            entries.add(new Entry(21,1300));
            entries.add(new Entry(22,1050));
            entries.add(new Entry(23,1150));
        }else if (id==6){
            entries.add(new Entry(0,2));
            entries.add(new Entry(1,2));
            entries.add(new Entry(2,3));
            entries.add(new Entry(3,1));
            entries.add(new Entry(4,4));
            entries.add(new Entry(5,1));
            entries.add(new Entry(6,3));
            entries.add(new Entry(7,1));
            entries.add(new Entry(8,4));
            entries.add(new Entry(9,1));
            entries.add(new Entry(10,3));
            entries.add(new Entry(11,1));
            entries.add(new Entry(12,4));
            entries.add(new Entry(13,1));
            entries.add(new Entry(14,3));
            entries.add(new Entry(15,2));
            entries.add(new Entry(16,4));
            entries.add(new Entry(17,1));
            entries.add(new Entry(18,4));
            entries.add(new Entry(19,1));
            entries.add(new Entry(20,3));
            entries.add(new Entry(21,1));
            entries.add(new Entry(22,4));
            entries.add(new Entry(23,3));
        }

        //2.将entries设置给LineDataSet数据集
        LineDataSet dataSet = new LineDataSet(entries,null);

        //3.将上面创建的LineDataSet对象设置给LineData
        LineData lineData = new LineData(dataSet);

        //4.把lineData设置给lineChart就可以显示出来折线图了，就像把adapter设置给listview一样
        mChartLine.setData(lineData);

//设置样式
        mChartLine.setBackgroundColor(0x20000000);//背景色
        mChartLine.setDragEnabled(true);    // 可拖动,默认true
        mChartLine.setScaleEnabled(true);   // 两个轴上的缩放,X,Y分别默认为true
        //设置x轴的样式
        XAxis xAxis = mChartLine.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisLineWidth(3);//x轴宽度
        xAxis.setDrawGridLines(false);//不显示网格线
        xAxis.setGridColor(Color.GRAY);//网格线颜色
        xAxis.setTextColor(Color.BLACK);//字体颜色
        xAxis.setTextSize(22);//字体大小
        xAxis.setLabelCount(20);//显示个数
        xAxis.setAxisMaximum(23);//最大值
        xAxis.setAxisMinimum(0);//最小值
        //设置左边y轴的样式
        YAxis yAxisLeft = mChartLine.getAxisLeft();
        yAxisLeft.setAxisLineWidth(3);//y轴宽度
        yAxisLeft.setDrawGridLines(false);//不显示网格线
        yAxisLeft.setTextSize(22);  //字体大小
        yAxisLeft.setGridColor(Color.GRAY);//网格线颜色
        yAxisLeft.setTextColor(Color.BLACK);//字体颜色
        //yAxisLeft.setLabelCount(15);//显示个数
       // yAxisLeft.setAxisMinimum(0);//最小值
        dataSet.setLineWidth(8.00f);//折线宽度
        //dataSet.setCircleSize(8f);//圆点大小
        dataSet.setCircleRadius(8f);//圆点大小
        dataSet.setCircleColor(Color.rgb(89,194,230));//圆点颜色
        dataSet.setValueTextSize(16f);//数值显示的大小
        dataSet.setValueTextColor(Color.rgb(89, 194, 230)); //数值显示的颜色


        return view;
   }



    public static FragmentMPAndroidChart newInstance(Bundle args){
        FragmentMPAndroidChart fragment=new FragmentMPAndroidChart();
        fragment.setArguments(args);
        return fragment;
    }
}
