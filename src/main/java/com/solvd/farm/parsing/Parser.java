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
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Parser {
    private static final Logger logger = LogManager.getLogger();

    public static <T> void main(String[] args) throws JAXBException, IOException, ParserConfigurationException, SAXException {
        int chooseAction = 0;
        Scanner in = new Scanner(System.in);
        while (chooseAction != 6) {
            logger.info("""
                    Choose action:
                    1 - Parse xml file with JAXB
                    2 - Parse xml file with DOM
                    3 - Parse xml file with SAX
                    4 - Parse json file with Jackson
                    5 - Exit""");
            chooseAction = Integer.parseInt(in.nextLine());
            switch (chooseAction) {
                case 1 -> {
                    logger.info("all users from xml by JAXB");
                    JAXBContext c = JAXBContext.newInstance(User.class);
                    Unmarshaller um = c.createUnmarshaller();
                    User u1;
                    u1 = (User) um.unmarshal(new FileReader("user.xml"));
                    logger.info(u1);
                }
                case 2 -> {
                    logger.info("all users from xml by SAX");
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
                    logger.info("all users from xml by DOM");
                    DOMParser domParser = new DOMParser();
                    try {
                        User u = new User();
                        logger.info(domParser.parseUser("user.xml", u));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case 4 -> {
                    JacksonParser jacksonParser = new JacksonParser();
                    logger.info("all users from json");
                    jacksonParser.readEntityFromFile("user.json", User.class).forEach(logger::info);
                }
                case 5 -> {
                    logger.info("Bye - bye..");
                    chooseAction = 6;
                }
                default -> logger.info("Choose another action.");
            }
        }
    }
}
