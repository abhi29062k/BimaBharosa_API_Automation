package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {

    /*public static String readPropertiesFile(String key, String filePath) throws IOException {

        String url;
        Properties properties = null;
        try{
            properties = new Properties();
            FileInputStream fis = new FileInputStream(filePath);
            properties.load(fis);
        }
        catch (FileNotFoundException e){

        }

        url = properties.getProperty("Entity_svc_URL");
        return  url;
    }*/

    public static Properties readPropertiesFile(String filePath) throws IOException {

        Properties properties = null;
        try{
            properties = new Properties();
            FileInputStream fis = new FileInputStream(filePath);
            properties.load(fis);
        }
        catch (FileNotFoundException e){

        }

       return properties;
    }
}
