package com.diabin.latte.app;


import android.app.Activity;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;


/**
 * package: com.diabin.latte.app
 * author: chengguang
 * created on: 2018/12/5 上午10:02
 * description: 配置文件
 */
public class Configurator {
    //存放配置信息  在内存不足是自动释放 弱引用
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    //拦截器集合
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();


    //构造函数私有 外部无法直接new 需要通过该类内部获取实例
    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY, false);
    }

    // 单例模式
    static Configurator getInstance() {
        return Holder.INSTANCE;
    }


    public final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    // 静态内部内  《高效的java》 调用的时候初始化 Configurator
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }


    public final void configure() {
        //初始化字体
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY, true);
    }

    //初始化字体库
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 0; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }


    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST, host);
        return this;
    }

    public final Configurator withLoaderDelayed(long delayed) {
        LATTE_CONFIGS.put(ConfigType.LOADER_DELAYED, delayed);
        return this;
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }


    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }


    public final Configurator withWeChatAppId(String appId){
        LATTE_CONFIGS.put(ConfigType.WE_CHAT_APP_ID, appId);
        return this;
    }

    public final Configurator withWeChatAppSecret(String appSecret){
        LATTE_CONFIGS.put(ConfigType.WE_CHAT_APP_SECRET, appSecret);
        return this;
    }



    public final Configurator withActivity(Activity activity){
        LATTE_CONFIGS.put(ConfigType.ACTIVITY, activity);
        return this;
    }

    private void checkConfiguration() {
        // 使用 final 保证 变量不被修改
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    /**
     * 根据配置的枚举  获取对应的值
     *
     * @param key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key) {
        // 首先检测是否已配置完成
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key);
    }


}
