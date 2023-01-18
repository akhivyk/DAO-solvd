package com.solvd.farm.dao.mysql;

import com.solvd.farm.animals.Cow;
import com.solvd.farm.dao.ICowDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CowDAO extends MySqlDAO implements ICowDAO {
    public static final String SQL_SELECT_ALL_COWS = "SELECT * FROM Cow";
    public static final String SQL_SELECT_COW_ID =
            "SELECT * FROM Cow WHERE idCow=?";

    public static final String SQL_DELETE_COW_ID = "DELETE FROM Cow WHERE idCow=?";
    public static final String SQL_INSERT_COW = "INSERT INTO Cow (age, name, weight, Farm_idFarm) VALUES (?, ?, ?, 1)";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<Cow> getAllCows() {
        List<Cow> allCows = new ArrayList<>();
        try (Connection connection = (Connection) MySqlDAO.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_COWS);
            while (rs.next()) {
                int id = rs.getInt(1);
                int age = rs.getInt(2);
                String name = rs.getString(3);
                double weight = rs.getDouble(4);
                int idBelongsFarm = rs.getInt(5);
                allCows.add(new Cow(id, age, name, weight, idBelongsFarm));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return allCows;
    }

    @Override
    public Cow createCow(int age, String name, double weight) {
        try (Connection connection = (Connection) MySqlDAO.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_COW)) {
            statement.setInt(1, age);
            statement.setString(2, name);
            statement.setDouble(3, weight);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Cow> getAllFarmCows(int idFarm, MySQLConnectionPool connPool) throws SQLException {
        String SQL_SELECT_COWS = "SELECT * FROM Cow WHERE Farm_idFarm = ?";
        List<Cow> allCowsByFarm = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connPool.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_COWS)) {
                statement.setInt(1, idFarm);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    int age = rs.getInt(2);
                    String name = rs.getString(3);
                    double weight = rs.getDouble(4);
                    int idBelongsFarm = rs.getInt(5);
                    allCowsByFarm.add(new Cow(id, age, name, weight, idBelongsFarm));
                }
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            connPool.returnConnection(connection);
        }

        return allCowsByFarm;
    }

    @Override
    public Cow getCowById(long idCow) {
        Cow cow = null;
        try (Connection connection = (Connection) MySqlDAO.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_COW_ID)) {
            statement.setInt(1, (int) idCow);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                int age = rs.getInt(2);
                String name = rs.getString(3);
                double weight = rs.getDouble(4);
                int idBelongsFarm = rs.getInt(5);
                cow = new Cow(id, age, name, weight, idBelongsFarm);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return cow;
    }

    @Override
    public Cow getCowByName(String name) {
        return null;
    }

    @Override
    public void removeCow(long id) {
        try (Connection connection = (Connection) MySqlDAO.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_COW_ID)) {
            statement.setInt(1, (int) id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public Cow getEntityById(long id) {
        return null;
    }

    @Override
    public void updateEntity(Cow entity) {

    }

    @Override
    public Cow createEntity(Cow entity) {
        return null;
    }

    @Override
    public void removeEntity(long id) {

    }
}
