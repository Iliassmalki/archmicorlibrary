package org.example.micrserviceemprunts.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivreDTO {
    private Long id;
    private String titre;
    private String isbn;
    private int annee;
    private List<String> auteurs; // just the names
    private Boolean disponible;    // optional, if you want to include availability
}

