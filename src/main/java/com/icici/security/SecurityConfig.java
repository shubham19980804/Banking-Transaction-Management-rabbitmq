package com.icici.security;

import java.beans.Customizer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Configure the security filter chain
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    HttpSecurity httpBasic = http.csrf(csrf -> csrf.disable()) // Disables CSRF protection
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/transactions/**").authenticated() // Restrict access to authenticated users
                        .requestMatchers("/api/accounts/**").permitAll()) // Allow public access
                .httpBasic(basic ->{}); // Enables HTTP Basic authentication
	    return http.build(); // Builds the SecurityFilterChain
	}

    // Define a user details service with in-memory authentication
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("admin"))
            .roles("ADMIN")
            .build();
        return new InMemoryUserDetailsManager(admin);
    }

    // Password encoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
