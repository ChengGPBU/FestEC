package com.diabin.latte.ec.main;

import com.diabin.latte.app.delegate.bottom.BaseBottomDelegate;
import com.diabin.latte.app.delegate.bottom.BottomItemDelegate;
import com.diabin.latte.app.delegate.bottom.BottomTabBean;
import com.diabin.latte.app.delegate.bottom.ItemBuilder;
import com.diabin.latte.ec.main.car.ShopCarDelegate;
import com.diabin.latte.ec.main.discover.DiscoverDelegate;
import com.diabin.latte.ec.main.index.IndexDelegate;
import com.diabin.latte.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * package: com.diabin.latte.ec.main
 * author: chengguang
 * created on: 2019/1/16 上午9:58
 * description:  电商主页容器页
 */
public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}","分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}","发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"), new ShopCarDelegate());
//        items.put(new BottomTabBean("{fa-user}","我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDeletage() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return 0;
    }
}
