package com.diabin.latte.app.delegate.web.client;

import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.diabin.latte.app.delegate.web.WebDelegate;
import com.diabin.latte.app.delegate.web.route.Router;
import com.diabin.latte.app.util.log.LatteLogger;

/**
 * package: com.diabin.latte.app.delegate.web.client
 * author: chengguang
 * created on: 2019/2/12 下午3:17
 * description:
 */
public class WebViewClientImpl extends WebViewClient {
    private final WebDelegate DELEGATE;


    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading",url);
        // 返回true 原生接管
        return Router.getInstance().handleWebUrl(DELEGATE,url);
    }
}
