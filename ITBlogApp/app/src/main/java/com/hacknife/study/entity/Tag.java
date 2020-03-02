package com.hacknife.study.entity;

public class Tag implements ITag {
    private long created;
    private String description;
    private long id;
    private long latestPostId;
    private String name;
    private long posts;
    private String thumbnail;
    private long updated;

    public long getCreated() {
        return created;
    }

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }

    public long getLatestPostId() {
        return latestPostId;
    }

    public String getName() {
        return name;
    }

    public long getPosts() {
        return posts;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public long getUpdated() {
        return updated;
    }
}
