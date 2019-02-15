package com.diabin.latte.app.delegate.web.event;

import android.widget.Toast;

/**
 * package: com.diabin.latte.app.delegate.web.event
 * author: chengguang
 * created on: 2019/2/12 下午5:12
 * description:
 */
public class TestEvent extends Event {
    @Override
    public String execute(String params) {
         Toast.makeText(getmContext(),params,Toast.LENGTH_LONG).show();
        return "";
    }
}
