package com.diabin.latte.app.delegate.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.diabin.latte.app.delegate.web.WebDelegate;

/**
 * package: com.diabin.latte.app.delegate.web.event
 * author: chengguang
 * created on: 2019/2/12 下午4:34
 * description:
 */
public abstract class Event implements IEvent {
    private Context mContext = null;
    private String mAction = null;
    private WebDelegate mDelegate = null;
    private String mUrl = null;
    private WebView mWebView = null;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public String getmAction() {
        return mAction;
    }

    public void setmAction(String mAction) {
        this.mAction = mAction;
    }

    public WebDelegate getmDelegate() {
        return mDelegate;
    }

    public void setmDelegate(WebDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public WebView getmWebView() {
        return mWebView;
    }

    public void setmWebView(WebView mWebView) {
        this.mWebView = mWebView;
    }

}
