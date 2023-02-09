package com.solvd.farm.dao.mysql;

import com.solvd.farm.dao.ITractorDAO;
import com.solvd.farm.transport.Tractor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TractorDAO extends DAO implements ITractorDAO {
    public static final String SQL_SELECT_ALL_TRACTORS = "SELECT * FROM Tractor";
    public static final String SQL_SELECT_TRACTOR_ID =
            "SELECT * FROM Tractor WHERE idTractor=?";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Tractor getEntityById(long id) {
        return null;
    }

    @Override
    public void updateEntity(Tractor entity) {

    }

    @Override
    public Tractor createEntity(Tractor entity) {
        return null;
    }

    @Override
    public void removeEntity(long id) {

    }

    @Override
    public List<Tractor> getAllTractors() {
        List<Tractor> allTractors = new ArrayList<>();
        try (Connection connection = (Connection) DAO.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_TRACTORS);
            while (rs.next()) {
                int id = rs.getInt(1);
                String carBrand = rs.getString(2);
                int maxSpeed = rs.getInt(3);
                String color = rs.getString(4);
                allTractors.add(new Tractor(id, carBrand, maxSpeed, color));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return allTractors;
    }

    @Override
    public Tractor getTractorById(long idTractor) {
        Tractor tractor = null;
        try (Connection connection = (Connection) DAO.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_TRACTOR_ID)) {
            statement.setInt(1, (int) idTractor);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String carBrand = rs.getString(2);
                int maxSpeed = rs.getInt(3);
                String color = rs.getString(4);
                tractor = new Tractor(id, carBrand, maxSpeed, color);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return tractor;
    }
}
