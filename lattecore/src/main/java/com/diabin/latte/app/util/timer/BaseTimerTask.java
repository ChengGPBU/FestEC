package com.diabin.latte.app.util.timer;

import java.util.TimerTask;

/**
 * package: com.diabin.latte.app.util.timer
 * author: chengguang
 * created on: 2018/12/17 上午9:32
 * description: 基类 计时任务
 */
public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener mITimerListener) {
        this.mITimerListener = mITimerListener;
    }

    @Override
    public void run() {
        if(mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}
