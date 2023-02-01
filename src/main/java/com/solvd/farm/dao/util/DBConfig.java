package com.solvd.farm.dao.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DBConfig {
    private final static Logger log = LogManager.getLogger();

    private static final Properties props = new Properties();

    static {
        try {
            props.load(new FileReader("db.properties"));
        } catch (FileNotFoundException e) {
            log.error("File was not found", e);
        } catch (IOException e) {
            log.error("Exception while loading properties file", e);
        }
    }

    private DBConfig() {

    }

    public static Properties getProps() {
        return props;
    }
}
