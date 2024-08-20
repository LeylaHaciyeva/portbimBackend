package az.code.portbim.controller;
import com.google.gson.Gson;
import okhttp3.*;
import com.google.gson.JsonObject;
import okhttp3.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.Base64;


@RestController
@RequestMapping("/api/forge")
public  class ForgeController {
    private static final Logger logger = LoggerFactory.getLogger(ForgeController.class);
    private static final String CLIENT_ID = "5PWWhxLGbWuNq57RM2G88ArO1maApDt0ldTjDCA8DUDHaiqG";
    private static final String CLIENT_SECRET = "MqGSa8AFJQTVATKp16nsedxfvFlb9FelUZp1MSkpKHz6AZRC00NSQlL4gYUPHRxp";
    private static final String AUTH_URL = "https://developer.api.autodesk.com/authentication/v2/token";
    private static final String BUCKET_URL = "https://developer.api.autodesk.com/oss/v2/buckets";
    private static final String OBJECTS_URL = "https://developer.api.autodesk.com/oss/v2/buckets/%s/objects/%s";
    private static final String TRANSLATE_URL = "https://developer.api.autodesk.com/modelderivative/v2/designdata/job";
    @GetMapping("/token")
    private String getForgeToken() throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&grant_type=client_credentials&scope=data:read data:write bucket:create bucket:read viewables:read");
        Request request = new Request.Builder()
                .url(AUTH_URL)
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        Response response = client.newCall(request).execute();
        assert response.body() != null;
        String jsonData = response.body().string();
        JsonObject jsonObject = new Gson().fromJson(jsonData, JsonObject.class);
        return jsonObject.get("access_token").getAsString();
    }

    @PostMapping("/buckets")
    public String createBucket(@RequestParam String bucketKey) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"bucketKey\":\"" + bucketKey + "\",\"policyKey\":\"transient\"}");
        Request request = new Request.Builder()
                .url(BUCKET_URL)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + getForgeToken())
                .build();
        Response response = client.newCall(request).execute();
            assert response.body() != null;
        return response.body().string();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam String bucketKey,
                                             @org.springframework.web.bind.annotation.RequestBody byte[] file)
            throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        Request request = new Request.Builder()
                .url(String.format(OBJECTS_URL, bucketKey, "uploaded_file"))
                .put(requestBody)
                .addHeader("Authorization", "Bearer " + getForgeToken())
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            assert response.body() != null;
            return ResponseEntity.ok(response.body().string());
        }
    }


@PostMapping("/translate")
    public String translateFile(@RequestParam String urn) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        String base64Urn = Base64.getEncoder().encodeToString(urn.getBytes());
        RequestBody body = RequestBody.create(mediaType, "{\"input\": {\"urn\": \"" + base64Urn + "\"}, \"output\": {\"formats\": [{\"type\": \"svf\", \"views\": [\"2d\", \"3d\"]}]}}");
        Request request = new Request.Builder()
                .url(TRANSLATE_URL)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + getForgeToken())
                .build();
        Response response = client.newCall(request).execute();
    assert response.body() != null;
    return response.body().string();
    }

    @GetMapping("/job-status/{urn}")
    public ResponseEntity<String> getJobStatus(@PathVariable String urn) throws IOException {
        String accessToken = getForgeToken(); // Method to get access token
        String base64Urn = Base64.getEncoder().encodeToString(urn.getBytes());

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://developer.api.autodesk.com/modelderivative/v2/designdata/job/" + base64Urn)
                .get()
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return ResponseEntity.status(response.code()).body(response.message());
            }
            assert response.body() != null;
            return ResponseEntity.ok(response.body().string());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    }

