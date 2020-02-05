
package com.yamibo.bbs.data.Model.ForumsContentMod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Group {

    @SerializedName("groupid")
    @Expose
    private String groupid;
    @SerializedName("grouptitle")
    @Expose
    private String grouptitle;

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getGrouptitle() {
        return grouptitle;
    }

    public void setGrouptitle(String grouptitle) {
        this.grouptitle = grouptitle;
    }

}
