package com.example.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruit();
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        // layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//横向显示

        FruitAdapter adapter=new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

    }

   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK ) {
           // System.currentTimeMillis();
        }
        return super.onKeyDown(keyCode, event);
    }
*/
    private void initFruit(){
        for (int i=0;i<20;i++){
            Fruit a=new Fruit("A",R.drawable.tou);
            fruitList.add(a);
            Fruit b=new Fruit("B",R.drawable.tou);
            fruitList.add(b);
            Fruit c=new Fruit("C",R.drawable.tou);
            fruitList.add(c);
            Fruit d=new Fruit("D",R.drawable.tou);
            fruitList.add(d);
            Fruit e=new Fruit("E",R.drawable.tou);
            fruitList.add(e);
            Fruit f=new Fruit("R",R.drawable.tou);
            fruitList.add(f);
            Fruit g=new Fruit("G",R.drawable.tou);
            fruitList.add(g);
            Fruit h=new Fruit("H",R.drawable.tou);
            fruitList.add(h);
            Fruit ii=new Fruit("I",R.drawable.tou);
            fruitList.add(ii);
            Fruit j=new Fruit("J",R.drawable.tou);
            fruitList.add(j);
            Fruit k=new Fruit("K",R.drawable.tou);
            fruitList.add(k);

        }
    }
}
