package com.livraison.livreur.service;

import com.livraison.livreur.model.Commande;
import com.livraison.livreur.model.CommandeStatus;
import com.livraison.livreur.model.User;
import com.livraison.livreur.repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandeService {

    private final CommandeRepository commandeRepository;

    @Transactional
    public Commande createCommande(User client, Double montant) {
        Commande commande = Commande.builder()
                .client(client)
                .dateCommande(LocalDateTime.now())
                .montantTotal(montant)
                .statut(CommandeStatus.EN_ATTENTE)
                .build();
        return commandeRepository.save(commande);
    }

    public List<Commande> getClientCommandes(User client) {
        return commandeRepository.findByClient(client);
    }

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    @Transactional
    public void updateStatut(Long id, CommandeStatus status) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Commande non trouvée"));
        commande.setStatut(status);
        commandeRepository.save(commande);
    }

    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Commande non trouvée"));
    }
}
