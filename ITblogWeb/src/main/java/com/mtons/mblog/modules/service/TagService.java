package com.mtons.mblog.modules.service;

import com.mtons.mblog.modules.data.PostTagVO;
import com.mtons.mblog.modules.data.TagVO;
import com.mtons.mblog.modules.entity.Tag;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author : langhsu
 */
public interface TagService {
	List<Tag> findAll();
    Page<TagVO> pagingQueryTags(Pageable pageable);
    Page<PostTagVO> pagingQueryPosts(Pageable pageable, String tagName);
    void batchUpdate(String names, long latestPostId);
    void deteleMappingByPostId(long postId);
}
