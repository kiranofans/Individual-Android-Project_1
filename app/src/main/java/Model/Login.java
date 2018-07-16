package Model;

import android.os.Message;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Login{
    @SerializedName("Charset")
    @Expose
    private JsonPrimitive charset;
    @SerializedName("Message")
    @Expose
    private JsonObject message;
    @SerializedName("Variables")
    @Expose
    private JsonObject variables;
    @SerializedName("Version")
    @Expose
    private JsonPrimitive version;

    @SerializedName("messagestr")
    @Expose
    private JsonPrimitive messagestr;
    @SerializedName("messageval")
    @Expose
    private JsonPrimitive messageval;
    private final static long serialVersionUID = -6019572768330337073L;
    public Login(){}

    /**@param message */
    public Login(JsonObject message){
        this.message=message;
    }
    public JsonPrimitive getCharset() {
        return charset;
    }

    public void setCharset(JsonPrimitive charset) {
        this.charset = charset;
    }

    public JsonObject getMessage() {
        return message;
    }
    /**@param message */
    public void setMessage(JsonObject message) {
        this.message = message;
    }

    public JsonObject getVariables() {
        return variables;
    }

    public void setVariables(JsonObject variables) {
        this.variables = variables;
    }

    public JsonPrimitive getVersion() {
        return version;
    }

    public void setVersion(JsonPrimitive version) {
        this.version = version;
    }
    public JsonPrimitive getMessagestr() {
        return messagestr;
    }
    /**@param messagestr */
    public void setMessagestr(JsonPrimitive messagestr) {
        this.messagestr = messagestr;
    }


    public JsonPrimitive getMessageval() {
        return messageval;
    }

    /**@param messageval */
    public void setMessageval(JsonPrimitive messageval) {
        this.messageval = messageval;
    }
    @Override
    public String toString() {

        return "Message{" +
                "'messagestr='"+messagestr +"'\'"+
                ",'messageval='"+messageval+"'\'" +
                "}";
    }

}
