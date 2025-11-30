package org.sid.catalogue.Controller;

import org.sid.catalogue.Dto.LivreCreateUpdateDto;
import org.sid.catalogue.Dto.LivreReadDto;
import org.sid.catalogue.Service.LivreService;
import org.sid.catalogue.entites.Livre;
import org.sid.catalogue.mappers.LivreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livres")
public class LivreController {

    @Autowired
    private LivreService livreService;

    @Autowired
    private LivreMapper livreMapper;

    @GetMapping("/{id}")
    public ResponseEntity<LivreReadDto> getLivreById(@PathVariable Long id) {
        return ResponseEntity.ok(
                livreMapper.toDto(livreService.getLivreById(id))
        );
    }

    @PostMapping("/create")
    public ResponseEntity<LivreReadDto> createLivre(@RequestBody LivreCreateUpdateDto dto) {

        Livre livre = livreMapper.toEntity(dto);
        Livre saved = livreService.createLivre(livre, dto.getAuteursNoms());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(livreMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivreReadDto> updateLivre(
            @PathVariable Long id,
            @RequestBody LivreCreateUpdateDto dto) {

        Livre existing = livreService.getLivreById(id);

        livreMapper.updateEntityFromDto(dto, existing);

        Livre updated = livreService.updateLivre(existing, dto.getAuteursNoms());

        return ResponseEntity.ok(livreMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivre(@PathVariable Long id) {
        livreService.deleteLivre(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<LivreReadDto>> getAllLivres() {
        return ResponseEntity.ok(
                livreService.getAllLivre()
                        .stream()
                        .map(livreMapper::toDto)
                        .toList()
        );
    }
}
