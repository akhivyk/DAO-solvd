package com.solvd.farm.dao.mysql;

import com.solvd.farm.animals.Goat;
import com.solvd.farm.dao.IGoatDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoatDAO extends MySqlDAO implements IGoatDAO {
    public static final String SQL_SELECT_ALL_GOATS = "SELECT * FROM Goat";
    public static final String SQL_SELECT_GOAT_ID =
            "SELECT * FROM Goat WHERE idGoat=?";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Goat getEntityById(long id) {
        return null;
    }

    @Override
    public void updateEntity(Goat entity) {

    }

    @Override
    public Goat createEntity(Goat entity) {
        return null;
    }

    @Override
    public void removeEntity(long id) {

    }

    @Override
    public List<Goat> getAllGoats() {
        List<Goat> allGoats = new ArrayList<>();
        try (Connection connection = (Connection) MySqlDAO.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_GOATS);
            while (rs.next()) {
                int id = rs.getInt(1);
                int age = rs.getInt(2);
                String name = rs.getString(3);
                double weight = rs.getDouble(4);
                int idBelongsFarm = rs.getInt(5);
                allGoats.add(new Goat(id, age, name, weight, idBelongsFarm));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return allGoats;
    }

    @Override
    public Goat getGoatById(long idGoat) {
        Goat goat = null;
        try (Connection connection = (Connection) MySqlDAO.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_GOAT_ID)) {
            statement.setInt(1, (int) idGoat);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                int age = rs.getInt(2);
                String name = rs.getString(3);
                double weight = rs.getDouble(4);
                int idBelongsFarm = rs.getInt(5);
                goat = new Goat(id, age, name, weight, idBelongsFarm);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return goat;
    }

    public List<Goat> getAllFarmGoats(int idFarm, MySQLConnectionPool connPool) throws SQLException {
        String SQL_SELECT_GOATS = "SELECT * FROM Goat WHERE Farm_idFarm = ?";
        List<Goat> allGoatsByFarm = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connPool.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_GOATS)) {
                statement.setInt(1, idFarm);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    int age = rs.getInt(2);
                    String name = rs.getString(3);
                    double weight = rs.getDouble(4);
                    int idBelongsFarm = rs.getInt(5);
                    allGoatsByFarm.add(new Goat(id, age, name, weight, idBelongsFarm));
                }
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            connPool.returnConnection(connection);
        }

        return allGoatsByFarm;
    }

    @Override
    public Goat getGoatByName(String name) {
        return null;
    }
}
