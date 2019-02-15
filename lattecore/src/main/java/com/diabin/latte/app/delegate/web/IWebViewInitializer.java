package com.diabin.latte.app.delegate.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * package: com.diabin.latte.app.delegate.web
 * author: chengguang
 * created on: 2019/2/12 下午2:43
 * description: webview初始化接口
 */
public interface IWebViewInitializer {
    WebView initWebView(WebView webView);

    WebViewClient initWebViewClient();

    WebChromeClient initWebChromeClient();
}
