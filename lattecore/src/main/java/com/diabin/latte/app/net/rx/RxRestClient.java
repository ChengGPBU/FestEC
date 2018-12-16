package com.diabin.latte.app.net.rx;

import android.content.Context;

import com.diabin.latte.app.net.HttpMethod;
import com.diabin.latte.app.net.RestClientBuilder;
import com.diabin.latte.app.net.RestCreator;
import com.diabin.latte.app.net.RestService;
import com.diabin.latte.app.net.callback.IError;
import com.diabin.latte.app.net.callback.IFailure;
import com.diabin.latte.app.net.callback.IRequest;
import com.diabin.latte.app.net.callback.ISuccess;
import com.diabin.latte.app.net.callback.RequestCallBacks;
import com.diabin.latte.app.net.download.DownloadHandler;
import com.diabin.latte.app.ui.loader.LatteLoader;
import com.diabin.latte.app.ui.loader.LoaderStyle;

import java.io.File;
import java.net.URI;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * package: com.diabin.latte.app.net.rx
 * author: chengguang
 * created on: 2018/12/16 下午6:39
 * description:
 */
public class RxRestClient {

    // builder之后不允许改变值  所以使用final修饰
    private final String url;
    private static final WeakHashMap<String, Object> params = RestCreator.getParams();
    private final RequestBody body;
    private final LoaderStyle loaderStyle;
    private final Context context;
    private final File file; // 上传文件


    public RxRestClient(String url,
                      Map<String, Object> params,
                      RequestBody body,
                      File file,
                      Context context,
                      LoaderStyle loaderStyle) {
        this.url = url;
        params.putAll(params);
        this.body = body;
        this.file = file;
        this.context = context;
        this.loaderStyle = loaderStyle;

    }


    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }


    private Observable<String> request(HttpMethod method) {
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;


        if (loaderStyle != null) {
            LatteLoader.showLoading(context, loaderStyle);
        }

        switch (method) {
            case GET:
                observable = service.get(url, params);
                break;
            case POST:
                observable = service.post(url, params);
                break;

            case POST_RAW:
                observable = service.postRaw(url, body);
                break;
            case PUT:
                observable = service.put(url, params);
                break;
            case PUT_RAW:
                observable = service.putRaw(url, body);
                break;
            case DELETE:
                observable = service.delete(url, params);
                break;

            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), file);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

                observable = service.upload(url, body);
                break;
            default:
                break;
        }

      return observable;
    }


    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }


    public final Observable<String> post() {
        if (body == null) {
            return request(HttpMethod.POST);
        } else {
            if (!params.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return   request(HttpMethod.POST_RAW);
        }

    }


    public final Observable<String> put() {
        if (body == null) {
            return  request(HttpMethod.PUT);
        } else {
            if (!params.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return   request(HttpMethod.PUT_RAW);
        }

    }


    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }


    public final Observable<String> upload() {
        return request(HttpMethod.UPLOAD);
    }

    public final Observable<ResponseBody> download() {
        final Observable<ResponseBody> responseBodyObservable = RestCreator.getRxRestService().download(url,params);
        return responseBodyObservable;
    }
}
