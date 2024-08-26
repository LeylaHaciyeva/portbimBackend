//package az.code.portbim.auth;//package az.code.portbim.auth;
////
////import az.code.portbim.config.JwtService;
////import az.code.portbim.model.Token;
////import az.code.portbim.model.TokenType;
////import az.code.portbim.model.User;
////import az.code.portbim.repository.TokenRepository;
////import az.code.portbim.repository.UserRepository;
////import lombok.RequiredArgsConstructor;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.stereotype.Service;
////
////@Service
////@RequiredArgsConstructor
////public class AuthenticationService {
////    private final UserRepository repository;
////    private final TokenRepository tokenRepository;
////    private final PasswordEncoder passwordEncoder;
////    private final JwtService jwtService;
////    private final AuthenticationManager authenticationManager;
////
////    public AuthenticationResponse register(RegisterRequest request) {
////        if (repository.existsByEmail(request.getEmail())) {
////            throw new IllegalArgumentException("Email already in use");
////        }
////        var user = User.builder()
////                .firstName(request.getFirstName())
////                .lastName(request.getLastName())
////                .email(request.getEmail())
////                .password(passwordEncoder.encode(request.getPassword()))
////                .build();
////        var savedUser = repository.save(user);
////        var jwtToken = jwtService.generateToken(user);
////        saveUserToken(savedUser, jwtToken);
////        return AuthenticationResponse.builder()
////                .token(jwtToken)
////                .build();
////    }
////
////    public AuthenticationResponse authenticate(AuthenticationRequest request) {
////        authenticationManager.authenticate(
////                new UsernamePasswordAuthenticationToken(
////                        request.getEmail(),
////                        request.getPassword()
////                )
////        );
////        var user = repository.findByEmail(request.getEmail())
////                .orElseThrow();
////        var jwtToken = jwtService.generateToken(user);
////        revokeAllUserTokens(user);
////        saveUserToken(user, jwtToken);
////        return AuthenticationResponse.builder()
////                .token(jwtToken)
////                .build();
////    }
////
////    private void saveUserToken(User user, String jwtToken) {
////        var token = Token.builder()
////                .user(user)
////                .token(jwtToken)
////                .tokenType(TokenType.BEARER)
////                .expired(false)
////                .revoked(false)
////                .build();
////        tokenRepository.save(token);
////    }
////
////    private void revokeAllUserTokens(User user) {
////        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
////        if (validUserTokens.isEmpty()) return;
////        validUserTokens.forEach(token -> {
////            token.setExpired(true);
////            token.setRevoked(true);
////        });
////        tokenRepository.saveAll(validUserTokens);
////    }
////}
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthenticationService {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
////    public boolean authenticate(String username, String password) {
////        try {
////            Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
////            Authentication result = authenticationManager.authenticate(authentication);
////            return result.isAuthenticated();
////        } catch (AuthenticationException e) {
////            return false;
////        }
////    }
//public boolean authenticate(String username, String password) {
//    try {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        return passwordEncoder.matches(password, userDetails.getPassword());
//    } catch (UsernameNotFoundException e) {
//        return false;
//    }
//}
//    // Optionally, you can add a method to get UserDetails if needed
//    public UserDetails loadUserByUsername(String username) {
//        return userDetailsService.loadUserByUsername(username);
//    }
//
//}
