package org.example.micrserviceemprunts.Service;

import org.example.micrserviceemprunts.DTO.EmpruntResponseDTO;
import org.example.micrserviceemprunts.DTO.LivreDTO;
import org.example.micrserviceemprunts.Entity.Emprunt;
import org.example.micrserviceemprunts.feign.CatalogueClient;
import org.example.micrserviceemprunts.repos.EmpruntRepository;
import org.springframework.stereotype.Service;

@Service
public class EmpruntService {

    private final EmpruntRepository empruntRepository;
    private final CatalogueClient catalogueClient;

    public EmpruntService(EmpruntRepository empruntRepository,
                          CatalogueClient catalogueClient) {
        this.empruntRepository = empruntRepository;
        this.catalogueClient = catalogueClient;
    }

    public EmpruntResponseDTO getEmpruntWithLivre(Long id) {

        Emprunt emprunt = empruntRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emprunt not found"));

        LivreDTO livre = catalogueClient.getLivreById(emprunt.getLivreId());

        EmpruntResponseDTO dto = new EmpruntResponseDTO();
        dto.setId(emprunt.getId());
        dto.setDateEmprunt(emprunt.getDateEmprunt());
        dto.setDateRetourPrevue(emprunt.getDateRetourPrevue());
        dto.setDateRetourEffective(emprunt.getDateRetourEffective());
        dto.setEtat(emprunt.getEtat());
        dto.setLivre(livre);
        dto.setUtilisateurId(emprunt.getUtilisateurId());

        return dto;
    }
}

