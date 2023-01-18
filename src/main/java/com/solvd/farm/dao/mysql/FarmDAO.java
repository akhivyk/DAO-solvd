package com.solvd.farm.dao.mysql;

import com.solvd.farm.Farm;
import com.solvd.farm.animals.Goat;
import com.solvd.farm.animals.Hen;
import com.solvd.farm.animals.Sheep;
import com.solvd.farm.dao.IFarmDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class FarmDAO extends MySqlDAO implements IFarmDAO {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Farm getEntityById(long id) {
        return null;
    }

    @Override
    public void updateEntity(Farm entity) {

    }

    @Override
    public Farm createEntity(Farm entity) {
        return null;
    }

    @Override
    public void removeEntity(long id) {

    }

    @Override
    public List<Farm> getAllFarms() {
        return null;
    }

    @Override
    public Farm getFarmById() {
        return null;
    }

    public void getAllInformation(int idFarm, MySQLConnectionPool connPool) throws SQLException {
        CowDAO cowDAO = new CowDAO();
        GoatDAO goatDAO = new GoatDAO();
        HenDAO henDAO = new HenDAO();
        HorseDAO horseDAO = new HorseDAO();
        SheepDAO sheepDAO = new SheepDAO();
        cowDAO.getAllFarmCows(idFarm, connPool).forEach(logger::info);
        goatDAO.getAllFarmGoats(idFarm, connPool).forEach(logger::info);
        henDAO.getAllFarmHen(idFarm, connPool).forEach(logger::info);
        horseDAO.getAllFarmHorse(idFarm, connPool).forEach(logger::info);
        sheepDAO.getAllFarmSheep(idFarm, connPool).forEach(logger::info);
    }
}
