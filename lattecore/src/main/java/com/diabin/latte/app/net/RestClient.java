package com.diabin.latte.app.net;

import android.content.Context;

import com.diabin.latte.app.net.callback.IError;
import com.diabin.latte.app.net.callback.IFailure;
import com.diabin.latte.app.net.callback.IRequest;
import com.diabin.latte.app.net.callback.ISuccess;
import com.diabin.latte.app.net.callback.RequestCallBacks;
import com.diabin.latte.app.net.download.DownloadHandler;
import com.diabin.latte.app.ui.loader.LatteLoader;
import com.diabin.latte.app.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
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
    private static final WeakHashMap<String, Object> params = RestCreator.getParams();
    private final IRequest iRequest;
    private final ISuccess iSuccess;
    private final IFailure iFailure;
    private final String download_dir; // 文件下载目录
    private final String extension;  // 文件拓展
    private final String name; // 文件名称
    private final IError iError;
    private final RequestBody body;
    private final LoaderStyle loaderStyle;
    private final Context context;
    private final File file; // 上传文件


    public RestClient(String url,
                      Map<String, Object> params,
                      String downloadDir,
                      String extension,
                      String name,
                      IRequest iRequest,
                      ISuccess iSuccess,
                      IFailure iFailure,
                      IError iError,
                      RequestBody body,
                      File file,
                      Context context,
                      LoaderStyle loaderStyle) {
        this.url = url;
        params.putAll(params);
        this.iRequest = iRequest;
        this.iSuccess = iSuccess;
        this.iFailure = iFailure;
        this.iError = iError;
        this.body = body;
        this.file = file;
        this.context = context;
        this.loaderStyle = loaderStyle;
        this.download_dir = downloadDir;
        this.extension = extension;
        this.name = name;
    }


    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }


    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (iRequest != null) {
            iRequest.onRequestStart();
        }

        if (loaderStyle != null) {
            LatteLoader.showLoading(context, loaderStyle);
        }

        switch (method) {
            case GET:
                call = service.get(url, params);
                break;
            case POST:
                call = service.post(url, params);
                break;

            case POST_RAW:
                call = service.postRaw(url, body);
                break;
            case PUT:
                call = service.put(url, params);
                break;
            case PUT_RAW:
                call = service.putRaw(url, body);
                break;
            case DELETE:
                call = service.delete(url, params);
                break;

            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), file);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

                call = service.upload(url, body);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }

    }


    private Callback<String> getRequestCallback() {
        return new RequestCallBacks(iRequest, iSuccess, iFailure, iError, loaderStyle);
    }


    public final void get() {
        request(HttpMethod.GET);
    }


    public final void post() {
        if (body == null) {
            request(HttpMethod.POST);
        } else {
            if (!params.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }

    }


    public final void put() {
        if (body == null) {
            request(HttpMethod.PUT);
        } else {
            if (!params.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }

    }


    public final void delete() {
        request(HttpMethod.DELETE);
    }


    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    public final void download() {
        new DownloadHandler(url, iRequest, iSuccess, iFailure, download_dir, extension, name, iError).handlerDownload();
    }

}
