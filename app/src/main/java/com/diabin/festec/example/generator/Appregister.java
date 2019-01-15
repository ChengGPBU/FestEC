package com.diabin.festec.example.generator;

import com.complier.AppRegisterGenerator;
import com.diabin.latte.app.wechat.template.AppRegisterTemplate;

/**
 * package: com.diabin.festec.example.generator
 * author: chengguang
 * created on: 2019/1/11 上午9:46
 * description:
 */


//com.GuangdaStaffapp
    //com.diabin.festec.example


@AppRegisterGenerator(
        packageName = "com.diabin.festec.example",
        registerTemplate = AppRegisterTemplate.class
)
public interface Appregister {
}
