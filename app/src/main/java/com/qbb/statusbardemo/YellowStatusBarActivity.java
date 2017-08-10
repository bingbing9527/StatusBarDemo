package com.qbb.statusbardemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class YellowStatusBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_status_bar);
    }
    @Override
    public void setStatusBarColor(){
        setStatusBarColor(Color.parseColor("#FFB500"));
    }
}
