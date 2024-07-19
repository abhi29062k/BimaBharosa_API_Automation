package Request;

import Base.Main;
import Utilities.PropertyFileReader;
import Utilities.XMLReader;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Request extends Main {

    public static String request;
    public static Response response = null;
    public static String msg;
    public static String status;

    public static Response register(String operationName,String file) throws IOException {

        //Initialize URL
        Properties prop = PropertyFileReader.readPropertiesFile(System.getProperty("user.dir")+"/src/main/resources/Properties/Entity_Service.properties");
        //RestAssured.baseURI = PropertyFileReader.readPropertiesFile("Entity_svc_URL",System.getProperty("user.dir")+"/src/main/resources/Properties/Entity_Service.properties");
        RestAssured.baseURI = prop.getProperty("Entity_svc_URL");

        //read XML file as a String
        request = XMLReader.readXMLFileAsString(operationName,file);

        try{
            response = given()
                            .header("Content-Type","text/xml")
                            .body(request)
                        .when()
                            .post()
                        .then()
                            .statusCode(200).extract().response();
        }
        catch(AssertionError e){
            msg="Status code is other than 200";
            test.log(Status.FAIL, msg);
            logger.error(msg);
            status="Fail";
            response = null;
        }


        return response;
    }


    public static Response update(String operationName,String file) throws IOException {

        //Initialize URL
        Properties prop = PropertyFileReader.readPropertiesFile(System.getProperty("user.dir")+"/src/main/resources/Properties/Entity_Service.properties");
        //RestAssured.baseURI = PropertyFileReader.readPropertiesFile("Entity_svc_URL",System.getProperty("user.dir")+"/src/main/resources/Properties/Entity_Service.properties");
        RestAssured.baseURI = prop.getProperty("Entity_svc_URL");
        //read XML file as a String
        request = XMLReader.readXMLFileAsString(operationName,file);

        try{
            response = given()
                    .header("Content-Type","text/xml")
                    .body(request)
                    .when()
                    .post()
                    .then()
                    .statusCode(200).log().all().extract().response();
        }
        catch(AssertionError | NullPointerException e){
            msg="Status code is other than 200";
            test.log(Status.FAIL, msg);
            logger.error(msg);
            status="Fail";
            //response = null;
        }

        return response;
    }



    public static List<String> getTestDetail(){
        List<String> lst = new ArrayList<>();
        lst.add(status);
        lst.add(msg);

        return lst;
    }

    public static String getRequestBody(){
        return request;
    }

    public static String getResponseBody(){
        String res = response.getBody().prettyPrint().toString();
        return res;
    }


}
