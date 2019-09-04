package Model;

public class LoginMod extends ApiResponsesMod{
    Login mLogin;

    public Login getLogin(){
        return mLogin;
    }

    public static class Login{
        UsersMod.UserProfile mUserProfile;
        AuthMod.Auth mOauth;

        public UsersMod.UserProfile getUserProfile(){
            return mUserProfile;
        }
        public AuthMod.Auth getOAuth(){
            return mOauth;
        }
    }
}
