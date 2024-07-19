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
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

import Response.RegisterResponse;
import io.restassured.response.Response;

public class Test_3_RegisterComplaint_Dates_Negative extends Main {

    //Author : Abhishek
    @Test(priority = 3)
    public void test_registerComplaint_FutureDate() throws IOException, TransformerException {

        test_name = "Negative_TC_003 : Register Complaint with Future Date";
        test = extent.createTest(test_name);

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setComplaintDate(getFutureDate());
        reg.setComplaintReceiptDate(getFutureDate());

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER,XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        String errorCodes = res.getErrorCodes();

        if(res.getErrorCodes().contains(ErrorCodes.COMPLAINT_DATE_INVALID) && res.getErrorCodes().contains(ErrorCodes.COMPLAINT_RECEIPT_DATE_INVALID)){
            message = "Test Passed : Invalid ComplaintDate and ComplaintReceiptDate is not working";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : Invalid ComplaintDate and ComplaintReceiptDate is working";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setComplaintDate(getCurrentDate());
        reg.setComplaintReceiptDate(getCurrentDate());

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }


    //Author : Abhishek
    @Test(priority = 4)
    public void test_registerComplaint_InvalidDateFormat() throws IOException, TransformerException, ParserConfigurationException, SAXException {

        test_name = "Negative_TC_004 : Register Complaint with Invalid Date Format";
        test = extent.createTest(test_name);

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setComplaintDate("2024-04-15");
        reg.setComplaintReceiptDate("2024-04-15");

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        String errorCodes = res.getErrorCodes();

        if(errorCodes.contains(ErrorCodes.COMPLAINT_DATE_INVALID) && errorCodes.contains(ErrorCodes.COMPLAINT_RECEIPT_DATE_INVALID)){
            message = "Test Passed : Invalid Complaint Date and Complaint Receipt Date format is not working";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        }
        else
        {
            message = "Test Failed : Invalid Complaint Date and Complaint Receipt Date format is working";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setComplaintDate(getCurrentDate());
        reg.setComplaintReceiptDate(getCurrentDate());

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }


    //Author : Abhishek
    @Test(priority = 5)
    public void test_complaintDateAndReceiptDate_Null() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        test_name = "Negative_TC_005 : Complaint Date and Receipt Date Null";
        test = extent.createTest(test_name);

        //removed complaintDate and complaintReceiptDate node
        XMLHelper.removeNode(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC, "Complaint_Date");
        XMLHelper.removeNode(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC, "Complaint_Receipt_Date");

        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(generateEntityReferenceNo());

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        String errorCodes = res.getErrorCodes();

        if(errorCodes.contains(ErrorCodes.COMPLAINT_DATE_MANDATORY) && errorCodes.contains(ErrorCodes.COMPLAINT_RECEIPT_DATE_MANDATORY)){
            message = "Test Passed : Complaint Date and Complaint ReceiptDate is mandatory";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        }
        else{
            message = "Test Failed : Complaint Date and Complaint ReceiptDate is mandatory but not provided";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setComplaintDate(getCurrentDate());
        reg.setComplaintReceiptDate(getCurrentDate());

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }


    //Author : Abhishek
    @Test(priority = 6)
    public void test_complaintDate_And_ComplaintReceiptDate_Zero() throws IOException, TransformerException {

        test_name = "Negative_TC_006 : Complaint Date and Receipt Date Zero";
        test = extent.createTest(test_name);

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setComplaintDate("00-00-0000");
        reg.setComplaintReceiptDate("00-00-0000");

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        String errorCodes = res.getErrorCodes();

        if(errorCodes.contains(ErrorCodes.COMPLAINT_DATE_INVALID) && errorCodes.contains(ErrorCodes.COMPLAINT_RECEIPT_DATE_INVALID)){
            message = "Test Passed : Complaint Date and Complaint Receipt Date is not working with Zero";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        }
        else {
            message = "Test Failed : Complaint Date and Complaint Receipt Date is working with zero";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setComplaintDate(getCurrentDate());
        reg.setComplaintReceiptDate(getCurrentDate());

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }


    //Author : Abhishek
    @Test(priority = 7)
    public void test_complaintDate_And_ComplaintReceiptDate_AsChracter() throws IOException, TransformerException {

        test_name = "Negative_TC_007 : Complaint Date and Receipt Date As Character";
        test = extent.createTest(test_name);

        //set values to xml file
        Register reg = new Register(XMLFileConstants.REGISTER_BASIC);
        reg.setEntityCompRefNumber(generateEntityReferenceNo());
        reg.setComplaintDate("DD-MM-YYYY");
        reg.setComplaintReceiptDate("DD-MM-YYYY");

        //send request
        Response response = Request.register(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        //validation
        RegisterResponse res = new RegisterResponse(response);
        String errorCodes = res.getErrorCodes();

        if(errorCodes.contains(ErrorCodes.COMPLAINT_DATE_INVALID) && errorCodes.contains(ErrorCodes.COMPLAINT_RECEIPT_DATE_INVALID)){
            message = "Test Passed : Complaint Date and Complaint Receipt Date should not a character";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        }
        else {
            message = "Test Failed : Complaint Date and Complaint Receipt Date is working with character";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }

        //Reset xml node with valid value
        reg.setComplaintDate(getCurrentDate());
        reg.setComplaintReceiptDate(getCurrentDate());

        //Format xml file
        XMLReader.formatXMLFile(BimaBharosaConstants.REGISTER, XMLFileConstants.REGISTER_BASIC);

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), res.getErrorCodes(), status, message, getCurrentDateTime(), res.getEntityCompRefNumber(), BimaBharosaConstants.REGISTER);

    }
}
