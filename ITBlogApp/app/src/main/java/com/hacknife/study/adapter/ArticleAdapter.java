package com.hacknife.study.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hacknife.study.R;
import com.hacknife.study.adapter.base.BaseRecyclerViewAdapter;
import com.hacknife.study.adapter.base.BaseRecyclerViewHolder;
import com.hacknife.study.adapter.viewholder.ArticleViewHolder;
import com.hacknife.study.entity.Article;

public class ArticleAdapter extends BaseRecyclerViewAdapter<Article, BaseRecyclerViewHolder> {

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArticleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, null));
    }

}
