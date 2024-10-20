package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disable CSRF for API testing
            .authorizeHttpRequests(authorize -> {
                authorize.requestMatchers("/api/v1/public/**").permitAll(); // Public endpoints
                authorize.requestMatchers("/api/v1/admin/**").hasRole("ADMIN"); // Admin-only endpoints
                authorize.anyRequest().authenticated(); // Other endpoints require authentication
            })
            .httpBasic(Customizer.withDefaults()); // Use basic HTTP authentication
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails john = User.builder()
            .username("john")
            .password(passwordEncoder().encode("john"))
            .roles("USER")
            .build();

        UserDetails sam = User.builder()
            .username("sam")
            .password(passwordEncoder().encode("sam"))
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(john, sam);
    }
}
