
package com.yamibo.bbs.data.Model.UserProfileMod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Space implements Serializable {

    @SerializedName("groupiconid")
    @Expose
    private String groupiconid;

    public String getGroupiconid() {
        return groupiconid;
    }

    public void setGroupiconid(String groupiconid) {
        this.groupiconid = groupiconid;
    }

}
