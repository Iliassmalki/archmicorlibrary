package org.example.micrserviceemprunts.DTO;

import java.time.LocalDate;

public class EmpruntRequestDTO {

    private Long livreId;
    private Long utilisateurId;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public Long getLivreId() {
        return livreId;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
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
// Getters & Setters
}
