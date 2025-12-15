package org.sid.catalogue.Service.Impl;

import org.sid.catalogue.Service.LivreService;
import org.sid.catalogue.entites.Auteur;
import org.sid.catalogue.entites.Livre;
import org.sid.catalogue.repositories.AuteurRepository;
import org.sid.catalogue.repositories.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreServiceImpl implements LivreService {

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private AuteurRepository auteurRepository;

    @Override
    public Livre getLivreById(Long id) {
        return livreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livre not found"));
    }

    @Override
    public Livre createLivre(Livre livre, List<String> auteursNoms) {

        List<Auteur> auteurs = auteurRepository.findByNomIn(auteursNoms);


        livre.setAuteurs(auteurs);
        return livreRepository.save(livre);
    }

    @Override
    public Livre updateLivre(Livre livre, List<String> auteursNoms) {

        List<Auteur> auteurs = auteurRepository.findByNomIn(auteursNoms);


        livre.setAuteurs(auteurs);
        return livreRepository.save(livre);
    }

    @Override
    public void deleteLivre(Long id) {
        livreRepository.deleteById(id);
    }

    @Override
    public List<Livre> getAllLivre() {
        return livreRepository.findAll();
    }
}
