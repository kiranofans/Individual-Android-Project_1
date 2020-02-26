package com.yamibo.bbs.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.yamibo.bbs.data.Model.Base_Items_Model;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    private TextView sectionTitleTv;

    int firstPosition;
    int sectionedPosition;
    String title;
    SparseArray<Base_Items_Model> sectionList;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }
    public abstract void bind(T object);

}
