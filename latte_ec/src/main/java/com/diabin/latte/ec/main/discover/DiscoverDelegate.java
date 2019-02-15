package com.diabin.latte.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.diabin.latte.app.delegate.LatteDelegate;
import com.diabin.latte.app.delegate.bottom.BottomItemDelegate;
import com.diabin.latte.app.delegate.web.WebDelegateImpl;
import com.diabin.latte.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * package: com.diabin.latte.ec.main.discover
 * author: chengguang
 * created on: 2019/2/12 下午2:29
 * description:
 */
public class DiscoverDelegate extends BottomItemDelegate {


    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl delegate = WebDelegateImpl.create("index.html");
        delegate.setTopDelegate(this.getParentDelegate());
        loadRootFragment(R.id.web_discovery_container,delegate);
    }


    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
