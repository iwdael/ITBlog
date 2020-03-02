
package com.hacknife.study.entity;
import java.util.List;


public class Article {

    private Author author;
    private long authorId;
    private Channel channel;
    private long channelId;
    private long comments;
    private String content;
    private long created;
    private String editor;
    private long favors;
    private long featured;
    private long id;
    private int status;
    private String summary;
    private String tags;
    private List<String> tagsArray;
    private String thumbnail;
    private String title;
    private long views;
    private long weight;

    public Author getAuthor() {
        return author;
    }

    public long getAuthorId() {
        return authorId;
    }

    public Channel getChannel() {
        return channel;
    }

    public long getChannelId() {
        return channelId;
    }

    public long getComments() {
        return comments;
    }

    public String getContent() {
        return content;
    }

    public long getCreated() {
        return created;
    }

    public String getEditor() {
        return editor;
    }

    public long getFavors() {
        return favors;
    }

    public long getFeatured() {
        return featured;
    }

    public long getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public String getSummary() {
        return summary;
    }

    public String getTags() {
        return tags;
    }

    public List<String> getTagsArray() {
        return tagsArray;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public long getViews() {
        return views;
    }

    public long getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "{" +
                "\"author\":" + author +
                ", \"authorId\":" + authorId +
                ", \"channel\":" + channel +
                ", \"channelId\":" + channelId +
                ", \"comments\":" + comments +
                ", \"content\":\'" + content + "\'" +
                ", \"created\":" + created +
                ", \"editor\":\'" + editor + "\'" +
                ", \"favors\":" + favors +
                ", \"featured\":" + featured +
                ", \"id\":" + id +
                ", \"status\":" + status +
                ", \"summary\":\'" + summary + "\'" +
                ", \"tags\":\'" + tags + "\'" +
                ", \"tagsArray\":" + tagsArray +
                ", \"thumbnail\":\'" + thumbnail + "\'" +
                ", \"title\":\'" + title + "\'" +
                ", \"views\":" + views +
                ", \"weight\":" + weight +
                '}';
    }
}