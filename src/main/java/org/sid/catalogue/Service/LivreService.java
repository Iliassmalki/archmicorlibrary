package org.sid.catalogue.Service;

import org.sid.catalogue.entites.Livre;

import java.util.List;

public interface LivreService {

    Livre getLivreById(Long id);

    Livre createLivre(Livre livre, List<String> auteursNoms);

    Livre updateLivre(Livre livre, List<String> auteursNoms);

    void deleteLivre(Long id);

    List<Livre> getAllLivre();
}
