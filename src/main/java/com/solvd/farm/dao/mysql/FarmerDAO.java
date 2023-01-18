package com.solvd.farm.dao.mysql;

import com.solvd.farm.dao.IFarmerDAO;
import com.solvd.farm.people.Farmer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FarmerDAO extends MySqlDAO implements IFarmerDAO {
    public static final String SQL_SELECT_ALL_FARMERS = "SELECT * FROM Farmer";
    public static final String SQL_SELECT_FARMER_ID =
            "SELECT * FROM Farmer WHERE idFarmer=?";
    private static final Logger logger = LogManager.getLogger();


    @Override
    public List<Farmer> getAllFarmers() {
        List<Farmer> allFarmers = new ArrayList<>();
        try (Connection connection = (Connection) MySqlDAO.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_FARMERS);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int age = rs.getInt(3);
                allFarmers.add(new Farmer(id, name, age));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return allFarmers;
    }

    @Override
    public Farmer getFarmerById(long idFarmer) {
        Farmer f = null;
        try (Connection connection = (Connection) MySqlDAO.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_FARMER_ID)) {
            statement.setInt(1, (int) idFarmer);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int age = rs.getInt(3);
                f = new Farmer(id, name, age);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return f;
    }

    @Override
    public Farmer getEntityById(long id) {
        return null;
    }

    @Override
    public void updateEntity(Farmer entity) {

    }

    @Override
    public Farmer createEntity(Farmer entity) {
        return null;
    }

    @Override
    public void removeEntity(long id) {

    }

    @Override
    public Farmer getFarmerByName(String name) {
        return null;
    }
}
