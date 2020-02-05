
package com.yamibo.bbs.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sublist implements Serializable {

    @SerializedName("fid")
    @Expose
    private String fid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("threads")
    @Expose
    private String threads;
    @SerializedName("posts")
    @Expose
    private String posts;
    @SerializedName("todayposts")
    @Expose
    private String todayposts;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThreads() {
        return threads;
    }

    public void setThreads(String threads) {
        this.threads = threads;
    }

    public String getPosts() {
        return posts;
    }

    public void setPosts(String posts) {
        this.posts = posts;
    }

    public String getTodayposts() {
        return todayposts;
    }

    public void setTodayposts(String todayposts) {
        this.todayposts = todayposts;
    }

}
