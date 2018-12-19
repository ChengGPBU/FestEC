package com.diabin.festec.example;




import android.util.Log;

import com.diabin.latte.app.activitys.ProxyActivity;
import com.diabin.latte.app.delegate.LatteDelegate;
import com.diabin.latte.ec.launcher.LauncherDelegate;
import com.diabin.latte.ec.launcher.LauncherScrollDelegate;


/**
 * delegate 本质上是一个Fragment
 */

public class ExampleActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelegate() {
        Log.e("ProxyActivity","setRootDelegate");
//        return new ExampleDelegate();
//        return new LauncherDelegate();
        return new LauncherScrollDelegate();
    }


}
