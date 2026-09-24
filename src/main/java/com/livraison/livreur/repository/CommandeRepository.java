package com.livraison.livreur.repository;

import com.livraison.livreur.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    java.util.List<Commande> findByClient(com.livraison.livreur.model.User client);
}
