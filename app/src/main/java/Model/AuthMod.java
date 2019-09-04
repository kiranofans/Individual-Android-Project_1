package Model;

public class AuthMod {
    public static class Auth {
        String mAccessToken;
        String mAccessSecrete;

        public String getAccessToken(){
            return mAccessToken;
        }
        public void setAccessToken(String authToken){
            this.mAccessToken = authToken;
        }
        public String getAccessSecrete(){
            return mAccessSecrete;
        }
    }
}
