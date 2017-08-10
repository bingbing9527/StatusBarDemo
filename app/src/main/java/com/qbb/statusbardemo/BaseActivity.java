package com.qbb.statusbardemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qbb.statusbardemo.utils.system.StatusBarHelper;

/**
 * 创建日期：2017/8/7 18:39
 *
 * @author Qian Bing Bing
 *         类说明：设置了状态栏颜色
 */

public class BaseActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColor();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    public void setStatusBarColor(){
        //默认状态栏颜色，修改此处改变默认状态栏颜色
        setStatusBarColor(getResources().getColor(R.color.colorPrimary));
    }

    public void setStatusBarColor(int statusColor) {
        StatusBarHelper.setStatusBarColor(this,statusColor);
    }

    public  void setStatusBarTranslucent(){
        StatusBarHelper.setStatusBarTranslucent(this);
    }


}
