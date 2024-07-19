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

public class Test_13_IntermediaryLicenseNumber_Negative extends Main {

    //Author : Abhishek
    @Test(priority = 21)
    public void test_Null_IntermediaryLicenseNumber() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        test_name = "Negative_TC_021 : Register Complaint with Null Intermediary License Number";
        test = extent.createTest(test_name);

        //Remove Intermediary License Number node
        XMLHelper.removeNode(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_AGENT, "Intermediary_License_Number");

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_AGAINST_AGENT);
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setComplaintAgainstTypeId(ComplaintAgainstTypeConstants.AGENT);
        reg.setIntermediaryName("ASHOK KUMAR SINGH");

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_AGENT);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        String errorCode = res.getErrorCodes();

        if(errorCode.equals(ErrorCodes.INTERMEDIARY_LICENSE_NUMBER_MANDATORY_AS_COMPLAINT_AGAINST_INTERMEDIARY)){
            message = "Test Passed : Intermediary License Number is Mandatory";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : Intermediary License Number is Mandatory but not provided";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setIntermediaryLicenseNumber("IRDA/IND/SLA-10181");

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_AGENT);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }


    //Author : Abhishek
    @Test(priority = 22)
    public void test_Invalid_IntermediaryLicenseNumber() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        test_name = "Negative_TC_022 : Register Complaint with Invalid Intermediary License Number (Defect)";
        test = extent.createTest(test_name);

        //Generate entity reference number
        String entityRefNumber = generateEntityReferenceNo();

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_AGAINST_AGENT);
        reg.setComplaintAgainstTypeId(ComplaintAgainstTypeConstants.AGENT);
        reg.setEntityCompRefNumber(entityRefNumber);
        reg.setIntermediaryName("ASHOK KUMAR SINGH");
        reg.setIntermediaryLicenseNumber("123456");

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_AGENT);

        //validation
        RegisterResponse res = new RegisterResponse(response);

        if(!res.getErrorCodes().isBlank()){
            message = "Test Passed : Invalid intermediary license number is not working";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : Invalid intermediary license number is working";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setIntermediaryLicenseNumber("IRDA/IND/SLA-10181");

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_AGENT);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }
}
