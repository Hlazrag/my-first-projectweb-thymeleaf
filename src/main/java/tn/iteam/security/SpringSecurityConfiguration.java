package tn.iteam.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {

    // LDAP or Database
    //InMoemory

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager(){


        // Create users
        var userDetails1 = createUserDetails("iteam", "12345");
        var userDetails2 = createUserDetails("poste", "12345");
        var userDetails3 = createUserDetails("enicarthage", "12345");

        return new InMemoryUserDetailsManager(userDetails1, userDetails2, userDetails3);
    }

    private UserDetails createUserDetails(String username, String password) {
        Function<String, String> passwordEncoder =
                input -> passwordEncoder().encode(input);

        return User.builder()
                .passwordEncoder(passwordEncoder)
                .username(username)
                .password(password)
                .roles("USER", "ADMIN")
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // All URLS are protected by Spring Scurity
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated());
        //A login form is shown for unauthorized request
        http.formLogin(Customizer.withDefaults());
        //CSRF disable
        http.csrf().disable();
        // Frames disable
        http.headers().frameOptions().disable();
        return http.build();

    }
} 
