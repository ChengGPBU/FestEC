package com.diabin.latte.app.ui.banner;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * package: com.diabin.latte.app.ui.banner
 * author: chengguang
 * created on: 2019/1/26 下午3:23
 * description: ImageHolder
 */
public class ImageHolder implements Holder<String> {

    private AppCompatImageView mImageView = null;

    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        Glide.with(context)
                .load(data)
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().centerCrop())
                .into(mImageView);
    }

}
