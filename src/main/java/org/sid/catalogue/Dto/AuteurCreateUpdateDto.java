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
public class AuteurCreateUpdateDto implements Serializable {
    String nom;
    String prenom;
    String genre;
}