package Rest;
import com.android.volley.AuthFailureError;

import Utils.StringUtils;

// TODO: kill this when we don't need any other rest client than the one in FluxC
public class OAuthAuthenticator implements Authenticator {
    private AccessToken mAccessToken;

    public OAuthAuthenticator(AccessToken accessToken) {
        mAccessToken = accessToken;
    }

    @Override
    public void authenticate(final AuthenticatorRequest request) {
        request.sendWithAccessToken(StringUtils.notNullStr(mAccessToken.get()));
    }
}

