package com.example.thishellotest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
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
 * 这是小车速度阀值页面
 * */
public class RechargeFragment_1 extends Fragment implements View.OnClickListener {
    private Button fg_1_button_chaxun;
    private Button fg_1_button_setup;
    private TextView fg_1_text_max,fg_1_text_min;
    private EditText fg_1_edittext_chaxun;
    private EditText fg_1_edittext_chehao;
    private EditText fg_1_edittext_max;
    private EditText fg_1_edittext_min;
    private String fg_1_chehao;
    private String fg_1_max;
    private String fg_1_min;
    private int first_fg_1=1;
    private String Carnum;
    private String Carmax;
    private String Carmin;
    int F;
    private BookThird lastbt= DataSupport.findLast(BookThird.class);//获取数据库最后一组数据

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.recharge_fragment_1, null);
    }

    private void vvvvView(){
            fg_1_button_chaxun=(Button)getActivity().findViewById(R.id.fragment_1_chaxun);
            fg_1_button_setup=(Button)getActivity().findViewById(R.id.fragment_1_Setup);
            fg_1_text_max=(TextView)getActivity().findViewById(R.id.fragment_1_text_max);
            fg_1_text_min=(TextView)getActivity().findViewById(R.id.fragment_1_text_min);
            fg_1_edittext_chaxun=(EditText)getActivity().findViewById(R.id.fragment_1_EditText_chaxun);
            fg_1_edittext_chehao=(EditText)getActivity().findViewById(R.id.fragment_1_chehao);
            fg_1_edittext_max=(EditText)getActivity().findViewById(R.id.fragment_1_max);
            fg_1_edittext_min=(EditText)getActivity().findViewById(R.id.fragment_1_min);

            fg_1_button_setup.setOnClickListener(this);
            fg_1_button_chaxun.setOnClickListener(this);
            fg_1_text_max.setOnClickListener(this);
            fg_1_text_min.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        vvvvView();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.fragment_1_chaxun:
                //Fragment_1查询
                if (first_fg_1==1){//内容为空时
                    Toast.makeText(getActivity(), "您还没有输入查询的数据", Toast.LENGTH_SHORT).show();
                }
                if (first_fg_1==2){
                    onStop();
                    //从数据空获取最后一条数据；
                    List<BookThird> btlast=DataSupport.select("f1carnumber","f1speedmax","f1speedmin")
                            .where("f1idt=?",String.valueOf(F)).find(BookThird.class);
                    for (BookThird last:btlast){
                        Carnum=last.getF1carnumber();
                        Carmax=last.getF1speedmax();
                        Carmin=last.getF1speedmin();
                    }
                    //显示获取的数据
                    fg_1_edittext_chaxun.setText("阀值：最低:"+ String.valueOf(Carmin)+"h/km------最高:"+String.valueOf(Carmax)+"h/km");
                    fg_1_edittext_chehao.setText(String.valueOf(Carnum));
                }

                break;
            case R.id.fragment_1_Setup:
                //Fragment_1设置
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
                        fg_1_chehao=fg_1_edittext_chehao.getText().toString();
                        fg_1_max=fg_1_edittext_max.getText().toString();
                        fg_1_min=fg_1_edittext_min.getText().toString();

                        if (fg_1_min.length()<2&&fg_1_max.length()<2||fg_1_chehao.length()<6){//判断内容是否为空,车号至少为6位
                            Toast.makeText(getContext(), "输入格式错误：车号至少为6位，Max至少为2位，Min至少为2位", Toast.LENGTH_SHORT).show();
                        }else {
                            int A=Integer.parseInt(fg_1_max);
                            int B=Integer.parseInt(fg_1_min);//获取输入的数字大小
                            if (fg_1_max.length()>=fg_1_min.length()){   //此处判断，Max必须比Min大（这里限定的是长度）
                                if (A<21||B<20){
                                    Toast.makeText(getActivity(), "输入错误\n速度设定范围不属于正常范围", Toast.LENGTH_SHORT).show();
                                    qingchu();
                                }
                                if (A>60||B>59){
                                    Toast.makeText(getActivity(), "输入错误\n最大速度峰值为：60km/h\n最小速度峰值为：20km/h", Toast.LENGTH_SHORT).show();
                                     qingchu();
                                }else {
                                if (A>B){   //正确范围
                                    //获取数据库最后一组数据的id；
                                    F=lastbt.getF1idt()+1;
                                    //储存EditText的内容
                                    BookThird bt=new BookThird();
                                    bt.setF1idt(F);
                                    bt.setF1carnumber(fg_1_chehao);
                                    bt.setF1speedmax(fg_1_max);
                                    bt.setF1speedmin(fg_1_min);
                                    bt.save();
                                    //将first_fg_1的值赋值为2；表示存入了数据，否则点击查询无效
                                    first_fg_1=2;
                                /*
                                这里是一个梗
                                * */
                                    //(这里是对数据库的清扫)，保留最后2组数据；其余清除；
                                    int L=F-1;//F为倒数第二的一组数据；
                                    DataSupport.deleteAll(BookThird.class,"f1idt<?",String.valueOf(L));

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
                                        // qingchu();
                                    }
                                }).show();
                            }
                        }
                    }
                });
                prodressDialog.show();

                break;
            case R.id.fragment_1_text_max:
                //Fragment_1最大
                Snackbar.make(getView(),"                                                                              " +
                                "超过最大速度阈值强制停止小车                                                                                                                                ",
                        Snackbar.LENGTH_LONG).setAction("知道了", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "当前速度大于 60h/km 了", Toast.LENGTH_SHORT).show();
                    }
                }).show();

                break;
            case R.id.fragment_1_text_min:
                //Fragment_1最小
                Snackbar.make(getView(),"                                                                              " +
                                "速度过慢，请提速                                                                                                                                ",
                        Snackbar.LENGTH_LONG).setAction("知道了", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "当前速度小于 10h/km 了", Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
        }
    }
    public void qingchu(){
        fg_1_edittext_max.setText(null);
        fg_1_edittext_min.setText(null);
        fg_1_edittext_chehao.setText(null);
        fg_1_edittext_chaxun.setText(null);
    }
   
}
