package az.code.portbim.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

@Service
public class ForgeAuthService {
    private static final String TOKEN_URL = "https://developer.api.autodesk.com/authentication/v2/token";
    private static final String CLIENT_ID = "5PWWhxLGbWuNq57RM2G88ArO1maApDt0ldTjDCA8DUDHaiqG";  // Replace with your client ID
    private static final String CLIENT_SECRET = "MqGSa8AFJQTVATKp16nsedxfvFlb9FelUZp1MSkpKHz6AZRC00NSQlL4gYUPHRxp";  // Replace with your client secret

    public String getAccessToken() throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(TOKEN_URL);
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");

            StringEntity entity = new StringEntity(
                    "grant_type=client_credentials&client_id=" + CLIENT_ID +
                            "&client_secret=" + CLIENT_SECRET + "&scope=data:read data:write"
            );
            post.setEntity(entity);
            HttpResponse response = client.execute(post);
            HttpEntity responseEntity = response.getEntity();
            return EntityUtils.toString(responseEntity);  // Parse token from response as needed
        }
    }
}
