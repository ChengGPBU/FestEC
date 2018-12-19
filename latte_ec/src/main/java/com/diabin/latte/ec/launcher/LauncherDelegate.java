package com.diabin.latte.ec.launcher;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.diabin.latte.app.delegate.LatteDelegate;
import com.diabin.latte.app.util.timer.BaseTimerTask;
import com.diabin.latte.app.util.timer.ITimerListener;
import com.diabin.latte.ec.R;
import com.diabin.latte.ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * package: com.diabin.latte.ec.launcher
 * author: chengguang
 * created on: 2018/12/17 上午9:31
 * description: 启动图 倒计时功能
 */
public class LauncherDelegate extends LatteDelegate implements ITimerListener {
    private Timer mTimer = null;

    private int mCount = 5;


    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {

    }


    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        // 0秒延时   每隔一秒 执行一次
        mTimer.schedule(task, 0, 1000);

    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        initTimer();
    }


    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0 ) {
                        if(mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                        }
                    }
                }
            }
        });
    }
}
