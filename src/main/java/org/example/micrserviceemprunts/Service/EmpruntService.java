package org.example.micrserviceemprunts.Service;

import org.example.micrserviceemprunts.DTO.EmpruntResponseDTO;
import org.example.micrserviceemprunts.DTO.LivreDTO;
import org.example.micrserviceemprunts.Entity.Emprunt;
import org.example.micrserviceemprunts.Entity.Enum.EtatEmprunt;
import org.example.micrserviceemprunts.Entity.Utilisateur;
import org.example.micrserviceemprunts.feign.CatalogueClient;
import org.example.micrserviceemprunts.repos.EmpruntRepository;
import org.example.micrserviceemprunts.repos.UtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpruntService {

    private static final Logger logger = LoggerFactory.getLogger(EmpruntService.class);
    private static final int DUREE_PAR_DEFAUT_JOURS = 14;

    private final EmpruntRepository empruntRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final CatalogueClient catalogueClient;

    public EmpruntService(EmpruntRepository empruntRepository,
                          UtilisateurRepository utilisateurRepository,
                          CatalogueClient catalogueClient) {
        this.empruntRepository = empruntRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.catalogueClient = catalogueClient;
    }

    public EmpruntResponseDTO createEmprunt(long livreId, long utilisateurId) {

        // 1. Vérifier que le livre existe et que le catalogue est disponible
        LivreDTO livre = catalogueClient.getLivreById(livreId);
        if (livre.getTitre() == null || livre.getTitre().contains("unavailable")) {
            throw new RuntimeException("Le livre n'existe pas ou le service catalogue est actuellement indisponible");
        }

        // 2. Vérifier que l'utilisateur existe
        utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id : " + utilisateurId));



        // 4. Créer et sauvegarder l'emprunt
        Emprunt emprunt = new Emprunt();
        emprunt.setLivreId(livreId);
        emprunt.setUtilisateurId(utilisateurId);
        emprunt.setDateEmprunt(LocalDate.now());
        emprunt.setDateRetourPrevue(LocalDate.now().plusDays(DUREE_PAR_DEFAUT_JOURS));
        emprunt.setEtat(EtatEmprunt.EN_COURS);

        emprunt = empruntRepository.save(emprunt);

        // 5. Retourner le DTO complet
        return buildResponseDTO(emprunt);
    }


    public EmpruntResponseDTO getEmpruntWithLivre(Long id) {
        Emprunt emprunt = empruntRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Emprunt with id {} not found", id);
                    return new RuntimeException("Emprunt not found with id: " + id);
                });

        return buildResponseDTO(emprunt);
    }
    public String  deleteEmprunt(Long id) {
        Optional<Emprunt> empruntOptional = empruntRepository.findById(id);
        if (empruntOptional.isPresent()) {
            empruntRepository.deleteById(id);
            return "Emprunt supprimé avec succès.";
        } else {
            throw new RuntimeException("Emprunt non trouvé avec l'id : " + id);
        }
    }

    public EmpruntResponseDTO extenddate(long emruntid, int nbJours){
        Emprunt  emprunt = empruntRepository.findById(emruntid).orElseThrow(() -> new IllegalArgumentException("Value not found!"));
        if (emprunt == null) {
            throw new RuntimeException("Aucun emprunt trouvé pour ce livre et cet utilisateur");
        }
        emprunt.setDateRetourPrevue(emprunt.getDateRetourPrevue().plusDays(nbJours));
        empruntRepository.save(emprunt);
        return buildResponseDTO(emprunt);
    }



    public List<EmpruntResponseDTO> getEmpruntsByUser(Long utilisateurId) {
        return empruntRepository.findByUtilisateurId(utilisateurId).stream()
                .map(this::buildResponseDTO)
                .collect(Collectors.toList());
    }


    public List<LivreDTO> getAllLivresFromCatalogue() {
        return List.of(catalogueClient.getAllLivres());
    }


    private EmpruntResponseDTO buildResponseDTO(Emprunt emprunt) {
        LivreDTO livre = catalogueClient.getLivreById(emprunt.getLivreId());

        // Si fallback → titre = "Livre service unavailable"
        if (livre.getTitre() == null || livre.getTitre().contains("unavailable")) {
            livre = new LivreDTO();
            livre.setId(emprunt.getLivreId());
            livre.setTitre("Livre service unavailable");
        }

        EmpruntResponseDTO dto = new EmpruntResponseDTO();
        dto.setId(emprunt.getId());
        dto.setLivre(livre);
        dto.setUtilisateurId(emprunt.getUtilisateurId());
        dto.setDateEmprunt(emprunt.getDateEmprunt());
        dto.setDateRetourPrevue(emprunt.getDateRetourPrevue());
        dto.setDateRetourEffective(emprunt.getDateRetourEffective());
        dto.setEtat(emprunt.getEtat());



        return dto;
    }

    public EmpruntResponseDTO retournerLivre(long livreId, long utilisateurId) {

        LivreDTO livre = catalogueClient.getLivreById(livreId);
        if (livre.getTitre() == null || livre.getTitre().contains("unavailable")) {
            throw new RuntimeException("Le livre n'existe pas ou le service catalogue est actuellement indisponible");
        }


        utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id : " + utilisateurId));

 Emprunt emprunt= empruntRepository.findByLivreIdAndUtilisateurId(livreId, utilisateurId);
 if (emprunt == null) {
            throw new RuntimeException("Aucun emprunt trouvé pour ce livre et cet utilisateur");
        }

        if (emprunt.getDateRetourEffective() != null) {
            throw new RuntimeException("Le livre a déjà été retourné");
        }
        if(LocalDate.now().isAfter(emprunt.getDateRetourPrevue()))  emprunt.setEtat(EtatEmprunt.RETARD);
         emprunt.setEtat(EtatEmprunt.RENDU);
        emprunt.setDateRetourEffective(LocalDate.now());
        empruntRepository.save(emprunt);
 EmpruntResponseDTO responseDTO = new EmpruntResponseDTO();
        responseDTO.setLivre(livre);
        responseDTO.setUtilisateurId(utilisateurId);
        responseDTO.setDateEmprunt(emprunt.getDateEmprunt());
        responseDTO.setDateRetourPrevue(emprunt.getDateRetourPrevue());
        responseDTO.setDateRetourEffective(emprunt.getDateRetourEffective());
        return responseDTO;

    }
}