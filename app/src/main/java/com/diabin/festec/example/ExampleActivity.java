package com.diabin.festec.example;




import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.Log;

import com.diabin.latte.app.activitys.ProxyActivity;
import com.diabin.latte.app.delegate.LatteDelegate;
import com.diabin.latte.ec.launcher.LauncherDelegate;
import com.diabin.latte.ec.launcher.LauncherScrollDelegate;
import com.diabin.latte.ec.login.SignInDelegate;
import com.diabin.latte.ec.login.SignUpDelegate;


/**
 * delegate 本质上是一个Fragment
 */

public class ExampleActivity extends ProxyActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
          actionBar.hide();
        }

    }

    @Override
    public LatteDelegate setRootDelegate() {
        Log.e("ProxyActivity","setRootDelegate");
//        return new ExampleDelegate();
//        return new LauncherDelegate();
        return new SignUpDelegate();

//        return new SignInDelegate();
    }


}
