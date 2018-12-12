package com.diabin.festec.example;


import android.app.Application;

import com.diabin.latte.app.Latte;
import com.diabin.latte.ec.icon.FontEcModule;
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

    }

    private void initProjectConfig() {
        Latte.init(this).withIcon(new FontAwesomeModule()).withIcon(new FontEcModule()).withApiHost("http://127.0.0.1/").configure();
    }

    private void initFragmentationConfig() {
        Fragmentation.builder()
                // 设置 栈视图 模式为 （默认）悬浮球模式   SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(true) // 实际场景建议.debug(BuildConfig.DEBUG)
                /**
                 * 可以获取到{@link me.yokeyword.fragmentation.exception.AfterSaveStateTransactionWarning}
                 * 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                 */
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();
    }
}
