package com.diabin.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.diabin.latte.app.delegate.bottom.BottomItemDelegate;
import com.diabin.latte.app.net.RestClient;
import com.diabin.latte.app.net.callback.IFailure;
import com.diabin.latte.app.net.callback.ISuccess;
import com.diabin.latte.app.ui.recycler.MultipleFields;
import com.diabin.latte.app.ui.recycler.MultipleItemEntity;
import com.diabin.latte.app.ui.refresh.RefreshHandler;
import com.diabin.latte.app.util.log.LatteLogger;
import com.diabin.latte.ec.R;
import com.diabin.latte.ec.R2;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * package: com.diabin.latte.ec.main.index
 * author: chengguang
 * created on: 2019/1/16 上午10:02
 * description:
 */
public class IndexDelegate extends BottomItemDelegate implements View.OnFocusChangeListener {

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.icon_index_message)
    IconTextView mIconMessage = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;


    private RefreshHandler mRefreshHandler = null;


    // 初始化 刷新样式
    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        //下拉过程中球会由小变大，回弹回由大变小,后面两个参数分别表示起始位置和结束位置
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }


    //初始化列表
    private void initRecylerView() {
       final GridLayoutManager manager = new GridLayoutManager(getContext(),4);
       mRecyclerView.setLayoutManager(manager);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        // 创建 刷新帮助类
        mRefreshHandler = RefreshHandler.create(mRefreshLayout,mRecyclerView,new IndexDataConvert());
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecylerView();
        mRefreshHandler.firstPage("http://10.0.2.2:8080/latte/index.json");
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }


}
