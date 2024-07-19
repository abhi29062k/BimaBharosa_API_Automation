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
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import static Base.RegisterHelper.registerAgainst_InsCompany_claim;
import static Base.Update_Complaint_Status_Helper.*;

public class Test_15_UpdateComplaint_Positive extends Main {

    @Test(priority =72)
    public void new_to_ack_positive_TC() throws TransformerException, IOException {
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusCode("2");
        regUp.setStatusChangeDate(getCurrentDate());
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();
        test_name="Positive_TC_072:New to Acknowledged status change ";
        test=extent.createTest(test_name);
        if (entityRef_no.equals(param.get(1)) && error_code.isBlank()) {
            message="New to Acknowledged status change done successfully";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        } else {
            test.log(Status.FAIL,message+ "Error code <b>"+error_code+"</b>");
            logger.error(message+ "Error code "+error_code);
            status = "FAIL";
        }
        //Reset xml node with valid value
        Update setStateAsOriginal = new Update("update.xml");
        setStateAsOriginal.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 73)
    public void new_to_esc_after_15_days_positive_TC() throws IOException, TransformerException {
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameterForPastDate();
        Update ackState = new Update("update.xml");
        ackState.setIrdaTokenNo(param.get(0));
        ackState.setEntityCompRefNumber(param.get(1));
        ackState.setStatusCode("5");
        ackState.setStatusChangeDate(getCurrentDate());
        Response ackResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(ackResponse);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();
        test_name="Positive_TC_073: New to Escalated after 15 days ";
        test=extent.createTest(test_name);
        if (entityRef_no.equals(param.get(1)) && error_code.isBlank()) {
            message="New to Escalated after 15 days done Successfuly.";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        } else {
            message="New to Escalated after 15 days Successfuly not done with error code ";
            test.log(Status.FAIL,message+ "Error code <b>"+error_code+"</b>");
            logger.error(message+ "Error code "+error_code);
            status = "FAIL";
        }
        //Reset xml node with valid value
        Update setStateAsOriginal = new Update("update.xml");
        setStateAsOriginal.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority =74)
    public void ack_to_pen_positive_TC() throws IOException, TransformerException {
        Response response = Update_Complaint_Status_Helper.ackStatusUpdate();
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo =res.getIRDATokenNumber();
        String updatEntityRefNo =res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updatEntityRefNo);
        regUp.setStatusCode("3");
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();
        test_name="Positive_TC_074:Acknowledged to Pending status chang";
        test=extent.createTest(test_name);
        message="";
        if (entityRef_no.equals(updatEntityRefNo) && error_code.isBlank()) {
            message="Acknowledged to Pending status chang done successfully";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        } else {
            message="Acknowledged to Pending status change not done";
            test.log(Status.FAIL,message+ "Error code <b>"+error_code+"</b>");
            logger.error(message+ "Error code "+error_code);
            status = "FAIL";
        }
        //Reset xml node with valid value
        Update setStateAsOriginal = new Update("update.xml");
        setStateAsOriginal.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 75)
    public void ack_to_esc_after_15_days_positive_TC() throws IOException, TransformerException {
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameterForPastDate();
        Update ackState = new Update("update.xml");
        ackState.setIrdaTokenNo(param.get(0));
        ackState.setEntityCompRefNumber(param.get(1));
        ackState.setStatusCode("2");
        ackState.setStatusChangeDate(getPastDate());
        Response ackResponse = Request.update("Update","update.xml");

        UpdateResponse ackStateRes = new UpdateResponse(ackResponse);
        Update EscState = new Update("update.xml");
        EscState.setIrdaTokenNo(ackStateRes.getIRDATokenNumber());
        EscState.setEntityCompRefNumber(ackStateRes.getEntityCompRefNumber());
        EscState.setStatusCode("5");
        EscState.setStatusChangeDate(getCurrentDate());
        Response attResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(attResponse);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();
        test_name="Positive_TC_075: Acknowledge to Escalated after 15 days ";
        test=extent.createTest(test_name);
        if (entityRef_no.equals(param.get(1)) && error_code.isBlank()) {
            message="Acknowledge to Escalated after 15 days done Successfuly.";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        } else {
            message="Acknowledge to Escalated after 15 days Successfuly not done";
            test.log(Status.FAIL,message+ "Error code <b>"+error_code+"</b>");
            logger.error(message+ "Error code "+error_code);
            status = "FAIL";
        }
        //Reset xml node with valid value
        Update setStateAsOriginal = new Update("update.xml");
        setStateAsOriginal.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 76)
    public void pen_to_att_positive_TC() throws TransformerException, IOException, ParserConfigurationException, SAXException {
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
        regUp.setRemarks("test me");
        Response UpdateResponse = Request.update("Update","update.xml");
        //Remove Type of disposal and remarks node from updated payload of xml file
        XMLHelper.removeNode("Update","update.xml", "Type_of_disposal");
        XMLHelper.removeNode("Update","update.xml", "Remarks");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();
        test_name="Positive_TC_076:Pending to Attended to";
        test=extent.createTest(test_name);
        if (entityRef_no.equals(updateEntityRefNo) && error_code.isBlank()) {
            message="Pending to Attended to status change done successfully";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        } else {
            message="Pending to Attended to status change not done";
            test.log(Status.FAIL,message+ "Error code <b>"+error_code+"</b>");
            logger.error(message+ "Error code "+error_code);
            status = "FAIL";
        }
        //Reset xml node with valid value
        Update resetfileObj = new Update("update.xml");
        resetfileObj.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 77)
    public void pen_to_attent_to_claim_positive_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
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
        setAttToStatus.setClaimReceivedAmount("2000");
        Response attToResponse = Request.update("Update","update.xml");
        XMLHelper.removeNode("Update","update.xml", "Type_of_disposal");
        XMLHelper.removeNode("Update","update.xml", "Remarks");
        XMLHelper.removeNode("Update","update.xml", "Claim_Received_Amount");
        UpdateResponse updateRes = new UpdateResponse(attToResponse);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();
        test_name="Positive_TC_077:Pending to Attended_to claim";
        test=extent.createTest(test_name);
        if (entityRef_no.equals(entityRef_no) && error_code.isBlank()) {
            message="Pending to Attended_to claim status change done successfully";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        } else {
            message="Pending to Attended_to claim status change not done";
            test.log(Status.FAIL,message+ "Error code <b>"+error_code+"</b>");
            logger.error(message+ "Error code "+error_code);
            status = "FAIL";
        }
        //Reset xml node with valid value
        Update resetXmlFile =new Update("update.xml");
        resetXmlFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 78)
    public void pen_to_esc_after_15_days_positive_TC() throws IOException, TransformerException {
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameterForPastDate();
        Update ackState = new Update("update.xml");
        ackState.setIrdaTokenNo(param.get(0));
        ackState.setEntityCompRefNumber(param.get(1));
        ackState.setStatusCode("2");
        ackState.setStatusChangeDate(getPastDate());
        Response ackResponse = Request.update("Update","update.xml");

        //code for make complaint Acknowledge to Pending status with status_change_date pastdate
        UpdateResponse ackUpdateResp = new UpdateResponse(ackResponse);
        Update penState = new Update("update.xml");
        penState.setIrdaTokenNo(ackUpdateResp.getIRDATokenNumber());
        penState.setEntityCompRefNumber(ackUpdateResp.getEntityCompRefNumber());
        penState.setStatusCode("3");
        penState.setStatusChangeDate(getPastDate());
        Response penResponse = Request.update("Update","update.xml");

        UpdateResponse Response = new UpdateResponse(penResponse);
        Update EscState = new Update("update.xml");
        EscState.setIrdaTokenNo(Response.getIRDATokenNumber());
        EscState.setEntityCompRefNumber(Response.getEntityCompRefNumber());
        EscState.setStatusCode("5");
        EscState.setStatusChangeDate(getCurrentDate());
        Response Res = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(Res);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();
        test_name="Positive_TC_078: Pending to Escalated after 15 days";
        test=extent.createTest(test_name);
        if (entityRef_no.equals(param.get(1)) && error_code.isBlank()) {
            message="Pending to Escalated after 15 days done Successfuly.";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        } else {
            message="Pending to Escalated after 15 days Successfuly not done";
            test.log(Status.FAIL,message+ "Error code <b>"+error_code+"</b>");
            logger.error(message+ "Error code "+error_code);
            status = "FAIL";
        }
        //Reset xml node with valid value
        Update setStateAsOriginal = new Update("update.xml");
        setStateAsOriginal.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority =79)
    //In First iteration of escalation, Script require escalation_remarks field as mandatory.
    public void att_to_esc_firstTime_positive_TC() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        Response response = attStatusUpdate();
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo = res.getIRDATokenNumber();
        String updateEntityRefNo = res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        //Add Escalated_Remark node in existing payload of xml file
        regUp.setEscalatedRemark("Escalated_Remark");
        regUp.setStatusCode("5");
        Response UpdateResponse = Request.update("Update","update.xml");
        XMLHelper.removeNode("Update","update.xml", "Escalated_Remark");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();
        test_name="Positive_TC_079:Attended_To to Escalation status change ";
        test=extent.createTest(test_name);
        if (entityRef_no.equals(updateEntityRefNo) && error_code.isBlank()) {
            message="Attended_To to Escalation status change done successfully";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        } else {
            message="Attended_To to Escalation status change not done";
            test.log(Status.FAIL,message+ "Error code <b>"+error_code+"</b>");
            logger.error(message+ "Error code "+error_code);
            status = "FAIL";
        }
        //Reset xml node with valid value
        Update setStateAsOriginal = new Update("update.xml");
        setStateAsOriginal.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 80)
    //In second iteration of escalation, Script is able to esc without escalation_remarks field
    public void att_to_esc_secondTime_positive_TC() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        Response response = ropStatusUpdate();
        //Update complaint to Attent_to status.
        UpdateResponse ropRes = new UpdateResponse(response);
        String updateTokenNo = ropRes.getIRDATokenNumber();
        String updateEntityRefNo = ropRes.getEntityCompRefNumber();
        Update attToState = new Update("update.xml");
        attToState.setIrdaTokenNo(updateTokenNo);
        attToState.setEntityCompRefNumber(updateEntityRefNo);
        attToState.setStatusCode("4");
        attToState.setTypeOfDisposal("1");
        Response updateAttTo = Request.update("Update","update.xml");
        XMLHelper.removeNode("Update","update.xml", "Type_of_disposal");
        //here updating complaint to escalation secondTime.
        UpdateResponse attToRes = new UpdateResponse(updateAttTo);
        Update escState = new Update("update.xml");
        escState.setEntityCompRefNumber(attToRes.getEntityCompRefNumber());
        escState.setIrdaTokenNo(attToRes.getIRDATokenNumber());
        escState.setStatusCode("5");
        Response updateEsc = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(updateEsc);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();
        test_name="Positive_TC_080:Attended_To to Escalation SecondTime status change ";
        test=extent.createTest(test_name);
        if (entityRef_no.equals(updateEntityRefNo) && error_code.isBlank()) {
            message="Attended_To to Escalation (SecondTime) status change done successfully";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        } else {
            message="Attended_To to Escalation (SecondTime) status change not done";
            test.log(Status.FAIL,message+ "Error code <b>"+error_code+"</b>");
            logger.error(message+ "Error code "+error_code);
            status = "FAIL";
        }
        //Reset xml node with valid value
        Update setStateAsOriginal = new Update("update.xml");
        setStateAsOriginal.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    //Complaint status : new -> ack -> pen -> att -> closed(after 60 or more days from att status)
    @Test(priority = 81)
    public void att_to_cls_positive_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameterForPastDate();
        Update ackState = new Update("update.xml");
        ackState.setIrdaTokenNo(param.get(0));
        ackState.setEntityCompRefNumber(param.get(1));
        ackState.setStatusCode("2");
        ackState.setStatusChangeDate(getPastDate());
        Response ackResponse = Request.update("Update","update.xml");

        //code for make complaint Acknowledge to Pending status with status_change_date pastdate
        UpdateResponse ackUpdateResp = new UpdateResponse(ackResponse);
        Update penState = new Update("update.xml");
        penState.setIrdaTokenNo(ackUpdateResp.getIRDATokenNumber());
        penState.setEntityCompRefNumber(ackUpdateResp.getEntityCompRefNumber());
        penState.setStatusCode("3");
        penState.setStatusChangeDate(getPastDate());
        Response penResponse = Request.update("Update","update.xml");

        //code for make complaint Pending to attended_to status with status_change_date pastdate
        // add and remove Type_of_disposal,Remarks in payload
        UpdateResponse penUpdateResp = new UpdateResponse(penResponse);
        Update attToState = new Update("update.xml");
        attToState.setIrdaTokenNo(penUpdateResp.getIRDATokenNumber());
        attToState.setEntityCompRefNumber(penUpdateResp.getEntityCompRefNumber());
        attToState.setStatusCode("4");
        attToState.setStatusChangeDate(getPastDate());
        attToState.setTypeOfDisposal("1");
        attToState.setRemarks("Remak during Att to Testing");
        Response attResponse = Request.update("Update","update.xml");

        //code for make complaint attended_to to close status with status_change_date current date
        UpdateResponse attUpdateResponse = new UpdateResponse(attResponse);
        XMLHelper.removeNode("Update","update.xml", "Type_of_disposal");
        XMLHelper.removeNode("Update","update.xml", "Remarks");
        Update closeState = new Update("update.xml");
        closeState.setIrdaTokenNo(attUpdateResponse.getIRDATokenNumber());
        closeState.setEntityCompRefNumber(attUpdateResponse.getEntityCompRefNumber());
        closeState.setStatusCode("7");
        closeState.setStatusChangeDate(getCurrentDate());
        Response closeResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(closeResponse);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();
        test_name="Positive_TC_081:Attended_To to Escalation SecondTime status change";
        test=extent.createTest(test_name);
        if (entityRef_no.equals(param.get(1)) && error_code.isBlank()) {
            message="Attended_To to close status change done successfully";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        } else {
            message="Attended_To to close status change not done";
            test.log(Status.FAIL,message+ "Error code <b>"+error_code+"</b>");
            logger.error(message+ "Error code "+error_code);
            status = "FAIL";
        }
        //Reset xml node with valid value
        ackState.setStatusCode("1");
        ackState.setStatusChangeDate(getCurrentDate());

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 82)
    public void closure_request_to_IRDIA_positive_TC() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        Response ropResponse = ropStatusUpdate();
        //Update complaint with attent_to state
        UpdateResponse ropResp = new UpdateResponse(ropResponse);
        Update resRopState = new Update("update.xml");
        resRopState.setIrdaTokenNo(ropResp.getIRDATokenNumber());
        resRopState.setEntityCompRefNumber(ropResp.getEntityCompRefNumber());
        resRopState.setStatusCode("4");
        resRopState.setTypeOfDisposal("1");
        Response irdiaResponse = Request.update("Update","update.xml");
        XMLHelper.removeNode("Update","update.xml", "Type_of_disposal");

        //Update complaint with close state
        UpdateResponse clsResp = new UpdateResponse(irdiaResponse);
        Update irdiaState = new Update("update.xml");
        irdiaState.setIrdaTokenNo(clsResp.getIRDATokenNumber());
        irdiaState.setEntityCompRefNumber(clsResp.getEntityCompRefNumber());
        irdiaState.setTypeOfDisposal("1");
        irdiaState.setOthersClsrAdditionalInfo("testing");
        irdiaState.setOthersClosureDescription("testing");
        irdiaState.setRequestIRDAIForClosure("1");
        irdiaState.setEntityClosureReqDesc("Complaint closure request");
        irdiaState.setClosureRequestLetterDate("01-01-2024");
        irdiaState.setDateOfHonoringService("01-01-2024");
        irdiaState.setInsurerResolutionLetterdate("01-01-2024");
        irdiaState.setClosureViolationRemarks("Complaint Closure");
        irdiaState.setRegulatoryImprovementSuggestion("testing");
        irdiaState.setIsComplainantInformed("Y");
        irdiaState.setClosureActionSuggested("Closure request");
        Response res = Request.update("Update","update.xml");
        //Remove added node from xml payload
        XMLHelper.removeNode("Update","update.xml", "Type_of_disposal");
        XMLHelper.removeNode("Update","update.xml", "Others_Clsr_Additional_Info");
        XMLHelper.removeNode("Update","update.xml", "Others_Closure_Description");
        XMLHelper.removeNode("Update","update.xml", "Request_IRDAI_For_Closure");
        XMLHelper.removeNode("Update","update.xml", "Entity_Closure_Req_Desc");
        XMLHelper.removeNode("Update","update.xml", "Closure_Request_Letter_Date");
        XMLHelper.removeNode("Update","update.xml", "Date_Of_Honoring_Service");
        XMLHelper.removeNode("Update","update.xml", "Insurer_Resolution_Letter_date");
        XMLHelper.removeNode("Update","update.xml", "Closure_Violation_Remarks");
        XMLHelper.removeNode("Update","update.xml", "Regulatory_Improvement_Suggestion");
        XMLHelper.removeNode("Update","update.xml", "Is_Complainant_Informed");
        XMLHelper.removeNode("Update","update.xml", "Closure_Action_Suggested");
//        String error_code = res.xmlPath().getString("Envelope.Body.UpdateComplaintResponse.UpdateComplaintResult.RESULT.COMPLAINTDETAILS.ERROR_CODES");
        UpdateResponse updateRes = new UpdateResponse(res);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();
        test_name="Positive_TC_082:Positive TC: Closure Request to IRDIA ";
        test=extent.createTest(test_name);
        if (entityRef_no.equals(clsResp.getEntityCompRefNumber()) && error_code.isBlank()) {
            message="Closure Request to IRDIA marked Successfully. Note: due to Functional issue data not getting updated in Database.";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        } else {
            message="Closure Request to IRDIA done successfully not done";
            test.log(Status.FAIL,message+ "Error code <b>"+error_code+"</b>");
            logger.error(message+ "Error code "+error_code);
            status = "FAIL";
        }
        //Reset xml node with valid value
        Update setStateAsOriginal = new Update("update.xml");
        setStateAsOriginal.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority = 83)
    public void esc_to_rop_positive_TC() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        Response response = escStatusUpdate();
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo = res.getIRDATokenNumber();
        String updateEntityRefNo = res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("6");
        regUp.updateXMLFile();
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();
        test_name="Positive_TC_083:Escalation to Reopen status change ";
        if (entityRef_no.equals(updateEntityRefNo) && error_code.isBlank()) {
            message="Escalation to Reopen status change done successfully";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        } else {
            message="Escalation to Reopen status change not done";
            test.log(Status.FAIL,message+ "Error code <b>"+error_code+"</b>");
            logger.error(message+ "Error code "+error_code);
            status = "FAIL";
        }
        //Reset xml node with valid value
        Update setStateAsOriginal = new Update("update.xml");
        setStateAsOriginal.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    //When complaint is type claim new -> Ack -> Pending -> attent_to -> esc -> Reopen -> attent_to
    //From Reopen -> Attend_to, it asked some mandatory fields as Claim_Payment_Cheque_Date,Claim_Payment_Cheque_Number,
    // Claim_Payment_Date,Date_Of_Honoring_Service,Insurer_Resolution_Letter_date,
    // Others_Clsr_Additional_Info,Claim_Clsr_Additional_Info.
    @Test(priority = 84)
    public void rop_to_att_with_claim_positive_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = registerAgainst_InsCompany_claim();
        RegisterResponse newState = new RegisterResponse(response);
        String tokenNo = newState.getIRDATokenNumber();
        String entityRefNo =newState.getEntityCompRefNumber();

        //update complaint from new to ack state
        Update setAckStatus =new Update("update.xml");
        setAckStatus.setIrdaTokenNo(tokenNo);
        setAckStatus.setEntityCompRefNumber(entityRefNo);
        setAckStatus.setStatusCode("2");
        setAckStatus.setTransLoginId("HDFCGADMIN");

        //update complaint from Ack to pending state
        Response ackResponse = Request.update("Update","update.xml");
        UpdateResponse ackState = new UpdateResponse(ackResponse);
        Update setPenStatus =new Update("update.xml");
        setPenStatus.setIrdaTokenNo(ackState.getIRDATokenNumber());
        setPenStatus.setEntityCompRefNumber(ackState.getEntityCompRefNumber());
        setPenStatus.setStatusCode("3");
        Response penResponse = Request.update("Update","update.xml");

        //update complaint from Pending to attent_to state with added and removed type_of_disposal, Remarks, Claim_REceived_amount
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

        //update complaint from attent_to to Esc state
        UpdateResponse escState = new UpdateResponse(attToResponse);
        Update setEscStatus =new Update("update.xml");
        setEscStatus.setIrdaTokenNo(escState.getIRDATokenNumber());
        setEscStatus.setEntityCompRefNumber(escState.getEntityCompRefNumber());
        setEscStatus.setStatusCode("5");
        setEscStatus.setEscalatedRemark("ESC remark");
        Response escResponse = Request.update("Update","update.xml");
        XMLHelper.removeNode("Update","update.xml", "Escalated_Remark");

        //update complaint from Esc to Reopen state
        UpdateResponse ropState = new UpdateResponse(escResponse);
        Update setRopStatus =new Update("update.xml");
        setRopStatus.setIrdaTokenNo(ropState.getIRDATokenNumber());
        setRopStatus.setEntityCompRefNumber(ropState.getEntityCompRefNumber());
        setRopStatus.setStatusCode("6");
        Response ropResponse = Request.update("Update","update.xml");

        //update complaint from Reopen to attent_to state
        UpdateResponse attToSecTimeState = new UpdateResponse(ropResponse);
        Update setAttToSecTimeStatus =new Update("update.xml");
        setAttToSecTimeStatus.setIrdaTokenNo(attToSecTimeState.getIRDATokenNumber());
        setAttToSecTimeStatus.setEntityCompRefNumber(attToSecTimeState.getEntityCompRefNumber());
        setAttToSecTimeStatus.setStatusCode("4");
        setAttToSecTimeStatus.setTypeOfDisposal("1");
        setAttToSecTimeStatus.setRemarks("Remarks");
        setAttToSecTimeStatus.setClaimPaymentChequeDate(getCurrentDate());
        setAttToSecTimeStatus.setClaimPaymentChequeNumber("12345");
        setAttToSecTimeStatus.setClaimPaymentDate(getCurrentDate());
        setAttToSecTimeStatus.setDateOfHonoringService(getCurrentDate());
        setAttToSecTimeStatus.setInsurerResolutionLetterdate(getCurrentDate());
        setAttToSecTimeStatus.setOthersClsrAdditionalInfo("Others_Clsr_Additional_Info");
        setAttToSecTimeStatus.setClaimClosureAdditionalInfo("setClaimClosureAdditionalInfo");
        Response attToSecTimeResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(attToSecTimeResponse);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();
        test_name="Positive_TC_084:Reopen to Attended to with Claim";
        test=extent.createTest(test_name);
        if (entityRef_no.equals(entityRef_no) && error_code.isBlank()) {
            message="Reopen to Attended to with claim  status change done successfully";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        } else {
            message="Reopen to Attended to with claim  status change not done";
            test.log(Status.FAIL,message+ "Error code <b>"+error_code+"</b>");
            logger.error(message+ "Error code "+error_code);
            status = "FAIL";
        }
        XMLHelper.removeNode("Update","update.xml", "Type_of_disposal");
        XMLHelper.removeNode("Update","update.xml", "Remarks");
        XMLHelper.removeNode("Update","update.xml", "Claim_Payment_Cheque_Date");
        XMLHelper.removeNode("Update","update.xml", "Claim_Payment_Cheque_Number");
        XMLHelper.removeNode("Update","update.xml", "Claim_Payment_Date");
        XMLHelper.removeNode("Update","update.xml", "Date_Of_Honoring_Service");
        XMLHelper.removeNode("Update","update.xml", "Insurer_Resolution_Letter_date");
        XMLHelper.removeNode("Update","update.xml", "Others_Clsr_Additional_Info");
        XMLHelper.removeNode("Update","update.xml", "Claim_Clsr_Additional_Info");
        Update resetXmlFile =new Update("update.xml");
        resetXmlFile.setStatusCode("1");
        resetXmlFile.setTransLoginId("SILICADMIN");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }


    @Test(priority = 85)
    public void rop_to_att_with_positive_TC() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        Response response = ropStatusUpdate();
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo = res.getIRDATokenNumber();
        String updateEntityRefNo = res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("4");
        regUp.setTypeOfDisposal("1");
        regUp.setRemarks("Remarks");
        Response attToSecTimeResponse = Request.update("Update", "update.xml");
        UpdateResponse updateRes = new UpdateResponse(attToSecTimeResponse);
        String error_code = updateRes.getErrorCode();
        String entityRef_no= updateRes.getEntityCompRefNumber();
        test_name="Positive_TC_085:Reopen to Attended to ";
        test = extent.createTest(test_name);
        if (entityRef_no.equals(entityRef_no) && error_code.isBlank()) {
            message="Reopen to Attended to   status change done successfully";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        } else {
            message="Reopen to Attended to   status change not done";
            test.log(Status.FAIL,message+ "Error code <b>"+error_code+"</b>");
            logger.error(message+ "Error code "+error_code);
            status = "FAIL";
        }
        XMLHelper.removeNode("Update","update.xml", "Type_of_disposal");
        XMLHelper.removeNode("Update","update.xml", "Remarks");
        Update resetXMLFile = new Update("update.xml");
        resetXMLFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }
}
