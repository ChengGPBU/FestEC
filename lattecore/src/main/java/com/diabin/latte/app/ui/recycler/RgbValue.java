package com.diabin.latte.app.ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * package: com.diabin.latte.app.ui.recycler
 * author: chengguang
 * created on: 2019/2/11 下午4:43
 * description:储存三原色
 */


@AutoValue
public abstract class RgbValue {
    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static RgbValue create(int red, int green, int blue) {
        return new AutoValue_RgbValue(red, green, blue);
    }
}

