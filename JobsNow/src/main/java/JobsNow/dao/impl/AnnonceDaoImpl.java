package JobsNow.dao.impl;

import JobsNow.dao.AnnonceDao;
import JobsNow.entities.Annonce;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnnonceDaoImpl implements AnnonceDao {

    @Override
    public Annonce addAnnonce(Annonce annonce) {
        String query = "INSERT INTO annonce(titre, description, dateDebut, lieu, formation, tenue, remuneration) VALUES(?,?,?,?,?,?,?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, annonce.getTitre());
            statement.setString(2, annonce.getDescription());
            statement.setDate(3, annonce.getDateDebut());
            statement.setString(4, annonce.getLieu());
            statement.setString(5, annonce.getFormation());
            statement.setString(6, annonce.getTenue());
            statement.setDouble(7, annonce.getRemuneration());
            statement.executeUpdate();

            try (ResultSet ids = statement.getGeneratedKeys()) {
                if (ids.next()) {
                    int generatedId = ids.getInt(1);
                    annonce.setIdAnnonce(generatedId);
                    return annonce;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Annonce> listAnnonce() {
        String query = "SELECT * FROM annonce ORDER BY idAnnonce";
        List<Annonce> listDesAnnonces = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()){
                listDesAnnonces.add(new Annonce(rs.getInt("idAnnonce"),
                                                rs.getString("titre"),
                                                rs.getString("description"),
                                                rs.getDate("dateDebut"),
                                                rs.getString("lieu"),
                                                rs.getString("formation"),
                                                rs.getString("tenue"),
                                                rs.getDouble("remuneration")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDesAnnonces;
    }

    @Override
    public void delAnnonce(Integer idAnnonce){
        String queryF = "DELETE FROM faire WHERE idAnnonce=?";
        String queryA = "DELETE FROM annonce WHERE idAnnonce=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(queryF)){
            statement.setInt(1, idAnnonce);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(queryA)){
            statement.setInt(1, idAnnonce);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
