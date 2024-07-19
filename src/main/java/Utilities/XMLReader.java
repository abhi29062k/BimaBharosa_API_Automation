package Utilities;



import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.dom4j.Document;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XMLReader {

    public static String readXMLFileAsString(String operationName, String xmlFile) {
        String filePath = System.getProperty("user.dir") + "/src/main/resources/XML/" + operationName + "/" + xmlFile;
        String content = "";
        try {
            // Read all bytes from the XML file
            byte[] encoded = Files.readAllBytes(Paths.get(filePath));
            // Convert bytes to string using UTF-8 encoding
            content = new String(encoded, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }


    //Added method by Abhishek
    //Format xml file
    public static void formatXMLFile(String operationName, String xmlFile){
        try {

            String inputXml = XMLReader.readXMLFileAsString(operationName,xmlFile);

            // Parse the input XML string
            Document document = DocumentHelper.parseText(inputXml);

            // Create an output format with indentation
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setIndentSize(4); // Set the indentation size

            // Write the formatted XML to a file
            try (FileWriter writer = new FileWriter(System.getProperty("user.dir") + "/src/main/resources/XML/"+operationName+"/"+xmlFile)) {
                XMLWriter xmlWriter = new XMLWriter(writer, format);
                xmlWriter.write(document);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}







