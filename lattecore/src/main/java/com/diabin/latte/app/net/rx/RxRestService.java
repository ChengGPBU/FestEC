package com.diabin.latte.app.net.rx;

import java.util.Map;


import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * package: com.diabin.latte.app.net.rx
 * author: chengguang
 * created on: 2018/12/16 下午6:37
 * description: rx版本RestService
 */
public interface RxRestService {


    @GET
    Observable<String> get(@Url String url, @QueryMap Map<String, Object> params);


    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap Map<String, Object> params);


    @POST
    Observable<String> postRaw(@Url String url, @Body RequestBody body);


    @FormUrlEncoded
    @PUT
    Observable<String> put(@Url String url, @FieldMap Map<String, Object> params);

    @PUT
    Observable<String> putRaw(@Url String url, @Body RequestBody body);


    @DELETE
    Observable<String> delete(@Url String url, @QueryMap Map<String, Object> params);


    /**
     * 边下载 边写入
     *
     * @param url
     * @param params
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * 上传接口
     *
     * @param url
     * @param file
     * @return
     */
    @Multipart
    @POST
    Observable<String> upload(@Url String url, @Part MultipartBody.Part file);

}
