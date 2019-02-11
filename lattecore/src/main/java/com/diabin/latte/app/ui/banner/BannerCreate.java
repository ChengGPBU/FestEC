package com.diabin.latte.app.ui.banner;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.diabin.latte.R;

import java.util.ArrayList;

/**
 * package: com.diabin.latte.app.ui.banner
 * author: chengguang
 * created on: 2019/1/26 下午3:22
 * description:
 */
public class BannerCreate {

    public static void setDefault(ConvenientBanner<String> convenientBanner,
                                  ArrayList<String> banners,
                                  OnItemClickListener clickListener){
        convenientBanner.setPages(new HolderCreate(), banners)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(clickListener)
//                .setPageTransformer()
                .startTurning(3000)
                .setCanLoop(true);
    }
}
