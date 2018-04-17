package com.example.thishellotest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;
/**
* 这是账户阀值页面
*
* */
public class RechargeFragment_2 extends Fragment implements View.OnClickListener{
    private Button fg_2_button_chaxun;
    private Button fg_2_button_setup;
    private EditText fg_2_edittext_chaxun;
    private EditText fg_2_edittext_chehao;
    private EditText fg_2_edittext_max;
    private EditText fg_2_edittext_min;
    private TextView fg_2_text_max,fg_2_text_min;
    private String fg_2_chehao;
    private String fg_2_max;
    private String fg_2_min;
    private int first_fg_2=1;
    private String carNum;
    private String carMax;
    private String carMin;
    int I;
    private BookSecond lastbs= DataSupport.findLast(BookSecond.class);//获取数据库最后一组数据


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.recharge_fragment_2, null);
    }

    private void vvvvView(){
        fg_2_button_chaxun=(Button)getActivity().findViewById(R.id.fragment_2_chaxun);
        fg_2_button_setup=(Button)getActivity().findViewById(R.id.fragment_2_Setup);
        fg_2_text_max=(TextView)getActivity().findViewById(R.id.fragment_2_text_max);
        fg_2_text_min=(TextView)getActivity().findViewById(R.id.fragment_2_text_min);
        fg_2_edittext_chaxun=(EditText)getActivity().findViewById(R.id.fragment_2_EditText_chaxun);
        fg_2_edittext_chehao=(EditText)getActivity().findViewById(R.id.fragment_2_chehao);
        fg_2_edittext_max=(EditText)getActivity().findViewById(R.id.fragment_2_max);
        fg_2_edittext_min=(EditText)getActivity().findViewById(R.id.fragment_2_min);


        fg_2_button_setup.setOnClickListener(this);
        fg_2_button_chaxun.setOnClickListener(this);
        fg_2_text_max.setOnClickListener(this);
        fg_2_text_min.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        vvvvView();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.fragment_2_chaxun:
                //Fragment_2查询
                if (first_fg_2==1){//内容为空时
                    Toast.makeText(getActivity(), "您还没有输入查询的数据", Toast.LENGTH_SHORT).show();
                }
                if (first_fg_2==2){
                    onStop();
                    //从数据库获取最后一条数据；
                    List<BookSecond> bslast=DataSupport.select("carnumber","speedmax","speedmin")
                            .where("idt=?",String.valueOf(I)).find(BookSecond.class);
                    for (BookSecond last:bslast){
                        carNum=last.getCarnumber();
                        carMax=last.getSpeedmax();
                        carMin=last.getSpeedmin();
                    }
                    //显示获取的数据
                    fg_2_edittext_chaxun.setText("阀值：最低:"+ String.valueOf(carMin)+"元------最高:"+String.valueOf(carMax)+"元");
                    fg_2_edittext_chehao.setText(String.valueOf(carNum));
                }
                break;
            case R.id.fragment_2_Setup:
                //Fragment_2设置
                //Dialog等待提示框
                ProgressDialog prodressDialog=new ProgressDialog(getActivity());
                prodressDialog.setTitle("是否保存修改的设置");
                prodressDialog.setMessage("请确认……");
                prodressDialog.setCancelable(false);//能否取消该属性；
                //DialogInterface.BUTTON_NEGATIVE这个属性使显示在最右边的左边
                prodressDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            qingchu();
                            Toast.makeText(getContext(), "您的设置取消成功", Toast.LENGTH_SHORT).show();
                    }
                });
                //DialogInterface.BUTTON_POSITIVE这个属性使显示在最右边的右边
                prodressDialog.setButton(DialogInterface.BUTTON_POSITIVE,"确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //获取输入的值；
                        fg_2_chehao=fg_2_edittext_chehao.getText().toString();
                        fg_2_max=fg_2_edittext_max.getText().toString();
                        fg_2_min=fg_2_edittext_min.getText().toString();

                        if (fg_2_min.length()<2&&fg_2_max.length()<3||fg_2_chehao.length()<6){//判断内容是否为空,车号至少为6位
                            Toast.makeText(getContext(), "输入格式错误：车号至少为6位，Max至少为3位，Min至少为2位", Toast.LENGTH_SHORT).show();
                        }else {
                            int A=Integer.parseInt(fg_2_max);
                            int B=Integer.parseInt(fg_2_min);//获取输入的数字大小
                            if (fg_2_max.length()>=fg_2_min.length()){   //此处判断，Max必须比Min大（这里限定的是长度）
                            if (A<11||B<10){
                                Toast.makeText(getActivity(), "输入错误\n速度设定范围不属于正常范围", Toast.LENGTH_SHORT).show();
                                qingchu();
                            }
                            if (A>5000||B>4999){
                                Toast.makeText(getActivity(), "输入错误\n最大速度峰值为：60km/h\n最小速度峰值为：20km/h", Toast.LENGTH_SHORT).show();
                                qingchu();
                            }
                            if (A<=5000&&B>=10){
                                if (A>B){
                                    //获取数据库最后一组数据的id；
                                    I=lastbs.getIdt()+1;
                                    //储存EditText的内容
                                    BookSecond bs=new BookSecond();
                                    bs.setIdt(I);
                                    bs.setCarnumber(fg_2_chehao);
                                    bs.setSpeedmax(fg_2_max);
                                    bs.setSpeedmin(fg_2_min);
                                    bs.save();
                                    //将first_fg_2的值赋值为2；表示存入了数据，否则点击查询无效
                                    first_fg_2=2;
                                    //(这里是对数据库的清扫)，保留最后2组数据；其余清除；
                                    int L=I-1;//I为倒数第二的一组数据；
                                    DataSupport.deleteAll(BookSecond.class,"idt<?",String.valueOf(L));
                                    //清除显示
                                    qingchu();
                                    Toast.makeText(getActivity(), "设置成功", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getActivity(), "您输入有误:(您设置的最大比最小金额小)", Toast.LENGTH_SHORT).show();
                                    qingchu();
                                }
                            }
                            }else {
                                Snackbar.make(getView(),"输入有误",Snackbar.LENGTH_LONG).setAction("立马改正", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                       qingchu();
                                    }
                                }).show();
                            }
                        }
                    }
                });
                prodressDialog.show();
                break;
            case R.id.fragment_2_text_max:
                //Fragment_2最大
                Snackbar.make(getView(),"                                                                              " +
                                "您的余额不足，强行停止运行                                                                                                                                 ",
                        Snackbar.LENGTH_LONG).setAction("充值", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //与下面相同
                        Intent i11=new Intent(getActivity(),AccountRecharge.class);
                        startActivity(i11);
                    }
                }).show();

                break;
            case R.id.fragment_2_text_min:
                //Fragment_2最小
                Snackbar.make(getView(),"                                                                              " +
                                "您的余额不足，强行停止运行                                                                                                                                ",
                        Snackbar.LENGTH_LONG).setAction("充值", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*此处填写充值查询逻辑；
                        建议：单独写一个方法，此处调用
                        * 思路：1.点击按钮将数据填写进数据库，2.跳转到充值查询页面，3.在充值查询页面做判断，如果第四组数据为空则
                        * 不去显示第四组TextView，不为空则显示，并且将数据提取出来
                        * */
                        Intent i11=new Intent(getActivity(),AccountRecharge.class);
                        startActivity(i11);
                    }
                }).show();
                break;
        }
    }
    public void qingchu(){
        fg_2_edittext_max.setText(null);
        fg_2_edittext_min.setText(null);
        fg_2_edittext_chehao.setText(null);
        fg_2_edittext_chaxun.setText(null);
    }
}
