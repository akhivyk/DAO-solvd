package com.solvd.farm.dao;

import com.solvd.farm.animals.Cow;
import com.solvd.farm.dao.mysql.MySQLConnectionPool;

import java.sql.SQLException;
import java.util.List;

public interface ICowDAO extends IBaseDAO<Cow> {
    List<Cow> getAllCows();

    Cow createCow(int age, String name, double weight, MySQLConnectionPool connPool);

    List<Cow> getAllFarmCows(int idFarm, MySQLConnectionPool connPool) throws SQLException;

    Cow getCowById(long idCow);

    Cow getCowByName(String name);

    void removeCow(long id);
}
