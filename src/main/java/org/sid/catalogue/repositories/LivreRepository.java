package org.sid.catalogue.repositories;

import org.sid.catalogue.entites.Auteur;
import org.sid.catalogue.entites.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LivreRepository  extends JpaRepository<Livre, Long> {
    Livre getLivreById(Long id);

}
