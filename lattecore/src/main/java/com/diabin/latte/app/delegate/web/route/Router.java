package com.diabin.latte.app.delegate.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.diabin.latte.app.delegate.LatteDelegate;
import com.diabin.latte.app.delegate.web.WebDelegate;
import com.diabin.latte.app.delegate.web.WebDelegateImpl;

/**
 * package: com.diabin.latte.app.delegate.web.route
 * author: chengguang
 * created on: 2019/2/12 下午2:53
 * description:
 */
public class Router {

    private Router() {

    }


    private static class Holder {
        private static final Router INSTANCE = new Router();
    }


    public static Router getInstance() {
        return Holder.INSTANCE;
    }


    public final boolean handleWebUrl(WebDelegate delegate, String url) {
        // 如果是一个电话
        if (url.contains("tel:")) {
            callPhone(delegate.getContext(), url);
            return true;
        }

        final LatteDelegate topDelegate = delegate.getTopDelegate();
        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        topDelegate.getSupportDelegate().start(webDelegate);
        return true;

    }

    private void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("WebView is null!");
        }
    }


    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/"+url);
    }


    private void loadPage(WebView webView, String url) {
        if(URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView,url);
        }else{
            loadLocalPage(webView,url);
        }

    }


    public void loadPage(WebDelegate delegate, String url) {
        loadPage(delegate.getWebView(), url);
    }


    private void callPhone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }
}
