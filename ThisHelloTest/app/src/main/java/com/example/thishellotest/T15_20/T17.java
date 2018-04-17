package com.example.thishellotest.T15_20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thishellotest.FirstMainActivity;
import com.example.thishellotest.R;

import org.w3c.dom.Text;

public class T17 extends AppCompatActivity implements View.OnClickListener {
    private Button back,CX;
    private TextView CON,COFF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t17);
        inttt();
    }
     private void inttt(){
         back= (Button) findViewById(R.id.T17_back);
         back.setOnClickListener(this);
         CX= (Button) findViewById(R.id.T17_chaxun);
         CX.setOnClickListener(this);
         CON= (TextView) findViewById(R.id.T17_caron);
         COFF= (TextView) findViewById(R.id.T17_caroff);
     }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.T17_back:
                Intent i1 = new Intent(T17.this, FirstMainActivity.class);
                startActivity(i1);
                finish();
                break;
            case R.id.T17_chaxun:
                CON.setBackgroundResource(R.drawable.car_on);
                COFF.setBackgroundResource(R.drawable.car_off);
                Toast.makeText(this, "2号车位有空闲 \n空闲时间：2h", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
