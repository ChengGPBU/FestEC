package com.diabin.latte.ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.diabin.latte.app.delegate.LatteDelegate;
import com.diabin.latte.ec.detail.GoodsDetailDelegate;

/**
 * package: com.diabin.latte.ec.main.index
 * author: chengguang
 * created on: 2019/2/12 上午9:01
 * description: 首页 item点击事件
 */
public class IndexItemClickListener extends SimpleClickListener {


    private final LatteDelegate mDelegate;


    public IndexItemClickListener(LatteDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public static SimpleClickListener create(LatteDelegate delegate ) {
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final GoodsDetailDelegate goodsDetailDelegate = GoodsDetailDelegate.create();
        mDelegate.start(goodsDetailDelegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
