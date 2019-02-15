package com.diabin.latte.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.diabin.latte.app.delegate.LatteDelegate;
import com.diabin.latte.app.net.RestClient;
import com.diabin.latte.app.net.callback.ISuccess;
import com.diabin.latte.ec.R;
import com.diabin.latte.ec.R2;

import java.util.List;

import butterknife.BindView;

/**
 * package: com.diabin.latte.ec.main.sort.content
 * author: chengguang
 * created on: 2019/2/12 上午9:44
 * description:
 */
public class ContentDelegate extends LatteDelegate {
    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;
    private List<SectionBean> mData = null;

    @BindView(R2.id.rv_content_list)
    RecyclerView mRecyclerView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    public static ContentDelegate newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_content_list;
    }


    private void initData() {
        RestClient.builder()
                .url("http://10.0.2.2:8080/latte/sort_content_data.json")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        mData = new SectionDataConverter().converter(response);
                        final SectionAdapter sectionAdapter = new SectionAdapter(R.layout.item_section_content,R.layout.item_section_header,mData);
                        mRecyclerView.setAdapter(sectionAdapter);
                    }
                })
                .build()
                .post();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        initData();
    }
}
