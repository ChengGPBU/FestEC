package com.diabin.festec.example.tinker;

import com.diabin.latte.app.Configurator;

/**
 * package: com.diabin.festec.example.tinker
 * author: chengguang
 * created on: 2019/2/13 下午7:01
 * description:
 */
public class Tinkerinitialization {
   public static class Holder{
       public static final Tinkerinitialization instance = new Tinkerinitialization();
   }


    // 单例模式
    static Tinkerinitialization getInstance() {
        return Tinkerinitialization.Holder.instance;
    }




}
