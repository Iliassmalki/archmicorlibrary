package org.sid.catalogue.Service;

import org.sid.catalogue.entites.Auteur;

import java.util.List;

public interface AuteurService {
    public Auteur getAuteurById(Long id);
    public Auteur createAuteur(Auteur auteur);
    public Auteur updateAuteur(Auteur auteur);
    public void deleteAuteur(Auteur auteur);

}
