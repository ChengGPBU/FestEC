package com.diabin.latte.app.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.diabin.latte.app.Latte;
import com.diabin.latte.app.net.RestClient;
import com.diabin.latte.app.net.callback.IFailure;
import com.diabin.latte.app.net.callback.ISuccess;
import com.diabin.latte.app.ui.recycler.DataConverter;
import com.diabin.latte.app.ui.recycler.MultipleRecyclerAdapter;
import com.diabin.latte.app.util.log.LatteLogger;

/**
 * package: com.diabin.latte.app.ui.refresh
 * author: chengguang
 * created on: 2019/1/17 上午9:39
 * description: 刷新助手
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener , BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLER_VIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;




    private RefreshHandler(SwipeRefreshLayout refreshLayout, RecyclerView recyclerView,
                           DataConverter converter, PagingBean bean) {
        this.REFRESH_LAYOUT = refreshLayout;
        this.RECYCLER_VIEW = recyclerView;
        this.CONVERTER = converter;
        this.BEAN = bean;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    //简单工厂方法
    public static RefreshHandler create(SwipeRefreshLayout refreshLayout, RecyclerView recyclerView,
                                        DataConverter converter) {
        return new RefreshHandler(refreshLayout, recyclerView, converter, new PagingBean());
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);

        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                REFRESH_LAYOUT.setRefreshing(false);
            }
        },2000);
    }


    public void firstPage(String url) {
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        // 日志打印
                        LatteLogger.json("商品列表", response);
                        final JSONObject object = JSON.parseObject(response);
                        BEAN.setTotal(object.getInteger("total"))
                        .setPageSize(object.getInteger("page_size"));
                        // 设置adapter
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response).convert());
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this,RECYCLER_VIEW);
                        RECYCLER_VIEW.setAdapter(mAdapter);
                        // 自加 1
                        BEAN.addIndex();
                        //累加数量
                        BEAN.setCurrentCount(mAdapter.getData().size());
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                    }
                })
                .build()
                .post();
    }


    // 分页加载
    private void paging() {
        final int pageSize = BEAN.getPageSize();
        final int currentCount = BEAN.getCurrentCount();
        final int total = BEAN.getTotal();
        final int index = BEAN.getPageIndex();

        if(mAdapter.getData().size() < pageSize || currentCount >= total) {
            mAdapter.loadMoreEnd(true);
        }else {
            Latte.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    RestClient.builder()
                            .url("http://10.0.2.2:8080/latte/index.json")
                            .success(new ISuccess() {
                                @Override
                                public void onSuccess(String response) {
                                    mAdapter.addData(CONVERTER.setJsonData(response).convert());
                                    //累加数量
                                    BEAN.setCurrentCount(mAdapter.getData().size());
                                    mAdapter.loadMoreComplete();
                                    BEAN.addIndex();
                                }
                            })
                            .build()
                            .post();
                }
            },1000);
        }



    }

    @Override
    public void onRefresh() {
        refresh();
    }



    // 加载更多
    @Override
    public void onLoadMoreRequested() {
        paging();
    }
}
