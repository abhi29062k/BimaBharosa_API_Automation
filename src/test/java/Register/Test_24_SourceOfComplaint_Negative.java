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

import javax.crypto.Mac;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

import Response.RegisterResponse;

public class Test_24_SourceOfComplaint_Negative extends Main {

    @Test(priority = 55)
    public void test_Source_Of_Complaint_Null() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name = "Negative_TC_055 : Register Complaint with Null Source of Complaint";
        test = extent.createTest(test_name);
        XMLHelper.removeNode("Register", "register_basic.xml", "Source_Of_Complaint");

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());

        //Send request
        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();

        if (errorCode.equals(ErrorCodes.SOURCE_OF_COMPLAINT_MANDATORY)) {
            message = "Test Passed : Source of Complaint is mandatory";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {

            message = "Test failed : Source of Complaint is mandatory but not provided!";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        // ReSet the node with valid value..
        reg.setSourceOfComplaint("1");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }

    @Test(priority = 56)
    public void test_Source_Of_Complaint_Alphabetical() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name = "Negative_TC_056 : Register Complaint with Source of Complaint as Alphabatic Character";
        test = extent.createTest(test_name);
        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setSourceOfComplaint("A");

        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();

        if (errorCode.equals(ErrorCodes.SOURCE_OF_COMPLAINT_SHOULD_VALID_NUMERIC_VALUE)) {
            message = "Test Passed : Source of Complaint is invalid Alphabetical";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {

            test.log(Status.FAIL, "Source of Complaint is invalid Alphabetical but It is working");
            logger.error("Test failed : Source of Complaint is invalid Alphabetical but It is working");
            status = "FAIL";
        }

        // ReSet the node with valid value..
        reg.setSourceOfComplaint("1");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }

    @Test(priority = 57)
    public void test_Source_Of_Complaint_SpecialChars() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name = "Negative_TC_057 : Register Complaint with Source of Complaint as Special Character";
        test = extent.createTest(test_name);
        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setSourceOfComplaint("$");

        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();

        if (errorCode.equals(ErrorCodes.SOURCE_OF_COMPLAINT_SHOULD_VALID_NUMERIC_VALUE)) {
            message = "Test Passed : Source of Complaint is invalid SpecialChars";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {

            message = "Test failed : Source of Complaint is invalid SpecialChars but It is working";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        // ReSet the node with valid value..
        reg.setSourceOfComplaint("1");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }

    @Test(priority = 58)
    public void test_Source_Of_Complaint_Invalid() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name = "Negative_TC_058 : Register Complaint with Invalid Source of Complaint";
        test = extent.createTest(test_name);
        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setSourceOfComplaint("99");

        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();

        if (errorCode.equals(ErrorCodes.SOURCE_OF_COMPLAINT_INVALID)) {
            message = "Test Passed : Source of Complaint is invalid";
            test.log(Status.PASS, "Source of Complaint is invalid");
            logger.info(message);
            message = "PASS";
        } else {

            message = "Test failed : Source of Complaint is invalid but It is working";
            test.log(Status.FAIL, message);
            logger.error(message);
            message = "FAIL";
        }

        // ReSet the node with valid value..
        reg.setSourceOfComplaint("1");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }


}
