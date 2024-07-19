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
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

import static Base.Update_Complaint_Status_Helper.attStatusUpdate;
import static Base.Update_Complaint_Status_Helper.escStatusUpdate;
import static Utilities.ErrorCodes.*;
import static Utilities.ExtentReport.extent;

public class Test_7_Att_to_Complaint extends Main {
    @Test(priority = 35)
    public void att_to_new_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = attStatusUpdate();
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
        String error_code = updateRes.getErrorCode();
        test_name = "Negative_TC_035: Attended_to to New status change";
        test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message = "Attended_to to New status change not updated.";
            test.log(Status.PASS,message + " Error code <b>"+error_code+"</b>");
            logger.info(message + " Error code "+error_code);
            status = "PASS";
        }else{
            message = "Attended_to to New status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        regUp.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 36)
    public void att_to_ack_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = attStatusUpdate();
        // function to get IRDATOKEN No and Entity ref no from response payload
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo =res.getIRDATokenNumber();
        String updateEntityRefNo =res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("2");
        // Save Updated payload
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name = "Negative_TC_036:  Attended_to to Acknowledge status change";
        test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message = "Attended_to to Acknowledge status change not updated";
            test.log(Status.PASS,message + " Error code <b>"+error_code+"</b>");
            logger.info(message + " Error code "+error_code);
            status = "PASS";
        }else{
            message ="Attended_to to Acknowledge status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        regUp.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 37)
    public void att_to_pen_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = attStatusUpdate();
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
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name = "Negative_TC_037:  Attended_to to Pending status change";
        test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message = "Attended_to to Pending status change not updated. ";
            test.log(Status.PASS, message + " Error code <b> "+error_code+"</b>");
            logger.info(message + " Error code "+error_code);
            status = "PASS";
        }else{
            message = "Attent_to to Pending status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        regUp.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 38)
    public void att_to_rop_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = attStatusUpdate();
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
        String error_code = updateRes.getErrorCode();
        test_name = "Negative_TC_038:  Attended_to to Reopen status change.";
        test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message = "Attended_to to Reopen status change not updated ";
            test.log(Status.PASS,message + " Error code <b>"+error_code+"</b>");
            logger.info(message + " Error code "+error_code);
            status = "PASS";
        }else{
            message = "Attent_to to Reopen status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        regUp.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 39)
    public void att_to_esc_with_mandatary_fields_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = attStatusUpdate();
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
        String error_code = updateRes.getErrorCode();
        test_name = "Negative_TC_039: Attended_to to Escalated status change(Defect)";
        test = extent.createTest(test_name);
        if(error_code.equals(ESCALATED_REMARKS_ARE_MANDATORY_WHILE_ESCALATING_COMPLAINT +",")){
            message = "Attended_to to Escalated status change not updated.";
            test.log(Status.PASS,message + " Error code <b> "+error_code+"</b>");
            logger.info(message +" Error code "+error_code);
            status = "PASS";
        }else{
            message = "Attended_to to Escalated status change is not updated . Showing";
            test.log(Status.FAIL,message+ " error code"+error_code+" for one mandatory field only");
            logger.error(message + " Error code "+error_code+" for one mandatory field only");
            status = "FAIL";
        }
        //Reset xml node with valid value
        regUp.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 40)
    public void att_to_cls_with_ESC_negative_TC() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        Response escState = escStatusUpdate();
        // function to get IRDATOKEN No and Entity ref no from response payload
        UpdateResponse escResp = new UpdateResponse(escState);
        Update resEscState = new Update("update.xml");
        resEscState.setIrdaTokenNo(escResp.getIRDATokenNumber());
        resEscState.setEntityCompRefNumber(escResp.getEntityCompRefNumber());
        resEscState.setStatusCode("6");//Update complaint with Reopen state
        Response ropResponse = Request.update("Update","update.xml");

        //Update complaint with attent_to state
        UpdateResponse ropResp = new UpdateResponse(ropResponse);
        Update resRopState = new Update("update.xml");
        resRopState.setIrdaTokenNo(ropResp.getIRDATokenNumber());
        resRopState.setEntityCompRefNumber(ropResp.getEntityCompRefNumber());
        resRopState.setStatusCode("4");
        resRopState.setTypeOfDisposal("1");
        Response clsResponse = Request.update("Update","update.xml");
        XMLHelper.removeNode("Update","update.xml", "Type_of_disposal");

        //Update complaint with close state
        Update resClsState = new Update("update.xml");
        UpdateResponse clsResp = new UpdateResponse(clsResponse);
        resClsState.setIrdaTokenNo(clsResp.getIRDATokenNumber());
        resClsState.setEntityCompRefNumber(clsResp.getEntityCompRefNumber());
        resClsState.setStatusCode("7");
        Response res = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(res);
        String error_code = updateRes.getErrorCode();
        test_name = "Negative_TC_040:Attended_to to close with Escalation";
        test=extent.createTest(test_name);
        if (error_code.equals(ESCALATED_COMPLAINTS_CAN_NOT_BE_CLOSED_BY_ENTITY)) {
            message="Attended_to to close with Escalation to status change is not done";
            test.log(Status.PASS, message + " Error code <b>"+error_code+"</b>");
            logger.info(message + " Error code "+error_code);
            status = "PASS";
        } else {
            message = "Attended_to to close with Escalation to status change is not done.";
            test.log(Status.FAIL, message + " Error code "+error_code);
            logger.error(message +" Error code "+error_code);
            status = "FAIL";
        }
        //Reset xml node with valid value
        resClsState.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 41)
    public void att_to_cls_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = attStatusUpdate();
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
        String error_code = updateRes.getErrorCode();
        test_name = "Negative_TC_041: Attent_to to Closed status change.";
        test = extent.createTest(test_name);
        if(error_code.equals(CLOSURE_REQUEST_IN_NOT_ALLOWED_BEFORE_60_DAYS)){
            test.log(Status.PASS,message + " Error code <b>"+error_code+"</b>");
            logger.info(message +" Error code "+error_code);
        }else{
            message = "Attended_to to Closed status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
        }
        //Reset xml node with valid value
        Update resetXmlFile = new Update("update.xml");
        resetXmlFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }
}
