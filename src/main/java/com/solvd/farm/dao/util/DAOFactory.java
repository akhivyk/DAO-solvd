package com.solvd.farm.dao.util;

import com.solvd.farm.dao.mysql.*;

public class DAOFactory {
    private CowDAO cowDAO;
    private GoatDAO goatDAO;
    private HenDAO henDAO;
    private HorseDAO horseDAO;
    private SheepDAO sheepDAO;

    public DAO createDAO (DAOTypes daoTypes) {
        DAO dao = null;

        switch (daoTypes) {
            case COW_DAO -> dao = new CowDAO();
            case FARM_DAO -> dao = new FarmDAO();
            case FEED_DAO -> dao = new FeedDAO();
            case HEN_DAO -> dao = new HenDAO();
            case GOAT_DAO -> dao = new GoatDAO();
            case HORSE_DAO -> dao = new HorseDAO();
            case SHEEP_DAO -> dao = new SheepDAO();
            case TRUCK_DAO -> dao = new TruckDAO();
            case FARMER_DAO -> dao = new FarmerDAO();
            case TRACTOR_DAO -> dao = new TractorDAO();
        }

        return dao;
    }

    public void createAllDAO() {
        this.cowDAO = new CowDAO();
        this.goatDAO = new GoatDAO();
        this.henDAO = new HenDAO();
        this.horseDAO = new HorseDAO();
        this.sheepDAO = new SheepDAO();
    }

    public CowDAO getCowDAO() {
        return cowDAO;
    }

    public GoatDAO getGoatDAO() {
        return goatDAO;
    }

    public HenDAO getHenDAO() {
        return henDAO;
    }

    public HorseDAO getHorseDAO() {
        return horseDAO;
    }

    public SheepDAO getSheepDAO() {
        return sheepDAO;
    }
}
