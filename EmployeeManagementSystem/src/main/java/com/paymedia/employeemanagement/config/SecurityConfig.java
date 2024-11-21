package com.paymedia.employeemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Configure in-memory authentication
    @Bean
    public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("empadmin").password("exam#123").roles("ADMIN")
                .and()
                .withUser("emp001").password("emppw#123").roles("USER");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/employees/**", "/api/departments/**").hasRole("ADMIN") // ADMIN only
                .antMatchers(HttpMethod.GET, "/api/employees/**", "/api/departments/**").hasAnyRole("ADMIN", "USER") // GET for both roles
                .anyRequest().authenticated()
                .and()
                .httpBasic(); // Enable Basic Auth
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
