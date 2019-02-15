package com.diabin.latte.app.delegate.web.chromeclient;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * package: com.diabin.latte.app.delegate.web.chromeclient
 * author: chengguang
 * created on: 2019/2/12 下午3:48
 * description:
 */
public class WebChromeClientImpl  extends WebChromeClient {

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }
}
