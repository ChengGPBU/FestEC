package com.diabin.festec.example;


import android.app.Application;
import android.support.annotation.Nullable;

import com.diabin.latte.app.Latte;
import com.diabin.latte.app.net.interceptors.DebugInterceptor;
import com.diabin.latte.app.util.log.LatteLogger;
import com.diabin.latte.ec.database.DatabaseManager;
import com.diabin.latte.ec.icon.FontEcModule;
import com.facebook.stetho.DumperPluginsProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

/**
 * package: com.diabin.festec.example
 * author: chengguang
 * created on: 2018/12/5 上午9:59
 * description:
 */
public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initProjectConfig();
        initFragmentationConfig();


        DatabaseManager.getInstance().init(this);

        // 初始化日志配置
        LatteLogger.initLogger();

        // 数据库工具查看 facebook
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());

    }

    private void initProjectConfig() {
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withLoaderDelayed(1000)
                .withApiHost("http://10.0.2.2/")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
    }

    private void initFragmentationConfig() {
        Fragmentation.builder()
                // 设置 栈视图 模式为 （默认）悬浮球模式   SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(true) // 实际场景建议.debug(BuildConfig.DEBUG)
                /*
                  可以获取到{@link me.yokeyword.fragmentation.exception.AfterSaveStateTransactionWarning}
                  在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                 */
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(@Nullable Exception e) {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();
    }
}
