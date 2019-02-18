package com.diabin.latte.app.activitys;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;

import com.diabin.latte.R;
import com.diabin.latte.app.delegate.LatteDelegate;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * package: com.diabin.latte.app.activitys
 * author: chengguang
 * created on: 2018/12/8 上午9:58
 * description: 全局代理Activitya
 */
public abstract class ProxyActivity extends AppCompatActivity implements ISupportActivity {

    private final SupportActivityDelegate delegate = new SupportActivityDelegate(this);

    public abstract LatteDelegate setRootDelegate();



    @Override
    public void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delegate.onCreate(savedInstanceState);
        this.initContainer(savedInstanceState);
    }


    private void initContainer(@Nullable Bundle savedInstanceState) {
        @SuppressLint("RestrictedApi")
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        Log.e("ProxyActivity","R.layout.layout_test");
        if(savedInstanceState == null) {
            delegate.loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }


    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return delegate;
    }

    @Override
    public ExtraTransaction extraTransaction() {
        return delegate.extraTransaction();
    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return delegate.getFragmentAnimator();
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        delegate.setFragmentAnimator(fragmentAnimator);

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return delegate.onCreateFragmentAnimator();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 垃圾回收
        System.gc();
        System.runFinalization();
    }
}
