package az.code.portbim.auth;//package az.code.portbim.auth;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//@RestController
//@CrossOrigin("http://localhost:3000")
//@RequestMapping()
//@RequiredArgsConstructor
//public class AuthenticationController {
//    private final AuthenticationService service;
//    @PostMapping(value = "/register",consumes = MediaType.ALL_VALUE)
//    public ResponseEntity<AuthenticationResponse> register(
//            @RequestBody RegisterRequest request
//    ) {
//        System.out.println(request);
//        return ResponseEntity.ok(service.register(request));
//    }
//    @GetMapping("/admin")
//    public String adminPage() {
//        return "admin";
//    }
////    @PostMapping("/login")
////    public String login(@RequestParam String username, @RequestParam String password) {
////        boolean isAuthenticated = service.authenticate(username, password);
////        if (isAuthenticated) {
////            return "Login successful!";
////        } else {
////            return "Invalid username or password!";
////        }
////    }
//
//    @PostMapping(value="/authenticate",consumes = MediaType.ALL_VALUE)
//    public ResponseEntity<AuthenticationResponse> authenticate(
//            @RequestBody AuthenticationRequest request
//    ) {
//        System.out.println("auth"+request);
//        return ResponseEntity.ok(service.authenticate(request));
//    }
//}



import az.code.portbim.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        boolean isAuthenticated = authenticationService.authenticate(username, password);
        if (isAuthenticated) {
            return "Login successful!";
        } else {
            return "Invalid username or password!";
        }
    }
    @GetMapping("/admin")
    public String dashboard() {
        return "admin";
    }
}
