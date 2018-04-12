DELETE FROM utilisateur;
DELETE FROM annonce;

ALTER TABLE utilisateur AUTO_INCREMENT=0;
ALTER TABLE annonce AUTO_INCREMENT=0;


INSERT INTO utilisateur(nom, prenom, dateNaissance, adresse, tel, email, mdp, admin) VALUES ('max', 'mez', '1996-11-11', '117 rue Canteleu', 0687654321, 'maxmez@hei.yncrea.fr', 0000, 1);
INSERT INTO utilisateur(nom, prenom, dateNaissance, adresse, tel, email, mdp, admin) VALUES ('hugo', 'mercier', '1996-12-18', '117 rue Canteleu', 0612345678, 'hm@hei.yncrea.fr', 0001, 1);


INSERT INTO annonce(titre, description, dateDebut, lieu, formation, tenue, remuneration) VALUES ('Serveurs', 'On recherche 3 serveurs pour une fête', '2018-03-08', 'Lille', 'non', 'soigné', 200.00);
INSERT INTO annonce(titre, description, dateDebut, lieu, formation, tenue, remuneration) VALUES ('Caissier', 'On recherche 2 caissiers pour un remplacement 15h/semaine', '2018-02-11', 'Mouvaux', 'oui', 'pas particulièrement', 113.12);

