package org.example.micrserviceemprunts.DTO;

public class UtilisateurDTO {

    private Long id;
    private String nom;
    private String email;

    public UtilisateurDTO() {}

    public UtilisateurDTO(Long id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }

    // ✅ Getters & Setters
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}