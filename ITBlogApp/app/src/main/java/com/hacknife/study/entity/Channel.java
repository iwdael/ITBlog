package com.hacknife.study.entity;


public class Channel implements ITag {

    private long id;
    private String key;
    private String name;
    private int status;
    private String thumbnail;
    private int weight;

    public long getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"key\":\'" + key + "\'" +
                ", \"name\":\'" + name + "\'" +
                ", \"status\":" + status +
                ", \"thumbnail\":\'" + thumbnail + "\'" +
                ", \"weight\":" + weight +
                '}';
    }
}