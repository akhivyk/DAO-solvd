package com.solvd.farm.dao.mysql;

import com.solvd.farm.animals.Hen;
import com.solvd.farm.dao.IHenDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HenDAO extends MySqlDAO implements IHenDAO {
    public static final String SQL_SELECT_ALL_HENS = "SELECT * FROM Hen";
    public static final String SQL_SELECT_HEN_ID =
            "SELECT * FROM Hen WHERE idHen=?";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Hen getEntityById(long id) {
        return null;
    }

    @Override
    public void updateEntity(Hen entity) {

    }

    @Override
    public Hen createEntity(Hen entity) {
        return null;
    }

    @Override
    public void removeEntity(long id) {

    }

    @Override
    public List<Hen> getAllHens() {
        List<Hen> allHens = new ArrayList<>();
        try (Connection connection = (Connection) MySqlDAO.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_HENS);
            while (rs.next()) {
                int id = rs.getInt(1);
                int age = rs.getInt(2);
                String name = rs.getString(3);
                double weight = rs.getDouble(4);
                int idBelongsFarm = rs.getInt(5);
                allHens.add(new Hen(id, age, name, weight, idBelongsFarm));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return allHens;
    }

    @Override
    public Hen getHenById(long idHen) {
        Hen hen = null;
        try (Connection connection = (Connection) MySqlDAO.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_HEN_ID)) {
            statement.setInt(1, (int) idHen);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                int age = rs.getInt(2);
                String name = rs.getString(3);
                double weight = rs.getDouble(4);
                int idBelongsFarm = rs.getInt(5);
                hen = new Hen(id, age, name, weight, idBelongsFarm);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return hen;
    }

    public List<Hen> getAllFarmHen(int idFarm, MySQLConnectionPool connPool) throws SQLException {
        String SQL_SELECT_HENS= "SELECT * FROM Hen WHERE Farm_idFarm = ?";
        List<Hen> allHensByFarm = new ArrayList<>();
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
                    int idBelongsFarm = rs.getInt(5);
                    allHensByFarm.add(new Hen(id, age, name, weight, idBelongsFarm));
                }
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            connPool.returnConnection(connection);
        }

        return allHensByFarm;
    }

    @Override
    public Hen getHenByName(String name) {
        return null;
    }
}
