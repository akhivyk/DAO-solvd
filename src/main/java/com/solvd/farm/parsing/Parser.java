package com.solvd.farm.parsing;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Parser {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws JAXBException, FileNotFoundException, ParserConfigurationException, SAXException {
        int chooseAction = 0;
        Scanner in = new Scanner(System.in);
        while (chooseAction != 5) {
            logger.info("""
                    Choose action:
                    1 - Parse xml file with JAXB
                    2 - Parse xml file with DOM
                    3 - Parse xml file with SAX
                    4 - Exit""");
            chooseAction = Integer.parseInt(in.nextLine());
            switch (chooseAction) {
                case 1 -> {
                    JAXBContext c = JAXBContext.newInstance(User.class);
                    Unmarshaller um = c.createUnmarshaller();
                    User u1;
                    u1 = (User) um.unmarshal(new FileReader("user.xml"));
                    logger.info(u1);
                }
                case 2 -> {
                    SAXParserFactory factory = SAXParserFactory.newInstance();
                    SAXParser saxParser = factory.newSAXParser();
                    MyHandlerUser myHandlerUser = new MyHandlerUser();

                    try {
                        saxParser.parse(new File("user.xml"), myHandlerUser);
                        myHandlerUser.getEmpList().forEach(logger::info);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 3 -> {
                    DOMParser domParser = new DOMParser();
                    try {
                        User u = new User();
                        logger.info(domParser.parseUser("user.xml", u));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case 4 -> {
                    logger.info("Bye - bye..");
                    chooseAction = 5;
                }
                default -> logger.info("Choose another action.");
            }
        }
    }
}
