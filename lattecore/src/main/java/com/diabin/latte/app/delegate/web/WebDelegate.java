package com.diabin.latte.app.delegate.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewFragment;

import com.diabin.latte.app.delegate.LatteDelegate;
import com.diabin.latte.app.delegate.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * package: com.diabin.latte.app.delegate.web
 * author: chengguang
 * created on: 2019/2/12 下午2:35
 * description: web 抽象基类
 */
public abstract class WebDelegate extends LatteDelegate implements IWebViewInitializer {


    private WebView mWebView = null;
    private final ReferenceQueue<WebView> webViewReferenceQueue = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebViewAvailable = false;
    private LatteDelegate mTopDelegate = null;

    public WebDelegate() {
    }


    public  abstract IWebViewInitializer setInitializer();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());
        initWebView();
    }


    private void initWebView() {
        if(mWebView !=null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        }else{
            final IWebViewInitializer initializer = setInitializer();
            if(initializer != null) {
                final WeakReference<WebView> webViewWeakReference = new WeakReference<>(new WebView(getContext()),webViewReferenceQueue);
                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                mWebView.addJavascriptInterface(LatteWebInterface.create(this),"latte");
                mIsWebViewAvailable = true;
            }else{
                throw  new NullPointerException("WebView Initializer is null!");
            }

        }
    }


    public void setTopDelegate(LatteDelegate delegate){
        mTopDelegate = delegate;
    }

    public LatteDelegate getTopDelegate(){
        if(mTopDelegate == null) {
            mTopDelegate = this;
        }
       return  mTopDelegate;
    }


    /**
     * Gets the WebView.
     */
    public WebView getWebView() {
        if(mWebView == null) {
            throw  new NullPointerException("WebView is null");
        }
        return mIsWebViewAvailable ? mWebView : null;
    }


    public String getUrl() {
        if(mUrl == null) {
            throw  new NullPointerException("mUrl is null");
        }
        return mUrl;
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mWebView != null) {
            mWebView.onPause();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if(mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAvailable = false;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        }
    }
}
