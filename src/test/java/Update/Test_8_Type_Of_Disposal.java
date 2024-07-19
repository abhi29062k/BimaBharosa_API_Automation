package Update;
import Base.Main;
import Base.Update_Complaint_Status_Helper;
import Constants.BimaBharosaConstants;
import Operations.Update;
import Response.UpdateResponse;
import Request.Request;
import Utilities.BimabharosaDatabaseHelper;
import Utilities.XMLHelper;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

import static Utilities.ErrorCodes.ORIGINAL_IRDA_TOKEN_NUM_MANDATORY_WHEN_TYPE_OF_DISPOSAL_DUPLICATE;
import static Utilities.ErrorCodes.TYPE_OF_DISPOSAL_INVALID;

public class Test_8_Type_Of_Disposal extends Main {
    @Test(priority = 42)
    //Pending to att
    public void type_of_dis_1_positive_TC() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        Response response = Update_Complaint_Status_Helper.penStatusUpdate();
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo = res.getIRDATokenNumber();
        String updateEntityRefNo = res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("4");
        //Add Type of disposal and remarks node in existing payload of xml file
        regUp.setTypeOfDisposal("1");
        regUp.setRemarks("Pending to attended testing");
        Response UpdateResponse = Request.update("Update","update.xml");
        //Remove Type of disposal and remarks node from updated payload of xml file
        XMLHelper.removeNode("Update","update.xml", "Type_of_disposal");
        XMLHelper.removeNode("Update","update.xml", "Remarks");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();
        test_name = "Positive_TC_042: Type of disposal 1";
        test=extent.createTest(test_name);
        if (entityRef_no.equals(updateEntityRefNo) && error_code.isBlank()) {
            message = "With type of disposal 1 complaint registered successfully";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {
            message = "With type of disposal 1 complaint not registered.";
            test.log(Status.FAIL, message +" Error Code <b>"+error_code + "</b>");
            logger.error(message + " Error code " +error_code);
            status = "FAIL";
        }
        Update resetXmlFile = new Update("update.xml");
        resetXmlFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 43)
    //Pending to att
    public void type_of_dis_2_positive_TC() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        Response response = Update_Complaint_Status_Helper.penStatusUpdate();
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo = res.getIRDATokenNumber();
        String updateEntityRefNo = res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("4");
        //Add Type of disposal and remarks node in existing payload of xml file
        regUp.setTypeOfDisposal("2");
        regUp.setRemarks("Pending to attended testing");
        Response UpdateResponse = Request.update("Update","update.xml");
        //Remove Type of disposal and remarks node from updated payload of xml file
        XMLHelper.removeNode("Update","update.xml", "Type_of_disposal");
        XMLHelper.removeNode("Update","update.xml", "Remarks");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();
        test_name = "Positive_TC_043:Type of disposal 2 ";
        test=extent.createTest(test_name);
        if (entityRef_no.equals(updateEntityRefNo) && error_code.isBlank()) {
            message = "With type of disposal 2 complaint registered succesfully.";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {
            message = "With type of disposal 2 complaint not registered";
            test.log(Status.FAIL, message +" Error Code <b>"+error_code + "</b>");
            logger.error(message +" Error Code "+error_code);
            status = "FAIL";
        }
        Update resetXmlFile = new Update("update.xml");
        resetXmlFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 44)
    //Pending to att
    public void type_of_dis_3_positive_TC() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        Response response = Update_Complaint_Status_Helper.penStatusUpdate();
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo = res.getIRDATokenNumber();
        String updateEntityRefNo = res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("4");
        //Add Type of disposal and remarks node in existing payload of xml file
        regUp.setTypeOfDisposal("3");
        regUp.setRemarks("Pending to attended testing");
        Response UpdateResponse = Request.update("Update","update.xml");
        //Remove Type of disposal and remarks node from updated payload of xml file
        XMLHelper.removeNode("Update","update.xml", "Type_of_disposal");
        XMLHelper.removeNode("Update","update.xml", "Remarks");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();
        test_name = "Positive_TC_044:Type of disposal 3 ";
        test=extent.createTest(test_name);
        if (entityRef_no.equals(updateEntityRefNo) && error_code.isBlank()) {
            message = "With type of disposal 3 complaint registered succesfully";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {
            message = "With type of disposal 3 complaint not registered";
            test.log(Status.FAIL, message +" Error Code <b>"+error_code + "</b>");
            logger.error(message +" Error Code <b>"+error_code);
            status = "FAIL";
        }
        Update resetXmlFile = new Update("update.xml");
        resetXmlFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 45)
    //Pending to att
    public void type_of_dis_9_positive_TC() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        Response response = Update_Complaint_Status_Helper.penStatusUpdate();
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo = res.getIRDATokenNumber();
        String updateEntityRefNo = res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("4");
        //Add Type of disposal and remarks node in existing payload of xml file
        regUp.setTypeOfDisposal("9");
        regUp.setRemarks("Pending to attended testing");
        regUp.setOriginal_IRDA_Token_Num("04-24-000286");
        Response UpdateResponse = Request.update("Update","update.xml");
        //Remove Type of disposal and remarks node from updated payload of xml file
        XMLHelper.removeNode("Update","update.xml", "Type_of_disposal");
        XMLHelper.removeNode("Update","update.xml", "Remarks");
        XMLHelper.removeNode("Update","update.xml", "Original_IRDA_Token_Num");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();
        test_name = "Positive_TC_045:Type of disposal 9 ";
        test=extent.createTest(test_name);
        if (entityRef_no.equals(updateEntityRefNo) && error_code.isBlank()) {
            message = "With type of disposal 9 complaint registered succesfully.";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "PASS";
        } else {
            message = "With type of disposal 9 complaint not registered";
            test.log(Status.FAIL, message +" Error Code <b>"+error_code + "</b>");
            logger.error(message +" Error Code "+error_code );
            status = "FAIL";
        }
        Update resetXmlFile = new Update("update.xml");
        resetXmlFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 46)
    //Pending to att
    public void type_of_dis_invalid_negative_TC() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        Response response = Update_Complaint_Status_Helper.penStatusUpdate();
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo = res.getIRDATokenNumber();
        String updateEntityRefNo = res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("4");
        //Add Type of disposal and remarks node in existing payload of xml file
        regUp.setTypeOfDisposal("5");
        regUp.setRemarks("Pending to attended testing");
        Response UpdateResponse = Request.update("Update","update.xml");
        //Remove Type of disposal and remarks node from updated payload of xml file
        XMLHelper.removeNode("Update","update.xml", "Type_of_disposal");
        XMLHelper.removeNode("Update","update.xml", "Remarks");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();
        test_name = "Negative_TC_046: Invalid type of disposal";
        test=extent.createTest(test_name);
        if (error_code.equals(TYPE_OF_DISPOSAL_INVALID))
        {
            message = "With invalid type of disposal  complaint not registered";
            test.log(Status.PASS,  message + " Error code <b> "+error_code+"</b>");
            logger.error(message + " Error code "+error_code);
            status = "PASS";
        }
        else
        {
            message = "With invalid type of disposal  complaint registered succesfully";
            test.log(Status.FAIL, message );
            logger.info("With invalid type of disposal  complaint registered succesfully");
            status= "FAIL";
        }
        Update resetXmlFile = new Update("update.xml");
        resetXmlFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 47)
    //Pending to att
    public void type_of_dis_9_without_mand_field_negative_TC() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        Response response = Update_Complaint_Status_Helper.penStatusUpdate();
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo = res.getIRDATokenNumber();
        String updateEntityRefNo = res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("4");
        //Add Type of disposal and remarks node in existing payload of xml file
        regUp.setTypeOfDisposal("9");
        regUp.setRemarks("Pending to attended testing");
        Response UpdateResponse = Request.update("Update","update.xml");
        //Remove Type of disposal and remarks node from updated payload of xml file
        XMLHelper.removeNode("Update","update.xml", "Type_of_disposal");
        XMLHelper.removeNode("Update","update.xml", "Remarks");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name = "Negative_TC_047:Type of disposal 9 without mandatory field.";
        test=extent.createTest(test_name);
        if (error_code.equals(ORIGINAL_IRDA_TOKEN_NUM_MANDATORY_WHEN_TYPE_OF_DISPOSAL_DUPLICATE)) {
            message = "With type of disposal 9 without mandatory field complaint not registered.";
            test.log(Status.PASS, message + " Error code <b>"+error_code+"</b>");
            logger.error(message + " Error code "+error_code);
            status = "PASS";
        } else {
            message = "With type of disposal 9 without mandatory field complaint registered succesfully.";
            test.log(Status.FAIL, message);
            logger.info(message);
            status = "FAIL";
        }
        Update resetXmlFile = new Update("update.xml");
        resetXmlFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }


}