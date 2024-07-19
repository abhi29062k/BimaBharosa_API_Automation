package Update;

import Base.Main;
import Constants.BimaBharosaConstants;
import Operations.Update;
import Response.RegisterResponse;
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

import static Base.RegisterHelper.registerAgainst_InsCompany_claim;
import static Base.Update_Complaint_Status_Helper.*;
import static Utilities.ErrorCodes.*;

public class Test_10_RopComplaint extends Main {
    @Test(priority = 54)
    public void rop_to_new_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response =ropStatusUpdate();
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
        regUp.updateXMLFile();
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_054: Reopen to New status change";
        test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message="Reopen to New status change not updated.";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message="Reopen to New status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        Update resetXMLFile = new Update("update.xml");
        resetXMLFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }

    @Test(priority = 55)
    public void rop_to_ack_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = ropStatusUpdate();
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
        regUp.updateXMLFile();
        Response UpdateResponse = Request.update("Update", "update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        regUp.setStatusCode("1");
        // Save Updated payload
        regUp.updateXMLFile();
        test_name="Negative_TC_055: Reopen to Acknowledgement status change";
        test = extent.createTest(test_name);
        if (error_code.equals(WRONG_COMPLAINT_STATUS_ID)) {
            message="Reopen to Acknowledgement status change not updated.";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        } else {
            message="Reopen to Acknowledgement status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        Update resetXMLFile = new Update("update.xml");
        resetXMLFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }

    @Test(priority = 56)
    public void rop_to_pending_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = ropStatusUpdate();
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
        regUp.setStatusCode("1");
        // Save Updated payload
        regUp.updateXMLFile();
        test_name="Negative_TC_056:  Reopen to Pending status change";
        test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message="Reopen to Pending status change not updated.";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message="Reopen to Pending status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        Update resetXMLFile = new Update("update.xml");
        resetXMLFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }

    @Test(priority = 57)
    public void rop_to_esc_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = ropStatusUpdate();
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
        regUp.setStatusCode("1");
        // Save Updated payload
        regUp.updateXMLFile();
        test_name="Negative_TC_057: Reopen to Escalation status change";
        test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message="Reopen to Escalation status change not updated.";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message="Reopen to Escalation status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        Update resetXMLFile = new Update("update.xml");
        resetXMLFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }

    @Test(priority = 58)
    public void rop_to_cls_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = ropStatusUpdate();
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
        regUp.updateXMLFile();
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        regUp.setStatusCode("1");
        // Save Updated payload
        regUp.updateXMLFile();

        test_name="Negative_TC_058: Reopen to Close status change";
        test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message="Reopen to Close status change not updated.";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message="Reopen to Close status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        Update resetXMLFile = new Update("update.xml");
        resetXMLFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }

    @Test(priority = 59)
    public void rop_to_att_with_claim_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = registerAgainst_InsCompany_claim();
        RegisterResponse newState = new RegisterResponse(response);
        String tokenNo = newState.getIRDATokenNumber();
        String entityRefNo =newState.getEntityCompRefNumber();

        Update setAckStatus =new Update("update.xml");
        setAckStatus.setIrdaTokenNo(tokenNo);
        setAckStatus.setEntityCompRefNumber(entityRefNo);
        setAckStatus.setStatusCode("2");
        setAckStatus.setTransLoginId("HDFCGADMIN");

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
        setAttToStatus.setClaimReceivedAmount("2000");
        Response attToResponse = Request.update("Update","update.xml");
        XMLHelper.removeNode("Update","update.xml", "Type_of_disposal");
        XMLHelper.removeNode("Update","update.xml", "Remarks");
        XMLHelper.removeNode("Update","update.xml", "Claim_Received_Amount");

        UpdateResponse escState = new UpdateResponse(attToResponse);
        Update setEscStatus =new Update("update.xml");
        setEscStatus.setIrdaTokenNo(escState.getIRDATokenNumber());
        setEscStatus.setEntityCompRefNumber(escState.getEntityCompRefNumber());
        setEscStatus.setStatusCode("5");
        setEscStatus.setEscalatedRemark("ESC remark");
        Response escResponse = Request.update("Update","update.xml");
        XMLHelper.removeNode("Update","update.xml", "Escalated_Remark");

        UpdateResponse ropState = new UpdateResponse(escResponse);
        Update setRopStatus =new Update("update.xml");
        setRopStatus.setIrdaTokenNo(ropState.getIRDATokenNumber());
        setRopStatus.setEntityCompRefNumber(ropState.getEntityCompRefNumber());
        setRopStatus.setStatusCode("6");
        Response ropResponse = Request.update("Update","update.xml");
        UpdateResponse attToSecTimeState = new UpdateResponse(ropResponse);
        Update setAttToSecTimeStatus =new Update("update.xml");
        setAttToSecTimeStatus.setIrdaTokenNo(attToSecTimeState.getIRDATokenNumber());
        setAttToSecTimeStatus.setEntityCompRefNumber(attToSecTimeState.getEntityCompRefNumber());
        setAttToSecTimeStatus.setStatusCode("4");
        setAttToSecTimeStatus.setTypeOfDisposal("1");
        setAttToSecTimeStatus.setRemarks("Remarks");
        Response attToSecTimeResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(attToSecTimeResponse);
        String error_code = updateRes.getErrorCode();

        test_name="Negative_TC_059: Reopen  to Attended_to with claim status change(Defect)";
        test = extent.createTest(test_name);
        if(error_code.equals(ADDITIONAL_CLOSURE_INFORMATION_MANTATORY_IN_CLOSURE_WITH_CLAIM_COMPLAINT +","+INSURER_RESOLUTION_LETTER_DATE +","+ DATE_OF_HONORING_SERVICE +","+CLAIM_PAYMENT_CHEQUE_DATE_IS_MANDATORY +","+CLAIM_PAYMENT_CHEQUE_NUMBER_IS_MANDATORY +","+CLAIM_PAYMENT_DATE_IS_MANDATORY)){
            message="Reopen  to Attent_to with claim status change not updated with error code ";
            test.log(Status.FAIL,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "FAIL";
        }else{
            message="Reopen  to Attended_to with claim status change is updated";
            test.log(Status.PASS,message);
            logger.error(message);
            status = "PASS";
        }
        XMLHelper.removeNode("Update","update.xml", "Type_of_disposal");
        XMLHelper.removeNode("Update","update.xml", "Remarks");
        Update resetXmlFile =new Update("update.xml");
        resetXmlFile.setStatusCode("1");
        resetXmlFile.setTransLoginId("SILICADMIN");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

}
