package com.qbb.statusbardemo.utils.system;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.IntDef;
import android.support.v4.view.ViewCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 适配4.4以上版本 MIUI6、Flyme 和其他 Android6.0 及以上版本状态栏字体颜色
 *
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2017/6/2
 */
public class StatusBarHelper {

    public static final int MIUI = 1;
    public static final int FLYME = 2;
    public static final int ANDROID_M = 3;
    public static final int OTHER = 4;

    @IntDef({MIUI, FLYME, ANDROID_M, OTHER})
    @Retention(RetentionPolicy.SOURCE)
    @interface SystemType {
    }

    public static int statusBarLightMode(Activity activity) {
        return statusMode(activity, true);
    }

    public static int statusBarDarkMode(Activity activity) {
        return statusMode(activity, false);
    }

    private static int statusMode(Activity activity, boolean isFontColorDark) {
        @SystemType int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (new MIUIHelper().setStatusBarLightMode(activity, isFontColorDark)) {
                result = MIUI;
            } else if (new FlymeHelper().setStatusBarLightMode(activity, isFontColorDark)) {
                result = FLYME;
            }
//            else if (new AndroidMHelper().setStatusBarLightMode(activity, isFontColorDark)) {
//                result = ANDROID_M;
//            }
        }
        return result;
    }


    public static void statusBarLightMode(Activity activity, @SystemType int type) {
        statusBarMode(activity, type, true);

    }

    public static void statusBarDarkMode(Activity activity, @SystemType int type) {
        statusBarMode(activity, type, false);
    }

    private static void statusBarMode(Activity activity, @SystemType int type, boolean isFontColorDark) {
        if (type == MIUI) {
            new MIUIHelper().setStatusBarLightMode(activity, isFontColorDark);
        } else if (type == FLYME) {
            new FlymeHelper().setStatusBarLightMode(activity, isFontColorDark);
        }
//        else if (type == ANDROID_M) {
//            new AndroidMHelper().setStatusBarLightMode(activity, isFontColorDark);
//        }
    }

    /**
     * 修改状态栏颜色，适配5.x以上
     *
     * @param activity
     * @param statusColor
     */
    @TargetApi(21)
    public static void setStatusBarColor21(Activity activity, int statusColor) {
        Window window = activity.getWindow();
        //取消状态栏透明
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        window.setStatusBarColor(statusColor);
        //设置系统状态栏处于可见状态
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        //让view不根据系统窗口来调整自己的布局
        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
            ViewCompat.requestApplyInsets(mChildView);
        }
    }

    /**
     * 设置状态栏颜色，适配4.4x以上
     *
     * @param activity
     * @param statusColor
     */
    public static void setStatusBarColor19(Activity activity, int statusColor) {
        Window window = activity.getWindow();
        ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
        View statusBarView = new View(window.getContext());
        int statusBarHeight = getStatusBarHeight(window.getContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
        params.gravity = Gravity.TOP;
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(statusColor);
        decorViewGroup.addView(statusBarView);
    }

    /**
     * 设置状态栏，除透明以外的颜色
     * @param activity
     * @param statusColor
     */
    public static void setStatusBarColor(Activity activity, int statusColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setStatusBarColor21(activity,statusColor);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setStatusBarColor19(activity,statusColor);
        }
    }

    /**
     * 设置透明状态栏
     *
     * @param activity
     */
    public static void setStatusBarTranslucent(Activity activity) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //设置系统状态栏处于可见状态,使布局可以在状态栏下显示
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN   //可见状态
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);   //布局可以在状态栏下显示
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 设置全屏
     *
     * @param activity
     */
    public static void setFullscreen(Activity activity) {
        Window window = activity.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    /**
     * 取消全屏
     *
     * @param activity
     */
    public static void clearFullscreen(Activity activity) {
        Window window = activity.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }




}
