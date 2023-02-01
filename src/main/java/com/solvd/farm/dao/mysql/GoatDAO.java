package com.solvd.farm.dao.mysql;

import com.solvd.farm.animals.Goat;
import com.solvd.farm.dao.IGoatDAO;
import com.solvd.farm.dao.util.MyBatisDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoatDAO extends MySqlDAO implements IGoatDAO {
    private static final SqlSessionFactory SESSION_FACTORY = MyBatisDao.getSqlSessionFactory();
    private static final Logger logger = LogManager.getLogger();
    private final SqlSession sqlSession = SESSION_FACTORY.openSession();
    private final IGoatDAO iGoatDAO = sqlSession.getMapper(IGoatDAO.class);

    @Override
    public Goat getEntityById(long id) {
        return null;
    }

    @Override
    public void updateEntity(Goat entity) {

    }

    @Override
    public Goat createEntity(Goat entity) {
        return null;
    }

    @Override
    public void removeEntity(long id) {

    }

    @Override
    public List<Goat> getAllGoats() {
        return null;
    }

    @Override
    public Goat getGoatById(long idGoat) {
        return null;
    }

    @Override
    public List<Goat> getAllFarmGoats(int idFarm) {
        return iGoatDAO.getAllFarmGoats(idFarm);
    }

    @Override
    public Goat getGoatByName(String name) {
        return null;
    }
}
