package org.example.micrserviceemprunts.repos;


import org.example.micrserviceemprunts.Entity.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    List<Emprunt> findByUtilisateurId(Long utilisateurId);

    // ✅ Find all emprunts by livre
    List<Emprunt> findByLivreId(Long livreId);

    // ✅ Find emprunts by state (EN_COURS, RENDU, RETARD)
    Emprunt findByLivreIdAndUtilisateurId(Long livreId, Long utilisateurId);
;
}
