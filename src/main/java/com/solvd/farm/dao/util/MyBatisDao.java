package com.solvd.farm.dao.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;

public class MyBatisDao {
    private final static Logger log = LogManager.getLogger();
    private final static MyBatisDao myBatisDao = new MyBatisDao();
    private static SqlSessionFactory sqlSessionFactory;

    private MyBatisDao() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-properties.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
