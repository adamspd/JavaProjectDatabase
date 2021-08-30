/*package data;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ModifyXML {
    public static void main (String[] args) {
        String filepath = "C:\\Users\\Adams Pierre David\\IdeaProjects\\Java2020Projet\\src\\data.xml";
        File xmlFile = new File(filepath);
        DocumentBuilderFactory dbFactory = new DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();


        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
            e1.printStackTrace();
        }

    }

    public void addCourse(Document doc) {
        NodeList course = doc.getElementsByTagName("data");
        Element emp = null;

        emp = (Element) course.item(0);
        Element cours = doc.createElement("Course");
        cours.appendChild(doc.createTextNode("Adams"));
        emp.appendChild(cours);

    }
}*/
