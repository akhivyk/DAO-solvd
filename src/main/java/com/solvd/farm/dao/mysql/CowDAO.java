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
    public static final String SQL_DELETE_COW_NAME = "DELETE FROM Cow WHERE name=?";
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
    public Cow createCow(int a, String n, double w, MySQLConnectionPool connPool) {
        Cow cow = null;
        int MAX_ID = 0;
        try (Connection connection = connPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_COW)) {
            PreparedStatement st = connection.prepareStatement("SELECT MAX(idCow) FROM Cow" );
            ResultSet idRS = st.executeQuery();
            if (idRS.next()) {
                MAX_ID = idRS.getInt(1);
            }
            statement.setInt(1, a);
            statement.setString(2, n);
            statement.setDouble(3, w);
            statement.executeUpdate();
            cow = new Cow(MAX_ID + 1, a, n, w, 1);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return cow;
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

    public Cow cloneCow(String oldName, String newName, MySQLConnectionPool connPool) {
        Cow cow = null;
        int MAX_ID = 0;
        String request = "SELECT * FROM Cow WHERE name = ?";
        try (Connection connection = connPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(request)) {
            PreparedStatement st = connection.prepareStatement("SELECT MAX(idCow) FROM Cow" );
            ResultSet idRS = st.executeQuery();
            if (idRS.next()) {
                MAX_ID = idRS.getInt(1);
            }

            statement.setString(1, oldName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int age = rs.getInt(2);
                double weight = rs.getDouble(4);
                int idBelongsFarm = rs.getInt(5);
                cow = new Cow(MAX_ID + 1, age, newName, weight, idBelongsFarm);
                createCow(age, newName, weight, connPool);
            } else {
                logger.info("Корова с указанной кличкой отсутсвует на ферме.");
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return cow;
    }

    public boolean updateCow(MySQLConnectionPool connPool, String name, String newName, double weight, int age) {
        String request = "SELECT * FROM Cow WHERE name = ?";
        try (Connection connection = connPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);

                String upd = "UPDATE Cow SET name = ?, weight = ?, age = ? WHERE idCow = ?";
                PreparedStatement st2 = connection.prepareStatement(upd);
                st2.setString(1, newName);
                st2.setDouble(2, weight);
                st2.setInt(3, age);
                st2.setInt(4, id);
                st2.executeUpdate();
            } else {
                throw new RuntimeException("Корова с указанной кличкой отсутствует на ферме.");
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return true;
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

    public boolean removeCow(String name) {
        try (Connection connection = (Connection) MySqlDAO.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_COW_NAME)) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return true;
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
