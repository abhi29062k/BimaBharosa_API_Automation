package Update;

import Base.Main;
import Base.Update_Complaint_Status_Helper;
import Constants.BimaBharosaConstants;
import Operations.Update;
import Response.UpdateResponse;
import Request.Request;
import Utilities.BimabharosaDatabaseHelper;
import Utilities.XMLHelper;
import Utilities.XMLReader;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Base.Main.logger;
import static Utilities.ErrorCodes.*;
import static Utilities.ExtentReport.extent;
import static Utilities.ExtentReport.test;

public class Test_5_Ack_Pen_MandateField_Validation extends Main {

    @Test(priority = 25)
    public void Update_Status_Without_Status_Change_Date_field_negative_TC() throws TransformerException, IOException {
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusCode("2");
        regUp.setStatusChangeDate("");
        Response UpdateResponse = Request.update("Update", "update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_025:Validation for Update status without status change date field";
        test = extent.createTest(test_name);
        if (error_code.equals(STATUS_CHANGE_DATE_MANDATORY)) {
            message="Status is not Updated without Status Change Date field";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        } else {
            message= "Status is Updated without Status Change Date field";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        Update resetXmlFile = new Update("update.xml");
        resetXmlFile.setStatusCode("1");
        resetXmlFile.setStatusChangeDate(getCurrentDate());

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }

    @Test(priority = 26)
    public void Update_Status_Without_IRDA_Token_Number_field_negative_TC() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo("");
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusCode("2");
        regUp.setStatusChangeDate(getCurrentDate());
        Response UpdateResponse = Request.update("Update", "update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        // Save Updated payload
        test_name = "Negative_TC_026:Validation for Update status without IRDA Token Number field";
        test = extent.createTest(test_name);
        if (error_code.equals(IRDA_TOKEN_NUMBER_MANDATORY)) {
            message="Status is not Updated without IRDA Token Number field with error code";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        } else {
            message="Status is Updated without IRDA Token Number field";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        Update resetXmlFile = new Update("update.xml");
        resetXmlFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }

    @Test(priority = 27)
    public void Update_Status_With_null_Entity_Complaint_Ref_Number_field_negative_TC() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setStatusCode("2");
        regUp.setEntityCompRefNumber("");
        regUp.setStatusChangeDate(getCurrentDate());
        Response UpdateResponse = Request.update("Update", "update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_027:Validation for Update status with null Entity Complaint Ref Number field";
        test = extent.createTest(test_name);
        if (error_code.equals(ENTITY_REF_NUMBER_MANDATORY)) {
            message="Status is not Updated with null Complaint Ref field";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        } else {
            message ="Status is Updated with null Entity Complaint Ref field";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        Update resetXmlFile = new Update("update.xml");
        resetXmlFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }

    @Test(priority = 28)
    public void Update_Status_Without_Complaint_Status_Id_field_negative_TC() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusCode("");
        regUp.setStatusChangeDate(getCurrentDate());
        Response UpdateResponse = Request.update("Update", "update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        String ref_number = updateRes.getEntityCompRefNumber();
        test_name="Negative_TC_028:Validation for Update status without Complaint Status Id field(Defect)";
        test = extent.createTest(test_name);
        if (error_code.isBlank() && ref_number.equals(param.get(1))) {
            message="Status is Updated without Complaint Status Id field. ";
            test.log(Status.FAIL,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "FAIL";
       } else {
            message= "Status is not Updated without Complaint Status Id field";
            test.log(Status.PASS,message);
            logger.error(message);
            status = "PASS";
       }
        Update resetXmlFile = new Update("update.xml");
        resetXmlFile.setStatusCode("1");
        resetXmlFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }


    @Test(priority = 29)
    public void Update_Status_Without_Trans_Login_Id_Field_negative_TC() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setStatusCode("2");
        regUp.setTransLoginId("");
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusChangeDate(getCurrentDate());
        Response UpdateResponse = Request.update("Update", "update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_029:Validation for Update status without Trans Login Id field";
        test = extent.createTest(test_name);
        if (error_code.equals(TRANS_LOGIN_ID_MANDATORY)) {
            message="Status is not Updated without Trans Login Id field. With error code ";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        } else {
            message= "Status is Updated without Trans Login Id field";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        Update resetXmlFile = new Update("update.xml");
        resetXmlFile.setStatusCode("1");
        resetXmlFile.setTransLoginId("SILICADMIN");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }

    @Test(priority = 30)
    public void Update_Status_Without_Option_Field_negative_TC() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setStatusCode("2");
        regUp.setTransLoginId("SILICADMIN");
        regUp.setOptionField("");
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusChangeDate(getCurrentDate());
        Response UpdateResponse = Request.update("Update", "update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_030:Validation for Update status without Option field";
        test = extent.createTest(test_name);
        if (error_code.equals(OPTION_MANDATORY)) {
         message="Status is not Updated without Option field. ";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        } else {
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        Update resetXmlFile = new Update("update.xml");
        resetXmlFile.setStatusCode("1");
        resetXmlFile.setOptionField("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }

    @Test(priority = 31)
    public void Update_Status_Without_Mode_Field_negative_TC() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setStatusCode("2");
        regUp.setTransLoginId("SILICADMIN");
        regUp.setOptionField("1");
        regUp.setModeField("");
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusChangeDate(getCurrentDate());
        Response UpdateResponse = Request.update("Update", "update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_031:Validation for Update status without Mode field";
        test = extent.createTest(test_name);
        if (error_code.equals(MODE_MANDATORY)) {
            message= "Status is not Updated without Mode field. ";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        } else {
            message="Status is Updated without Mode field";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        Update resetXmlFile = new Update("update.xml");
        resetXmlFile.setStatusCode("1");
        resetXmlFile.setModeField("2");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }


    @Test(priority = 32)
    public void Update_Status_Invalid_Mode_Field_negative_TC() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusCode("2");
        regUp.setTransLoginId("SILICADMIN");
        regUp.setOptionField("1");
        regUp.setModeField("1");
        regUp.setStatusChangeDate(getCurrentDate());
        Response UpdateResponse = Request.update("Update", "update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        test_name="Negative_TC_032:Validation for Update status Invalid Mode field";
        test = extent.createTest(test_name);
        if (error_code.equals(INVALID_MODE_MANDATORY)) {
            message= "Status is not Updated Invalid Mode field";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        } else {
            message= "Status is Updated Invalid Mode field";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        Update updatemode = new Update("update.xml");
        updatemode.setStatusCode("1");
        updatemode.setModeField("2");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }


    @Test(priority = 33)
    public void Update_Status_With_Removed_Entity_ref_Id_field_negative_TC() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        test_name="Negative_TC_033:Validation for Update status with removed Entity ref Id field(Defect)";
        test = extent.createTest(test_name);
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        // Set values to update payload
        regUp.setIrdaTokenNo(param.get(0));
//        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusCode("2");
        regUp.setStatusChangeDate(getCurrentDate());
        XMLHelper.removeNode("Update","update.xml", "Entity_Complaint_Ref_Number");
        Response UpdateResponse = Request.update("Update", "update.xml");

//        // Save Updated payload

//        if (error_code.isBlank() && ref_number.equals(param.get(1))) {
//            test.log(Status.FAIL, "Status is Updated with removed Entity ref Id field  " );
//            logger.info("Status is Updated with removed Entity ref Id field  " );
//        } else {
//            test.log(Status.PASS, "Status is not Updated with removed Entity ref Id field. With error code: "+error_code);
//            logger.error("Status is not Updated with removed  Entity ref Id field. With error code: "+error_code);
//        }
        Update resetXmlFile = new Update("update.xml");

        resetXmlFile.setEntityCompRefNumber(generateEntityReferenceNo());
        resetXmlFile.setStatusCode("1");
        XMLReader.formatXMLFile("Update","update.xml");

        List<String> lst = Request.getTestDetail();

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), null, null, lst.get(0), lst.get(1), getCurrentDateTime(), null, BimaBharosaConstants.UPDATE);


    }

}
