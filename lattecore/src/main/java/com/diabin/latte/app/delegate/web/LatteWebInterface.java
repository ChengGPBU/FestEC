package com.diabin.latte.app.delegate.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.diabin.latte.app.delegate.web.event.Event;
import com.diabin.latte.app.delegate.web.event.EventManager;
import com.diabin.latte.app.delegate.web.event.TestEvent;

import retrofit2.http.DELETE;


/**
 * package: com.diabin.latte.app.delegate.web
 * author: chengguang
 * created on: 2019/2/12 下午3:03
 * description:
 */
public class LatteWebInterface {

    private final WebDelegate DELEGATE;

    private LatteWebInterface(WebDelegate webDelegate) {
        DELEGATE = webDelegate;
    }

    static LatteWebInterface create(WebDelegate delegate) {
        return new LatteWebInterface(delegate);
    }


    // js 端调用这个方法
    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        EventManager.getInstance().addEvent("test",new TestEvent());
        final Event event = EventManager.getInstance().createEvent(action);
        if(event != null) {
            event.setmAction(action);
            event.setmDelegate(DELEGATE);
            event.setmContext(DELEGATE.getContext());
            event.setmUrl(DELEGATE.getUrl());
            return event.execute(params);
        }

        return "";
    }
}
