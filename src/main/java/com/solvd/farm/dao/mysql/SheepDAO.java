package com.solvd.farm.dao.mysql;

import com.solvd.farm.animals.Sheep;
import com.solvd.farm.dao.ISheepDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SheepDAO extends MySqlDAO implements ISheepDAO {
    public static final String SQL_SELECT_ALL_SHEEP = "SELECT * FROM Sheep";
    public static final String SQL_SELECT_SHEEP_ID =
            "SELECT * FROM Sheep WHERE idSheep=?";
    private static final Logger logger = LogManager.getLogger();


    @Override
    public Sheep getEntityById(long id) {
        return null;
    }

    @Override
    public void updateEntity(Sheep entity) {

    }

    @Override
    public Sheep createEntity(Sheep entity) {
        return null;
    }

    @Override
    public void removeEntity(long id) {

    }

    @Override
    public List<Sheep> getAllSheep() {
        List<Sheep> allSheep = new ArrayList<>();
        try (Connection connection = (Connection) MySqlDAO.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_SHEEP);
            while (rs.next()) {
                int id = rs.getInt(1);
                int age = rs.getInt(2);
                String name = rs.getString(3);
                double weight = rs.getDouble(4);
                String color = rs.getString(5);
                int idBelongsFarm = rs.getInt(6);
                allSheep.add(new Sheep(id, age, name, weight, color, idBelongsFarm));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return allSheep;
    }

    @Override
    public Sheep getSheepById(long idSheep) {
        Sheep sheep = null;
        try (Connection connection = (Connection) MySqlDAO.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_SHEEP_ID)) {
            statement.setInt(1, (int) idSheep);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                int age = rs.getInt(2);
                String name = rs.getString(3);
                double weight = rs.getDouble(4);
                String color = rs.getString(5);
                int idBelongsFarm = rs.getInt(6);
                sheep = new Sheep(id, age, name, weight, color, idBelongsFarm);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return sheep;
    }

    public List<Sheep> getAllFarmSheep(int idFarm, MySQLConnectionPool connPool) throws SQLException {
        String SQL_SELECT_HENS= "SELECT * FROM Sheep WHERE Farm_idFarm = ?";
        List<Sheep> allSheepByFarm = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connPool.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_HENS)) {
                statement.setInt(1, idFarm);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    int age = rs.getInt(2);
                    String name = rs.getString(3);
                    double weight = rs.getDouble(4);
                    String color = rs.getString(5);
                    int idBelongsFarm = rs.getInt(6);
                    allSheepByFarm.add(new Sheep(id, age, name, weight, color, idBelongsFarm));
                }
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            connPool.returnConnection(connection);
        }

        return allSheepByFarm;
    }

    @Override
    public Sheep getSheepByName(String name) {
        return null;
    }

    @Override
    public List<Sheep> getAllSheepColor(String color) {
        return null;
    }
}
