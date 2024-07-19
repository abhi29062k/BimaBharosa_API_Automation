package Register;

import Base.Main;
import Constants.BimaBharosaConstants;
import Constants.XMLFileConstants;
import Operations.Register;
import Request.Request;
import Utilities.BimabharosaDatabaseHelper;
import Utilities.ErrorCodes;
import Utilities.XMLHelper;
import Utilities.XMLReader;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.dom4j.DocumentException;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import Response.RegisterResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;


public class Test_14_StateIDAndDistrictID_Negative extends Main {

    //Author : Abhishek
    @Test(priority = 23)
    public void test_Null_StateID() throws IOException, ParserConfigurationException, TransformerException, SAXException, DocumentException {

        test_name = "Negative_TC_023 : Register Complaint with Null State ID";
        test = extent.createTest(test_name);

        //Remove State ID node
        XMLHelper.removeNode(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC, "State_Id");

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setDistrictID("332");   //set district to Mumbai = 332

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);


        //validation
        RegisterResponse res = new RegisterResponse(response);
        String errorCode = res.getErrorCodes();
        if(errorCode.equals(ErrorCodes.STATE_IS_MANDATORY)){
            message = "Test Passed : State ID is Mandatory";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : State ID is Mandatory but not provided";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setStateID("21");    //set State to Maharashtra = 21

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }


    //Author : Abhishek
    @Test(priority = 24)
    public void test_Invalid_StateID() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        test_name = "Negative_TC_024 : Register Complaint with Invalid State ID";
        test = extent.createTest(test_name);

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setStateID("ABC");
        reg.setDistrictID("332");   //set district to Mumbai = 332

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        String errorCode = res.getErrorCodes();

        if(errorCode.equals(ErrorCodes.INVALID_STATE_ID)){
            message = "Test Passed : Invalid State ID is not working";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : Invalid State Id is working";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setStateID("21");    //set State to Maharashtra = 21

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }

    //Author : Abhishek
    @Test(priority = 25)
    public void test_Null_DistrictID() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        test_name = "Negative_TC_025 : Register Complaint with Null District ID";
        test = extent.createTest(test_name);

        //Generate entity reference number
        String entityRefNumber = generateEntityReferenceNo();

        //Remove District ID node
        XMLHelper.removeNode(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC, "District_Id");

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(entityRefNumber);
        reg.setStateID("21"); //set State to Maharashtra = 21

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        String errorCode = res.getErrorCodes();

        if(errorCode.equals(ErrorCodes.DISTRICT_ID_MANDATORY)){
            message = "Test Passed : District ID is Mandatory";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : District ID is Mandatory but no provided";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setDistrictID("332");   //set District to Mumbai = 332

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }


    //Author : Abhishek
    @Test(priority = 26)
    public void test_Invalid_DistrictID() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        test_name = "Negative_TC_026 : Register Complaint with Invalid District ID";
        test = extent.createTest(test_name);

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setStateID("21"); //set State to Maharashtra = 21
        reg.setDistrictID("PQR");

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        String errorCode = res.getErrorCodes();

        if(errorCode.equals(ErrorCodes.INVALID_DISTRICT_ID)){
            message = "Test Passed : Invalid District ID is not working";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : Invalid District ID is working";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setDistrictID("332");   //set District to Mumbai = 332

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }
}
