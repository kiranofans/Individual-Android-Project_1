package Model;

import com.google.gson.annotations.SerializedName;

public class PostsListItems implements Base_Items_Model {

    private String newMemberPosts,hashTags,newPosts,threads,authorities;
    @SerializedName("author")
    private String authors;

    @SerializedName("replies")//num of replies
    private String replies;

    @SerializedName("reply")
    private String usrReply; //people's replies

    @SerializedName("subject")
    private String postTitles;

    @SerializedName("dateline")
    private String post_dates;

    @SerializedName("lastposter")
    private String lastReplies;

    private String authorIds,viewers;

    @SerializedName("lastpost")
    private String lastReplyDate;
    private String bannerImgURL,admins;

    @SerializedName("pid")
    private String pid;

    @SerializedName("tid")
    private String tid;

    @SerializedName("avatar")
    private String avatarUrls;

    private String postGroupId,messages;
    public PostsListItems(){ }
    public PostsListItems(String bannerImgURL,String admins)
    {
        this.bannerImgURL=bannerImgURL;
        this.admins=admins;
    }

    public PostsListItems(String postTitles, String authors,
                          String lastReplies,String post_dates) {
        this.authors = authors;
        this.postTitles = postTitles;
        this.lastReplies = lastReplies;//new replies or last poster
        this.post_dates= post_dates;
    }

    public PostsListItems(String authorIds, String avatarUrls, String messages) {
        this.authorIds = authorIds;
        this.avatarUrls = avatarUrls;
        this.messages = messages;
    }
    public PostsListItems(String tid){
        this.tid=tid;
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

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPostTitles() {
        return postTitles;
    }

    public void setPostTitles(String postTitles) {
        this.postTitles = postTitles;
    }

    public String getPost_dates() {
        return post_dates;
    }

    public void setPost_dates(String post_dates) {
        this.post_dates = post_dates;
    }

    public String getLastReplies() {
        return lastReplies;
    }

    public void setLastReplies(String lastReplies) {
        this.lastReplies = lastReplies;
    }

    public String getLastReplyDate() {
        return lastReplyDate;
    }

    public void setLastReplyDate(String lastReplyDate) {
        this.lastReplyDate = lastReplyDate;
    }

    public String getTid(){
        return tid;
    }
}
