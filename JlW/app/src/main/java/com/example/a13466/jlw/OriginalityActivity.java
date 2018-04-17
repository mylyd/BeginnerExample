package com.example.a13466.jlw;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class OriginalityActivity extends AppCompatActivity implements View.OnClickListener {

    private PieChart mPieChart;
    private Button m1Btn;
    private Button m2Btn;
    private Button m3Btn;
    private Button m4Btn;
    private Button m5Btn;
    private Button m6Btn;
    private Button m7Btn;
    private EditText mEdit;
    int t = 1, b = 1;
    private Button m0Btn;
    private Button mBtnBack;
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_originality);
        initView();
        setChartPie();
        mTvTitle.setText("数据可视化");
    }

    private void initView() {
        mPieChart = (PieChart) findViewById(R.id.pie_chart);
        m1Btn = (Button) findViewById(R.id.btn_1);
        m1Btn.setOnClickListener(this);
        m2Btn = (Button) findViewById(R.id.btn_2);
        m2Btn.setOnClickListener(this);
        m3Btn = (Button) findViewById(R.id.btn_3);
        m3Btn.setOnClickListener(this);
        m4Btn = (Button) findViewById(R.id.btn_4);
        m4Btn.setOnClickListener(this);
        m5Btn = (Button) findViewById(R.id.btn_5);
        m5Btn.setOnClickListener(this);
        m6Btn = (Button) findViewById(R.id.btn_6);
        m6Btn.setOnClickListener(this);
        m7Btn = (Button) findViewById(R.id.btn_7);
        m7Btn.setOnClickListener(this);
        mEdit = (EditText) findViewById(R.id.Edit);
        m0Btn = (Button) findViewById(R.id.btn_0);
        m0Btn.setOnClickListener(this);
        mBtnBack = (Button) findViewById(R.id.btn_back);
        mBtnBack.setOnClickListener(this);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
    }

    private void setChartPie() {
        // 显示百分比
        mPieChart.setUsePercentValues(true);
        // 描述信息
        mPieChart.setDescription(null);
        // 设置偏移量
        mPieChart.setExtraOffsets(5, 10, 5, 5);
        // 设置滑动减速摩擦系数
        mPieChart.setDragDecelerationFrictionCoef(0.95f);
        //中间文字
        mPieChart.setCenterText("数 据 可 视 化");
        mPieChart.setCenterTextSize(30f);
        mPieChart.setCenterTextColor(0xFF3BAD9D);
        /*
            设置饼图中心是否是空心的
            true 中间是空心的，环形图
            false 中间是实心的 饼图
         */
        mPieChart.setDrawHoleEnabled(true);
        /*
            设置中间空心圆孔的颜色是否透明
            true 透明的
            false 非透明的
         */
        mPieChart.setHoleColorTransparent(true);
        // 设置环形图和中间空心圆之间的圆环的颜色
        mPieChart.setTransparentCircleColor(Color.WHITE);
        // 设置环形图和中间空心圆之间的圆环的透明度
        mPieChart.setTransparentCircleAlpha(110);
        // 设置圆孔半径
        mPieChart.setHoleRadius(48f);
        // 设置空心圆的半径
        mPieChart.setTransparentCircleRadius(52f);
        // 设置是否显示中间的文字
        mPieChart.setDrawCenterText(true);
        //


        // add a selection listener
        // mPieChart.setOnChartValueSelectedListener(this);
        mPieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                //Log.d("MainActivity", "1111111111111: "+e.getVal());//获取你点击的模块所占的比列
                //  Log.d("MainActivity", "3333333333333: "+e.getXIndex());//h.getXIndex()与这个相同获取你点击模块的数值，类似于id 从0起步
                if (e.getXIndex() == 0) {
                    Toast.makeText(OriginalityActivity.this, "百分比：" + e.getVal() * 100 + "%", Toast.LENGTH_SHORT).show();
                }
                if (e.getXIndex() == 1) {
                    Toast.makeText(OriginalityActivity.this, "百分比：" + e.getVal() * 100 + "%", Toast.LENGTH_SHORT).show();
                }
                if (e.getXIndex() == 2) {
                    Toast.makeText(OriginalityActivity.this, "百分比：" + e.getVal() * 100 + "%", Toast.LENGTH_SHORT).show();
                }
                if (e.getXIndex() == 3) {
                    Toast.makeText(OriginalityActivity.this, "百分比：" + e.getVal() * 100 + "%", Toast.LENGTH_SHORT).show();
                }
                if (e.getXIndex() == 4) {
                    Toast.makeText(OriginalityActivity.this, "百分比：" + e.getVal() * 100 + "%", Toast.LENGTH_SHORT).show();
                }
                // Log.d("MainActivity", "6666666666666: "+h.getDataSetIndex()+"  "+h.getXIndex()+"  "+h.getRange()+"  "+h.getStackIndex());

            }

            @Override
            public void onNothingSelected() {
                // Toast.makeText(MainActivity.this, "这是点击了饼图外区域", Toast.LENGTH_SHORT).show();
            }
        });


        //设置数据
        TreeMap<String, Float> data = new TreeMap<>();
        data.put("CO2", 0.4f);
        data.put("PM 2.5", 0.2f);
        data.put("温度", 0.1f);
        data.put("湿度", 0.1f);
        data.put("光照强度", 0.2f);
        setData(data);

        // 设置动画
        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        // 设置显示的比例
        Legend l = mPieChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
    }

    public void setData(TreeMap<String, Float> data) {
        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        int i = 0;
        Iterator it = data.entrySet().iterator();
        while (it.hasNext()) {
            // entry的输出结果如key0=value0等
            Map.Entry entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            float value = (float) entry.getValue();
            xVals.add(key);
            yVals1.add(new Entry(value, i++));
        }
        /**
         *  " "里面填写对标签的描述文字
         */
        PieDataSet dataSet = new PieDataSet(yVals1, "");

        // 设置饼图区块之间的距离
        dataSet.setSliceSpace(2f);
        dataSet.setSelectionShift(20f);//点击后放大的大小

        // 添加颜色 我每个数据分配不同的颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        PieData data1 = new PieData(xVals, dataSet);
        data1.setValueFormatter(new PercentFormatter());
        data1.setValueTextSize(20f);//图饼文字大小
        data1.setValueTextColor(Color.BLACK);
        mPieChart.setData(data1);
        // undo all highlights
        mPieChart.highlightValues(null);
        mPieChart.invalidate();//刷新
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_0:
                // TODO 18/03/16 百分比显示
                b++;
                if (b % 2 == 0) {
                    mPieChart.setDrawSliceText(false);
                    m0Btn.setText("显示文字");
                } else {
                    mPieChart.setDrawSliceText(true);
                    m0Btn.setText("关闭文字");
                }


                mPieChart.invalidate();
                break;
            case R.id.btn_1:
                // TODO 18/03/16 显示百分比
                for (DataSet<?> set : mPieChart.getData().getDataSets()) {
                    set.setDrawValues(!set.isDrawValuesEnabled());
                }
                t++;
                if (t % 2 == 0) {
                    m1Btn.setText("显示百分比");
                } else {
                    m1Btn.setText("关闭百分比");
                }
                mPieChart.invalidate();
                break;
            case R.id.btn_2:
                // TODO 18/03/16 是否铺满
                /**
                 * 判断是否为空心圆，
                 * if：设置为false（空心圆）
                 * else：设置为true（实心圆）
                 * ---注意：每次改变 UI 后都需要调用incalidate()刷新
                 */
                if (mPieChart.isDrawHoleEnabled()) {
                    mPieChart.setDrawHoleEnabled(false);
                    m2Btn.setText("空心圆");
                } else {
                    mPieChart.setDrawHoleEnabled(true);
                    m2Btn.setText("实心圆");
                }
                mPieChart.invalidate();
                break;
            case R.id.btn_3:
                // TODO 18/03/16 X轴动画
                /**
                 * 以下每个括号参数代表动画执行的毫秒数
                 * */
                mPieChart.animateX(1800);
                break;
            case R.id.btn_4:
                // TODO 18/03/16 Y轴动画
                mPieChart.animateY(1800);
                break;
            case R.id.btn_5:
                // TODO 18/03/16 XY动画
                mPieChart.animateXY(3000, 1400);
                break;
            case R.id.btn_6:
                // TODO 18/03/16 显示中间文字
                if (mPieChart.isDrawCenterTextEnabled()) {
                    mPieChart.setDrawCenterText(false);
                    m6Btn.setText("显示中间文字");
                } else {
                    mPieChart.setDrawCenterText(true);
                    m6Btn.setText("关闭中间文字");
                }
                mPieChart.invalidate();
                break;
            case R.id.btn_7:
                // TODO 18/03/16 旋转动画
                String angle = mEdit.getText().toString();
                if (mEdit.length() != 0) {
                    int AG = Integer.parseInt(angle);
                    if (AG >= 1 && AG <= 7200) {
                        if (AG != 7200) {
                            mPieChart.spin(1700, mPieChart.getRotationAngle(), mPieChart.getRotationAngle() + AG, Easing.EasingOption
                                    .EaseInCubic);
                        } else {
                            mPieChart.spin(20000, mPieChart.getRotationAngle(), mPieChart.getRotationAngle() + 720000, Easing.EasingOption
                                    .EaseInCubic);
                        }
                    } else {
                        Toast.makeText(this, "输入范围：Max:3600,Min:1", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请输入动画旋转的角度", Toast.LENGTH_SHORT).show();
                }

                mEdit.setText(null);
                break;

            case R.id.btn_back:
                // TODO 18/03/16 back
                finish();
                break;
            default:
                break;
        }
    }
}
