package com.mtons.mblog.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.modules.data.PostTagVO;
import com.mtons.mblog.modules.data.PostVO;
import com.mtons.mblog.modules.entity.Channel;
import com.mtons.mblog.modules.entity.ChannelTag;
import com.mtons.mblog.modules.entity.Tag;
import com.mtons.mblog.modules.service.ChannelService;
import com.mtons.mblog.modules.service.PostSearchService;
import com.mtons.mblog.modules.service.PostService;
import com.mtons.mblog.modules.service.TagService;
import com.mtons.mblog.modules.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/app/api")
public class AppController {

	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private TagService tagService;

	@Autowired
	private PostSearchService postSearchService;

	@RequestMapping(value = "/page")
	public List<PostVO> page(HttpServletRequest request) {
		int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 10);
		List<PostVO> ret = postService.paging(pageNo - 1, pageSize);
		int len = ret.size();
		for (int i = 0; i < len; i++) {
			ret.get(i).setAuthor(userService.get(ret.get(i).getAuthorId()));
			ret.get(i).setChannel(channelService.getById(ret.get(i).getChannelId()));
		}
		return ret;
	}

	@RequestMapping(value = "/search")
	public List<PostVO> search(HttpServletRequest request) {
		String kw = ServletRequestUtils.getStringParameter(request, "kw", "");
		if (kw == null || kw.equalsIgnoreCase("")) {
			return new ArrayList<PostVO>();
		}
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 10);
		int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 1);

		Sort sort = Sort.unsorted();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		 List<PostVO> ret = postSearchService.search(pageable, kw).getContent();
		int len = ret.size();
		for (int i = 0; i < len; i++) {
			ret.get(i).setAuthor(userService.get(ret.get(i).getAuthorId()));
			ret.get(i).setChannel(channelService.getById(ret.get(i).getChannelId()));
		}
		return ret;
	}

	@RequestMapping(value = "/article")
	public PostVO acticle(HttpServletRequest request) {
		long articleId = ServletRequestUtils.getIntParameter(request, "id", 1);
		PostVO ret = postService.get(articleId);
		ret.setAuthor(userService.get(ret.getAuthorId()));
		ret.setChannel(channelService.getById(ret.getChannelId()));
		return ret;
	}

	@RequestMapping(value = "/channelAndTag")
	public ChannelTag channelAndTag(HttpServletRequest request) {
		return new ChannelTag(channelService.findAll(0), tagService.findAll());
	}

	@RequestMapping(value = "/channel")
	public List<Channel> channel(HttpServletRequest request) {
		return channelService.findAll(0);
	}

	@RequestMapping(value = "/channelPage")
	public List<PostVO> channelPage(HttpServletRequest request) {
		int id = ServletRequestUtils.getIntParameter(request, "id", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 10);
		int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 1);
		return postService
				.paging(PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Direction.DESC, "weight")), id, null)
				.getContent();
	}

	@RequestMapping(value = "/tag")
	public List<Tag> tag(HttpServletRequest request) {
		return tagService.findAll();
	}

	@RequestMapping(value = "/tagPage")
	public List<PostVO> tagPage(HttpServletRequest request) {
		Sort sort = Sort.by(Sort.Direction.DESC, "weight");
		String tag = ServletRequestUtils.getStringParameter(request, "tag", "");
		int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 10);
		int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 1);
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		List<PostTagVO> postTagVOs = tagService.pagingQueryPosts(pageable, tag).getContent();
		List<PostVO> postVOs = new ArrayList<PostVO>(postTagVOs.size());
		postTagVOs.forEach(new Consumer<PostTagVO>() {

			@Override
			public void accept(PostTagVO t) {
				postVOs.add(t.getPost());

			}
		});
		return postVOs;

	}
}
