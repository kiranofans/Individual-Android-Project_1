package Model;

public abstract class ListItems_Abstraction {
    public static final int LIST_TYPE=0;
    public static final int LIST_TYPE2=1;
    private String newMemberPosts,authors,authorities,hashTags,postTitles;
    private String newPosts,post_dates;
    private String lastReplies,threads,authorIds,lastReplyDate,viewers;
    private String forumsTitles,description,subListsName;
    private String fId,numOftodayPosts,numOfposts;
    private String label;
    public ListItems_Abstraction(String label){
        this.label=label;
    }

    public ListItems_Abstraction(String forumsTitles,String description,
                          String todayPosts) {
        //inner forums
        this.forumsTitles = forumsTitles;
        this.description=description;
        this.numOftodayPosts=todayPosts;
    }
    public ListItems_Abstraction(String threads,String numOfposts,String fid,String subLists) {
        this.threads = threads;//rank in forums
        this.numOfposts = numOfposts;
        this.fId=fid;
        this.subListsName=subLists;
    }
    public ListItems_Abstraction() {
        this.authors = authors;
        this.postTitles = postTitles;
        this.lastReplies = lastReplies;//new replies or last poster
        this.lastReplyDate=lastReplyDate;
        this.viewers=viewers;
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

    public String getViewers() {
        return viewers;
    }

    public void setViewers(String viewers) {
        this.viewers = viewers;
    }

    public static int getListType2() {
        return LIST_TYPE2;
    }

    public static int getListType() {
        return LIST_TYPE;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    abstract public int getType();

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

    public String getfId() {
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
}
