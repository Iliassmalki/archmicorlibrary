package org.sid.catalogue.Controller;


import org.sid.catalogue.Dto.AuteurCreateUpdateDto;
import org.sid.catalogue.Dto.AuteurReadDto;
import org.sid.catalogue.Dto.LivreCreateUpdateDto;
import org.sid.catalogue.Dto.LivreReadDto;
import org.sid.catalogue.Service.AuteurService;
import org.sid.catalogue.entites.Auteur;
import org.sid.catalogue.entites.Livre;
import org.sid.catalogue.mappers.AuteurMapper;
import org.sid.catalogue.repositories.AuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Auteur")
public class AuteurController {
    @Autowired
    private AuteurService auteurService;
    @Autowired
    private AuteurMapper auteurMapper;

    @GetMapping("/{id}")
    public ResponseEntity<AuteurReadDto> getAuteurById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(auteurMapper.toDto(auteurService.getAuteurById(id)));
    }
    @PostMapping("/create")
    public ResponseEntity<AuteurReadDto> createAuteur(@RequestBody AuteurCreateUpdateDto auteurCreateUpdateDto) {
        Auteur auteur = auteurMapper.toEntity(auteurCreateUpdateDto);
        Auteur savedauteur = auteurService.createAuteur(auteur);
        return ResponseEntity.status(HttpStatus.CREATED).body(auteurMapper.toDto(savedauteur));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<AuteurReadDto> updateAuteur(@PathVariable Long id, @RequestBody AuteurCreateUpdateDto auteurCreateUpdateDto) {
        Auteur auteur = auteurMapper.toEntity(auteurCreateUpdateDto);
        auteur.setId(id);
        Auteur updateAuteur = auteurService.updateAuteur(auteur);
        return ResponseEntity.ok(auteurMapper.toDto(updateAuteur));
    }


    }
