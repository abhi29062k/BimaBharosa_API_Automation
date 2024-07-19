package Update;

import Base.Main;
import Base.Update_Complaint_Status_Helper;
import Constants.BimaBharosaConstants;
import Operations.Update;
import Response.UpdateResponse;
import Request.Request;
import Utilities.XMLHelper;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import static Base.Update_Complaint_Status_Helper.*;
import static Utilities.ErrorCodes.IS_DOES_NOT_PERTAIN_REMARKS_NOT_NULL;
import Utilities.BimabharosaDatabaseHelper;


public class Test_12_not_pertain_to extends Main {

    @Test(priority =66)
    //New to not pertain
    public void new_not_pertain_to_positive_TC() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusCode("1");
        regUp.setIS_Does_Not_Pertain("1");
        regUp.setISDoesNotPertain_Remarks("Remarks");
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();
        XMLHelper.removeNode("Update","update.xml", "IS_Does_Not_Pertain");
        XMLHelper.removeNode("Update","update.xml", "ISDoesNotPertain_Remarks");
        test_name="Positive_TC_066:Not pertain  to in new stage";
        test=extent.createTest(test_name);
        if (entityRef_no.equals(param.get(1)) && error_code.isBlank()) {
            message="Complaint closed for reason Not pertain  to in new stage successfully";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";

        } else {
            message="Complaint not closed even reason is Not pertain  to in new stage";
            test.log(Status.FAIL,message+ "Error code <b>"+error_code+"</b>");
            logger.error(message+ "Error code "+error_code);
            status = "FAIL";

        }
        Update resetXMLFile = new Update("update.xml");
        resetXMLFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }

    @Test(priority =67)
    //Ack to not pertain
    public void ack_not_pertain_to_positive_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        Response response = Update_Complaint_Status_Helper.ackStatusUpdate();
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo =res.getIRDATokenNumber();
        String updatEntityRefNo =res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updatEntityRefNo);
        regUp.setIS_Does_Not_Pertain("1");
        regUp.setISDoesNotPertain_Remarks("Remarks");
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();
        XMLHelper.removeNode("Update","update.xml", "IS_Does_Not_Pertain");
        XMLHelper.removeNode("Update","update.xml", "ISDoesNotPertain_Remarks");
        test_name="Positive_TC_067:Not pertain  to in Acknowledged stage";
        test=extent.createTest(test_name);
        if (entityRef_no.equals(updatEntityRefNo) && error_code.isBlank()) {
            message="Complaint closed for reason Not pertain  to in Acknowledged stage successfully";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        } else {
            message="Complaint not closed even reason is Not pertain  to in Pending stage";
            test.log(Status.FAIL,message+ "Error code <b>"+error_code+"</b>");
            logger.error(message+ "Error code "+error_code);
            status = "FAIL";
        }
        Update resetXmlFile = new Update("update.xml");
        resetXmlFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }

    @Test(priority =68)
    //Pending to not pertain
    public void pending_not_pertain_to_positive_TC() throws TransformerException, IOException, ParserConfigurationException, SAXException {
        //
        Response response = Update_Complaint_Status_Helper.penStatusUpdate();
        UpdateResponse res = new UpdateResponse(response);
        String updateTokenNo = res.getIRDATokenNumber();
        String updateEntityRefNo = res.getEntityCompRefNumber();
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(updateTokenNo);
        regUp.setEntityCompRefNumber(updateEntityRefNo);
        regUp.setStatusCode("3");
        regUp.setIS_Does_Not_Pertain("1");
        regUp.setISDoesNotPertain_Remarks("Remarks");
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();        XMLHelper.removeNode("Update","update.xml", "IS_Does_Not_Pertain");
        XMLHelper.removeNode("Update","update.xml", "ISDoesNotPertain_Remarks");
        test_name="Positive_TC_068:Not pertain  to in Pending stage";
        test=extent.createTest(test_name);
        if (entityRef_no.equals(updateEntityRefNo) && error_code.isBlank()) {
            message="Complaint closed for reason Not pertain  to in Pending stage successfully";
            test.log(Status.PASS,message);
            logger.info(message);
            status = "PASS";
        } else {
            message="Complaint not closed even reason is Not pertain  to in Pending stage";
            test.log(Status.FAIL,message+ "Error code <b>"+error_code+"</b>");
            logger.error(message+ "Error code "+error_code);
            status = "FAIL";
        }
        Update resetXmlFile = new Update("update.xml");
        resetXmlFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);
    }


    @Test(priority =69)
    //Without remark field mandatory field
    public void without_mand_field_negative_TC() throws IOException, TransformerException, ParserConfigurationException, SAXException {
        ArrayList<String> param = Update_Complaint_Status_Helper.returnParameter();
        Update regUp = new Update("update.xml");
        regUp.setIrdaTokenNo(param.get(0));
        regUp.setEntityCompRefNumber(param.get(1));
        regUp.setStatusCode("1");
        regUp.setIS_Does_Not_Pertain("1");
        Response UpdateResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(UpdateResponse);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();        XMLHelper.removeNode("Update","update.xml", "IS_Does_Not_Pertain");
        XMLHelper.removeNode("Update","update.xml", "ISDoesNotPertain_Remarks");
        test_name="Negative_TC_069: Without Mandatory field not pertain to check";
        test=extent.createTest(test_name);
        if (error_code.equals(IS_DOES_NOT_PERTAIN_REMARKS_NOT_NULL)) {
            message="Complaint not closed for reason Not pertain to as mandatory field is not present";
            test.log(Status.PASS,message+ "Error code <b>"+error_code+"</b>");
            logger.info(message+ "Error code "+error_code);
            status = "PASS";
        } else {
            message="Complaint  closed for reason Not pertain to. ";
            test.log(Status.FAIL,message);
            logger.error(message);
            status = "FAIL";
        }
        Update resetXmlFile = new Update("update.xml");
        resetXmlFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }
}
