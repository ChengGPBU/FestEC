package com.diabin.latte.app;

/**
 * package: com.diabin.latte.app
 * author: chengguang
 * created on: 2018/12/5 上午10:07
 * description: 配置枚举类
 */
public enum ConfigType {
    API_HOST, // 网络请求的域名
    APPLICATION_CONTEXT, // 全局Context
    CONFIG_READY, //配置是否完成
    ICON, //
    LOADER_DELAYED,
    INTERCEPTOR,//拦截器 集合
    WE_CHAT_APP_ID,
    WE_CHAT_APP_SECRET,
    ACTIVITY, // 主activity
    HANDLER,

}
