package org.example.micrserviceemprunts.Entity;


import jakarta.persistence.*;
import org.example.micrserviceemprunts.Entity.Enum.EtatEmprunt;

import java.time.LocalDate;

@Entity
@Table(name = "emprunts")
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long livreId;          // ✅ From Catalogue Service
    private Long utilisateurId;    // ✅ From User Service (or same MS)

    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective;

    @Enumerated(EnumType.STRING)
    private EtatEmprunt etat;

    public Emprunt() {}

    // ✅ Getters & Setters
    public Long getId() { return id; }
    public Long getLivreId() { return livreId; }
    public Long getUtilisateurId() { return utilisateurId; }
    public LocalDate getDateEmprunt() { return dateEmprunt; }
    public LocalDate getDateRetourPrevue() { return dateRetourPrevue; }
    public LocalDate getDateRetourEffective() { return dateRetourEffective; }
    public EtatEmprunt getEtat() { return etat; }

    public void setId(Long id) { this.id = id; }
    public void setLivreId(Long livreId) { this.livreId = livreId; }
    public void setUtilisateurId(Long utilisateurId) { this.utilisateurId = utilisateurId; }
    public void setDateEmprunt(LocalDate dateEmprunt) { this.dateEmprunt = dateEmprunt; }
    public void setDateRetourPrevue(LocalDate dateRetourPrevue) { this.dateRetourPrevue = dateRetourPrevue; }
    public void setDateRetourEffective(LocalDate dateRetourEffective) { this.dateRetourEffective = dateRetourEffective; }
    public void setEtat(EtatEmprunt etat) { this.etat = etat; }
}
