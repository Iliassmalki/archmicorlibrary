package org.example.micrserviceemprunts.Controller;

import lombok.RequiredArgsConstructor;
import org.example.micrserviceemprunts.DTO.UtilisateurRequestDTO;
import org.example.micrserviceemprunts.DTO.UtilisateurResponseDTO;
import org.example.micrserviceemprunts.Service.Utilisateurservice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final Utilisateurservice utilisateurservice;

    @PostMapping
    public ResponseEntity<UtilisateurResponseDTO> create(@RequestBody UtilisateurRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(utilisateurservice.createUtilisateur(request));
    }

    @GetMapping("getall")
    public ResponseEntity<List<UtilisateurResponseDTO>> getAll() {
        return ResponseEntity.ok(utilisateurservice.getAllUtilisateurs());
    }

    @GetMapping("getuser/{id}")
    public ResponseEntity<UtilisateurResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(utilisateurservice.getUtilisateurById(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<UtilisateurResponseDTO> update(@PathVariable Long id,
                                                         @RequestBody UtilisateurRequestDTO request) {
        return ResponseEntity.ok(utilisateurservice.updateUtilisateur(id, request));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        utilisateurservice.deleteUtilisateur(id);
        return ResponseEntity.noContent().build();
    }
}