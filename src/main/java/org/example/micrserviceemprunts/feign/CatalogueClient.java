package org.example.micrserviceemprunts.feign;

import org.example.micrserviceemprunts.DTO.LivreDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalogue-service")
public interface CatalogueClient {

    @GetMapping("/api/livres/{id}")
    LivreDTO getLivreById(@PathVariable Long id);
}
