package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import Utils.Constants;

public class PostListItems implements Base_Items_Model,Serializable {
    @SerializedName("forum_threadlist")
    private PostListItems postList;
    private String newMemberPosts,hashTags,newPosts,threads,authorities;
    @SerializedName("author")
    @Expose
    private String authors;

    @SerializedName("replies")//num of replies
    private String replies;

    @SerializedName("reply")
    private String usrReply; //people's replies

    @SerializedName("subject")
    @Expose
    private String postTitles;

    @SerializedName("dateline")
    @Expose
    private String post_dates;

    @SerializedName("lastposter")
    @Expose
    private String lastReplies;

    private String authorIds,viewers;

    @SerializedName("lastpost")
    @Expose
    private String lastReplyDate;
    private String bannerImgURL,admins;

    @SerializedName("pid")
    private String pid;

    @SerializedName("tid")
    @Expose
    private String tid;

    @SerializedName("avatar")
    private String avatarUrls;

    private String postGroupId,messages;
    public PostListItems(){ }
    public PostListItems(String bannerImgURL)
    {
        this.bannerImgURL=bannerImgURL;
    }

    public PostListItems(String postTitles, String authors,
                         String lastReplies, String post_dates) {
        this.authors = authors;
        this.postTitles = postTitles;
        this.lastReplies = lastReplies;//new replies or last poster
        this.post_dates= post_dates;
    }

    public PostListItems(String authorIds, String avatarUrls, String messages) {
        this.authorIds = authorIds;
        this.avatarUrls = avatarUrls;
        this.messages = messages;
    }
    public PostListItems(String postTitles,String post_dates){
        this.post_dates=post_dates;
        this.postTitles=postTitles;
    }
    public String getReplies() {
        return replies;
    }

    public void setReplies(String replies) {
        this.replies = replies;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public String getAvatarUrls() {
        return avatarUrls;
    }

    public void setAvatarUrls(String avatarUrls) {
        this.avatarUrls = avatarUrls;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getBannerImgURL() {
        return bannerImgURL;
    }

    public void setBannerImgURL(String bannerImgURL) {
        this.bannerImgURL = bannerImgURL;
    }

    public String getAdmins() {
        return admins;
    }

    public void setAdmins(String admins) {
        this.admins = admins;
    }

    @Override
    public int getViewType() {
        return Constants.ViewTypes.POSTS_TYPES;
    }
    public String getPostGroupId(){
        return postGroupId;
    }
    public void setPostGroupId(String groupId){
        this.postGroupId=groupId;
    }
    public String getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(String authorIds) {
        this.authorIds = authorIds;
    }

    public String getViewers() {
        return viewers;
    }

    public void setViewers(String viewers) {
        this.viewers = viewers;
    }

    public String getAuthors() {
        return authors;
    }

    /**@param authors
     * The authors */
    public void setAuthors(String authors) {
        this.authors = authors;
    }

    /**@return
     * The postTitles*/
    public String getPostTitles() {
        return postTitles;
    }

    /** @param postTitles
     * The postTitles*/
    public void setPostTitles(String postTitles) {
        this.postTitles = postTitles;
    }

    /**@return
     * The post_dates*/
    public String getPost_dates() {
        return post_dates;
    }

    /** @param post_dates
     * The post_dates*/
    public void setPost_dates(String post_dates) {
        this.post_dates = post_dates;
    }

    /**@return
     * The lastReplies */
    public String getLastReplies() {
        return lastReplies;
    }

    /** @param lastReplies
     * the lastReplies*/
    public void setLastReplies(String lastReplies) {
        this.lastReplies = lastReplies;
    }

    public String getLastReplyDate() {
        return lastReplyDate;
    }

    public void setLastReplyDate(String lastReplyDate) {
        this.lastReplyDate = lastReplyDate;
    }

    /** @return
     * The tid */
    public String getTid(){
        return tid;
    }

    /** @param tid
     * the tid*/
    public void setTid(String tid){
        this.tid=tid;
    }

    public PostListItems getPostList() {
        return postList;
    }

    public void setPostList(PostListItems postList) {
        this.postList = postList;
    }
}
