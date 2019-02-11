package com.diabin.latte.app.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * package: com.diabin.latte.app.ui.recycler
 * author: chengguang
 * created on: 2019/1/25 上午9:44
 * description: Holder
 */
public class MultipleViewHolder extends BaseViewHolder {
    public MultipleViewHolder(View view) {
        super(view);
    }

    public static MultipleViewHolder create(View view) {
        return new MultipleViewHolder(view);
    }

}
