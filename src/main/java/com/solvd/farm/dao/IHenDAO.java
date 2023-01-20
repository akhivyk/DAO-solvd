package com.solvd.farm.dao;

import com.solvd.farm.animals.Hen;
import com.solvd.farm.dao.mysql.MySQLConnectionPool;

import java.util.List;

public interface IHenDAO extends IBaseDAO<Hen> {
    List<Hen> getAllHens();

    Hen getHenById(long idHen);

    Hen createHen(int age, String name, double weight, MySQLConnectionPool connPool);

    Hen getHenByName(String name);
}
