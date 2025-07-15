package com.connexus.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.connexus.entities.Providers;
import com.connexus.entities.User;
import com.connexus.helpers.AppConstants;
import com.connexus.repositories.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        // logger.info("OAuth Authentication Success: {}");

        // ? Get the authenticated user details
        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();

        // logger.info(user.getName());

        // user.getAttributes().forEach((key, value) -> {
        // logger.info("User Attribute: {} => {}", key, value);
        // });

        // logger.info(user.getAuthorities().toString());

        // ? Fetching data from OAuth2 user attributes
        String email = user.getAttribute("email").toString();
        String name = user.getAttribute("name").toString();
        String picture = user.getAttribute("picture").toString();

        // ? Creating user and saving to the database
        User user1 = new User();
        user1.setEmail(email);
        user1.setName(name);
        user1.setProfilePic(picture);
        user1.setPassword("password");
        user1.setUserId(UUID.randomUUID().toString());
        user1.setProvider(Providers.GOOGLE);
        user1.setEnabled(true);
        user1.setEmailVerified(true);
        user1.setProviderUserId(user.getName());
        user1.setRoleList(List.of(AppConstants.ROLE_USER));
        user1.setAbout("Account created using Google...");

        // ? Saving user to the database
        User user2 = userRepository.findByEmail(email).orElse(null);
        if (user2 == null) {
            userRepository.save(user1);
            logger.info("User created successfully: {}", email);
        }

        // ? Redirect to user profile page after successful authentication
        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }

}
