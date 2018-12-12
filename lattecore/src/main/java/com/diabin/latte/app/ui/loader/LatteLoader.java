package com.diabin.latte.app.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.diabin.latte.R;
import com.diabin.latte.app.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * package: com.diabin.latte.app.ui
 * author: chengguang
 * created on: 2018/12/12 上午9:31
 * description:
 */
public class LatteLoader {
    //缩放宽高比的默认值
    private static final int LOADER_SIZE_SCALE = 8;

    //偏移量
    private static final int LOADER_OFFSET_SCALE = 10;
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();


    public static void showLoading(Context context, Enum<LoaderStyle> type) {
        showLoading(context, type.name());
    }

    /**
     * 通过dialog 作为载体
     *
     * @param context
     * @param type
     */
    public static void showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);

        dialog.setContentView(avLoadingIndicatorView);

        int devicewidth = DimenUtil.getScreenWidth();
        int deviceheight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = devicewidth / LOADER_SIZE_SCALE;
            lp.height = deviceheight / LOADER_SIZE_SCALE;

            lp.height = lp.height + deviceheight / LOADER_OFFSET_SCALE;

            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }


    /**
     * 展示默认loading
     *
     * @param context
     */
    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }


    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        }
    }
}
