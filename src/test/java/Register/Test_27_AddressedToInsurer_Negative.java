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

public class Test_27_AddressedToInsurer_Negative extends Main {

    @Test(priority = 66)
    public void test_Addressed_To_Insurer_Null() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name="Negative_TC_066 : Register Complaint with Null Addressed to Insurer";
        test = extent.createTest(test_name);
        XMLHelper.removeNode("Register", "register_basic.xml", "Addressed_To_Insurer");

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());

        //Send request
        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();

        if (errorCode.equals(ErrorCodes.ADDRESS_TO_INSURER_MANDATORY)) {
            message="Test Passed : Addressed To Insurer is mandatory";
            test.log(Status.PASS,message);
            logger.info(message);
            status="PASS";
        } else {

            message="Test failed : Addressed To Insurer is mandatory but not provided!";
            test.log(Status.FAIL,message);
            logger.error(message);
            status="FAIL";
        }

        // ReSet the node with valid value..
        reg.setAddressedToInsurer("Y");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }

    @Test(priority = 67)
    public void test_Addressed_To_Insurer_With_N() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name="Negative_TC_067 : Register Complaint with Addressed To Insurer N";
        test = extent.createTest(test_name);

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setAddressedToInsurer("N");

        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();
        if (errorCode.equals(ErrorCodes.ADDRESS_TO_INSURER_SHOULD_Y_AS_THE_COMPLAINT_AGAINST_INSURER)) {

            message=" Test Passed : Addressed to insurer invalid with N";
            test.log(Status.PASS, message);
            logger.info(message);
            status="PASS";
        } else {

            message="Test failed : Addressed to insurer invalid with N but It is working";
            test.log(Status.FAIL, message);
            logger.error(message);
            status="FAIL";
        }

        // ReSet the node with valid value..
        reg.setAddressedToInsurer("Y");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }


    @Test(priority = 68)
    public void test_Addressed_To_Insurer_Numeric() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name="Negative_TC_068 : Register Complaint with Addressed To Insurer as Numeric";
        test = extent.createTest(test_name);

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setAddressedToInsurer("1");

        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();
        if (errorCode.equals(ErrorCodes.ADDRESS_TO_INSURER_EITHER_Y_OR_N)) {

            message="Test Passed : Addressed to insurer numeric invalid";
            test.log(Status.PASS, message);
            logger.info(message);
            status="PASS";
        } else {

            message="Test failed : Addressed to insurer numeric invalid but is working";
            test.log(Status.FAIL, message);
            logger.error(message);
            status="FAIL";
        }

        // ReSet the node with valid value..
        reg.setAddressedToInsurer("Y");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }

    @Test(priority = 69)
    public void test_Addressed_To_Insurer_SpecialChars() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name ="Negative_TC_069 : Register Complaint with Addressed To Insurer as Special Character";
        test = extent.createTest(test_name);

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setAddressedToInsurer("@");

        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();
        if (errorCode.equals(ErrorCodes.ADDRESS_TO_INSURER_EITHER_Y_OR_N)) {

            message="Test Passed : Addressed to insurer SpecialChars invalid";
            test.log(Status.PASS,message);
            logger.info(message);
            status="PASS";
        } else {

            message="Test failed : Addressed to insurer SpecialChars invalid but It is working";
            test.log(Status.FAIL, "Addressed to insurer SpecialChars invalid but It is working");
            logger.error(message);
            status="FAIL";
        }

        // ReSet the node with valid value..
        reg.setAddressedToInsurer("Y");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }

    @Test(priority = 70)
    public void test_Addressed_To_Insurer_Invalid() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name="Negative_TC_070 : Register Complaint with Invalid Addressed To Insurer";
        test = extent.createTest(test_name);

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setAddressedToInsurer("F");

        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();
        if (errorCode.equals(ErrorCodes.ADDRESS_TO_INSURER_EITHER_Y_OR_N)) {

            message="Test Passed : Addressed to insurer invalid";
            test.log(Status.PASS, message);
            logger.info(message);
            status="PASS";
        } else {

            message="Test failed : Addressed to insurer invalid but It is working";
            test.log(Status.FAIL, message);
            logger.error(message);
            status="FAIL";
        }

        // ReSet the node with valid value..
        reg.setAddressedToInsurer("Y");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }
}
