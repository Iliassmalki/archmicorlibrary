package org.example.micrserviceemprunts.feign;

import org.example.micrserviceemprunts.DTO.LivreDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.example.micrserviceemprunts.DTO.LivreDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Feign client pointing to CatalogueService
@FeignClient(
        name = "catalogue-service",
        url = "http://localhost:8098",
        fallback = CatalogueClientFallback.class
)
public interface CatalogueClient {
    @GetMapping("/api/livres/{id}")
    LivreDTO getLivreById(@PathVariable("id") Long id);

    @GetMapping("/api/livres/all")
    LivreDTO[] getAllLivres();
}

// Fallback implementation
@Component
class CatalogueClientFallback implements CatalogueClient {

    private static final Logger logger = LoggerFactory.getLogger(CatalogueClientFallback.class);

    @Override
    public LivreDTO getLivreById(Long id) {
        logger.error("CatalogueService unavailable or livre id {} not found. Returning fallback.", id);
        LivreDTO livre = new LivreDTO();
        livre.setId(id);
        livre.setTitre("Livre service unavailable");
        return livre;
    }

    @Override
    public LivreDTO[] getAllLivres() {
        return new LivreDTO[0];
    }
}

