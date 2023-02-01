package com.solvd.farm.dao.mysql;

import com.solvd.farm.animals.Horse;
import com.solvd.farm.dao.IHorseDAO;
import com.solvd.farm.dao.util.MyBatisDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class HorseDAO extends MySqlDAO implements IHorseDAO {

    private static final SqlSessionFactory SESSION_FACTORY = MyBatisDao.getSqlSessionFactory();
    private final SqlSession sqlSession = SESSION_FACTORY.openSession();
    private final IHorseDAO iHorseDAO = sqlSession.getMapper(IHorseDAO.class);

    @Override
    public long createHorse(Horse horse) {
        long newId;
        iHorseDAO.createHorse(horse);
        newId = iHorseDAO.getMaxId() + 1;
        sqlSession.commit();
        return newId;
    }

    @Override
    public long getMaxId() {
        return iHorseDAO.getMaxId();
    }

    public long cloneHorse(String oldName, String newName) {
        Horse h = this.getHorseByName(oldName);
        h.setName(newName);
        return this.createHorse(h);
    }

    @Override
    public Horse getHorseById(long idHorse) {
        return iHorseDAO.getHorseById(idHorse);
    }

    @Override
    public Horse getHorseByName(String name) {
        return iHorseDAO.getHorseByName(name);
    }

    @Override
    public List<Horse> getAllFarmHorses(int idFarm) {
        return iHorseDAO.getAllFarmHorses(idFarm);
    }

    @Override
    public void removeHorse(long id) {
        iHorseDAO.removeHorse(id);
        sqlSession.commit();
    }

    @Override
    public boolean removeHorseName(String name) {
        iHorseDAO.removeHorseName(name);
        sqlSession.commit();
        return true;
    }

    @Override
    public boolean updateHorse(String name, double weight, int age, int id) {
        iHorseDAO.updateHorse(name, weight, age, id);
        sqlSession.commit();
        return true;
    }

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
}
