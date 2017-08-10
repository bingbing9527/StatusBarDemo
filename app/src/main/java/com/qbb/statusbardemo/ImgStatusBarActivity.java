package com.qbb.statusbardemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ImgStatusBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_status_bar);
    }
    @Override
    public void setStatusBarColor(){
        setStatusBarTranslucent();
    }
}
