package com.diabin.latte.ec.main.car;

/**
 * package: com.diabin.latte.ec.main.car
 * author: chengguang
 * created on: 2019/2/14 上午9:37
 * description:
 */
public interface ICarItemListener {

    // item 被操作之后  传入总价
    void onItemClick(double itemTotalPrice);
}
