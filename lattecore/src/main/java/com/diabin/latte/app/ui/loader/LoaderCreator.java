package com.diabin.latte.app.ui.loader;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * package: com.diabin.latte.app.ui
 * author: chengguang
 * created on: 2018/12/11 上午10:06
 * description: Loader创建
 */
public final class LoaderCreator {
    private static final String TAG = "LoaderCreator";

    private static final WeakHashMap<String,Indicator> LOADING_MAP = new WeakHashMap<>();


    /**
     *
     * @param type   // indicator的名称 不加 修饰词 包名内 调用
     * @param context
     * @return
     */
    static AVLoadingIndicatorView create(String type, Context context) {
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if(LOADING_MAP.get(type) == null) {
            final  Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type,indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    /**
     * 通过反射 创建对象实体
     * @param name
     * @return
     */
    private static Indicator getIndicator(String name) {
        if (TextUtils.isEmpty(name)){
            return null;
        }
        StringBuilder drawableClassName=new StringBuilder();
        if (!name.contains(".")){
         final    String defaultPackageName=AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        try {
         final  Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return  (Indicator) drawableClass.newInstance();

        } catch (Exception e) {
            Log.e(TAG,"Didn't find your class , check the name again !");
            return null;
        }
    }

}
