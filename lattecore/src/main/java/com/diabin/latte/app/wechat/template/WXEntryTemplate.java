package com.diabin.latte.app.wechat.template;

import com.diabin.latte.app.wechat.BaseWXEntryActivity;
import com.diabin.latte.app.wechat.LatteWeChat;

/**
 * package: com.diabin.latte.app.wechat.template
 * author: chengguang
 * created on: 2019/1/11 上午9:42
 * description:
 */
public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        // 关闭的时候 没有动画
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        // 微信登录  成功之后的回调
        LatteWeChat.getInstance().getIWeChatSignInCallback().onSignInSuccess(userInfo);
    }
}
