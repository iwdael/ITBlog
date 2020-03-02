package com.hacknife.study.ui.viewmodel.impl;

import com.hacknife.study.databinding.ActivityArticleBinding;
import com.hacknife.study.entity.Article;
import com.hacknife.study.ui.base.impl.BaseViewModel;
import com.hacknife.study.ui.model.IArticleModel;
import com.hacknife.study.ui.model.impl.ArticleModel;
import com.hacknife.study.ui.view.IArticleView;
import com.hacknife.study.ui.viewmodel.IArticleViewModel;


public class ArticleViewModel extends BaseViewModel<IArticleView, IArticleModel, ActivityArticleBinding> implements IArticleViewModel {


    public ArticleViewModel(IArticleView view, ActivityArticleBinding binding) {
        super(view, binding);
    }

    @Override
    protected void binding(ActivityArticleBinding binding) {
        binding.setViewModel(this);
    }

    @Override
    protected IArticleModel createModel() {
        return new ArticleModel(this);
    }


    @Override
    public void loadArticle(long id) {
        model.loadArticle(id);
    }

    @Override
    public void loadArticle(Article article) {
        binding.setArticle(article);
    }

    @Override
    public void loadFail() {
        view.loadFail();
    }
}
