package com.notifications.streaming.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("Tejaswa").password(encoder.encode("Pwd1"))
                .roles("ADMIN").build();
        UserDetails user = User.withUsername("User").password(encoder.encode("Pwd2"))
                .roles("USER").build();
        return new InMemoryUserDetailsManager(admin, user);
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
//        httpSecurity.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/api/auth/login").permitAll().and()
//                .authorizeHttpRequests().requestMatchers("/api/**").authenticated();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
