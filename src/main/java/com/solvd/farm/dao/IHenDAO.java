package com.solvd.farm.dao;

import com.solvd.farm.animals.Hen;

import java.util.List;

public interface IHenDAO extends IBaseDAO<Hen> {
    List<Hen> getAllHens();

    Hen getHenById(long idHen);

    Hen getHenByName(String name);
}
