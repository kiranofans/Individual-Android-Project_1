
package com.yamibo.bbs.data.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.yamibo.bbs.data.Model.ForumsContentMod.Forum;
import com.yamibo.bbs.data.Model.ForumsContentMod.ForumThreadMod;
import com.yamibo.bbs.data.Model.ForumsContentMod.Group;
import com.yamibo.bbs.data.Model.ForumsContentMod.Threadtypes;

public class Variables {

    @SerializedName("cookiepre")
    @Expose
    private String cookiepre;
    @SerializedName("auth")
    @Expose
    private Object auth;
    @SerializedName("saltkey")
    @Expose
    private String saltkey;
    @SerializedName("member_uid")
    @Expose
    private String memberUid;
    @SerializedName("member_username")
    @Expose
    private String memberUsername;
    @SerializedName("member_avatar")
    @Expose
    private String memberAvatar;
    @SerializedName("groupid")
    @Expose
    private String groupid;
    @SerializedName("formhash")
    @Expose
    private String formhash;
    @SerializedName("ismoderator")
    @Expose
    private String ismoderator;
    @SerializedName("readaccess")
    @Expose
    private String readaccess;
    @SerializedName("notice")
    @Expose
    private Notice notice;
    @SerializedName("forum")
    @Expose
    private Forum forum;
    @SerializedName("group")
    @Expose
    private Group group;
    @SerializedName("forum_threadlist")
    @Expose
    private List<ForumThreadMod> forumThreadlist = null;
    @SerializedName("sublist")
    @Expose
    private List<Sublist> sublist = null;
    @SerializedName("tpp")
    @Expose
    private String tpp;
    @SerializedName("page")
    @Expose
    private String page;
    @SerializedName("reward_unit")
    @Expose
    private String rewardUnit;
    @SerializedName("threadtypes")
    @Expose
    private Threadtypes threadtypes;

    public String getCookiepre() {
        return cookiepre;
    }

    public void setCookiepre(String cookiepre) {
        this.cookiepre = cookiepre;
    }

    public Object getAuth() {
        return auth;
    }

    public void setAuth(Object auth) {
        this.auth = auth;
    }

    public String getSaltkey() {
        return saltkey;
    }

    public void setSaltkey(String saltkey) {
        this.saltkey = saltkey;
    }

    public String getMemberUid() {
        return memberUid;
    }

    public void setMemberUid(String memberUid) {
        this.memberUid = memberUid;
    }

    public String getMemberUsername() {
        return memberUsername;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }

    public String getMemberAvatar() {
        return memberAvatar;
    }

    public void setMemberAvatar(String memberAvatar) {
        this.memberAvatar = memberAvatar;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getFormhash() {
        return formhash;
    }

    public void setFormhash(String formhash) {
        this.formhash = formhash;
    }

    public String getIsmoderator() {
        return ismoderator;
    }

    public void setIsmoderator(String ismoderator) {
        this.ismoderator = ismoderator;
    }

    public String getReadaccess() {
        return readaccess;
    }

    public void setReadaccess(String readaccess) {
        this.readaccess = readaccess;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<ForumThreadMod> getForumThreadlist() {
        return forumThreadlist;
    }

    public void setForumThreadlist(List<ForumThreadMod> forumThreadlist) {
        this.forumThreadlist = forumThreadlist;
    }

    public List<Sublist> getSublist() {
        return sublist;
    }

    public void setSublist(List<Sublist> sublist) {
        this.sublist = sublist;
    }

    public String getTpp() {
        return tpp;
    }

    public void setTpp(String tpp) {
        this.tpp = tpp;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getRewardUnit() {
        return rewardUnit;
    }

    public void setRewardUnit(String rewardUnit) {
        this.rewardUnit = rewardUnit;
    }

    public Threadtypes getThreadtypes() {
        return threadtypes;
    }

    public void setThreadtypes(Threadtypes threadtypes) {
        this.threadtypes = threadtypes;
    }

}
