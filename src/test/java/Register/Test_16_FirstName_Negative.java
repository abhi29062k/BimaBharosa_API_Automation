package Register;

import Base.Main;
import Constants.BimaBharosaConstants;
import Constants.XMLFileConstants;
import Operations.Register;
import Request.Request;
import Utilities.BimabharosaDatabaseHelper;
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

public class Test_16_FirstName_Negative extends Main {

    //Author : Abhishek
    @Test(priority = 31)
    public void test_Null_FirstName() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        test_name = "Negative_TC_031 : Register Complaint with Null First Name (Defect)";
        test = extent.createTest(test_name);

        //Generate entity reference number
        String entityRefNumber = generateEntityReferenceNo();

        //Remove First Name node
        XMLHelper.removeNode(BimaBharosaConstants.REGISTER,XMLFileConstants.REGISTER_BASIC, "First_Name");

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(entityRefNumber);

        //send Request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        if(!res.getErrorCodes().isBlank()){
            message = "Test Passed : First Name field is mandatory";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : Complaint Registered without First Name field";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset XML node with valid value
        reg.setFirstName("Abhishek Gupta");

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }


    //Author : Abhishek
    @Test(priority = 32)
    public void test_maxLength_exceeding_FirstName_Field() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        test_name = "Negative_TC_032 : Register Complaint with Firstname field exceeding length (Defect)";
        test = extent.createTest(test_name);

        //Generate entity reference number
        String entityRefNumber = generateEntityReferenceNo();

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setFirstName(generateRandomString(305));  //Maximum 300 characters allowed for first name field
        reg.setEntityCompRefNumber(entityRefNumber);

        //send Request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validtaion
        RegisterResponse res = new RegisterResponse(response);
        if(!res.getErrorCodes().isBlank()){
            message = "Test Passed : First Name field is throwing error when exceeding specified limit";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : Complaint Registered, even exceeding the First Name field limit";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset XML node with valid value
        reg.setFirstName("Abhishek Gupta");

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }
}
