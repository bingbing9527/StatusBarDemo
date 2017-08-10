package com.qbb.statusbardemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.qbb.statusbardemo.utils.system.StatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FullScreenActivity extends BaseActivity {

    @BindView(R.id.button)
    Button button;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        unbinder = ButterKnife.bind(this);
        //设置全屏
        StatusBarHelper.setFullscreen(this);
    }
    @Override
    public void setStatusBarColor(){
        setStatusBarTranslucent();
    }
    @OnClick(R.id.button)
    public void onClick() {
        //取消全屏
        StatusBarHelper.clearFullscreen(this);
        startActivity(new Intent(this,RedStatusBarActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
