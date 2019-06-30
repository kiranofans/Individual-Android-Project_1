package Rest;

import com.android.volley.RequestQueue;

public class RestClientFactory {
    private static RestClientFactoryAbstract sFactory;

    public static RestClient instantiate(RequestQueue queue) {
        return instantiate(queue, RestClient.REST_CLIENT_VERSIONS.V1);
    }

    public static RestClient instantiate(RequestQueue queue, RestClient.REST_CLIENT_VERSIONS version) {
        if (sFactory == null) {
            sFactory = new RestClientFactoryDefault();
        }
        return sFactory.make(queue, version);
    }
}
