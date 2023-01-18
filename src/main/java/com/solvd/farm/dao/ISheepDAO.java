package com.solvd.farm.dao;

import com.solvd.farm.animals.Sheep;

import java.util.List;

public interface ISheepDAO extends IBaseDAO<Sheep> {
    List<Sheep> getAllSheep();

    Sheep getSheepById(long idSheep);

    Sheep getSheepByName(String name);

    List<Sheep> getAllSheepColor(String color);
}
