package Update;

import Base.Main;
import Base.Update_Complaint_Status_Helper;
import Constants.BimaBharosaConstants;
import Operations.Update;
import Request.Request;
import Response.UpdateResponse;
import Utilities.BimabharosaDatabaseHelper;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

import static Utilities.ErrorCodes.*;

public class Test_3_AckComplaint extends Main {
    @Test(priority = 14)
    public void ack_to_new_negative_TC() throws IOException, TransformerException {
        Response response = Update_Complaint_Status_Helper.ackStatusUpdate();
        // function to get IRDATOKEN No and Entity ref no from response payload
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo =res.getIRDATokenNumber();
        String updateEntityRefNo =res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("1");
        // Save Updated payload
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code =updateRes.getErrorCode();
        test_name="Negative_TC_014: Acknowledgement to New status change";
        test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message="Acknowledgement to New status change not updated";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message="Acknowledgement to New status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        regUp.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 15)
    public void ack_to_att_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = Update_Complaint_Status_Helper.ackStatusUpdate();
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
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code =updateRes.getErrorCode();
        test_name="Negative_TC_015: Acknowledgement to Attended status change";
        test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message="Acknowledgement to Attended status change not updated";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";

        }else{
            message="Acknowledgement to Attended status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        regUp.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }


    @Test(priority = 16)
    public void ack_to_esc_negative_TC() throws TransformerException, IOException {
        Response response = Update_Complaint_Status_Helper.ackStatusUpdate();
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
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code =updateRes.getErrorCode();
        test_name="Negative_TC_016: Acknowledgement to Escalated status change";
        test = extent.createTest(test_name);
        if(error_code.equals(ONLY_COMPLAINT_WITH_TAT_CROSSED_CAN_BE_ESCALTED_FROM_ACK_STATE)){
            message="Acknowledgement to Escalation status change (before  15 days TAT of complaint registration) not updated";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else {
            message = "Acknowledgement to Escalation status change (before  15 days TAT of complaint registration) is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        regUp.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 17)
    public void ack_to_rop_negative_TC() throws IOException, TransformerException {
        Response response = Update_Complaint_Status_Helper.ackStatusUpdate();
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
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code =updateRes.getErrorCode();
        test_name="Negative_TC_017: Acknowledgement to Reopen status change";
        test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message="Acknowledgement to Reopen status change not updated";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message="Acknowledgement to Reopen status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        regUp.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }


    @Test(priority = 18)
    public void ack_to_cls_negative_TC() throws TransformerException, IOException {
        Response response = Update_Complaint_Status_Helper.ackStatusUpdate();
        // function to get IRDATOKEN No and Entity ref no from response payload
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo =res.getIRDATokenNumber();
        String updateEntityRefNo =res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("7");
        // Save Updated payload
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code =updateRes.getErrorCode();
        test_name="Negative_TC_018: Acknowledgement to Close status change";
        test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message="Acknowledgement to Close status change not updated";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message="Acknowledgement to Close status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        regUp.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }
}
