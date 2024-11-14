package com.example.demo.Confing;


import com.example.demo.Users.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration

public class ApplicationConfig {

    private final UserRepository repository;


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> repository.FindByuserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//    @Bean
//    public AuditorAware<Integer> auditorAware() {
//        return new ApplicationAuditAware();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    public ApplicationConfig(UserRepository repository) {
        this.repository = repository;
    }
}