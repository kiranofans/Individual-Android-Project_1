
package com.yamibo.bbs.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notice {

    @SerializedName("newpush")
    @Expose
    private String newpush;
    @SerializedName("newpm")
    @Expose
    private String newpm;
    @SerializedName("newprompt")
    @Expose
    private String newprompt;
    @SerializedName("newmypost")
    @Expose
    private String newmypost;

    public String getNewpush() {
        return newpush;
    }

    public void setNewpush(String newpush) {
        this.newpush = newpush;
    }

    public String getNewpm() {
        return newpm;
    }

    public void setNewpm(String newpm) {
        this.newpm = newpm;
    }

    public String getNewprompt() {
        return newprompt;
    }

    public void setNewprompt(String newprompt) {
        this.newprompt = newprompt;
    }

    public String getNewmypost() {
        return newmypost;
    }

    public void setNewmypost(String newmypost) {
        this.newmypost = newmypost;
    }

}
