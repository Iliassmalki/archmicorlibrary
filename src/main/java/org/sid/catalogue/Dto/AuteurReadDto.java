package org.sid.catalogue.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.sid.catalogue.entites.Auteur}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuteurReadDto implements Serializable {
    Long id;
    String nom;
    String prenom;
    String genre;
}