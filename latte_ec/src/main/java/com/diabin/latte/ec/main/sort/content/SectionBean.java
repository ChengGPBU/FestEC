package com.diabin.latte.ec.main.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * package: com.diabin.latte.ec.main.sort.content
 * author: chengguang
 * created on: 2019/2/12 下午12:14
 * description:
 */
public class SectionBean extends SectionEntity<SectionContentItemEntity> {
    private boolean mIsMore = false;
    private int mId = -1;


    public SectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SectionBean(SectionContentItemEntity sectionContentItemEntity) {
        super(sectionContentItemEntity);
    }

    public boolean isMore() {
        return mIsMore;
    }

    public void setMore(boolean more) {
        mIsMore = more;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }
}
