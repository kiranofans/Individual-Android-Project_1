package Model;

public class LoginMod extends ApiResponsesMod{
    Login mLogin;

    public Login getLogin(){
        return mLogin;
    }

    public static class Login{
        UsersMod.UserProfile mUserProfile;
        OAuthMod.OAuth mOauth;

        public UsersMod.UserProfile getUserProfile(){
            return mUserProfile;
        }
        public OAuthMod.OAuth getOAuth(){
            return mOauth;
        }
    }
}
