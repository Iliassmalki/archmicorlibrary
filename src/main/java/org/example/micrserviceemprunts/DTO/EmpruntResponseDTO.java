package org.example.micrserviceemprunts.DTO;

import org.example.micrserviceemprunts.Entity.Enum.EtatEmprunt;

import java.time.LocalDate;

public class EmpruntResponseDTO {

    private Long id;

    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective;

    private EtatEmprunt etat;

    private LivreDTO livre;   // ✅ From Catalogue Service
    private Long utilisateurId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public void setDateRetourEffective(LocalDate dateRetourEffective) {
        this.dateRetourEffective = dateRetourEffective;
    }

    public void setEtat(EtatEmprunt etat) {
        this.etat = etat;
    }

    public void setLivre(LivreDTO livre) {
        this.livre = livre;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public LocalDate getDateRetourEffective() {
        return dateRetourEffective;
    }

    public EtatEmprunt getEtat() {
        return etat;
    }

    public LivreDTO getLivre() {
        return livre;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    // Getters & Setters
}
