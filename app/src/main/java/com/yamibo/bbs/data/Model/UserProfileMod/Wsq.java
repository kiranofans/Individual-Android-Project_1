
package com.yamibo.bbs.data.Model.UserProfileMod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Wsq implements Serializable {

    @SerializedName("wsq_apicredit")
    @Expose
    private Object wsqApicredit;

    public Object getWsqApicredit() {
        return wsqApicredit;
    }

    public void setWsqApicredit(Object wsqApicredit) {
        this.wsqApicredit = wsqApicredit;
    }

}
