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

public class Test_21_PolicyTypeID_Negative extends Main {

    @Test(priority = 46)
    public void test_Policy_Type_ID_Null() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name = "Negative_TC_046 : Register Complaint with Null Policy Type ID";
        test = extent.createTest(test_name);
        XMLHelper.removeNode("Register", "register_basic.xml", "Policy_Type_Id");

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());

        //Send request
        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();

        if (errorCode.equals(ErrorCodes.POLICY_TYPE_ID_MANDATORY)) {
            message = "Test Passed : Policy type ID is mandatory";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {
            message = "Test failed : Policy type ID is mandatory but not provided!";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        // ReSet the node with valid value..
        reg.setPolicyTypeId("1");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }

    @Test(priority = 47)
    public void test_Policy_Type_ID_Invalid() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name = "Negative_TC_047 : Register Complaint with Invalid Policy Type ID";
        test = extent.createTest(test_name);

        String entityNum = generateEntityReferenceNo();
        Register reg = new Register("register_basic.xml");
        reg.setPolicyTypeId("#$EY");
        reg.setEntityCompRefNumber(entityNum);
        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();
        if (errorCode.equals(ErrorCodes.INVALID_POLICY_TYPE_ID)) {

            message = " Test Passed : Policy type ID is invalid";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {

            message = "Test failed : Policy type ID is invalid but It is working";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        // ReSet the node with valid value..
        reg.setPolicyTypeId("1");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }

    @Test(priority = 48)
    public void test_Policy_Type_ID_Invalid_Numeric() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name = "Negative_TC_048 : Register Complaint with Invalid Numeric Policy Type ID";
        test = extent.createTest(test_name);

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setPolicyTypeId("15");
        Response response = Request.register("Register", "register_basic.xml");
        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();
        if (errorCode.equals(ErrorCodes.INVALID_COMBINATION_OF_COMPLAINT_CLASSIFICATION_IDS)) {

            message = " Test Passed : Policy type ID is numeric invalid";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {

            message = "Test failed : Policy type ID is numeric invalid but It is working";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        // ReSet the node with valid value..
        reg.setPolicyTypeId("1");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }

}
