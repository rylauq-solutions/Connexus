package com.connexus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.connexus.services.impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {

    // // * Creating user and login functionality using Java with memory service
    // @Bean
    // public UserDetailsService userDetailsService() {

    // // Creating User
    // UserDetails user1 = User
    // .withDefaultPasswordEncoder()
    // .username("admin123")
    // .password("admin123")
    // .roles("ADMIN", "USER")
    // .build();

    // UserDetails user2 = User
    // .withDefaultPasswordEncoder()
    // .username("user123")
    // .password("user123")
    // .build();

    // var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1,
    // user2);
    // return inMemoryUserDetailsManager;
    // }

    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    @Autowired
    private OAuthAuthenticationSuccessHandler handler;

    // * Configuration for AuthenticationProvider
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // Object of UserDetailsService
        daoAuthenticationProvider.setUserDetailsService(userDetailService);

        // Object of PasswordEncoder
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    // * Configuration for SecurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // Configuring HTTP Security
        httpSecurity
                .authorizeHttpRequests(authorize -> {
                    // authorize.requestMatchers("/home", "/register", "/services").permitAll();
                    authorize.requestMatchers("/user/**").authenticated();
                    authorize.anyRequest().permitAll();
                });

        // Default form login configuration
        httpSecurity.formLogin(formLogin -> {
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.defaultSuccessUrl("/user/profile", true);
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");

            // formLogin.failureHandler(new AuthenticationFailureHandler() {
            // @Override
            // public void onAuthenticationFailure(HttpServletRequest request,
            // HttpServletResponse response,
            // AuthenticationException exception) throws IOException, ServletException {
            // // You can customize your failure response here
            // throw new UnsupportedOperationException("Custom failure handler not
            // implemented yet");
            // }
            // });

            // formLogin.successHandler(new AuthenticationSuccessHandler() {
            // @Override
            // public void onAuthenticationSuccess(HttpServletRequest request,
            // HttpServletResponse response,
            // org.springframework.security.core.Authentication authentication)
            // throws IOException, ServletException {
            // // You can customize your success response here
            // throw new UnsupportedOperationException("Custom success handler not
            // implemented yet");
            // }
            // });

        });

        // Configuring HTTP Security for CSRF and Logout
        httpSecurity.csrf(csrf -> {
            csrf.disable(); // Disabling CSRF for simplicity, not recommended for production
        });
        httpSecurity.logout(logout -> {
            logout.logoutUrl("/do-logout");
            logout.logoutSuccessUrl("/login?logout=true");
            // logout.invalidateHttpSession(true);
            // logout.deleteCookies("JSESSIONID");
        });

        // oAuth2 login configuration
        httpSecurity.oauth2Login(oauth -> {
            oauth.loginPage("/login");
            oauth.successHandler(handler); // Use default success handler
        });

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
