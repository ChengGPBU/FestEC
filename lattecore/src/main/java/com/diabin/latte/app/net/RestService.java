package com.diabin.latte.app.net;


import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
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
 * package: com.diabin.latte.app.net
 * author: chengguang
 * created on: 2018/12/8 下午7:08
 * description: service 统一接口
 */
public interface RestService {

    @GET
    Call<String> get(@Url String url, @QueryMap Map<String, Object> params);


    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String, Object> params);


    @POST
    Call<String> postRaw(@Url String url, @Body RequestBody body);


    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String, Object> params);

    @PUT
    Call<String> putRaw(@Url String url, @Body RequestBody body);


    @DELETE
    Call<String> delete(@Url String url, @QueryMap Map<String, Object> params);


    /**
     * 边下载 边写入
     *
     * @param url
     * @param params
     * @return
     */
    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * 上传接口
     *
     * @param url
     * @param file
     * @return
     */
    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);


}
