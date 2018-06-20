package Model;

public abstract class Users {
    private String usrname,usrPswd,uId,credits,user_avarta;
    private Boolean usrStatus;//for active user fragment
    private int profileType;
    private int getProfileType(){
        return profileType;
    }

    public Users(String usrname, String usrPswd, Boolean usrStatus) {
        this.usrname = usrname;
        this.usrPswd = usrPswd;
        this.usrStatus = usrStatus;
    }

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public String getUsrPswd() {
        return usrPswd;
    }

    public void setUsrPswd(String usrPswd) {
        this.usrPswd = usrPswd;
    }

    public Boolean getUsrStatus() {
        return usrStatus;
    }

    public void setUsrStatus(Boolean usrStatus) {
        this.usrStatus = usrStatus;
    }
}
