package Update;

import Base.Main;
import Base.Update_Complaint_Status_Helper;
import Constants.BimaBharosaConstants;
import Operations.Update;
import Response.UpdateResponse;
import Response.RegisterResponse;
import Request.Request;
import Utilities.BimabharosaDatabaseHelper;
import Utilities.XMLHelper;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

import static Base.RegisterHelper.registerAgainst_InsCompany_claim;
import static Utilities.ErrorCodes.*;

public class Test_4_PenComplaint extends Main {
    @Test(priority = 19)
    public void pen_to_new_negative_TC() throws TransformerException, IOException {
        Response response = Update_Complaint_Status_Helper.penStatusUpdate();
        // function to get IRDATOKEN No and Entity ref no from response payload
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo = res.getIRDATokenNumber();
        String updateEntityRefNo = res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("1");
        // Save Updated payload
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_019: Pending to new status change";
        ExtentTest test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message="Pending to new status change not updated. ";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message="Pending to new status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        regUp.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 20)
    public void pen_to_ack_negative_TC() throws IOException, TransformerException {
        Response response = Update_Complaint_Status_Helper.penStatusUpdate();
        // function to get IRDATOKEN No and Entity ref no from response payload
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo = res.getIRDATokenNumber();
        String updateEntityRefNo = res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");

        // Set values to update payload
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("2");

        // Save Updated payload
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_020:  Pending to ack status change";
        ExtentTest test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message="Pending to ack status change not updated. ";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message="Pending to ack status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        regUp.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 21)
    public void pen_to_esc_negative_TC() throws IOException, TransformerException {
        Response response = Update_Complaint_Status_Helper.penStatusUpdate();
        // function to get IRDATOKEN No and Entity ref no from response payload
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo = res.getIRDATokenNumber();
        String updateEntityRefNo = res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("5");
        // Save Updated payload
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_021: Pending to Escalated status change";
        ExtentTest test = extent.createTest(test_name);

        if(error_code.equals(ONLY_COMPLAINT_WITH_TAT_CROSSED_CAN_BE_ESCALTED_FROM_PENDING_STATE)){
            message="Pending to Escalation status change (before  15 days TAT of complaint registration) not updated";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }

        else
        {
            message = "Pending to Escalation status change (before  15 days TAT of complaint registration) is  updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        regUp.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 22)
    public void pen_to_rop_negative_TC() throws IOException, TransformerException {
        Response response = Update_Complaint_Status_Helper.penStatusUpdate();
        // function to get IRDATOKEN No and Entity ref no from response payload
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo = res.getIRDATokenNumber();
        String updateEntityRefNo = res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("6");
        // Save Updated payload
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name= "Negative_TC_022: Pending to Reopen status change";
        test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message="Pending to Reopen status change not updated. ";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message="Pending to Reopen status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        regUp.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 23)
    public void pen_to_cls_negative_TC() throws IOException, TransformerException {
        Response response = Update_Complaint_Status_Helper.penStatusUpdate();
        // function to get IRDATOKEN No and Entity ref no from response payload
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo = res.getIRDATokenNumber();
        String updateEntityRefNo = res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("7");
        // Save Updated payload
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_023: Pending to close status change";
        ExtentTest test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message="Pending to close status change not updated. ";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";

        }else{
            message="Pending to close status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        regUp.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }


    @Test(priority = 24)
    public void pen_to_attent_to_claim_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = registerAgainst_InsCompany_claim();
        RegisterResponse newState = new RegisterResponse(response);
        String tokenNo = newState.getIRDATokenNumber();
        String entityRefNo =newState.getEntityCompRefNumber();

        Update setAckStatus =new Update("update.xml");
        setAckStatus.setIrdaTokenNo(tokenNo);
        setAckStatus.setEntityCompRefNumber(entityRefNo);
        setAckStatus.setStatusCode("2");

        Response ackResponse = Request.update("Update","update.xml");
        UpdateResponse ackState = new UpdateResponse(ackResponse);
        Update setPenStatus =new Update("update.xml");
        setPenStatus.setIrdaTokenNo(ackState.getIRDATokenNumber());
        setPenStatus.setEntityCompRefNumber(ackState.getEntityCompRefNumber());
        setPenStatus.setStatusCode("3");
        Response penResponse = Request.update("Update","update.xml");

        UpdateResponse penState = new UpdateResponse(penResponse);
        Update setAttToStatus =new Update("update.xml");
        setAttToStatus.setIrdaTokenNo(penState.getIRDATokenNumber());
        setAttToStatus.setEntityCompRefNumber(penState.getEntityCompRefNumber());
        setAttToStatus.setTypeOfDisposal("1");
        setAttToStatus.setRemarks("Remarks");
        setAttToStatus.setStatusCode("4");
//        setAttToStatus.setClaimReceivedAmount("2000");
        Response attToResponse = Request.update("Update","update.xml");
        XMLHelper.removeNode("Update","update.xml", "Type_of_disposal");
        XMLHelper.removeNode("Update","update.xml", "Remarks");
//        XMLHelper.removeNode("Update/update.xml", "Claim_Received_Amount");
        UpdateResponse updateRes = new UpdateResponse(attToResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_024: Pending to Attent_to with claim status change";
        test = extent.createTest(test_name);
        if(error_code.equals(CLAIM_RECEIVED_AMOUNT_IS_MANDATORY)){
            test.log(Status.PASS,"Pending to Attent_to with claim status change not updated with error code <b>"+ error_code+"</b>");
            message="Pending to Attent_to with claim status change not updated. ";
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message="Pending to Attent_to with claim status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        Update resetXmlFile =new Update("update.xml");
        resetXmlFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }


}
