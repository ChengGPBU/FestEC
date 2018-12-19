package com.diabin.latte.app.delegate;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diabin.latte.app.activitys.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * package: com.diabin.latte.app.delegate
 * author: chengguang
 * created on: 2018/12/8 上午10:00
 * description: 基类Fragment 抽象类
 */
public abstract class BaseDelegate extends SwipeBackFragment {
    @SuppressWarnings("SpellCheckingInspection")
    private Unbinder mUnbinder = null;

    // 子类 传入view
    public abstract Object setLayout();


    // 当视图创建 并绑定之后 子类 执行该方法
    public abstract void onBindView(@Nullable Bundle savedInstanceState,View rootView);


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        //如果传入的参数是一个int
        if(setLayout() instanceof  Integer){
            rootView = inflater.inflate((Integer) setLayout(),container,false);
        }else if(setLayout() instanceof  View){
            rootView = (View) setLayout();
        }else{
            throw new ClassCastException("type must be int or view");
        }

        // 统一绑定
        if(rootView!=null){
            mUnbinder = ButterKnife.bind(this,rootView);
            onBindView(savedInstanceState,rootView);
        }
        return rootView;
    }
    
    
    public final ProxyActivity getProxyActivity() {
        return (ProxyActivity) _mActivity;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
