package com.example.thishellotest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

public class RechargeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button Back_Re;
    private Button chaxun;
    private Button pxButton;
    private LinearLayout L1,L2,L3,L4;
    private TextView l1v1,l2v1,l3v1,l4v1,
                     l1v2,l2v2,l3v2,l4v2,
                     l1v3,l2v3,l3v3,l4v3,
                     l1v4,l2v4,l3v4,l4v4,
                     l1v5,l2v5,l3v5,l4v5;

    private TextView V1;
    private Book book;
    private int count=0;
    int F=0;
    int ID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recharge_chongzhijilu);

        initView();
        int count_1 = DataSupport.count(Book.class);

        if (count_1>0) {
            Book last= DataSupport.findLast(Book.class);
            ID=last.getIde();
        }


        L1.setVisibility(View.GONE);//隐藏表格
        L2.setVisibility(View.GONE);
        L3.setVisibility(View.GONE);
        L4.setVisibility(View.GONE);
        V1.setVisibility(View.GONE);


    }

    private void initView(){
        Back_Re=(Button)findViewById(R.id.Recharge_BackButton);
        chaxun= (Button) findViewById(R.id.Chaxun_button);
        pxButton= (Button) findViewById(R.id.PX_button);

        book=new Book();

        L1= (LinearLayout) findViewById(R.id.layout_id_1);
        L2= (LinearLayout) findViewById(R.id.layout_id_2);
        L3= (LinearLayout) findViewById(R.id.layout_id_3);
        L4= (LinearLayout) findViewById(R.id.layout_id_4);
        V1= (TextView) findViewById(R.id.layout_view);

        /**/
        l1v1=(TextView)findViewById(R.id.layout_id_1_Text_1);
        l1v2=(TextView)findViewById(R.id.layout_id_1_Text_2);
        l1v3=(TextView)findViewById(R.id.layout_id_1_Text_3);
        l1v4=(TextView)findViewById(R.id.layout_id_1_Text_4);
        l1v5=(TextView)findViewById(R.id.layout_id_1_Text_5);
        l2v1=(TextView)findViewById(R.id.layout_id_2_Text_1);
        l2v2=(TextView)findViewById(R.id.layout_id_2_Text_2);
        l2v3=(TextView)findViewById(R.id.layout_id_2_Text_3);
        l2v4=(TextView)findViewById(R.id.layout_id_2_Text_4);
        l2v5=(TextView)findViewById(R.id.layout_id_2_Text_5);
        l3v1=(TextView)findViewById(R.id.layout_id_3_Text_1);
        l3v2=(TextView)findViewById(R.id.layout_id_3_Text_2);
        l3v3=(TextView)findViewById(R.id.layout_id_3_Text_3);
        l3v4=(TextView)findViewById(R.id.layout_id_3_Text_4);
        l3v5=(TextView)findViewById(R.id.layout_id_3_Text_5);
        l4v1=(TextView)findViewById(R.id.layout_id_4_Text_1);
        l4v2=(TextView)findViewById(R.id.layout_id_4_Text_2);
        l4v3=(TextView)findViewById(R.id.layout_id_4_Text_3);
        l4v4=(TextView)findViewById(R.id.layout_id_4_Text_4);
        l4v5=(TextView)findViewById(R.id.layout_id_4_Text_5);

        /**/

        chaxun.setOnClickListener(this);
        pxButton.setOnClickListener(this);
        Back_Re.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.Recharge_BackButton:
                 //Back
                 Intent intent1=new Intent(RechargeActivity.this,FirstMainActivity.class);
                 startActivity(intent1);
                 finish();
                 break;

             case R.id.PX_button:
                 //排序
                new AlertDialog.Builder(this).setSingleChoiceItems(new String[]{"时  间  降  序", "时  间  升  序"}, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==1){
                            //时间升序
                            pxButton.setText("时  间  升  序");
                            F=1;
                            dialog.dismiss();//退出Dialog
                        }
                        if (which==0){
                            //时间降序
                            pxButton.setText("时  间  降  序");
                             F=0;
                            dialog.dismiss();
                        }
                    }
                }).show();
                 break;

             case R.id.Chaxun_button:
                 //查询
                count++;
                 if (count==1){
                     V1.setVisibility(View.VISIBLE);//显示没有历史记录
                 }
                 if (count>1){
                     V1.setVisibility(View.GONE);//隐藏暂无历史记录文本
                     L1.setVisibility(View.VISIBLE);
                     L2.setVisibility(View.VISIBLE);
                     L3.setVisibility(View.VISIBLE);
                     L4.setVisibility(View.VISIBLE);

                     if (F==0){
                         jiangxu();
                     }
                     if (F==1){
                         shen();
                     }

                 }

                 break;
         }
    }

    private void jiangxu(){
        //第四条同步语句；id=4
        List<Book> books4=DataSupport.select("number","money","namee","time")
                .where("ide=?",String.valueOf(ID)).find(Book.class);
        for (Book book:books4){
            l1v1.setText("1");
            l1v2.setText(book.getNumber());
            l1v3.setText(book.getMoney());
            l1v4.setText(book.getNamee());
            l1v5.setText(book.getTime());
        }
        //查询id=3的第一条语句
            List<Book> books3=DataSupport.select("number","money","namee","time")
                    .where("ide=?",String.valueOf(ID-1)).find(Book.class);
            for (Book book:books3){
                l2v1.setText("2");
                l2v2.setText(book.getNumber());
                l2v3.setText(book.getMoney());
                l2v4.setText(book.getNamee());
                l2v5.setText(book.getTime());
            }
            //查询id=2的第一条语句
            List<Book> books2=DataSupport.select("number","money","namee","time")
                    .where("ide=?",String.valueOf(ID-2)).find(Book.class);
            for (Book book:books2){
                l3v1.setText("3");
                l3v2.setText(book.getNumber());
                l3v3.setText(book.getMoney());
                l3v4.setText(book.getNamee());
                l3v5.setText(book.getTime());
            }
            //查询id=1的第一条语句
            List<Book> books1=DataSupport.select("number","money","namee","time")
                    .where("ide=?",String.valueOf(ID-3)).find(Book.class);
            for (Book book:books1){
                l4v1.setText("4");
                l4v2.setText(book.getNumber());
                l4v3.setText(book.getMoney());
                l4v4.setText(book.getNamee());
                l4v5.setText(book.getTime());
        }
    }
    private void shen(){
        List<Book> books1=DataSupport.select("number","money","namee","time")
                .where("ide=?",String.valueOf(ID-3)).find(Book.class);
        for (Book book:books1){
            l1v1.setText("1");
            l1v2.setText(book.getNumber());
            l1v3.setText(book.getMoney());
            l1v4.setText(book.getNamee());
            l1v5.setText(book.getTime());
        }
        //查询id=2的第一条语句
        List<Book> books2=DataSupport.select("number","money","namee","time")
                .where("ide=?",String.valueOf(ID-2)).find(Book.class);
        for (Book book:books2){
            l2v1.setText("2");
            l2v2.setText(book.getNumber());
            l2v3.setText(book.getMoney());
            l2v4.setText(book.getNamee());
            l2v5.setText(book.getTime());
        }
        //查询id=3的第一条语句
        List<Book> books3=DataSupport.select("number","money","namee","time")
                .where("ide=?",String.valueOf(ID-1)).find(Book.class);
        for (Book book:books3){
            l3v1.setText("3");
            l3v2.setText(book.getNumber());
            l3v3.setText(book.getMoney());
            l3v4.setText(book.getNamee());
            l3v5.setText(book.getTime());
        }
        //第四条同步语句；id=4
        List<Book> books4=DataSupport.select("number","money","namee","time")
                .where("ide=?",String.valueOf(ID)).find(Book.class);
        for (Book book:books4){
            l4v1.setText("4");
            l4v2.setText(book.getNumber());
            l4v3.setText(book.getMoney());
            l4v4.setText(book.getNamee());
            l4v5.setText(book.getTime());
        }
    }
}
