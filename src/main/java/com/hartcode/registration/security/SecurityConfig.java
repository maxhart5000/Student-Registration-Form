package com.hartcode.registration.security;

import com.hartcode.registration.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// Define the configuration for Spring Security
@Configuration
public class SecurityConfig {

    // Define a BCrypt password encoder as a Spring bean
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Define a DaoAuthenticationProvider as a Spring bean to handle authentication
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        // Set the custom user details service
        authenticationProvider.setUserDetailsService(userService);

        // Set the password encoder - bcrypt
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    // Define the security filter chain that configures HTTP security rules
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationSuccessHandler authenticationSuccessHandler) throws Exception {
        http
                .authorizeHttpRequests(config -> config
                        .requestMatchers("/").permitAll()  // Allow public access to the root path
                        .requestMatchers("/home").permitAll()  // Allow public access to the /home path
                        .requestMatchers("/home/showRegistrationForm").permitAll()
                        .requestMatchers("/home/processRegistrationForm").permitAll()
                        .requestMatchers("/home/directory").hasAnyRole("STUDENT", "TEACHER", "ADMIN") // Requires roles for /home/directory
                        .requestMatchers("/home/showFormForAdd").hasAnyRole("TEACHER", "ADMIN")  // Requires roles for /home/showFormForAdd
                        .requestMatchers("/home/showFormForUpdate").hasAnyRole("TEACHER", "ADMIN")  // Requires roles for /home/showFormForUpdate
                        .requestMatchers("/home/delete").hasRole("ADMIN")  // Requires the ADMIN role for /home/delete
                        .anyRequest()
                        .authenticated())  // All other requests require authentication

                .formLogin(form -> form
                        .loginPage("/home/showLoginPage")  // Specify the custom login page
                        .loginProcessingUrl("/authenticateTheUser")  // URL for processing login
                        .defaultSuccessUrl("/home/directory")  // Default success URL after login
                        .successHandler(authenticationSuccessHandler) // Custom success handler
                        .permitAll())  // Allow public access to the login form

                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))  // URL for logging out
                        .permitAll()
                        .logoutSuccessUrl("/home")  // URL to redirect after successful logout
                        .deleteCookies("JSESSIONID")  // Delete session cookies
                        .invalidateHttpSession(true))  // Invalidate the HTTP session on logout

                .exceptionHandling(config -> config
                        .accessDeniedPage("/home/access-denied"));  // Custom page for access denied (authorization failure)

        return http.build();
    }
}
