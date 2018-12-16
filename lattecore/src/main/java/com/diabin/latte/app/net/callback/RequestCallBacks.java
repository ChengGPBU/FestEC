package com.diabin.latte.app.net.callback;

import android.os.Handler;

import com.diabin.latte.app.ConfigType;
import com.diabin.latte.app.Latte;
import com.diabin.latte.app.ui.loader.LatteLoader;
import com.diabin.latte.app.ui.loader.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * package: com.diabin.latte.app.net.callback
 * author: chengguang
 * created on: 2018/12/10 下午8:04
 * description:
 */
public class RequestCallBacks implements Callback<String> {

    private final IRequest iRequest;
    private final ISuccess iSuccess;
    private final IFailure iFailure;
    private final IError iError;
    private final LoaderStyle loaderStyle;
    //Handler 尽量声明成static类型，这样可以避免一些内存泄漏
    private static final Handler handler = new Handler();

    public RequestCallBacks(IRequest iRequest, ISuccess iSuccess, IFailure iFailure, IError iError,LoaderStyle loaderStyle) {
        this.iRequest = iRequest;
        this.iSuccess = iSuccess;
        this.iFailure = iFailure;
        this.iError = iError;
        this.loaderStyle = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()) {
            if(call.isExecuted()) {
                if(iSuccess != null) {
                  iSuccess.onSuccess(response.body());
                }
            }
        }else {
            if(iError != null) {
                iError.onError(response.code(),response.message());
            }
        }
        stopLoading();

    }

    @Override
    public void onFailure(Call<String> call, Throwable throwable) {
            if(iFailure != null) {
                iFailure.onFailure();
            }
            if(iRequest != null) {
               iRequest.onRequestEnd();
            }
        stopLoading();
    }


    private void stopLoading() {
        if (loaderStyle != null) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            }, (Long) Latte.getConfiguration(ConfigType.LOADER_DELAYED));
        }
    }

}
