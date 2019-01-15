package com.diabin.festec.example.generator;

import com.complier.PayEntryGenerator;
import com.diabin.latte.app.wechat.template.WXPayEntryTemplate;

/**
 * package: com.diabin.festec.example.generator
 * author: chengguang
 * created on: 2019/1/11 上午9:46
 * description:
 */


@PayEntryGenerator(
        packageName = "com.diabin.festec.example",
        payEntryTemplete = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
