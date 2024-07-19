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
import org.testng.annotations.Test;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import static Utilities.ErrorCodes.*;

public class Test_1_StatusChangeDate_Validation extends Main {
    @Test(priority = 1)
    public void future_statusChangeDate_negative_TC() throws TransformerException, IOException {
        test_name = "Negative_TC_001:Date Validation with future_statusChangeDate";
        test = extent.createTest(test_name);

        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusChangeDate(getFutureDate());
        // Save Updated payload

        Response UpdateResponse = Request.update("Update", "update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        if (error_code.equals(WRONG_DATE_FORMAT)) {
            message = "Complaint status update with future date is not updated with error code";
            test.log(Status.PASS, message+"<b>"+error_code+"</b>");
            logger.info(message);
            status = "PASS";
        } else {
            message = "Complaint status update with future date is updated";
            test.log(Status.FAIL, message);
            logger.error(message+ "Error code "+error_code);
            status = "FAIL";
        }
        regUp.setStatusChangeDate(getCurrentDate());

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), updateRes.getErrorCode(), status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 2)
    public void current_statusChangeDate_positive_TC() throws TransformerException, IOException {
        test_name = "Positive_TC_002:Date Validation of complaint status update date with valid date";
        test = extent.createTest(test_name);
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");

        // Set values to update payload
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusChangeDate(getCurrentDate());
        // Save Updated payload

        Response UpdateResponse = Request.update("Update", "update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        String entityref_no = updateRes.getEntityCompRefNumber();


        if (entityref_no.equals(param.get(1)) && error_code.isBlank()) {
            message = "complaint status update date  with valid date is updated successfully! ";
            test.log(Status.PASS, message+"Error code = <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        } else {
            message = "complaint status update date  with valid date is not updated";
            test.log(Status.PASS, message);
            logger.info(message);
            status = "FAIL";

        }
        regUp.setStatusChangeDate(getCurrentDate());

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), updateRes.getErrorCode(), status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 3)
    public void Date_validation_format_YYYY_DD_MM_negative_TC() throws TransformerException, IOException {
        test_name = "Negative_TC_003:Date Validation with date format YYYY_DD_MM";
        test = extent.createTest(test_name);

        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusChangeDate("2024-04-21");
        // Save Updated payload

        Response UpdateResponse = Request.update("Update", "update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        if (error_code.equals(WRONG_DATE_FORMAT)) {
            message = "Complaint status update with date format YYYY_DD_MM is not updated.";
            test.log(Status.PASS, message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        } else {
            message = "Complaint status update with date format YYYY_DD_MM is updated";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }
        regUp.setStatusChangeDate(getCurrentDate());

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), updateRes.getErrorCode(), status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);


    }

    @Test(priority = 4)
    public void Date_validation_format_MM_DD_YYYY_negative_TC() throws TransformerException, IOException {
        test_name = "Negative_TC_004:Date Validation with date format MM_DD_YYYY";
        test = extent.createTest(test_name);
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusChangeDate("04-21-2024");
        // Save Updated payload

        Response UpdateResponse = Request.update("Update", "update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();

        if (error_code.equals(WRONG_DATE_FORMAT)) {
            message = "Complaint status update with date format MM_DD_YYYY is not updated.";
            test.log(Status.PASS, message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        } else {
            message = "Complaint status update with date format MM_DD_YYYY is updated";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";

        }
        regUp.setStatusChangeDate(getCurrentDate());

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), updateRes.getErrorCode(), status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);


    }

    @Test(priority = 5)
    public void Date_validation_format_00_00_0000_negative_TC() throws TransformerException, IOException {
        test_name = "Negative_TC_005:Date Validation with date format 00_00_0000";
        test = extent.createTest(test_name);

        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusChangeDate("00-00-0000");
        // Save Updated payload

        Response UpdateResponse = Request.update("Update", "update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        if (error_code.equals(WRONG_DATE_FORMAT)) {
            message = "Complaint status update with date format 00_00_0000 is not updated.";
            test.log(Status.PASS, message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        } else {
            message = "Complaint status update with date format 00_00_0000 is updated.";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";
        }
        regUp.setStatusChangeDate(getCurrentDate());

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), updateRes.getErrorCode(), status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);


    }

    @Test(priority = 6)
    public void Date_validation_alphabet_specialchar_negative_TC() throws TransformerException, IOException {
        String test_name = "Negative_TC_006:Date Validation date alphabet_special character";
        test = extent.createTest(test_name);

        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusChangeDate("ad-qwe-!@#$");
        // Save Updated payload

        Response UpdateResponse = Request.update("Update", "update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        if (error_code.equals(WRONG_DATE_FORMAT)) {
            message = "Complaint status update with date format alphabet_special character is not updated. ";
            test.log(Status.PASS, message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        } else {
            message = "Complaint status update with date format alphabet_special character is updated";
            test.log(Status.FAIL, message);
            logger.error(message);
            status = "FAIL";

        }
        regUp.setStatusChangeDate(getCurrentDate());

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), updateRes.getErrorCode(), status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);


    }

    @Test(priority = 7)
    public void Status_Change_date_not_older_than_complaint_receipt_date_negative_TC() throws TransformerException, IOException {
        test_name = "Negative_TC_007:Date Validation Status Change date should not older than complaint receipt date";
        test = extent.createTest(test_name);

        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusChangeDate("11-02-2024");
        // Save Updated payload

        Response UpdateResponse = Request.update("Update", "update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        if (error_code.equals(STATUS_CHANGE_DATE_OLDER_THAN_PREVIOUS_STATUS_CHANGE_DATE + "," + STATUS_CHANGE_DATE_OLDER_THAN_COMPLAINT_RECEIPT_DATE + "," + STATUS_CHANGE_DATE_OLDER_THAN_COMPLAINT_DATE)) {
            message = "Complaint with Status Change date older than complaint receipt date is not updated.";
            test.log(Status.PASS, message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        } else {
            message ="Complaint with Status Change date older than complaint receipt date is updated";
            test.log(Status.FAIL, message);
            logger.error(message);

        }
        regUp.setStatusChangeDate(getCurrentDate());

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), updateRes.getErrorCode(), status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);


    }

    @Test(priority = 8)
    public void Status_Change_date_not_older_than_previous_status_change_date_negative_TC() throws TransformerException, IOException {
        test_name = "Negative_TC_008:Date Validation Status Change date should not older than previous status change date";
        test = extent.createTest(test_name);

        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update newRes = new Update("update.xml");
        // Set values to update payload
        newRes.setIrdaTokenNo(param.get(0));
        newRes.setEntityCompRefNumber(param.get(1));
        newRes.setStatusChangeDate(getCurrentDate());
        newRes.setStatusCode("2");
        // Save Updated payload

        Response updateResponse = Request.update("Update", "update.xml");
        UpdateResponse ackRes = new UpdateResponse(updateResponse);
        Update ackResponse = new Update("update.xml");
        ackResponse.setIrdaTokenNo(ackRes.getIRDATokenNumber());
        ackResponse.setEntityCompRefNumber(ackRes.getEntityCompRefNumber());
        ackResponse.setStatusCode("3");
        ackResponse.setStatusChangeDate("01-08-2023");

        Response pendingRes = Request.update("Update", "update.xml");
        UpdateResponse finalRes = new UpdateResponse(pendingRes);
        String error_code = finalRes.getErrorCode();
        if (error_code.equals(STATUS_CHANGE_DATE_OLDER_THAN_PREVIOUS_STATUS_CHANGE_DATE + "," + STATUS_CHANGE_DATE_OLDER_THAN_COMPLAINT_RECEIPT_DATE + "," + STATUS_CHANGE_DATE_OLDER_THAN_COMPLAINT_DATE)) {
            message = "Complaint with Status Change date older than previous status change date is not updated.";
            test.log(Status.PASS, message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        } else {
            message = "Complaint with Status Change date older than previous status change date is updated";
            test.log(Status.FAIL, message);
            logger.error(message);
            status ="FAIL";

        }
        ackResponse.setStatusChangeDate(getCurrentDate());
        ackResponse.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //Insert log history in database
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), finalRes.getErrorCode(), status, message, getCurrentDateTime(), finalRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);


    }
}

