package com.diabin.latte.app;

import com.diabin.latte.app.util.storage.LattePreference;

/**
 * package: com.diabin.latte.app
 * author: chengguang
 * created on: 2019/1/8 上午10:05
 * description: 管理用户信息
 */
public class AccountManager {

    // 登陆标识
    private enum SignTag{
        SIGN_TAG
    }
    //保存用户登录状态，登录后调用
    public static void setSignState(boolean state){
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn(){
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker){
        if (isSignIn()){
            checker.onSignIn();
        }else{
            checker.onNotSignIn();
        }
    }
}
