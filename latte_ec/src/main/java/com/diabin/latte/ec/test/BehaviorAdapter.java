package com.diabin.latte.ec.test;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * package: com.diabin.latte.ec.test
 * author: chengguang
 * created on: 2019/2/11 下午6:49
 * description:
 */
public class BehaviorAdapter extends BaseAdapter {
    private ArrayList list = null;

    private BehaviorAdapter() {
    }


    private BehaviorAdapter(ArrayList list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }



    private class ViewHolder  {

    }
}
