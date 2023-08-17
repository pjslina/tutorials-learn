package com.panjin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.*;

/**
 * Spring Controller Definitions.
 * @author panjin
 */
@Controller
@Slf4j
public class MyController {

    @RequestMapping("/")
    public String init(Map<String, Object> model, Principal principal) {
        model.put("title", "PUBLIC AREA");
        model.put("message", "Any user can view this page");
        model.put("username", getUserName(principal));
        model.put("userroles", getUserRoles(principal));
        return "home";
    }

    @RequestMapping("/secure")
    public String secure(Map<String, Object> model, Principal principal) {
        model.put("title", "SECURE AREA");
        model.put("message", "Only Authorised Users Can See This Page");
        model.put("username", getUserName(principal));
        model.put("userroles", getUserRoles(principal));
        return "home";
    }

    private String getUserName(Principal principal) {
        if (principal == null) {
            return "anonymous";
        } else {
            final UserDetails currentUser = (UserDetails) ((Authentication) principal).getPrincipal();
            Collection<? extends GrantedAuthority> authorities = currentUser.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                log.info("grantedAuthority.getAuthority() = {}", grantedAuthority.getAuthority());
            }
            return principal.getName();
        }
    }

    private Collection<String> getUserRoles(Principal principal) {
        if (principal == null) {
            return List.of("none");
        } else {
            Set<String> roles = new HashSet<>();

            final UserDetails currentUser = (UserDetails) ((Authentication) principal).getPrincipal();
            Collection<? extends GrantedAuthority> authorities = currentUser.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                roles.add(grantedAuthority.getAuthority());
            }
            return roles;
        }
    }
}
