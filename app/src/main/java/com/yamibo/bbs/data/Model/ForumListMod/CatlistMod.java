
package com.yamibo.bbs.data.Model.ForumListMod;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CatlistMod {

    @SerializedName("fid")
    @Expose
    private String fid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("forums")
    @Expose
    private List<String> forums = null;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getForums() {
        return forums;
    }

    public void setForums(List<String> forums) {
        this.forums = forums;
    }

}
