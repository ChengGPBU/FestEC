package com.diabin.latte.ec.main.car;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;
import android.widget.Toast;

import com.diabin.latte.app.net.RestClient;
import com.diabin.latte.app.net.callback.ISuccess;
import com.diabin.latte.app.ui.recycler.MultipleItemEntity;
import com.diabin.latte.app.util.log.LatteLogger;
import com.diabin.latte.ec.R;

import com.diabin.latte.app.delegate.bottom.BottomItemDelegate;
import com.diabin.latte.ec.R2;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * package: com.diabin.latte.ec.main.car
 * author: chengguang
 * created on: 2019/2/14 上午9:04
 * description:
 */
public class ShopCarDelegate extends BottomItemDelegate implements ISuccess {
    private ShopCarAdapter mAdapter = null;
    //列表中当前剩余item数
    private int mCurrentCount = 0;
    private int mTotalCount = 0;


    @BindView(R2.id.rv_shop_car)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.icon_shop_car_select_all) // 绑定全选控件
            IconTextView mIconSelectedAll = null;
    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStubCompat = null;
    @BindView(R2.id.tv_shop_total_price)
    AppCompatTextView mTvTotalPrice = null;

    // 全选
    @OnClick(R2.id.icon_shop_car_select_all)
    void onClickSelectAll() {
        final int tag = (int) mIconSelectedAll.getTag();
        if (tag == 0) {
            // 选中
            mIconSelectedAll.setTextColor(ContextCompat.getColor(getContext(), R.color.app_main));
            mIconSelectedAll.setTag(1);
            mAdapter.setIsSelectedAll(true);
            //更新RecyclerVIew的显示状态
//            mAdapter.notifyDataSetChanged();  // 全部一次更新
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        } else {
            // 取消选中
            mIconSelectedAll.setTag(0);
            mIconSelectedAll.setTextColor(Color.GRAY);
            mAdapter.setIsSelectedAll(false);
//            mAdapter.notifyDataSetChanged();
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());

        }
    }

    @OnClick(R2.id.tv_top_shop_car_remove_selected)
    void onClickRemoveSelectedItem() {
        final List<MultipleItemEntity> data = mAdapter.getData();
        // 找到需要删除的数据
        List<MultipleItemEntity> deleteEntity = new ArrayList<>();
        for (MultipleItemEntity entity : data) {
            final boolean isSelected = entity.getField(ShopCarItemFields.IS_SELECTED);
            if (isSelected) {
                deleteEntity.add(entity);
            }
        }

        for (MultipleItemEntity entity : deleteEntity) {
            int removePosition;
            final int entityPosition = entity.getField(ShopCarItemFields.POSITION);
            if (entityPosition > mCurrentCount - 1) {
                // removePosition 在list中的下标index； mTotalCount - mCurrentCount 删除个数的偏移量；entityPosition item自身初始化的位置
                removePosition = entityPosition - (mTotalCount - mCurrentCount);
            } else {
                removePosition = entityPosition;
            }
            // 移除
            if (removePosition <= mAdapter.getItemCount()) {
                mAdapter.remove(removePosition);
                // 列表中剩余个数
                mCurrentCount = mAdapter.getItemCount();
                //更新数据
                mAdapter.notifyItemRangeChanged(removePosition, mAdapter.getItemCount());
            }
        }
        checkItemCount();
    }


    // 清空购物列表
    @OnClick(R2.id.tv_top_shop_car_clear)
    void onClickClear() {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
        checkItemCount();
    }


    // 检查Item数量
    @SuppressLint("RestrictedApi")
    private void checkItemCount() {
        final int count = mAdapter.getItemCount();
        if (count == 0) {
            final View stubView = mStubCompat.inflate();
            final AppCompatTextView tvToBy = stubView.findViewById(R.id.tv_stub_to_buy);
            tvToBy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "您该购物啦！", Toast.LENGTH_SHORT).show();
                }
            });
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_car;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mIconSelectedAll.setTag(0);
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("http://10.0.2.2:8080/latte/shop_cart.json")
                .loader(getContext())
                .success(this)
                .build()
                .post();
    }

    @Override
    public void onSuccess(String response) {
        LatteLogger.d("购物车数据", response);
        final ArrayList<MultipleItemEntity> data = new ShopCarDataConverter().setJsonData(response).convert();
        mAdapter = new ShopCarAdapter(data);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        // 初始化时的 总个数
        mTotalCount = mAdapter.getItemCount();
        checkItemCount();
    }
}
