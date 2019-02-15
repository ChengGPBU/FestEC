package com.diabin.latte.app.delegate.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.diabin.latte.app.delegate.web.chromeclient.WebChromeClientImpl;
import com.diabin.latte.app.delegate.web.client.WebViewClientImpl;
import com.diabin.latte.app.delegate.web.route.RouteKeys;
import com.diabin.latte.app.delegate.web.route.Router;

/**
 * package: com.diabin.latte.app.delegate.web
 * author: chengguang
 * created on: 2019/2/12 下午3:10
 * description:
 */
public class WebDelegateImpl extends WebDelegate {


    // 静态工厂方法创建
    public static WebDelegateImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getUrl() != null) {
            //用原生的方式模拟Web跳转并且进行页面加载
            Router.getInstance().loadPage(this, getUrl());
        }
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new  WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
