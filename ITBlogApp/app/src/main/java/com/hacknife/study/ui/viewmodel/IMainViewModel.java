package com.hacknife.study.ui.viewmodel;


import com.hacknife.study.entity.Article;
import com.hacknife.study.entity.Channel;
import com.hacknife.study.entity.Tag;
import com.hacknife.study.ui.base.IBaseViewModel;

import java.util.List;


public interface IMainViewModel extends IBaseViewModel {

    void refresh();

    void loadMore();

    void articleLoad(List<Article> articles);

    void articleRefresh(List<Article> articles);

    void tags(List<Tag> tags);

    void channel(List<Channel> tags);

    void articleRefreshFail();

    void articleLoadFail();

}
