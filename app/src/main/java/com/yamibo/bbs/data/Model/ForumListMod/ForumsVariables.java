package com.yamibo.bbs.data.Model.ForumListMod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.yamibo.bbs.data.Model.ForumsContentMod.Group;
import com.yamibo.bbs.data.Model.Notice;

import java.io.Serializable;
import java.util.List;

public class ForumsVariables implements Serializable {

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
    private Object ismoderator;
    @SerializedName("readaccess")
    @Expose
    private String readaccess;
    @SerializedName("notice")
    @Expose
    private Notice notice;
    @SerializedName("member_email")
    @Expose
    private Object memberEmail;
    @SerializedName("member_credits")
    @Expose
    private String memberCredits;
    @SerializedName("setting_bbclosed")
    @Expose
    private String settingBbclosed;
    @SerializedName("group")
    @Expose
    private Group group;
    @SerializedName("catlist")
    @Expose
    private List<CatlistMod> catlist = null;
    @SerializedName("forumlist")
    @Expose
    private List<ForumsListInfoMod> forumlist = null;

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

    public Object getIsmoderator() {
        return ismoderator;
    }

    public void setIsmoderator(Object ismoderator) {
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

    public Object getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(Object memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberCredits() {
        return memberCredits;
    }

    public void setMemberCredits(String memberCredits) {
        this.memberCredits = memberCredits;
    }

    public String getSettingBbclosed() {
        return settingBbclosed;
    }

    public void setSettingBbclosed(String settingBbclosed) {
        this.settingBbclosed = settingBbclosed;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<CatlistMod> getCatlist() {
        return catlist;
    }

    public void setCatlist(List<CatlistMod> catlist) {
        this.catlist = catlist;
    }

    public List<ForumsListInfoMod> getForumlist() {
        return forumlist;
    }

    public void setForumlist(List<ForumsListInfoMod> forumlist) {
        this.forumlist = forumlist;
    }

}
