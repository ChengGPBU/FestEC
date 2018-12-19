package com.diabin.latte.app.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * package: com.diabin.latte.app.ui.launcher
 * author: chengguang
 * created on: 2018/12/18 上午9:54
 * description:
 */
public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder> {

    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
