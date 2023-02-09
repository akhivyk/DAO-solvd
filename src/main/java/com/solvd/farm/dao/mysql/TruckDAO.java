package com.solvd.farm.dao.mysql;

import com.solvd.farm.dao.ITruckDAO;
import com.solvd.farm.transport.Truck;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TruckDAO extends DAO implements ITruckDAO {
    public static final String SQL_SELECT_ALL_TRUCKS = "SELECT * FROM Truck";
    public static final String SQL_SELECT_TRUCK_ID =
            "SELECT * FROM Truck WHERE idTruck=?";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Truck getEntityById(long id) {
        return null;
    }

    @Override
    public void updateEntity(Truck entity) {

    }

    @Override
    public Truck createEntity(Truck entity) {
        return null;
    }

    @Override
    public void removeEntity(long id) {

    }

    @Override
    public List<Truck> getAllTrucks() {
        List<Truck> allTrucks = new ArrayList<>();
        try (Connection connection = (Connection) DAO.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_TRUCKS);
            while (rs.next()) {
                int id = rs.getInt(1);
                String carBrand = rs.getString(2);
                int maxSpeed = rs.getInt(3);
                double liftingCapacity = rs.getDouble(4);
                allTrucks.add(new Truck(id, carBrand, maxSpeed, liftingCapacity));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return allTrucks;
    }

    @Override
    public Truck getTruckById(long idTruck) {
        Truck truck = null;
        try (Connection connection = (Connection) DAO.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_TRUCK_ID)) {
            statement.setInt(1, (int) idTruck);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String carBrand = rs.getString(2);
                int maxSpeed = rs.getInt(3);
                double liftingCapacity = rs.getDouble(4);
                truck = new Truck(id, carBrand, maxSpeed, liftingCapacity);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return truck;
    }
}
