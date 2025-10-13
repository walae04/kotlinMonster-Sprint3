CREATE TABLE Entraineurs(id INTEGER PRIMARY KEY AUTO_INCREMENT,
                         nom VARCHAR(255),
                         argents INTEGER);

CREATE TABLE EspeceMonstre(id INTEGER PRIMARY KEY AUTO_INCREMENT,
                           nom  VARCHAR(255),
                           type  VARCHAR(255),
                           baseAttaque  INT,
                           baseDefense  INT,
                           baseVitesse  INT,
                           baseAttaqueSpe  INT,
                           baseDefenseSpe INT,
                           basePv  INT,
                           modAttaque  DOUBLE,
                           modDefense DOUBLE,
                           modVitesse  DOUBLE,
                           modAttaqueSpe  DOUBLE,
                           modDefenseSpe  DOUBLE,
                           modPv  DOUBLE,
                           description  TEXT,
                           particularites  TEXT,
                           caracteres  TEXT);

CREATE TABLE IndividusMonstre(id INTEGER PRIMARY KEY AUTO_INCREMENT,
                              nom  VARCHAR(255),
                              niveau  INT,
                              attaque  INT,
                              defense  INT,
                              vitesse INT,
                              attaqueSpe  INT,
                              defenseSpe  INT,
                              pvMax  INT,
                              potentiel  DOUBLE,
                              exp  DOUBLE,
                              pv  INT,
                              espece_id INT DEFAULT NULL REFERENCES EspeceMonstre(id)
                                  ON DELETE SET NULL
                                  ON UPDATE CASCADE,
                              entraineur_equipe_id INT DEFAULT NULL REFERENCES Entraineurs(id)
                                  ON DELETE SET NULL
                                  ON UPDATE CASCADE,
                              entraineur_boite_id INT DEFAULT NULL REFERENCES Entraineurs(id)
                                  ON DELETE SET NULL
                                  ON UPDATE CASCADE);
INSERT INTO Entraineurs(id, nom, argents
) VALUES (1,'Bob',200),
         (2,'Alice',100),
         (3,'Clara',600);

INSERT INTO EspeceMonstre(id, nom, type, baseAttaque, baseDefense, baseVitesse, baseAttaqueSpe, baseDefenseSpe, basePv,
 modAttaque, modDefense, modVitesse, modAttaqueSpe, modDefenseSpe, modPv,
 description, particularites, caracteres)
VALUES
    (1, 'springleaf', 'Graine', 9, 11, 10, 12, 14, 60,
     6.5, 9.0, 8.0, 7.0, 10.0, 14.0,
     'Un petit monstre espiègle au corps rond comme une graine. Il aime se cacher dans l’herbe haute et se dorer au soleil.',
     'Sa feuille sur la tête s’incline pour indiquer son humeur.',
     'Curieux, amical, un peu timide.'),

    (4, 'flamkip', 'Animal', 12, 8, 13, 16, 7, 50,
     10.0, 5.5, 9.5, 9.5, 6.5, 12.0,
     'Ce petit animal est toujours entouré d’une flamme dansante. Il déteste le froid et s’énerve facilement quand on tente d’éteindre son feu.',
     'Sa flamme change d’intensité selon son niveau d’énergie.',
     'Impulsif, joueur, loyal.'),

    (5, 'pyrokip', 'Animal', 18, 12, 15, 22, 11, 70,
     12.0, 8.0, 11.0, 12.5, 8.0, 15.0,
     'Pyrokip, l’évolution de Flamkip. Son feu est devenu intense et ses flammes sont capables de fondre la pierre. Fier et courageux, il protège son dresseur à tout prix.',
     'Ses flammes changent de couleur selon son humeur : rouge vif en colère, dorées quand il est calme.',
     'Fier, protecteur, explosif.'),

    (7, 'aquamy', 'Meteo', 10, 11, 9, 14, 14, 55,
     9.0, 10.0, 7.5, 12.0, 12.0, 13.5,
     'Une créature vaporeuse qui ressemble à un petit nuage. Les gouttes qui tombent de son corps sont pures et rafraîchissantes.',
     'Fait légèrement baisser la température autour de lui quand il s’endort.',
     'Calme, rêveur, mystérieux.'),

    (8, 'laoumi', 'Animal', 11, 10, 9, 8, 11, 58,
     11.0, 8.0, 7.0, 6.0, 11.5, 14.0,
     'Un petit ourson au pelage soyeux. Il adore se tenir debout et brandir ses petites pattes comme s’il dansait.',
     'Son grognement est plus mignon qu’effrayant, mais il devient redoutable pour défendre ses amis.',
     'Affectueux, protecteur, gourmand.'),

    (10, 'bugsyface', 'Insecte', 10, 13, 8, 7, 13, 45,
     7.0, 11.0, 6.5, 8.0, 11.5, 10.0,
     'Un insecte à la carapace luisante qui se déplace par petits bonds. Il communique en faisant vibrer ses antennes.',
     'Sa carapace devient plus dure après chaque mue, augmentant sa défense.',
     'Travailleur, sociable, infatigable.'),

    (13, 'galum', 'Mineral', 12, 15, 6, 8, 12, 55,
     9.0, 13.0, 4.0, 6.5, 10.5, 13.0,
     'Un golem ancien sculpté dans la pierre. Ses yeux s’illuminent d’une lueur mystérieuse quand il se met en garde.',
     'Peut rester immobile pendant des heures, le faisant passer pour une statue.',
     'Sérieux, stoïque, fiable.');

Insert into IndividusMonstre(nom,niveau,attaque,defense,vitesse,attaqueSpe,defenseSpe,pvMax,potentiel,exp,pv,espece_id,entraineur_equipe_id)
values ('springleaf',5, 30, 25, 20, 15, 20, 50, 0.8, 100, 50, 1, 2),
       ('aquamy', 7, 35, 30, 22, 25, 20, 55, 0.9, 200, 55, 7, 1),
       ('bugsyface', 6, 28, 20, 35, 18, 15, 48, 0.7, 150, 48,10 , 1),
       ('galum', 8, 40, 35, 25, 20, 30, 60, 0.95, 300, 60, 4, 3),
       ('flamkip', 9, 45, 30, 28, 35, 25, 65, 1.0, 400, 65, 13, 3);

