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
import org.apache.groovy.parser.antlr4.GroovyParser;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import Response.RegisterResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Test_4_UserType_Negative extends Main {

    //Author : Abhishek
    @Test(priority = 8)
    public void test_registerComplaint_NullUserType() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        test_name = "Negative_TC_008 : Register Complaint with Null User type";
        test = extent.createTest(test_name);

        //removed User_Type node
        XMLHelper.removeNode(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC,"User_Type");

        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(generateEntityReferenceNo());

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        if(res.getErrorCodes().equals(ErrorCodes.USER_TYPE_MANDATORY)){
            message = "Test Passed : User Type is Mandatory";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : User Type is Mandatory but not provided";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setUserType(BimaBharosaConstants.INDIVIDUAL);

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }

    //Author : Abhishek
    @Test(priority = 9)
    public void test_registerComplaint_NumericUserType() throws IOException, TransformerException {

        test_name = "Negative_TC_009 : Register Complaint with Numeric User Type";
        test = extent.createTest(test_name);

        //set value to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setUserType("2");

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER,XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);

        if(res.getErrorCodes().equals(ErrorCodes.USER_TYPE_SHOULD_EITHER_F_OR_I)){
            message = "Test Passed : User Type Should be either F OR I";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : User Type Should be either F OR I";
            test.log(Status.FAIL,message);
            logger.info(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setUserType(BimaBharosaConstants.INDIVIDUAL);

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }


    //Author : Abhishek
    @Test(priority = 10)
    public void test_registerComplaint_InvalidUserType() throws IOException, TransformerException {

        test_name = "Negative_TC_010 : Register Complaint with Invalid User Type";
        test = extent.createTest(test_name);

        //set value to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setUserType("K");

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER,XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);

        if(res.getErrorCodes().equals(ErrorCodes.USER_TYPE_SHOULD_EITHER_F_OR_I)){
            message = "Test Passed : User Type Should be either F OR I";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : User Type Should be either F OR I";
            test.log(Status.FAIL,message);
            logger.info(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setUserType(BimaBharosaConstants.INDIVIDUAL);

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }
}
