package com.livraison.livreur.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Après une connexion réussie, redirige l'utilisateur vers le dashboard
 * correspondant à son rôle : /admin/dashboard pour un ADMIN,
 * /client/dashboard pour un CLIENT.
 */
@Component
public class RoleBasedAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                         Authentication authentication) throws IOException, ServletException {

        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));

        String targetUrl = isAdmin ? "/admin/orders" : "/client/espace";
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
