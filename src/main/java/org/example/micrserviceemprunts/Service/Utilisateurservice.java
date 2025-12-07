package org.example.micrserviceemprunts.Service;

import org.example.micrserviceemprunts.DTO.UtilisateurRequestDTO;
import org.example.micrserviceemprunts.DTO.UtilisateurResponseDTO;
import org.example.micrserviceemprunts.Entity.Utilisateur;
import org.example.micrserviceemprunts.repos.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Utilisateurservice {

    private final UtilisateurRepository utilisateurRepository;

    public Utilisateurservice(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    // ==================== CREATE ====================
    public UtilisateurResponseDTO createUtilisateur(UtilisateurRequestDTO request) {
        // Vérifier que l'email n'existe pas déjà
        if (utilisateurRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(request.nom());
        utilisateur.setEmail(request.email());

        utilisateur = utilisateurRepository.save(utilisateur);

        return toResponseDTO(utilisateur);
    }

    // ==================== READ ALL ====================
    public List<UtilisateurResponseDTO> getAllUtilisateurs() {
        return utilisateurRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // ==================== READ ONE ====================
    public UtilisateurResponseDTO getUtilisateurById(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id : " + id));
        return toResponseDTO(utilisateur);
    }

    // ==================== UPDATE ====================
    public UtilisateurResponseDTO updateUtilisateur(Long id, UtilisateurRequestDTO request) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id : " + id));

        // Vérifier l'unicité de l'email si il change
        if (!utilisateur.getEmail().equals(request.email()) &&
                utilisateurRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Cet email est déjà utilisé par un autre utilisateur");
        }

        utilisateur.setNom(request.nom());
        utilisateur.setEmail(request.email());

        utilisateur = utilisateurRepository.save(utilisateur);

        return toResponseDTO(utilisateur);
    }

    // ==================== DELETE ====================
    public void deleteUtilisateur(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id : " + id));

        utilisateurRepository.delete(utilisateur);
    }

    // ==================== MAPPER PRIVÉ ====================
    private UtilisateurResponseDTO toResponseDTO(Utilisateur utilisateur) {
        return new UtilisateurResponseDTO(
                utilisateur.getId(),
                utilisateur.getNom(),
                utilisateur.getEmail()
        );
    }
}