package org.sid.catalogue.repositories;

import org.sid.catalogue.entites.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuteurRepository extends JpaRepository<Auteur, Long> {
    Auteur getAuteurById(Long id);
    List<Auteur> findByNomIn(List<String> noms);
}
