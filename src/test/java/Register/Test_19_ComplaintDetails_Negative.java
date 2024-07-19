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

public class Test_19_ComplaintDetails_Negative extends Main {


    //Author : Abhishek
    @Test(priority = 40)
    public void test_Null_ComplaintDetails() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        test_name = "Negative_TC_040 : Register Complaint with Null Complaint Details";
        test = extent.createTest(test_name);

        //Remove ComplaintDetails node
        XMLHelper.removeNode(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC, "Complaint_Details");

        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(generateEntityReferenceNo());

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        String errorCode = res.getErrorCodes();

        if (errorCode.equals(ErrorCodes.COMPLAINT_DETAILS_MANDATORY)) {
            message = "Test Passed : Complaint details are mandatory";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {
            message = "Test Failed : Complaint details are mandatory but not provided";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setComplaintDetails("Registration of complaint");

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }


    //Author : Abhishek
    @Test(priority = 41)
    public void test_ComplaintDetails_Contain_SpecialCharacter() throws IOException, TransformerException {

        test_name = "Negative_TC_041 : Register Complaint with Complaint Details contain Special Character (Defect)";
        test = extent.createTest(test_name);

        //Generate entity reference number
        String entityRefNumber = generateEntityReferenceNo();

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(entityRefNumber);
        reg.setComplaintDetails("~!@#$^&;[]");

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        if (!res.getErrorCodes().isBlank()) {
            message = "Test Passed : Special characters are not allowed in Complaint details field";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {
            message = "Test Failed : Special characters are not allowed but accepting";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setComplaintDetails("Registration of Complaint");

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }


    //Author : Abhishek
    @Test(priority = 42)
    public void test_maxLength_exceeding_complaintDetails_field() throws IOException, TransformerException {

        test_name = "Negative_TC_042 : Register Complaint with Complaint Details field exceeding (Defect)";
        test = extent.createTest(test_name);

        //Generate entity reference number
        String entityRefNumber = generateEntityReferenceNo();

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(entityRefNumber);
        reg.setComplaintDetails(generateRandomString(4005));  //Maximum 4000 character allowed

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);

        if (!res.getErrorCodes().isBlank()) {
            message = "Test Passed : Complaint details field is throwing error when exceeding the specified limit";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {
            message = "Test Failed : Complaint Registered, even exceeding the Complaint details field";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with value data
        reg.setComplaintDetails("Registration of Complaint");

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }
}
