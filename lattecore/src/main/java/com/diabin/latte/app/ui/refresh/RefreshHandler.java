package com.diabin.latte.app.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.diabin.latte.app.Latte;
import com.diabin.latte.app.net.RestClient;
import com.diabin.latte.app.net.callback.IFailure;
import com.diabin.latte.app.net.callback.ISuccess;
import com.diabin.latte.app.util.log.LatteLogger;

/**
 * package: com.diabin.latte.app.ui.refresh
 * author: chengguang
 * created on: 2019/1/17 上午9:39
 * description: 刷新助手
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout refreshLayout;

    public RefreshHandler(SwipeRefreshLayout refresh_layout) {
        this.refreshLayout = refresh_layout;
        refreshLayout.setOnRefreshListener(this);
    }



    private void refresh() {
        refreshLayout.setRefreshing(true);

        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        },2000);
    }


    public void firstPage(String url) {
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        // 日志打印
                        LatteLogger.json("商品列表", response);
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

    @Override
    public void onRefresh() {
        refresh();
    }
}
