package com.diabin.latte.ec.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.diabin.latte.app.delegate.LatteDelegate;
import com.diabin.latte.app.ui.recycler.ItemType;
import com.diabin.latte.app.ui.recycler.MultipleFields;
import com.diabin.latte.app.ui.recycler.MultipleItemEntity;
import com.diabin.latte.app.ui.recycler.MultipleRecyclerAdapter;
import com.diabin.latte.app.ui.recycler.MultipleViewHolder;
import com.diabin.latte.ec.R;
import com.diabin.latte.ec.main.sort.SortDelegate;
import com.diabin.latte.ec.main.sort.content.ContentDelegate;

import java.util.List;

import me.yokeyword.fragmentation.SupportHelper;

/**
 * package: com.diabin.latte.ec.main.sort.list
 * author: chengguang
 * created on: 2019/2/12 上午10:31
 * description: 分类左侧列表 adapter
 */
public class SortRecyclerAdapter extends MultipleRecyclerAdapter {

    private final SortDelegate DELEGATE;
    private int mPrePosition = 0;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     * @param delegate
     */
    protected SortRecyclerAdapter(List<MultipleItemEntity> data, SortDelegate delegate) {
        super(data);
        DELEGATE = delegate;
        // 添加垂直菜单布局
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);
    }


    // 创建一个Holder
    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }



    @Override
    protected void convert(final MultipleViewHolder holder,final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()){
            case ItemType.VERTICAL_MENU_LIST:
                final String text = entity.getField(MultipleFields.TEXT);
                final boolean isClick = entity.getField(MultipleFields.TAG);
                final AppCompatTextView name = holder.getView(R.id.tv_vertical_item_name);
//                final View line = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentPosition = holder.getAdapterPosition();
                        //还原上一个item
                        if (mPrePosition != currentPosition){
                            getData().get(mPrePosition).setField(MultipleFields.TAG, false);
                            notifyItemChanged(mPrePosition);

                            //更新选中的item
                            entity.setField(MultipleFields.TAG, true);
                            notifyItemChanged(currentPosition);
                            mPrePosition = currentPosition;

                            final int contentId = getData().get(currentPosition).getField(MultipleFields.ID);
                            showContent(contentId);
                        }
                    }
                });

                if (!isClick){
//                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));
                }else{
//                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
//                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                }

                holder.setText(R.id.tv_vertical_item_name, text);

                break;
            default:
                break;
        }
    }


    private void showContent(int contentId){
        final ContentDelegate delegate = ContentDelegate.newInstance(contentId);
        switchContent(delegate);
    }


    // 切换分类 右侧的fragment
    private void switchContent(ContentDelegate delegate){
        final LatteDelegate contentDelegate =
                SupportHelper.findFragment(DELEGATE.getChildFragmentManager(), ContentDelegate.class);
        if (contentDelegate!=null){
            contentDelegate.getSupportDelegate().replaceFragment(delegate, false);
        }
    }
}
