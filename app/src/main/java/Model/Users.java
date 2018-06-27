package Model;

public class Users implements Base_Items_Model{
    private String username,usrPswd,usrID,credits,usrAvatarUrl;
    private Boolean usrStatus;//for active user fragment
    private String notices, othersReplies,usrReplies,groupID;
    private String readAccess,usrSpace;

    public Users(){}
    public Users(String notices,String imgUrl,String username, String usrId,String groupId) {
        this.notices=notices;
        this.usrAvatarUrl=imgUrl;
        this.username = username;
        this.usrID = usrId;
        this.groupID=groupId;
    }
    public Users (String usrAvatarUrl, String username, String space, Boolean status){
        this.usrAvatarUrl=usrAvatarUrl;
        this.username = username;
        this.usrSpace=space;
        this.usrStatus = status;
    }

    public Users (String usrID, String username, String readAccess, String notices){
        this.usrID=usrID;
        this.username = username;
        this.readAccess=readAccess;
        this.notices=notices;
    }
    public Users (String receivedNotices,String othersReplies,String usrReplies){
        this.notices=receivedNotices;
        this.othersReplies=othersReplies;
        this.usrReplies=usrReplies;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getUsrAvatarUrl() {
        return usrAvatarUrl;
    }

    public void setUsrAvatarUrl(String usrAvatarUrl) {
        this.usrAvatarUrl = usrAvatarUrl;
    }

    public String getNotices() {
        return notices;
    }

    public void setNotices(String notices) {
        this.notices = notices;
    }

    public String getOthersReplies() {
        return othersReplies;
    }

    public void setOthersReplies(String othersReplies) {
        this.othersReplies = othersReplies;
    }

    public String getUsrReplies() {
        return usrReplies;
    }

    public void setUsrReplies(String usrReplies) {
        this.usrReplies = usrReplies;
    }

    public String getReadAccess() {
        return readAccess;
    }

    public void setReadAccess(String readAccess) {
        this.readAccess = readAccess;
    }

    public String getUsrSpace() {
        return usrSpace;
    }

    public void setUsrSpace(String usrSpace) {
        this.usrSpace = usrSpace;
    }

    public String getGroupID(){
        return groupID;
    }
    public void setGroupID(String groupID){
        this.groupID=groupID;
    }
    public String getUsrID(){
        return usrID;
    }
    public void setUsrID(String usrID){
        this.usrID=usrID;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsrPswd() {
        return usrPswd;
    }

    public void setUsrPswd(String usrPswd) {
        this.usrPswd = usrPswd;
    }

    public Boolean getUsrStatus() {
        return usrStatus;
    }

    public void setUsrStatus(Boolean usrStatus) {
        this.usrStatus = usrStatus;
    }

    @Override
    public int getViewType() {
        return Constants.ViewTypes.USERS;
    }
}
