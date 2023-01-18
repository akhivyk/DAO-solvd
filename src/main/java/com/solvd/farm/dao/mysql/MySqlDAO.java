package com.solvd.farm.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDAO {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://52.59.193.212:3306/";
        String user = "root";
        String pass = "devintern";
        String dbName = "Farm";

        return DriverManager.getConnection(url + dbName, user, pass);
    }
}
