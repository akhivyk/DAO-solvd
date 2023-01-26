package com.solvd.farm.parsing;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandlerCar extends DefaultHandler {
    private Car car = null;
    private StringBuilder data = null;
    boolean isMaxSpeed = false;
    boolean isCountPlace = false;

    public Car getCar() {
        return car;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("car")) {
            String carBrand = attributes.getValue("carBrand");
            car = new Car();
            car.setCarBrand(carBrand);
        } else if (qName.equalsIgnoreCase("maxSpeed")) {
            isMaxSpeed = true;
        } else if (qName.equalsIgnoreCase("countPlace")) {
            isCountPlace = true;
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (isMaxSpeed) {
            car.setMaxSpeed(Integer.parseInt(data.toString()));
            isMaxSpeed = false;
        } else if (isCountPlace) {
            car.setCountPlace(Integer.parseInt(data.toString()));
            isCountPlace = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }

}
