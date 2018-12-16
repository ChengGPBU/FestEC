package com.diabin.festec.example;




import android.util.Log;

import com.diabin.latte.app.activitys.ProxyActivity;
import com.diabin.latte.app.delegate.LatteDelegate;



/**
 * delegate 本质上是一个Fragment
 */

public class ExampleActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelegate() {
        Log.e("ProxyActivity","setRootDelegate");
        return new ExampleDelegate();
    }


}
