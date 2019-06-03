package Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import Utils.Constants;
import Utils.Utility;

public class PostListItemsMod implements Base_Items_Model, Serializable {
    private PostListItemsMod mPostListMod;
    private static PostListItemsMod postItem;

    private String newMemberPosts, hashTags, newPosts, threads, authorities;
    private String replies, postReaderReplies; //people's replies
    public String postTitles, postDates,postLastReplies;
    public String postAuthors, mPostThreadId;

    private String lastReplyDate;
    private String postViewers;
    private String bannerImgURL, postAvatarUrls;
    private String mPostId, authorIds, mPostGroupId;
    private String messages, admins;

    public PostListItemsMod() { }

    public static synchronized PostListItemsMod getInstance(){
        if(postItem != null){
            return postItem = new PostListItemsMod();
        }
        return postItem;
    }

    public PostListItemsMod(String bannerImgURL) {
        this.bannerImgURL = bannerImgURL;
    }

    public PostListItemsMod(List<Base_Items_Model> list,PostListItemsMod postItems, JSONObject response) throws JSONException {
        JSONObject var = response.getJSONObject("Variables");
        JSONArray threadArr = var.getJSONArray("forum_threadlist");

        //Post items data
        for (int i = 0; i < threadArr.length(); i++) {
            JSONObject threadObj = threadArr.getJSONObject(i);
            this.mPostThreadId = threadObj.getString("mPostThreadId");
            this.postTitles = threadObj.getString("subject");
            this.postAuthors = threadObj.getString("author");
            this.postLastReplies = threadObj.getString("lastposter");
            this.postDates = threadObj.getString("dateline");
            /*postItems = new PostListItemsMod(this.mPostThreadId, this.postTitles,
                    this.postAuthors,"Post at: "+this.postDates);
            getSpecialTIds(list,postItems);*/
            Utility.getSpecialThreadIds(list,this.mPostThreadId,postItems);
        }


    }

    public void getSpecialTIds(List<Base_Items_Model> list) {
        if (this.mPostThreadId.equals("47447") || this.mPostThreadId.equals("20425") || this.mPostThreadId.equals("232743")
                || mPostThreadId.equals("240477")) {
            list.add(postItem);
        } else {
            list.add(postItem);
        }
    }

    public PostListItemsMod(String postTitles, String postAuthors,
                            String postLastReplies, String postDates) {
        this.postAuthors = postAuthors;
        this.postTitles = postTitles;
        this.postLastReplies = postLastReplies;//new replies or last poster
        this.postDates = postDates;
    }

    public PostListItemsMod(String authorIds, String postAvatarUrls, String messages) {
        this.authorIds = authorIds;
        this.postAvatarUrls = postAvatarUrls;
        this.messages = messages;
    }

    public PostListItemsMod(String postTitles, String postDates) {
        this.postDates = postDates;
        this.postTitles = postTitles;
    }

    public String getReplies() {
        return replies;
    }
    public String getAuthorities() {
        return authorities;
    }
    public String getPostAvatarUrls() {
        return postAvatarUrls;
    }
    public String getMessages() {
        return messages;
    }
    public String getPostId() {
        return mPostId;
    }
    public String getBannerImgURL() {
        return bannerImgURL;
    }
    public String getAdmins() {
        return admins;
    }

    @Override
    public int getViewType() {
        return Constants.ViewTypes.POSTS_TYPES;
    }

    public String getmPostGroupId() {
        return mPostGroupId;
    }
    public String getAuthorIds() {
        return authorIds;
    }
    public String getPostViewers() {
        return postViewers;
    }
    public String getPostAuthors() {
        return postAuthors;
    }
    public String getPostTitles() {
        return postTitles;
    }
    public String getPostDates() {
        return postDates;
    }
    public String getPostLastReplies() {
        return postLastReplies;
    }
    public String getLastReplyDate() {
        return lastReplyDate;
    }
    public String getmPostThreadId() {
        return mPostThreadId;
    }
    public PostListItemsMod getmPostListMod() {
        return mPostListMod;
    }
}
