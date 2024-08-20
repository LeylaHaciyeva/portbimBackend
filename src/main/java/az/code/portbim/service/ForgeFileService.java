//package az.code.portbim.service;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.FileEntity;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//
//@Service
//public class ForgeFileService {
//
//    private static final String UPLOAD_URL = "https://developer.api.autodesk.com/oss/v2/buckets/{bucketKey}/objects/{objectName}";
//    private static final String DERIVATIVE_URL = "https://developer.api.autodesk.com/modelderivative/v2/designdata/job";
//
//    @Autowired
//    private ForgeAuthService forgeAuthService;
//
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//        if (file.isEmpty()) {
//            return ResponseEntity.badRequest().body("File is empty");
//        }
//
//        try {
//            // Define a location to save the uploaded file
//            File tempFile = File.createTempFile(file.getOriginalFilename(), null);
//            file.transferTo(tempFile);
//
//            // Process the file as needed
//            // e.g., upload to Autodesk Forge
//
//            // Clean up temporary file
//            tempFile.delete();
//
//            return ResponseEntity.ok("File uploaded successfully");
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("File upload failed");
//        }
//    }
//
//
//    public String createDerivative(String urn) throws Exception {
//        String token = forgeAuthService.getAccessToken();
//        String jsonPayload = "{ \"input\": { \"urn\": \"" + urn + "\" }, \"output\": { \"formats\": [{ \"type\": \"OBJ\", \"views\": [\"2d\"] }] } }";
//
//        CloseableHttpClient client = HttpClients.createDefault();
//        HttpResponse response = null;
//        try {
//            HttpPost post = new HttpPost(DERIVATIVE_URL);
//            post.setHeader("Authorization", "Bearer " + token);
//            post.setHeader("Content-Type", "application/json");
//            post.setEntity(new StringEntity(jsonPayload));
//
//            response = client.execute(post);
//            HttpEntity responseEntity = response.getEntity();
//            return EntityUtils.toString(responseEntity);
//        } finally {
//            if (response != null) {
//                EntityUtils.consume(response.getEntity());
//            }
//            client.close();
//        }
//    }
//
//    public String saveFile(MultipartFile file) {
//    }
//}
