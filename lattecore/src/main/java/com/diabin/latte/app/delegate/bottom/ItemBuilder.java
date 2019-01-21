package com.diabin.latte.app.delegate.bottom;

import android.content.ClipData;

import java.util.LinkedHashMap;

/**
 * package: com.diabin.latte.app.delegate.bottom
 * author: chengguang
 * created on: 2019/1/16 上午9:17
 * description: tab item 构造
 */
public class ItemBuilder {
    // LinkedHashMap 有序 保证tab 不会乱序
    private final LinkedHashMap<BottomTabBean,BottomItemDelegate> ITEMS = new LinkedHashMap<>();


    static ItemBuilder builder() {
        return new ItemBuilder();
    }


    public final ItemBuilder addItem(BottomTabBean bean, BottomItemDelegate delegate){
       ITEMS.put(bean,delegate);
       return  this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean,BottomItemDelegate> items){
        ITEMS.putAll(items);
        return  this;
    }


    public final LinkedHashMap<BottomTabBean,BottomItemDelegate> build(){
        return ITEMS;
    }
}
