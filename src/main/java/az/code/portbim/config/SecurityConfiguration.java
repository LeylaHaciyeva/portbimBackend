package az.code.portbim.config;//package az.code.portbim.config;//package az.code.portbim.config;
////import az.code.portbim.service.UserService;
////import lombok.RequiredArgsConstructor;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.authentication.AuthenticationProvider;
////import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
////import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
////import org.springframework.security.config.http.SessionCreationPolicy;
////import org.springframework.security.core.context.SecurityContextHolder;
////import org.springframework.security.core.userdetails.User;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.provisioning.InMemoryUserDetailsManager;
////import org.springframework.security.web.SecurityFilterChain;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////import org.springframework.security.web.authentication.logout.LogoutHandler;
////import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
////import org.springframework.security.web.csrf.CsrfTokenRepository;
////import org.springframework.web.cors.CorsConfiguration;
////import org.springframework.web.cors.CorsConfigurationSource;
////import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
////
////import java.util.ArrayList;
////import java.util.Arrays;
////import java.util.List;
////
////import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
////
////@Configuration
////@EnableWebSecurity
////@RequiredArgsConstructor
//////public class SecurityConfiguration {
//////    private final JwtAuthenticationFilter jwtAuthFilter;
//////    private final AuthenticationProvider authenticationProvider;
////
//////    @Bean
//////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//////        http
//////                .cors()
//////                .and()
//////                .csrf()
//////                .disable()
//////                .authorizeHttpRequests()
//////                .requestMatchers("/**")
//////                .permitAll()
//////                .anyRequest()
//////                .authenticated()
//////                .and()
//////                .sessionManagement()
//////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//////                .and()
//////                .authenticationProvider(authenticationProvider)
//////                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//////        ;
//////
//////        return http.build();
//////    }
//////@Bean
//////public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//////    http
//////            .authorizeHttpRequests(authorizeRequests ->
//////                    authorizeRequests
//////                            .requestMatchers("/**").permitAll() // Allow all requests
//////                            .anyRequest().authenticated() // Require authentication for any other request
//////            )
//////            .sessionManagement(sessionManagement ->
//////                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless session
//////            )
//////            .authenticationProvider(authenticationProvider) // Custom authentication provider
//////            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter
//////
//////    http.csrf(csrf ->
//////            csrf.csrfTokenRepository(csrfTokenRepository())
//////    );
//////
//////    return http.build();
//////}
//////
//////    @Bean
//////    public CsrfTokenRepository csrfTokenRepository() {
//////        return new CookieCsrfTokenRepository(); // Example: Use a cookie-based CSRF token repository
//////    }
//////
//////@Bean
//////    CorsConfigurationSource corsConfigurationSource() {
//////        CorsConfiguration configuration = new CorsConfiguration();
//////        configuration.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
////////        configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5500/","http://selony.s3-website.eu-north-1.amazonaws.com/"));
//////        configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE"));
//////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//////        source.registerCorsConfiguration("/**", configuration);
//////        return source;
//////    }
////
//////}
////public class SecurityConfiguration {
////    private final JwtAuthenticationFilter jwtAuthenticationFilter;
////    private final AuthenticationProvider authenticationProvider;
//////    private final UserService userService;
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http.csrf(AbstractHttpConfigurer::disable)
////                .authorizeHttpRequests(request -> request
////                        .requestMatchers("/api/v1/auth/*").permitAll()
////                        .anyRequest().authenticated())
////                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
////                .authenticationProvider(authenticationProvider).addFilterBefore(
////                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
////        return http.build();
////    }
////
////
////    public UserDetailsService userDetailsService() {
////        List<UserDetails> users= new ArrayList<UserDetails>();
////        users.add(User.withDefaultPasswordEncoder().username("admin").password("pass").build());
////        return new InMemoryUserDetailsManager(users);
////    }
////}
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .authorizeHttpRequests((authorize) -> authorize
////                        .anyRequest().authenticated()
////                )
////                .httpBasic(Customizer.withDefaults())
////                .formLogin(Customizer.withDefaults());
////
////        return http.build();
////    }
//@Bean
//public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    http
//            .authorizeHttpRequests(authorize -> authorize
//                    .requestMatchers("/admin/**").hasRole("USER")
//                    .anyRequest().authenticated()
//            )
//            .formLogin(form -> form
//                    .loginPage("/custom-login")  // Custom login page URL
//                    .loginProcessingUrl("/perform_login")  // Custom login form submission URL
//                    .permitAll()  // Allow all users to access the login page
//            )
//            .httpBasic(Customizer.withDefaults());  // Enable HTTP Basic Auth for additional endpoints
//
//    return http.build();
//}
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetails);
//    }
//
//}
//
//




//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails adminUser = User.withUsername("admin")
//                .password(passwordEncoder().encode("adminpassword"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder().encode("userpassword"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(adminUser, user);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf->csrf.disable())
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/user/**").hasRole("USER")
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .loginProcessingUrl("/perform_login")
//                        .permitAll()
//                );
////                .httpBasic(); // Enable HTTP Basic Auth for testing
//
//        return http.build();
//    }
//}





import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

        @Bean
        public UserDetailsService userDetailsService() {
        UserDetails normalUser = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();
        UserDetails adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN", "USER")
                .build();
        return new InMemoryUserDetailsManager(normalUser, adminUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin/*").hasRole("ADMIN")
                        .requestMatchers("/user/*").hasRole("USER")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")  // Custom login page URL
                        .loginProcessingUrl("/perform_login")  // Custom login form submission URL
                        .permitAll()).httpBasic(Customizer.withDefaults());

        return http.build();
    }


}
