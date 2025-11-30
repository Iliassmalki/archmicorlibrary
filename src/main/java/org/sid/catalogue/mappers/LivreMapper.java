package org.sid.catalogue.mappers;

import org.mapstruct.*;
import org.sid.catalogue.Dto.LivreCreateUpdateDto;
import org.sid.catalogue.Dto.LivreReadDto;
import org.sid.catalogue.entites.Auteur;
import org.sid.catalogue.entites.Livre;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LivreMapper {

    Livre toEntity(LivreCreateUpdateDto dto);

    @Mapping(target = "auteurs", source = "auteurs", qualifiedByName = "auteursToNames")
    LivreReadDto toDto(Livre livre);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(LivreCreateUpdateDto dto, @MappingTarget Livre entity);
    @Named("auteursToNames")
    default List<String> mapAuteursToNames(List<Auteur> auteurs) {
        if (auteurs == null) return null;
        return auteurs.stream()
                .map(Auteur::getNom)
                .toList();
    }
}
