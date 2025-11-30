package org.example.micrserviceemprunts.DTO;

import org.example.micrserviceemprunts.Entity.Enum.EtatEmprunt;

import java.time.LocalDate;

public class EmpruntDTO {

    private Long id;
    private Long livreId;
    private Long utilisateurId;

    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective;

    private EtatEmprunt etat;

    private UtilisateurDTO utilisateur;

    public EmpruntDTO() {}

    public EmpruntDTO(Long id, Long livreId, Long utilisateurId,
                      LocalDate dateEmprunt,
                      LocalDate dateRetourPrevue,
                      LocalDate dateRetourEffective,
                      EtatEmprunt etat,
                      UtilisateurDTO utilisateur) {
        this.id = id;
        this.livreId = livreId;
        this.utilisateurId = utilisateurId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourEffective = dateRetourEffective;
        this.etat = etat;
        this.utilisateur = utilisateur;
    }

    // ✅ Getters & Setters
    public Long getId() {
        return id;
    }

    public Long getLivreId() {
        return livreId;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
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

    public UtilisateurDTO getUtilisateur() {
        return utilisateur;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLivreId(Long livreId) {
        this.livreId = livreId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
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

    public void setUtilisateur(UtilisateurDTO utilisateur) {
        this.utilisateur = utilisateur;
    }
}