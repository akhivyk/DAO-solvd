package com.solvd.farm.dao.mysql;

import com.solvd.farm.animals.Cow;
import com.solvd.farm.dao.ICowDAO;
import com.solvd.farm.dao.util.MyBatisDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class CowDAO extends DAO implements ICowDAO {
    private static final SqlSessionFactory SESSION_FACTORY = MyBatisDao.getSqlSessionFactory();
    private final SqlSession sqlSession = SESSION_FACTORY.openSession();
    private final ICowDAO iCowDAO = sqlSession.getMapper(ICowDAO.class);

    @Override
    public long createCow(Cow cow) {
        long newId;
        iCowDAO.createCow(cow);
        newId = iCowDAO.getMaxId() + 1;
        sqlSession.commit();
        return newId;
    }

    @Override
    public long getMaxId() {
        return iCowDAO.getMaxId();
    }

    public long cloneCow(String oldName, String newName) {
        Cow c = this.getCowByName(oldName);
        c.setName(newName);
        return this.createCow(c);
    }

    @Override
    public Cow getCowById(long idCow) {
        return iCowDAO.getCowById(idCow);
    }

    @Override
    public Cow getCowByName(String name) {
        return iCowDAO.getCowByName(name);
    }

    @Override
    public List<Cow> getAllFarmCows(int idFarm) {
        return iCowDAO.getAllFarmCows(idFarm);
    }

    @Override
    public void removeCow(long id) {
        iCowDAO.removeCow(id);
        sqlSession.commit();
    }

    @Override
    public boolean updateCow(String name, double weight, int age, int id) {
        iCowDAO.updateCow(name, weight, age, id);
        sqlSession.commit();
        return true;
    }

    @Override
    public boolean removeCowName(String name) {
        iCowDAO.removeCowName(name);
        sqlSession.commit();
        return true;
    }

    @Override
    public Cow getEntityById(long id) {
        return null;
    }

    @Override
    public void updateEntity(Cow entity) {

    }

    @Override
    public Cow createEntity(Cow entity) {
        return null;
    }

    @Override
    public void removeEntity(long id) {

    }
}
