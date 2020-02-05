
package com.yamibo.bbs.data.Model.LoginMod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Message implements Serializable {

    @SerializedName("messageval")
    @Expose
    private String messageval;
    @SerializedName("messagestr")
    @Expose
    private String messagestr;

    public String getMessageval() {
        return messageval;
    }

    public void setMessageval(String messageval) {
        this.messageval = messageval;
    }

    public String getMessagestr() {
        return messagestr;
    }

    public void setMessagestr(String messagestr) {
        this.messagestr = messagestr;
    }

}
