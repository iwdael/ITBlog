package com.hacknife.study.adapter.base;


public interface OnItemLongClickListener<E> extends OnRecyclerViewListener<E> {
    void onItemLongClick(E t);
}
