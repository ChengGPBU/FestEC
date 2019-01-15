package com.diabin.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.diabin.latte.app.AccountManager;
import com.diabin.latte.app.IUserChecker;
import com.diabin.latte.app.delegate.LatteDelegate;
import com.diabin.latte.app.ui.launcher.ILauncherListener;
import com.diabin.latte.app.ui.launcher.OnLauncherFinishTag;
import com.diabin.latte.app.ui.launcher.ScrollLauncherTag;
import com.diabin.latte.app.util.storage.LattePreference;
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
 * description: 开启app的时候 启动 倒计时功能
 *
 *
 * 流程：启动页倒计时  计时结束 检测是否第一次打开  是：进入滚动引导页  （否：检查是否登录 是：进入主页 否：进入登录也）
 */
public class LauncherDelegate extends LatteDelegate implements ITimerListener {
    private Timer mTimer = null;

    private int mCount = 5;
    private ILauncherListener mILauncherListener = null;

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }


    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        // 0秒延时   每隔一秒 执行一次
        mTimer.schedule(task, 0, 1000);

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    /**
     * 判断是否展示滑动启动页
     */
    private void checkIsShowScroll() {
        // 是否首次打开首次打开
      if(!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
          start(new LauncherScrollDelegate(),SINGLETASK);
      }else{
          // 检查用户是否登录了App
          AccountManager.checkAccount(new IUserChecker() {
              @Override
              public void onSignIn() {
                  // 登录 注册成功
                  if(mILauncherListener != null) {
                      mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                  }
              }

              @Override
              public void onNotSignIn() {
                  // 未登录注册
                  if(mILauncherListener != null) {
                      mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                  }
              }
          });
      }
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
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
