package com.hacknife.study.api;

import com.hacknife.study.entity.Article;
import com.hacknife.study.entity.Channel;
import com.hacknife.study.entity.Tag;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("app/api/page")
    Observable<List<Article>> page(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize);

    @GET("app/api/article")
    Observable<Article> article(@Query("id") long id);

    @GET("app/api/tag")
    Observable<List<Tag>> tags();

    @GET("app/api/channel")
    Observable<List<Channel>> channel();

    @GET("app/api/channelPage")
    Observable<List<Article>> channelPage(@Query("id") long id, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize);

    @GET("app/api/tagPage")
    Observable<List<Article>> tagPage(@Query("tag") String tag, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize);

    @GET("app/api/search")
    Observable<List<Article>> search(@Query("kw") String kw, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize);
}
