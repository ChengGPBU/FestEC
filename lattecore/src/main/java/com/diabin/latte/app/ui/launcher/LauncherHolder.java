package com.diabin.latte.app.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * package: com.diabin.latte.app.ui.launcher
 * author: chengguang
 * created on: 2018/12/18 上午9:54
 * description: 轮播图 Holder
 */
public class LauncherHolder implements Holder<Integer> {
    private AppCompatImageView mImageView = null;



    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int i, Integer integer) {
        mImageView.setBackgroundResource(integer);
    }
}
