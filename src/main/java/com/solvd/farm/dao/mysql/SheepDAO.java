package com.solvd.farm.dao.mysql;

import com.solvd.farm.animals.Sheep;
import com.solvd.farm.dao.ISheepDAO;
import com.solvd.farm.dao.util.MyBatisDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class SheepDAO extends DAO implements ISheepDAO {
    private static final SqlSessionFactory SESSION_FACTORY = MyBatisDao.getSqlSessionFactory();
    private final SqlSession sqlSession = SESSION_FACTORY.openSession();
    private final ISheepDAO iSheepDAO = sqlSession.getMapper(ISheepDAO.class);


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
        return null;
    }

    @Override
    public Sheep getSheepById(long idSheep) {
        return null;
    }

    @Override
    public List<Sheep> getAllFarmSheep(int idFarm) {
        return iSheepDAO.getAllFarmSheep(idFarm);
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
