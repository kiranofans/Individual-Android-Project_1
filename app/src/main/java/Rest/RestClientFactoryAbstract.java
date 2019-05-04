package Rest;

import com.android.volley.RequestQueue;

public interface RestClientFactoryAbstract {
    RestClient make(RequestQueue queue);
    RestClient make(RequestQueue queue, RestClient.REST_CLIENT_VERSIONS version);
}
