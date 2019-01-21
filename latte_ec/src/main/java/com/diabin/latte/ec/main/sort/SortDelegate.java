package com.diabin.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.diabin.latte.app.delegate.bottom.BottomItemDelegate;
import com.diabin.latte.ec.R;

/**
 * package: com.diabin.latte.ec.main.sort
 * author: chengguang
 * created on: 2019/1/16 上午10:03
 * description:
 */
public class SortDelegate extends BottomItemDelegate implements View.OnFocusChangeListener {
    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
