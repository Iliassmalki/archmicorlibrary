package org.example.micrserviceemprunts.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.micrserviceemprunts.DTO.AuteurCreateUpdateDTO;
import org.example.micrserviceemprunts.DTO.AuteurReadDTO;
import org.example.micrserviceemprunts.DTO.LivreDTO;
import org.example.micrserviceemprunts.feign.AuteurClient;
import org.example.micrserviceemprunts.feign.CatalogueClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/Auteur")
@RequiredArgsConstructor
public   class AuteurController {
    private final AuteurClient auteurClient;

    @GetMapping("/{id}")
    public AuteurReadDTO getAuteur(@PathVariable Long id) {
        return auteurClient.getAuteurById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<AuteurReadDTO> createAuteur(@RequestBody AuteurCreateUpdateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(auteurClient.createAuteur(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AuteurReadDTO> updateAuteur(@PathVariable Long id,
                                                      @RequestBody AuteurCreateUpdateDTO dto) {
        return ResponseEntity.ok(auteurClient.updateAuteur(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAuteur(@PathVariable Long id) {
        auteurClient.deleteAuteur(id);
        return ResponseEntity.noContent().build();
    }
}
