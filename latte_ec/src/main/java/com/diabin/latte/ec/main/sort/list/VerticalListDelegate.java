package com.diabin.latte.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.diabin.latte.app.delegate.LatteDelegate;
import com.diabin.latte.app.net.RestClient;
import com.diabin.latte.app.net.callback.ISuccess;
import com.diabin.latte.app.ui.recycler.MultipleItemEntity;
import com.diabin.latte.ec.R;
import com.diabin.latte.ec.R2;
import com.diabin.latte.ec.main.sort.SortDelegate;

import java.util.List;

import butterknife.BindView;

/**
 * package: com.diabin.latte.ec.main.sort.list
 * author: chengguang
 * created on: 2019/2/12 上午9:40
 * description: 分类--左侧 列表
 */
public class VerticalListDelegate extends LatteDelegate {
    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        // 屏蔽动画效果
        mRecyclerView.setAnimation(null);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("http://10.0.2.2:8080/latte/sort_list.json")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final List<MultipleItemEntity> data =
                                new VerticalListDataConverter().setJsonData(response).convert();
                        final SortDelegate delegate = getParentDelegate();
                        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, delegate);
                        mRecyclerView.setAdapter(adapter);


                    }
                })
                .build()
                .post();
    }
}
