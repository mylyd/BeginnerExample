package com.example.thishellotest.T15_20;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.thishellotest.Book;
import com.example.thishellotest.FirstMainActivity;
import com.example.thishellotest.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class T19 extends AppCompatActivity implements View.OnClickListener {
    private Button back;
    private LineChart lineChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t19);
        intz();
        MPAndroidChart_lineChart();
    }
    private void intz(){
        back= (Button) findViewById(R.id.T19_back);
        back.setOnClickListener(this);
        lineChart= (LineChart) findViewById(R.id.T19_Chart);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.T19_back:
                Intent i1 = new Intent(T19.this, FirstMainActivity.class);
                startActivity(i1);
                finish();
                break;
        }
    }
    private void MPAndroidChart_lineChart(){
        //创建描述信息
        Description description=new Description();
        description.setText("红绿灯信息");
        description.setTextColor(Color.RED);
        description.setTextSize(20);
        lineChart.setDescription(description);//设置图表描述信息
        lineChart.setNoDataText("没有数据熬");//没有数据时显示的文字
        lineChart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
        lineChart.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
        lineChart.setDrawBorders(false);//禁止绘制图表边框的线
        //创建适配器
        ArrayList<Entry> v1=new ArrayList<>();
        ArrayList<Entry> v2=new ArrayList<>();
        ArrayList<Entry> v3=new ArrayList<>();
        //第一条线段
        v1.add(new Entry(1,30));
        v1.add(new Entry(2,20));
        v1.add(new Entry(3,40));
        //第二条线段
        v2.add(new Entry(1,30));
        v2.add(new Entry(2,20));
        v3.add(new Entry(3,25));
        //第三条线段
        v3.add(new Entry(1,10));
        v3.add(new Entry(2,10));
        v3.add(new Entry(3,10));
        //设置给LineDataSet数据集
        LineDataSet dataSet = new LineDataSet(v1,null);

    }
}
