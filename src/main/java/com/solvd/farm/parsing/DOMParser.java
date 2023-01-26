package com.solvd.farm.parsing;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMParser {
    public User parseUser(String fileName, User user) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(fileName));
            doc.getDocumentElement().normalize();
            NodeList list = doc.getElementsByTagName("user");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    user.setId(Long.parseLong(element.getAttribute("id")));
                    user.setFname(element.getElementsByTagName("fname").item(0).getTextContent());
                    user.setLname(element.getElementsByTagName("lname").item(0).getTextContent());
                    user.setAge(Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent()));

                    user.setDof(new DateAdapter().unmarshal(element.getAttribute("date")));

                    NodeList carNodeList = element.getElementsByTagName("car");
                    Car c = new Car();
                    for (int i = 0; i < carNodeList.getLength(); i++) {
                        Node node2 = carNodeList.item(i);
                        if (node2.getNodeType() == node2.ELEMENT_NODE) {
                            Element el = (Element) node2;
                            c.setCarBrand(el.getAttribute("carBrand"));
                            c.setMaxSpeed(Integer.parseInt(el.getElementsByTagName("maxSpeed").item(0).getTextContent()));
                            c.setCountPlace(Integer.parseInt(el.getElementsByTagName("countPlace").item(0).getTextContent()));
                        }
                    }

                    user.setCar(c);

                    NodeList computerNodeList = element.getElementsByTagName("computer");
                    List<Computer> compList = new ArrayList<>();
                    for (int i = 0; i < computerNodeList.getLength(); i++) {
                        Node node3 = computerNodeList.item(i);
                        if (node3.getNodeType() == node3.ELEMENT_NODE) {
                            Element elem = (Element) node3;

                            String model = elem.getAttribute("model");
                            int RAM = Integer.parseInt(elem.getElementsByTagName("RAM").item(0).getTextContent());
                            double screenSize = Double.parseDouble(elem.getElementsByTagName("screenSize").item(0).getTextContent());
                            compList.add(new Computer(model, RAM, screenSize));
                        }
                    }

                    user.setComputers(compList);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return user;
    }
}
