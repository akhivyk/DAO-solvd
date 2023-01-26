package com.solvd.farm.parsing;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;

public class MyHandlerComputer extends DefaultHandler {
    private List<Computer> compList = null;
    private Computer computer = null;
    private StringBuilder data = null;

    public List<Computer> getCompList() {
        return compList;
    }

    public Computer getComputer() {
        return computer;
    }

    boolean isRam = false;
    boolean isScreenSize = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("computer")) {
            String model = attributes.getValue("model");
            computer = new Computer();
            computer.setModel(model);
            if (compList == null)
                compList = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("RAM")) {
            isRam = true;
        } else if (qName.equalsIgnoreCase("screenSize")) {
            isScreenSize = true;
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (isRam) {
            computer.setRAM(Integer.parseInt(data.toString()));
            isRam = false;
        } else if (isScreenSize) {
            computer.setScreenSize(Double.parseDouble(data.toString()));
            isScreenSize = false;
        }

        if (qName.equalsIgnoreCase("computer")) {
            compList.add(computer);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
}
