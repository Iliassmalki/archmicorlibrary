package org.sid.catalogue.mappers;

import org.mapstruct.*;
import org.sid.catalogue.Dto.AuteurCreateUpdateDto;
import org.sid.catalogue.Dto.AuteurReadDto;
import org.sid.catalogue.entites.Auteur;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AuteurMapper {

    // DTO → Entity (Create)
    Auteur toEntity(AuteurCreateUpdateDto dto);

    // Entity → DTO (Read)
    AuteurReadDto toDto(Auteur auteur);

    // DTO → Entity (Update avec PATCH/PUT)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(AuteurCreateUpdateDto dto, @MappingTarget Auteur auteur);
}

