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
import org.apache.logging.log4j.message.Message;
import org.testng.Assert;
import org.testng.annotations.Test;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

import static Utilities.ErrorCodes.*;

public class Test_2_NewComplaint extends Main {
    @Test(priority = 9)
    public void new_to_pen_negative_TC() throws IOException, TransformerException {
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusCode("3");
        regUp.setStatusChangeDate(getCurrentDate());
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_009: New to Pending status change";
        test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message="Test Passed :New to Pending status change not updated.";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message = "Test Failed :New to Pending status change is updated";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        //Reset xml node with valid value
        Update resetXMLFile = new Update("update.xml");
        resetXMLFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }

    @Test(priority = 10)
    public void new_to_att_negative_TC() throws TransformerException, IOException {
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusCode("4");
        regUp.setStatusChangeDate(getCurrentDate());
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_010: New to Attened_to status change";
        test = extent.createTest(test_name);
        if(error_code.equals(MANDATORY_FIELD_TYPE_OF_DISPOSAL +","+WRONG_COMPLAINT_STATUS_ID)){
            message="New to Attened_to status change not updated.";
            test.log(Status.PASS,message+"<b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message= "New to Attened_to status change is updated";
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


    @Test(priority = 11)
    public void new_to_esc_negative_TC() throws TransformerException, IOException {
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusCode("5");
        regUp.setStatusChangeDate(getCurrentDate());
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_011: New to Escalated status change. (Defect)";
        test = extent.createTest(test_name);
        if(error_code.equals(ONLY_COMPLAINT_WITH_TAT_CROSSED_CAN_BE_ESCALTED_FROM_NEW_STATE)){
            message="New to Escalation status change before  15 days TAT of complaint registration not updated";
            test.log(Status.PASS,message+"<b>" +error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else if (error_code.equals(ONLY_COMPLAINT_WITH_TAT_CROSSED_CAN_BE_ESCALTED_FROM_ACK_STATE)){
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


    @Test(priority = 12)
    public void new_to_rop_negative_TC() throws TransformerException, IOException {
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusCode("6");
        regUp.setStatusChangeDate(getCurrentDate());
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_012: New to Reopen status change";
        test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message="New to Reopen status change not updated.";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
            message="New to Reopen status change is updated";
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


    @Test(priority = 13)
    public void new_to_cls_negative_TC() throws TransformerException, IOException {
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusCode("7");
        regUp.setStatusChangeDate(getCurrentDate());
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_013: New to Close status change";
        test = extent.createTest(test_name);
        if(error_code.equals(WRONG_COMPLAINT_STATUS_ID)){
            message="New to Close status change not updated";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        }else{
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
