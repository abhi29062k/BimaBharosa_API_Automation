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

public class Test_11_BrokerLicenseNumber_Negative extends Main {

    //Author : Abhishek
    @Test(priority = 17)
    public void test_registerComplaint_Invalid_BrokerLicenseNumber() throws IOException, TransformerException, ParserConfigurationException, SAXException {

        test_name = "Negative_TC_017 : Register Complaint with Invalid Broker License Number (Defect)";
        test = extent.createTest(test_name);

        //Generate entity reference number
        String entityRefNumber = generateEntityReferenceNo();

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_AGAINST_BROKER);
        reg.setEntityCompRefNumber(entityRefNumber);
        reg.setComplaintAgainstTypeId(ComplaintAgainstTypeConstants.BROKER);
        reg.setBrokerLicenseNumber("$#%TY");

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_BROKER);

        //validation
        RegisterResponse res = new RegisterResponse(response);

        if (!res.getErrorCodes().isBlank()) {
            message = "Test Passed : Invalid broker license number is not working";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {
            message = "Test Failed : Invalid broker license number is working";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setBrokerLicenseNumber("B317");

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_BROKER);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);


    }


    //Author : Abhishek
    @Test(priority = 18)
    public void test_registerComplaint_Null_BrokerLicenseNumber() throws IOException, TransformerException, ParserConfigurationException, SAXException {

        test_name = "Negative_TC_018 : Register Complaint with Null Broker License Number";
        test = extent.createTest(test_name);

        //Generate entity reference number
        String entityRefNumber = generateEntityReferenceNo();

        //Remove Broker License Number node
        XMLHelper.removeNode(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_BROKER, "Broker_License_Number");

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_AGAINST_BROKER);
        reg.setEntityCompRefNumber(entityRefNumber);
        reg.setComplaintAgainstTypeId(ComplaintAgainstTypeConstants.BROKER);

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_BROKER);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        String errorCode = res.getErrorCodes();

        if (errorCode.equals(ErrorCodes.BROKER_LICENSE_NUMBER_MANDATORY_AS_COMPLAINT_AGAINST_BROKER)) {
            message = "Test Passed : Broker License Number is Mandatory";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {
            message = "Test Failed : Broker License Number is Mandatory but not provided";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setBrokerLicenseNumber("B317");

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_BROKER);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }
}
