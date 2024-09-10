package com.appdong.website.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @GetMapping("/loginOk")
    public ResponseEntity<Map<String, String>> loginOk() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String authorities = authentication.getAuthorities().toString();
        
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("username", username);
        userInfo.put("authorities", authorities);

        return ResponseEntity.ok(userInfo);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> checkAuthentication(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        if (authentication != null && authentication.isAuthenticated()
            && !(authentication instanceof AnonymousAuthenticationToken)) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            List<String> authorities = userDetails.getAuthorities()
                            .stream().map(GrantedAuthority::getAuthority)
                            .toList();

                response.put("username", userDetails.getUsername());
                response.put("authorities", authorities);
                response.put("authenticated", true);
        } else {
            response.put("authenticated", false);
            response.put("message", "인증되지 않은 사용자입니다.");
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/logoutOk")
    public ResponseEntity<Void> logoutOk() {
        return ResponseEntity.ok().build();
    }
}
