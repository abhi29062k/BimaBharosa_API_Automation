package Update;

import Base.Main;
import Constants.BimaBharosaConstants;
import Operations.Update;
import Request.Request;
import Response.UpdateResponse;
import Response.RegisterResponse;
import Utilities.BimabharosaDatabaseHelper;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import javax.xml.transform.TransformerException;
import java.io.IOException;

import static Base.RegisterHelper.registerAgainst_InsCompany_claim;

public class Test_14_status_update_with_diff_transLogin_Id extends Main {
    @Test(priority = 71)
    public void status_update_with_diff_transLogin_Id_negative_TC() throws IOException, TransformerException {
        Response response = registerAgainst_InsCompany_claim();
        RegisterResponse newState = new RegisterResponse(response);
        String tokenNo = newState.getIRDATokenNumber();
        String entityRefNo =newState.getEntityCompRefNumber();

        Update setAckStatus =new Update("update.xml");
        setAckStatus.setIrdaTokenNo(tokenNo);
        setAckStatus.setEntityCompRefNumber(entityRefNo);
        setAckStatus.setStatusCode("2");
        setAckStatus.setTransLoginId("SILICADMIN");
        Response ackResponse = Request.update("Update","update.xml");
        UpdateResponse updateRes = new UpdateResponse(ackResponse);
        String error_code = updateRes.getErrorCode();
        String entityRef_no = updateRes.getEntityCompRefNumber();
        test_name = "Negative_TC_071: Status change with Different Translogin ID(Defect)";
        test=extent.createTest(test_name);
        if (entityRef_no.equals(entityRef_no) && error_code.isBlank()) {
            message = "With diff Translogin Id status should not update but it is getting update.";
            test.log(Status.FAIL, message + " Error code <b>" +error_code +"</b>");
            logger.error(message + " Error code " +error_code);
            status = "FAIL";
        } else {
            message = "With diff Translogin Id status should not update and  it is not getting update.";
            test.log(Status.PASS, message);
            logger.info(message);
        }
        Update resetXmlFile =new Update("update.xml");
        resetXmlFile.setStatusCode("1");

        /*Commented the code as it was implemented for local db*/
        //BimabharosaDatabaseHelper.insertRecord(test_name, Request.getRequestBody(), Request.getResponseBody(), error_code, status, message, getCurrentDateTime(), updateRes.getEntityCompRefNumber(), BimaBharosaConstants.UPDATE);

    }
}
