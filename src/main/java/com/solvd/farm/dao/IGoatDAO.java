package com.solvd.farm.dao;

import com.solvd.farm.animals.Goat;

import java.util.List;

public interface IGoatDAO extends IBaseDAO<Goat> {
    List<Goat> getAllGoats();

    Goat getGoatById(long idGoat);

    Goat getGoatByName(String name);
}
