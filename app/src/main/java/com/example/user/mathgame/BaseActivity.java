package com.example.user.mathgame;



        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.res.Resources;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;
        import android.view.MotionEvent;
        import android.view.Window;
        import android.view.WindowManager;
        import android.view.inputmethod.InputMethodManager;



        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;

        import me.jessyan.autosize.AutoSizeCompat;
        import me.jessyan.autosize.internal.CustomAdapt;


/**
 * @author caibou
 */
public class BaseActivity extends AppCompatActivity implements CustomAdapt {
    private List<Activity> activities = new ArrayList<>();

    LoadingDialog loadingDialog;
    protected Boolean isDarkBar = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = new LoadingDialog();
        activities.add(this);

    }


    protected void setStatusBar(boolean isDarkStatusBar) {
        Window window = getWindow();
        if (window == null) return;
        // 设置状态栏背景透明
        StatusBarUtils.transparentStatusBar(window);
        // 设置图标主题
        if (isDarkStatusBar) {
            StatusBarUtils.setDarkMode(window);
        } else {
            StatusBarUtils.setLightMode(window);
        }
    }

    public void loadingWithDialog(String message) {
        if (!loadingDialog.isAdded())
            loadingDialog.show(getSupportFragmentManager(), "");
        loadingDialog.setMessage(message);
    }

    public void loadingWithDialog() {
        loadingWithDialog(getString(R.string.app_name));
    }

    public void dismissLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isAdded())
            loadingDialog.dismissAllowingStateLoss();
    }

    @Override
    protected void onDestroy() {
        dismissLoadingDialog();
        super.onDestroy();
    }

    /**
     * 点击空白位置 隐藏软键盘
     */
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            InputMethodManager mInputMethodManager
                    = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(
                    this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }


    /**
     * 隐藏软键盘
     */
    public void hideInput() {
        if (null != this.getCurrentFocus()) {
            InputMethodManager mInputMethodManager
                    = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            mInputMethodManager.hideSoftInputFromWindow(
                    this.getCurrentFocus().getWindowToken(), 0);
        }
    }


    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isDarkBar) {
            setStatusBar(isDarkBar);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart(getClass().getName());
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd(getClass().getName());
//        MobclickAgent.onPause(this);
    }

    protected void openActivity(Class<?> cls){
        startActivity(new Intent(this,cls).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    protected void fullscreen(boolean enable) {

        if (enable) { //显示状态栏

            WindowManager.LayoutParams lp = getWindow().getAttributes();

            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;

            getWindow().setAttributes(lp);

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        } else { //隐藏状态栏

            WindowManager.LayoutParams lp = getWindow().getAttributes();

            lp.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);

            getWindow().setAttributes(lp);

            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        }

    }

    /**
     *  判断某个字符串是否存在于数组中
     *  用来判断该配置是否通道相关
     *  @param stringArray 原数组
     *  @param source 查找的字符串
     *  @return 是否找到
     */
    public static boolean contains(String[] stringArray, String source) {
        // 转换为list
        List<String> tempList = Arrays.asList(stringArray);

        // 利用list的包含方法,进行判断
        return tempList.contains(source);
    }

    @Override
    public boolean isBaseOnWidth() {
        return true;
    }

    @Override
    public float getSizeInDp() {
        return 375                                                                                                                                                                                                                                                           ;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
      //  super.attachBaseContext(LocalManageUtil.setLocal(newBase));
    }

    @Override
    public Resources getResources() {
        //需要升级到 v1.1.2 及以上版本才能使用 AutoSizeCompat
        AutoSizeCompat.autoConvertDensityOfGlobal((super.getResources()));//如果没有自定义需求用这个方法
        AutoSizeCompat.autoConvertDensity(super.getResources(), 375, true);//如果有自定义需求就用这个方法
        return super.getResources();
    }
}
