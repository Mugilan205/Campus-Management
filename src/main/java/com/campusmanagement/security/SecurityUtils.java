package com.campusmanagement.security;

import com.campusmanagement.user.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No authenticated user found.");
        }

        Object principal = authentication.getPrincipal();

//        every authentication method has diffrent principal
//        JWT → CustomUserDetails
//        OAuth2 → OAuth2User
//        Anonymous user → "anonymousUser" (a String)
//        That's why you can't assume it's always your class.

        if (!(principal instanceof CustomUserDetails customUserDetails)) {
            throw new RuntimeException("Invalid authenticated principal.");
        }

        return customUserDetails.getUser();
    }
}