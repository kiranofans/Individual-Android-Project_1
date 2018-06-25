package Model;

public class PostsListItems implements Base_Items_Model {
    private String newMemberPosts,authors,authorities,replies;
    private String hashTags,postTitles,newPosts,post_dates;
    private String lastReplies,threads,authorIds,lastReplyDate,viewers;
    private String postGroupId,avatarUrls,messages,pid,tid;
    public PostsListItems(){

    }
    public PostsListItems(String postTitles, String authors, String lastReplies,String post_dates) {
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
