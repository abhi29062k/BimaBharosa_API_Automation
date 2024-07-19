package Register;

import Base.Main;
import Constants.BimaBharosaConstants;
import Operations.Register;
import Request.Request;
import Utilities.BimabharosaDatabaseHelper;
import Utilities.ErrorCodes;
import Utilities.XMLHelper;
import Utilities.XMLReader;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

import Response.RegisterResponse;

public class Test_25_PolicyNumber_Negative extends Main {

    @Test(priority = 59)
    public void test_Policy_Number_Null() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name = "Negative_TC_059 : Register Complaint with Null Policy Number";
        test = extent.createTest(test_name);
        XMLHelper.removeNode("Register", "register_basic.xml", "Policy_Number");

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());

        //Send request
        Response response = Request.register("Register", "register_basic.xml");
        RegisterResponse res = new RegisterResponse(response);
        String errorCode = res.getErrorCodes();

        if (errorCode.equals(ErrorCodes.POLICY_NUM_MANDATORY)) {
            message = "Test Passed : Policy_Number is mandatory";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {
            message = "Test failed : Policy_Number is mandatory but not provided!";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "message";
        }

        // ReSet the node with valid value..
        reg.setPolicyNumber("123456789");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }


    @Test(priority = 60)
    public void test_Policy_Number_Exceeding_length() throws TransformerException, IOException, ParserConfigurationException, SAXException {

        test_name = "Negative_TC_060 : Register Complaint with Policy Number exceeding length (Defect)";
        test = extent.createTest(test_name);
        String entityRefNumber = generateEntityReferenceNo();

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(entityRefNumber);
        reg.setPolicyNumber("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaawwww");

        //Send request
        Response response = Request.register("Register", "register_basic.xml");
        RegisterResponse res = new RegisterResponse(response);


        if (!res.getErrorCodes().isBlank()) {
            message = "Test Passed : Policy number exceeding length is Working";
            test.log(Status.PASS, message);
            logger.info(message);
            message = "PASS";
        } else {
            message = "Test failed : Policy number exceeding length is not Working";
            test.log(Status.FAIL, message);
            logger.error(message);
            message = "FAIL";
        }

        // ReSet the node with valid value..
        reg.setPolicyNumber("123456789");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }


    @Test(priority = 61)
    public void test_Policy_Number_Duplicate() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name = "Negative_TC_061 : Register Complaint with Duplicate Policy Number";
        test = extent.createTest(test_name);

        //Generate the entity reference number...
        String entityNumber = generateEntityReferenceNo();

        Register reg = new Register("register_basic.xml");
        reg.setPolicyNumber("123456789");
        reg.setEntityCompRefNumber(entityNumber);

        //Send request
        Response response = Request.register("Register", "register_basic.xml");
        RegisterResponse res = new RegisterResponse(response);

        if (res.getEntityCompRefNumber().equals(entityNumber) && res.getErrorCodes().isBlank()) {
            message = "Test Passed : Duplicate Policy Number is accepting";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {
            message = "Test failed : Duplicate Policy Number is not accepting";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        // ReSet the node with valid value..
        reg.setPolicyNumber("123456789");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }

}
