package com.solvd.farm.dao;

import com.solvd.farm.people.Farmer;

import java.util.List;

public interface IFarmerDAO extends IBaseDAO<Farmer> {
    List<Farmer> getAllFarmers();

    Farmer getFarmerById(long idFarmer);

    Farmer getFarmerByName(String name);
}
