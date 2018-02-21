package JobsNow.dao.impl;

import JobsNow.dao.UtilisateurDao;
import JobsNow.entities.Utilisateur;

import java.sql.*;

public class UtilisateurDaoImpl implements UtilisateurDao {

    @Override
    public Utilisateur addUtilisateur(Utilisateur utilisateur){
        String query = "INSERT INTO utilisateur(nom, prenom, dateNaissance, adresse, tel, email, mdp, admin) VALUES (?, ?, ?, ?, ?, ?, ?, 0)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, utilisateur.getNom());
            statement.setString(2, utilisateur.getPrenom());
            statement.setDate(3, utilisateur.getDateNaissance());
            statement.setString(4, utilisateur.getAdresse());
            statement.setString(5, utilisateur.getTel());
            statement.setString(6, utilisateur.getEmail());
            statement.setString(7, utilisateur.getMdp());
            statement.executeUpdate();


            try (ResultSet ids = statement.getGeneratedKeys()){
                if (ids.next()){
                    int generatedId = ids.getInt(1);
                    utilisateur.setIdUser(generatedId);
                    return utilisateur;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
