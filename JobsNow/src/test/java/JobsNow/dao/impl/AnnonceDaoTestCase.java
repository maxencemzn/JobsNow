package JobsNow.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.sql.*;
import java.util.List;

import JobsNow.dao.AnnonceDao;
import JobsNow.entities.Annonce;
import org.junit.Before;
import org.junit.Test;

public class AnnonceDaoTestCase {

    private AnnonceDao annonceDao = new AnnonceDaoImpl();

    @Before
    public void initDb() throws Exception {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM utilisateur;");
            stmt.executeUpdate("DELETE FROM annonce;");
            stmt.executeUpdate("ALTER TABLE utilisateur AUTO_INCREMENT=0;");
            stmt.executeUpdate("ALTER TABLE annonce AUTO_INCREMENT=0;");
            stmt.executeUpdate("INSERT INTO utilisateur(nom, prenom, dateNaissance, adresse, tel, email, mdp, admin) VALUES ('max', 'mez', '1996-11-11', '117 rue Canteleu', '0687654321', 'maxmez@hei.yncrea.fr', 0000, 1);");
            stmt.executeUpdate("INSERT INTO utilisateur(nom, prenom, dateNaissance, adresse, tel, email, mdp, admin) VALUES ('hugo', 'mercier', '1996-12-18', '117 rue Canteleu', '0612345678', 'hm@hei.yncrea.fr', 0001, 1);");
            stmt.executeUpdate("INSERT INTO annonce(titre, description, dateDebut, lieu, formation, tenue, remuneration) VALUES ('Serveurs', 'On recherche 3 serveurs pour une fête', '2018-03-08', 'Lille', 'non', 'soigné', 200.00);");
            stmt.executeUpdate("INSERT INTO annonce(titre, description, dateDebut, lieu, formation, tenue, remuneration) VALUES ('Caissier', 'On recherche 2 caissiers pour un remplacement 15h/semaine', '2018-02-11', 'Mouvaux', 'oui', 'pas particulièrement', 113.12);");
        }
    }

    @Test
    public void shouldAddAnnonce() throws Exception {
        // GIVEN
        Annonce newAnnonce = new Annonce(null, "Serveur", "serveur pour le bar La faluche le mercredi soir et jeudi soir", Date.valueOf("2018-05-05"), "Lille", "non", "Pas de tenue particulière", 15.00);
        // WHEN
        Annonce createdAnnonce = annonceDao.addAnnonce(newAnnonce);
        // THEN
        assertThat(createdAnnonce).isNotNull();
        assertThat(createdAnnonce.getIdAnnonce()).isNotNull();
        assertThat(createdAnnonce.getIdAnnonce()).isGreaterThan(0);
        assertThat(createdAnnonce.getTitre()).isEqualTo("Serveur");
        assertThat(createdAnnonce.getDescription()).isEqualTo("serveur pour le bar La faluche le mercredi soir et jeudi soir");
        assertThat(createdAnnonce.getDateDebut()).isEqualTo("2018-05-05");
        assertThat(createdAnnonce.getLieu()).isEqualTo("Lille");
        assertThat(createdAnnonce.getFormation()).isEqualTo("non");
        assertThat(createdAnnonce.getTenue()).isEqualTo("Pas de tenue particulière");
        assertThat(createdAnnonce.getRemuneration()).isEqualTo(15.00);

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM annonce WHERE titre='Serveur' AND description='serveur pour le bar La faluche le mercredi soir et jeudi soir' AND dateDebut='2018-05-05' AND lieu='Lille' AND formation='non' AND tenue='Pas de tenue particulière' AND remuneration=15.00")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("idAnnonce")).isEqualTo(createdAnnonce.getIdAnnonce());
                assertThat(rs.getString("titre")).isEqualTo(createdAnnonce.getTitre());
                assertThat(rs.getString("description")).isEqualTo(createdAnnonce.getDescription());
                assertThat(rs.getDate("dateDebut")).isEqualTo(createdAnnonce.getDateDebut());
                assertThat(rs.getString("lieu")).isEqualTo(createdAnnonce.getLieu());
                assertThat(rs.getString("formation")).isEqualTo(createdAnnonce.getFormation());
                assertThat(rs.getString("tenue")).isEqualTo(createdAnnonce.getTenue());
                assertThat(rs.getDouble("remuneration")).isEqualTo(createdAnnonce.getRemuneration());

                assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldListAnnonce() throws Exception {
        // WHEN
        List<Annonce> annonces = annonceDao.listAnnonce();
        // THEN
        assertThat(annonces).hasSize(2);
        assertThat(annonces).extracting("titre", "description", "dateDebut", "lieu", "formation", "tenue", "remuneration").containsOnly(tuple("Serveurs", "On recherche 3 serveurs pour une fête", Date.valueOf("2018-03-08"), "Lille", "non", "soigné", 200.00),
                                                                                                                                                        tuple("Caissier", "On recherche 2 caissiers pour un remplacement 15h/semaine", Date.valueOf("2018-02-11"), "Mouvaux", "oui", "pas particulièrement", 113.12));
    }

    @Test
    public void shouldDelAnnonce() throws Exception {
        //WHEN
        annonceDao.delAnnonce(2);
        //THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM annonce WHERE idAnnonce='2'")) {
                assertThat(rs.next()).isFalse();
            }
        }
    }
}
