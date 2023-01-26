package com.solvd.farm.parsing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyHandlerUser extends DefaultHandler {
    private static final Logger logger = LogManager.getLogger();

    private List<User> usList = null;
    private User us = null;
    private StringBuilder data = null;

    public List<User> getEmpList() {
        return usList;
    }

    boolean isFname = false;
    boolean isLname = false;
    boolean isAge = false;
    boolean isDate = false;
    boolean isCar = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("user")) {
            String id = attributes.getValue("id");
            String date = attributes.getValue("date");
            us = new User();
            us.setId(Integer.parseInt(id));
            try {
                us.setDof(new DateAdapter().unmarshal(date));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (usList == null)
                usList = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("fname")) {
            isFname = true;
        } else if (qName.equalsIgnoreCase("lname")) {
            isLname = true;
        } else if (qName.equalsIgnoreCase("age")) {
            isAge = true;
        } else if (qName.equalsIgnoreCase("dof")) {
            isDate = true;
        } else if (qName.equalsIgnoreCase("car")) {
            isCar = true;
        } else if (qName.equalsIgnoreCase("computers")) {
            SAXParserFactory factory=SAXParserFactory.newInstance();
            MyHandlerComputer myHandlerComputer = new MyHandlerComputer();
            try {
                SAXParser saxParser = factory.newSAXParser();
                try {
                    saxParser.parse(new File("user.xml"), myHandlerComputer);
                    us.setComputers(myHandlerComputer.getCompList());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            }
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (isFname) {
            us.setFname(data.toString());
            isFname = false;
        } else if (isLname) {
            us.setLname(data.toString());
            isLname = false;
        } else if (isAge) {
            us.setAge(Integer.parseInt(data.toString()));
            isAge = false;
        } else if (isDate) {
            isDate = false;
        } else if (isCar) {
            SAXParserFactory factory=SAXParserFactory.newInstance();
            MyHandlerCar myHandlerCar = new MyHandlerCar();
            try {
                SAXParser saxParser = factory.newSAXParser();
                try {
                    saxParser.parse(new File("user.xml"), myHandlerCar);
                    us.setCar(myHandlerCar.getCar());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            }
            isCar = false;
        }

        if (qName.equalsIgnoreCase("user")) {
            usList.add(us);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
}
