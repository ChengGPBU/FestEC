package com.diabin.latte.app.net;

import android.content.Context;

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
 * package: com.diabin.latte.app.net
 * author: chengguang
 * created on: 2018/12/10 上午9:23
 * description: Client 建造者
 */
public class RestClientBuilder {
    private static final Map<String, Object> PARAMS = RestCreator.getParams();

    private String mUrl = null;
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;
    private File mFile = null;
    private LoaderStyle mLoaderStyle = null;
    private Context mContext= null;
    private String mDownloadDir = null;
    private String mName = null;
    private String mExtension = null;

    //如果不加public，它会默认加protected，则只能在同一包里才能调用。
    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        this.PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, String value) {
        this.PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }


    public final RestClientBuilder file(String fileName) {
        this.mFile = new File(fileName);
        return this;
    }



    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }


    public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }


    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }


    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }


    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }


    public final RestClientBuilder loader(Context context,LoaderStyle loaderStyle) {
        this.mLoaderStyle = loaderStyle;
        this.mContext = context;
        return this;
    }


    public final RestClientBuilder loader(Context context) {
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        this.mContext = context;
        return this;
    }



    public final RestClient build() {
        return new RestClient(mUrl, PARAMS,mDownloadDir,mExtension,mName, mIRequest, mISuccess, mIFailure, mIError, mBody,mFile,mContext,mLoaderStyle);
    }

}
