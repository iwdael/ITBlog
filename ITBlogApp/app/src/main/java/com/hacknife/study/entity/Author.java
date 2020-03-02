package com.hacknife.study.entity;

public class Author {

    private String avatar;
    private long comments;
    private long created;
    private long id;
    private long lastLogin;
    private String name;
    private long posts;
    private String signature;
    private int status;
    private String username;

    public String getAvatar() {
        return avatar;
    }

    public long getComments() {
        return comments;
    }

    public long getCreated() {
        return created;
    }

    public long getId() {
        return id;
    }

    public long getLastLogin() {
        return lastLogin;
    }

    public String getName() {
        return name;
    }

    public long getPosts() {
        return posts;
    }

    public String getSignature() {
        return signature;
    }

    public int getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "{" +
                "\"avatar\":\'" + avatar + "\'" +
                ", \"comments\":" + comments +
                ", \"created\":" + created +
                ", \"id\":" + id +
                ", \"lastLogin\":" + lastLogin +
                ", \"name\":\'" + name + "\'" +
                ", \"posts\":" + posts +
                ", \"signature\":\'" + signature + "\'" +
                ", \"status\":" + status +
                ", \"username\":\'" + username + "\'" +
                '}';
    }
}