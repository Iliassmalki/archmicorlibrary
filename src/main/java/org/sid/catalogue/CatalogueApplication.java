package org.sid.catalogue;

import org.sid.catalogue.entites.Auteur;
import org.sid.catalogue.entites.Livre;
import org.sid.catalogue.repositories.AuteurRepository;
import org.sid.catalogue.repositories.LivreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class CatalogueApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogueApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AuteurRepository auteurRepository, LivreRepository livreRepository) {
        return args -> {
            // Create authors with genre
            Auteur a1 = new Auteur(null, "Victor", "Hugo", "M", null);
            Auteur a2 = new Auteur(null, "Albert", "Camus", "M", null);
            Auteur a3 = new Auteur(null, "J.K.", "Rowling", "F", null);

            auteurRepository.saveAll(Arrays.asList(a1, a2, a3));

            // Create books
            Livre l1 = new Livre(null, "Les Misérables", "978-1234567890", 1862, List.of(a1));
            Livre l2 = new Livre(null, "L'Étranger", "978-0987654321", 1942, List.of(a2));
            Livre l3 = new Livre(null, "Harry Potter à l'école des sorciers", "978-1111111111", 1997, List.of(a3));

            // Save books
            livreRepository.saveAll(Arrays.asList(l1, l2, l3));

            // Update authors' book lists (bidirectional)
            a1.setLivres(List.of(l1));
            a2.setLivres(List.of(l2));
            a3.setLivres(List.of(l3));

            auteurRepository.saveAll(Arrays.asList(a1, a2, a3));
        };
    }
}
