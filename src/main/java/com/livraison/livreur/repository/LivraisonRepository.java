package com.livraison.livreur.repository;

import com.livraison.livreur.model.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivraisonRepository extends JpaRepository<Livraison, Long> {
    java.util.List<Livraison> findByLivreur(com.livraison.livreur.model.User livreur);
    java.util.Optional<Livraison> findByCommande(com.livraison.livreur.model.Commande commande);
}
