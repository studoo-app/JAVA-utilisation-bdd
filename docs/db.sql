-- Création de la table Category
CREATE TABLE Category
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

-- Création de la table Product
CREATE TABLE Product
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    price       INT          NOT NULL,
    category_id INT          NOT NULL
);

-- Clé étrangère vers Category
ALTER TABLE Product
    ADD CONSTRAINT fk_category
        FOREIGN KEY (category_id)
            REFERENCES Category (id)
            ON DELETE RESTRICT
            ON UPDATE CASCADE;

-- Index pour améliorer les performances des recherches par catégorie
CREATE INDEX idx_category_id ON Product (category_id);

-- Insertion de quelques catégories exemples
INSERT INTO Category (name)
VALUES ('Électronique'),
       ('Vêtements'),
       ('Alimentation'),
       ('Livres');

-- Insertion de quelques produits exemples
INSERT INTO Product (name, description, price, category_id)
VALUES ('Smartphone XYZ', 'Smartphone dernière génération', 699, 1),
       ('Ordinateur portable', 'PC portable 15 pouces', 899, 1),
       ('T-shirt bleu', 'T-shirt en coton 100%', 19, 2),
       ('Jean slim', 'Jean coupe slim', 49, 2),
       ('Pâtes bio', 'Paquet de pâtes biologiques', 3, 3),
       ('Roman policier', 'Thriller captivant', 15, 4);
