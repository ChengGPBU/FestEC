package com.diabin.latte.app.net.callback;

/**
 * package: com.diabin.latte.app.net.callback
 * author: chengguang
 * created on: 2018/12/10 上午9:26
 * description: 错误结果的回调
 */
public interface IError {
    void onError(int code, String msg);
}
