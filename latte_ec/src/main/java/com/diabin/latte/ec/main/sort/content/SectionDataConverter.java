package com.diabin.latte.ec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.diabin.latte.app.ui.recycler.DataConverter;
import com.diabin.latte.app.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * package: com.diabin.latte.ec.main.sort.content
 * author: chengguang
 * created on: 2019/2/12 下午12:14
 * description:
 */
public class SectionDataConverter {

    final List<SectionBean> converter(String json){
        final List<SectionBean> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(json).getJSONArray("data");

        final int size = dataArray.size();
        for (int i = 0; i<size; i++){
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String title = data.getString("section");

            //添加title
            final SectionBean sectionTitleBean = new SectionBean(true, title);
            sectionTitleBean.setId(id);
            sectionTitleBean.setMore(true);
            dataList.add(sectionTitleBean);

            final JSONArray goods =  data.getJSONArray("goods");
            //商品内容循环
            final int goodsSize = goods.size();
            for (int j = 0; j < goodsSize; j++){
                final JSONObject contentItem = goods.getJSONObject(j);
                final int goodsId = contentItem.getInteger("goods_id");
                final String goodsName = contentItem.getString("goods_name");
                final String goodsThumb = contentItem.getString("goods_thumb");
                //获取内容
                final SectionContentItemEntity itemEntity = new SectionContentItemEntity();
                itemEntity.setmGoodsId(goodsId);
                itemEntity.setmGoodsName(goodsName);
                itemEntity.setmGoodsThumb(goodsThumb);
                //添加到dataList里面
                dataList.add(new SectionBean(itemEntity));
            }
            //商品内容循环结束
        }
        //Section循环结束
        return dataList;
    }

}
