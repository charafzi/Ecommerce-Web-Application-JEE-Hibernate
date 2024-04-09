create TABLE Produit(
  idProd int primary key,
  nom VARCHAR2(50),
  designation VARCHAR2(255),
  prix NUMBER(7,2)
);


create TABLE Client(
  idclient int primary key,
  nom VARCHAR2(50),
  prenom VARCHAR2(50),
  login VARCHAR2(50),
  password VARCHAR2(50)
);

create TABLE Admin(
  idadmin int primary key,
  nom VARCHAR2(50),
  prenom VARCHAR2(50),
  login VARCHAR2(50),
  password VARCHAR2(50)
);

create Table categorie(
  idcat int primary key,
  nom VARCHAR2(50)
)

create Table Achat(
  idachat int primary key,
  dateAchat Date,
  total number(7,2)
);

create Table LigneAchat(
  idachat int,
  idprod int,
  quantite int,
  CONSTRAINT fk_1 foreign KEY (idachat) references Achat(idachat),
  CONSTRAINT fk_2 foreign KEY (idprod) references Produit(idprod),
  CONSTRAINT pk_1 primary key(idAchat,idprod)
);

create Table LigneAchat(
  idligne number PRIMARY KEY,
  idAchat int,
  idprod int,
  quantite int,
  CONSTRAINT fk_1 foreign KEY (idachat) references Achat(idachat),
  CONSTRAINT fk_2 foreign KEY (idprod) references Produit(idprod)
);

INSERT INTO categorie (idcat, nom) VALUES (5, 'Smartphones');
INSERT INTO categorie (idcat, nom) VALUES (6, 'Ordinateurs portables');
INSERT INTO categorie (idcat, nom) VALUES (7, 'Accessoires informatiques');
INSERT INTO categorie (idcat, nom) VALUES (8, 'Appareils photo');

INSERT ALL
  INTO Produit (idProd, nom, designation, prix, idcat) VALUES (21, 'IPhone 13', 'Smartphone Apple', 999.99, 5)
  INTO Produit (idProd, nom, designation, prix, idcat) VALUES (22, 'Samsung Galaxy S21', 'Smartphone Samsung', 899.99, 5)
  INTO Produit (idProd, nom, designation, prix, idcat) VALUES (23, 'MacBook Pro', 'Ordinateur portable Apple', 1499.99, 6)
  INTO Produit (idProd, nom, designation, prix, idcat) VALUES (24, 'Dell XPS 13', 'Ordinateur portable Dell', 1299.99, 6)
  INTO Produit (idProd, nom, designation, prix, idcat) VALUES (25, 'Souris sans fil', 'Souris Logitech', 29.99, 7)
  INTO Produit (idProd, nom, designation, prix, idcat) VALUES (26, 'Clavier mecanique', 'Clavier Corsair', 99.99, 7)
  INTO Produit (idProd, nom, designation, prix, idcat) VALUES (27, 'Ecouteurs Bluetooth', 'Ecouteurs Sony', 79.99, 5)
  INTO Produit (idProd, nom, designation, prix, idcat) VALUES (28, 'Webcam HD', 'Webcam Logitech', 49.99, 7)
  INTO Produit (idProd, nom, designation, prix, idcat) VALUES (29, 'Appareil photo Canon EOS', 'Appareil photo reflex Canon', 599.99, 8)
  INTO Produit (idProd, nom, designation, prix, idcat) VALUES (30, 'Casque audio', 'Casque audio Bose', 249.99, 5)
  INTO Produit (idProd, nom, designation, prix, idcat) VALUES (31, 'Tablette graphique', 'Tablette Wacom', 199.99, 7)
  INTO Produit (idProd, nom, designation, prix, idcat) VALUES (32, 'Disque dur externe', 'Disque dur Seagate', 89.99, 7)
  INTO Produit (idProd, nom, designation, prix, idcat) VALUES (33, 'Moniteur 27 pouces', 'Moniteur Asus', 299.99, 7)
  INTO Produit (idProd, nom, designation, prix, idcat) VALUES (34, 'Imprimante multifonction', 'Imprimante HP', 129.99, 7)
  INTO Produit (idProd, nom, designation, prix, idcat) VALUES (35, 'GoPro Hero 10', 'Caméra d''action GoPro', 449.99, 8)
  INTO Produit (idProd, nom, designation, prix, idcat) VALUES (36, 'Enceinte Bluetooth', 'Enceinte JBL', 79.99, 5)
  INTO Produit (idProd, nom, designation, prix, idcat) VALUES (37, 'Routeur Wi-Fi', 'Routeur TP-Link', 59.99, 7)
  INTO Produit (idProd, nom, designation, prix, idcat) VALUES (38, 'Laptop Stand', 'Support pour ordinateur portable', 29.99, 7)
  INTO Produit (idProd, nom, designation, prix, idcat) VALUES (39, 'Chargeur sans fil', 'Chargeur Samsung', 39.99, 7)
  INTO Produit (idProd, nom, designation, prix, idcat) VALUES (40, 'Etui pour iPhone', 'Etui OtterBox', 19.99, 7)
SELECT * FROM dual;


INSERT INTO Client VALUES (1,'Alex','Diaz','alexdiaz@gmail.com','123');


UPDATE Produit
SET IMAGEURL = 
    CASE NOM
        WHEN 'IPhone 13' THEN 'resources/images/iphone13.jpeg'
        WHEN 'Samsung Galaxy S21' THEN 'resources/images/galaxy21.jpeg'
        WHEN 'MacBook Pro' THEN 'resources/images/macbook.jpeg'
        WHEN 'Dell XPS 13' THEN 'resources/images/dellxps13.jpeg'
        WHEN 'Souris sans fil' THEN 'resources/images/sourissansfil.jpeg'
        WHEN 'Clavier mecanique' THEN 'resources/images/clavier.jpeg'
        WHEN 'Ecouteurs Bluetooth' THEN 'resources/images/ecouteurs.jpeg'
        WHEN 'Webcam HD' THEN 'resources/images/webcam.jpeg'
        WHEN 'Appareil photo Canon EOS' THEN 'resources/images/canone05.jpeg'
        WHEN 'Casque audio' THEN 'resources/images/casque.jpeg'
        WHEN 'Tablette graphique' THEN 'resources/images/tablette.jpeg'
        WHEN 'Disque dur externe' THEN 'resources/images/disquedurexterne.jpeg'
        WHEN 'Moniteur 27 pouces' THEN 'resources/images/moniteur.jpeg'
        WHEN 'Imprimante multifonction' THEN 'resources/images/imprimante.jpeg'
        WHEN 'GoPro Hero 10' THEN 'resources/images/gopro.jpeg'
        WHEN 'Enceinte Bluetooth' THEN 'resources/images/enceintes.jpeg'
        WHEN 'Routeur Wi-Fi' THEN 'resources/images/routeur.jpeg'
        WHEN 'Laptop Stand' THEN 'resources/images/laptopstand..jpeg'
        WHEN 'Chargeur sans fil' THEN 'resources/images/chargeur.jpeg'
        WHEN 'Etui pour iPhone' THEN 'resources/images/etui.jpeg'
        ELSE IMAGEURL
    END;

ALTER TABLE Produit ADD idcat INT;
ALTER TABLE Produit ADD imageurl VARCHAR2;
ALTER TABLE Produit 
ADD CONSTRAINT fk_cat FOREIGN KEY (idcat) REFERENCES Categorie(idcat);

ALTER Table Achat add idclient int;
ALTER Table Achat
ADD CONSTRAINT fk_client foreign KEY (idclient) references Client(idclient);

CREATE SEQUENCE ligne_seq START WITH 1 increment by 1;
