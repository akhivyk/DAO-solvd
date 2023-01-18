package com.solvd.farm.dao;

import com.solvd.farm.transport.Tractor;

import java.util.List;

public interface ITractorDAO extends IBaseDAO<Tractor> {
    List<Tractor> getAllTractors();

    Tractor getTractorById(long idTractor);
}
