package com.example.a13466.jl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }
    public void animationText(View v){
        onBackPressed();
    }

    public void onBackPressed(){
        finish();
        overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_right);
    }
}
