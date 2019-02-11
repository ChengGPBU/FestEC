package com.diabin.latte.app.ui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * package: com.diabin.latte.app.ui.banner
 * author: chengguang
 * created on: 2019/1/26 下午3:23
 * description:
 */
public class HolderCreate implements CBViewHolderCreator<ImageHolder> {
    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}

