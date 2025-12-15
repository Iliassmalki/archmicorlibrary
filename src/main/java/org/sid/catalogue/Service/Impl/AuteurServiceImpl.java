package org.sid.catalogue.Service.Impl;

import org.sid.catalogue.Service.AuteurService;
import org.sid.catalogue.entites.Auteur;
import org.sid.catalogue.repositories.AuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuteurServiceImpl implements AuteurService {
     @Autowired
    public AuteurRepository auteurRepository;
     @Override
    public Auteur getAuteurById(Long id) {
         return auteurRepository.getReferenceById(id);
     }
     @Override
    public Auteur createAuteur(Auteur auteur) {
         return auteurRepository.save(auteur);
     }
     @Override
    public Auteur updateAuteur(Auteur auteur) {
         Auteur auteur1 = auteurRepository.getReferenceById(auteur.getId());
         auteur1.setNom(auteur.getNom());
         auteur1.setPrenom(auteur.getPrenom());
         auteur1.setGenre(auteur.getGenre());
         auteur1.setLivres(auteur.getLivres());
         return auteurRepository.save(auteur1);
     }
     @Override
    public void deleteAuteur(Long Id ) {
        Auteur auteur = auteurRepository.getReferenceById(Id);
        auteurRepository.delete(auteur);
     }

    @Override
    public List<Auteur> getAllAuteurs() {
        return auteurRepository.findAll();
    }
}
