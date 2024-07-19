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

public class Test_12_IntermediaryName_Negative extends Main {

    //Author : Abhishek
    @Test(priority = 19)
    public void test_Null_IntermediaryName() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        test_name = "Negative_TC_019 : Register Complaint with Null Intermediary Name";
        test = extent.createTest(test_name);

        //Remove Intermediary Name node
        XMLHelper.removeNode(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_AGENT, "Intermediary_Name");

        //save values to xml values
        Register reg = new Register(XMLFileConstants.REGISTER_AGAINST_AGENT);
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setComplaintAgainstTypeId(ComplaintAgainstTypeConstants.AGENT);
        reg.setIntermediaryLicenseNumber("IRDA/IND/SLA-10181");

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_AGENT);

        //validation
        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();
        if(errorCode.equals(ErrorCodes.INTERMEDIARY_NAME_MANDATORY_AS_COMPLAINT_AGAINST_INTERMEDIARY)){
            message = "Test Passed : Intermediary name is Mandatory";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : Intermediary name is Mandatory but not provided";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setIntermediaryName("ASHOK KUMAR SINGH");

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_AGENT);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }


    //Author : Abhishek
    @Test(priority = 20)
    public void test_Invalid_IntermediaryName() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        test_name = "Negative_TC_020 : Register Complaint with Invalid Intermediary Name (Defect)";
        test = extent.createTest(test_name);

        //Generate entity reference number
        String entityRefNumber = generateEntityReferenceNo();

        //save values to xml values
        Register reg = new Register(XMLFileConstants.REGISTER_AGAINST_AGENT);
        reg.setEntityCompRefNumber(entityRefNumber);
        reg.setComplaintAgainstTypeId(ComplaintAgainstTypeConstants.AGENT);
        reg.setIntermediaryName("ASD#67");
        reg.setIntermediaryLicenseNumber("IRDA/IND/SLA-10181");

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_AGENT);

        //validation
        RegisterResponse res = new RegisterResponse(response);


        if(!res.getErrorCodes().isBlank()){
            message = "Test Passed : Invalid intermediary name is not working";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : Invalid intermediary name is working";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setIntermediaryName("ASHOK KUMAR SINGH");

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_AGAINST_AGENT);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }


}
