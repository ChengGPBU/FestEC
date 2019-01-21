package com.diabin.latte.app.delegate.bottom;

/**
 * package: com.diabin.latte.app.delegate.bottom
 * author: chengguang
 * created on: 2019/1/16 上午9:15
 * description: Tab 实例类
 */
public class BottomTabBean {
    private final CharSequence ICON;
    private final CharSequence TITLE;

    public BottomTabBean(CharSequence icon, CharSequence title) {
        ICON = icon;
        TITLE = title;
    }

    public CharSequence getICON() {
        return ICON;
    }

    public CharSequence getTITLE() {
        return TITLE;
    }
}
