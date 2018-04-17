package com.example.a13466.nanjingt.HomeFragment.EnvironmentalFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a13466.nanjingt.R;
import com.example.a13466.nanjingt.bean.MyApplication;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MPAndroidChartFragment extends Fragment {
    private TextView mChartText;
    private Bundle arg;
    private TextView mYText;
    private LineChart mChartLine;
    private Random random;
    private Handler handler;
    private List<Entry> entries;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.en_viewpager_mpandroidchart_fragment, container, false);
        initView(view);
        mChartText.setText(arg.getString("title"));
        mYText.setText(arg.getString("Y"));
        timer.schedule(timerTask,0,3000);
        return view;
    }
    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                   setMPAndroidChart();
                }
            });
        }
    };
    private void setMPAndroidChart() {
       /* SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm");
        Date date = new Date(System.currentTimeMillis());
        String timer = simpleDateFormat.format(date);
        int N = Integer.parseInt(timer);*/
        int J = arg.getInt("id",100);
        ////1.创建一个List集合，用来存放一条折线上的所有点
        entries = new ArrayList<Entry>();
        for (int i=1;i<20;i++){
            int EN = random.nextInt(J)+1;
            entries.add(new Entry((float)(i*0.03),EN));
        }

        //2.将entries设置给LineDataSet数据集
        LineDataSet lineDataSet = new LineDataSet(entries,null);
        //3.将上面创建的LineDataSet对象设置给LineData
        LineData lineData = new LineData(lineDataSet);
        mChartLine.setData(lineData);
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
        xAxis.setTextSize(18);//字体大小
        xAxis.setLabelCount(20);//显示个数
        /* xAxis.setAxisMaximum(23);//最大值*/
        xAxis.setAxisMinimum(0);//最小值
        //设置左边y轴的样式
        YAxis yAxisLeft = mChartLine.getAxisLeft();
        yAxisLeft.setAxisLineWidth(3);//y轴宽度
        yAxisLeft.setDrawGridLines(false);//不显示网格线
        yAxisLeft.setTextSize(18);  //字体大小
        yAxisLeft.setGridColor(Color.GRAY);//网格线颜色
        yAxisLeft.setTextColor(Color.BLACK);//字体颜色
        //
        lineDataSet.setLineWidth(4.00f);//折线宽度
        lineDataSet.setCircleRadius(8f);//圆点大小
        lineDataSet.setCircleColor(Color.rgb(89,194,230));//圆点颜色
        lineDataSet.setValueTextSize(16f);//数值显示的大小
        lineDataSet.setValueTextColor(Color.rgb(89, 194, 230)); //数值显示的颜色
        mChartLine.invalidate();//刷新
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arg = getArguments();
    }

    private void initView(View item) {
        mChartText = (TextView) item.findViewById(R.id.text_viewChart);
        mYText = (TextView) item.findViewById(R.id.text_Y);
        mChartLine = (LineChart) item.findViewById(R.id.line_chart);
        random = new Random();
        handler = new Handler();
    }

    public static MPAndroidChartFragment newInstance(Bundle args) {
        MPAndroidChartFragment mpAndroidChartFragment = new MPAndroidChartFragment();
        mpAndroidChartFragment.setArguments(args);
        return mpAndroidChartFragment;
    }
}
