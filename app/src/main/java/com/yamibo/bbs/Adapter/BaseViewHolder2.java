package com.yamibo.bbs.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseViewHolder2<T> extends RecyclerView.ViewHolder {
    public BaseViewHolder2(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bind(T object);
}
