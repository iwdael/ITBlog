package com.hacknife.study.adapter.base;

public interface OnItemClickListener<E> extends OnRecyclerViewListener<E> {
    void onItemClick(E t);
}
