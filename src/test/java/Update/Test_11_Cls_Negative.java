package Update;

import Base.Main;
import Base.Update_Complaint_Status_Helper;
import Constants.BimaBharosaConstants;
import Operations.Update;
import Response.UpdateResponse;
import Request.Request;
import Utilities.BimabharosaDatabaseHelper;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

import static Utilities.ErrorCodes.THIS_COMPLAINT_HAS_ALREADY_BEEN_CLOSED;
import static Utilities.ErrorCodes.WRONG_COMPLAINT_STATUS_ID;

public class Test_11_Cls_Negative extends Main {
    @Test(priority = 60)
    public void cls_to_new_nagative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = Update_Complaint_Status_Helper.closedComplaint();
        //function to get IRDATOKEN No and Entity ref no from response payload
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo =res.getIRDATokenNumber();
        String updateEntityRefNo =res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("1");
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_060: Closed to New status change";
        test = extent.createTest(test_name);
        if(error_code.equals(THIS_COMPLAINT_HAS_ALREADY_BEEN_CLOSED)){
            message="Closed to New status change not updated";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message="Closed to New status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        Update resetXMLFile = new Update("update.xml");
        resetXMLFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 61)
    public void cls_to_ack_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = Update_Complaint_Status_Helper.closedComplaint();
        // function to get IRDATOKEN No and Entity ref no from response payload
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo =res.getIRDATokenNumber();
        String updateEntityRefNo =res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("2");
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_061: Closed to Acknowledge status change";
        test = extent.createTest(test_name);
        if(error_code.equals(THIS_COMPLAINT_HAS_ALREADY_BEEN_CLOSED)){
            message="Closed to Acknowledge status change not updated";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message="Closed to Acknowledge status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        Update resetXMLFile = new Update("update.xml");
        resetXMLFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 62)
    public void cls_to_pen_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = Update_Complaint_Status_Helper.closedComplaint();
        // function to get IRDATOKEN No and Entity ref no from response payload
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo =res.getIRDATokenNumber();
        String updateEntityRefNo =res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("3");
        // Save Updated payload
        regUp.updateXMLFile();
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_062: Closed to Pending status change";
        test = extent.createTest(test_name);
        if(error_code.equals(THIS_COMPLAINT_HAS_ALREADY_BEEN_CLOSED)){
            message="Closed to Pending status change not updated";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message="Closed to Pending status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        Update resetXMLFile = new Update("update.xml");
        resetXMLFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 63)
    public void cls_to_att_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = Update_Complaint_Status_Helper.closedComplaint();
        // function to get IRDATOKEN No and Entity ref no from response payload
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo =res.getIRDATokenNumber();
        String updateEntityRefNo =res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("4");
        // Save Updated payload
        regUp.updateXMLFile();
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_063: Closed to Attend_to status change";
        test = extent.createTest(test_name);
        if(error_code.equals(THIS_COMPLAINT_HAS_ALREADY_BEEN_CLOSED)){
            message="Closed to Attend_to status change not updated";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message="Closed to Attend_to status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        Update resetXMLFile = new Update("update.xml");
        resetXMLFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 64)
    public void cls_to_esc_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = Update_Complaint_Status_Helper.closedComplaint();
        // function to get IRDATOKEN No and Entity ref no from response payload
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo =res.getIRDATokenNumber();
        String updateEntityRefNo =res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("5");
        // Save Updated payload
        regUp.updateXMLFile();
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_064: Closed to Escalated status change";
        test = extent.createTest(test_name);
        if(error_code.equals(THIS_COMPLAINT_HAS_ALREADY_BEEN_CLOSED)){
            message="Closed to Escalated status change not updated";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message="Closed to Escalated status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        Update resetXMLFile = new Update("update.xml");
        resetXMLFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }
    @Test(priority = 65)
    public void cls_to_rop_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = Update_Complaint_Status_Helper.closedComplaint();
        // function to get IRDATOKEN No and Entity ref no from response payload
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo =res.getIRDATokenNumber();
        String updateEntityRefNo =res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("6");
        // Save Updated payload
        regUp.updateXMLFile();
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_065: Closed to Reopen status change";
        test = extent.createTest(test_name);
        if(error_code.equals(THIS_COMPLAINT_HAS_ALREADY_BEEN_CLOSED)){
            message="Closed to Reopen status change not updated";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }
        else{
            message="Closed to Reopen status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        Update resetXMLFile = new Update("update.xml");
        resetXMLFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }


}
