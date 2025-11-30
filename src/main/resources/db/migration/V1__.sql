CREATE TABLE auteur
(
    id     BIGINT AUTO_INCREMENT NOT NULL,
    nom    VARCHAR(255) NULL,
    prenom VARCHAR(255) NULL,
    genre  VARCHAR(255) NULL,
    CONSTRAINT pk_auteur PRIMARY KEY (id)
);

CREATE TABLE livre
(
    id    BIGINT AUTO_INCREMENT NOT NULL,
    titre VARCHAR(255) NULL,
    isbn  VARCHAR(255) NULL,
    annee INT NOT NULL,
    CONSTRAINT pk_livre PRIMARY KEY (id)
);

CREATE TABLE livre_auteur
(
    auteur_id BIGINT NOT NULL,
    livre_id  BIGINT NOT NULL
);

ALTER TABLE livre_auteur
    ADD CONSTRAINT fk_livaut_on_auteur FOREIGN KEY (auteur_id) REFERENCES auteur (id);

ALTER TABLE livre_auteur
    ADD CONSTRAINT fk_livaut_on_livre FOREIGN KEY (livre_id) REFERENCES livre (id);