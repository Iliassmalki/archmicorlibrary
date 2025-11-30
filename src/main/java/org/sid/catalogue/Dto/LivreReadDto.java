package org.sid.catalogue.Dto;

import lombok.Data;
import java.util.List;

@Data
public class LivreReadDto {
    private Long id;
    private String titre;
    private String isbn;
    private int annee;

    // نعرض غير الأسماء
    private List<String> auteurs;
}