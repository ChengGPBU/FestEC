package com.diabin.latte.app.ui.recycler;

import android.content.Context;
import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;


/**
 * package: com.diabin.latte.app.ui.recycler
 * author: chengguang
 * created on: 2019/2/11 下午4:03
 * description: 分割线
 */
public class BaseDecoration extends DividerItemDecoration {

    private BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookupImpl(color, size));
    }

    public static BaseDecoration create(@ColorInt int color, int size){
        return new BaseDecoration(color, size);
    }
}
