package com.diabin.latte.app.net.callback;

/**
 * package: com.diabin.latte.app.net.callback
 * author: chengguang
 * created on: 2018/12/10 上午9:28
 * description: 请求开始 结束的接口
 */
public interface IRequest {
    void onRequestStart();
    void onRequestEnd();
}
