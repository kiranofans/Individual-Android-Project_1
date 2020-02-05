
package com.yamibo.bbs.data.Model.LoginMod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponseMod implements Serializable {

    @SerializedName("Version")
    @Expose
    private String version;
    @SerializedName("Charset")
    @Expose
    private String charset;
    @SerializedName("Variables")
    @Expose
    private LoginVariables variables;
    @SerializedName("Message")
    @Expose
    private Message message;

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

    public LoginVariables getVariables() {
        return variables;
    }

    public void setVariables(LoginVariables variables) {
        this.variables = variables;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

}
