package com.solvd.farm.dao;

import com.solvd.farm.Farm;

import java.util.List;

public interface IFarmDAO extends IBaseDAO<Farm> {
    List<Farm> getAllFarms();

    Farm getFarmById();
}
