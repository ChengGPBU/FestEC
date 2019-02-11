package com.diabin.latte.app.ui.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.diabin.latte.R;
import com.diabin.latte.app.ui.banner.BannerCreate;

import java.util.ArrayList;
import java.util.List;

/**
 * package: com.diabin.latte.app.ui.recycler
 * author: chengguang
 * created on: 2019/1/25 上午9:43
 * description:
 */
public class MultipleRecyclerAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>
        implements BaseQuickAdapter.SpanSizeLookup, OnItemClickListener {


    //确保初始化一次banner，防止重复Item加载
    private boolean mIsInitBanner = false;

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
    protected MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        // 初始化布局
        init();
    }

    // 简单工厂方法  创建一个adapter
    public static MultipleRecyclerAdapter create(List<MultipleItemEntity> data) {
        return new MultipleRecyclerAdapter(data);
    }


    // 简单工厂方法  创建一个adapter
    public static MultipleRecyclerAdapter create(DataConverter converter) {
        return new MultipleRecyclerAdapter(converter.convert());
    }


    private void init() {
        //设置不同的布局
        // 文本布局
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        //设置图片布局
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
        // 图文混合布局
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_image_text);
        // 广告轮播布局
        addItemType(ItemType.BANNERS, R.layout.item_multiple_banner);
        //设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation();
        // 多次执行动画
        isFirstOnly(false);
    }



    // 创建一个Holder
    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        final String text;
        final String imageUrl;
        final ArrayList<String> bannerImages;


        switch (holder.getItemViewType()) {
            case ItemType.TEXT:
                text = entity.getField(MultipleFields.TEXT);
                holder.setText(R.id.text_single, text);
                break;
            case ItemType.IMAGE:
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(REQUEST_OPTIONS)
                        .into((ImageView) holder.getView(R.id.image_single));

                break;
            case ItemType.TEXT_IMAGE:
                text = entity.getField(MultipleFields.TEXT);
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(REQUEST_OPTIONS)
                        .into((ImageView) holder.getView(R.id.img_multiple));
                holder.setText(R.id.tv_multiple, text);
                break;

            case ItemType.BANNERS:
                if (!mIsInitBanner){
                    bannerImages = entity.getField(MultipleFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recycler_item);
                    BannerCreate.setDefault(convenientBanner, bannerImages, this);
                    mIsInitBanner = true;
                }

            default:
                break;

        }

    }


    // 宽度监听回调
    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        // 获取item 占比
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }

    @Override
    public void onItemClick(int position) {

    }
}
