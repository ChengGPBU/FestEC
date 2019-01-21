package com.diabin.latte.app.delegate.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.diabin.latte.R;
import com.diabin.latte.app.delegate.LatteDelegate;

/**
 * package: com.diabin.latte.app.delegate.bottom
 * author: chengguang
 * created on: 2019/1/16 上午8:57
 * description: 单个Tab Item 容器类
 */
public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener {
    private long mExitTime = 0;
    private static final int EXIT_TIME = 2000;

    @Override
    public void onResume() {
        super.onResume();
        final View rootView = getView();
        // 不为null的时候 设置监听事件 需要看下fragmention 的源码
        if (rootView != null) {
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > EXIT_TIME) {
                Toast.makeText(getContext(), "双击退出" + getString(com.wang.avi.R.string.app_name), Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                _mActivity.finish();
                if (mExitTime != 0) {
                    mExitTime = 0;
                }
            }
            // 事件被消费 事件不会传递
            return true;
        }
        // 事件未被消费  事件传递
        return false;
    }
}
