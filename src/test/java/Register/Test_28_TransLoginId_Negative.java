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

public class Test_28_TransLoginId_Negative extends Main {

    @Test(priority = 71)
    public void test_Trans_Login_Id_Null() throws TransformerException, IOException, ParserConfigurationException, SAXException {

        test_name = "Negative_TC_071 : Register Complaint with Null TransLogin ID";
        test = extent.createTest(test_name);
        XMLHelper.removeNode("Register", "register_basic.xml", "Trans_Login_Id");

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());

        //Send request
        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();

        if (errorCode.equals(ErrorCodes.TRANS_LOGIN_ID_MANDATORY)) {
            message = "Test Passed : Trans login ID is mandatory";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {

            message = "Test failed : Trans login ID is mandatory but not provided!";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        // ReSet the node with valid value..
        reg.setTransLoginId("SILICADMIN");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }

    @Test(priority = 72)
    public void test_Trans_Login_Id_Invalid() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name = "Negative_TC_072 : Register Complaint with Invalid TransLogin ID";
        test = extent.createTest(test_name);

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setTransLoginId("DE$%F3");

        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();
        if (errorCode.equals(ErrorCodes.INVALID_TRANSACTION_USER)) {

            message = "Test Passed : Trans login Id invalid";
            test.log(Status.PASS, "Trans login Id invalid");
            logger.info(message);
            status = "PASS";
        } else {

            message = "Test failed : Trans login Id invalid but It is working";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        // ReSet the node with valid value..
        reg.setAddressedToInsurer("Y");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }

    @Test(priority = 73)
    public void test_Trans_Login_Id_Mapping_Invalid() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name = "Negative_TC_073 : Register Complaint with Invalid Mapping of TransLogin ID (Defect)";
        test = extent.createTest(test_name);

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setTransLoginId("HDFCLAdmin");

        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        if (!res.getErrorCodes().isBlank()) {
            message = "Test Passed : Trans login Id mapping invalid and is not working";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {

            message = "Test failed : Trans login Id mapping invalid but It is working";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        // ReSet the node with valid value..
        reg.setTransLoginId("SILICADMIN");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }


}
