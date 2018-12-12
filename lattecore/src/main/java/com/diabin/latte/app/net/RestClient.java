package com.diabin.latte.app.net;

import android.content.Context;

import com.diabin.latte.app.net.callback.IError;
import com.diabin.latte.app.net.callback.IFailure;
import com.diabin.latte.app.net.callback.IRequest;
import com.diabin.latte.app.net.callback.ISuccess;
import com.diabin.latte.app.net.callback.RequestCallBacks;
import com.diabin.latte.app.ui.loader.LatteLoader;
import com.diabin.latte.app.ui.loader.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * package: com.diabin.latte.app.net
 * author: chengguang
 * created on: 2018/12/8 下午7:00
 * description: 网络请求Client 建造者模式
 */
public class RestClient {
    // builder之后不允许改变值  所以使用final修饰
    private final String url;
    private static final WeakHashMap<String,Object> params = RestCreator.getParams();
    private final IRequest iRequest;
    private final ISuccess iSuccess;
    private final IFailure iFailure;
    private final IError iError;
    private final RequestBody body;
    private final LoaderStyle loaderStyle;
    private final Context context;

    public RestClient(String url,
                     Map<String,Object> params,
                      IRequest iRequest,
                      ISuccess iSuccess,
                      IFailure iFailure,
                      IError iError,
                      RequestBody body,
                      Context context,
                      LoaderStyle loaderStyle) {
        this.url = url;
        params.putAll(params);
        this.iRequest = iRequest;
        this.iSuccess = iSuccess;
        this.iFailure = iFailure;
        this.iError = iError;
        this.body = body;
        this.context = context;
        this.loaderStyle = loaderStyle;
    }


    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }


    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if(iRequest !=null ) {
            iRequest.onRequestStart();
        }

        if(loaderStyle != null) {
            LatteLoader.showLoading(context,loaderStyle);
        }

        switch (method) {
            case GET:
                call = service.get(url,params);
                break;
            case POST:
                call = service.post(url,params);
                break;
            case PUT:
                call = service.put(url,params);
                break;
            case DELETE:
                call = service.delete(url,params);
                break;
            default:
                break;
        }

        if(call != null) {
            call.enqueue(getRequestCallback());
        }

    }


    private Callback<String> getRequestCallback() {
        return new RequestCallBacks( iRequest,  iSuccess,  iFailure,  iError, loaderStyle);
    }


    public final  void get() {
        request(HttpMethod.GET);
    }


    public final  void post() {
        request(HttpMethod.POST);
    }


    public final  void put() {
        request(HttpMethod.PUT);
    }


    public final  void delete() {
        request(HttpMethod.DELETE);
    }

}
