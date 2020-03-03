package com.mtons.mblog.modules.entity;

import java.util.List;

public class ChannelTag {
	private List<Channel> channels;
	private List<Tag> tags;

	public ChannelTag(List<Channel> channels, List<Tag> tags) {
		super();
		this.channels = channels;
		this.tags = tags;
	}

	public List<Channel> getChannels() {
		return channels;
	}

	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

}
