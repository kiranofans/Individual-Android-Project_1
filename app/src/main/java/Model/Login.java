package Model;

import android.os.Message;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Login{
    @SerializedName("Charset")
    @Expose
    private String charset;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Variables")
    @Expose
    private String variables;
    @SerializedName("Version")
    @Expose
    private String version;

    @SerializedName("messagestr")
    @Expose
    private String messagestr;
    @SerializedName("messageval")
    @Expose
    private String messageval;
    public Login(){}

    /**@param message */
    public Login(String message){
        this.message=message;
    }
    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getMessage() {
        return message;
    }
    /**@param message */
    public void setMessage(String message) {
        this.message = message;
    }

    public String getVariables() {
        return variables;
    }

    public void setVariables(String variables) {
        this.variables = variables;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    public String getMessagestr() {
        return messagestr;
    }
    /**@param messagestr */
    public void setMessagestr(String messagestr) {
        this.messagestr = messagestr;
    }


    public String getMessageval() {
        return messageval;
    }

    /**@param messageval */
    public void setMessageval(String messageval) {
        this.messageval = messageval;
    }




}
