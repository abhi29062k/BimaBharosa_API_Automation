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

public class Test_29_Mode_Negative extends Main {

    @Test(priority = 74)
    public void test_Mode_Null() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name="Negative_TC_074 : Register Complaint with Null Mode field";
        test = extent.createTest(test_name);
        XMLHelper.removeNode("Register","register_basic.xml", "Mode");

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());

        //Send request
        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();

        if (errorCode.equals(ErrorCodes.MODE_IS_MANDATORY)) {
            message="Test Passed : Mode is mandatory";
            test.log(Status.PASS,message);
            logger.info(message);
            status="PASS";
        } else {

            message="Test failed : Mode is mandatory but not provided!";
            test.log(Status.FAIL,message);
            logger.error(message);
            status="FAIL";
        }

        // ReSet the node with valid value..
        reg.setMode("1");
        XMLReader.formatXMLFile("Register","register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }

    @Test(priority = 75)
    public void test_Mode_Invalid() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name="Negative_TC_075 : Register Complaint with Invalid Mode field";
        test = extent.createTest(test_name);

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setMode("2");

        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();
        if (errorCode.equals(ErrorCodes.INVALID_MODE_IT_SHOULD_1_FOR_INSERTION)) {

            message="Test Passed : Mode is invalid";
            test.log(Status.PASS,message);
            logger.info(message);
            status="PASS";
        } else {
            message="Test failed : Mode is invalid but It is working";

            test.log(Status.FAIL,message);
            logger.error(message);
            status="FAIL";
        }

        // ReSet the node with valid value..
        reg.setMode("1");
        XMLReader.formatXMLFile("Register","register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }

    @Test(priority = 76)
    public void test_Mode_Alphabetical() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name="Negative_TC_076 : Register Complaint with Mode field as Alphabatic Character";
        test = extent.createTest(test_name);

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setMode("C");

        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();
        if (errorCode.equals(ErrorCodes.MODE_SHOULD_VALID_NUMERIC_VALUE)) {

            message="Test Passed : Mode is Alphabetical invalid";
            test.log(Status.PASS,message);
            logger.info(message);
            status="PASS";
        } else {

            message="Test failed : Mode is Alphabetical invalid but It is working";
            test.log(Status.FAIL,message);
            logger.error(message);
            status="FAIL";
        }

        // ReSet the node with valid value..
        reg.setMode("1");
        XMLReader.formatXMLFile("Register","register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }

    @Test(priority = 77)
    public void test_Mode_SpecialChars() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name="Negative_TC_077 : Register Complaint with Mode field as Special Character";
        test = extent.createTest(test_name);

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setMode("@");

        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();
        if (errorCode.equals(ErrorCodes.MODE_SHOULD_VALID_NUMERIC_VALUE)) {

            message="Test Passed : Mode is SpecialChars invalid";
            test.log(Status.PASS,message);
            logger.info(message);
            status="PASS";
        } else {

            message="Test failed : Mode is SpecialChars invalid but It is working";
            test.log(Status.FAIL,message);
            logger.error(message);
            status="FAIL";
        }

        // ReSet the node with valid value..
        reg.setMode("1");
        XMLReader.formatXMLFile("Register","register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }


}
