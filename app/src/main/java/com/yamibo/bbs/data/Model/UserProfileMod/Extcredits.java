
package com.yamibo.bbs.data.Model.UserProfileMod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Extcredits implements Serializable {

    @SerializedName("1")
    @Expose
    private com.yamibo.bbs.data.Model.UserProfileMod._1 _1;

    public com.yamibo.bbs.data.Model.UserProfileMod._1 get1() {
        return _1;
    }

    public void set1(com.yamibo.bbs.data.Model.UserProfileMod._1 _1) {
        this._1 = _1;
    }

}
