package com.diabin.latte.app.net.rx;

import android.content.Context;

import com.diabin.latte.app.net.RestClient;
import com.diabin.latte.app.net.RestClientBuilder;
import com.diabin.latte.app.net.RestCreator;
import com.diabin.latte.app.net.callback.IError;
import com.diabin.latte.app.net.callback.IFailure;
import com.diabin.latte.app.net.callback.IRequest;
import com.diabin.latte.app.net.callback.ISuccess;
import com.diabin.latte.app.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * package: com.diabin.latte.app.net.rx
 * author: chengguang
 * created on: 2018/12/16 下午6:40
 * description:
 */
public class RxRestClientBuilder {

    private static final Map<String, Object> PARAMS = RestCreator.getParams();

    private String mUrl = null;
    private RequestBody mBody = null;
    private File mFile = null;
    private LoaderStyle mLoaderStyle = null;
    private Context mContext = null;


    //如果不加public，它会默认加protected，则只能在同一包里才能调用。
    RxRestClientBuilder() {
    }

    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        this.PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, String value) {
        this.PARAMS.put(key, value);
        return this;
    }

    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }


    public final RxRestClientBuilder file(String fileName) {
        this.mFile = new File(fileName);
        return this;
    }


    public final RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }


    public final RxRestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        this.mLoaderStyle = loaderStyle;
        this.mContext = context;
        return this;
    }


    public final RxRestClientBuilder loader(Context context) {
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        this.mContext = context;
        return this;
    }


    public final RxRestClient build() {
        return new RxRestClient(mUrl, PARAMS, mBody, mFile, mContext, mLoaderStyle);
    }

}
