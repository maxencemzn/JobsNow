package JobsNow.dao.impl;

import JobsNow.dao.AnnonceDao;
import JobsNow.entities.Annonce;

import java.sql.*;

public class AnnonceDaoImpl implements AnnonceDao {

    @Override
    public Annonce addAnnonce(Annonce annonce){
        String query = "INSERT INTO annonce(titre, description, dateDebut, lieu, formation, tenue, remuneration) VALUES(?,?,?,?,?,?,?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, annonce.getTitre());
            statement.setString(2, annonce.getDescription());
            statement.setDate(3,annonce.getDateDebut());
            statement.setString(4, annonce.getLieu());
            statement.setInt(5, annonce.getFormation());
            statement.setString(6, annonce.getTenue());
            statement.setDouble(7, annonce.getRemuneration());
            statement.executeUpdate();

            try (ResultSet ids = statement.getGeneratedKeys()){
                if (ids.next()){
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
}
