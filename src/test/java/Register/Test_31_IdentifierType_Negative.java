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
import java.util.List;

import Response.RegisterResponse;

public class Test_31_IdentifierType_Negative extends Main {

    @Test(priority = 79)
    public void test_Identifier_Type_Null() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name = "Negative_TC_079 : Register Complaint with Null Identifier Type";
        test = extent.createTest(test_name);
        XMLHelper.removeNode("Register", "register_basic.xml", "Identifier_Type");

        Register reg = new Register("register_basic.xml");
        String entityNum = generateEntityReferenceNo();
        reg.setEntityCompRefNumber(entityNum);

        //Send request
        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        String errorCode = res.getErrorCodes();

        if (errorCode.equals(ErrorCodes.IDENTIFIED_TYPE_MANDATORY)) {
            message = "Identifier type is mandatory";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {

            message = "Test failed : Identifier type is mandatory but not provided!";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        // ReSet the node with valid value..
        reg.setIdentifierType("1");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }

    @Test(priority = 80)
    public void test_Identifier_type_SpecialChars() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name = "Negative_TC_080 : Register Complaint with Identifier Type as Special Character (Defect)";
        test = extent.createTest(test_name);

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setIdentifierType("$");

        Response response = Request.register("Register", "register_basic.xml");

        // ReSet the node with valid value..
        reg.setIdentifierType("1");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        List<String> ls = Request.getTestDetail();

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), null, null, ls.get(0), ls.get(1), getCurrentDateTime(), null, BimaBharosaConstants.REGISTER);

    }

    @Test(priority = 81)
    public void test_Identifier_type_Alphabetical() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name = "Negative_TC_081 : Register Complaint with Identifier Type as Alphabatic Character (Defect)";
        test = extent.createTest(test_name);

        Register reg = new Register("register_basic.xml");
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setIdentifierType("A");

        Response response = Request.register("Register", "register_basic.xml");

        // ReSet the node with valid value..
        reg.setIdentifierType("1");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        List<String> ls = Request.getTestDetail();

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), null, null, ls.get(0), ls.get(1), getCurrentDateTime(), null);

    }


    @Test(priority = 82)
    public void test_Identifier_type_Invalid() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name = "Negative_TC_082 : Register Complaint with Invalid Identifier Type (Defect)";
        test = extent.createTest(test_name);

        Register reg = new Register("register_basic.xml");
        String entityNum = generateEntityReferenceNo();
        reg.setEntityCompRefNumber(entityNum);
        reg.setIdentifierType("7");

        Response response = Request.register("Register", "register_basic.xml");

        RegisterResponse res = new RegisterResponse(response);

        if (!res.getErrorCodes().isBlank()) {
            message = "Identifier type invalid is not working";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {
            message = "Identifier type invalid is working";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }

        // ReSet the node with valid value..
        reg.setIdentifierType("1");
        XMLReader.formatXMLFile("Register", "register_basic.xml");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);
    }

}
