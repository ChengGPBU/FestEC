package com.diabin.latte.ec.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.view.View;

import com.diabin.latte.app.delegate.LatteDelegate;
import com.diabin.latte.ec.R;
import com.diabin.latte.ec.R2;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * package: com.diabin.latte.ec.test
 * author: chengguang
 * created on: 2019/2/11 下午6:41
 * description: 测试 Behavior 使用
 */
public class NestDelegate extends LatteDelegate {


    private BehaviorAdapter adapter = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_test;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initData();
    }

    private void initData() {
        ArrayList list = new ArrayList();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }


    }
}
