package com.diabin.latte.app.net;

import com.diabin.latte.app.ConfigType;
import com.diabin.latte.app.Latte;
import com.diabin.latte.app.net.rx.RxRestService;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * package: com.diabin.latte.app.net
 * author: chengguang
 * created on: 2018/12/8 下午7:21
 * description: restful 接口 网络请求类的必要配置以及参数单例共享构造者
 */
public class RestCreator {

    private static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }


    public static WeakHashMap<String, Object> getParams() {
        return  ParamsHolder.PARAMS;
    }



    public static RestService getRestService() {
        return  RestServiceHolder.REST_SERVICE;
    }


    /**
     * 这是一种Java并发编程单例模式创建方式，也就是内部类Holder
     * 构造全局Retrofit客户端
     */
    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Latte.getConfigurations().get(ConfigType.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    private static final class OKHttpHolder {
        private static final int TIME_TOU = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        // 拦截器
        private static final ArrayList<Interceptor>  INTERCEPTORS = Latte.getConfiguration(ConfigType.INTERCEPTOR);

        private static OkHttpClient.Builder addInterceptor(){
            if(INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_TOU,TimeUnit.SECONDS)
                .build();
    }

    /**
     *     同样是一个静态内部类
     *     Service 接口
     */

    private static final class RestServiceHolder {
        private static final  RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }


    /**x
     * RxService 接口获取
     */


    private static final class RxRetrofitServiceHolder {
        private static final  RxRestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);

    }

    public static RxRestService getRxRestService() {
        return  RxRetrofitServiceHolder.REST_SERVICE;
    }



}
