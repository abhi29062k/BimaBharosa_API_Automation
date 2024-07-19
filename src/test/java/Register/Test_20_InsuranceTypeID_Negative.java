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
import java.util.Random;

import Response.RegisterResponse;
import io.restassured.response.Response;

public class Test_20_InsuranceTypeID_Negative extends Main {

    @Test(priority = 43)
    public void test_Insurance_Type_ID_Null() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name = "Negative_TC_043 : Register Complaint with Null Insurance Type ID";
        test = extent.createTest(test_name);

        XMLHelper.removeNode("Register", "register_basic.xml", "Insurance_Type_Id");

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());

        //Send request
        Response response = Request.register("Register", "register_basic.xml");

        // Parsing response
        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();

        if (errorCode.equals(ErrorCodes.INSURANCE_TYPE_ID_MANDATORY)) {
            message = "Test Passed : Insurer type ID is mandatory";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {
            message = "Test failed : Insurer type ID is mandatory but not provided!";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        // ReSet the node with valid value..
        reg.setInsuranceTypeId("1");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }

    @Test(priority = 44)
    public void test_Insurance_Type_ID_Invalid() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name = "Negative_TC_044 : Register Complaint with Invalid Insurance Type ID";
        test = extent.createTest(test_name);

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setInsuranceTypeId("@RT");

        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();
        if (errorCode.equals(ErrorCodes.INVALID_INSURANCE_TYPE_ID)) {

            message = " Test Passed : Insurer type ID is invalid";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {

            message = "Test failed : Insurer type ID is invalid but It is working";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        // ReSet the node with valid value..
        reg.setInsuranceTypeId("1");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }

    @Test(priority = 45)
    public void test_Insurance_Type_ID_Invalid_Numeric() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name = "Negative_TC_045 : Register Complaint with Invalid Numeric Insurance Type ID";
        test = extent.createTest(test_name);

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setInsuranceTypeId("3");

        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();
        if (errorCode.contains(ErrorCodes.INSURANCE_TYPE_ID_NOT_MATCHING_WITH_TRANSACTION_USER) && errorCode.contains(ErrorCodes.INVALID_COMBINATION_OF_COMPLAINT_CLASSIFICATION_IDS)) {
            message = " Test Passed : Insurer type ID is numeric invalid";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {

            message = "Test failed : Insurer type ID is numeric invalid but It is working";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        // ReSet the node with valid value..
        reg.setInsuranceTypeId("1");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }


}
