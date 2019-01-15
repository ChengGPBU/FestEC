package com.diabin.festec.example.generator;

import com.complier.EntryGenerator;
import com.diabin.latte.app.wechat.template.WXEntryTemplate;

/**
 * package: com.diabin.festec.example.generator
 * author: chengguang
 * created on: 2019/1/11 上午9:44
 * description:
 */


//com.GuangdaStaffapp
//com.diabin.festec.example

@EntryGenerator(
        packageName = "com.diabin.festec.example",
        entryTemplete = WXEntryTemplate.class
)
public interface WeChatEntry {
}
