package com.solvd.farm;

public class Farm {
    private static long idFarm;
    private String farmName;

    public Farm() {

    }

    public Farm(String farmName) {
        this.farmName = farmName;
    }

    @Override
    public String toString() {
        return "Farm{" +
                "farmName='" + farmName + '\'' +
                '}';
    }

    public static long getIdFarm() {
        return idFarm;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }
}
