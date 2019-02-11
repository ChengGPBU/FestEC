package com.diabin.latte.app.ui.recycler;

import java.util.ArrayList;

/**
 * package: com.diabin.latte.app.ui
 * author: chengguang
 * created on: 2019/1/22 上午9:35
 * description:  数据转换
 */
public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITYS = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL");
        }
        return mJsonData;
    }
}
