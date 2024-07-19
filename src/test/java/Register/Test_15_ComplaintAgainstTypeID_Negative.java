package Register;

import Base.Main;
import Constants.BimaBharosaConstants;
import Constants.ComplaintAgainstTypeConstants;
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

public class Test_15_ComplaintAgainstTypeID_Negative extends Main {

    //Author : Abhishek
    @Test(priority = 27)
    public void test_Null_ComplaintAgainstTypeID() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        test_name = "Negative_TC_027 : Register Complaint with Null Complaint Against Type ID";
        test = extent.createTest(test_name);

        //Remove node Complaint Against Type ID
        XMLHelper.removeNode(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC, "Complaint_Against_Type_Id");

        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(generateEntityReferenceNo());

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        String errorCode = res.getErrorCodes();

        if(errorCode.equals(ErrorCodes.COMPLAINT_AGAINST_TYPE_ID_MANDATORY)){
            message = "Test Passed : Complaint Against Type Id is Mandatory";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        }
        else {
            message = "Test Failed : Complaint Against Type ID is Mandatory but not provided";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setComplaintAgainstTypeId(ComplaintAgainstTypeConstants.INSURER);

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }

    //Author : Abhishek
    @Test(priority = 28)
    public void test_Alphabatical_ComplaintAgainstTypeID() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        test_name = "Negative_TC_028 : Register Complaint with Complaint Against Type ID as Alphabatic character";
        test = extent.createTest(test_name);

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setComplaintAgainstTypeId("A");

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        String errorCode = res.getErrorCodes();

        if(errorCode.equals(ErrorCodes.COMPLAINT_AGAINST_TYPE_ID_SHOULD_VALID_NUMERIC_VALUE)){
            message = "Test Passed : Complaint Against Type ID should be valid numeric value";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : Complaint Against Type ID should be valid numeric value";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setComplaintAgainstTypeId(ComplaintAgainstTypeConstants.INSURER);

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }


    //Author : Abhishek
    @Test(priority = 29)
    public void test_SpecialCharacter_ComplaintAgainstTypeID() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        test_name = "Negative_TC_029 : Register Complaint with Complaint Against Type ID as Special Character";
        test = extent.createTest(test_name);

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setComplaintAgainstTypeId("@");

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        String errorCode = res.getErrorCodes();

        if(errorCode.equals(ErrorCodes.COMPLAINT_AGAINST_TYPE_ID_SHOULD_VALID_NUMERIC_VALUE)){
            message = "Test Passed : Complaint Against Type ID should be valid numeric value";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : Complaint Against Type ID should be valid numeric value";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setComplaintAgainstTypeId(ComplaintAgainstTypeConstants.INSURER);

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }


    //Author : Abhishek
    @Test(priority = 30)
    public void test_Invalid_ComplaintAgainstTypeID() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        test_name = "Negative_TC_030 : Register Complaint with Invalid Complaint Against Type ID";
        test = extent.createTest(test_name);

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setComplaintAgainstTypeId("15");

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        String errorCode = res.getErrorCodes();

        if(errorCode.equals(ErrorCodes.INVALID_COMPLAINT_AGAINST_TYPE_ID)){
            message = "Test Passed : Invalid Complaint Against Type ID is not working";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : Invalid Complaint Against Type ID is working";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setComplaintAgainstTypeId(ComplaintAgainstTypeConstants.INSURER);

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }
}
