package com.example.mpanndroidchart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LineChart mLineChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.从xml中获取linechart的引用
        mLineChart = (LineChart) findViewById(R.id.linechart);

        //2.创建一个List集合，用来存放一条折线上的所有点
        List<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(1,20));
        entries.add(new Entry(2,30));
        entries.add(new Entry(3,15));
        entries.add(new Entry(4,50));

        //3.将entries设置给LineDataSet数据集
        LineDataSet dataSet = new LineDataSet(entries, "0000000000000");

        //4.将上面创建的LineDataSet对象设置给LineData
        LineData lineData = new LineData(dataSet);

        //5.把lineData设置给lineChart就可以显示出来折线图了，就像把adapter设置给listview一样
        mLineChart.setData(lineData);

//
        //mLineChart.setBackgroundColor(0x70000000);//背景色
        mLineChart.setDragEnabled(true);    // 可拖动,默认true
        mLineChart.setScaleEnabled(true);   // 两个轴上的缩放,X,Y分别默认为true
        //设置x轴的样式
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisLineWidth(3);//x轴宽度
        xAxis.setDrawGridLines(false);//不显示网格线
        xAxis.setGridColor(Color.GRAY);//网格线颜色
        xAxis.setTextColor(Color.BLACK);//字体颜色
        xAxis.setTextSize(22);//字体大小
        xAxis.setLabelCount(20);//显示个数
        xAxis.setAxisMaximum(24);//最大值
        xAxis.setAxisMinimum(0);//最小值
        //设置左边y轴的样式
        YAxis yAxisLeft = mLineChart.getAxisLeft();
        yAxisLeft.setAxisLineWidth(3);//y轴宽度
        yAxisLeft.setDrawGridLines(false);//不显示网格线
        yAxisLeft.setTextSize(22);  //字体大小
        yAxisLeft.setGridColor(Color.GRAY);//网格线颜色
        yAxisLeft.setTextColor(Color.BLACK);//字体颜色
        yAxisLeft.setLabelCount(15);//显示个数
        yAxisLeft.setAxisMinimum(0);//最小值

      //  LineDataSet dataset=new LineDataSet(entries,lineName);
        dataSet.setLineWidth(8.00f);//折线宽度
        dataSet.setCircleSize(8f);//圆点大小
        dataSet.setCircleColor(Color.rgb(89,194,230));//圆点颜色
        dataSet.setValueTextSize(16f);//数值显示的大小
        dataSet.setValueTextColor(Color.rgb(89, 194, 230)); //数值显示的颜色

    }

}
