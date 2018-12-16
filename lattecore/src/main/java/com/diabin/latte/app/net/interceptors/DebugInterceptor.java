package com.diabin.latte.app.net.interceptors;

import android.support.annotation.RawRes;

import com.diabin.latte.app.util.file.FileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * package: com.diabin.latte.app.net.interceptors
 * author: chengguang
 * created on: 2018/12/16 下午3:40
 * description: Debug拦截器
 */
public class DebugInterceptor extends BaseInterceport {
    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debugUrl, int rawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = rawId;
    }


    private Response getResponse(Chain chain,String json) {
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type","application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"),json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .build();
    }


    private Response debugResponse(Chain chain, @RawRes int rawID) {
        final  String json = FileUtil.getRawFile(rawID);
        return getResponse(chain,json);

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final String url = chain.request().url().toString();
        if(url.contains(DEBUG_URL)) {
            return debugResponse(chain,DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());
    }
}
