package org.example.micrserviceemprunts.Controller;

import lombok.extern.slf4j.Slf4j;
import org.example.micrserviceemprunts.DTO.EmpruntRequestDTO;
import org.example.micrserviceemprunts.DTO.EmpruntResponseDTO;
import org.example.micrserviceemprunts.DTO.LivreDTO;
import org.example.micrserviceemprunts.Entity.Emprunt;
import org.example.micrserviceemprunts.Service.EmpruntService;
import org.example.micrserviceemprunts.feign.CatalogueClient;
import org.example.micrserviceemprunts.repos.EmpruntRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Reader;
import java.util.List;

@RestController
@RequestMapping("/api/emprunts")
public class EmpruntController {

    private static final Logger logger = LoggerFactory.getLogger(EmpruntController.class);

    private final EmpruntService empruntService;
    private final EmpruntRepository empruntRepository;

    public EmpruntController(EmpruntService empruntService,
                             EmpruntRepository empruntRepository) {
        this.empruntService = empruntService;
        this.empruntRepository = empruntRepository;
    }
@PutMapping("extend/{empruntid}/{nbdays}")
    public ResponseEntity<EmpruntResponseDTO> extendEmprunt(@RequestBody EmpruntRequestDTO request,
                                                            @PathVariable int nbdays,
@PathVariable Long empruntid) {
       try{
           EmpruntResponseDTO emprunt = empruntService.extenddate(

                   empruntid,nbdays

           );
           return ResponseEntity.status(HttpStatus.ACCEPTED).body(emprunt);
       }
       catch (RuntimeException e) {
           logger.warn("Échec extension emprunt : {}", e.getMessage());
           return ResponseEntity
                   .status(HttpStatus.BAD_REQUEST)
                   .body(EmpruntResponseDTO.error(e.getMessage()));
       }
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmpruntResponseDTO> getEmpruntWithLivre(@PathVariable("id") Long id) {
        try {
            EmpruntResponseDTO dto = empruntService.getEmpruntWithLivre(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            logger.warn("Emprunt not found: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error fetching emprunt {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Emprunt>> getAllEmprunts() {
        List<Emprunt> list = empruntRepository.findAll();
        return ResponseEntity.ok(list);
    }
    @RestController
    @Slf4j
    @RequestMapping("/api")
    public static class getLivreController {

        private final CatalogueClient catalogueClient;

        public getLivreController(CatalogueClient catalogueClient) {
            this.catalogueClient = catalogueClient;
        }

        @GetMapping("/livre/{id}")
        public LivreDTO test(@PathVariable Long id) {
            log.info("Calling Feign client for livre id={}", id);
            return catalogueClient.getLivreById(id);
        }
    }

    @GetMapping("/livres")
    public ResponseEntity<List<LivreDTO>> getAllLivres() {
        List<LivreDTO> livres = empruntService.getAllLivresFromCatalogue();
        return ResponseEntity.ok(livres);
    }
    @PostMapping("/emprunter")
    public ResponseEntity<EmpruntResponseDTO> emprunterLivre(@RequestBody EmpruntRequestDTO request) {

        try {
            EmpruntResponseDTO emprunt = empruntService.createEmprunt(
                    request.getLivreId(),
                    request.getUtilisateurId()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(emprunt);

        } catch (RuntimeException e) {
            logger.warn("Échec emprunt : {}", e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(EmpruntResponseDTO.error(e.getMessage()));
        }
    }
@DeleteMapping ("/emprunt/delete/{id}")
    public ResponseEntity<Void> deleteEmprunt(@PathVariable Long id) {
        empruntService.deleteEmprunt(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("return")
    public  ResponseEntity<EmpruntResponseDTO> returnLivre(@RequestBody EmpruntRequestDTO request) {
        try {
            EmpruntResponseDTO emprunt = empruntService.retournerLivre(
                    request.getLivreId(),
                    request.getUtilisateurId()
            );
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(emprunt);
        }
            catch (RuntimeException e) {
            logger.warn("Échec retour : {}", e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(EmpruntResponseDTO.error(e.getMessage()));
        }
    }
    }


