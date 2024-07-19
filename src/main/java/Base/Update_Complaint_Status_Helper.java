package Base;

import Operations.Update;
import Response.UpdateResponse;
import Request.Request;
import Utilities.XMLHelper;
import io.restassured.response.Response;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public class Update_Complaint_Status_Helper extends Main{
    public static ArrayList<String> returnParameter() throws IOException, TransformerException {
        Response response = RegisterHelper.registerAgainst_InsCompany();
        ArrayList<String> lis = new ArrayList<String>();
        String tokenNo = response.xmlPath().getString("Envelope.Body.RegisterComplaintResponse.RegisterComplaintResult.RESULT.COMPLAINTDETAILS.IRDA_TOKEN_NUMBER");
        String entityRefNo = response.xmlPath().getString("Envelope.Body.RegisterComplaintResponse.RegisterComplaintResult.RESULT.COMPLAINTDETAILS.ENTITY_COMPLAINT_REF_NUMBER");
        String errorCode = response.xmlPath().getString("Envelope.Body.RegisterComplaintResponse.RegisterComplaintResult.RESULT.COMPLAINTDETAILS.ERROR_CODES");
        lis.add(tokenNo);
        lis.add(entityRefNo);
        lis.add(errorCode);
        return lis;
    }
    public static ArrayList<String> returnParameterForPastDate() throws IOException, TransformerException {
        Response response = RegisterHelper.registerAgainst_InsCompany_withPastDateReg();
        ArrayList<String> lis = new ArrayList<String>();
        String tokenNo = response.xmlPath().getString("Envelope.Body.RegisterComplaintResponse.RegisterComplaintResult.RESULT.COMPLAINTDETAILS.IRDA_TOKEN_NUMBER");
        String entityRefNo = response.xmlPath().getString("Envelope.Body.RegisterComplaintResponse.RegisterComplaintResult.RESULT.COMPLAINTDETAILS.ENTITY_COMPLAINT_REF_NUMBER");
        String errorCode = response.xmlPath().getString("Envelope.Body.RegisterComplaintResponse.RegisterComplaintResult.RESULT.COMPLAINTDETAILS.ERROR_CODES");
        lis.add(tokenNo);
        lis.add(entityRefNo);
        lis.add(errorCode);
        return lis;
    }
    public static Response ackStatusUpdate() throws TransformerException, IOException {
        ArrayList<String> param =Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusCode("2");
        regUp.setStatusChangeDate(getCurrentDate());//Updated status change date as current
        // Save Updated payload

        Response UpdateResponse = Request.update("Update","update.xml");
//        String updateTokenNo = UpdateResponse.xmlPath().getString("Envelope.Body.UpdateComplaintResponse.UpdateComplaintResult.RESULT.COMPLAINTDETAILS.IRDA_TOKEN_NUMBER");
//        String updatEntityRefNo = UpdateResponse.xmlPath().getString("Envelope.Body.UpdateComplaintResponse.UpdateComplaintResult.RESULT.COMPLAINTDETAILS.ENTITY_COMPLAINT_REF_NUMBER");
        return UpdateResponse;
    }
    public static Response penStatusUpdate() throws IOException, TransformerException {
        Response response =Update_Complaint_Status_Helper.ackStatusUpdate();
        // function to get IRDATOKEN No and Entity ref no from response payload
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo =res.getIRDATokenNumber();
        String updateEntityRefNo =res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusChangeDate(getCurrentDate());
        regUp.setStatusCode("3");
        // Save Updated payload

        Response UpdateResponse = Request.update("Update","update.xml");
        return UpdateResponse;
    }
    public static Response attStatusUpdate() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response =Update_Complaint_Status_Helper.penStatusUpdate();
        // function to get IRDATOKEN No and Entity ref no from response payload
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo =res.getIRDATokenNumber();
        String updateEntityRefNo =res.getEntityCompRefNumber();
        // Set values to update payload
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("4");
        regUp.setStatusChangeDate(getCurrentDate());
        regUp.setTypeOfDisposal("1");
        regUp.setRemarks("test me");
        // Save Updated payload
        Response UpdateResponse = Request.update("Update","update.xml");
        XMLHelper.removeNode("Update","update.xml", "Type_of_disposal");
        XMLHelper.removeNode("Update","update.xml", "Remarks");
        return UpdateResponse;
    }
    public static Response escStatusUpdate() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response =Update_Complaint_Status_Helper.attStatusUpdate();
        // function to get IRDATOKEN No and Entity ref no from response payload
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo =res.getIRDATokenNumber();
        String updateEntityRefNo =res.getEntityCompRefNumber();
        // Set values to update payload
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("5");
        regUp.setStatusChangeDate(getCurrentDate());
        regUp.setEscalatedRemark("Escalated_Remark");
        // Save Updated payload

        Response UpdateResponse = Request.update("Update","update.xml");
        XMLHelper.removeNode("Update","update.xml", "Escalated_Remark");
//        String updateTokenNo = UpdateResponse.xmlPath().getString("Envelope.Body.RegisterComplaintResponse.RegisterComplaintResult.RESULT.COMPLAINTDETAILS.IRDA_TOKEN_NUMBER");
//        String updatEntityRefNo = UpdateResponse.xmlPath().getString("Envelope.Body.RegisterComplaintResponse.RegisterComplaintResult.RESULT.COMPLAINTDETAILS.ENTITY_COMPLAINT_REF_NUMBER");
        return UpdateResponse;
    }
    public static Response ropStatusUpdate() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response param =Update_Complaint_Status_Helper.escStatusUpdate();
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(param.xmlPath().getString("Envelope.Body.UpdateComplaintResponse.UpdateComplaintResult.RESULT.COMPLAINTDETAILS.IRDA_TOKEN_NUMBER"));
        regUp.setEntityCompRefNumber(param.xmlPath().getString("Envelope.Body.UpdateComplaintResponse.UpdateComplaintResult.RESULT.COMPLAINTDETAILS.ENTITY_COMPLAINT_REF_NUMBER"));
        regUp.setStatusCode("6");
        regUp.setStatusChangeDate(getCurrentDate());

        Response UpdateResponse = Request.update("Update","update.xml");
//        String updateTokenNo = UpdateResponse.xmlPath().getString("Envelope.Body.RegisterComplaintResponse.RegisterComplaintResult.RESULT.COMPLAINTDETAILS.IRDA_TOKEN_NUMBER");
//        String updatEntityRefNo = UpdateResponse.xmlPath().getString("Envelope.Body.RegisterComplaintResponse.RegisterComplaintResult.RESULT.COMPLAINTDETAILS.ENTITY_COMPLAINT_REF_NUMBER");
//        System.out.println(updateTokenNo);
//        System.out.println(updatEntityRefNo);
        return UpdateResponse;
    }
    public static Response closedComplaint() throws IOException, TransformerException, ParserConfigurationException, SAXException {
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
        attToState.setRemarks("test me");
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
        return closeResponse;
    }



}
