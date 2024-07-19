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
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import Response.RegisterResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Random;

public class Test_17_MobileNumber_Negative extends Main {

    //Author : Abhishek
    @Test(priority = 33)
    public void test_Null_MobileNumber_Field() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        test_name="Negative_TC_033 : Register Complaint with Null Mobile Number field";
        test = extent.createTest(test_name);

        //Remove Mobile Number node
        XMLHelper.removeNode(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC, "Mobile_Number");

        //Generate entity reference number
        String entityRefNumber = generateEntityReferenceNo();

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(entityRefNumber);

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        String errorCode = res.getErrorCodes();

        if(errorCode.equals(ErrorCodes.ATLEAST_ONE_OF_THE_IDENTIFICATION_NUM_IS_MANDATORY)){
            message="Test Passed : Atleast one of the identification number is mandatory";
            test.log(Status.PASS, message);
            logger.info(message);
            status="PASS";
        }
        else{
            message="Test Failed : Without identification number it is working";
            test.log(Status.FAIL,message );
            logger.error(message);
            status="FAIL";
        }

        //Reset xml node with valid value
        reg.setMobileNumber("9967465389");

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }



    //Author : Abhishek
    @Test(priority = 34)
    public void test_lessThen_10_Digit_MobileNumber() throws IOException, TransformerException {

        test_name="Negative_TC_034 : Register Complaint with less than 10 digit Mobile Number";
        test = extent.createTest(test_name);

        //Generate entity reference number
        String entityRefNumber = generateEntityReferenceNo();

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(entityRefNumber);
        reg.setMobileNumber("996746");   //less than 10 digit

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        String errorCode = res.getErrorCodes();

        if (errorCode.equals(ErrorCodes.MOBILE_NUM_SHOULD_HAVE_10_DIGITS)) {
            message="Test Passed : Mobile number should have 10 digits";
            test.log(Status.PASS,message);
            logger.info(message);
            status="PASS";
        }
        else{
            message="Test Failed : Mobile number should have 10 digits";
            test.log(Status.FAIL,message);
            logger.error(message);
            status="FAIL";
        }

        //Reset xml node with valid value
        reg.setMobileNumber("9967465389");

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }


    //Author : Abhishek
    @Test(priority = 35)
    public void test_moreThen_10_Digit_MobileNumber() throws IOException, TransformerException {

        test_name="Negative_TC_035 : Register Complaint with more than 10 digit Mobile Number";
        test = extent.createTest(test_name);

        //Generate entity reference number
        String entityRefNumber = generateEntityReferenceNo();

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(entityRefNumber);
        reg.setMobileNumber("9967465366788");   //more than 10 digit

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        String errorCode = res.getErrorCodes();

        if (errorCode.equals(ErrorCodes.MOBILE_NUM_SHOULD_HAVE_10_DIGITS)) {
            message="Test Passed : Mobile number should have 10 digits";
            test.log(Status.PASS,message);
            logger.info(message);
            status="PASS";
        }
        else{
            message="Test Failed : Mobile number should have 10 digits";
            test.log(Status.FAIL,message);
            logger.error(message);
            status="FAIL";
        }

        //Reset xml node with valid value
        reg.setMobileNumber("9967465389");

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }

    //Author : Abhishek
    @Test(priority = 36)
    public void test_MobileNumber_As_AlphabaticCharacter() throws IOException, TransformerException {

        test_name="Negative_TC_036 : Register Complaint with Mobile Number as Alphabatic character";
        test = extent.createTest(test_name);

        //Generate entity reference number
        String entityRefNumber = generateEntityReferenceNo();

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(entityRefNumber);
        reg.setMobileNumber("AJHTYREUIW");   //Mobile number as Alphabatic character

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        String errorCode = res.getErrorCodes();

        if (errorCode.equals(ErrorCodes.MOBILE_NUM_SHOULD_VALID_NUMERIC_VALUE)) {
            message="Test Passed : Mobile number should be a numeric value";
            test.log(Status.PASS,message);
            logger.info(message);
            status="PASS";
        }
        else{
            message="Test Failed : Alphbatical character as a Mobile number working";
            test.log(Status.FAIL,message);
            logger.error(message);
            status="FAIL";
        }

        //Reset xml node with valid value
        reg.setMobileNumber("9967465389");

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }


    //Author : Abhishek
    @Test(priority = 37)
    public void test_MobileNumber_As_SpecialCharacter() throws IOException, TransformerException {

        test_name="Negative_TC_037 : Register Complaint with Mobile Number as Special Character";
        test = extent.createTest(test_name);

        //Generate entity reference number
        String entityRefNumber = generateEntityReferenceNo();

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(entityRefNumber);
        reg.setMobileNumber("@@@@@$$$$$");   //Mobile number as Special character

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        String errorCode = res.getErrorCodes();

        if(errorCode.equals(ErrorCodes.MOBILE_NUM_SHOULD_VALID_NUMERIC_VALUE)){
            message="Test Passed : Mobile number should be a numeric value";
            test.log(Status.PASS, message);
            logger.info(message);
            status="PASS";
        }
        else{
            message="Test Failed : Special character as a mobile number working";
            test.log(Status.FAIL,message);
            logger.error(message);
            status="FAIL";
        }

        //Reset xml node with valid value
        reg.setMobileNumber("9967465389");

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }
}
