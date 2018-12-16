package com.diabin.latte.app.net.download;

import android.os.AsyncTask;

import com.diabin.latte.app.net.RestCreator;
import com.diabin.latte.app.net.callback.IError;
import com.diabin.latte.app.net.callback.IFailure;
import com.diabin.latte.app.net.callback.IRequest;
import com.diabin.latte.app.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * package: com.diabin.latte.app.net.download
 * author: chengguang
 * created on: 2018/12/16 下午2:00
 * description:
 */
public class DownloadHandler {

    private final String url;
    private static final WeakHashMap<String, Object> params = RestCreator.getParams();
    private final IRequest iRequest;
    private final ISuccess iSuccess;
    private final IFailure iFailure;
    private final IError iError;
    private final String download_dir; // 文件下载目录
    private final String extension;  // 文件拓展
    private final String name; // 文件名称

    public DownloadHandler(String url, IRequest iRequest, ISuccess iSuccess, IFailure iFailure, String download_dir, String extension, String name, IError iError) {
        this.url = url;
        this.iRequest = iRequest;
        this.iSuccess = iSuccess;
        this.iFailure = iFailure;
        this.download_dir = download_dir;
        this.extension = extension;
        this.name = name;
        this.iError = iError;
    }

    public final void handlerDownload() {
        if(iRequest != null){
            iRequest.onRequestStart();
        }

        RestCreator.getRestService().download(url,params).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    final SaveFileTask task = new SaveFileTask(iRequest, iSuccess);
                    final ResponseBody body = response.body();
                    // 线程池执行
                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, download_dir, extension, body, name);
                    // 这里一定要注意判断，文件是否下载完全
                    if (task.isCancelled()) {
                        if (iRequest != null) {
                            iRequest.onRequestEnd();
                        }
                    }
                }else {
                    if(iError != null){
                        iError.onError(response.code(),response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                    if(iFailure != null) {
                        iFailure.onFailure();
                    }
            }
        });
    }


}
