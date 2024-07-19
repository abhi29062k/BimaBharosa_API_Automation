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

public class Test_23_ComplaintDescriptionTypeID_Negative extends Main {

    @Test(priority = 52)
    public void test_Complaint_Description_Type_ID_Null() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name = "Negative_TC_052 : Register Complaint with Null Description Type ID";
        test = extent.createTest(test_name);
        XMLHelper.removeNode("Register", "register_basic.xml", "Complaint_Description_Id");

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());

        //Send request
        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();

        if (errorCode.equals(ErrorCodes.COMPLAINT_DESC_TYPE_ID_MANDATORY)) {
            message = "Test Passed : Complaint description type ID is mandatory";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {

            message = "Test failed : Complaint description type ID is mandatory but not provided!";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        // ReSet the node with valid value..
        reg.setComplaintDescriptionId("1");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }

    @Test(priority = 53)
    public void test_Complaint_Description_Type_ID_Invalid() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name = "Negative_TC_053 : Register Complaint with Invalid Complaint Description Type ID";
        test = extent.createTest(test_name);

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setComplaintDescriptionId("JHK#@");

        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();
        if (errorCode.equals(ErrorCodes.INVALID_COMBINATION_OF_COMPLAINT_CLASSIFICATION_IDS)) {

            message = " Test Passed : Complaint description type ID is invalid";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {

            test.log(Status.FAIL, "Complaint description type ID is invalid but It is working");
            logger.error("Test failed : Complaint description type ID is invalid but It is working");
            status = "FAIL";
        }

        // ReSet the node with valid value..
        reg.setComplaintDescriptionId("1");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }

    @Test(priority = 54)
    public void test_Complaint_Description_Type_ID_Invalid_Numeric() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name = "Negative_TC_054 : Register Complaint with Invalid Numeric Complaint Description Type ID";
        test = extent.createTest(test_name);
        String entityNum = generateEntityReferenceNo();

        Register reg = new Register("register_basic.xml");
        reg.setComplaintDescriptionId("207");
        reg.setEntityCompRefNumber(entityNum);
        Response response = Request.register("Register", "register_basic.xml");
        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();
        if (errorCode.equals(ErrorCodes.INVALID_COMBINATION_OF_COMPLAINT_CLASSIFICATION_IDS)) {

            message = "Test Passed : Complaint description type ID is numeric invalid";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {

            message = "Test failed : Complaint description type ID is numeric invalid but It is working";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        // ReSet the node with valid value..
        reg.setComplaintDescriptionId("1");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }
}
