package com.diabin.latte.app;

import android.content.Context;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * package: com.diabin.latte.app
 * author: chengguang
 * created on: 2018/12/5 上午9:58
 * description: 对外工具类  不让外部继承
 */
public final class Latte {

    /**
     * 该初始化方法 已在主工程的application中调用
     * @param context
     * @return
     */
    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();

    }

    /**
     * 获取所有的配置信息
     * @return
     */
    public static HashMap<Object, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    /**
     * 根据枚举key获取对应的配置
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T getConfiguration(Enum<ConfigType> key){
        return Configurator.getInstance().getConfiguration(key);
    }


    public static Context getApplicationContext(){
        return (Context) getConfiguration(ConfigType.APPLICATION_CONTEXT);
    }
}


