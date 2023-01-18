package com.solvd.farm.dao;

import com.solvd.farm.animals.Horse;

import java.util.List;

public interface IHorseDAO extends IBaseDAO<Horse> {
    List<Horse> getAllHorses();

    Horse getHorseById(long idHorse);

    Horse getHorseByName(String name);
}
