package com.diabin.latte.ec.main.sort.content;

/**
 * package: com.diabin.latte.ec.main.sort.content
 * author: chengguang
 * created on: 2019/2/12 下午12:15
 * description: 分类商品对象
 */
public class SectionContentItemEntity {
    private int mGoodsId = 0;
    private String mGoodsName = null;
    private String mGoodsThumb = null;

    public int getmGoodsId() {
        return mGoodsId;
    }

    public void setmGoodsId(int mGoodsId) {
        this.mGoodsId = mGoodsId;
    }

    public String getmGoodsName() {
        return mGoodsName;
    }

    public void setmGoodsName(String mGoodsName) {
        this.mGoodsName = mGoodsName;
    }

    public String getmGoodsThumb() {
        return mGoodsThumb;
    }

    public void setmGoodsThumb(String mGoodsThumb) {
        this.mGoodsThumb = mGoodsThumb;
    }
}
