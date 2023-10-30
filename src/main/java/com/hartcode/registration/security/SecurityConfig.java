package com.hartcode.registration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    // Add support for JDBC
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        return new JdbcUserDetailsManager(dataSource);
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//
//        UserDetails teacher = User.builder()
//                .username("teacher")
//                .password("{noop}teacher123")
//                .roles("TEACHER")
//                .build();
//        UserDetails student = User.builder()
//                .username("student")
//                .password("{noop}student123")
//                .roles("STUDENT")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{noop}admin123")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(teacher, student, admin);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

            http
            .authorizeHttpRequests(config -> config
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/home").permitAll()
                    .requestMatchers("/home/directory").hasAnyRole("STUDENT", "TEACHER", "ADMIN")
                    .requestMatchers("/home/showFormForAdd").hasAnyRole ("TEACHER", "ADMIN")
                    .requestMatchers("/home/showFormForUpdate").hasAnyRole("TEACHER", "ADMIN")
                    .requestMatchers("/home/delete").hasRole("ADMIN")
                    .anyRequest()
                    .authenticated())
            .formLogin(form -> form
                    .loginPage("/home/showLoginPage")
                    .loginProcessingUrl("/authenticateTheUser")
                    .defaultSuccessUrl("/home/directory")
                    .permitAll())
            .logout(logout -> logout
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .permitAll()
                    .logoutSuccessUrl("/home")
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true))
            .exceptionHandling(config -> config
                    .accessDeniedPage("/home/access-denied"));

            return http.build();
    }
}
