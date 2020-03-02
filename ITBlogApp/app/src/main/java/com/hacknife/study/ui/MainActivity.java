package com.hacknife.study.ui;


import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hacknife.study.R;
import com.hacknife.study.adapter.ArticleAdapter;
import com.hacknife.study.adapter.TagsAdapter;
import com.hacknife.study.adapter.base.OnItemClickListener;
import com.hacknife.study.constant.ARGS;
import com.hacknife.study.databinding.ActivityMainBinding;
import com.hacknife.study.entity.Article;
import com.hacknife.study.entity.Channel;
import com.hacknife.study.entity.Tag;
import com.hacknife.study.ui.base.impl.BaseActivity;
import com.hacknife.study.ui.view.IMainView;
import com.hacknife.study.ui.viewmodel.IMainViewModel;
import com.hacknife.study.ui.viewmodel.impl.MainViewModel;

public class MainActivity extends BaseActivity<IMainViewModel, ActivityMainBinding> implements IMainView {

    @Override
    protected IMainViewModel performViewModel() {
        return new MainViewModel(this, dataBinding);
    }

    @Override
    protected ActivityMainBinding performBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void init() {
        setSupportActionBar(dataBinding.toolbar);
        ArticleAdapter adapter = new ArticleAdapter();
        dataBinding.rcView.setAdapter(adapter);
        dataBinding.rcView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        dataBinding.refresh.setOnRefreshListener(refreshLayout -> viewModel.refresh());
        dataBinding.refresh.setOnLoadMoreListener(refreshLayout -> viewModel.loadMore());
        dataBinding.refresh.autoRefresh();
        adapter.setOnRecyclerViewListener((OnItemClickListener<Article>) article -> startActivity(ArticleActivity.class, ARGS.TITLE, article.getTitle(), ARGS.ID, article.getId()));
        final TagsAdapter<Tag> tagTagsAdapter = new TagsAdapter<>();
        final TagsAdapter<Channel> channelTagsAdapter = new TagsAdapter<>();
        dataBinding.flowTag.setAdapter(tagTagsAdapter);
        dataBinding.flowChannel.setAdapter(channelTagsAdapter);
        dataBinding.flowTag.setOnTagClickListener((view, position, parent) -> {
            startActivity(TagActivity.class, ARGS.TAG, tagTagsAdapter.getTag(position).getName());
            mSearchView.clearFocus();
            mSearchView.findViewById(R.id.search_close_btn).performClick();
            mSearchView.findViewById(R.id.search_close_btn).performClick();

            return false;
        });
        dataBinding.flowChannel.setOnTagClickListener((view, position, parent) -> {
            startActivity(ChannelActivity.class, ARGS.ID, channelTagsAdapter.getTag(position).getId(), ARGS.TAG, channelTagsAdapter.getTag(position).getName());
            mSearchView.clearFocus();
            mSearchView.findViewById(R.id.search_close_btn).performClick();
            mSearchView.findViewById(R.id.search_close_btn).performClick();
            return false;
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_search_view, menu);
        initSearch(menu);
        return true;
    }

    SearchView mSearchView;

    private void initSearch(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.menu_search_view);
        mSearchView = (SearchView) searchItem.getActionView();
        ((TextView) mSearchView.findViewById(R.id.search_src_text)).setTextColor(Color.WHITE);
        mSearchView.setOnQueryTextFocusChangeListener((v, hasFocus) -> dataBinding.refresh.setVisibility(hasFocus ? View.GONE : View.VISIBLE));
        mSearchView.setSubmitButtonEnabled(false);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.trim().length() > 0)
                    startActivity(SearchActivity.class, ARGS.SEARCH, mSearchView.getQuery().toString());
                mSearchView.clearFocus();
                mSearchView.findViewById(R.id.search_close_btn).performClick();
                mSearchView.findViewById(R.id.search_close_btn).performClick();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }
}
