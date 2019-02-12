package com.diabin.festec.example;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.Toast;

import com.diabin.latte.app.Latte;
import com.diabin.latte.app.activitys.ProxyActivity;
import com.diabin.latte.app.delegate.LatteDelegate;
import com.diabin.latte.app.ui.launcher.ILauncherListener;
import com.diabin.latte.app.ui.launcher.OnLauncherFinishTag;
import com.diabin.latte.ec.launcher.LauncherDelegate;
import com.diabin.latte.ec.launcher.LauncherScrollDelegate;
import com.diabin.latte.ec.login.ISignListener;
import com.diabin.latte.ec.login.SignInDelegate;
import com.diabin.latte.ec.login.SignUpDelegate;
import com.diabin.latte.ec.main.EcBottomDelegate;
import com.diabin.latte.ec.test.NestDelegate;

import qiu.niorgai.StatusBarCompat;


/**
 * delegate 本质上是一个Fragment
 */

public class ExampleActivity extends ProxyActivity implements ISignListener, ILauncherListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        Latte.getConfigurator().withActivity(this);

        //translucent status bar
        StatusBarCompat.translucentStatusBar(this, true);

    }

    @Override
    public LatteDelegate setRootDelegate() {
        Log.e("ProxyActivity", "setRootDelegate");
        // 测试页
//        return new ExampleDelegate();
        // 启动页
//        return new LauncherDelegate();
        // 注册页
//        return new SignUpDelegate();
        // 登录页
//        return new SignInDelegate();
        // 电商首页
        return new EcBottomDelegate();

        // 测试Behavior
//        return new NestDelegate();
    }


    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户已登录", Toast.LENGTH_LONG).show();
                startWithPop(new ExampleDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户未登录", Toast.LENGTH_LONG).show();
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
