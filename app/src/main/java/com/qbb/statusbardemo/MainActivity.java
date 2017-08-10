package com.qbb.statusbardemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建日期：2017/8/10 18:29
 * @author Qian Bing Bing
 * 类说明： 使用的是设置的默认的状态栏颜色，
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_1)
    Button btn1;
    @BindView(R.id.btn_2)
    Button btn2;
    @BindView(R.id.btn_3)
    Button btn3;
    @BindView(R.id.btn_4)
    Button btn4;
    @BindView(R.id.btn_5)
    Button btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                startActivity(new Intent(this,FullScreenActivity.class));
                break;
            case R.id.btn_2:
                startActivity(new Intent(this,RedStatusBarActivity.class));
                break;
            case R.id.btn_3:
                startActivity(new Intent(this,YellowStatusBarActivity.class));
                break;
            case R.id.btn_4:
                startActivity(new Intent(this,ImgStatusBarActivity.class));
                break;
            case R.id.btn_5:
                startActivity(new Intent(this,ToolbarStatusBarActivity.class));
                break;
        }
    }
}
