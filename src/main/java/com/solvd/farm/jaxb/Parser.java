package com.solvd.farm.jaxb;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Parser {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        Car car = new Car("Opel", 220, 7);
        Computer comp1 = new Computer("Lenovo", 8, 16.5);
        Computer comp2 = new Computer("Apple", 16, 13.5);
        List<Computer> computers = new ArrayList<>();
        computers.add(comp1);
        computers.add(comp2);
        User u = new User(1, "Arnold", "WWW", 23, new Date(22-11-2022), car, computers);
        JAXBContext c = JAXBContext.newInstance(User.class);
        Marshaller m = c.createMarshaller();
        Unmarshaller um = c.createUnmarshaller();
        m.marshal(u, new File("user.xml"));
        logger.info(u);
        logger.info("\n/////////////////////////\n");
        User u1 = new User();
        logger.info(u1);
        logger.info("\n//////////////////////\n");
        u1 = (User) um.unmarshal(new FileReader("user.xml"));
        logger.info(u1);
    }
}
