package JobsNow.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.*;

import JobsNow.dao.UtilisateurDao;
import JobsNow.entities.Utilisateur;
import org.junit.Before;
import org.junit.Test;

public class UtilisateurDaoTestCase {

    private UtilisateurDao utilisateurDao = new UtilisateurDaoImpl();

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
            stmt.executeUpdate("INSERT INTO annonce(titre, description, dateDebut, lieu, formation, tenue, remuneration) VALUES ('Serveurs', 'On recherche 3 serveurs pour une fête', '2018-03-08', 'Lille', 0, 'soigné', 200.00);");
            stmt.executeUpdate("INSERT INTO annonce(titre, description, dateDebut, lieu, formation, tenue, remuneration) VALUES ('Caissier', 'On recherche 2 caissiers pour un remplacement 15h/semaine', '2018-02-11', 'Mouvaux', 1, 'pas particulièrement', 113.12);");
        }
    }

    @Test
    public void shouldAddUtilisateur() throws Exception {
        // GIVEN
        Utilisateur newUtilisateur = new Utilisateur(null, "Mercier", "Hugo", Date.valueOf("1996-12-18"),"117 rue Canteleu", "0613712379", "m.m@hei.yncrea.fr", "0000", 0);
        // WHEN
        Utilisateur createdUtilisateur = utilisateurDao.addUtilisateur(newUtilisateur);
        // THEN
        assertThat(createdUtilisateur).isNotNull();
        assertThat(createdUtilisateur.getIdUser()).isNotNull();
        assertThat(createdUtilisateur.getIdUser()).isGreaterThan(0);
        assertThat(createdUtilisateur.getNom()).isEqualTo("Mercier");
        assertThat(createdUtilisateur.getPrenom()).isEqualTo("Hugo");
        assertThat(createdUtilisateur.getDateNaissance()).isEqualTo("1996-12-18");
        assertThat(createdUtilisateur.getAdresse()).isEqualTo("117 rue Canteleu");
        assertThat(createdUtilisateur.getTel()).isEqualTo("0613712379");
        assertThat(createdUtilisateur.getEmail()).isEqualTo("m.m@hei.yncrea.fr");
        assertThat(createdUtilisateur.getMdp()).isEqualTo("0000");
        assertThat(createdUtilisateur.getAdmin()).isEqualTo(0);

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM utilisateur WHERE nom='Mercier' AND prenom='Hugo' AND dateNaissance='1996-12-18' AND adresse='117 rue Canteleu' AND tel='0613712379' AND email='m.m@hei.yncrea.fr' AND mdp='0000' AND admin=0")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("idUser")).isEqualTo(createdUtilisateur.getIdUser());
                assertThat(rs.getString("nom")).isEqualTo(createdUtilisateur.getNom());
                assertThat(rs.getString("prenom")).isEqualTo(createdUtilisateur.getPrenom());
                assertThat(rs.getDate("dateNaissance")).isEqualTo(createdUtilisateur.getDateNaissance());
                assertThat(rs.getString("tel")).isEqualTo(createdUtilisateur.getTel());
                assertThat(rs.getString("email")).isEqualTo(createdUtilisateur.getEmail());
                assertThat(rs.getString("mdp")).isEqualTo(createdUtilisateur.getMdp());
                assertThat(rs.getInt("admin")).isEqualTo(createdUtilisateur.getAdmin());

                assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldConnecterUtilisateur() throws Exception{
        // GIVEN
        Utilisateur newUtilisateur = new Utilisateur(null, "Mercier", "Hugo", Date.valueOf("1996-12-18"),"117 rue Canteleu", "0613712379", "m.m@hei.yncrea.fr", "0000", 0);
        // WHEN
        Utilisateur createdUtilisateur = utilisateurDao.addUtilisateur(newUtilisateur);
        boolean r = utilisateurDao.seConnecter(newUtilisateur);
        //THEN
        assertThat(r).isEqualTo(true);
    }
}
