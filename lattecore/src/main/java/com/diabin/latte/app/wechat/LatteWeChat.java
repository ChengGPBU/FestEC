package com.diabin.latte.app.wechat;

import android.app.Activity;

import com.diabin.latte.app.ConfigType;
import com.diabin.latte.app.Latte;
import com.diabin.latte.app.wechat.callbacks.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * package: com.diabin.latte.app.wechat
 * author: chengguang
 * created on: 2019/1/11 上午9:58
 * description:
 */
public class LatteWeChat {

    public static final String APP_ID = Latte.getConfiguration(ConfigType.WE_CHAT_APP_ID);
    public static final String APP_SECRET = Latte.getConfiguration(ConfigType.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mIWeChatSignInCallback = null;


    private static final class Holder{
        private static final LatteWeChat INSTANCE = new LatteWeChat();
    }


    public static LatteWeChat getInstance(){
        return Holder.INSTANCE;
    }


    private LatteWeChat() {
        final Activity activity = Latte.getConfiguration(ConfigType.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }


    public final IWXAPI getWXAPI(){
        return WXAPI;
    }


    public IWeChatSignInCallback getIWeChatSignInCallback(){
        return mIWeChatSignInCallback;
    }



    public LatteWeChat onSignInSuccess(IWeChatSignInCallback iWeChatSignInCallback){
        this.mIWeChatSignInCallback = iWeChatSignInCallback;
        return this;
    }


    public final void signIn(){
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }




}
