package com.livraison.livreur.controller;

import com.livraison.livreur.model.Commande;
import com.livraison.livreur.model.User;
import com.livraison.livreur.security.UserPrincipal;
import com.livraison.livreur.service.CommandeService;
import com.livraison.livreur.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final UserService userService;
    private final CommandeService commandeService;

    @GetMapping("/espace")
    public String espace(@AuthenticationPrincipal UserPrincipal principal, Model model) {
        User user = userService.findByEmail(principal.getEmail());
        List<Commande> orders = commandeService.getClientCommandes(user);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("telephone", user.getTelephone());
        model.addAttribute("orders", orders);
        return "client/espace";
    }
}
