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
import java.util.ArrayList;

import static Base.Update_Complaint_Status_Helper.escStatusUpdate;
import static Utilities.ErrorCodes.TYPE_OF_DISPOSAL_ONLY_APPLICABLE_IN_ATT_TO;
import static Utilities.ErrorCodes.WRONG_COMPLAINT_STATUS_ID;

public class Test_9_EscComplaint extends Main {
    @Test(priority = 48)
    public void esc_to_new_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = escStatusUpdate();

        // function to get IRDATOKEN No and Entity ref no from response payload
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
        test_name="Negative_TC_048: Escalation to new status change";
        test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message="Escalation to new status change not updated. ";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message="Escalation to new status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        Update resetXMLFile = new Update("update.xml");
        resetXMLFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }

    @Test(priority = 49)
    public void esc_to_ack_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = escStatusUpdate();
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
        test_name="Negative_TC_049: Escalation to Acknowledgement status change";
        test=extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message="Escalation to Acknowledgement status change not updated ";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message="Escalation to Acknowledgement status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        Update resetXMLFile = new Update("update.xml");
        resetXMLFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }

    @Test(priority = 50)
    public void esc_to_pending_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = escStatusUpdate();
        // function to get IRDATOKEN No and Entity ref no from response payload
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo =res.getIRDATokenNumber();
        String updateEntityRefNo =res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("3");
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_050: Escalation to Pending status change";
        test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message="Escalation to Pending status change not updated.";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message="Escalation to Pending status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        Update resetXMLFile = new Update("update.xml");
        resetXMLFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }

    @Test(priority = 51)
    public void esc_to_att_to_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = escStatusUpdate();
        // function to get IRDATOKEN No and Entity ref no from response payload
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo =res.getIRDATokenNumber();
        String updateEntityRefNo =res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("4");
//        // Save Updated payload
        regUp.updateXMLFile();
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_51: Escalation to Attended to status change";
        test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message="Escalation to Attended to status change not updated. ";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message="Escalation to Attended to status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        Update resetXMLFile = new Update("update.xml");
        resetXMLFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }

    @Test(priority =52 )
    public void esc_to_reopen_less_than_15_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = escStatusUpdate();
        // function to get IRDATOKEN No and Entity ref no from response payload
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo =res.getIRDATokenNumber();
        String updateEntityRefNo =res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("6");
        regUp.setTypeOfDisposal("1");
        // Save Updated payload
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        XMLHelper.removeNode("Update","update.xml", "Type_of_disposal");
        test_name="Negative_TC_052: Escalation to Reopen status change";
        test = extent.createTest(test_name);
        if(error_code.equals(TYPE_OF_DISPOSAL_ONLY_APPLICABLE_IN_ATT_TO)){
            message="Escalation to Reopen status change not updated as type od disposal field is added.";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message="Escalation to Reopen status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";

        }
        Update resetXMLFile = new Update("update.xml");
        resetXMLFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }

    @Test(priority = 53)
    public void esc_to_close_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = escStatusUpdate();
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
        test_name="Negative_TC_053: Escalation to Close status change with disposal field(Mandatory field for Attended to status only)";
        test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID))
        {
            message="Escalation to Close status change  not updated.";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }
        else
        {
            message="Escalation to close done";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        // Save Updated payload
        }
        Update resetXMLFile = new Update("update.xml");
        resetXMLFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }


}

