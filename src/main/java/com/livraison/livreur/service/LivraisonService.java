package com.livraison.livreur.service;

import com.livraison.livreur.model.Commande;
import com.livraison.livreur.model.Livraison;
import com.livraison.livreur.model.LivraisonStatus;
import com.livraison.livreur.model.User;
import com.livraison.livreur.repository.CommandeRepository;
import com.livraison.livreur.repository.LivraisonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LivraisonService {

    private final LivraisonRepository livraisonRepository;
    private final CommandeRepository commandeRepository;

    @Transactional
    public Livraison assignLivreur(Long commandeId, User livreur, String adresse) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new IllegalArgumentException("Commande non trouvée"));

        // Vérification que la commande est bien validée avant l'assignation
        if (!"VALIDEE".equals(commande.getStatut().name())) {
            throw new IllegalArgumentException("La commande doit être validée avant d'être assignée à un livreur.");
        }

        Livraison livraison = Livraison.builder()
                .commande(commande)
                .livreur(livreur)
                .adresseLivraison(adresse)
                .dateAssignation(LocalDateTime.now())
                .statut(LivraisonStatus.ASSIGNEE)
                .build();

        return livraisonRepository.save(livraison);
    }

    public List<Livraison> getLivraisonsByLivreur(User livreur) {
        return livraisonRepository.findByLivreur(livreur);
    }

    @Transactional
    public void updateLivraisonStatut(Long id, LivraisonStatus status) {
        Livraison livraison = livraisonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Livraison non trouvée"));
        livraison.setStatut(status);

        if (status == LivraisonStatus.LIVREE) {
            livraison.setDateLivraison(LocalDateTime.now());
        }

        livraisonRepository.save(livraison);
    }

    public Optional<Livraison> getLivraisonByCommande(Commande commande) {
        return livraisonRepository.findByCommande(commande);
    }
}
