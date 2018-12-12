package com.diabin.latte.app.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.diabin.latte.app.Latte;

/**
 * package: com.diabin.latte.app.util
 * author: chengguang
 * created on: 2018/12/12 上午9:42
 * description: 尺寸工具类
 */
public class DimenUtil {

    /**
     * 获取屏幕宽度
     * @return
     */
    public static int getScreenWidth() {
        final Resources resources = Latte.getApplicationContext().getResources();
       final DisplayMetrics dm = resources.getDisplayMetrics();
       return dm.widthPixels;
    }


    /**
     * 获取屏幕高度
     * @return
     */
    public static int getScreenHeight() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
