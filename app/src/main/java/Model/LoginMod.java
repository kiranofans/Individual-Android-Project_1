package Model;

import android.os.Message;
import android.util.Log;


import java.lang.reflect.Type;

public class LoginMod {

    private String charset;

    private String message;

    private String variables;

    private String version;

    private String messagestr;

    private String messageval;
    public LoginMod(){}

    /**@param message */
    public LoginMod(String message){
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
