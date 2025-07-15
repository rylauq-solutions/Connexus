package com.connexus.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {
    public static String getEmailOfLoggedInUser(Authentication authentication) {

        if (authentication instanceof OAuth2AuthenticationToken) {

            var aOAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oauth2User = (OAuth2User) authentication.getPrincipal();
            String username = "";

            // Provider is GOOGLE
            if (clientId.equals("google")) {
                System.out.println("Getting email from Google OAuth2");
                username = oauth2User.getAttribute("email").toString();
            }

            // Provider is GITHUB
            else if (clientId.equals("github")) {
                System.out.println("Getting email from Github OAuth2");
                username = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString()
                        : oauth2User.getAttribute("login").toString() + "@github.com";
            }
            return username;
        }

        // Provider is SELF
        else {
            return authentication.getName();
        }
    }
}
