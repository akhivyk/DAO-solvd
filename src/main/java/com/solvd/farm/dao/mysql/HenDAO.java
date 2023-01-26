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
    public static final String SQL_INSERT_HEN = "INSERT INTO Hen (age, name, weight, Farm_idFarm) VALUES (?, ?, ?, 1)";
    public static final String SQL_DELETE_HEN_NAME = "DELETE FROM Hen WHERE name=?";
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

    @Override
    public Hen createHen(int a, String n, double w, MySQLConnectionPool connPool) {
        Hen hen = null;
        int MAX_ID = 0;
        try (Connection connection = connPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_HEN)) {
            PreparedStatement st = connection.prepareStatement("SELECT MAX(idHen) FROM Hen");
            ResultSet idRS = st.executeQuery();
            if (idRS.next()) {
                MAX_ID = idRS.getInt(1);
            }
            statement.setInt(1, a);
            statement.setString(2, n);
            statement.setDouble(3, w);
            statement.executeUpdate();
            hen = new Hen(MAX_ID + 1, a, n, w, 1);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return hen;
    }

    public Hen cloneHen(String oldName, String newName, MySQLConnectionPool connPool) {
        Hen hen = null;
        int MAX_ID = 0;
        String request = "SELECT * FROM Hen WHERE name = ?";
        try (Connection connection = connPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(request)) {
            PreparedStatement st = connection.prepareStatement("SELECT MAX(idHen) FROM Hen");
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
                hen = new Hen(MAX_ID + 1, age, newName, weight, idBelongsFarm);
                createHen(age, newName, weight, connPool);
            } else {
                logger.info("Курица с указанной кличкой отсутсвует на ферме.");
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return hen;
    }

    public boolean updateHen(MySQLConnectionPool connPool, String name, String newName, double weight, int age) {
        String request = "SELECT * FROM Hen WHERE name = ?";
        try (Connection connection = connPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);

                String upd = "UPDATE Hen SET name = ?, weight = ?, age = ? WHERE idHen = ?";
                PreparedStatement st2 = connection.prepareStatement(upd);
                st2.setString(1, newName);
                st2.setDouble(2, weight);
                st2.setInt(3, age);
                st2.setInt(4, id);
                st2.executeUpdate();
            } else {
                throw new RuntimeException("Курица с указанной кличкой отсутствует на ферме.");
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return true;
    }

    public List<Hen> getAllFarmHen(int idFarm, MySQLConnectionPool connPool) throws SQLException {
        String SQL_SELECT_HENS = "SELECT * FROM Hen WHERE Farm_idFarm = ?";
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

    public boolean removeHen(String name) {
        try (Connection connection = (Connection) MySqlDAO.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_HEN_NAME)) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return true;
    }

    @Override
    public Hen getHenByName(String name) {
        return null;
    }
}
