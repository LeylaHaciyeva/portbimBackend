package az.code.portbim.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForgeBucketService {

    @Autowired
    private ForgeAuthService authService;

    private static final String BUCKETS_URL = "https://developer.api.autodesk.com/oss/v2/buckets/";

    public String createBucket(String bucketKey) throws Exception {
        String accessToken = authService.getAccessToken();
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(BUCKETS_URL);
            post.setHeader("Authorization", "Bearer " + accessToken);
            post.setHeader("Content-Type", "application/json");

            String jsonBody = "{ \"bucketKey\": \"" + bucketKey + "\", \"policyKey\": \"transient\" }";
            post.setEntity(new StringEntity(jsonBody));

            HttpResponse response = client.execute(post);
            HttpEntity responseEntity = response.getEntity();
            return EntityUtils.toString(responseEntity);
        }
    }

    public String getBucket(String bucketKey) throws Exception {
        String accessToken = authService.getAccessToken();
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(BUCKETS_URL + bucketKey);
            get.setHeader("Authorization", "Bearer " + accessToken);

            HttpResponse response = client.execute(get);
            HttpEntity responseEntity = response.getEntity();
            return EntityUtils.toString(responseEntity);
        }
    }
}
