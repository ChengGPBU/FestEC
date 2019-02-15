package com.diabin.latte.ec.main.car;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.diabin.latte.app.Latte;
import com.diabin.latte.app.ui.recycler.ItemType;
import com.diabin.latte.app.ui.recycler.MultipleFields;
import com.diabin.latte.app.ui.recycler.MultipleItemEntity;
import com.diabin.latte.app.ui.recycler.MultipleRecyclerAdapter;
import com.diabin.latte.app.ui.recycler.MultipleViewHolder;
import com.diabin.latte.ec.R;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

/**
 * package: com.diabin.latte.ec.main.car
 * author: chengguang
 * created on: 2019/2/14 上午9:30
 * description:
 */
public class ShopCarAdapter extends MultipleRecyclerAdapter {

    private boolean mIsSelectedAll = false;
    private ICarItemListener mICarItemListener = null;
    private double mTotalPrice = 0.00;

    //设置图片加载策略
    private static final RequestOptions REQUEST_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected ShopCarAdapter(List<MultipleItemEntity> data) {
        super(data);
        // 添加购物车item布局
        addItemType(ShopCarItemType.SHOP_CAR_ITEM, R.layout.item_shop_car);
    }


    public void setIsSelectedAll(boolean isSelectedAll) {
        this.mIsSelectedAll = isSelectedAll;
    }


    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ShopCarItemType.SHOP_CAR_ITEM:
                //取出所有值
                final int id = entity.getField(MultipleFields.ID);
                final String thumb = entity.getField(MultipleFields.IMAGE_URL);
                final String title = entity.getField(ShopCarItemFields.TITLE);
                final String desc = entity.getField(ShopCarItemFields.DESC);
                final int count = entity.getField(ShopCarItemFields.COUNT);
                final double price = entity.getField(ShopCarItemFields.PRICE);

                //取出所有控件
                final AppCompatImageView imgThumb = holder.getView(R.id.image_item_shop_car);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_car_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_car_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_car_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_car_count);
                final IconTextView iconIsSelect = holder.getView(R.id.icon_item_shop_car);


                //赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));
                Glide.with(mContext)
                        .load(thumb)
                        .apply(REQUEST_OPTIONS)
                        .into(imgThumb);

                //在左侧勾勾渲染之前改变全选状态
                entity.setField(ShopCarItemFields.IS_SELECTED, mIsSelectedAll);
                final boolean isSelected = entity.getField(ShopCarItemFields.IS_SELECTED);

                //根据数据状态显示左侧勾勾
                if (isSelected) {
                    iconIsSelect.setTextColor(ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));
                } else {
                    iconIsSelect.setTextColor(Color.GRAY);
                }
                //添加左侧勾勾点击事件
                iconIsSelect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean currentSelected = entity.getField(ShopCarItemFields.IS_SELECTED);
                        if (currentSelected) {
                            iconIsSelect.setTextColor(Color.GRAY);
                            entity.setField(ShopCarItemFields.IS_SELECTED, false);
                        } else {
                            iconIsSelect.setTextColor(ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));
                            entity.setField(ShopCarItemFields.IS_SELECTED, true);
                        }
                    }
                });





                break;
            default:break;
        }
    }
}
