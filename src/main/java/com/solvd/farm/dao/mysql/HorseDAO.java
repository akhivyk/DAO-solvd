package com.solvd.farm.dao.mysql;

import com.solvd.farm.animals.Horse;
import com.solvd.farm.dao.IHorseDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HorseDAO extends MySqlDAO implements IHorseDAO {
    public static final String SQL_SELECT_ALL_HORSES = "SELECT * FROM Horse";
    public static final String SQL_SELECT_HORSE_ID =
            "SELECT * FROM Horse WHERE idHorse=?";
    public static final String SQL_DELETE_HORSE_NAME = "DELETE FROM Horse WHERE name=?";
    private static final Logger logger = LogManager.getLogger();
    public static final String SQL_INSERT_HORSE = "INSERT INTO Horse (age, name, weight, Farm_idFarm) VALUES (?, ?, ?, 1)";

    @Override
    public Horse getEntityById(long id) {
        return null;
    }

    public Horse createHorse(int a, String n, double w, MySQLConnectionPool connPool) {
        Horse horse = null;
        int MAX_ID = 0;
        try (Connection connection = connPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_HORSE)) {
            PreparedStatement st = connection.prepareStatement("SELECT MAX(idHorse) FROM Horse" );
            ResultSet idRS = st.executeQuery();
            if (idRS.next()) {
                MAX_ID = idRS.getInt(1);
            }
            statement.setInt(1, a);
            statement.setString(2, n);
            statement.setDouble(3, w);
            statement.executeUpdate();
            horse = new Horse(MAX_ID + 1, a, n, w, 1);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return horse;
    }

    public Horse cloneHorse(String oldName, String newName, MySQLConnectionPool connPool) {
        Horse horse = null;
        int MAX_ID = 0;
        String request = "SELECT * FROM Horse WHERE name = ?";
        try (Connection connection = connPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(request)) {
            PreparedStatement st = connection.prepareStatement("SELECT MAX(idHorse) FROM Horse" );
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
                horse = new Horse(MAX_ID + 1, age, newName, weight, idBelongsFarm);
                createHorse(age, newName, weight, connPool);
            } else {
                throw new RuntimeException("Лошадь с указанной кличкой отсутствует на ферме.");
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return horse;
    }

    public boolean updateHorse(MySQLConnectionPool connPool, String name, String newName, double weight, int age) {
        String request = "SELECT * FROM Horse WHERE name = ?";
        try (Connection connection = connPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);

                String upd = "UPDATE Horse SET name = ?, weight = ?, age = ? WHERE idHorse = ?";
                PreparedStatement st2 = connection.prepareStatement(upd);
                st2.setString(1, newName);
                st2.setDouble(2, weight);
                st2.setInt(3, age);
                st2.setInt(4, id);
                st2.executeUpdate();
            } else {
                throw new RuntimeException("Лошадь с указанной кличкой отсутствует на ферме.");
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return true;
    }

    @Override
    public void updateEntity(Horse entity) {

    }

    @Override
    public Horse createEntity(Horse entity) {
        return null;
    }

    @Override
    public void removeEntity(long id) {

    }

    @Override
    public List<Horse> getAllHorses() {
        List<Horse> allHorses = new ArrayList<>();
        try (Connection connection = (Connection) MySqlDAO.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_HORSES);
            while (rs.next()) {
                int id = rs.getInt(1);
                int age = rs.getInt(2);
                String name = rs.getString(3);
                double weight = rs.getDouble(4);
                int idBelongsFarm = rs.getInt(5);
                allHorses.add(new Horse(id, age, name, weight, idBelongsFarm));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return allHorses;
    }

    @Override
    public Horse getHorseById(long idHorse) {
        Horse horse = null;
        try (Connection connection = (Connection) MySqlDAO.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_HORSE_ID)) {
            statement.setInt(1, (int) idHorse);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                int age = rs.getInt(2);
                String name = rs.getString(3);
                double weight = rs.getDouble(4);
                int idBelongsFarm = rs.getInt(5);
                horse = new Horse(id, age, name, weight, idBelongsFarm);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return horse;
    }

    public List<Horse> getAllFarmHorse(int idFarm, MySQLConnectionPool connPool) throws SQLException {
        String SQL_SELECT_HORSES = "SELECT * FROM Horse WHERE Farm_idFarm = ?";
        List<Horse> allHorsesByFarm = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connPool.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_HORSES)) {
                statement.setInt(1, idFarm);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    int age = rs.getInt(2);
                    String name = rs.getString(3);
                    double weight = rs.getDouble(4);
                    int idBelongsFarm = rs.getInt(5);
                    allHorsesByFarm.add(new Horse(id, age, name, weight, idBelongsFarm));
                }
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            connPool.returnConnection(connection);
        }

        return allHorsesByFarm;
    }

    public boolean removeHorse(String name) {
        try (Connection connection = (Connection) MySqlDAO.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_HORSE_NAME)) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return true;
    }

    @Override
    public Horse getHorseByName(String name) {
        return null;
    }
}
