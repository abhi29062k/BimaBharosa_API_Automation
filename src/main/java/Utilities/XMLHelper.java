package Utilities;



import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.IOException;

public class XMLHelper {

    //static File xmlFile;
    static String xmlFile;
    static Document doc;

    //Modified by Abhishek
    public static void loadXMLFile(String operation, String xmlFile) throws ParserConfigurationException, IOException, SAXException {
        XMLHelper.xmlFile = xmlFile;
        // Load the XML file
        //xmlFile = new File(System.getProperty("user.dir") + "/src/main/resources/XML/" + filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(System.getProperty("user.dir") + "/src/main/resources/XML/"+operation+"/" + XMLHelper.xmlFile);
    }


    public static void addNode(Document doc, String node) throws IOException, TransformerException {

        Element newElement = doc.createElement(node);

        // Get the parent node where you want to insert the new node
        Element parentNode = (Element) doc.getElementsByTagName("Complaint").item(0);
        parentNode.appendChild(newElement);

        //System.out.println("Node added successfully.");
    }

    //Modified by Abhishek
    public static void removeNode(String operation, String xmlFile, String node) throws IOException, TransformerException, ParserConfigurationException, SAXException {
        try {
            //Load XML file
            loadXMLFile(operation, xmlFile);

            // Get node to remove
            Node nodeToRemove = doc.getElementsByTagName(node).item(0);

            // Get the parent node of the node to remove
            Node parentNode = nodeToRemove.getParentNode();

            // Remove the node from its parent
            parentNode.removeChild(nodeToRemove);

            saveXMLFile(operation);

            //System.out.println("Node removed successfully");
        } catch (NullPointerException e) {
            //System.out.println("Node Already removed in xml file");
        }

    }

    //Abhishek modfied method in XMLHelper class
    public static void saveXMLFile(String operation) throws IOException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(System.getProperty("user.dir") + "/src/main/resources/XML/"+operation+"/" + XMLHelper.xmlFile);
        transformer.transform(source, result);
        System.out.println("Node removed successfully");
    }


}
