package com.diabin.latte.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.diabin.latte.app.ui.recycler.DataConverter;
import com.diabin.latte.app.ui.recycler.ItemType;
import com.diabin.latte.app.ui.recycler.MultipleFields;
import com.diabin.latte.app.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * package: com.diabin.latte.ec.main.sort.list
 * author: chengguang
 * created on: 2019/2/12 上午10:09
 * description: 分类左侧 list 数据转换
 */
public class VerticalListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("list");

        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");


            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE,ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.TEXT,name)
                    .setField(MultipleFields.TAG,false)
                    .build();

            dataList.add(entity);
            // 设置第一个默认选中
            dataList.get(0).setField(MultipleFields.TAG,true);
        }
        return dataList;
    }
}
