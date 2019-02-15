package com.diabin.latte.app.delegate.web.event;

import com.diabin.latte.app.util.log.LatteLogger;

/**
 * package: com.diabin.latte.app.delegate.web.event
 * author: chengguang
 * created on: 2019/2/12 下午4:35
 * description:
 */
public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent", params);
        return null;
    }
}
