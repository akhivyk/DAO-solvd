package com.solvd.farm.dao;

import com.solvd.farm.transport.Truck;

import java.util.List;

public interface ITruckDAO extends IBaseDAO<Truck> {
    List<Truck> getAllTrucks();

    Truck getTruckById(long idTruck);
}
