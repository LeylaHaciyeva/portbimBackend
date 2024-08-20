package az.code.portbim.controller;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user", method = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})

public class UserController {
    @Autowired
    UserService userService;

    @Transactional
    @PostMapping("/signup")
    public void signup(@RequestBody SignupDto signupDto) throws Exception {
        userService.signup(signupDto);
    }
}