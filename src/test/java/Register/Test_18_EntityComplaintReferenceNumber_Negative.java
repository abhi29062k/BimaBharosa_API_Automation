package Register;

import Base.Main;
import Base.RegisterHelper;
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
import Response.RegisterResponse;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Test_18_EntityComplaintReferenceNumber_Negative extends Main {


    //Author : Abhishek
    @Test(priority = 38)
    public void test_Duplicate_EntityComplaintReferenceNumber() throws IOException, TransformerException {

        test_name = "Negative_TC_038 : Register Complaint with Duplicate Entity Complaint Reference Number";
        test = extent.createTest(test_name);

        //Register complaint first time
        Response firstComplaintResponse = RegisterHelper.registerAgainst_InsCompany();

        //Parsing response
        RegisterResponse res1 = new RegisterResponse(firstComplaintResponse);

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_AGAINST_INSURANCE_COMPANY);
        reg.setEntityCompRefNumber(res1.getEntityCompRefNumber());

        //Register new complaint with same entity reference number
        Response duplicateComplaintResponse = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_INSURANCE_COMPANY);

        //validation
        RegisterResponse res = new RegisterResponse(duplicateComplaintResponse);
        String errorCode = res.getErrorCodes();

        if (res.getErrorCodes().equals(ErrorCodes.COMPLAINT_WITH_GIVEN_ENTITY_REF_NUMBER_ALREADY_REGISTERED_DUPLICATION_PROHIBITED)) {
            message = "Test Passed : Duplication of complaint is prohibited";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {
            message = "Test Failed : Duplicate complaint registered with same entity reference number";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_INSURANCE_COMPANY);

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }


    //Author : Abhishek
    @Test(priority = 39)
    public void test_Null_EntityComplaintReferenceNumber() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        test_name = "Negative_TC_039 : Register Complaint with Null Entity Complaint Reference Number";
        test = extent.createTest(test_name);

        //Remove EntityComplaintReferenceNumber node
        XMLHelper.removeNode(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC, "Entity_Complaint_Ref_Number");

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        String errorCode = res.getErrorCodes();

        if (errorCode.equals(ErrorCodes.ENTITY_REF_NUM_MANDATORY)) {
            message = "Test Passed : Entity reference number is mandatory";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {
            message = "Test Failed : Entity reference number is mandatory but not provided";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(generateEntityReferenceNo());

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }
}
