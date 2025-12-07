package org.example.micrserviceemprunts.feign;

import org.example.micrserviceemprunts.DTO.AuteurCreateUpdateDTO;
import org.example.micrserviceemprunts.DTO.AuteurReadDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "client-service", url = "http://localhost:8098", fallback = AuteurClientFallback.class)
public interface AuteurClient {

    @GetMapping("/api/Auteur/{id}")
    AuteurReadDTO getAuteurById(@PathVariable("id") Long id);

    @PostMapping("/api/Auteur/create")
    AuteurReadDTO createAuteur(@RequestBody AuteurCreateUpdateDTO dto);

    @PutMapping("/api/Auteur/update/{id}")
    AuteurReadDTO updateAuteur(@PathVariable("id") Long id, @RequestBody AuteurCreateUpdateDTO dto);

    @DeleteMapping("/api/Auteur/delete/{id}")
    void deleteAuteur(@PathVariable("id") Long id);
}

@Component
class AuteurClientFallback implements AuteurClient {
    private static final Logger logger = LoggerFactory.getLogger(AuteurClientFallback.class);

    @Override
    public AuteurReadDTO getAuteurById(Long id) {
        logger.error("CatalogueService unavailable for auteur {}", id);
        AuteurReadDTO fallback = new AuteurReadDTO();
        fallback.setId(id);
        fallback.setNom("Auteur indisponible");
        return fallback;
    }

    @Override
    public AuteurReadDTO createAuteur(AuteurCreateUpdateDTO dto) {
        logger.error("CatalogueService unavailable - cannot create auteur");
        throw new RuntimeException("Service catalogue indisponible");
    }

    @Override
    public AuteurReadDTO updateAuteur(Long id, AuteurCreateUpdateDTO dto) {
        logger.error("CatalogueService unavailable - cannot update auteur {}", id);
        throw new RuntimeException("Service catalogue indisponible");
    }

    @Override
    public void deleteAuteur(Long id) {
        logger.error("CatalogueService unavailable - cannot delete auteur {}", id);
    }
}
