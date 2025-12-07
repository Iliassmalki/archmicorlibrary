package org.example.micrserviceemprunts.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuteurReadDTO implements Serializable {
    Long id;
    String nom;
    String prenom;
    String genre;
}
