package com.hartcode.registration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails teacher = User.builder()
                .username("teacher")
                .password("{noop}teacher123")
                .roles("TEACHER")
                .build();
        UserDetails student = User.builder()
                .username("student")
                .password("{noop}student123")
                .roles("STUDENT")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(teacher, student, admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

            http
            .authorizeHttpRequests(config -> config
                    .requestMatchers("/home").hasAnyRole("STUDENT", "TEACHER", "ADMIN")
                    .requestMatchers("/home/directory").hasAnyRole("STUDENT", "TEACHER", "ADMIN")
                    .requestMatchers("/home/showFormForAdd").hasAnyRole ("TEACHER", "ADMIN")
                    .requestMatchers("/home/showFormForUpdate").hasAnyRole("TEACHER", "ADMIN")
                    .requestMatchers("/home/delete").hasRole("ADMIN")
                    .anyRequest()
                    .authenticated())
            .formLogin(form -> form
                    .loginPage("/showLoginPage")
                    .loginProcessingUrl("/authenticateTheUser")
                    .defaultSuccessUrl("/home/directory")
                    .permitAll())
            .logout(LogoutConfigurer::permitAll)
            .exceptionHandling(config -> config
                    .accessDeniedPage("/access-denied"));

            return http.build();
    }
}
