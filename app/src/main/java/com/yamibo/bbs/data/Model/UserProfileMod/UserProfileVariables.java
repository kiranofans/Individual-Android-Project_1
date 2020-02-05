
package com.yamibo.bbs.data.Model.UserProfileMod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.yamibo.bbs.data.Model.Notice;

import java.io.Serializable;

public class UserProfileVariables implements Serializable {

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
    @SerializedName("space")
    @Expose
    private Space space;
    @SerializedName("extcredits")
    @Expose
    private Extcredits extcredits;
    @SerializedName("wsq")
    @Expose
    private Wsq wsq;

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

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public Extcredits getExtcredits() {
        return extcredits;
    }

    public void setExtcredits(Extcredits extcredits) {
        this.extcredits = extcredits;
    }

    public Wsq getWsq() {
        return wsq;
    }

    public void setWsq(Wsq wsq) {
        this.wsq = wsq;
    }

}
