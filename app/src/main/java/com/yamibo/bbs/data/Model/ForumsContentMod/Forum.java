
package com.yamibo.bbs.data.Model.ForumsContentMod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forum {

    @SerializedName("fid")
    @Expose
    private String fid;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("rules")
    @Expose
    private String rules;
    @SerializedName("picstyle")
    @Expose
    private String picstyle;
    @SerializedName("fup")
    @Expose
    private String fup;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("threads")
    @Expose
    private String threads;
    @SerializedName("posts")
    @Expose
    private String posts;
    @SerializedName("autoclose")
    @Expose
    private String autoclose;
    @SerializedName("threadcount")
    @Expose
    private String threadcount;
    @SerializedName("password")
    @Expose
    private String password;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getPicstyle() {
        return picstyle;
    }

    public void setPicstyle(String picstyle) {
        this.picstyle = picstyle;
    }

    public String getFup() {
        return fup;
    }

    public void setFup(String fup) {
        this.fup = fup;
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

    public String getAutoclose() {
        return autoclose;
    }

    public void setAutoclose(String autoclose) {
        this.autoclose = autoclose;
    }

    public String getThreadcount() {
        return threadcount;
    }

    public void setThreadcount(String threadcount) {
        this.threadcount = threadcount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
