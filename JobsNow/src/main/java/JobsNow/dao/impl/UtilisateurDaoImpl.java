package JobsNow.dao.impl;

import JobsNow.dao.UtilisateurDao;
import JobsNow.entities.Utilisateur;

import java.sql.*;

public class UtilisateurDaoImpl implements UtilisateurDao {

    @Override
    public Utilisateur addUtilisateur(Utilisateur utilisateur){
        String query = "INSERT INTO utilisateur(nom, prenom, tel, email, mdp) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, utilisateur.getNom());
            statement.setString(2, utilisateur.getPrenom());
            statement.setString(3, utilisateur.getTel());
            statement.setString(4, utilisateur.getEmail());
            statement.setString(5, utilisateur.getMdp());
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

    public boolean seConnecter(Utilisateur utilisateur){
        String query = "SELECT * FROM utilisateur WHERE email=? AND mdp=?";
        boolean res=false;
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, utilisateur.getEmail());
            statement.setString(2, utilisateur.getMdp());
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    res = true;
                } else {
                    res = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
      return res;
    }
}
