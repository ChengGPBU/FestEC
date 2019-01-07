package com.diabin.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.diabin.latte.app.delegate.LatteDelegate;
import com.diabin.latte.app.ui.launcher.LauncherHolderCreator;
import com.diabin.latte.app.ui.launcher.ScrollLauncherTag;
import com.diabin.latte.app.util.storage.LattePreference;
import com.diabin.latte.ec.R;

import java.util.ArrayList;

/**
 * package: com.diabin.latte.ec.launcher
 * author: chengguang
 * created on: 2018/12/18 上午9:47
 * description: 启动轮播图
 */
public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> IMAGE_INTEGER = new ArrayList<>();

    private void initBanner() {
        IMAGE_INTEGER.add(R.mipmap.launcher_00);
        IMAGE_INTEGER.add(R.mipmap.launcher_01);

        mConvenientBanner
                .setPages(new LauncherHolderCreator(),IMAGE_INTEGER)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
        .setOnItemClickListener(this)
        .setCanLoop(false);
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }


    // 点击单个轮播图
    @Override
    public void onItemClick(int position) {
        // 如果点击的是最后一个图片
        if(position == IMAGE_INTEGER.size() -1) {
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
            // 检查用户是否已经登录
        }
    }
}
