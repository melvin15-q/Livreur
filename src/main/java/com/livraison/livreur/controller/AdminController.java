package com.livraison.livreur.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * L'ancien tableau de bord /admin/dashboard (page statique, non stylée) a été
 * remplacé par la gestion des commandes sur /admin/orders (voir CommandeController).
 * Cette redirection évite un lien mort si quelque chose pointe encore vers l'ancienne URL.
 */
@Controller
public class AdminController {

    @GetMapping("/admin/dashboard")
    public String legacyDashboardRedirect() {
        return "redirect:/admin/orders";
    }
}
