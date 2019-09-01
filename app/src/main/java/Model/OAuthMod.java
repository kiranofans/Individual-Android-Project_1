package Model;

public class OAuthMod {
    public static class OAuth{
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
