package Model;

import Utility.Constants;

public class ForumsListItem implements Base_Items_Model{
    private String forumsTitles,description,subListsName;
    private String catListForums,fId,imgUrl,groupId;
    private String numOftodayPosts, threads,numOfposts;

    /**Constructors*/
    public ForumsListItem (){}
    public ForumsListItem(String fid){
        this.fId=fid;
    }
    public ForumsListItem(String forumsTitles, String description,
                          String todayPosts) {
        //inner forums
        this.forumsTitles = forumsTitles;
        this.description=description;
        this.numOftodayPosts=todayPosts;
    }

    public ForumsListItem(String subListsName, String description, String todayposts, String string){
        this.subListsName=subListsName;
    }
    public ForumsListItem(String threads, String numOfposts) {

        this.threads = threads;//threads
        this.numOfposts = numOfposts;
    }
    public String getForumsTitles() {
        return forumsTitles;
    }

    public void setForumsTitles(String forumsTitles) {
        this.forumsTitles = forumsTitles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubListsName() {
        return subListsName;
    }

    public void setSubListsName(String subListsName) {
        this.subListsName = subListsName;
    }

    public String getCatListForums() {
        return catListForums;
    }

    public void setCatListForums(String catListForums) {
        this.catListForums = catListForums;
    }
    public String getFid() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getNumOftodayPosts() {
        return numOftodayPosts;
    }

    public void setNumOftodayPosts(String numOftodayPosts) {
        this.numOftodayPosts = numOftodayPosts;
    }

    public String getThreads() {
        return threads;
    }

    public void setThreads(String threads) {
        this.threads = threads;
    }

    public String getNumOfposts() {
        return numOfposts;
    }

    public void setNumOfposts(String numOfposts) {
        this.numOfposts = numOfposts;
    }
    public String getImgUrl(){
        return imgUrl;
    }
    public void setImgUrl(String imgUrl){
        this.imgUrl=imgUrl;
    }
    public String getGroupId(){
        return groupId;
    }
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public int getViewType() {
        return Constants.ViewTypes.FORUMS_TYPES;
    }
}
