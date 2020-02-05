
package com.yamibo.bbs.data.Model.ForumsContentMod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reply {

    @SerializedName("pid")
    @Expose
    private String pid;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("authorid")
    @Expose
    private String authorid;
    @SerializedName("message")
    @Expose
    private String message;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
