package com.diabin.latte.app.ui.recycler;


import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * package: com.diabin.latte.app.ui.recycler
 * author: chengguang
 * created on: 2019/1/25 上午9:11
 * description: MultipleItemEntity 的builder 类
 */
public class MultipleEntityBuilder {
    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public MultipleEntityBuilder(){
        //先清除之前的数据
        FIELDS.clear();
    }

    public final MultipleEntityBuilder setItemType(int itemType){
        FIELDS.put(MultipleFields.ITEM_TYPE, itemType);
        return this;
    }

    public final MultipleEntityBuilder setField(Object key, Object value){
        FIELDS.put(key, value);
        return this;
    }

    public final MultipleEntityBuilder setFileds(LinkedHashMap<?,?> map){
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build(){
        return new MultipleItemEntity(FIELDS);
    }
}
