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
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Horse getEntityById(long id) {
        return null;
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

    @Override
    public Horse getHorseByName(String name) {
        return null;
    }
}
