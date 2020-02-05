
package com.yamibo.bbs.data.Model.ForumsContentMod;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForumThreadMod implements Serializable {

    @SerializedName("tid")
    @Expose
    private String tid;
    @SerializedName("typeid")
    @Expose
    private String typeid;
    @SerializedName("readperm")
    @Expose
    private String readperm;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("authorid")
    @Expose
    private String authorid;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("dateline")
    @Expose
    private String dateline;
    @SerializedName("lastpost")
    @Expose
    private String lastpost;
    @SerializedName("lastposter")
    @Expose
    private String lastposter;
    @SerializedName("views")
    @Expose
    private String views;
    @SerializedName("replies")
    @Expose
    private String replies;
    @SerializedName("displayorder")
    @Expose
    private String displayorder;
    @SerializedName("digest")
    @Expose
    private String digest;
    @SerializedName("special")
    @Expose
    private String special;
    @SerializedName("attachment")
    @Expose
    private String attachment;
    @SerializedName("recommend_add")
    @Expose
    private String recommendAdd;
    @SerializedName("replycredit")
    @Expose
    private String replycredit;
    @SerializedName("dbdateline")
    @Expose
    private String dbdateline;
    @SerializedName("dblastpost")
    @Expose
    private String dblastpost;
    @SerializedName("rushreply")
    @Expose
    private String rushreply;
    @SerializedName("reply")
    @Expose
    private List<Reply> reply = null;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getReadperm() {
        return readperm;
    }

    public void setReadperm(String readperm) {
        this.readperm = readperm;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getLastpost() {
        return lastpost;
    }

    public void setLastpost(String lastpost) {
        this.lastpost = lastpost;
    }

    public String getLastposter() {
        return lastposter;
    }

    public void setLastposter(String lastposter) {
        this.lastposter = lastposter;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getReplies() {
        return replies;
    }

    public void setReplies(String replies) {
        this.replies = replies;
    }

    public String getDisplayorder() {
        return displayorder;
    }

    public void setDisplayorder(String displayorder) {
        this.displayorder = displayorder;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getRecommendAdd() {
        return recommendAdd;
    }

    public void setRecommendAdd(String recommendAdd) {
        this.recommendAdd = recommendAdd;
    }

    public String getReplycredit() {
        return replycredit;
    }

    public void setReplycredit(String replycredit) {
        this.replycredit = replycredit;
    }

    public String getDbdateline() {
        return dbdateline;
    }

    public void setDbdateline(String dbdateline) {
        this.dbdateline = dbdateline;
    }

    public String getDblastpost() {
        return dblastpost;
    }

    public void setDblastpost(String dblastpost) {
        this.dblastpost = dblastpost;
    }

    public String getRushreply() {
        return rushreply;
    }

    public void setRushreply(String rushreply) {
        this.rushreply = rushreply;
    }

    public List<Reply> getReply() {
        return reply;
    }

    public void setReply(List<Reply> reply) {
        this.reply = reply;
    }

}
