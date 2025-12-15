package org.sid.catalogue.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LivreCreateUpdateDto implements Serializable {
    private String titre;
    private String isbn;
    private int annee;

    private List<String> auteursNoms;
}
