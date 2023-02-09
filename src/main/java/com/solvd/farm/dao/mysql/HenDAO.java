package com.solvd.farm.dao.mysql;

import com.solvd.farm.animals.Hen;
import com.solvd.farm.dao.IHenDAO;
import com.solvd.farm.dao.util.MyBatisDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class HenDAO extends DAO implements IHenDAO {
    private static final SqlSessionFactory SESSION_FACTORY = MyBatisDao.getSqlSessionFactory();
    private final SqlSession sqlSession = SESSION_FACTORY.openSession();
    private final IHenDAO iHenDAO = sqlSession.getMapper(IHenDAO.class);

    @Override
    public long createHen(Hen hen) {
        long newId;
        iHenDAO.createHen(hen);
        newId = iHenDAO.getMaxId() + 1;
        sqlSession.commit();
        return newId;
    }

    @Override
    public long getMaxId() {
        return iHenDAO.getMaxId();
    }

    public long cloneHen(String oldName, String newName) {
        Hen h = this.getHenByName(oldName);
        h.setName(newName);
        return this.createHen(h);
    }

    @Override
    public Hen getHenById(long idHen) {
        return iHenDAO.getHenById(idHen);
    }

    @Override
    public Hen getHenByName(String name) {
        return iHenDAO.getHenByName(name);
    }

    @Override
    public List<Hen> getAllFarmHens(int idFarm) {
        return iHenDAO.getAllFarmHens(idFarm);
    }

    @Override
    public void removeHen(long id) {
        iHenDAO.removeHen(id);
        sqlSession.commit();
    }

    @Override
    public boolean removeHenName(String name) {
        iHenDAO.removeHenName(name);
        sqlSession.commit();
        return true;
    }

    @Override
    public boolean updateHen(String name, double weight, int age, int id) {
        iHenDAO.updateHen(name, weight, age, id);
        sqlSession.commit();
        return true;
    }

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
}
