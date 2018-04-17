package com.example.thishellotest.T15_20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.thishellotest.FirstMainActivity;
import com.example.thishellotest.R;

public class T20 extends AppCompatActivity implements View.OnClickListener {
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t20);
        intcc();
    }
    private void intcc(){
        back= (Button) findViewById(R.id.T20_back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.T20_back:
                Intent i1 = new Intent(T20.this, FirstMainActivity.class);
                startActivity(i1);
                finish();
                break;
        }
    }
}
