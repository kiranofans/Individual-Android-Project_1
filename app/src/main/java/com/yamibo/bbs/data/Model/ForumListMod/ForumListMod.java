
package com.yamibo.bbs.data.Model.ForumListMod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.yamibo.bbs.data.Model.Variables;

public class ForumListMod {

    @SerializedName("Version")
    @Expose
    private String version;
    @SerializedName("Charset")
    @Expose
    private String charset;
    @SerializedName("Variables")
    @Expose
    private Variables variables;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public Variables getVariables() {
        return variables;
    }

    public void setVariables(Variables variables) {
        this.variables = variables;
    }

}
