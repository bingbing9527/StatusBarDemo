package com.qbb.statusbardemo;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qbb.statusbardemo.utils.system.StatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * 创建日期：2017/8/10 18:32
 * @author Qian Bing Bing
 * 类说明：
 */
public class ToolbarStatusBarActivity extends BaseActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.view_status)
    View viewStatus;
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll_action)
    LinearLayout llAction;
    private int actionHeight;
    private int mDistanceY = 80;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_status_bar);
        ButterKnife.bind(this);

        ViewTreeObserver observer = llAction.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                actionHeight = llAction.getHeight();
                llAction.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        ViewGroup.LayoutParams layoutParams = viewStatus.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = StatusBarHelper.getStatusBarHeight(this);
            viewStatus.setLayoutParams(layoutParams);
        }
        llAction.setAlpha(0);
        ToolbarStatusBarActivityAdapter adapter = new ToolbarStatusBarActivityAdapter(this);
        recycler.setAdapter(adapter);
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mDistanceY += dy;
                if (actionHeight <= 0) {
                    llAction.setAlpha(0);
                } else if (mDistanceY < actionHeight) {
                    float scale = ((float) mDistanceY) / actionHeight;
                    if (mDistanceY == 80)
                        llAction.setAlpha(0);
                    else
                        llAction.setAlpha(scale);
                } else {
                    llAction.setAlpha(1);
                }
                Log.e("tag","mDistanceY:" + mDistanceY + "--actionHeight:" + actionHeight);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

        });

    }

    @Override
    public void setStatusBarColor() {
        setStatusBarTranslucent();
    }
    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
