package com.hacknife.study.adapter.viewholder;


import android.view.View;

import com.hacknife.study.adapter.base.BaseRecyclerViewHolder;
import com.hacknife.study.databinding.ItemArticleBinding;
import com.hacknife.study.entity.Article;

public class ArticleViewHolder extends BaseRecyclerViewHolder<Article, ItemArticleBinding> {

    public ArticleViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(Article entity) {
        binding.setEntity(entity);
    }


}