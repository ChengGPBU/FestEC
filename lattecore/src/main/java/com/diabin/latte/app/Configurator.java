package com.diabin.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * package: com.diabin.latte.app
 * author: chengguang
 * created on: 2018/12/5 上午10:02
 * description: 文件 数据 缓存 配置文件
 */
public class Configurator {
    //存放配置信息  在内存不足是自动释放 弱引用
    private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();


    //构造函数私有 外部无法直接new 需要通过该类内部获取实例
    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    // 单例模式
    static Configurator getInstance() {
        return Holder.INSTANCE;
    }


    public final WeakHashMap<String,Object>  getLatteConfigs(){
        return LATTE_CONFIGS;
    }

    // 静态内部内  《高效的java》 调用的时候初始化 Configurator
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }


    public final void configure() {
        //初始化字体
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
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
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }



    private void checkConfiguration() {
        // 使用 final 保证 变量不被修改
        final  boolean isReady  = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if(!isReady) {
            throw  new RuntimeException("Configuration is not ready,call configure");
        }
    }

    /**
     * 根据配置的枚举  获取对应的值
     * @param key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key) {
        // 首先检测是否已配置完成
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }





}
